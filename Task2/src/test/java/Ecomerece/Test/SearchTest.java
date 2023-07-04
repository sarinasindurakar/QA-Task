package Ecomerece.Test;

import Base.BaseTest;
import org.testng.annotations.BeforeTest;

public class SearchTest extends BaseTest {
    @BeforeTest
    public void homepage(){
        linkpage.clickonlogo();
        homepage.closepopup();
    }
}
