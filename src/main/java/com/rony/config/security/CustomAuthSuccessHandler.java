package com.rony.config.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Logger;
import com.rony.models.User;

public class CustomAuthSuccessHandler implements AuthenticationSuccessHandler {
    private Logger logger = Logger.getLogger(CustomAuthSuccessHandler.class.getName());
    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            Authentication authentication
    ) throws IOException, ServletException {
        HttpSession session = httpServletRequest.getSession();
        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        session.setAttribute("usr", authUser);
        session.setAttribute("id", authUser.getId());
        session.setAttribute("username", authUser.getUsername().split("@")[0]);
        session.setAttribute("authorities", authentication.getAuthorities());
        System.out.println("Authenticated user = "+authUser.toString());

        Authentication authentication2 = SecurityContextHolder.getContext().getAuthentication();
        var role = authentication2.getAuthorities().stream()
                .findFirst().get().getAuthority();
        var cm = (User) authentication2.getPrincipal();

        boolean whichRole = role.equals("ROLE_TEAM_MANAGER");

        if(cm.getCountry() != null && whichRole){
            var cid = String.valueOf(cm.getCountry().getId());
            session.setAttribute("cid",cid);
            logger.info("country id from homeController -> success() : " + cid);
        }else{
            logger.info("not a country manager ! ");
        }


        //set our response to OK status
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);

        //since we have created our custom success handler, its up to us to where
        //we will redirect the user after successfully login
        SavedRequest savedRequest = new HttpSessionRequestCache()
                .getRequest(httpServletRequest, httpServletResponse);
//        String requestUrl = savedRequest.getRedirectUrl();
        httpServletResponse.sendRedirect("/"); //requestUrl!=null?requestUrl:
    }
}
