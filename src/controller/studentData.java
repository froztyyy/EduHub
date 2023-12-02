/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author jcarl
 */
public class studentData {
    private int studentID;
    private String password;
    private String surname;
    private String firstname;
    private String middlename;
    private String suffix;
    private int courseID;
    private int sectionID;

    // Constructor
    public studentData(int studentID, String password, String surname, String firstname, String middlename, String suffix, int courseID, int sectionID) {
        this.studentID = studentID;
        this.password = password;
        this.surname = surname;
        this.firstname = firstname;
        this.middlename = middlename;
        this.suffix = suffix;
        this.courseID = courseID;
        this.sectionID = sectionID;
    }

    // Getters (and optionally setters)
    public int getStudentID() {
        return studentID;
    }

    public String getPassword() {
        return password;
    }

    public String getSurname() {
        return surname;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public String getSuffix() {
        return suffix;
    }

    public int getCourseID() {
        return courseID;
    }

    public int getSectionID() {
        return sectionID;
    }
}
