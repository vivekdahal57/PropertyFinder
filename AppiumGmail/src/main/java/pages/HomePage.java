/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 *
 * @author Alex
 */
public class HomePage extends BasePage {

    @FindBy(className = "android.widget.ImageButton")
    public WebElement homeIcon;

    @FindBy(id = "com.google.android.gm:id/welcome_tour_got_it")
    public WebElement gotItButton;

    @FindBy(id = "com.google.android.gm:id/action_done")
    public WebElement takeMeToGmail;

    @FindBy(className = "android.widget.RelativeLayout")
    public WebElement accountSwitch;

    @FindBy(id = "com.google.android.gm:id/clickable_area")
    public WebElement dahal1Account;

    @FindBy(id = "com.google.android.gm:id/clickable_area")
    public WebElement dahal57Account;

    @FindBy(className = "android.widget.LinearLayout")
    public WebElement inbox;

    @FindBy(id = "com.google.android.gm:id/search")
    public WebElement searchIcon;
    
    @FindBy(id="com.google.android.gm:id/compose_button")
    public WebElement composeEmailButton;
    
    @FindBy(id="com.google.android.gm:id/send")
    public WebElement sendButton;
    
    @FindBy(id="com.google.android.gm:id/to")
    public WebElement toTextArea;
    
    @FindBy(id="com.google.android.gm:id/subject")
    public WebElement subjectTextArea;
    
    @FindBy(className = "android.webkit.WebView")
    public WebElement contentTextArea;
            

    public HomePage(WebDriver driver) {
        super(driver);
    }

}
