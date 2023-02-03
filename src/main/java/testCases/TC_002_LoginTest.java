package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC_002_LoginTest extends BaseClass {

    @Test(groups={"Sanity","Master"})  //step8 groups added
    public void test_login()
    {
        logger.info("*** Starting TTC_002_LoginTest***");
try {
    HomePage hp = new HomePage(driver);
    hp.clickMyAccount();
    logger.info("clicked on my account");

    hp.clickLogin();
    logger.info("clicked on login link");

    LoginPage lp = new LoginPage(driver);
    logger.info("providing login data");
    lp.setEmail(rb.getString("email")); //email id from config properties
    lp.setPassword(rb.getString("password"));

    lp.clickLogin();
    logger.info("clicked on login button");

     /* MyAccountPage macc=new MyAccountPage(driver);
        boolean targetpage= macc.isMyAccountPageExists();
        Assert.assertEquals(targetpage,true);*/
}
catch(Exception e)
{
    Assert.fail();
}
logger.info("*** Finished TC_002_LoginTest***");
    }

}
