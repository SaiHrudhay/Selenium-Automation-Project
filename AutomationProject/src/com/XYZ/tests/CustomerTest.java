package com.XYZ.tests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.AssertJUnit;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.XYZ.pages.CustomerPage;


import io.github.bonigarcia.wdm.WebDriverManager;

public class CustomerTest {
	// Declaring variables globally
	WebDriver driver;
	CustomerPage cl;
	String search = "Chrome";
// Using TestNG @BeforeTest annotation
	@BeforeTest
	public void setUp() {
		// Using control statements if else if
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
		// navigate to home page 
		driver.get("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		cl = new CustomerPage(driver);
	}
// here we are using TestNG @Test annotation
	@Test
	public void a_verifylogin() {
		cl.login();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		String actual = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]")).getText();
		boolean expected = actual.contains("Balance");
		AssertJUnit.assertTrue(expected);
		// Here we are using Asserts class 
	}

	@Test(dataProvider ="amt_1")
	public void b_deposit(String depositcheck) {
		cl.deposit(depositcheck);
	}
//Here we are using the @ Data Provider for the sending multiple inputs
	@DataProvider
	public Object[][]amt_1(){
		Object[][] obj = new Object[1][1];
		obj[0][0] = "10000";
		return obj;
	}
	
	// Here we are using @Test annotation including with data provider
	@Test(dataProvider ="amt_2")
	public void c_withdrawl(String withdrawlcheck) {
		cl.withdrawl(withdrawlcheck);
	}
	
	@DataProvider
	public Object[][]amt_2(){
		Object[][] obj = new Object[1][1];
		obj[0][0] = "5000";
		return obj;
	}
	
	@Test
	public void transaction() throws IOException, InterruptedException {
		// here we are giving implicty waits 
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		cl.transaction();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	// Here we are TestNG @AfterTest Annotation
	@AfterTest
	public void teardown() {
		driver.close();
	}
}