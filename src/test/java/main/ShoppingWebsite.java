package main;

import static chromeoptions.SeleniumChromeCookies.chromeCfg;
import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ShoppingWebsite {
	private static ExtentReports extent;
	private static ExtentTest test;
	private static int count = 0;
	private WebDriver driver;
	private String URL = "http://automationpractice.com/index.php";

	@BeforeClass
	public static void init() {
		extent = new ExtentReports("src/test/resources/reports/ShoppingWebsite.html", true);

	}

	@Before
	public void setup() {
		test = extent.startTest("Shopping Website: Test Number " + ++count);
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
	@Ignore
	public void testDressSearch() {
		driver.get(URL);
		WebElement searchBar = driver.findElement(By.xpath("//input[@id= 'search_query_top']"));
		searchBar.sendKeys("Dress");
		searchBar.submit();
		new WebDriverWait(driver, 3)
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='product-count']")));
		WebElement view = driver.findElement(By.xpath("//i[@class='icon-th-list']"));
		view.click();
		WebElement targetDress = driver.findElement(By.xpath("//a[@title = 'Printed Summer Dress']"));
		targetDress.click();
		WebElement actual = driver.findElement(By.xpath("//h1[@itemprop = 'name']"));
		if (actual.getText().equals("Printed Summer Dress")) {
			test.log(LogStatus.PASS, "Passed");
			assertEquals("Printed Summer Dress", actual.getText());
		} else {
			test.log(LogStatus.FAIL, "Failed");
			assertEquals("Printed Summer Dress", actual.getText());
		}

	}

	@Test
	public void testCheckOut() {
		String email = "cijas44263@28woman.com";
		String password = "WYk5CvQ@wYdKy@4";
		driver.get(URL);
		WebElement searchBar = driver.findElement(By.xpath("//input[@id= 'search_query_top']"));
		searchBar.sendKeys("Dress");
		searchBar.submit();
		new WebDriverWait(driver, 3)
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='product-count']")));
		WebElement view = driver.findElement(By.xpath("//i[@class='icon-th-list']"));
		view.click();
		WebElement targetDress = driver.findElement(By.xpath("//a[@title = 'Printed Summer Dress']"));
		targetDress.click();
		WebElement addToCart = driver.findElement(By.xpath("//button[@name = 'Submit']"));
		addToCart.click();
		new WebDriverWait(driver, 3)
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@title= 'Proceed to checkout']")));
		WebElement checkout = driver.findElement(By.xpath("//*[contains(text(), 'Proceed to checkout')]"));
		Actions action = new Actions(driver);
		action.moveToElement(checkout).click();
		new WebDriverWait(driver, 3)
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@title= 'Proceed to checkout']")));
		action.moveToElement(checkout).click();
		WebElement termsAndServices = driver.findElement(By.xpath("//*[@id=\"uniform-cgv\"]"));
		termsAndServices.click();

	}
}
