package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;//log4j
import org.apache.logging.log4j.Logger;//log4j
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	public static  WebDriver driver;
	public Logger logger;//log4j
	public Properties p;
	@BeforeClass(groups= {"sanity","regression","Master"})
	@Parameters({"os","browser"})
	public void setup(String os,String br) throws IOException, URISyntaxException {
		//Loading config.properties file
		FileReader file = new FileReader("./src//test//resources//congig.properties");
		p = new Properties();
		p.load(file);

		logger = LogManager.getLogger(this.getClass());//Log4j

		if(p.getProperty("execution_env").equalsIgnoreCase("remote")) {

			DesiredCapabilities capablities = new DesiredCapabilities();

			if(os.equalsIgnoreCase("windows")) {
				capablities.setPlatform(Platform. WIN11);
			}else if(os.equalsIgnoreCase("linux")){
				capablities. setPlatform (Platform.LINUX);
			}
			else if(os.equalsIgnoreCase("mac")){
				capablities. setPlatform (Platform.MAC);
			}
			else {
				System.out.println("No matching os");
				return ;
			}

			//broeser

			switch(br.toLowerCase()) {

			case "chrome" :capablities.setBrowserName("chrome") ; break;
			case "edge" : capablities.setBrowserName("MicrosoftEdge") ; break;
			case "firefox" : capablities.setBrowserName("Firefox") ; break;
			default : System.out.println("Invalid browser name.."); return;

			}
			
			driver=new RemoteWebDriver(new URI("http://localhost:4444/wd/hub").toURL(),capablities);

		}


		if(p.getProperty("execution_env").equalsIgnoreCase("local")) {
			switch(br.toLowerCase()) {

			case "chrome" : driver = new ChromeDriver(); break;
			case "edge" : driver = new EdgeDriver(); break;
			case "firefox" : driver = new FirefoxDriver();break;
			default : System.out.println("Invalid browser name.."); return;

			}

		}


		//driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(p.getProperty("appURL2"));//reading url from propertis file
		driver.manage().window().maximize();

	}
	@AfterClass(groups= {"sanity","regression","Master"})
	public void tearDown() {
		driver.quit();
	}

	public String  randomString(){

		String randomStr = UUID.randomUUID().toString();
		while(randomStr.length() < 5) {
			randomStr += UUID.randomUUID().toString();
		}
		return randomStr.substring(0, 5);
	}

	public String captureScreen(String tname) throws IOException {
		String timeStamp = new SimpleDateFormat ("yyyy.MM.dd. HH.mm.SS"). format(new Date());//time stamp

		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

		String targetFilePath = System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp;
		File targetFile = new File(targetFilePath);

		sourceFile.renameTo(targetFile);
		return targetFilePath;
	}

}
