package com.mindex.challenge.service;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;

/**
 * FileName: EmployeeService Interface
 * This is the EmployeeService Interface,
 * This interface defined the abstract signature of ReportingStructure in the EmployeeInterface.
 * @author : Kedar Nadkarni
 */
public interface EmployeeService {
    Employee create(Employee employee);
    Employee read(String id);
    Employee update(Employee employee);
    ReportingStructure getReportingStructure(String id);
}
