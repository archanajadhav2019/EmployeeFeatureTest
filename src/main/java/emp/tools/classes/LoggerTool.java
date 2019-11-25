package emp.tools.classes;

import org.testng.Reporter;


public class LoggerTool {
	
	//Log levels
	public static enum LOG_LEVEL {
        INFO, DEBUG
    }
	
	//Method to log data
	public static void log(final LoggerTool.LOG_LEVEL logType, final String textToPrint) {
		if(logType==LoggerTool.LOG_LEVEL.INFO){
            Reporter.log(" INFO::" + textToPrint, true);
        } else{
        	Reporter.log(" DEBUG::" + textToPrint, true);
        }
        	
}
}
