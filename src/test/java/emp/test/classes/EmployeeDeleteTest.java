package emp.test.classes;

import java.util.LinkedHashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import emp.restInterfaces.functions.EmployeeManagementFunctions;
import emp.tools.classes.LoggerTool;
import emp.tools.classes.UtilityTools;
import emp.tools.classes.LoggerTool.LOG_LEVEL;

public class EmployeeDeleteTest extends TestVerification{

	/**
	 * Method to check basic employee delete functionality
	 */
	@Test(groups = { "EmployeeDelete", "P1" })
	public void deleteEmployeeWithAllValidData(){
		LoggerTool.log(LOG_LEVEL.INFO, "Scenario to test basic employee delete function with valid data");
		LinkedHashMap<String, Object> fieldsToSend=new LinkedHashMap<String, Object>();
		fieldsToSend.put(EmployeeManagementFunctions.nameJsonPath,UtilityTools.generateRandomString(5));
		fieldsToSend.put(EmployeeManagementFunctions.imageJsonPath,"http://www.image1.jpg");
		fieldsToSend.put(EmployeeManagementFunctions.ageJsonPath,UtilityTools.getRandomNumber(2));
		fieldsToSend.put(EmployeeManagementFunctions.salaryJsonPath,UtilityTools.getRandomNumber(4));
		EmployeeManagementFunctions emp=new EmployeeManagementFunctions();
		String[] reqResp=emp.addEmployee(fieldsToSend, 200);
		String id=emp.getJsonPathValue(reqResp[0],EmployeeManagementFunctions.idJsonPath);
		emp.deleteEmployee(id, 200);
		String resp=emp.getEmployee(id, 200);
		Assert.assertFalse(resp.contains(id));
	}
	
	/**
	 * Method to check employee delete with not existing id
	 */
	@Test(groups = { "EmployeeDelete", "P2" })
	public void deleteEmployeeWithNotExistingId(){
		LoggerTool.log(LOG_LEVEL.INFO, "Scenario to test employee delete with not existing id");
		EmployeeManagementFunctions emp=new EmployeeManagementFunctions();
		emp.deleteEmployee("765765765747446464654546546465465", 200);
	}
	
	/**
	 * Method to check employee delete with inavlid id
	 */
	@Test(groups = { "EmployeeDelete", "P2" })
	public void deleteEmployeeWithInvalidId(){
		LoggerTool.log(LOG_LEVEL.INFO, "Scenario to test employee delete with invalid id");
		EmployeeManagementFunctions emp=new EmployeeManagementFunctions();
		emp.deleteEmployee(UtilityTools.generateRandomString(4), 200);
	}
	
	/**
	 * Method to check employee delete without id
	 */
	@Test(groups = { "EmployeeDelete", "P2" })
	public void deleteEmployeeWithoutId(){
		LoggerTool.log(LOG_LEVEL.INFO, "Scenario to test employee delete without id");
		EmployeeManagementFunctions emp=new EmployeeManagementFunctions();
		emp.deleteEmployee("", 404);
	}
	
}
