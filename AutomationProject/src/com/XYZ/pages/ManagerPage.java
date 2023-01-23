package com.XYZ.pages;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.DataProvider;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ManagerPage {
	// declaring variables globally 
	ExtentSparkReporter reporter;
	ExtentReports extent;
	ExtentTest test;
	// Here we are finding elements in the manager login page by using xpath
	WebDriver driver;
	By managerBtn = By.xpath("/html/body/div/div/div[2]/div/div[1]/div[2]/button");
	//Here we are finding the xpath of the manager login button
	By addCust = By.xpath("/html/body/div/div/div[2]/div/div[1]/button[1]");
	// Here we are finding the xpath of the add customer
	By fName = By.xpath("/html/body/div/div/div[2]/div/div[2]/div/div/form/div[1]/input");
	// Here we are the finding the frist name in add customer
	By lName = By.xpath("/html/body/div/div/div[2]/div/div[2]/div/div/form/div[2]/input");
	// Here we are the xpath of the last name in add customer
	By pCode = By.xpath("/html/body/div/div/div[2]/div/div[2]/div/div/form/div[3]/input");
	// Here we are finding the xpath of the Post Code in add customer 
	By sBtn  = By.xpath("/html/body/div/div/div[2]/div/div[2]/div/div/form/button");
	// Here we are finding the xpath of the submit button
	By openAcc = By.xpath("/html/body/div/div/div[2]/div/div[1]/button[2]");
	// Here we are  find the xpath of the open Account 
	
	WebElement drop1;
	WebElement drop2;
	// Here we are using the drop down to select the name and currency from the drop down
	Select by;

	By sBtn2 = By.xpath("/html/body/div/div/div[2]/div/div[2]/div/div/form/button");
	
	public ManagerPage(WebDriver driver) {
		this.driver = driver;
	}

	public void addCustomer() throws IOException {
		
		// Here we are creating extent reports to add customer module
		reporter = new ExtentSparkReporter(".\\Data\\reports\\report.html");
		reporter.config().setDocumentTitle("Automatiom Reports");
		reporter.config().setReportName("XYZBank.org automation");
		reporter.config().setTheme(Theme.DARK);
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		test = extent.createTest("My first test", "this is the test to validate search page of XYZ Bank.com");
		// Here Manager performing add customer by using excel sheet called XYZ Bank.xlsx
		Assert.assertSame(managerBtn, managerBtn);
		driver.findElement(managerBtn).click();
		// Finding the manager button and performing click on it
		Assert.assertSame(addCust, addCust);
		driver.findElement(addCust).click();
		// Finding the add customer button and performing click on it
		test.log(Status.INFO , "starting the testcase");
		// Setting the path of the excel file
		FileInputStream fis = new FileInputStream(".\\Data\\XYZ Bank.xlsx");
		// creating excel file
        XSSFWorkbook workBook = new XSSFWorkbook(fis);
        // And here we are creating sheet in workbook
        XSSFSheet sheet = workBook.getSheet("Sheet1");
        test.pass("The Excel sheet was created successfully");
        int row = sheet.getLastRowNum();
        int col = sheet.getRow(1).getLastCellNum();
        // by using for statements the application going to take details of customer from excel sheet
        for(int i=1;i<=row;i++) {
        // Here we are taking first name, last name and post code from excel sheet
         driver.findElement(fName).sendKeys(sheet.getRow(i).getCell(1).getStringCellValue());
         driver.findElement(lName).sendKeys(sheet.getRow(i).getCell(2).getStringCellValue());
         driver.findElement(pCode).sendKeys(sheet.getRow(i).getCell(3).getStringCellValue());
		 driver.findElement(sBtn).click();
		 test.pass("enter the next in the details box");
		 extent.flush();
 }	
	}

	public void  openAccount() {
		// Here manager wants to open the account of the customer 
		// And also adding extent reports
		reporter = new ExtentSparkReporter(".\\Data\\reports\\report1.html");
		reporter.config().setDocumentTitle("Automatiom Reports");
		reporter.config().setReportName("XYZ Bank.com automation");
		reporter.config().setTheme(Theme.DARK);
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		test = extent.createTest("my first test", "this is the test to validate search page of XYZ Bank.com");
		//Finding open account and clicking on it and adding customer 
		driver.findElement(openAcc).click();
		test.log(Status.INFO , "Again starting the testcase");
		WebElement drop1 = driver.findElement(By.name("userSelect"));
		Select by = new Select(drop1);
		// And selecting customer name from dropdown
		by.selectByVisibleText("Venkat Degala");
		test.pass("customer is visiable in page");
		WebElement drop2 = driver.findElement(By.id("currency"));
		Select by1 = new Select(drop2);
		// And selecting currency of the customer 
		by1.selectByVisibleText("Rupee");
		test.pass("customer successfully select currency");
		Assert.assertSame(sBtn2, sBtn2);
		driver.findElement(sBtn2).click();;
		// finding submit button and clicking on it
		extent.flush();
	
		
		
		
	}


}