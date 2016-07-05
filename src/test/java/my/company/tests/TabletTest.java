package my.company.tests;

import my.company.steps.WebDriverSteps;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.CapabilityType;
import java.util.concurrent.TimeUnit;


public class TabletTest {

    private WebDriverSteps steps;

    @Before
    public void setUp() throws Exception {
    	steps = new WebDriverSteps(
                new PhantomJSDriver(new DesiredCapabilities())                
        );
    }

    @Test
    public void tabletTest() throws Exception {
    	// setting implicit wait and opening main test page    	
    	steps.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    	steps.openMainPage();
        steps.makeScreenshot();
                
        // navigating through market sub-pages
        steps.openPageLinkText("Маркет");
        steps.openPageLinkText("Компьютеры");        
        steps.openPageLinkText("Планшеты");
        steps.makeScreenshot();
        
        // go to extended search and fill the params
        steps.openPagePartialLinkText("Расширенный");
        steps.openPageLinkText("Ещё");
        steps.sendValueById("gf-pricefrom-var", "20000");
        steps.sendValueById("gf-priceto-var", "25000");
        steps.setCheckboxText("Acer");
        steps.setCheckboxText("DELL");
        steps.clickButtonText("Применить");        
        steps.makeScreenshot();
        
        // count elements and verify first item info
        steps.assertCountByClass("snippet-card__header-text", 10);        
        String first_el = steps.getFirstClassName("snippet-card__header-text");
        steps.search(first_el);        
        steps.assertFirstElemByClass("snippet-card__header-text", first_el);
        steps.makeScreenshot();
        
        steps.quit();        
    }

}

