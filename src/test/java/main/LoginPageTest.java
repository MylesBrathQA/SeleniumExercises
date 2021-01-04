package main;

import static chromeoptions.SeleniumChromeCookies.chromeCfg;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class LoginPageTest {

	private static ExtentReports extent;
	private static ExtentTest test;
	private static int count = 0;
	private WebDriver driver;
	private String URL = "http://thedemosite.co.uk/";

	@BeforeClass
	public static void init() {
		extent = new ExtentReports("src/test/resources/reports/report.html", true);

	}

	@Before
	public void setup() {
		test = extent.startTest("TheDemoSite: Test Number " + ++count);
		System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver_win32/chromedriver.exe");
		driver = new ChromeDriver(chromeCfg());
		driver.manage().window().setSize(new Dimension(1366, 768));
	}

	@After
	public void tearDown() {
		driver.quit();
		extent.endTest(test);
	}

	@AfterClass
	public static void fini() {
		extent.flush();
		extent.close();
	}

	@Test
	public void testPageLoaded() throws InterruptedException {
		driver.get(URL);
		if (driver.getCurrentUrl().equals(URL)) {
			test.log(LogStatus.PASS, "Passed");
			assertTrue(true);
		} else {
			test.log(LogStatus.FAIL, "Failed");
			assertTrue(false);
		}
	}

	@Test
	public void testLoginPageLoaded() {
		driver.get(URL);
		WebElement link = driver.findElement(By.cssSelector(
				"body > div > center > table > tbody > tr:nth-child(2) > td > div > center > table > tbody > tr > td:nth-child(2) > p > small > a:nth-child(7)"));
		link.click();
		System.out.println(driver.getCurrentUrl());
		if (driver.getCurrentUrl().equals("http://thedemosite.co.uk/login.php")) {
			test.log(LogStatus.PASS, "Passed");
			assertTrue(true);
		} else {
			test.log(LogStatus.FAIL, "Failed");
			assertTrue(false);
		}
	}

	@Test
	public void testLogin() {
		driver.get(URL + "/login.php");
		WebElement username = driver.findElement(By.name("username"));
		WebElement password = driver.findElement(By.name("password"));
		username.sendKeys("fdgghgfhgf");
		password.sendKeys("dfgdfg");
		WebElement submit = driver.findElement(By.name("FormsButton2"));
		submit.click();
		WebElement flag = driver.findElement(By.cssSelector(
				"body > table > tbody > tr > td.auto-style1 > big > blockquote > blockquote > font > center > b"));
		System.out.println(flag.getText());
		if (flag.getText().equals("**Successful Login**")) {
			test.log(LogStatus.PASS, "Passed");
			assertTrue(true);
		} else {
			test.log(LogStatus.FAIL, "Failed");
			assertTrue(false);
		}

	}

}
