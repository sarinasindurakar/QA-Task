package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class HomePage {
    private WebDriver driver;
    private By popup=By.id("closeButton");

    public HomePage(WebDriver driver){
        this.driver=driver;
        //Actions action = new Actions(driver);

    }
    public void closepopup(){
        Actions action = new Actions(driver);
        WebElement p= driver.findElement(popup);
        action.moveToElement(p).build().perform();

    }

}
