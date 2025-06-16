package Automation.TestComponents;

import org.testng.annotations.AfterMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chromium.ChromiumOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import Automation.Framework.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public WebDriver driver;
	public LandingPage landingPage;
	public WebDriver initializeDriver() throws IOException
	{
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//Automation//resources//GlobalData.properties"); 
		prop.load(fis);
		String browserName = System.getProperty("browser")!=null ? System.getProperty("browser"):prop.getProperty("browser");
		//prop.getProperty("browser");
		
		if(browserName.equalsIgnoreCase("Chrome"))
		{
		ChromeOptions 	options = new ChromeOptions();
		WebDriverManager.chromedriver().setup();
		if(browserName.contains("headless")) {
		options.addArguments("headless");}
		
		driver = new ChromeDriver(options);
		driver.manage().window().setSize(new Dimension(1440,900)); // to use if we see any flakiness in scripts browser will run in fullscreen
		}
		else if(browserName.equalsIgnoreCase("edge"))
		{
			System.setProperty("webdriver.edge.driver", "edge.exe");
			driver = new EdgeDriver();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}
	
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException
	{
		 driver = initializeDriver();
		 landingPage = new LandingPage(driver);
			landingPage.goTo();
			return landingPage;
	}
	
	public List<HashMap<String, String>> getJsonData(String filePath) throws IOException
	{
		//reading JSON to string
		String jsonContent = FileUtils.readFileToString(new File(filePath),
				StandardCharsets.UTF_8);
		
		//Convert String to Hashmap with Jackson Databind
		
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
		});
		
		return data;
		}
	
	public String getScreenshot(String testCaseName,WebDriver driver) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//"+ testCaseName + ".png");
		
		FileUtils.copyFile(source, file);
		
		return System.getProperty("user.dir") + "//reports//"+ testCaseName + ".png";
	}
	
	@AfterMethod(alwaysRun=true)
	
	public void tearDown()
	{
		driver.close();
	}

}
