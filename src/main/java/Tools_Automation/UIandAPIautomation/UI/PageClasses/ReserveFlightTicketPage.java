package Tools_Automation.UIandAPIautomation.UI.PageClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class ReserveFlightTicketPage {
	
	WebDriver driver;

	@FindBy(xpath="/html/body/div[2]/h2")
	public WebElement headline;
	
	@FindBy(className="btn-primary")
	public WebElement purchaseflight;
	
	@FindBy(id="inputName")
	public WebElement name;
	
	@FindBy(id="address")
	public WebElement address;
	
	@FindBy(id="city")
	public WebElement city;
	
	@FindBy(id="state")
	public WebElement state;
	
	@FindBy(id="zipCode")
	public WebElement zipcode;
	
	@FindBy(id="cardType")
	public WebElement cardtype;
	
	@FindBy(id="creditCardNumber")
	public WebElement creditcardnumber;
	
	@FindBy(id="creditCardMonth")
	public WebElement creditcardmonth;
	
	@FindBy(id="creditCardYear")
	public WebElement creditcardyear;
	
	@FindBy(id="nameOnCard")
	public WebElement nameoncard;
	
	@FindBy(id="rememberMe")
	public WebElement rememberme;
	
	public ReserveFlightTicketPage(WebDriver driver)
	{
		this.driver=driver;
	    PageFactory.initElements(driver, this);
	}
	
	public void verifypage()
	{
		String verifyText="Your flight from TLV to SFO has been reserved.";
		System.out.println("verify page "+headline.getText());
		Assert.assertEquals(headline.getText(), verifyText);
	}
	
	public void reserveticket(String uname,String uaddress,String ucity,String ustate,String zp,String ctype,String ccnum,String ccmonth,String ccyear,String urememberme)
	{
		name.sendKeys(uname);
		address.sendKeys(uaddress);
		city.sendKeys(ucity);
		state.sendKeys(ustate);
		zipcode.sendKeys(zp);
		Select cctype=new Select(cardtype);
		cctype.selectByValue(ctype);
		creditcardnumber.sendKeys(ccnum);
		creditcardmonth.sendKeys(ccmonth);
		creditcardyear.sendKeys(ccyear);
		nameoncard.sendKeys(uname);
		if(urememberme.equals("Yes"))
		{
			rememberme.click();
		}
		purchaseflight.click();
	}

}
