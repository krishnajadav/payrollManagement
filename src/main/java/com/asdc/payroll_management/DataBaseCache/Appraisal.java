package com.asdc.payroll_management.DataBaseCache;

public class Appraisal {
    private String employee_ID;
    private String manager_ID;
    private String employee_rating;
    private String eployee_comments;
    private String manager_rating;
    private String manager_comments;
    private String employee_projects;
    private String technologies_learnt;
    private String final_rating;
    private String communication_rating;
    private String projects_rating;

    public Appraisal(String employee_ID, String manager_ID, String employee_rating, String eployee_comments, String manager_rating, String manager_comments, String employee_projects, String technologies_learnt, String final_rating, String communication_rating, String projects_rating) {
        this.employee_ID = employee_ID;
        this.manager_ID = manager_ID;
        this.employee_rating = employee_rating;
        this.eployee_comments = eployee_comments;
        this.manager_rating = manager_rating;
        this.manager_comments = manager_comments;
        this.employee_projects = employee_projects;
        this.technologies_learnt = technologies_learnt;
        this.final_rating = final_rating;
        this.communication_rating = communication_rating;
        this.projects_rating = projects_rating;
    }

    public String getEmployee_ID() {
        return employee_ID;
    }

    public void setEmployee_ID(String employee_ID) {
        this.employee_ID = employee_ID;
    }

    public String getManager_ID() {
        return manager_ID;
    }

    public void setManager_ID(String manager_ID) {
        this.manager_ID = manager_ID;
    }

    public String getEmployee_rating() {
        return employee_rating;
    }

    public void setEmployee_rating(String employee_rating) {
        this.employee_rating = employee_rating;
    }

    public String getEployee_comments() {
        return eployee_comments;
    }

    public void setEployee_comments(String eployee_comments) {
        this.eployee_comments = eployee_comments;
    }

    public String getManager_rating() {
        return manager_rating;
    }

    public void setManager_rating(String manager_rating) {
        this.manager_rating = manager_rating;
    }

    public String getManager_comments() {
        return manager_comments;
    }

    public void setManager_comments(String manager_comments) {
        this.manager_comments = manager_comments;
    }

    public String getEmployee_projects() {
        return employee_projects;
    }

    public void setEmployee_projects(String employee_projects) {
        this.employee_projects = employee_projects;
    }

    public String getTechnologies_learnt() {
        return technologies_learnt;
    }

    public void setTechnologies_learnt(String technologies_learnt) {
        this.technologies_learnt = technologies_learnt;
    }

    public String getFinal_rating() {
        return final_rating;
    }

    public void setFinal_rating(String final_rating) {
        this.final_rating = final_rating;
    }

    public String getCommunication_rating() {
        return communication_rating;
    }

    public void setCommunication_rating(String communication_rating) {
        this.communication_rating = communication_rating;
    }

    public String getProjects_rating() {
        return projects_rating;
    }

    public void setProjects_rating(String projects_rating) {
        this.projects_rating = projects_rating;
    }
}
