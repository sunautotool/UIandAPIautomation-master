package Tools_Automation.UIandAPIautomation.UI.Configuration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.logging.Level; 
import java.util.logging.Logger;

public class SelectBrowserConfig {
	private final static Logger LOGGER =  Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	public static WebDriver driver=null;
	public WebDriver getBrowserName(String browsername)
	{
		if(browsername.equals("chrome")) {
			LOGGER.log(Level.INFO, "Browser Got selected");
			LOGGER.log(Level.INFO, System.getProperty("user.dir"));
			System.out.println(" Current Path=="+System.getProperty("user.dir"));
		    System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\chromedriver.exe");
			driver= new ChromeDriver();
		}
		else if(browsername.equals("firefox"))
		{
			driver= new FirefoxDriver();
		}
		else if(browsername.equals("ie"))
		{
			System.out.println(" Current Path=="+System.getProperty("user.dir"));
		    System.setProperty("webdriver.ie.driver",System.getProperty("user.dir")+"\\IEDriverServer.exe");
			driver= new InternetExplorerDriver();
		}
		return driver;
		
	}

}
