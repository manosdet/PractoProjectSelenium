package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.HomePage;
import pageObject.LoginPage;
import pageObject.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass{

	@Test(groups={"Master","Sanity"})
	public void verify_login() {
		
		logger.info("****** Starting TC002****");
		//HomePage
		try {
		HomePage hp = new HomePage(driver);
		//hp.clickMyAccount();
		hp.clickLoginBtn();
		//LoginPage
		LoginPage lp =new LoginPage(driver);
		lp.setEmail(p.getProperty("email"));
		System.out.println(driver.getTitle());
		Assert.assertEquals("Practo Accounts", driver.getTitle());
		//lp.setPassword(p.getProperty("password"));
		//lp.clickLogin();
		
		//My AccountPage
	//	MyAccountPage macc=new MyAccountPage(driver);
		//boolean targetPage =macc.isMyAccountPageExists();
		
		//Assert.assertTrue(targetPage);//Assert.assertEquals(targetPage , true,"Login failed");
		}catch(Exception e){
			Assert.fail();
		}
		logger.info("*****Finished TC002_LoginTest*****");
	}
}
