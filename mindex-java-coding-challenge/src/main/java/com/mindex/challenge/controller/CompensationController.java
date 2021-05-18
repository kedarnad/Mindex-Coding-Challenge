package com.mindex.challenge.controller;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.CompensationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * Filename: CompensationController.java
 * A controller created as a middle point that handles the REST endpoint of the Compensation Type Service.
 * This is a spring boot framework to build a REST controller for the compensation module. This middleware controller
 * handles the POST and GET methods.
 *
 * This implementation creates a Compensation for employees who are already present.
 * It does not create an Additional Compensation when a particular compensation for an Employee has already been created.
 *
 * @author: Kedar Nadkarni
 */
@RestController
public class CompensationController {
    private
    final Logger LOG = LoggerFactory.getLogger(CompensationController.class);

    @Autowired
    private CompensationService compensationService;


    /**
     * A REST endpoint created for creating a new compensation.
     * @param compensation_employee: an object created for the compensation of
     * @return : return a Compensation object for that particular employee
     */
    @PostMapping(path="/compensation/create")
    public Compensation generateNewCompensation(@RequestBody Compensation compensation_employee){
        LOG.debug("Received compensation create request for [{}]", compensation_employee);
        compensationService.addNewCompensationEmployee(compensation_employee);
        return compensation_employee;
    }

    /**
     * An endpoint read to get the compensation of the respective Employee ID:
     * @param id : takes the employee id as the GetMapping input from the get request.
     * @return : Returns the Compensation of that respective Employee.
     */
    @GetMapping(path="/compensation/read/{id}")
    public Compensation viewCompensation(@PathVariable String id){
        LOG.debug("Received compensation get request for id [{}]", id);
        return compensationService.getCompensationEmployeeID(id);
    }

}
