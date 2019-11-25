package emp.test.classes;

import java.util.LinkedHashMap;

import javax.print.attribute.TextSyntax;

import org.testng.Assert;
import org.testng.annotations.Test;

import emp.restInterfaces.functions.EmployeeManagementFunctions;
import emp.tools.classes.LoggerTool;
import emp.tools.classes.UtilityTools;
import emp.tools.classes.LoggerTool.LOG_LEVEL;

public class EmployeeCRUDTest extends TestVerification{
	
	/**
	 * Method to check basic employee end to end functionality
	 */
	@Test(groups = { "EmployeeCRUD", "P1" })
	public void updateGetEmployeeWithValidRequiredData(){
		LoggerTool.log(LOG_LEVEL.INFO, "Scenario to test basic employee get function with valid data");
		//Create employee
		LinkedHashMap<String, Object> fieldsToCreate=new LinkedHashMap<String, Object>();
		fieldsToCreate.put(EmployeeManagementFunctions.nameJsonPath,UtilityTools.generateRandomString(5));
		fieldsToCreate.put(EmployeeManagementFunctions.ageJsonPath,UtilityTools.getRandomNumber(2));
		fieldsToCreate.put(EmployeeManagementFunctions.salaryJsonPath,UtilityTools.getRandomNumber(4));
		EmployeeManagementFunctions emp=new EmployeeManagementFunctions();
		String[] reqResp=emp.addEmployee(fieldsToCreate, 200);
		String id=emp.getJsonPathValue(reqResp[0],EmployeeManagementFunctions.idJsonPath);
		//Verify in employee get response
		String getResp=emp.getEmployee(id, 200);
		verifyEmpGetResponse(reqResp[1], getResp);
		//Verify in Employee list response
		String empListResp=emp.getEmployees(200);
		Assert.assertTrue(empListResp.contains(id),"List response doesn't contains empId");
		//Update employee
		LinkedHashMap<String, Object> fieldsToUpdate=new LinkedHashMap<String, Object>();
		fieldsToUpdate.put(EmployeeManagementFunctions.nameJsonPath,UtilityTools.generateRandomString(5));
		fieldsToUpdate.put(EmployeeManagementFunctions.ageJsonPath,UtilityTools.getRandomNumber(2));
		fieldsToUpdate.put(EmployeeManagementFunctions.salaryJsonPath,UtilityTools.getRandomNumber(4));
		String[] updateResp=emp.updateEmployee(fieldsToUpdate, id, 200);
		getResp=emp.getEmployee(id, 200);
		verifyEmpGetResponse(updateResp[1], getResp);
		//Delete employee
		emp.deleteEmployee(id,200);
		//Get employee after deleting
		getResp=emp.getEmployee(id, 200);
		Assert.assertFalse(getResp.contains(id), "Employeee is not deleted");
	}
	
	/**
	 * Method to check employee create and delete and get functionality
	 */
	@Test(groups = { "EmployeeCRUD", "P1" })
	public void createGetEmployeeWithValidRequiredData(){
		LoggerTool.log(LOG_LEVEL.INFO, "Scenario to test employee create and delete and get functionality");
		//Create employee
		LinkedHashMap<String, Object> fieldsToCreate=new LinkedHashMap<String, Object>();
		fieldsToCreate.put(EmployeeManagementFunctions.nameJsonPath,UtilityTools.generateRandomString(5));
		fieldsToCreate.put(EmployeeManagementFunctions.ageJsonPath,UtilityTools.getRandomNumber(2));
		fieldsToCreate.put(EmployeeManagementFunctions.salaryJsonPath,UtilityTools.getRandomNumber(4));
		EmployeeManagementFunctions emp=new EmployeeManagementFunctions();
		String[] reqResp=emp.addEmployee(fieldsToCreate, 200);
		String id=emp.getJsonPathValue(reqResp[0],EmployeeManagementFunctions.idJsonPath);
		//Verify in employee get response
		String getResp=emp.getEmployee(id, 200);
		verifyEmpGetResponse(reqResp[1], getResp);
		//Verify in Employee list response
		String empListResp=emp.getEmployees(200);
		Assert.assertTrue(empListResp.contains(id),"List response doesn't contains empId");
		//Delete employee
		emp.deleteEmployee(id,200);
		//Get employee after deleting
		getResp=emp.getEmployee(id, 200);
		Assert.assertFalse(getResp.contains(id), "Employeee is not deleted");
	}
	
}
