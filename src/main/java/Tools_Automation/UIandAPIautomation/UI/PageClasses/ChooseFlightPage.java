package Tools_Automation.UIandAPIautomation.UI.PageClasses;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.apache.poi.ss.formula.functions.FactDouble;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class ChooseFlightPage {
	
	WebDriver driver;
	
	@FindBy(xpath="//table/tbody/tr")
	public List<WebElement> tablerows;
	
	@FindBy(xpath="/html/body/div[2]/h3")
	public WebElement headline;
	
	public ChooseFlightPage(WebDriver driver)
	{
		this.driver=driver;
	    PageFactory.initElements(driver, this);
	}
	
	public void verifypage(String departurecity,String destinationcity)
	{
		String verifyText="Flights from "+departurecity+" to "+destinationcity+":";
		System.out.println("verify page "+headline.getText());
		Assert.assertEquals(headline.getText(), verifyText);
	}
	
	public void chooseFlightByLowestPrice() 
	{
		System.out.println("Rows are"+tablerows.size());
		HashMap<Integer,Integer> pricelist=new HashMap<Integer,Integer>();
		int count=1;
		for(WebElement rows:tablerows)
		{
			System.out.println("Row details are "+rows.getText());
			String rowsText=rows.getText();
			String price=rowsText.substring(rowsText.lastIndexOf("$")+1);
			String pricevalue=String.valueOf(Math.round(Double.valueOf(price)));
			pricelist.put(count,Integer.parseInt(pricevalue));
			count++;
		}
		int minvalue=Collections.min(pricelist.values());
		for(Entry<Integer, Integer> en:pricelist.entrySet())
		{
			if(en.getValue().equals(minvalue))
			{
				String chooseflightxpath="//table/tbody/tr["+en.getKey()+"]/td[1]";
				System.out.println("Xpath is"+chooseflightxpath);
				driver.findElement(By.xpath(chooseflightxpath)).click();
			}
		}
		
	}
	
	
	public void chooseFlightByHighestPrice() 
	{
		System.out.println("Rows are"+tablerows.size());
		HashMap<Integer,Integer> pricelist=new HashMap<Integer,Integer>();
		int count=1;
		for(WebElement rows:tablerows)
		{
			System.out.println("Row details are "+rows.getText());
			String rowsText=rows.getText();
			String price=rowsText.substring(rowsText.lastIndexOf("$")+1);
			String pricevalue=String.valueOf(Math.round(Double.valueOf(price)));
			pricelist.put(count,Integer.parseInt(pricevalue));
			count++;
		}
		int minvalue=Collections.max(pricelist.values());
		for(Entry<Integer, Integer> en:pricelist.entrySet())
		{
			if(en.getValue().equals(minvalue))
			{
				String chooseflightxpath="//table/tbody/tr["+en.getKey()+"]/td[1]";
				System.out.println("Xpath is"+chooseflightxpath);
				driver.findElement(By.xpath(chooseflightxpath)).click();
			}
		}
		
	}
	
	public void chooseFlightByAirline(String airline) 
	{
		System.out.println("Rows are"+tablerows.size());
		int count=1;
		for(WebElement rows:tablerows)
		{
			System.out.println("Row details are "+rows.getText());
			String rowsText=rows.getText();
			if(rowsText.contains(airline))
			{
				String chooseflightxpath="//table/tbody/tr["+count+"]/td[1]";
				System.out.println("Xpath is"+chooseflightxpath);
				driver.findElement(By.xpath(chooseflightxpath)).click();
				break;
			}
			count++;
		}
	}
	
	public void chooseFlightByLessTime() 
	{
		System.out.println("Rows are"+tablerows.size());
		HashMap<Integer,Double> pricelist=new HashMap<Integer,Double>();
		int count=1;
		for(WebElement rows:tablerows)
		{
			System.out.println("Row details are "+rows.getText());
			String depttime="//table/tbody/tr["+count+"]/td[4]";
			String arrivaltime="//table/tbody/tr["+count+"]/td[5]";
			Date d1 = null;
		    Date d2 = null;
			WebElement dpttime=driver.findElement(By.xpath(depttime));
			WebElement arrltime=driver.findElement(By.xpath(arrivaltime));
			String[] dptime=dpttime.getText().split("AM");
			String[] altime=arrltime.getText().split("PM");
			System.out.println("De "+dptime[0]);
			System.out.println("De "+altime[0]);
			SimpleDateFormat format = new SimpleDateFormat("HH:mm");
			try {
		        d1 = format.parse(dptime[0]);
		        d2 = format.parse(altime[0]);
		    } catch (ParseException e) {
		        e.printStackTrace();
		    }
			long travelduration = d2.getTime() - d1.getTime();
			System.out.println("De "+travelduration);
			pricelist.put(count,(double) travelduration);
			count++;
		}
		Double minvalue=Collections.min(pricelist.values());
		for(Entry<Integer, Double> en:pricelist.entrySet())
		{
			if(en.getValue().equals(minvalue))
			{
				String chooseflightxpath="//table/tbody/tr["+en.getKey()+"]/td[1]";
				System.out.println("Xpath is"+chooseflightxpath);
				driver.findElement(By.xpath(chooseflightxpath)).click();
			}
		}
		
	}
	

}
