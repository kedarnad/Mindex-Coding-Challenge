package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.CompensationService;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.text.ParseException;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CompensationServiceImplTest {

    private String employeeUrl;
    private String createCompensationUrl;
    private String readCompensationUrl;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CompensationService compensationService;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private Employee EmployeeOneTest;

    @Before
    public void setup() {
        employeeUrl = "http://localhost:" + port + "/employee";
        createCompensationUrl = "http://localhost:" + port + "/compensation/create";
        readCompensationUrl = "http://localhost:" + port + "/compensation/read/{id}";
        EmployeeOneTest = employeeRepository.findByEmployeeId("16a596ae-edd3-4847-99fe-c4518e82c86f");
    }

    @After
    public void teardown() {
        employeeUrl = null;
        createCompensationUrl = null;
        readCompensationUrl = null;
        EmployeeOneTest = null;
    }

    @Test
    public void testCreateGetCompensation() throws ParseException {
        Compensation compensationCreateTest = new Compensation();
        compensationCreateTest.setEmployee(EmployeeOneTest);
        compensationCreateTest.setSalary(80000);
        compensationCreateTest.setEffectiveDate("04-25-2021");

        // Create checks to add the Compensation
        Compensation createdCompensation = restTemplate.postForEntity(createCompensationUrl, compensationCreateTest, Compensation.class).getBody();
        assertEquals(compensationCreateTest.getSalary(), createdCompensation.getSalary());
        assertEquals(compensationCreateTest.getEffectiveDate(), createdCompensation.getEffectiveDate());
        assertEquivalenceOfEmployee(compensationCreateTest.getEmployee(), createdCompensation.getEmployee());


        // Read checks to retrieve the Compensation
        Compensation compensationReadTest = restTemplate.getForEntity(readCompensationUrl, Compensation.class, createdCompensation.getEmployee().getEmployeeId()).getBody();
        assertEquals(compensationReadTest.getSalary(), createdCompensation.getSalary());
        assertEquals(compensationReadTest.getEffectiveDate(), createdCompensation.getEffectiveDate());
        assertEquivalenceOfEmployee(compensationReadTest.getEmployee(), createdCompensation.getEmployee());


    }

    public static void assertEquivalenceOfEmployee(Employee expected, Employee actual){
        // check for Employee Parameter Equivalence
        assertEquals(expected.getFirstName(), actual.getFirstName());
        assertEquals(expected.getLastName(), actual.getLastName());
        assertEquals(expected.getDepartment(), actual.getDepartment());
        assertEquals(expected.getPosition(), actual.getPosition());
    }



}