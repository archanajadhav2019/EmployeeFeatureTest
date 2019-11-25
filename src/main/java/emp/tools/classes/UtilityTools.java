package emp.tools.classes;

import java.util.Random;

//Class to functions providing different utilities to generate test data
public class UtilityTools {
	
    private static final String CHAR_LIST = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

	//Method to create random string of given length
	 public static String generateRandomString(final int charCount) {
	        final StringBuffer randStr = new StringBuffer();
	        for (int i = 0; i < charCount; i++) {
	            final int number = getRandomNumber(CHAR_LIST);
	            final char ch = CHAR_LIST.charAt(number);
	            randStr.append(ch);
	        }
	        return randStr.toString();
	    }
	 
	 //MEthod to create random number 
	 private static int getRandomNumber(final String chooseList) {
	        int randomInt = 0;
	        final Random randomGenerator = new Random();
	        randomInt = randomGenerator.nextInt(chooseList.length());
	        if (randomInt - 1 == -1) {
	            return randomInt;
	        } else {
	            return randomInt - 1;
	        }
	    }
	 
	 //Method to create random number of given length
	 public static int getRandomNumber(final int length) {
	        String limit = "1";
	        for (int i = 0; i < length; i++) {
	            limit = limit + "0";
	        }
	        int randomNum = (int) Math.round(Math.random() * Integer.valueOf(limit));
	        int count = 0, number = randomNum;
	        while (number != 0) {
	            number /= 10;
	            ++count;
	        }
	        if (count != length) {
	            randomNum = Integer.parseInt(randomNum + "0");
	        }
	        return randomNum;
	    }

}
