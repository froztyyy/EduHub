/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author lugtu
 */
public class GetArchiveFeedBack {

    public int getDesignRating() {
        return designRating;
    }

    public void setDesignRating(int designRating) {
        this.designRating = designRating;
    }

    public int getFunctionRating() {
        return functionRating;
    }

    public void setFunctionRating(int functionRating) {
        this.functionRating = functionRating;
    }

    public int getExperienceRating() {
        return experienceRating;
    }

    public void setExperienceRating(int experienceRating) {
        this.experienceRating = experienceRating;
    }

    public String getFeedbackReport() {
        return feedbackReport;
    }

    public void setFeedbackReport(String feedbackReport) {
        this.feedbackReport = feedbackReport;
    }

    private int designRating;
    private int functionRating;
    private int experienceRating;
    private String feedbackReport;

}
