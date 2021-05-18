package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.CompensationService;
import com.mindex.challenge.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Filename: CompensationServiceImpl.java
 * This is the concrete class implementation of the CompensationService that implements the CompensationService interface.
 * This class gives the concrete implementation for the create and the
 *
 * @author: Kedar Nadkarni
 */
@Service
public class CompensationServiceImpl implements CompensationService {
    private static final Logger LOG = LoggerFactory.getLogger(CompensationServiceImpl.class);

    /**
     * Internally autowired the compensation Repository interface
     * this is useful in performing dependency injection of the Repository interface:
     */
    @Autowired
    private CompensationRepository compensationRepository;

    /**
     * Internally autowired employeeService object
     * This helps in performing dependency injection of the employee Repo interacting with the DB.
     */
    @Autowired
    private EmployeeService employeeService;

    /**
     * This function serves the create request for the particular compensation type.
     * creates a compensation type in the database repository in the service layer.
     * The compensation is only added for the employees present in the mongo repository.
     * Once the compensation is created this method handles duplicate compensation requests and blocks them
     * @param compensation : takes in Compensation type from the HTTP:Request method as the input.
     * @return : it returns the compensation object  that is newly created.
     */
    @Override
    public Compensation addNewCompensationEmployee(Compensation compensation) {
        LOG.debug("Generating a new compensation  type [{}]", compensation);

        // Retrieves the employee and checks if the employee is present or not
        String id=compensation.getEmployee().getEmployeeId();
        // The above ID if gets a null employee then there would be an exception that would be thrown
        // this can be read from the read api from the Employee Interface
        Employee currentEmployee=employeeService.read(id);
        compensation.setEmployee(currentEmployee);

        /**
         * we use a compensationRepo to insert the object of the compensation.
         */
        compensationRepository.insert(compensation);
        return compensation;
    }

    /**
     * This function serves the get request endpoint and retrieves the compensation of the given Employee ID:
     * @param id: takes in the Employee ID for whom the compensation needs to be retrieved
     * @return : returns the compensation of the Employee ID:
     */
    @Override
    public Compensation getCompensationEmployeeID(String id) {
        // This function fetches the current employee compensation if present
        Compensation compensation = compensationRepository.findByEmployee_EmployeeId(id);
        if (compensation == null) {
            //if null it throws a Run time Exception.
            throw new RuntimeException("No compensation on record for employeeId: " + id);
        }
        return compensation;
    }
}
