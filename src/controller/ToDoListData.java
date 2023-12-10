/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author jcarl
 */
public class ToDoListData {

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getAudience() {
        return audience;
    }

    public void setAudience(String audience) {
        this.audience = audience;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getUser_CourseID() {
        return user_CourseID;
    }

    public void setUser_CourseID(String user_CourseID) {
        this.user_CourseID = user_CourseID;
    }

    public String getUser_SectionID() {
        return user_SectionID;
    }

    public void setUser_SectionID(String user_SectionID) {
        this.user_SectionID = user_SectionID;
    }

    public String getUser_Surname() {
        return user_Surname;
    }

    public void setUser_Surname(String user_Surname) {
        this.user_Surname = user_Surname;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getTodoId() {
        return todoId;
    }

    public void setTodoId(int todoId) {
        this.todoId = todoId;
    }

    private int todoId;
    private String title;
    private String body;
    private String deadline;
    private String audience;
    private String priority;
    private String user_CourseID;
    private String user_SectionID;
    private String user_Surname;

    public String getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }
    private String postDate;

    public String getUser_StudentID() {
        return user_StudentID;
    }

    public void setUser_StudentID(String user_StudentID) {
        this.user_StudentID = user_StudentID;
    }
    private String user_StudentID;

    public ToDoListData(String title, String body, String deadline, String audience, String priority, String user_CourseID, String user_SectionID, String user_Surname, String user_StudentID, String postDate, String todoID) {
        this.title = title;
        this.body = body;
        this.deadline = deadline;
        this.audience = audience;
        this.priority = priority;
        this.user_CourseID = user_CourseID;
        this.user_SectionID = user_SectionID;
        this.user_Surname = user_Surname;
        this.user_StudentID = user_StudentID;
        this.postDate = postDate;
    }

    public ToDoListData(int todoId, String title, String body, String deadline, String audience, String priority, String user_CourseID, String user_SectionID, String user_Surname, String user_StudentID, String postDate) {
        this.todoId = todoId;
        this.title = title;
        this.body = body;
        this.deadline = deadline;
        this.audience = audience;
        this.priority = priority;
        this.user_CourseID = user_CourseID;
        this.user_SectionID = user_SectionID;
        this.user_Surname = user_Surname;
        this.user_StudentID = user_StudentID;
        this.postDate = postDate;
    }

}
