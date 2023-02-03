package testBase;

//import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.xml.DOMConfigurator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class BaseClass {
    public static WebDriver driver;
    public Logger logger;
    public ResourceBundle rb; //to read config.prorperties

    @BeforeClass(groups={"Master","Sanity","Regression"})  //step 8 groups added
    @Parameters("browser")
    public void setup(String br) {
        DOMConfigurator.configure("src\\test\\resources\\log4j2.xml");
        logger = LogManager.getLogger(this.getClass()); //logging

        rb = ResourceBundle.getBundle("config");//load config properties

        //WebDriverManager.chromedriver().setup();
        //driver=new ChromeDriver();

        //multi browsers
        if (br.equals("chrome")) {
            driver = new ChromeDriver();
        } else if (br.equals("edge")) {
            driver = new EdgeDriver();
        } else {
            driver = new FirefoxDriver();
        }
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        // driver.get("https://demo.opencart.com/index.php");
        driver.get(rb.getString("appURL")); //..get url from config properties
        driver.manage().window().maximize();
    }

    @AfterClass(groups = {"Master","Sanity","Regression"}) //step 8 groups added
    public void teardown()
    {

        driver.quit();
    }

    public String randomeString() {
        String generatedString = RandomStringUtils.randomAlphabetic(5);
        return (generatedString);
    }

    public String randomeNumber() {
        String generatedString2 = RandomStringUtils.randomNumeric(10);
        return (generatedString2);
    }

    public String randomAlphaNumeric() {
        String st = RandomStringUtils.randomAlphabetic(4);
        String num = RandomStringUtils.randomNumeric(3);
        return (st + "@" + num);
    }

    public String captureScreen(String tname) throws IOException {

        String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
/*SimpleDateFormat df=new SimpleDateFormat("yyyyMMddhhmmss");
new Date();
df.format(dt);*/
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
        String destination = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";

        try {
            FileUtils.copyFile(source, new File(destination));
        } catch (Exception e) {
            e.getMessage();
        }
        return destination;

    }
}
