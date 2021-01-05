package main;

import static chromeoptions.SeleniumChromeCookies.chromeCfg;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;

import pages.HomePage;

public class IDrawTest {
	private static RemoteWebDriver driver;
	Actions action = new Actions(driver);
	HomePage nav = PageFactory.initElements(driver, HomePage.class);

	@BeforeClass
	public static void init() {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver_win32/chromedriver.exe");
		driver = new ChromeDriver(chromeCfg());
		driver.manage().window().setSize(new Dimension(1366, 768));
	}

	@Before
	public void setup() {
		driver.get(nav.URL);
	}

	@AfterClass
	public static void tearDown() {
		driver.quit();
	}

	@Test
	public void drawInitial() throws InterruptedException {
		WebElement canvas = driver.findElement(By.cssSelector("#catch"));
		nav.clickBrush();
		action.moveToElement(canvas).moveByOffset(-250, -50).clickAndHold().moveByOffset(0, -200).moveByOffset(50, 125)
				.moveByOffset(50, -125).moveByOffset(0, 200).release().moveByOffset(100, 0).clickAndHold()
				.moveByOffset(0, 200).moveByOffset(100, -50).moveByOffset(-100, -50).moveByOffset(100, -50)
				.moveByOffset(-100, -50).build().perform();
		Thread.sleep(4000);
	}
}
