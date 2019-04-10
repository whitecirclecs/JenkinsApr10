package com.whitecircleschool.JenkinsDemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MultipleValidations {

	WebDriver driver;

	@BeforeTest
	public void openBrowser() {
		// 1) Open the browser
		System.setProperty("webdriver.chrome.driver", "chromedriver");
		driver = new ChromeDriver();

		// 2) Maximize the browser
		driver.manage().window().maximize();

		// 3) Navigate to the application
		driver.get("https://www.linkedin.com/");
	}

	// 5) Sign in button must be disabled before entering username and password
	@Test(priority = 1)
	public void verifySignBtnBeforeText() {
		boolean signInBtn = driver.findElement(By.id("login-submit")).isEnabled();
		Assert.assertFalse(signInBtn);
	}

	// 6) Sign in button must be enabled after entering username and password
	@Test(priority = 2, enabled = false)
	public void verifySignBtnAfterText() {
		driver.findElement(By.id("login-email")).sendKeys("abc@gmail.com");
		driver.findElement(By.id("login-password")).sendKeys("password");
		boolean signInBtn = driver.findElement(By.id("login-submit")).isEnabled();
		Assert.assertTrue(signInBtn);
	}

	// 7) Validate 'Be Great at what you do' is visible or not
	@Test(priority = 3)
	public void verifyText() {
		String expectedText = "Be great at what you do";
		String actualText = driver.findElement(By.cssSelector("#regForm > h2")).getText();
		Assert.assertEquals(actualText, expectedText);
	}

	// 8) Validate if 'Search' button is visible or not
	@Test(priority = 4)
	public void verifySearchBtn() {
		boolean searchBtn = driver.findElement(By.name("search")).isDisplayed();
		Assert.assertTrue(searchBtn);
	}

	@AfterTest
	public void closeBrowser() {
		driver.close()
	}

}
