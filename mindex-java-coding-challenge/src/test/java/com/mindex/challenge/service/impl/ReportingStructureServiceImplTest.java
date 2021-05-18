package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReportingStructureServiceImplTest {

    private String employeeUrl;
    private String readReportingStructureUrl;

    @Autowired
    private EmployeeRepository employeeRepository;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private Employee EmployeeOneTest;

    @Before
    public void setup() {
        employeeUrl = "http://localhost:" + port + "/employee";
        readReportingStructureUrl = "http://localhost:" + port + "/reportingStructure/{id}";
        EmployeeOneTest = employeeRepository.findByEmployeeId("16a596ae-edd3-4847-99fe-c4518e82c86f");
    }

    @After
    public void teardown() {
        employeeUrl = null;
        readReportingStructureUrl = null;
        EmployeeOneTest = null;
    }

    @Test
    public void testCreateReadUpdate() {
        ReportingStructure testReportingStructure = new ReportingStructure();
        testReportingStructure.setEmployee(EmployeeOneTest);


        // We test that 4 People report to John
        ReportingStructure readReportingStructure = restTemplate.getForEntity(readReportingStructureUrl,
                ReportingStructure.class, testReportingStructure.getEmployee().getEmployeeId()).getBody();
        assertNotNull(readReportingStructure);
        assertEquals(4, readReportingStructure.getNumberOfReports());

        //Testing with a Different Employee object whom John Reports
        // Creating an Additional Level of Hierarchy for an Employee whom John Reports
        // This should return the total number of reports as 5 for this new Employee
        Employee superEmp = new Employee();
        superEmp.setFirstName("Kedar");
        superEmp.setLastName("N");
        superEmp.setDepartment("Dev Manager A");
        superEmp.setPosition("Developer");
        List<Employee> empList = new ArrayList<>();
        empList.add(employeeRepository.findByEmployeeId("16a596ae-edd3-4847-99fe-c4518e82c86f"));
        superEmp.setDirectReports(empList);

        Employee createdEmployee = restTemplate.postForEntity(employeeUrl, superEmp, Employee.class).getBody();
        // Finding the Reporting Structure for this new Employee whom John Reports:
        // This should return 5.
        testReportingStructure = new ReportingStructure();
        testReportingStructure.setEmployee(createdEmployee);

        readReportingStructure = restTemplate.getForEntity(readReportingStructureUrl,
                ReportingStructure.class, testReportingStructure.getEmployee().getEmployeeId()).getBody();
        assertNotNull(readReportingStructure);
        assertEquals(5, readReportingStructure.getNumberOfReports());

    }
}