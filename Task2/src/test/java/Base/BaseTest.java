package Base;
import DriverFactory.DriverFactory;
import Pages.*;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.util.Properties;

public class BaseTest {
    private WebDriver driver;
    public LinkPage linkpage;
    public HomePage homepage;
    public DriverFactory driverFactory;
    public Properties properties;

    @BeforeTest
    public void setUp(){

        driverFactory = new DriverFactory();

        properties=driverFactory.initProperties();
        // System.out.println(properties);
        //String bb= properties.getProperty("browser");
        // System.out.println(bb);
        driver = driverFactory.initDriver(properties);
/*


       System.setProperty("webdriver.chrome.driver", "F:\\Internship-2022\\Automation\\chromedriver.exe");
       driver = new ChromeDriver();
       driver.get("https://iatipublisher-dev.yipl.com.np/");
*/
        driver.manage().window().maximize();
        linkpage=new LinkPage(driver);
        homepage=new HomePage(driver);


    }
    @AfterTest
    public void tearDown(){

        driver.quit();
    }

}
