package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage{

	public AccountRegistrationPage(WebDriver driver) {
		super(driver);
		
	}
	
	@FindBy(xpath="//input[@id='input-firstname']")
	WebElement txtFirstName;

	@FindBy(xpath="//input[@id='input-lastname']")
	WebElement txtLastname;
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement txtEmail;
	@FindBy(xpath="//input[@id='input-lastname']")
	WebElement txtTelephone;
	@FindBy(xpath="//input[@id='input-password']")
	WebElement txtPassword;
	@FindBy(xpath="//input[@id='input-lastname']")
	WebElement txtConfirmPassword;
	@FindBy(xpath="//input[@name='agree']")
	WebElement chkPolicy;
	@FindBy(xpath="//button[@type='submit']")
	WebElement btnContinue;

	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement msgConfirmation;
	
	
	
	public void setFirstName(String fname) {
		txtFirstName.sendKeys(fname);
	}
	
	public void setLastName(String lname) {
		txtLastname.sendKeys(lname);
	}
	public void setEmail(String email) {
		txtEmail.sendKeys(email);
	}
	public void setTelephone(String tel) {
		txtTelephone.sendKeys(tel);
	}
	
	public void setPassword(String pwd) {
		txtPassword.sendKeys(pwd);
	}
	public void setPrivacyPolicy() {
		chkPolicy.click();
	}
	public void clickContinue() {
		//method 1
		btnContinue.click();
		
		//method 2
		//btnContinue.submit();
		
		//method3
		//Actions act = new Actions(driver);
		//act.moveToElement(btnContinue).click().perform();
		
		//method 4
		//JavascriptExecuter js =(javascriptExecuter)driver;
		//js.executeScript("arguments[0].click();',btnContinue);
		
		//method
		//btnContinue.sendKeys(keys.RETURN);
		
		//method 6
		//WebDriverWait myWait = new WebDriverWait(driver,Duration.ofSeconds(10)0;
		//mywait.until(ExpectedConditions.elementtoBeClickable(btnContinue)).click();
		
	}
	
	public String getConfirmationMsg() {
		try {
			return (msgConfirmation.getText());
		}catch(Exception e) {
			return (e.getMessage());
		}
	}
}
