/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author lugtu
 */
public class ControllerManager {

    private static UserDashboardController userDashboardController;

    public static UserDashboardController getUserDashboardController() {
        return userDashboardController;
    }

    public static void setUserDashboardController(UserDashboardController userDashboardController) {
        ControllerManager.userDashboardController = userDashboardController;
    }
}
