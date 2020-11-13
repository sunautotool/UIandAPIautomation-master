package Tools_Automation.UIandAPIautomation.UI.PageClasses;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FindFlightPage {
	
	WebDriver driver;

	@FindBy(name="fromPort")
	public WebElement departuercity;
	
	@FindBy(name="toPort")
	public WebElement destinationcity;
	
	@FindBy(className="btn-primary")
	public WebElement findflights;
	
	public FindFlightPage(WebDriver driver)
	{
		this.driver=driver;
	    PageFactory.initElements(driver, this);
	}
	
	public void selectdeparturecity(String city)
	{
		Select deptcity=new Select(departuercity);
		deptcity.selectByValue(city);
	}
	
	public void selectdestinationcity(String city)
	{
		Select destcity=new Select(destinationcity);
		destcity.selectByValue(city);
	}
	
	public void findflights(String depart,String dest)
	{
		selectdeparturecity(depart);
		selectdestinationcity(dest);
		findflights.click();		
	}

}
