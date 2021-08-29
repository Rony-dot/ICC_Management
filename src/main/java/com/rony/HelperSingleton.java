package com.rony;

public class HelperSingleton {
    private static HelperSingleton helperSingleton; // = new HelperSingleton();

    private HelperSingleton() {
    }

    public static HelperSingleton getInstance(){
        if(helperSingleton==null){
            helperSingleton = new HelperSingleton();
        }
        return  helperSingleton;
    }

    public void printMsg(String msg){
        System.out.println(msg);
    }

    public void printErr(String err){
        System.err.println(err);
    }



}
