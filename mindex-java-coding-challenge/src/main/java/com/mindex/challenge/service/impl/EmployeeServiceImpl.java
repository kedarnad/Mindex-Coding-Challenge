package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger LOG = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee create(Employee employee) {
        LOG.debug("Creating employee [{}]", employee);

        employee.setEmployeeId(UUID.randomUUID().toString());
        employeeRepository.insert(employee);

        return employee;
    }

    @Override
    public Employee read(String id) {
        LOG.debug("Creating employee with id [{}]", id);

        Employee employee = employeeRepository.findByEmployeeId(id);

        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }

        return employee;
    }

    @Override
    public Employee update(Employee employee) {
        LOG.debug("Updating employee [{}]", employee);

        return employeeRepository.save(employee);
    }

    /**
     *  This is a logic reconstruction of the Employee Reporting structure
     *
     * @param id
     * @return : Returns a reporting structure after performing a employee graph search operation.
     */
    @Override
    public ReportingStructure getReportingStructure(String id) {
        if(id==null|| id.isEmpty())
            throw new NullPointerException("No Employee id.");

        Employee employee = read(id);
        // if no employee found in the DB with this id we move ahead.
        if(employee == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }

        int numOfDirectReports = getDistinct_Direct_Emp_Reports(employee);

        ReportingStructure reportingStruct = new ReportingStructure();
        reportingStruct.setEmployee(employee);
        reportingStruct.setNumberOfReports(numOfDirectReports);
        return reportingStruct; // returning the reporting struct variable:

    }

    /**
     * function: getDistinct_Direct_Emp_Reports():
     *           -- This function builds the basic logic of recursing through the entire Directory and checks for the
     *              direct and indirect reports under a particular employee.
     *           -- This function uses a basic DFS/BFS logic to loop through all the Employees under a particular
     *              Employee.
     *
     *           -- The commented code gives a DFS logic, the uncommented code shows the BFS logic that has been used.
     * @param emp: employee
     * @return : returning total direct reports
     */
    private int getDistinct_Direct_Emp_Reports(Employee emp) {
//        Employee employee = read(id);
        int totdirectReports =0; // base case is 0

        // the assumption here is that no two employees on the same level report to each other:
        // hierarchy of the employee is maintained.
        // the boss doesnt report his subordinates : so no cycles are to be found in the graph:

        List<Employee> TotalDirectReports_list = emp.getDirectReports();

        // performing a DFS operation on the number of employees present:
        if(TotalDirectReports_list==null || TotalDirectReports_list.isEmpty())
            return totdirectReports;
        else{
            totdirectReports+=emp.getDirectReports().size();
            for(Employee emp_temp: TotalDirectReports_list){
                totdirectReports+=getDistinct_Direct_Emp_Reports(read(emp_temp.getEmployeeId()));
            }
        }


        return totdirectReports;
    }

}
