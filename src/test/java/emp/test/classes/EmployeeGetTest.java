package emp.test.classes;

import java.util.LinkedHashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import emp.restInterfaces.functions.EmployeeManagementFunctions;
import emp.tools.classes.LoggerTool;
import emp.tools.classes.UtilityTools;
import emp.tools.classes.LoggerTool.LOG_LEVEL;

public class EmployeeGetTest extends TestVerification{

	/**
	 * Method to check basic employee get functionality
	 */
	@Test(groups = { "EmployeeGet", "P1" })
	public void getEmployeeWithAllValidData(){
		LoggerTool.log(LOG_LEVEL.INFO, "Scenario to test basic employee get function with valid data");
		LinkedHashMap<String, Object> fieldsToSend=new LinkedHashMap<String, Object>();
		fieldsToSend.put(EmployeeManagementFunctions.nameJsonPath,UtilityTools.generateRandomString(5));
		fieldsToSend.put(EmployeeManagementFunctions.imageJsonPath,"http://www.image1.jpg");
		fieldsToSend.put(EmployeeManagementFunctions.ageJsonPath,UtilityTools.getRandomNumber(2));
		fieldsToSend.put(EmployeeManagementFunctions.salaryJsonPath,UtilityTools.getRandomNumber(4));
		EmployeeManagementFunctions emp=new EmployeeManagementFunctions();
		String[] reqResp=emp.addEmployee(fieldsToSend, 200);
		String id=emp.getJsonPathValue(reqResp[0],EmployeeManagementFunctions.idJsonPath);
		String getResp=emp.getEmployee(id, 200);
		verifyEmpGetResponse(reqResp[1], getResp);
		emp.deleteEmployee(id,200);
	}
	
	/**
	 * Method to check employee get with not existing id
	 */
	@Test(groups = { "EmployeeGet", "P2" })
	public void getEmployeeWithNotExistingId(){
		LoggerTool.log(LOG_LEVEL.INFO, "Scenario to test employee get with not existing id");
		EmployeeManagementFunctions emp=new EmployeeManagementFunctions();
		emp.getEmployee("765765765747446464654546546465465", 200);
	}
	
	/**
	 * Method to check employee get with inavlid id
	 */
	@Test(groups = { "EmployeeGet", "P2" })
	public void getEmployeeWithInvalidId(){
		LoggerTool.log(LOG_LEVEL.INFO, "Scenario to test employee get with invalid id");
		EmployeeManagementFunctions emp=new EmployeeManagementFunctions();
		emp.getEmployee(UtilityTools.generateRandomString(4), 200);
	}
	
	/**
	 * Method to check employee get without id
	 */
	@Test(groups = { "EmployeeGet", "P2" })
	public void getEmployeeWithoutId(){
		LoggerTool.log(LOG_LEVEL.INFO, "Scenario to test employee get without id");
		EmployeeManagementFunctions emp=new EmployeeManagementFunctions();
		emp.getEmployee("", 404);
	}
	
}
