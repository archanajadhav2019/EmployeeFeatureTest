package emp.test.classes;

import java.util.LinkedHashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import emp.restInterfaces.functions.EmployeeManagementFunctions;
import emp.tools.classes.LoggerTool;
import emp.tools.classes.UtilityTools;
import emp.tools.classes.LoggerTool.LOG_LEVEL;

public class EmployeeListTest extends TestVerification{

	/**
	 * Method to check basic employee list functionality
	 */
	@Test(groups = { "EmployeeList", "P1" })
	public void listEmployeeWithAllValidData(){
		LoggerTool.log(LOG_LEVEL.INFO, "Scenario to test basic employee list function with valid data");
		LinkedHashMap<String, Object> fieldsToSend=new LinkedHashMap<String, Object>();
		fieldsToSend.put(EmployeeManagementFunctions.nameJsonPath,UtilityTools.generateRandomString(5));
		fieldsToSend.put(EmployeeManagementFunctions.imageJsonPath,"http://www.image1.jpg");
		fieldsToSend.put(EmployeeManagementFunctions.ageJsonPath,UtilityTools.getRandomNumber(2));
		fieldsToSend.put(EmployeeManagementFunctions.salaryJsonPath,UtilityTools.getRandomNumber(4));
		EmployeeManagementFunctions emp=new EmployeeManagementFunctions();
		String[] reqResp=emp.addEmployee(fieldsToSend, 200);
		String id=emp.getJsonPathValue(reqResp[0],EmployeeManagementFunctions.idJsonPath);
		String resp=emp.getEmployees(200);
		Assert.assertTrue(resp.contains(id),"Newly created employee id is absent in list response");
		emp.deleteEmployee(id, 200);
	}
	
}
