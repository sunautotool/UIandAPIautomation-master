package Tools_Automation.UIandAPIautomation.UI.PageClasses;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class FlightConfirmationPage {
	
	WebDriver driver;

	@FindBy(xpath="/html/body/div[2]/div/h1")
	public WebElement headline;
	
	@FindBy(xpath="//table/tbody/tr[1]/td[2]")
	public WebElement confirmationid;
	
	public FlightConfirmationPage(WebDriver driver)
	{
		this.driver=driver;
	    PageFactory.initElements(driver, this);
	}
	
	public void verifypage()
	{
		String verifyText="Thank you for your purchase today!";
		System.out.println("verify page "+headline.getText());
		Assert.assertEquals(headline.getText(), verifyText);
	}

}
