package testCases;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_001_AccountRegistrationTest extends BaseClass {
    @Test(groups={"Regression","Master"}) //step8 groups added
    public void test_account_Registration() throws InterruptedException
    {
        logger.info("****** Starting TC_001_AccountRegistrationTest *****");
        try {
            HomePage hp = new HomePage(driver);
            hp.clickMyAccount();
            logger.info("clicked on My Account");

            hp.clickRegister();
          logger.info("clicked on  Register");

            AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
            logger.info("Providing Customer Data");
            /*
            regpage.setFirstName("abc0098");
            regpage.setLastName("jamesbond");
            regpage.setEmail("abc0098jamesbond@gmail.com");
            //to scroll page
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0,4000)", "");

            regpage.setPassword("testabc@123");
            regpage.setPrivacyPolicy();
            regpage.clickContinue();

            //  String confrmsg= regpage.getConfirmationMsg();
            // Assert.assertEquals(confrmsg,"Your Account Has Been Created!");

             */
            regpage.setFirstName(randomeString().toUpperCase());
           regpage.setLastName(randomeString().toUpperCase());
           regpage.setEmail(randomeString()+"@gmail.com");
            //to scroll page
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0,4000)", "");

           // regpage.setPassword(randomAlphaNumeric());
          String  password=randomAlphaNumeric();
          regpage.setPassword(password);

            regpage.setPrivacyPolicy();
            regpage.clickContinue();
            logger.info("Clicked on Continue");
        }
        catch(Exception e)
        {
         Assert.fail();
        }
logger.info("*** Finished TC_001AccountRegistration****");
    }

}
