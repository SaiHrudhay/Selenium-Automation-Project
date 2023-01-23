package com.XYZ.pages;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class CustomerPage {

	// Here we choosing the xpath of the elements in customer login module
	WebDriver driver;
	By customerlogin = By.xpath("/html/body/div/div/div[2]/div/div[1]/div[1]/button");
	// finding the xpath of the customer login button
	By loginbtn3 = By.xpath("/html/body/div/div/div[2]/div/form/button");
	// finding the xpath of the login button
	By deposit = By.xpath("/html/body/div/div/div[2]/div/div[3]/button[2]");
	// finding the xpath of the deposit button
	By depositamt = By.xpath("/html/body/div/div/div[2]/div/div[4]/div/form/div/input");
	// finding the xpath of the text box of the deposit amount
	By depositbtn = By.xpath("/html/body/div/div/div[2]/div/div[4]/div/form/button");
	// finding the xpath of the deposit button
	By withdrawl = By.xpath("/html/body/div/div/div[2]/div/div[3]/button[3]");
	// finding the xpath of the withdrawl element
	By withdrawlamt = By.xpath("/html/body/div/div/div[2]/div/div[4]/div/form/div/input");
	// finding the xpath of the text box of the withdrawl amount
	By withdrawlbtn = By.xpath("/html/body/div/div/div[2]/div/div[4]/div/form/button");
	// finding the xpath of the withdrawl element
	ExtentSparkReporter reporter;
	ExtentReports extent;
	ExtentTest test;

	By transaction = By.xpath("/html/body/div/div/div[2]/div/div[3]/button[1]");

	public CustomerPage(WebDriver driver) {
		this.driver = driver;
	}

	public void login() {
		// Here we are using extent reports
		reporter = new ExtentSparkReporter(".\\Data\\reports\\report2.html");
		reporter.config().setDocumentTitle("Automatiom Reports");
		reporter.config().setReportName("XYZBank.org automation");
		reporter.config().setTheme(Theme.DARK);
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		test = extent.createTest("My first test", "this is the test to validate search page of XYZ Bank.com");
		// Here selecting the customer login and selecting name of customer from
		// dropdown
		test.log(Status.INFO, "starting the testcase in next module");
		Assert.assertSame(customerlogin, customerlogin);
		driver.findElement(customerlogin).click();
		test.pass("It successfully finding the elements");
		WebElement drop1 = driver.findElement(By.name("userSelect"));
		Select by = new Select(drop1);
		by.selectByVisibleText("Hermoine Granger");
		test.pass("Customer has login successfully");
		// we are finding the login and clicking on it
		driver.findElement(loginbtn3).click();

		extent.flush();
	}

	public void deposit(String depositcheck) {
		// Here we are navigated to despoit module to deposit some amount
		// And adding Extent Reports
		reporter = new ExtentSparkReporter(".\\Data\\reports\\report3.html");
		reporter.config().setDocumentTitle("Automatiom Reports");
		reporter.config().setReportName("XYZBank.org automation");
		reporter.config().setTheme(Theme.DARK);
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		test = extent.createTest("My Second test", "this is the test to validate search page of XYZ Bank.com");

		test.log(Status.INFO, "starting the testcase of deposit");
		// Here adding some amount and click on the submit button
		Assert.assertSame(deposit, deposit);
		driver.findElement(deposit).click();
		// Here we are entering the amount
		driver.findElement(depositamt).sendKeys(depositcheck);
		// And here we are clicking the submit
		driver.findElement(depositbtn).click();
		test.pass("Depoist was successfully done");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		// Here we using implicitly Waits
		String actualtext = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[4]/div/span")).getText();
		String expectedtext = "Deposit Successful";
		driver.navigate().refresh();
		// Here we are using the Assersions to campare the text after click on the
		// submit button
		Assert.assertEquals(actualtext, expectedtext);
		test.pass("Comparsion of actual message and expected message");
		extent.flush();
	}

	public void withdrawl(String withdrawlcheck) {
		// Here we are performing the withdrawl amount from account
		// And including Extent Reports to withdrawl module
		reporter = new ExtentSparkReporter(".\\Data\\reports\\report4.html");
		reporter.config().setDocumentTitle("Automatiom Reports");
		reporter.config().setReportName("XYZBank.org automation");
		reporter.config().setTheme(Theme.DARK);
		extent = new ExtentReports();
		extent.attachReporter(reporter);

		test = extent.createTest("My Third test", "this is the test to validate search page of XYZ Bank.com");
		test.log(Status.INFO, "starting the testcase of withdrawl");
		// And we are withdraw some amount from account
		Assert.assertSame(withdrawl, withdrawl);
		driver.findElement(withdrawl).click();
		// Here we are entering the amount
		driver.findElement(withdrawlamt).sendKeys(withdrawlcheck);
		// And here we are clicking the submit
		driver.findElement(withdrawlbtn).click();
		test.pass("Withdrawl was done successfully");

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		String actualtext = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[4]/div/span")).getText();
		String expectedtext = "Transaction successful";
		test.pass("The Transaction was successful");
		driver.navigate().refresh();
		Assert.assertEquals(actualtext, expectedtext);
		test.pass("All TestCases are passed");
		extent.flush();
	}

	public void transaction() throws IOException, InterruptedException {
		driver.navigate().refresh();
		Assert.assertSame(transaction, transaction);
		driver.findElement(transaction).click();
		// Taking screenshot of the transcations of particular customer
		Thread.sleep(20000);
		TakesScreenshot ts = (TakesScreenshot) driver;
		// Here we are using the Takes Screenshot Interface
		File screen = ts.getScreenshotAs(OutputType.FILE);
		File shot = new File(".\\screenshots\\transaction3.png");
		FileUtils.copyFile(screen, shot);
	}
}