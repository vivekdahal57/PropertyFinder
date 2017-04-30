/*
 * To change hp license header, choose License Headers in Project Properties.
 * To change hp template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pageTest;

import io.appium.java_client.android.AndroidDriver;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;

/**
 *
 * @author Alex
 */
public class AppiumTest {

    AndroidDriver driver;

    @BeforeMethod
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("appium-version", "1.0");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "4.4.2");
        capabilities.setCapability("deviceName", "huawei-ho_u19-TSTCIFBE9SZH49DA");
        capabilities.setCapability("appPackage", "com.google.android.gm");
        capabilities.setCapability("appActivity", "com.google.android.gm.ConversationListActivityGmail");
        driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
    }

    @Test(priority = 0)
    public void testToSwichAccountAndRevertIt(){
        HomePage hp=new HomePage(driver);
        hp.waitForVisibilityOf(hp.gotItButton);
        hp.gotItButton.click();
        hp.takeMeToGmail.click();
        hp.homeIcon.click();
        hp.waitForVisibilityOf(hp.inbox);
        hp.accountSwitch.click();
        hp.waitForVisibilityOf(hp.dahal1Account);
        hp.dahal1Account.click();
        hp.waitForVisibilityOf(hp.searchIcon);
        hp.homeIcon.click();
        hp.accountSwitch.click();
        hp.dahal57Account.click();
    }
    
    @Test(priority = 1)
    public void testToComposeEmail(){
        try {
            HomePage hp=new HomePage(driver);
            hp.waitForVisibilityOf(hp.gotItButton);
            hp.gotItButton.click();
            hp.takeMeToGmail.click();
            hp.composeEmailButton.click();
            hp.waitForVisibilityOf(hp.sendButton);
            hp.toTextArea.sendKeys("vivek.dahal1@gmail.com");
            hp.subjectTextArea.sendKeys("Hello from the Automation Side!!!");
            hp.contentTextArea.sendKeys("My Name is Bibek and I am Automating Mobile Apps as well :) !!!");
            hp.sendButton.click();
            Thread.sleep(10000);
        } catch (InterruptedException ex) {
            Logger.getLogger(AppiumTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @AfterMethod
    public void tearDown() throws Exception {
        driver.quit();
    }
}
