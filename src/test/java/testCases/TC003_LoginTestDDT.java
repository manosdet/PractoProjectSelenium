package testCases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.BasePage;
import pageObject.HomePage;
import pageObject.LoginPage;
import pageObject.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

/* Data is valid -login success-test pass-logout
 * Data is valid --login failed -test fail
 * 
 * Data is invalid -login success -test fail -logout
 * Data is invalid --login failed -test fail
 */
public class TC003_LoginTestDDT extends BaseClass {

	@Test(dataProvider="LoginData",dataProviderClass=DataProviders.class ,groups="DataDriven")//gating data provider from diffrent classs 
	public void verify_loginDDT(String email,String pwd ,String exp) {
		logger.info("*****Starting TC_LoginDDT****");

		try {
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			hp.clickLogin();
			//LoginPage
			LoginPage lp =new LoginPage(driver);
			lp.setEmail(p.getProperty("email"));
			lp.setPassword(p.getProperty("password"));
			lp.clickLogin();

			//My AccountPage
			MyAccountPage macc=new MyAccountPage(driver);
			boolean targetPage =macc.isMyAccountPageExists();

			if(exp.equalsIgnoreCase("Valid")) {
				if(targetPage==true) {

					macc.clickLogout();
					Assert.assertTrue(true);
				}else
				{
					Assert.assertTrue(false);
				}
			}
			if(exp.equalsIgnoreCase("Inalid")) {
				if(targetPage==true) {

					macc.clickLogout();
					Assert.assertTrue(true);
				}else
				{
					Assert.assertTrue(false);
				}
			}

		}catch(Exception e){
			Assert.fail();
		}
		logger.info("*****Endinging TC_LoginDDT****");
	}
}
