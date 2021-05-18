This is the coding Challenge from Mindex.

# How to run the application?
1.  cd into the 'mindex-java-code-challenge' and run './gradlew bootRun'
3. If ./gradlew is not present or absent, run:  bash install gradle
3. The go to the "./mindex-java-code-challenge/src/test/java/com/mindex/challenge/service/impl"
4. Then run the three files present : CompensationServiceImplTest.java , EmployeeServiceImplTest.java,  ReportingStructureServiceImplTest.java 

# If gradle is not present:
a. bash install gradle (for MAC)
b. 'sudo su ./gradlew bootRun'

# Requirements:
1. Install gradle Spring Dependences from spring initializer.
2. this Application requires Java 10

# API endpoints:
1. TASK1 : reporting structure endpoint : http://localhost:8080//reportingStructure/{id}
2. TASK2 : Compensation read endpoint : http://localhost:8080/compensation/read/{id}
    - where id is the employeeID stored in the MongoDB  in both the above tasks.
3. TASK2 : Compensation create endpoint : http://localhost:8080/compensation/create
    - create compensation needs to have the employee object to be sent as a POST request body.
