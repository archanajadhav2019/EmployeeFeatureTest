package emp.test.classes;

import org.testng.Assert;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

import emp.restInterfaces.functions.EmployeeManagementFunctions;
//Class to compare and verify response
public class TestVerification {

	//Method to verify employee create and update response
	void verifyEmpAddResponse(String request,String response){
		final DocumentContext responseContext = JsonPath.parse(response);
		final DocumentContext requestContext = JsonPath.parse(request);
		Assert.assertEquals(responseContext.read(EmployeeManagementFunctions.nameJsonPath).toString(), requestContext.read(EmployeeManagementFunctions.nameJsonPath).toString(), "Name is incorrect in the response");
		Assert.assertEquals(responseContext.read(EmployeeManagementFunctions.ageJsonPath).toString(), requestContext.read(EmployeeManagementFunctions.ageJsonPath).toString(), "Age is incorrect in the response");
		Assert.assertEquals(responseContext.read(EmployeeManagementFunctions.salaryJsonPath).toString(), requestContext.read(EmployeeManagementFunctions.salaryJsonPath).toString(), "Salary is incorrect in the response");
		//Assert.assertEquals(responseContext.read(EmployeeManagementFunctions.imageJsonPath).toString(), requestContext.read(EmployeeManagementFunctions.imageJsonPath).toString(), "Image is incorrect in the response");
		Assert.assertTrue(response.contains("id"), "Employee id is not generated in the response");
	}
	
	//Method to verify employee get response
	void verifyEmpGetResponse(String request,String response){
		final DocumentContext responseContext = JsonPath.parse(response);
		final DocumentContext requestContext = JsonPath.parse(request);
		Assert.assertEquals(responseContext.read(EmployeeManagementFunctions.employeeNameJsonPath).toString(), requestContext.read(EmployeeManagementFunctions.nameJsonPath).toString(), "Name is incorrect in the response");
		Assert.assertEquals(responseContext.read(EmployeeManagementFunctions.employeeAgeJsonPath).toString(), requestContext.read(EmployeeManagementFunctions.ageJsonPath).toString(), "Age is incorrect in the response");
		Assert.assertEquals(responseContext.read(EmployeeManagementFunctions.employeeSalaryJsonPath).toString(), requestContext.read(EmployeeManagementFunctions.salaryJsonPath).toString(), "Salary is incorrect in the response");
		//Assert.assertEquals(responseContext.read(EmployeeManagementFunctions.profileImageJsonPath).toString(), requestContext.read(EmployeeManagementFunctions.imageJsonPath).toString(), "Image is incorrect in the response");
		Assert.assertTrue(response.contains("id"), "Employee id is not generated in the response");
	}

}
