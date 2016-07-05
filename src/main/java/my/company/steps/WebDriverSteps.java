package my.company.steps;

import java.util.*;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.Keys;

import ru.yandex.qatools.allure.annotations.Attachment;
import ru.yandex.qatools.allure.annotations.Step;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class WebDriverSteps {

    public WebDriver driver;

    public WebDriverSteps(WebDriver driver) {
        this.driver = driver;
    }

    @Step
    public void openMainPage() {
        driver.get("http://www.yandex.ru");
    }

    @Step
    public void search(String text) {
    	driver.findElement(By.id("header-search")).sendKeys(text);            	
    	WebDriverWait wait = new WebDriverWait(driver, 30);
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.className("button__text")));
        button.click();
    }

    @Step
    public void openPageLinkText(String text) {
    	WebDriverWait wait = new WebDriverWait(driver, 30);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.linkText(text)));
        element.click();
    }
    
    @Step
    public void openPagePartialLinkText(String text) {
    	WebDriverWait wait = new WebDriverWait(driver, 30);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText(text)));
        element.click();
    }    
    
    @Step
    public void sendValueById(String id, String value) {
        driver.findElement(By.id(id)).sendKeys(value);        
    }
    
    @Step
    public void setCheckboxText(String text) {        
        WebDriverWait wait = new WebDriverWait(driver, 30);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='checkbox__label' and text()='" + text + "']")));
        element.click();
    }
    
    @Step
    public void clickButtonText(String text) {            	 	
        // using javascript due to click() method somehow not working with driver
        ////driver.findElement(By.xpath("//*[contains(text(), '" + text + "')]")).click();
    	WebDriverWait wait = new WebDriverWait(driver, 30);
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(), '" + text + "')]")));
     	JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].click();", button);
    }
    
    @Step
    public void assertCountByClass(String class_name, int count) {        
    	List<WebElement> list = driver.findElements(By.className(class_name));
        for (WebElement el : list) {
           System.out.println(el.getText());
        }  
        assertThat(list.size(), is(count));
    }
    
    @Step
    public String getFirstClassName(String class_name) {        
    	List<WebElement> list = driver.findElements(By.className(class_name));
        System.out.println("First item found: " + list.get(0).getText());             
        return list.get(0).getText();
    }
    
    @Step
    public void assertFirstElemByClass(String class_name, String first_el) {
    	String first_refined = getFirstClassName(class_name);
    	assertThat(first_refined, is(first_el));    
    }  
    
    @Attachment(type = "image/png")
    public byte[] makeScreenshot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
        
    public void quit() {
        driver.quit();
    }
}
