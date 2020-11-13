package Tools_Automation.UIandAPIautomation.UI;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Tools_Automation.UIandAPIautomation.UI.Configuration.SelectBrowserConfig;
import Tools_Automation.UIandAPIautomation.UI.InputDataProvider.InputDataProviderFromExcel;
import Tools_Automation.UIandAPIautomation.UI.PageClasses.ChooseFlightPage;
import Tools_Automation.UIandAPIautomation.UI.PageClasses.FindFlightPage;
import Tools_Automation.UIandAPIautomation.UI.PageClasses.FlightConfirmationPage;
import Tools_Automation.UIandAPIautomation.UI.PageClasses.ReserveFlightTicketPage;

public class BookAFlight extends SelectBrowserConfig {
	
	public static WebDriver driver=null;
	public static FindFlightPage findflightpage=null;
	public static ChooseFlightPage chooseflightpage=null;
	public static ReserveFlightTicketPage reserveflight=null;
	public static FlightConfirmationPage confirmation=null;
	InputDataProviderFromExcel inputdata =new InputDataProviderFromExcel();
	
	public static void init()
	{
		findflightpage=new FindFlightPage(driver);
		chooseflightpage=new ChooseFlightPage(driver);
		reserveflight=new ReserveFlightTicketPage(driver);
		confirmation=new FlightConfirmationPage(driver);
	}
	
	
	@Parameters({"selenium.browser","selenium.url"})
	@BeforeMethod()
	public void lauchbrowser(String browser,String url) 
	{
		driver=this.getBrowserName(browser);
		BookAFlight.init();
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);	
	}
	
	@DataProvider(name="inputdata")
    public Object[][] data() throws IOException {
		String inputData=System.getProperty("user.dir")+"\\inputData.xlsx";
	    FileInputStream fis = new FileInputStream(inputData);
	    inputdata.loadFromSpreadsheet(fis);
	    return inputdata.getData();    
   }
	
	
	@Test(description="Test cases to book a flight ticket by lowest,highest price and by airline",dataProvider = "inputdata")
	public void flightbooktestcases(String departurecity,String destinationcity,String chooseflightby,String airlinename,String uname,String uaddress,String ucity,String ustate,String zip,String cardtype,String cardnum,String ccmonth,String ccyear,String urememberme) 
	{
		Assert.assertNotNull(findflightpage.findflights);
		findflightpage.findflights(departurecity, destinationcity);
		chooseflightpage.verifypage(departurecity, destinationcity);
		if(chooseflightby.equals("lowestprice"))
		{
			chooseflightpage.chooseFlightByLowestPrice();
		}
		else if(chooseflightby.equals("highestprice"))
		{
			chooseflightpage.chooseFlightByHighestPrice();
		}
		else if(chooseflightby.equals("airline"))
		{
			chooseflightpage.chooseFlightByAirline(airlinename);
		}
		else if(chooseflightby.equals("bylesstime"))
		{
			chooseflightpage.chooseFlightByLessTime();
		}
		reserveflight.verifypage();
		reserveflight.reserveticket(uname, uaddress, ucity, ustate, zip, cardtype, cardnum, ccmonth, ccyear, urememberme);
		Assert.assertNotNull(confirmation.headline);
		confirmation.verifypage();
		Assert.assertNotNull(confirmation.confirmationid);
	}
	
	@AfterMethod
	public void teardown()
	{
		driver.quit();
	}
}
