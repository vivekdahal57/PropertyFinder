/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pageTest;

import com.google.common.base.Predicate;
import java.util.List;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Alex
 */
public class PropertyFinderHomePage {

    private final WebDriver driver;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @FindBy(xpath = "//*[@id=\"search-form-property\"]/div[3]/div[1]/span/input")
    private WebElement searchArea;

    @FindBy(linkText = "Reset")
    private WebElement resetLink;
    
    @FindBy(xpath = "//*[@id=\"search-form-property\"]/div[3]/div[2]/div/button")
    private WebElement catButton;

    @FindBy(xpath = "//*[@id=\"search-form-property\"]/div[3]/div[2]/div/div/ul/li")
    private List<WebElement> catOption;

    @FindBy(xpath = "//*[@id=\"bedroom_group\"]/div[1]/div/button")
    private WebElement minBedButton;

    @FindBy(xpath = "//*[@id=\"bedroom_group\"]/div[1]/div/div/ul/li")
    private List<WebElement> minBedOption;

    @FindBy(xpath = "//*[@id=\"bedroom_group\"]/div[2]/div/button")
    private WebElement maxBedButton;

    @FindBy(xpath = "//*[@id=\"bedroom_group\"]/div[2]/div/div/ul/li")
    private List<WebElement> maxBedOption;

    @FindBy(xpath = "//*[@id=\"search-form-property\"]/div[3]/div[1]/button")
    private WebElement searchBtn;

    public PropertyFinderHomePage(WebDriver driver) {
        this.driver = driver;
        //This initElements method will create  all WebElements
        PageFactory.initElements(driver, this);
    }

    public void setCategorySelector(String category) {
        this.catButton.click();
        for (WebElement ele : catOption) {
            if (ele.getText().equals(category)) {
                ele.click();
                return;
            }
        }
        logger.info(category + " Option not found");
        this.catOption.get(0).click();
    }

    public void setMinBedSelector(String bedNumber) {
        this.minBedButton.click();
        for (WebElement ele : minBedOption) {
            if (ele.getText().equals(bedNumber)) {
                ele.click();
                return;
            }
        }
        logger.info(bedNumber + " Option not found. Resetting to Default");
        this.minBedOption.get(0).click();
    }

    public void setMaxBedSelector(String bedNumber) {
        this.maxBedButton.click();
        for (WebElement ele : maxBedOption) {
            if (ele.getText().equals(bedNumber)) {
                ele.click();
                return;
            }
        }
        logger.info(bedNumber + " Option not found. Resetting to default");
        this.maxBedOption.get(0).click();
    }

    public void searchValue(String value) {
        this.searchArea.clear();
        this.searchArea.sendKeys(value);
    }

    public void clickSearchBtn() {
        this.searchBtn.click();
    }
    
    public void clickResetLink(){
        this.resetLink.click();
    }

    public WebElement getSearchArea() {
        return searchArea;
    }

    public WebElement getSearchBtn() {
        return searchBtn;
    }

    public void fullLoad() {
        new WebDriverWait(this.driver, 5).ignoring(StaleElementReferenceException.class).ignoring(WebDriverException.class)
                .until(new Predicate<WebDriver>() {
                    public boolean apply(WebDriver arg0) {
                        return true;
                    }
                });
    }
}
