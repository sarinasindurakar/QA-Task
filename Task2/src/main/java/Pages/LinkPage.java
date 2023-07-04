package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LinkPage {
    private WebDriver driver;
    private By home= By.xpath("//a[@class=\"header-logo__image\"]");

    public LinkPage( WebDriver driver){
        this.driver=driver;

    }
    public HomePage clickonlogo(){
        // wait.presenceOfElements()
        driver.findElement(home).click();
        return new HomePage(driver);

    }

}
