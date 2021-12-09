package com.asdc.payroll_management.DataBaseCache;

public class Appraisal {
    private String employeeID;
    private String managerID;
    private String employeeRating;
    private String eployeeComments;
    private String managerRating;
    private String managerComments;
    private String employeeProjects;
    private String technologiesLearnt;
    private String finalRating;
    private String communicationRating;
    private String projectsRating;

    public Appraisal(String employeeID, String managerID, String employeeRating, String eployeeComments, String managerRating, String managerComments, String employeeProjects, String technologiesLearnt, String finalRating, String communicationRating, String projectsRating) {
        this.employeeID = employeeID;
        this.managerID = managerID;
        this.employeeRating = employeeRating;
        this.eployeeComments = eployeeComments;
        this.managerRating = managerRating;
        this.managerComments = managerComments;
        this.employeeProjects = employeeProjects;
        this.technologiesLearnt = technologiesLearnt;
        this.finalRating = finalRating;
        this.communicationRating = communicationRating;
        this.projectsRating = projectsRating;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public String getManagerID() {
        return managerID;
    }

    public void setManagerID(String managerID) {
        this.managerID = managerID;
    }

    public String getEmployeeRating() {
        return employeeRating;
    }

    public void setEmployeeRating(String employeeRating) {
        this.employeeRating = employeeRating;
    }

    public String getEployeeComments() {
        return eployeeComments;
    }

    public void setEployeeComments(String eployeeComments) {
        this.eployeeComments = eployeeComments;
    }

    public String getManagerRating() {
        return managerRating;
    }

    public void setManagerRating(String managerRating) {
        this.managerRating = managerRating;
    }

    public String getManagerComments() {
        return managerComments;
    }

    public void setManagerComments(String managerComments) {
        this.managerComments = managerComments;
    }

    public String getEmployeeProjects() {
        return employeeProjects;
    }

    public void setEmployeeProjects(String employeeProjects) {
        this.employeeProjects = employeeProjects;
    }

    public String getTechnologiesLearnt() {
        return technologiesLearnt;
    }

    public void setTechnologiesLearnt(String technologiesLearnt) {
        this.technologiesLearnt = technologiesLearnt;
    }

    public String getFinalRating() {
        return finalRating;
    }

    public void setFinalRating(String finalRating) {
        this.finalRating = finalRating;
    }

    public String getCommunicationRating() {
        return communicationRating;
    }

    public void setCommunicationRating(String communicationRating) {
        this.communicationRating = communicationRating;
    }

    public String getProjectsRating() {
        return projectsRating;
    }

    public void setProjectsRating(String projectsRating) {
        this.projectsRating = projectsRating;
    }
}
