/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pageTest;

import com.google.common.base.Predicate;
import java.util.List;
import org.openqa.selenium.By;
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
public class PropertyFinderSearch {

    private final WebDriver driver;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @FindBy(xpath = "//*[@id=\"serp-nav\"]/div[1]/div/div/button")
    private WebElement sortByButton;

    @FindBy(xpath = "//*[@id=\"serp-nav\"]/div[1]/div/div/div/ul/li")
    private List<WebElement> sortByOption;

    @FindBy(xpath = "//*[@id=\"serp-nav\"]/div[2]/div[2]")
    private WebElement resultCount;

    @FindBy(xpath = "//*[@id=\"primary-content\"]/div[1]")
    private WebElement noProperty;

    @FindBy(xpath = "//*[@id=\"serp\"]/ul/li")
    private List<WebElement> resultSet;

    @FindBy(xpath = "//*[@id=\"property-facts\"]/table/tbody/tr[5]/td")
    private WebElement finalResultBedCount;

    public PropertyFinderSearch(WebDriver driver) {
        this.driver = driver;
        //This initElements method will create  all WebElements
        PageFactory.initElements(driver, this);
    }

    public void goToPageNumber(int pageNumber) {
        driver.get(driver.getCurrentUrl() + "&page=" + pageNumber);
        fullLoad();
    }

    private int getResultCount() {
        fullLoad();
        return Integer.parseInt(resultCount.getText().split(" ")[0].replace(",", ""));
    }

    public void selectLastProperty() {
        int max=driver.findElements(By.xpath("//*[@id=\"serp\"]/ul/li")).size();
        String xpath = "//*[@id=\"serp\"]/ul/li[" + max + "]/div[3]/h2/a";
        driver.findElement(By.xpath(xpath)).click();
        fullLoad();
    }

    public int setSortBySelector(String option) {
        if (noProperty.isDisplayed()) {
            logger.info("No Property To sort");
            return 0;
        }
        this.sortByButton.click();
        for (WebElement ele : sortByOption) {
            if (ele.getText().equals(option)) {
                ele.click();
                return getResultCount();
            }
        }
        logger.info(option + " Option not found");
        return 0;
    }

    public WebElement getFinalBedcount() {
        return finalResultBedCount;
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
