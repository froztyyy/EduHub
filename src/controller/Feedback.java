/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author lugtu
 */
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Feedback {
    private String designRating;
    private String functionRating;
    private String experienceRating;
    private String feedbackReport;

    // Constructors

    // Getter and Setter methods for designRating
    public String getDesignRating() {
        return designRating;
    }

    public void setDesignRating(String designRating) {
        this.designRating = designRating;
    }

    // Getter and Setter methods for functionRating
    public String getFunctionRating() {
        return functionRating;
    }

    public void setFunctionRating(String functionRating) {
        this.functionRating = functionRating;
    }

    // Getter and Setter methods for experienceRating
    public String getExperienceRating() {
        return experienceRating;
    }

    public void setExperienceRating(String experienceRating) {
        this.experienceRating = experienceRating;
    }

    // Getter and Setter methods for feedbackReport
    public String getFeedbackReport() {
        return feedbackReport;
    }

    public void setFeedbackReport(String feedbackReport) {
        this.feedbackReport = feedbackReport;
    }
}