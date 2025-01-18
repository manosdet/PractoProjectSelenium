package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.AccountRegistrationPage;
import pageObject.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {

	@Test(groups={"Regression","Master"})
	 public void verify_account_registration() throws InterruptedException{
		
		logger.info("** Starting TC001_AccountRegistrationTest**");
		try{
		HomePage hp= new HomePage(driver);
		
		Thread.sleep(5000);
		hp.clickMyAccount();
		Thread.sleep(3000);
		hp.clickRegister();
		
		AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
		regpage.setFirstName(randomString().toUpperCase());
		regpage.setLastName(randomString().toUpperCase());
		regpage.setEmail(randomString()+"@gmail.com");
		regpage.setTelephone("123123123");
		
		regpage.setPassword("xuyz@123");
		regpage.setPrivacyPolicy();
		regpage.clickContinue();
		
		String confmsg = regpage.getConfirmationMsg();
		
		Assert.assertEquals(confmsg, "Your Account Has Been Created!");
		
	}catch(Exception e) {
		logger.error("Test Failed");
	//	logger.debug("Debug logs");
		Assert.fail();
	}
		logger.info("** Ending TC001_AccountRegistrationTest**");
	} 
	
	 
}
