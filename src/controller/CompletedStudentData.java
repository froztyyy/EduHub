/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author jcarl
 */
public class CompletedStudentData {

    public CompletedStudentData(String studentID, String surname, String title, String note, String deadline) {
        this.studentID = studentID;
        this.surname = surname;
        this.title = title;
        this.note = note;
        this.deadline = deadline;
    }

    private String studentID;
    private String surname;
    private String title;
    private String note;
    private String deadline;


    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
   

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }
  
    // getters and setters

    public CompletedStudentData() {
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

