package testCases;

import java.time.Duration;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;

public class TC_001AccountRegistrationTest extends BaseClass {
	
	//public WebDriver driver; this will be remove as we already inheriting the driver from Base class
	
	
	@Test(groups={"Regression","Master"})
	public void verify_account_registration()
	{
		logger.info("****** Starting TC_001AccountRegistrationTest *********");
		try
		{
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Clicked on my Account Link");
		hp.clickRegister();
		logger.info("Clicked on my Register Link");
		AccountRegistrationPage repage = new AccountRegistrationPage(driver);
		logger.info("Providing Customer details");
		//Here we have hardcoded the values which is not practical though next time it will show account already exists
		//We need to generate the random data
		//repage.setFirstName("Samiksha");
		repage.setFirstName(randomeString().toUpperCase());
		//repage.setLastName("Tiwari");
		repage.setLastName(randomeString().toUpperCase());
		//repage.setEmail("samiksha12@gmail.com");
		repage.setEmail(randomeString() + "@gmail.com");
		//repage.setTelephone("76587698");
		repage.setTelephone(randomeNumber());
		//repage.setPassword("sakshi123"); //for password we need alhpanumeric for that also we will create an another method
		String password = randomeAlphaNumberic();
		repage.setPassword(password);
		//repage.setConfirmPassword("sakshi123");
		repage.setConfirmPassword(password); //if we will two times call this method both the time it will generate other password which is incorrect
		repage.setPrivacyPolicy();
		repage.clickContinue();
		logger.info("Validating expected message..");
		String confmsg = repage.getConfirmationMsg();
		Assert.assertEquals(confmsg, "Your Account Has Been Created!");
		}
		catch(Exception e) //if the test gets fail we will capture the error and debug logs both and also use assert fail
		{
			logger.error("Test Failed");
			logger.debug("Debug Logs..");
			Assert.fail();
			logger.info("Finished ");
			
		}
		
	}
	

	}


