package emp.test.classes;

import java.util.LinkedHashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import emp.restInterfaces.functions.EmployeeManagementFunctions;
import emp.tools.classes.LoggerTool;
import emp.tools.classes.LoggerTool.LOG_LEVEL;
import emp.tools.classes.UtilityTools;

//Test class to test employee create functionality
public class EmployeeCreateTest extends TestVerification{

	/**
	 * Method to check basic employee create functionality
	 */
	@Test(groups = { "EmployeeCreate", "P1" })
	public void createEmployeeWithAllValidData(){
		LoggerTool.log(LOG_LEVEL.INFO, "Scenario to test basic employee create function with valid data");
		LinkedHashMap<String, Object> fieldsToSend=new LinkedHashMap<String, Object>();
		fieldsToSend.put(EmployeeManagementFunctions.nameJsonPath,UtilityTools.generateRandomString(5));
		fieldsToSend.put(EmployeeManagementFunctions.imageJsonPath,"http://www.image1.jpg");
		fieldsToSend.put(EmployeeManagementFunctions.ageJsonPath,UtilityTools.getRandomNumber(2));
		fieldsToSend.put(EmployeeManagementFunctions.salaryJsonPath,UtilityTools.getRandomNumber(4));
		EmployeeManagementFunctions emp=new EmployeeManagementFunctions();
		String[] reqResp=emp.addEmployee(fieldsToSend, 200);
		verifyEmpAddResponse(reqResp[1], reqResp[0]);
		String id=emp.getJsonPathValue(reqResp[0],EmployeeManagementFunctions.idJsonPath);
		emp.deleteEmployee(id, 200);
	}

	/**
	 * Method to check employee create interface without salary
	 */
	@Test(groups = { "EmployeeCreate", "P2" })
	public void createEmployeeWithoutSalary(){
		LoggerTool.log(LOG_LEVEL.INFO, "Scenario to check employee create interface without salary");
		LinkedHashMap<String, Object> fieldsToSend=new LinkedHashMap<String, Object>();
		fieldsToSend.put(EmployeeManagementFunctions.nameJsonPath,UtilityTools.generateRandomString(5));
		fieldsToSend.put(EmployeeManagementFunctions.imageJsonPath,"http://www.image1.jpg");
		fieldsToSend.put(EmployeeManagementFunctions.ageJsonPath,UtilityTools.getRandomNumber(2));
		fieldsToSend.put(EmployeeManagementFunctions.salaryJsonPath,"missing");
		EmployeeManagementFunctions emp=new EmployeeManagementFunctions();
		String[] reqResp=emp.addEmployee(fieldsToSend, 200);
		Assert.assertTrue(reqResp[0].contains("Integrity constraint violation: 1048 Column \'employee_salary\' cannot be null"));
	}

	/**
	 * Method to check employee create interface without age
	 */
	@Test(groups = { "EmployeeCreate", "P2" })
	public void createEmployeeWithoutAge(){
		LoggerTool.log(LOG_LEVEL.INFO, "Scenario to test employee create interface without age");
		LinkedHashMap<String, Object> fieldsToSend=new LinkedHashMap<String, Object>();
		fieldsToSend.put(EmployeeManagementFunctions.nameJsonPath,UtilityTools.generateRandomString(5));
		fieldsToSend.put(EmployeeManagementFunctions.imageJsonPath,"http://www.image1.jpg");
		fieldsToSend.put(EmployeeManagementFunctions.ageJsonPath,"missing");
		fieldsToSend.put(EmployeeManagementFunctions.salaryJsonPath,UtilityTools.getRandomNumber(4));
		EmployeeManagementFunctions emp=new EmployeeManagementFunctions();
		String[] reqResp=emp.addEmployee(fieldsToSend, 200);
		Assert.assertTrue(reqResp[0].contains("Integrity constraint violation: 1048 Column \'employee_age\' cannot be null"));
	}

	/**
	 * Method to check employee create interface without image
	 */
	@Test(groups = { "EmployeeCreate", "P2" })
	public void createEmployeeWithoutProfileImage(){
		LoggerTool.log(LOG_LEVEL.INFO, "Scenario to test employee create interface without image");
		LinkedHashMap<String, Object> fieldsToSend=new LinkedHashMap<String, Object>();
		fieldsToSend.put(EmployeeManagementFunctions.nameJsonPath,UtilityTools.generateRandomString(5));
		fieldsToSend.put(EmployeeManagementFunctions.imageJsonPath,"missing");
		fieldsToSend.put(EmployeeManagementFunctions.ageJsonPath,UtilityTools.getRandomNumber(2));
		fieldsToSend.put(EmployeeManagementFunctions.salaryJsonPath,UtilityTools.getRandomNumber(4));
		EmployeeManagementFunctions emp=new EmployeeManagementFunctions();
		String[] reqResp=emp.addEmployee(fieldsToSend, 200);
		String id=emp.getJsonPathValue(reqResp[0],EmployeeManagementFunctions.idJsonPath);
		emp.deleteEmployee(id, 200);
	}

	/**
	 * Method to check employee create interface without name
	 */
	@Test(groups = { "EmployeeCreate", "P2" })
	public void createEmployeeWithoutName(){
		LoggerTool.log(LOG_LEVEL.INFO, "Scenario to test employee create interface without name");
		LinkedHashMap<String, Object> fieldsToSend=new LinkedHashMap<String, Object>();
		fieldsToSend.put(EmployeeManagementFunctions.nameJsonPath,"missing");
		fieldsToSend.put(EmployeeManagementFunctions.imageJsonPath,"http://www.image1.jpg");
		fieldsToSend.put(EmployeeManagementFunctions.ageJsonPath,UtilityTools.getRandomNumber(2));
		fieldsToSend.put(EmployeeManagementFunctions.salaryJsonPath,UtilityTools.getRandomNumber(4));
		EmployeeManagementFunctions emp=new EmployeeManagementFunctions();
		String[] reqResp=emp.addEmployee(fieldsToSend, 200);
		Assert.assertTrue(reqResp[0].contains("Integrity constraint violation: 1048 Column \'employee_name\' cannot be null"));
	}

	/**
	 * Method to check employee create interface with null value for salary
	 */
	@Test(groups = { "EmployeeCreate", "P2" })
	public void createEmployeeWithNullSalary(){
		LoggerTool.log(LOG_LEVEL.INFO, "Scenario to test employee create interface with null value for salary");
		LinkedHashMap<String, Object> fieldsToSend=new LinkedHashMap<String, Object>();
		fieldsToSend.put(EmployeeManagementFunctions.nameJsonPath,UtilityTools.generateRandomString(5));
		fieldsToSend.put(EmployeeManagementFunctions.imageJsonPath,"http://www.image1.jpg");
		fieldsToSend.put(EmployeeManagementFunctions.ageJsonPath,UtilityTools.getRandomNumber(2));
		fieldsToSend.put(EmployeeManagementFunctions.salaryJsonPath,null);
		EmployeeManagementFunctions emp=new EmployeeManagementFunctions();
		String[] reqResp=emp.addEmployee(fieldsToSend, 200);
		Assert.assertTrue(reqResp[0].contains("Integrity constraint violation: 1048 Column \'employee_salary\' cannot be null"));
	}

	/**
	 * Method to check employee create interface with null value for age
	 */
	@Test(groups = { "EmployeeCreate", "P2" })
	public void createEmployeeWithNullAge(){
		LoggerTool.log(LOG_LEVEL.INFO, "Scenario to test employee create interface with null value for age");
		LinkedHashMap<String, Object> fieldsToSend=new LinkedHashMap<String, Object>();
		fieldsToSend.put(EmployeeManagementFunctions.nameJsonPath,UtilityTools.generateRandomString(5));
		fieldsToSend.put(EmployeeManagementFunctions.imageJsonPath,"http://www.image1.jpg");
		fieldsToSend.put(EmployeeManagementFunctions.ageJsonPath,null);
		fieldsToSend.put(EmployeeManagementFunctions.salaryJsonPath,UtilityTools.getRandomNumber(4));
		EmployeeManagementFunctions emp=new EmployeeManagementFunctions();
		String[] reqResp=emp.addEmployee(fieldsToSend, 200);
		Assert.assertTrue(reqResp[0].contains("Integrity constraint violation: 1048 Column \'employee_age\' cannot be null"));
	}

	/**
	 * Method to check employee create interface with null value for image
	 */
	@Test(groups = {"EmployeeCreate", "P2" })
	public void createEmployeeWithNullProfileImage(){
		LoggerTool.log(LOG_LEVEL.INFO, "Scenario to test employee create interface with null value for image");
		LinkedHashMap<String, Object> fieldsToSend=new LinkedHashMap<String, Object>();
		fieldsToSend.put(EmployeeManagementFunctions.nameJsonPath,UtilityTools.generateRandomString(5));
		fieldsToSend.put(EmployeeManagementFunctions.imageJsonPath,null);
		fieldsToSend.put(EmployeeManagementFunctions.ageJsonPath,UtilityTools.getRandomNumber(2));
		fieldsToSend.put(EmployeeManagementFunctions.salaryJsonPath,UtilityTools.getRandomNumber(4));
		EmployeeManagementFunctions emp=new EmployeeManagementFunctions();
		String[] reqResp=emp.addEmployee(fieldsToSend, 200);
		String id=emp.getJsonPathValue(reqResp[0],EmployeeManagementFunctions.idJsonPath);
		emp.deleteEmployee(id, 200);
	}

	/**
	 * Method to check employee create interface with null name
	 */
	@Test(groups = { "EmployeeCreate", "P2" })
	public void createEmployeeWithNullName(){
		LoggerTool.log(LOG_LEVEL.INFO, "Scenario to test employee create interface with null name");
		LinkedHashMap<String, Object> fieldsToSend=new LinkedHashMap<String, Object>();
		fieldsToSend.put(EmployeeManagementFunctions.nameJsonPath,null);
		fieldsToSend.put(EmployeeManagementFunctions.imageJsonPath,"http://www.image1.jpg");
		fieldsToSend.put(EmployeeManagementFunctions.ageJsonPath,UtilityTools.getRandomNumber(2));
		fieldsToSend.put(EmployeeManagementFunctions.salaryJsonPath,UtilityTools.getRandomNumber(4));
		EmployeeManagementFunctions emp=new EmployeeManagementFunctions();
		String[] reqResp=emp.addEmployee(fieldsToSend, 200);
		Assert.assertTrue(reqResp[0].contains("Integrity constraint violation: 1048 Column 'employee_name' cannot be null"));

	}
	
	/**
	 * Method to check employee create interface with empty name
	 */
	@Test(groups = { "EmployeeCreate", "P2" })
	public void createEmployeeWithEmptyName(){
		LoggerTool.log(LOG_LEVEL.INFO, "Scenario to test employee create interface with empty name");
		LinkedHashMap<String, Object> fieldsToSend=new LinkedHashMap<String, Object>();
		fieldsToSend.put(EmployeeManagementFunctions.nameJsonPath,"");
		fieldsToSend.put(EmployeeManagementFunctions.imageJsonPath,"http://www.image1.jpg");
		fieldsToSend.put(EmployeeManagementFunctions.ageJsonPath,UtilityTools.getRandomNumber(2));
		fieldsToSend.put(EmployeeManagementFunctions.salaryJsonPath,UtilityTools.getRandomNumber(4));
		EmployeeManagementFunctions emp=new EmployeeManagementFunctions();
		String[] reqResp=emp.addEmployee(fieldsToSend, 200);
		Assert.assertTrue(reqResp[0].contains("employee_name_unique"));
	}

	/**
	 * Method to check employee create interface with special characters in name
	 */
	@Test(groups = { "EmployeeCreate", "P2" })
	public void createEmployeeWithSpecialCharsInName(){
		LoggerTool.log(LOG_LEVEL.INFO, "Scenario to test employee create interface with special characters in name");
		LinkedHashMap<String, Object> fieldsToSend=new LinkedHashMap<String, Object>();
		fieldsToSend.put(EmployeeManagementFunctions.nameJsonPath,"$%^&");
		fieldsToSend.put(EmployeeManagementFunctions.imageJsonPath,"http://www.image1.jpg");
		fieldsToSend.put(EmployeeManagementFunctions.ageJsonPath,UtilityTools.getRandomNumber(2));
		fieldsToSend.put(EmployeeManagementFunctions.salaryJsonPath,UtilityTools.getRandomNumber(4));
		EmployeeManagementFunctions emp=new EmployeeManagementFunctions();
		emp.addEmployee(fieldsToSend, 200);
	}
	
	/**
	 * Method to check employee create interface with alphanumeric characters in name
	 */
	@Test(groups = { "EmployeeCreate", "P2" })
	public void createEmployeeWithAlphaNumericInName(){
		LoggerTool.log(LOG_LEVEL.INFO, "Scenario to test employee create interface with alphanumeric characters in name");
		LinkedHashMap<String, Object> fieldsToSend=new LinkedHashMap<String, Object>();
		fieldsToSend.put(EmployeeManagementFunctions.nameJsonPath,UtilityTools.generateRandomString(4)+UtilityTools.getRandomNumber(4));
		fieldsToSend.put(EmployeeManagementFunctions.imageJsonPath,"http://www.image1.jpg");
		fieldsToSend.put(EmployeeManagementFunctions.ageJsonPath,UtilityTools.getRandomNumber(2));
		fieldsToSend.put(EmployeeManagementFunctions.salaryJsonPath,UtilityTools.getRandomNumber(4));
		EmployeeManagementFunctions emp=new EmployeeManagementFunctions();
		String[] reqResp=emp.addEmployee(fieldsToSend, 200);
		verifyEmpAddResponse(reqResp[1], reqResp[0]);
		String id=emp.getJsonPathValue(reqResp[0],EmployeeManagementFunctions.idJsonPath);
		emp.deleteEmployee(id, 200);
	}
	
	/**
	 * Method to check employee create interface with decimal value for salary
	 */
	@Test(groups = { "EmployeeCreate", "P2" })
	public void createEmployeeWithDecimalSalary(){
		LoggerTool.log(LOG_LEVEL.INFO, "Scenario to test employee create interface with decimal value for salary");
		LinkedHashMap<String, Object> fieldsToSend=new LinkedHashMap<String, Object>();
		fieldsToSend.put(EmployeeManagementFunctions.nameJsonPath,UtilityTools.generateRandomString(5));
		fieldsToSend.put(EmployeeManagementFunctions.imageJsonPath,"http://www.image1.jpg");
		fieldsToSend.put(EmployeeManagementFunctions.ageJsonPath,UtilityTools.getRandomNumber(2));
		fieldsToSend.put(EmployeeManagementFunctions.salaryJsonPath,7.7647);
		EmployeeManagementFunctions emp=new EmployeeManagementFunctions();
		String[] reqResp=emp.addEmployee(fieldsToSend, 200);
		verifyEmpAddResponse(reqResp[1], reqResp[0]);	
		String id=emp.getJsonPathValue(reqResp[0],EmployeeManagementFunctions.idJsonPath);
		emp.deleteEmployee(id, 200);
		
	}
	
	/**
	 * Method to check employee create interface with alphanumeric value for salary
	 */
	@Test(groups = { "EmployeeCreate", "P2" })
	public void createEmployeeWithAlphaNumericSalary(){
		LoggerTool.log(LOG_LEVEL.INFO, "Scenario to test employee create interface with alphanumeric value for salary");
		LinkedHashMap<String, Object> fieldsToSend=new LinkedHashMap<String, Object>();
		fieldsToSend.put(EmployeeManagementFunctions.nameJsonPath,UtilityTools.generateRandomString(5));
		fieldsToSend.put(EmployeeManagementFunctions.imageJsonPath,"http://www.image1.jpg");
		fieldsToSend.put(EmployeeManagementFunctions.ageJsonPath,UtilityTools.getRandomNumber(2));
		fieldsToSend.put(EmployeeManagementFunctions.salaryJsonPath,UtilityTools.generateRandomString(5)+UtilityTools.getRandomNumber(5));
		EmployeeManagementFunctions emp=new EmployeeManagementFunctions();
		String[] reqResp=emp.addEmployee(fieldsToSend, 200);
		verifyEmpAddResponse(reqResp[1], reqResp[0]);
		String id=emp.getJsonPathValue(reqResp[0],EmployeeManagementFunctions.idJsonPath);
		emp.deleteEmployee(id, 200);
	}
	
	/**
	 * Method to check employee create interface with decimal value for age
	 */
	@Test(groups = { "EmployeeCreate", "P2" })
	public void createEmployeeWithDecimalAge(){
		LoggerTool.log(LOG_LEVEL.INFO, "Scenario to test employee create interface with decimal value for age");
		LinkedHashMap<String, Object> fieldsToSend=new LinkedHashMap<String, Object>();
		fieldsToSend.put(EmployeeManagementFunctions.nameJsonPath,UtilityTools.generateRandomString(5));
		fieldsToSend.put(EmployeeManagementFunctions.imageJsonPath,"http://www.image1.jpg");
		fieldsToSend.put(EmployeeManagementFunctions.ageJsonPath,5.7657);
		fieldsToSend.put(EmployeeManagementFunctions.salaryJsonPath,UtilityTools.getRandomNumber(5));
		EmployeeManagementFunctions emp=new EmployeeManagementFunctions();
		String[] reqResp=emp.addEmployee(fieldsToSend, 200);
		verifyEmpAddResponse(reqResp[1], reqResp[0]);
		String id=emp.getJsonPathValue(reqResp[0],EmployeeManagementFunctions.idJsonPath);
		emp.deleteEmployee(id, 200);
	}
	
	/**
	 * Method to check employee create interface with alphanumeric value for age
	 */
	@Test(groups = { "EmployeeCreate", "P2" })
	public void createEmployeeWithAlphaNumericAge(){
		LoggerTool.log(LOG_LEVEL.INFO, "Scenario to test employee create interface with decimal value for age");
		LinkedHashMap<String, Object> fieldsToSend=new LinkedHashMap<String, Object>();
		fieldsToSend.put(EmployeeManagementFunctions.nameJsonPath,UtilityTools.generateRandomString(5));
		fieldsToSend.put(EmployeeManagementFunctions.imageJsonPath,"http://www.image1.jpg");
		fieldsToSend.put(EmployeeManagementFunctions.ageJsonPath,UtilityTools.generateRandomString(5)+UtilityTools.getRandomNumber(5));
		fieldsToSend.put(EmployeeManagementFunctions.salaryJsonPath,UtilityTools.getRandomNumber(6));
		EmployeeManagementFunctions emp=new EmployeeManagementFunctions();
		String[] reqResp=emp.addEmployee(fieldsToSend, 200);
		verifyEmpAddResponse(reqResp[1], reqResp[0]);
		String id=emp.getJsonPathValue(reqResp[0],EmployeeManagementFunctions.idJsonPath);
		emp.deleteEmployee(id, 200);
	}
	
	/**
	 * Method to check employee create interface with special chars for age
	 */
	@Test(groups = { "EmployeeCreate", "P2" })
	public void createEmployeeWithSpecialCharsAge(){
		LoggerTool.log(LOG_LEVEL.INFO, "Scenario to test employee create interface with special chars for age");
		LinkedHashMap<String, Object> fieldsToSend=new LinkedHashMap<String, Object>();
		fieldsToSend.put(EmployeeManagementFunctions.nameJsonPath,UtilityTools.generateRandomString(5));
		fieldsToSend.put(EmployeeManagementFunctions.imageJsonPath,"http://www.image1.jpg");
		fieldsToSend.put(EmployeeManagementFunctions.ageJsonPath,"$%%^");
		fieldsToSend.put(EmployeeManagementFunctions.salaryJsonPath,UtilityTools.getRandomNumber(5));
		EmployeeManagementFunctions emp=new EmployeeManagementFunctions();
		String[] reqResp=emp.addEmployee(fieldsToSend, 200);
		verifyEmpAddResponse(reqResp[1], reqResp[0]);	
		String id=emp.getJsonPathValue(reqResp[0],EmployeeManagementFunctions.idJsonPath);
		emp.deleteEmployee(id, 200);
	}
	
	/**
	 * Method to check employee create interface with special chars for salary
	 */
	@Test(groups = { "EmployeeCreate", "P2" })
	public void createEmployeeWithSpecialCharsSalary(){
		LoggerTool.log(LOG_LEVEL.INFO, "Scenario to test employee create interface special chars for salary");
		LinkedHashMap<String, Object> fieldsToSend=new LinkedHashMap<String, Object>();
		fieldsToSend.put(EmployeeManagementFunctions.nameJsonPath,UtilityTools.generateRandomString(5));
		fieldsToSend.put(EmployeeManagementFunctions.imageJsonPath,"http://www.image1.jpg");
		fieldsToSend.put(EmployeeManagementFunctions.ageJsonPath,UtilityTools.getRandomNumber(2));
		fieldsToSend.put(EmployeeManagementFunctions.salaryJsonPath,"@$%^&");
		EmployeeManagementFunctions emp=new EmployeeManagementFunctions();
		String[] reqResp=emp.addEmployee(fieldsToSend, 200);
		verifyEmpAddResponse(reqResp[1], reqResp[0]);
		String id=emp.getJsonPathValue(reqResp[0],EmployeeManagementFunctions.idJsonPath);
		emp.deleteEmployee(id, 200);
	}
	
	/**
	 * Method to check employee create interface with invalid image URL
	 */
	@Test(groups = { "EmployeeCreate", "P2" })
	public void createEmployeeWithInvalidImageUrl(){
		LoggerTool.log(LOG_LEVEL.INFO, "Scenario to test employee create interface with invalid image URL");
		LinkedHashMap<String, Object> fieldsToSend=new LinkedHashMap<String, Object>();
		fieldsToSend.put(EmployeeManagementFunctions.nameJsonPath,UtilityTools.generateRandomString(5));
		fieldsToSend.put(EmployeeManagementFunctions.imageJsonPath,UtilityTools.generateRandomString(50));
		fieldsToSend.put(EmployeeManagementFunctions.ageJsonPath,UtilityTools.getRandomNumber(2));
		fieldsToSend.put(EmployeeManagementFunctions.salaryJsonPath,UtilityTools.getRandomNumber(5));
		EmployeeManagementFunctions emp=new EmployeeManagementFunctions();
		String[] reqResp=emp.addEmployee(fieldsToSend, 200);
		verifyEmpAddResponse(reqResp[1], reqResp[0]);
		String id=emp.getJsonPathValue(reqResp[0],EmployeeManagementFunctions.idJsonPath);
		emp.deleteEmployee(id, 200);
	}
	
	/**
	 * Method to check employee create interface with .png image URL
	 */
	@Test(groups = { "EmployeeCreate", "P2" })
	public void createEmployeeWithPNGImageUrl(){
		LoggerTool.log(LOG_LEVEL.INFO, "Scenario to test employee create interface with .png image URL");
		LinkedHashMap<String, Object> fieldsToSend=new LinkedHashMap<String, Object>();
		fieldsToSend.put(EmployeeManagementFunctions.nameJsonPath,UtilityTools.generateRandomString(5));
		fieldsToSend.put(EmployeeManagementFunctions.imageJsonPath,"http://www.image1.png");
		fieldsToSend.put(EmployeeManagementFunctions.ageJsonPath,UtilityTools.getRandomNumber(2));
		fieldsToSend.put(EmployeeManagementFunctions.salaryJsonPath,UtilityTools.getRandomNumber(5));
		EmployeeManagementFunctions emp=new EmployeeManagementFunctions();
		String[] reqResp=emp.addEmployee(fieldsToSend, 200);
		Assert.assertTrue(reqResp[0].contains("id"));
		String id=emp.getJsonPathValue(reqResp[0],EmployeeManagementFunctions.idJsonPath);
		emp.deleteEmployee(id, 200);
	}
	
	/**
	 * Method to check employee create interface with .gif image URL
	 */
	@Test(groups = { "EmployeeCreate", "P2" })
	public void createEmployeeWithGIFImageUrl(){
		LoggerTool.log(LOG_LEVEL.INFO, "Scenario to test employee create interface with .GIF image URL");
		LinkedHashMap<String, Object> fieldsToSend=new LinkedHashMap<String, Object>();
		fieldsToSend.put(EmployeeManagementFunctions.nameJsonPath,UtilityTools.generateRandomString(5));
		fieldsToSend.put(EmployeeManagementFunctions.imageJsonPath,"http://www.image1.gif");
		fieldsToSend.put(EmployeeManagementFunctions.ageJsonPath,UtilityTools.getRandomNumber(2));
		fieldsToSend.put(EmployeeManagementFunctions.salaryJsonPath,UtilityTools.getRandomNumber(5));
		EmployeeManagementFunctions emp=new EmployeeManagementFunctions();
		String[] reqResp=emp.addEmployee(fieldsToSend, 200);
		verifyEmpAddResponse(reqResp[1], reqResp[0]);
		String id=emp.getJsonPathValue(reqResp[0],EmployeeManagementFunctions.idJsonPath);
		emp.deleteEmployee(id, 200);
	}

}
