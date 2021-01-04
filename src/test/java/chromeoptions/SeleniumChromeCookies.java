package chromeoptions;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class SeleniumChromeCookies {

// Designed to return ChromeOptions to configure new ChromeDrivers in Selenium
	public static ChromeOptions chromeCfg() {
		Map<String, Object> prefs = new HashMap<String, Object>();
		ChromeOptions cOptions = new ChromeOptions();

		// Setting
		prefs.put("profile.default_content_setting_values.cookies", 2);
		prefs.put("network.cookie.cookieBehavior", 2);
		prefs.put("profile.block_third_party_cookies", true);

		// Create ChromeOptions to disable Cookies pop-up
		cOptions.setExperimentalOption("prefs", prefs);

		return cOptions;
	}

// Designed to return FirefoxOptions to configure new FirefoxDriver in Selenium
	public static FirefoxOptions firefoxCfg() {
		FirefoxOptions cOptions = new FirefoxOptions();
		cOptions.setHeadless(true);
		cOptions.addPreference("profile.default_content_setting_values.cookies", 2);
		cOptions.addPreference("network.cookie.cookieBehavior", 2);
		cOptions.addPreference("profile.block_third_party_cookies", true);

		return cOptions;
	}
}