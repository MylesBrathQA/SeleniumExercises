package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage {
	public static final String URL = "https://www.youidraw.com/apps/painter/";

	@FindBy(css = "#brush")
	private WebElement brush;

	public void clickBrush() {
		brush.click();
	}

}
