package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import utilities.DataProviders;

public class TC_003LoginDDT extends BaseClass {
	
	@Test(dataProvider="LoginData",dataProviderClass=DataProviders.class,groups="datadriven") //this means the class name of Data Provider .class is the class name
	//getting data provider from different class
	public void verify_login(String email , String pwd , String exp) //the testdata will be stored in these three variables
	{
		logger.info("****Starting TC_003LoginTestDDT******");
		try 
		{
		//HomePage
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		//LoginPage
		LoginPage lp = new LoginPage(driver);
		lp.setEmail(email);
		lp.setPassword(pwd);
		lp.clickLogin();
		//My Account
		MyAccountPage macc = new MyAccountPage(driver);
		boolean targetPage = macc.isMyAccountPageExists(); //in the target page we will put the value which is true or false
		
		/*Data is valid - 		login success - test pass - logout
		 				  		login failed - test fail
			Data is invalid - 	login success - test fail 
		 					  	login failed - test pass
		 */
		if(exp.equalsIgnoreCase("Valid"))//for the safer side we habe used equals ignore case in case we use in excel
		{
			if(targetPage==true)
			{
				
				macc.clickLogout();
				Assert.assertTrue(true);
			}
			else
			{
				Assert.assertTrue(false); //Test if failed cursor remains in the same page
			}
		}
		if(exp.equalsIgnoreCase("Invalid"))//when test is pass then assert.assert will true else will be false
		{
			if(targetPage==true)
			{
				
				macc.clickLogout();
				Assert.assertTrue(false);
			}
			else
			{
				Assert.assertTrue(true);
			}
		}
		}
		catch(Exception e)
		{
			Assert.fail("An exception occurred: " + e.getMessage());
		}
		logger.info("****Finished TC_003LoginTestDDT******");
	}

	
}
