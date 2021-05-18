package com.mindex.challenge.service;
//import com.mindex.challenge.data.Compensation;


import com.mindex.challenge.data.Compensation;

/**
 * CompensationService.java : Interface
 * This interface is created to have some strict design rules for the role
 * the Compensation Service will play.
 * The Compensation Service will deal with Objects Compensations as defined in the data package.
 *
 * Roles:
 *  a. It will help to create different set of compensations of type Compensation.
 *  b. It will return Compensations with respect to the employee ID.
 *
 * @author: Kedar Nadkarni
 */
public interface CompensationService {
    /**
     * In this  class we define the signature of two service endpoints
     *  to retrieve types of compensations of a particular ID:
     *  Create a particular type of compensation.
     *
     */

    // retriving the compensations given a ID of the employee
    Compensation getCompensationEmployeeID(String id);
    // service endpoint to create a particular type of Compensation
    Compensation addNewCompensationEmployee(Compensation compensation);
}
