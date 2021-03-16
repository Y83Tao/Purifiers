package com.projectgrapes.game.Resources;

public class Mail {
    private String title;
    private String message;
    Mail(String title, String message){
        this.title = title;
        this.message = message;
    }
    public String getTitle(){return title;}
    public String getMessage(){return message;}
    public void changeTitle(String title){this.title = title;}
}
