package com.XYZ.tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.XYZ.pages.ManagerPage;


import io.github.bonigarcia.wdm.WebDriverManager;

public class ManagerTest {
	// Declaring variables globally
	WebDriver driver;
	ManagerPage ml;
	Alert alert;
	Alert alert1;
	String search="Chrome";
	String altext1;
	// Finding the elements in Manager Login and storing in them in individual variables 
	By confirm = By.xpath("/html/body/div/div/div[2]/div/div[2]/div/div/table/tbody/tr[6]/td[4]/span[2]");
	// Finding the xpath of the customers 
	By customers = By.xpath("/html/body/div/div/div[2]/div/div[1]/button[3]");
	// After the navigate to the customers we can find the xpath of the delete customer
	By cusDelete = By.xpath("/html/body/div/div/div[2]/div/div[2]/div/div/table/tbody/tr[4]/td[5]/button");

	By cusDelete1 = By.xpath("/html/body/div/div/div[2]/div/div[2]/div/div/table/tbody/tr[5]/td[5]/button");

// Here we are using the TestNG @BeforeTest Annotation
	@BeforeClass
	public void setUp() {
// And setup the browser by using control statements like if else if
		if(search.equalsIgnoreCase("Chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();

		}else if(search.equalsIgnoreCase("Edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();

		}else if(search.equalsIgnoreCase("Opera")) {
			WebDriverManager.operadriver().setup();
			driver = new OperaDriver();

		}else {
			System.out.println("INVALID");
		}
		driver.get("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		ml = new ManagerPage(driver);
	}
// Here we are using the TEstNG @Test Annotations
	@Test
	public void verifyAddCustomers() throws IOException {
	//	Here We are calling the methods from com.Pages 
		ml.addCustomer();
		alert = driver.switchTo().alert();
		String altext = alert.getText();
		String expText = "Customer added successfully with customer id :6";
		alert.accept();
		// We are using assersion to compare the text in the alerts
		AssertJUnit.assertEquals(altext, expText);

	}

	// Here we are using the TEstNG @Test Annotations
	@Test
	public void verifyOpenAccount() {
//		Here We are calling the methods from com.Pages 
		ml.openAccount();
		// Here we are using alerts 
		alert1 = driver.switchTo().alert();
		altext1 = alert1.getText();
		String expText1 = "Account created successfully with account Number :1016";
		alert.accept();
		// Here we are giving implicitly waits
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		// We are using assersion to compare the text in the alerts
		AssertJUnit.assertEquals(altext1, expText1);
		driver.navigate().refresh();
		boolean altext3 = altext1.contains("1016");
		// Here we are using Asserts Classes
		AssertJUnit.assertTrue(altext3);
		driver.findElement(customers).click();
		// After find the customers we are performing click on it
		driver.findElement(cusDelete).click();
		// After find the customer delete we are performing click on it
		driver.findElement(cusDelete1).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

// Here we are using the TestNg @AfterClass Annotation

	@AfterClass
	public void teardown() {
		driver.close();
		// we are closing the browser
	}
}