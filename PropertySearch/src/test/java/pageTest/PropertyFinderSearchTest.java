/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pageTest;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import utility.Utility;

/**
 *
 * @author Alex
 */
public class PropertyFinderSearchTest {

    public WebDriver driver;
    public String searchValue = "marina";
    public String noOfBeds = "2 Beds";
    public String catType = "RENT";
    public String sortBy = "Price (low)";
    public static final String CHROME_DRIVER_PATH = "src/test/resource/drivers/chromedriver.exe";
    public static final String CHROME_DRIVER_KEY = "webdriver.chrome.driver";
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    public String url = "https://www.propertyfinder.ae/";

    @BeforeTest
    public void setup() {
        System.setProperty(CHROME_DRIVER_KEY, CHROME_DRIVER_PATH);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(url);
    }

    /**
     * This test go to https://www.propertyfinder.ae/ Verify search area, bed
     * count and category option
     */
    @Test
    public void testToSearchLeastCostMin2BedroomProperty() {
        //Create Home Page object
        PropertyFinderHomePage pfhp = new PropertyFinderHomePage(driver);
        pfhp.fullLoad();
        //Verify Search criteria 
        pfhp.setCategorySelector(catType.toUpperCase());
        pfhp.setMinBedSelector(noOfBeds); //at least 2 bedrooms
        pfhp.searchValue(searchValue);
        pfhp.clickSearchBtn();
        PropertyFinderSearch pfs = new PropertyFinderSearch(driver);
        pfs.fullLoad();
        //least price apartment List
        int totalResult = pfs.setSortBySelector(sortBy);
        if (totalResult == 0) {
            return;
        }
        pfs.fullLoad();
    }

    @Test
    public void testToSearchLeastCostMin2BedroomPropertyAndViewLastOfList() {
        //Create Home Page object
        PropertyFinderHomePage pfhp = new PropertyFinderHomePage(driver);
        pfhp.fullLoad();
        //Verify Search criteria 
        pfhp.setCategorySelector(catType.toUpperCase());
        pfhp.setMinBedSelector(noOfBeds);//at least 2 bedrooms
        pfhp.setMaxBedSelector(noOfBeds);//at max 2 bedrooms
        pfhp.searchValue(searchValue);
        pfhp.clickSearchBtn();
        PropertyFinderSearch pfs = new PropertyFinderSearch(driver);
        pfs.fullLoad();
        //least price apartment List
        int totalResult = pfs.setSortBySelector(sortBy);
        if (totalResult == 0) {
            return;
        }
        pfs.fullLoad();
        //goTo Last item of the list
        pfs.goToPageNumber(new Utility().pageCalculator(totalResult, 25));//25 is the records available in the page at a time
        pfs.selectLastProperty();//to select last property available in the last page of the search list
        Assert.assertEquals(pfs.getFinalBedcount().getText(), "2");
    }

    @Test
    public void dataDrivenTestToSearchProperty() {
        //Create Home Page object
        List<Map> data = new Utility().getParametersFromPropertiesFile();
        if (data == null) {
            logger.info("Cannot Run Test as dataDriven.properties file is missing.");
        } else {
            for (Map map : data) {
                PropertyFinderHomePage pfhp = new PropertyFinderHomePage(driver);
                pfhp.fullLoad();
                //Verify Search criteria 
                pfhp.setCategorySelector(map.get("categoryType").toString().toUpperCase());
                pfhp.setMinBedSelector(map.get("minBedCount").toString());//at least 2 bedrooms
                pfhp.setMaxBedSelector(map.get("maxBedCount").toString());//at max 2 bedrooms
                pfhp.searchValue(map.get("location").toString());
                pfhp.clickSearchBtn();
                PropertyFinderSearch pfs = new PropertyFinderSearch(driver);
                pfs.fullLoad();
                //least price apartment List
                int totalResult = pfs.setSortBySelector(map.get("sortByParameter").toString());
                if (totalResult == 0) {
                    return;
                }
                pfs.fullLoad();
                //goTo Last item of the list
                pfs.goToPageNumber(new Utility().pageCalculator(totalResult, 25));//25 is the records available in the page at a time
                pfs.selectLastProperty();//to select last property available in the last page of the search list
                logger.info("Bed count for the last item of the resultset: " + pfs.getFinalBedcount().getText());
                pfhp.clickResetLink();
            }
        }
    }

    @AfterClass
    public void tearDown() throws Exception {
        driver.quit();
    }
}
