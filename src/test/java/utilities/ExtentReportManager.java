package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener {


	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent; 
	public ExtentTest test;

	String repName;

	public void onStart(ITestContext testContext) {
		/*SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd. HH.mm.SS");
	Date dt=new Date();
	String currentdatetimestamp=df.format(dt);
		 */

		String timeStamp = new SimpleDateFormat ("yyyy.MM.dd. HH.mm.SS"). format(new Date());//time stamp
		repName="Test-Report -"+timeStamp+".html";
		sparkReporter=new ExtentSparkReporter(".\\reports\\"+ repName);//specify location of the report
		sparkReporter.config().setDocumentTitle("Open Cart Automation Report"); // Title of report 
		sparkReporter.config().setReportName("Open Cart Functional Testing");//name of the report
		sparkReporter.config().setTheme(Theme.DARK);
		extent=new ExtentReports();
		extent. attachReporter (sparkReporter);
		extent. setSystemInfo("Application", "Pest Store Users API");
		extent. setSystemInfo("Operating System", System.getProperty("os.name"));
		extent. setSystemInfo("User Name", System.getProperty("user.name"));
		extent. setSystemInfo( "Environemnt", "SDET");
		extent. setSystemInfo("user", "Prem");

		String os = testContext.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating System", os);

		String browser = testContext.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Browser", browser);

		List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
		if(!includedGroups.isEmpty()) {
			extent.setSystemInfo("Groups",includedGroups.toString());
		}
	}

	public void onTestSuccess(ITestResult result){


		test=extent.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups()) ;
		test.createNode(result.getName()); 
		test.log(Status.PASS, "Test Passed");
	}
	public void onTestFailure (ITestResult result) {
		test=extent.createTest(result.getName());
		test.createNode(result.getName());
		test.assignCategory(result.getMethod().getGroups()); 
		test.log(Status.FAIL, "Test Failed");
		test.log(Status.FAIL, result.getThrowable().getMessage());
		try {
			String impPath = new BaseClass().captureScreen(result.getName());
			test.addScreenCaptureFromPath(impPath);
		}catch(IOException e1){
			e1.printStackTrace();

		}
	}
	public void onTestSkipped(ITestResult result)
	{
		test=extent.createTest(result.getName());
		test.createNode(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test. log(Status.SKIP, "Test Skipped");
		test.log(Status.SKIP, result.getThrowable().getMessage());
	}
	public void onFinish(ITestContext testContext) {
		extent.flush();
		
		String pathOfExtentReport = System.getProperty("user.dir") + "\\reports\\" + repName;
		File extendReport = new File(pathOfExtentReport);
		try {
			Desktop.getDesktop().browse(extendReport.toURI());
		}catch(IOException e) {
			e.printStackTrace();
		}
		/*
		try 
		{
		URL url = new URL ("file:///"+System.getProperty("user.dir")+"\\reports\\"+repName) ;
		// Create the email message
		ImageHtmlEmail email = new ImageHtmlEmail();
		email.setDataSourceResolver(new DataSourceUrlResolver (url));
		email. setHostName ("smtp.googlemail.com");
		email.setSmtpPort (465);
		email. setAuthenticator(new DefaultAuthenticator("manoharkokare777@gmail.com", "password"));
		email. setSSLOnConnect (true);
		email. setFrom("manoharkokare777@gmail.com"); //Sender
		email.setSubject ("Test Results");
		email.setMsg("Please find Attached Report....");
		email. addTo("premkokre777@gmail.com"); //Receiver
		email. attach(url, "extent report", "please check report...");
		email. send(); // send the email
		}
		catch (Exception e)
		{
		e. printStackTrace();
		}
		*/
	}
}
