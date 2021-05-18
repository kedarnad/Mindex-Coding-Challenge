package com.mindex.challenge.controller;

import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Filename: ReportingStructureController.java
 *
 * This is an API layer that uses spring framework for handing the Reporting Structure type of any employee that is
 * already present in the MongoRepository.
 * This class helps in creating a reporting structure and sending back such a reporting structure for any employee.
 * This is a separate REST endpoint for getting the above functionality.
 *
 * The Reporting Structure field contains the employee field and the number of reports of that employee.
 *
 * @author: Kedar Nadkarni
 */
@RestController
public class ReportingStructureController {

    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureController.class);

    // Autowiring the EmployeeService interface: this automatically takes the created object of the
    // concrete class EmployeeServiceImp;
    @Autowired
    private EmployeeService employeeService;


    /**
     * This method takes an employee  id and returns its particular reporting structure.
     * @param id : employeeId
     * @return: Reporting Structure: (containing employee fields and number of reports)
     */
    @GetMapping("/reportingStructure/{id}") //because we would want to query on the id:
    public ReportingStructure read(@PathVariable String id){
        /* Endpoint created for the reading the Getting the Reporting Structure per employee:
         * This operation reads through the  getReportingStructure method of the EmplouyeeServiceImpl class of the
         * EmployeeService interface
         */
        LOG.debug("Fetching a GET request for employee with id: [{}]", id);

        return employeeService.getReportingStructure(id);
    }
}
