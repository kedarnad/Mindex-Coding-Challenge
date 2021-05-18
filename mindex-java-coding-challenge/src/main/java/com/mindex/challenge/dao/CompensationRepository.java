package com.mindex.challenge.dao;

import com.mindex.challenge.data.Compensation;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Filename: CompensationRepository.java
 * compensation repository structure:
 * This interface helps in creating a binding of the compensation type class with
 * the mongo repository.
 */
public interface CompensationRepository extends MongoRepository<Compensation, String> {
    //Reference from :https://stackoverflow.com/questions/55579240/spring-data-find-by-property-of-a-nested-object
    //In order to find the ID of the nested object we can use a underscore and use the same
    // prefix name of the parent object method and suffix of the id of the object to be found.
    // in this case "_EmployeeID"
    public Compensation findByEmployee_EmployeeId(String id);
}
