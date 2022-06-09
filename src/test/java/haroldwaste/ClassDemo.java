package haroldwaste;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ClassDemo {
	WebDriver	 driver;
	 String userName="user1demo@haroldwaste.com";
	 String password="12345678";
	 // change the General_info[1] for unique name
	 String General_info[]={"Abc123", "Legal entity name","Legal form", "Identification code","Location identification code", "Tax code","Registration code", "Industry code","License number", "Other ref 1","Other ref 2", "Email address","Phone Number", "Fax"};
	 String Billing_Financial[]= {"ADDRESS","Address","Address 2"," State/region","City"," Zip/Postal code", "Bank name","IBAN number","BIC number"};
	 String company_name = General_info[0];

	@BeforeTest
	public void tearUp()
	{
		WebDriverManager.chromedriver().setup();
			 driver= new ChromeDriver();
			 driver.manage().window().maximize();	
			 driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
			 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	 @BeforeClass
	public void login()
	{
			driver.get("https://staging.haroldwaste.com/supplier-board");
			driver.findElement(By.xpath("//input[@name='email']")).sendKeys(userName);
			driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
			
	}
	
	@Test(priority = 1)
	public void createSupplier() throws InterruptedException
	{
		driver.findElement(By.xpath("//span[@class='MuiButton-label']")).click();
		WebElement element1= driver.findElement(By.xpath("//div[@class='anchor'][1]"));
		
		Actions action= new Actions(driver);
		action.moveToElement(element1).build().perform();
		driver.findElement(By.linkText("Suppliers & Sites")).click();
		
		driver.findElement(By.xpath("//span[text()='Add a new supplier']")).click();
		List<WebElement> General_info_elements=driver.findElements(By.xpath("//input[@class='MuiInputBase-input MuiOutlinedInput-input MuiInputBase-inputMarginDense MuiOutlinedInput-inputMarginDense']"));
	
		Iterator<WebElement> i= General_info_elements.iterator();
		 int j=0;
		while(i.hasNext())
		{
		
			WebElement e=i.next();
			e.sendKeys(General_info[j]);
			j++;
		}
        Thread.sleep(3000);
		driver.findElement(By.xpath("//span[text()='Next'] ")).click();
		int a=0;
		List<WebElement> Billing_Financial_elements=driver.findElements(By.xpath("//input[@class='MuiInputBase-input MuiOutlinedInput-input MuiInputBase-inputMarginDense MuiOutlinedInput-inputMarginDense']"));
		Iterator<WebElement> i1= Billing_Financial_elements.iterator();

		while(i1.hasNext())
		{
		for( a=0; a<=8;a++) {
			WebElement e=i1.next();
			// System.out.println(e);
			e.sendKeys(Billing_Financial[a]);}
			a++;
		}
        Thread.sleep(3000);
		driver.findElement(By.cssSelector("button[data-tracker-id='Company']")).click();
	}
	
	@Test(priority = 2)
	public void editSupplier() throws InterruptedException
	{
		
		String xpath="//td[@value='"+company_name+"']//following-sibling::td[4]/div[1]/div[1]/button";
		driver.findElement(By.xpath(xpath)).click();
		
		WebElement Legal_form= driver.findElement(By.cssSelector("input[value='Legal form']"));
		Legal_form.clear();
		Legal_form.sendKeys("Legal Form is edited");
		
		WebElement Identification_code= driver.findElement(By.cssSelector("input[value='Identification code']"));
		Identification_code.clear();
		Identification_code.sendKeys("Identification code is edited");
		
		WebElement Location_identification= driver.findElement(By.cssSelector("input[value='Location identification code']"));
	    Location_identification.clear();
	    Location_identification.sendKeys("Location identification code is edited");
        Thread.sleep(3000);
		driver.findElement(By.xpath("//span[text()='Next'] ")).click();
		driver.findElement(By.cssSelector("button[data-tracker-id='Company']")).click();
        Thread.sleep(3000);
	}
	
	@Test(priority = 3)
	public void blockSupplier() throws InterruptedException
	{ 
		driver.findElement(By.xpath("//td[@value='"+company_name+"'] //following-sibling::td[4]/div[1]/div[2]/button")).click();
        Thread.sleep(3000);
		driver.findElement(By.xpath("//div[@class='MuiPaper-root MuiDialog-paper MuiDialog-paperScrollPaper MuiDialog-paperWidthSm MuiPaper-elevation24 MuiPaper-rounded']/div[1]/div[3]/button[2]")).click();
		
	}
	@AfterTest
	public void tearDown() throws InterruptedException
	{
		Thread.sleep(5000);
		driver.close();
	}
	
}
