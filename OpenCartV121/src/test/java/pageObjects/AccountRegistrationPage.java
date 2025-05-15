package pageObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage{

	public AccountRegistrationPage(WebDriver driver) 
	{
		super(driver);
		
	}

	@FindBy(xpath="(//input[@id='input-firstname'])")
	WebElement FirstName;
	@FindBy(xpath="(//input[@id='input-lastname'])")
	WebElement LastName;
	@FindBy(xpath="(//input[@id='input-email'])")
	WebElement Email;
	@FindBy(xpath="(//input[@id='input-telephone'])")
	WebElement Telephone;
	@FindBy(xpath="(//input[@id='input-password'])")
	WebElement Password;
	@FindBy(xpath="(//input[@id='input-confirm'])")
	WebElement ConfirmPassword;
	@FindBy(xpath="//input[@name='agree']")
	WebElement chkpolicy;
	@FindBy(xpath="//input[@value='Continue']")
	WebElement ContinueBtn;
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement MessageConfirmation;
	
	public void setFirstName(String fname)
	{
		FirstName.sendKeys(fname);	
	}
	public void setLastName(String lname)
	{
		LastName.sendKeys(lname);	
	}
	public void setEmail(String email)
	{
		Email.sendKeys(email);	
	}
	public void setTelephone(String tel)
	{
		Telephone.sendKeys(tel);	
	}
	public void setPassword(String pwd)
	{
		Password.sendKeys(pwd);
	}
	
	public void setConfirmPassword(String pwd)
	{
		ConfirmPassword.sendKeys(pwd);	
	}
	public void setPrivacyPolicy()
	{
		chkpolicy.click();	
	}
	public void clickContinue()
	{
		ContinueBtn.click();	
	}
	public String getConfirmationMsg()
	{
		try {
			return (MessageConfirmation.getText());
		}
		catch (Exception e)
		{
			return(e.getMessage());
		}
	}
	
	}


