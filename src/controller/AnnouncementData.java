/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author PC
 */
public class AnnouncementData {

    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return text;
    }

    public void setBody(String text) {
        this.text= text;
    }
    

    private String title;
    private String text;
    public AnnouncementData (String title, String text) {
        this.title = title;
        this.text = text;
 
    }
}

   
    
    
    
