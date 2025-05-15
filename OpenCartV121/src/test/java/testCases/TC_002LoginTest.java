package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import utilities.DataProviders;

public class TC_002LoginTest extends BaseClass {
	
	@Test (groups={"Sanity","Master"}) //master means all the test should be executed
	public void verify_login() 
	{
		logger.info("****Starting TC_002LoginTest******");
		//HomePage
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		//LoginPage
		LoginPage lp = new LoginPage(driver);
		lp.setEmail(p.getProperty("email"));
		lp.setPassword(p.getProperty("password"));
		lp.clickLogin();
		//My Account
		MyAccountPage macc = new MyAccountPage(driver);
		boolean targetPage = macc.isMyAccountPageExists(); //in the target page we will put the value which is true or false
		Assert.assertEquals(targetPage, true, "Login failed"); //If assert result true then ok but if fails then it will print login failed
		logger.info("****Finished TC_002LoginTest******");
	}

	
}
