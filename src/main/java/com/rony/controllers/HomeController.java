package com.rony.controllers;

import com.rony.ApplicationProperties;
import com.rony.config.HibernateConfig;
import com.rony.config.Initializer;
import com.rony.enums.Countries;
import com.rony.enums.UserRole;
import com.rony.exceptions.InternalServerException;
import com.rony.models.Country;
import com.rony.models.User;
import com.rony.requestDto.UserReqDto;
import com.rony.services.CountryService;
import com.rony.services.RoleService;
import com.rony.services.UserService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.awt.geom.QuadCurve2D;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

@PropertySource("classpath:application.properties")
@Controller
public class HomeController {

    @Autowired
    private HibernateConfig hibernateConfig;
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleService roleService;

    @Autowired
    private CountryService countryService;

    @Autowired
    private Environment environment;




    private static final Logger logger = LogManager.getLogger(HomeController.class);

    public HomeController(UserService userService, HibernateConfig hibernateConfig, PasswordEncoder passwordEncoder, RoleService roleService) {
        this.userService = userService;
        this.hibernateConfig = hibernateConfig;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }


    @GetMapping("/")
    public String home(Model model, HttpServletRequest request){

        System.out.println(environment.getProperty("message"));

        logger.info("application started"+ new Date());
        logger.info("initilizer called");
        Initializer initializer = new Initializer(roleService, userService, passwordEncoder, hibernateConfig, countryService);
        logger.info("initilizer finished");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String role = authentication.getAuthorities().stream()
                .findFirst().get().getAuthority();
        logger.info("your role is --------> " + role);

        // check for a particular role
        boolean whichRole = authentication.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ROLE_ICC_AUTHORITY"));

        // to get all the roles
        Set<String> roles = authentication.getAuthorities().stream()
                .map(r -> r.getAuthority()).collect(Collectors.toSet());

        for(String r : roles){
            logger.info("role from for looop : " + r);
        }


        return "index";
    }

//    @GetMapping({"/hello",""})
//    public String home(Model model){
//        return "index";
//    }

    @GetMapping("/success")
    public String success(Model model, HttpServletRequest request){
        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String role = authentication.getAuthorities().stream()
                    .findFirst().get().getAuthority();
            User cm = (User) authentication.getPrincipal();

//            var country = countryService.allCountries().stream()
//                    .filter(c -> c.getManagingDirector().getId() == cm.getId())
//                    .findFirst()
//                    .get();
//            var cid = country.getId();

            HttpSession session = request.getSession();
            model.addAttribute("msg",role );

            boolean whichRole = role.equals("ROLE_TEAM_MANAGER");

            if(cm.getCountry() != null && whichRole){
                String cid = String.valueOf(cm.getCountry().getId());
                session.setAttribute("cid",cid);
                logger.info("country id from homeController -> success() : " + cid);
            }else{
                logger.info("not a country manager ! ");
            }

            return "successPage";
        }catch (Exception e){
            e.printStackTrace();
            logger.info("success page exception");
            throw new InternalServerException("Success page exception occurred");
        }

    }

    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "logout", required = false) String logout,
                        Model model, HttpSession session){
        session.invalidate();
        logger.info("someone tried to login ?");
        String errorMessge = null;
        if(error != null) {
            errorMessge = "Username or Password is incorrect !!";
        }
        model.addAttribute("errorMsg", errorMessge);
        return "login";
    }

    @GetMapping("/logout")
    public String logout(Model model, HttpSession session){
        logger.info("logging out ? ");
        session.invalidate();
        logger.info("logged out success ! ");
        return "login";
    }

    @GetMapping("/register")
    public String registrationForm(Model model,  HttpSession session){
        session.invalidate();
        model.addAttribute("user", new UserReqDto());
        model.addAttribute("genders",userService.getGenders());
        model.addAttribute("homeTowns",userService.getHomeTowns());
        model.addAttribute("salutations",userService.getSalutations());
        model.addAttribute("countries",countryService.CountryRespDtoList());
        return "registration";
    }

    @PostMapping("/register")
    public String processRegistration(Model model,  HttpServletRequest servletRequest,
                                      @Valid @ModelAttribute(name = "user") UserReqDto userReqDto,
                                      BindingResult errors){

        String errorMsg = "";
        if(errors.hasErrors()){
            for(ObjectError error: errors.getAllErrors()){
                errorMsg += error.getDefaultMessage()+"<br>";
                logger.error("errror :"+ error.getDefaultMessage());
            }
            model.addAttribute("errorMsg", errorMsg);
            model.addAttribute("genders",userService.getGenders());
            model.addAttribute("homeTowns",userService.getHomeTowns());
            model.addAttribute("salutations",userService.getSalutations());
            model.addAttribute("countries",countryService.CountryRespDtoList());
            return "registration";
        }else {
            //Get the uploaded files and store them
            List<MultipartFile> files = userReqDto.getImages();
            List<String> fileNames = new ArrayList<String>();
            if (null != files && files.size() > 0) {
                for (MultipartFile multipartFile : files) {
                    String fileName = multipartFile.getOriginalFilename();
                    fileNames.add(fileName);
                    File imageFile = new File(servletRequest.getServletContext().getRealPath("/image"), fileName);
                    try {
                        multipartFile.transferTo(imageFile);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
//            user.setUserRole(roleService.findByRoleName("ROLE_USER"));
            userService.addUser(userReqDto);
//            logger.info("registration success ! "+userReqDto.getEmail());
            return "redirect:/login";
        }
    }

    @GetMapping("/fileUpload")
    public String fileUpload(Model model){
        return "fileUpload";
    }

    public static String TEMP_PATH = System.getProperty("user.home") + "/icc-management/temp";
    public static String WRITE_PATH = System.getProperty("user.home") + "/icc-management/temp";
    public static String READ_FROM_PATH = System.getProperty("user.home") + "/icc-management";
    private static final String UPLOADED_FOLDER = WRITE_PATH;

    @PostMapping("/uploadImage")
    public String upload(Model model, HttpSession session, @RequestParam("profile") CommonsMultipartFile file){

        User userEntity = (User) session.getAttribute("usr");
        Long userId = userEntity.getId();
        Long attachmentId = System.currentTimeMillis();
        try {

            String path = session.getServletContext().getRealPath("/");
            System.out.println(path);
            byte[] data = file.getBytes();
            logger.info("file upload success !");


            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();


            File dir = new File(UPLOADED_FOLDER  + "/" + userId + "/");
            if (!dir.exists()) {
                dir.mkdirs();
            }


            File myDir = new File(path+"/"+"WEB-INF"+"/"+"resouces"+"/"+"images"+"/"+"uploads"+"/");
//            if (!myDir.exists()) {
//                myDir.mkdirs();
//            }

            String extension = "";
            StringTokenizer tokenizer = new StringTokenizer(file.getOriginalFilename(), ".");
            while (tokenizer.hasMoreTokens()) {
                extension = tokenizer.nextToken();
            }
            String url = UPLOADED_FOLDER + "/" + userId + "/" + attachmentId + "." + extension;
            String myUrl = path+"/"+"WEB-INF"+"/"+"resouces"+"/"+"images"+"/"+"uploads"+"/";

            File serverFile = new File(url);

            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
            stream.write(bytes);
            stream.close();

            File myServerFile = new File(myUrl);
            BufferedOutputStream myStream = new BufferedOutputStream(new FileOutputStream(myUrl));
            stream.write(data);
            stream.close();
            logger.info("File written successfully.");
//            url = "/temp/" + "userId" + "/" + attachment.getAttachmentId() + "." + extension;
//            attachment.setFileURL(url);
//            attachment.setFileType(Files.probeContentType(Paths.get(url)));
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("file upload error !");
            logger.error("file upload error !");
        }

        return  "/";
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied_GET() {
        logger.info("Access Denied page ");
        return "/errors/error_403";
    }

    @RequestMapping(value = "/404", method = RequestMethod.GET)
    public String notFound_GET() {
        logger.info("Page not found  ");
        return "/errors/error_404";
    }

    @RequestMapping(value = "/500", method = RequestMethod.GET)
    public String internalServerError_GET() {
        logger.info("Internal server eroor  ");
        return "/errors/error_500";
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder){
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

//    @PostMapping("/login")
//    public String login(Model model, @RequestParam("email") String email,
//                          @RequestParam("password") String password,
//                        HttpServletRequest request){
//       try{
//           if(!userService.validateUser(email,password)) {
//               System.err.println("invalid user");
//               model.addAttribute("msg","invalid email/password");
//               return login(model);
//           }
//       }catch (Exception e){
//           System.err.println("exception of login post in HomeController");
//           return login(model);
//       }
//        model.addAttribute("msg","logged in success!");
//        System.err.println("valid user");
//        System.out.println("logged in bro ! ");
//        model.addAttribute("msg","logged in bro ! ");
//        HttpSession session = request.getSession();
//        session.setAttribute("email", email);
//        var user = userService.getUserByEmail(email);
//        session.setAttribute("role",user.getUserRole());
//        System.err.println("role setting : "+user.getUserRole());
//        return home(model,session);
//    }


}
