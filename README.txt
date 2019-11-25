Tools Used:
 1. TestNG testing framework
 2. Log4j for logging
 3. Maven build tool
 4. RESTAssured library for calling API's
 5. minidev json-smart library for creating and updating JSON objects
 
Steps for running tests:
Command:
mvn clean install
Trough TestNG:
Right click on testng XML and run as TestNGSuite
   
Maven Folder structure:
1. src/main/java:
    i. Classes to create and update JSON from JSON standard templates
	ii. Classes to send requests to API using REST Assured library
	iii.Class to provide all the possible functional methods for employeee management feature
	iv. Class to handle exception
	v. Class to implement testNG listener
	vi. Class to log functionality and utility 
2. src/main/resources
    i. Standard json templates fro create and update functions
	ii.log4j properties
3. src/test/java  
    i. TestNG test calsses for each interface functionality and for CRUD functionality
	ii. Class providing methods to compare and verify responses
4. src/test/resources  
    i. Testdata for test classes (Not used)
	
Reporting:
 1. TestNG reports can be used
 2. Console log for debugging and sharing can be used
	