package emp.testng.integration;


import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

public class TestngListeners implements ITestListener {

	private final boolean LogToStandardOutput = true;
	private static int count = 1;
	private static org.apache.log4j.Logger log = Logger.getLogger(TestngListeners.class);

	public void onTestStart(final ITestResult arg0) {
		Reporter.setCurrentTestResult(arg0);
		Reporter.log("<br>");
		Reporter.log("----------------------------------------------------------", LogToStandardOutput);
		log.info("----------------------------------------------------------");
		Reporter.log("</br>");
		final int no = count++;
		Reporter.log(no + "::Initiating TestCase::" + arg0.getName() + " (" + arg0.getMethod().getId() + ")",
				LogToStandardOutput);
		log.info(no + "::Initiating TestCase::" + arg0.getName() + " (" + arg0.getMethod().getId() + ")");

		Reporter.log("</br>");

	}

	public void onTestSuccess(final ITestResult arg0) {
		Reporter.log("<br>");
		Reporter.log("Completed TestCase :: " + arg0.getName() + " => Status: PASS", LogToStandardOutput);
		log.info("Completed TestCase :: " + arg0.getName() + " => Status: PASS");
		Reporter.log("</br>");
		Reporter.log("----------------------------------------------------------", LogToStandardOutput);
		log.info("----------------------------------------------------------");

	}

	public void onTestFailure(final ITestResult arg0) {
		Reporter.log("</br>");
		Reporter.log("Completed TestCase :: " + arg0.getName()+ " =>  "+ "Status: FAIL",LogToStandardOutput);
		log.info("Completed TestCase :: " + arg0.getName() + " => Status: FAIL");
		Reporter.log("</br>");
		Reporter.log("----------------------------------------------------------", LogToStandardOutput);
		log.info("----------------------------------------------------------");
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}


}