
package com.cst438;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;



public class E2E_AddAssignment {

	public static final String CHROME_DRIVER_FILE_LOCATION 
                          = "C:/chromedriver.exe";
	public static final String URL = "http://localhost:3000";
	public static final String COURSE_ID = "40443";
	public static final String ASSIGNMENT_NAME = "test";
	public static final String DUE_DATE = "2021-03-25";
	public static final int SLEEP_DURATION = 1000; // 1 second.

	@Test
	public void playGame() throws Exception {


		// set the driver location and start driver
		//@formatter:off
		//
		// browser	property name 				Java Driver Class
		// -------  ------------------------    ----------------------
		// Edge 	webdriver.edge.driver 		EdgeDriver
		// FireFox 	webdriver.firefox.driver 	FirefoxDriver
		// IE 		webdriver.ie.driver 		InternetExplorerDriver
		// Chrome   webdriver.chrome.driver     ChromeDriver
		//
		//@formatter:on


		//TODO update the property name for your browser 
		System.setProperty("webdriver.chrome.driver",
                     CHROME_DRIVER_FILE_LOCATION);
	//TODO update the class ChromeDriver()  for your browser
	// For chromedriver 111 need to specify the following options 
		ChromeOptions ops = new ChromeOptions();
		ops.addArguments("--remote-allow-origins=*");	

	
           WebDriver driver = new ChromeDriver(ops);
		
		try {
			WebElement we;
			
			driver.get(URL);
			// must have a short wait to allow time for the page to download 
			Thread.sleep(SLEEP_DURATION);


			// click on add assignment button
			we = driver.findElement(By.id("addAssignment"));
			we.click();
			Thread.sleep(SLEEP_DURATION);


			
			// enter the input for courseId
		
			we = driver.findElement(By.name("courseId"));
			//typing in answer
			we.sendKeys(COURSE_ID);
			
			// enter the input for assignmentName
			we = driver.findElement(By.name("name"));
			//typing into text field
			we.sendKeys(ASSIGNMENT_NAME);
			
			// enter the input for assignmentName
			we = driver.findElement(By.name("dueDate"));
			//typing into text field
			we.sendKeys(ASSIGNMENT_NAME);
			
			// find and click the add button
			we = driver.findElement(By.id("Add"));
			//clicks the submit button
			we.click();
			Thread.sleep(SLEEP_DURATION);
			
			/* verify the correct message\
			we = driver.findElement(By.id("toast_message"));
			String message = we.getText();
			assertEquals("Course succesfully added", message);
			*/
			
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		


	
		} finally {
			driver.close();
			driver.quit();
		}
	}
		
}

