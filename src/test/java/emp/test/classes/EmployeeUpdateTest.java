package emp.test.classes;

import java.util.LinkedHashMap;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import emp.restInterfaces.functions.EmployeeManagementFunctions;
import emp.tools.classes.LoggerTool;
import emp.tools.classes.LoggerTool.LOG_LEVEL;
import emp.tools.classes.UtilityTools;

//Test class to test employee update functionality
public class EmployeeUpdateTest extends TestVerification{

	String empId="";

	//Create employee test data for update
	@BeforeClass(alwaysRun=true)
	public void updateEmployeeTestData(){
		LinkedHashMap<String, Object> fieldsToSend=new LinkedHashMap<String, Object>();
		fieldsToSend.put(EmployeeManagementFunctions.nameJsonPath,UtilityTools.generateRandomString(5));
		fieldsToSend.put(EmployeeManagementFunctions.ageJsonPath,UtilityTools.getRandomNumber(2));
		fieldsToSend.put(EmployeeManagementFunctions.salaryJsonPath,UtilityTools.getRandomNumber(4));
		EmployeeManagementFunctions emp=new EmployeeManagementFunctions();
		String[] reqResp=emp.addEmployee(fieldsToSend, 200);
		empId=emp.getJsonPathValue(reqResp[0], EmployeeManagementFunctions.idJsonPath);
	}

	/**
	 * Method to check basic employee update functionality
	 */
	@Test(groups = { "EmployeeUpdate", "P1" })
	public void updateEmployeeWithAllValidData(){
		LoggerTool.log(LOG_LEVEL.INFO, "Scenario to test basic employee update function with valid data");
		LinkedHashMap<String, Object> fieldsToSend=new LinkedHashMap<String, Object>();
		fieldsToSend.put(EmployeeManagementFunctions.nameJsonPath,UtilityTools.generateRandomString(5));
		fieldsToSend.put(EmployeeManagementFunctions.ageJsonPath,UtilityTools.getRandomNumber(2));
		fieldsToSend.put(EmployeeManagementFunctions.salaryJsonPath,UtilityTools.getRandomNumber(4));
		EmployeeManagementFunctions emp=new EmployeeManagementFunctions();
		String[] reqResp=emp.updateEmployee(fieldsToSend,empId, 200);
		String empGetResp=emp.getEmployee(empId, 200);
		verifyEmpGetResponse(reqResp[1],empGetResp);
	}

	/**
	 * Method to check employee update interface without salary
	 */
	@Test(groups = { "EmployeeUpdate", "P2" })
	public void updateEmployeeWithoutSalary(){
		LoggerTool.log(LOG_LEVEL.INFO, "Scenario to check employee update interface without salary");
		LinkedHashMap<String, Object> fieldsToSend=new LinkedHashMap<String, Object>();
		fieldsToSend.put(EmployeeManagementFunctions.nameJsonPath,UtilityTools.generateRandomString(5));
		fieldsToSend.put(EmployeeManagementFunctions.imageJsonPath,"http://www.image1.jpg");
		fieldsToSend.put(EmployeeManagementFunctions.ageJsonPath,UtilityTools.getRandomNumber(2));
		fieldsToSend.put(EmployeeManagementFunctions.salaryJsonPath,"missing");
		EmployeeManagementFunctions emp=new EmployeeManagementFunctions();
		emp.updateEmployee(fieldsToSend,empId, 200);
		String empGetResp=emp.getEmployee(empId, 200);
		Assert.assertEquals(emp.getJsonPathValue(empGetResp, EmployeeManagementFunctions.employeeSalaryJsonPath), "0");
	}

	/**
	 * Method to check employee update interface without age
	 */
	@Test(groups = { "EmployeeUpdate", "P2" })
	public void updateEmployeeWithoutAge(){
		LoggerTool.log(LOG_LEVEL.INFO, "Scenario to test employee update interface without age");
		LinkedHashMap<String, Object> fieldsToSend=new LinkedHashMap<String, Object>();
		fieldsToSend.put(EmployeeManagementFunctions.nameJsonPath,UtilityTools.generateRandomString(5));
		fieldsToSend.put(EmployeeManagementFunctions.imageJsonPath,"http://www.image1.jpg");
		fieldsToSend.put(EmployeeManagementFunctions.ageJsonPath,"missing");
		fieldsToSend.put(EmployeeManagementFunctions.salaryJsonPath,UtilityTools.getRandomNumber(4));
		EmployeeManagementFunctions emp=new EmployeeManagementFunctions();
		emp.updateEmployee(fieldsToSend,empId, 200);
		String empGetResp=emp.getEmployee(empId, 200);
		Assert.assertEquals(emp.getJsonPathValue(empGetResp, EmployeeManagementFunctions.employeeAgeJsonPath), "0");
	}

	/**
	 * Method to check employee update interface without image
	 */
	@Test(groups = { "EmployeeUpdate", "P2" })
	public void updateEmployeeWithoutProfileImage(){
		LoggerTool.log(LOG_LEVEL.INFO, "Scenario to test employee update interface without image");
		LinkedHashMap<String, Object> fieldsToSend=new LinkedHashMap<String, Object>();
		fieldsToSend.put(EmployeeManagementFunctions.nameJsonPath,UtilityTools.generateRandomString(5));
		fieldsToSend.put(EmployeeManagementFunctions.imageJsonPath,"missing");
		fieldsToSend.put(EmployeeManagementFunctions.ageJsonPath,UtilityTools.getRandomNumber(2));
		fieldsToSend.put(EmployeeManagementFunctions.salaryJsonPath,UtilityTools.getRandomNumber(4));
		EmployeeManagementFunctions emp=new EmployeeManagementFunctions();
		String[] reqResp=emp.updateEmployee(fieldsToSend,empId, 200);
		String empGetResp=emp.getEmployee(empId, 200);
		verifyEmpGetResponse(reqResp[1],empGetResp);
	}

	/**
	 * Method to check employee update interface without name
	 */
	@Test(groups = { "EmployeeUpdate", "P2" })
	public void updateEmployeeWithoutName(){
		LoggerTool.log(LOG_LEVEL.INFO, "Scenario to test employee update interface without name");
		LinkedHashMap<String, Object> fieldsToSend=new LinkedHashMap<String, Object>();
		fieldsToSend.put(EmployeeManagementFunctions.nameJsonPath,"missing");
		fieldsToSend.put(EmployeeManagementFunctions.imageJsonPath,"http://www.image1.jpg");
		fieldsToSend.put(EmployeeManagementFunctions.ageJsonPath,UtilityTools.getRandomNumber(2));
		fieldsToSend.put(EmployeeManagementFunctions.salaryJsonPath,UtilityTools.getRandomNumber(4));
		EmployeeManagementFunctions emp=new EmployeeManagementFunctions();
		String[] reqResp=emp.updateEmployee(fieldsToSend,empId, 200);
		Assert.assertTrue(reqResp[0].contains("Duplicate entry"));
	}

	/**
	 * Method to check employee update interface with null value for salary
	 */
	@Test(groups = { "EmployeeUpdate", "P2" })
	public void updateEmployeeWithNullSalary(){
		LoggerTool.log(LOG_LEVEL.INFO, "Scenario to test employee update interface with null value for salary");
		LinkedHashMap<String, Object> fieldsToSend=new LinkedHashMap<String, Object>();
		fieldsToSend.put(EmployeeManagementFunctions.nameJsonPath,UtilityTools.generateRandomString(5));
		fieldsToSend.put(EmployeeManagementFunctions.imageJsonPath,"http://www.image1.jpg");
		fieldsToSend.put(EmployeeManagementFunctions.ageJsonPath,UtilityTools.getRandomNumber(2));
		fieldsToSend.put(EmployeeManagementFunctions.salaryJsonPath,null);
		EmployeeManagementFunctions emp=new EmployeeManagementFunctions();
		emp.updateEmployee(fieldsToSend,empId, 200);
		String empGetResp=emp.getEmployee(empId, 200);
		Assert.assertEquals(emp.getJsonPathValue(empGetResp, EmployeeManagementFunctions.employeeSalaryJsonPath), "0");

	}

	/**
	 * Method to check employee update interface with null value for age
	 */
	@Test(groups = { "EmployeeUpdate", "P2" })
	public void updateEmployeeWithNullAge(){
		LoggerTool.log(LOG_LEVEL.INFO, "Scenario to test employee update interface with null value for age");
		LinkedHashMap<String, Object> fieldsToSend=new LinkedHashMap<String, Object>();
		fieldsToSend.put(EmployeeManagementFunctions.nameJsonPath,UtilityTools.generateRandomString(5));
		fieldsToSend.put(EmployeeManagementFunctions.imageJsonPath,"http://www.image1.jpg");
		fieldsToSend.put(EmployeeManagementFunctions.ageJsonPath,null);
		fieldsToSend.put(EmployeeManagementFunctions.salaryJsonPath,UtilityTools.getRandomNumber(4));
		EmployeeManagementFunctions emp=new EmployeeManagementFunctions();
		emp.updateEmployee(fieldsToSend,empId, 200);
		String empGetResp=emp.getEmployee(empId, 200);
		Assert.assertEquals(emp.getJsonPathValue(empGetResp, EmployeeManagementFunctions.employeeAgeJsonPath), "0");
	}

	/**
	 * Method to check employee update interface with null value for image
	 */
	@Test(groups = {"EmployeeUpdate", "P2" })
	public void updateEmployeeWithNullProfileImage(){
		LoggerTool.log(LOG_LEVEL.INFO, "Scenario to test employee update interface with null value for image");
		LinkedHashMap<String, Object> fieldsToSend=new LinkedHashMap<String, Object>();
		fieldsToSend.put(EmployeeManagementFunctions.nameJsonPath,UtilityTools.generateRandomString(5));
		fieldsToSend.put(EmployeeManagementFunctions.imageJsonPath,null);
		fieldsToSend.put(EmployeeManagementFunctions.ageJsonPath,UtilityTools.getRandomNumber(2));
		fieldsToSend.put(EmployeeManagementFunctions.salaryJsonPath,UtilityTools.getRandomNumber(4));
		EmployeeManagementFunctions emp=new EmployeeManagementFunctions();
		String[] reqResp=emp.updateEmployee(fieldsToSend,empId, 200);
		String empGetResp=emp.getEmployee(empId, 200);
		verifyEmpGetResponse(reqResp[1],empGetResp);
	}

	/**
	 * Method to check employee update interface with null name
	 */
	@Test(groups = { "EmployeeUpdate", "P2" })
	public void updateEmployeeWithNullName(){
		LoggerTool.log(LOG_LEVEL.INFO, "Scenario to test employee update interface with null name");
		LinkedHashMap<String, Object> fieldsToSend=new LinkedHashMap<String, Object>();
		fieldsToSend.put(EmployeeManagementFunctions.nameJsonPath,null);
		fieldsToSend.put(EmployeeManagementFunctions.imageJsonPath,"http://www.image1.jpg");
		fieldsToSend.put(EmployeeManagementFunctions.ageJsonPath,UtilityTools.getRandomNumber(2));
		fieldsToSend.put(EmployeeManagementFunctions.salaryJsonPath,UtilityTools.getRandomNumber(4));
		EmployeeManagementFunctions emp=new EmployeeManagementFunctions();
		String[] reqResp=emp.updateEmployee(fieldsToSend,empId, 200);
		Assert.assertTrue(reqResp[0].contains("Duplicate entry"));
	}

	/**
	 * Method to check employee update interface with empty name
	 */
	@Test(groups = { "EmployeeUpdate", "P2" })
	public void updateEmployeeWithEmptyName(){
		LoggerTool.log(LOG_LEVEL.INFO, "Scenario to test employee update interface with empty name");
		LinkedHashMap<String, Object> fieldsToSend=new LinkedHashMap<String, Object>();
		fieldsToSend.put(EmployeeManagementFunctions.nameJsonPath,"");
		fieldsToSend.put(EmployeeManagementFunctions.imageJsonPath,"http://www.image1.jpg");
		fieldsToSend.put(EmployeeManagementFunctions.ageJsonPath,UtilityTools.getRandomNumber(2));
		fieldsToSend.put(EmployeeManagementFunctions.salaryJsonPath,UtilityTools.getRandomNumber(4));
		EmployeeManagementFunctions emp=new EmployeeManagementFunctions();
		String[] reqResp=emp.updateEmployee(fieldsToSend,empId, 200);
		Assert.assertTrue(reqResp[0].contains("Duplicate entry"));
	}

	/**
	 * Method to check employee update interface with special characters in name
	 */
	@Test(groups = { "EmployeeUpdate", "P2" })
	public void updateEmployeeWithSpecialCharsInName(){
		LoggerTool.log(LOG_LEVEL.INFO, "Scenario to test employee update interface with special characters in name");
		LinkedHashMap<String, Object> fieldsToSend=new LinkedHashMap<String, Object>();
		fieldsToSend.put(EmployeeManagementFunctions.nameJsonPath,"$%^&");
		fieldsToSend.put(EmployeeManagementFunctions.imageJsonPath,"http://www.image1.jpg");
		fieldsToSend.put(EmployeeManagementFunctions.ageJsonPath,UtilityTools.getRandomNumber(2));
		fieldsToSend.put(EmployeeManagementFunctions.salaryJsonPath,UtilityTools.getRandomNumber(4));
		EmployeeManagementFunctions emp=new EmployeeManagementFunctions();
		String[] reqResp=emp.updateEmployee(fieldsToSend,empId, 200);
		Assert.assertTrue(reqResp[0].contains("Duplicate entry"));
	}

	/**
	 * Method to check employee update interface with alphanumeric characters in name
	 */
	@Test(groups = { "EmployeeUpdate", "P2" })
	public void updateEmployeeWithAlphaNumericInName(){
		LoggerTool.log(LOG_LEVEL.INFO, "Scenario to test employee update interface with alphanumeric characters in name");
		LinkedHashMap<String, Object> fieldsToSend=new LinkedHashMap<String, Object>();
		fieldsToSend.put(EmployeeManagementFunctions.nameJsonPath,UtilityTools.generateRandomString(4)+UtilityTools.getRandomNumber(4));
		fieldsToSend.put(EmployeeManagementFunctions.imageJsonPath,"http://www.image1.jpg");
		fieldsToSend.put(EmployeeManagementFunctions.ageJsonPath,UtilityTools.getRandomNumber(2));
		fieldsToSend.put(EmployeeManagementFunctions.salaryJsonPath,UtilityTools.getRandomNumber(4));
		EmployeeManagementFunctions emp=new EmployeeManagementFunctions();
		String[] reqResp=emp.updateEmployee(fieldsToSend,empId, 200);
		String empGetResp=emp.getEmployee(empId, 200);
		verifyEmpGetResponse(reqResp[1],empGetResp);
	}

	/**
	 * Method to check employee update interface with decimal value for salary
	 */
	@Test(groups = { "EmployeeUpdate", "P2" })
	public void updateEmployeeWithDecimalSalary(){
		LoggerTool.log(LOG_LEVEL.INFO, "Scenario to test employee update interface with decimal value for salary");
		LinkedHashMap<String, Object> fieldsToSend=new LinkedHashMap<String, Object>();
		fieldsToSend.put(EmployeeManagementFunctions.nameJsonPath,UtilityTools.generateRandomString(5));
		fieldsToSend.put(EmployeeManagementFunctions.imageJsonPath,"http://www.image1.jpg");
		fieldsToSend.put(EmployeeManagementFunctions.ageJsonPath,UtilityTools.getRandomNumber(2));
		fieldsToSend.put(EmployeeManagementFunctions.salaryJsonPath,7.7647);
		EmployeeManagementFunctions emp=new EmployeeManagementFunctions();
		emp.updateEmployee(fieldsToSend,empId, 200);
		String empGetResp=emp.getEmployee(empId, 200);
		Assert.assertEquals(emp.getJsonPathValue(empGetResp, EmployeeManagementFunctions.employeeSalaryJsonPath),Integer.toString((int)(Math.ceil(7.7647))));

	}

	/**
	 * Method to check employee update interface with alphanumeric value for salary
	 */
	@Test(groups = { "EmployeeUpdate", "P2" })
	public void updateEmployeeWithAlphaNumericSalary(){
		LoggerTool.log(LOG_LEVEL.INFO, "Scenario to test employee update interface with alphanumeric value for salary");
		LinkedHashMap<String, Object> fieldsToSend=new LinkedHashMap<String, Object>();
		fieldsToSend.put(EmployeeManagementFunctions.nameJsonPath,UtilityTools.generateRandomString(5));
		fieldsToSend.put(EmployeeManagementFunctions.imageJsonPath,"http://www.image1.jpg");
		fieldsToSend.put(EmployeeManagementFunctions.ageJsonPath,UtilityTools.getRandomNumber(2));
		fieldsToSend.put(EmployeeManagementFunctions.salaryJsonPath,UtilityTools.generateRandomString(5)+UtilityTools.getRandomNumber(5));
		EmployeeManagementFunctions emp=new EmployeeManagementFunctions();
		emp.updateEmployee(fieldsToSend,empId, 200);
		String empGetResp=emp.getEmployee(empId, 200);
		Assert.assertEquals(emp.getJsonPathValue(empGetResp, EmployeeManagementFunctions.employeeSalaryJsonPath), "0");
	}

	/**
	 * Method to check employee update interface with decimal value for age
	 */
	@Test(groups = { "EmployeeUpdate", "P2" })
	public void updateEmployeeWithDecimalAge(){
		LoggerTool.log(LOG_LEVEL.INFO, "Scenario to test employee update interface with decimal value for age");
		LinkedHashMap<String, Object> fieldsToSend=new LinkedHashMap<String, Object>();
		fieldsToSend.put(EmployeeManagementFunctions.nameJsonPath,UtilityTools.generateRandomString(5));
		fieldsToSend.put(EmployeeManagementFunctions.imageJsonPath,"http://www.image1.jpg");
		fieldsToSend.put(EmployeeManagementFunctions.ageJsonPath,5.7657);
		fieldsToSend.put(EmployeeManagementFunctions.salaryJsonPath,UtilityTools.getRandomNumber(5));
		EmployeeManagementFunctions emp=new EmployeeManagementFunctions();
		emp.updateEmployee(fieldsToSend,empId, 200);
		String empGetResp=emp.getEmployee(empId, 200);
		Assert.assertEquals(emp.getJsonPathValue(empGetResp, EmployeeManagementFunctions.employeeAgeJsonPath), Integer.toString((int)(Math.ceil(5.7657))));
	}

	/**
	 * Method to check employee update interface with alphanumeric value for age
	 */
	@Test(groups = { "EmployeeUpdate", "P2" })
	public void updateEmployeeWithAlphaNumericAge(){
		LoggerTool.log(LOG_LEVEL.INFO, "Scenario to test employee update interface with alphanumeric value for age");
		LinkedHashMap<String, Object> fieldsToSend=new LinkedHashMap<String, Object>();
		fieldsToSend.put(EmployeeManagementFunctions.nameJsonPath,UtilityTools.generateRandomString(5));
		fieldsToSend.put(EmployeeManagementFunctions.imageJsonPath,"http://www.image1.jpg");
		fieldsToSend.put(EmployeeManagementFunctions.ageJsonPath,"abc"+UtilityTools.getRandomNumber(5));
		fieldsToSend.put(EmployeeManagementFunctions.salaryJsonPath,UtilityTools.getRandomNumber(6));
		EmployeeManagementFunctions emp=new EmployeeManagementFunctions();
		emp.updateEmployee(fieldsToSend,empId, 200);
		String empGetResp=emp.getEmployee(empId, 200);
		Assert.assertEquals(emp.getJsonPathValue(empGetResp, EmployeeManagementFunctions.employeeAgeJsonPath), "0");
	}

	/**
	 * Method to check employee update interface with special chars for age
	 */
	@Test(groups = { "EmployeeUpdate", "P2" })
	public void updateEmployeeWithSpecialCharsAge(){
		LoggerTool.log(LOG_LEVEL.INFO, "Scenario to test employee update interface with special chars for age");
		LinkedHashMap<String, Object> fieldsToSend=new LinkedHashMap<String, Object>();
		fieldsToSend.put(EmployeeManagementFunctions.nameJsonPath,UtilityTools.generateRandomString(5));
		fieldsToSend.put(EmployeeManagementFunctions.imageJsonPath,"http://www.image1.jpg");
		fieldsToSend.put(EmployeeManagementFunctions.ageJsonPath,"$%%^");
		fieldsToSend.put(EmployeeManagementFunctions.salaryJsonPath,UtilityTools.getRandomNumber(5));
		EmployeeManagementFunctions emp=new EmployeeManagementFunctions();
		emp.updateEmployee(fieldsToSend,empId, 200);
		String empGetResp=emp.getEmployee(empId, 200);
		Assert.assertEquals(emp.getJsonPathValue(empGetResp, EmployeeManagementFunctions.employeeAgeJsonPath), "0");
	}

	/**
	 * Method to check employee update interface with special chars for salary
	 */
	@Test(groups = { "EmployeeUpdate", "P2" })
	public void updateEmployeeWithSpecialCharsSalary(){
		LoggerTool.log(LOG_LEVEL.INFO, "Scenario to test employee update interface special chars for salary");
		LinkedHashMap<String, Object> fieldsToSend=new LinkedHashMap<String, Object>();
		fieldsToSend.put(EmployeeManagementFunctions.nameJsonPath,UtilityTools.generateRandomString(5));
		fieldsToSend.put(EmployeeManagementFunctions.imageJsonPath,"http://www.image1.jpg");
		fieldsToSend.put(EmployeeManagementFunctions.ageJsonPath,UtilityTools.getRandomNumber(2));
		fieldsToSend.put(EmployeeManagementFunctions.salaryJsonPath,"@$%^&");
		EmployeeManagementFunctions emp=new EmployeeManagementFunctions();
		emp.updateEmployee(fieldsToSend,empId, 200);
		String empGetResp=emp.getEmployee(empId, 200);
		Assert.assertEquals(emp.getJsonPathValue(empGetResp, EmployeeManagementFunctions.employeeSalaryJsonPath), "0");
	}

	/**
	 * Method to check employee update interface with invalid image URL
	 */
	@Test(groups = { "EmployeeUpdate", "P2" })
	public void updateEmployeeWithInvalidImageUrl(){
		LoggerTool.log(LOG_LEVEL.INFO, "Scenario to test employee update interface with invalid image URL");
		LinkedHashMap<String, Object> fieldsToSend=new LinkedHashMap<String, Object>();
		fieldsToSend.put(EmployeeManagementFunctions.nameJsonPath,UtilityTools.generateRandomString(5));
		fieldsToSend.put(EmployeeManagementFunctions.imageJsonPath,UtilityTools.generateRandomString(50));
		fieldsToSend.put(EmployeeManagementFunctions.ageJsonPath,UtilityTools.getRandomNumber(2));
		fieldsToSend.put(EmployeeManagementFunctions.salaryJsonPath,UtilityTools.getRandomNumber(5));
		EmployeeManagementFunctions emp=new EmployeeManagementFunctions();
		String[] reqResp=emp.updateEmployee(fieldsToSend,empId, 200);
		String empGetResp=emp.getEmployee(empId, 200);
		verifyEmpGetResponse(reqResp[1],empGetResp);
	}

	/**
	 * Method to check employee update interface with .png image URL
	 */
	@Test(groups = { "EmployeeUpdate", "P2" })
	public void updateEmployeeWithPNGImageUrl(){
		LoggerTool.log(LOG_LEVEL.INFO, "Scenario to test employee update interface with .png image URL");
		LinkedHashMap<String, Object> fieldsToSend=new LinkedHashMap<String, Object>();
		fieldsToSend.put(EmployeeManagementFunctions.nameJsonPath,UtilityTools.generateRandomString(5));
		fieldsToSend.put(EmployeeManagementFunctions.imageJsonPath,"http://www.image1.png");
		fieldsToSend.put(EmployeeManagementFunctions.ageJsonPath,UtilityTools.getRandomNumber(2));
		fieldsToSend.put(EmployeeManagementFunctions.salaryJsonPath,UtilityTools.getRandomNumber(5));
		EmployeeManagementFunctions emp=new EmployeeManagementFunctions();
		String[] reqResp=emp.updateEmployee(fieldsToSend,empId, 200);
		String empGetResp=emp.getEmployee(empId, 200);
		verifyEmpGetResponse(reqResp[1],empGetResp);
	}

	/**
	 * Method to check employee update interface with .gif image URL
	 */
	@Test(groups = { "EmployeeUpdate", "P2" })
	public void updateEmployeeWithGIFImageUrl(){
		LoggerTool.log(LOG_LEVEL.INFO, "Scenario to test employee update interface with .GIF image URL");
		LinkedHashMap<String, Object> fieldsToSend=new LinkedHashMap<String, Object>();
		fieldsToSend.put(EmployeeManagementFunctions.nameJsonPath,UtilityTools.generateRandomString(5));
		fieldsToSend.put(EmployeeManagementFunctions.imageJsonPath,"http://www.image1.gif");
		fieldsToSend.put(EmployeeManagementFunctions.ageJsonPath,UtilityTools.getRandomNumber(2));
		fieldsToSend.put(EmployeeManagementFunctions.salaryJsonPath,UtilityTools.getRandomNumber(5));
		EmployeeManagementFunctions emp=new EmployeeManagementFunctions();
		String[] reqResp=emp.updateEmployee(fieldsToSend,empId, 200);
		String empGetResp=emp.getEmployee(empId, 200);
		verifyEmpGetResponse(reqResp[1],empGetResp);
	}

	//Delete created employee
	@AfterClass(alwaysRun=true)
	public void deleteEmployee(){
		EmployeeManagementFunctions emp=new EmployeeManagementFunctions();
		emp.deleteEmployee(empId, 200);
	}

}
