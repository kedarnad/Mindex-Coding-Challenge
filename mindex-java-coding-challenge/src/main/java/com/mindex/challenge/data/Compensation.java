package com.mindex.challenge.data;

/**
 * Filename: Compensation.java
 *
 * This class defined the structure of the Compensation needed and
 * the various features needed with this compensation for the employee class.
 * This file type is used to create a compensation type with the employee fields.
 *
 * @author: Kedar Nadkarni
 */
public class Compensation {

    Employee employee;
    String effectiveDate;
    int salary;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

    //This function sets the date in simple String format : "MM-DD-YYYY"
    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    //This Function helps getting the salary:
    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}
