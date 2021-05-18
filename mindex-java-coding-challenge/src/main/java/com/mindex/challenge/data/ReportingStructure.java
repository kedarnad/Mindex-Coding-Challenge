package com.mindex.challenge.data;

import java.util.Objects;

/**
 * Filename: ReportingStructure.java
 * This class defines the reporting structure available for each employee:
 * This class definition is useful for retrieving the number of reports for each employee
 * and the number of employees under his team and so on.
 *
 * @author: Kedar Nadkarni
 */
public class ReportingStructure {

    /**
     * Number of reports should be the total reports under the employee which contains the number of direct reports and
     * distinct reports of all the employees under him.
     */
    private Employee employee;
    private int numberOfReports;

    public ReportingStructure(){
    }

    public Employee getEmployee() {
        return employee;
    }

    /**
     * sets the current employee for the consideration of this type of report.
     * @param employee
     */
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    // gets the total number of report that has been set by adding
    // all the employees under him and the total number of direct reports:
    public int getNumberOfReports() {
        return this.numberOfReports;
    }

    // setting the number of direct reports;
    // plus the total number of reports for all the employees under him
    public void setNumberOfReports(int numberOfReports) {
        this.numberOfReports = numberOfReports;
    }

    /**
     * Overriding an equals method for the Reporting Structure
     * @param o: takes an Object as the input and this method checks if it is of the ReportingStructure type
     * @return : returns a boolean type output.
     */

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ReportingStructure)) {
            return false;
        }
        ReportingStructure reportingStructure = (ReportingStructure) o;
        return Objects.equals(employee, reportingStructure.employee)
                && numberOfReports == reportingStructure.numberOfReports;
    }

    /**
     * Creating a custom Defined HashCode for the type Reporting Structure:
     * @return : returns the hashcode of the output in int format
     */
    @Override
    public int hashCode() {
        return Objects.hash(employee, numberOfReports);
    }

    /**
     * Create a toString method that signifies the Object Definition.
     * @return : returns the string format of the output
     */
    @Override
    public String toString() {
        return "{" +
                " employee='" + getEmployee() + "'" +
                ", numberOfReports='" + getNumberOfReports() + "'" +
                "}";
    }

}
