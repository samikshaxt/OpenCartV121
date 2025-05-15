//This class contains reusable methods and classes and this is the common class which every class will use 
package testCases;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class BaseClass {

	public static WebDriver driver;
	public Logger logger;
	public Properties p;
	@BeforeClass(groups= {"Sanity","Regression","Master"}) //master is the final group which contains all the tests
	@Parameters({"os", "browser"})
	public void setup(String os , String br) throws IOException
	{
		//Loading config.properties file , for this we need to use a special class that is public  properties class and its object 
		p = new Properties();
		FileReader file = new FileReader("./src//test//resources//config.properties"); // ./represents current project location
		//now we have load this file for that we need to first create the object of the Property class and using that we will load
		p.load(file);
		
		logger = LogManager.getLogger(this.getClass());
		switch(br.toLowerCase())
		{
		case "chrome" : driver=new ChromeDriver(); break;
		case "firefox" : driver=new FirefoxDriver(); break;
		case "edge" : driver=new EdgeDriver(); break;
		default : System.out.println("Invalid Browser Name..");return; //return will totally exit from execution
		}
		
		
		
		
//The keyword this refers to the current object. So this.getClass() returns the runtime class of the current object.
//You want to get a logger that's named after the current class, so that your logs clearly indicate which class the log messages are coming from.
//logger variable is used to generate the logs	
		
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(p.getProperty("URL")); //reading URL from properties file
		driver.manage().window().maximize();
		}
	
	@AfterClass (groups= {"Sanity","Regression","Master"}) 
	public void tearDown()
	{
		driver.quit();
	}
		public String randomeString() //only rando string method will generated only small letters not the capital letters 
		//so in order to use in first and last name we need to convert it's case
		//this method is for string , we can also create for numbers as well
		{
			String generatedString = RandomStringUtils.randomAlphabetic(5);
			return generatedString;
		}
		public String randomeNumber() 
		//this method is for string , we can also create for numbers as well
		{
			String generatedNumber= RandomStringUtils.randomNumeric(10);
			return generatedNumber;
		}
		public String randomeAlphaNumberic() 
		//this method is for string , we can also create for numbers as well
		{
			String generatedString = RandomStringUtils.randomAlphabetic(5);
			String generatedNumber= RandomStringUtils.randomNumeric(10);
			
			return (generatedString + generatedNumber);
		}
			public String captureScreen(String tname) throws IOException {

				String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
						
				TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
				File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
				
				String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";
				File targetFile=new File(targetFilePath);
				
				sourceFile.renameTo(targetFile);
					
				return targetFilePath; //if we do not return this it will not be the part of report 
		}
	
	
}
