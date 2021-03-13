package basicAppium;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BasicAppiumTest {

    private AppiumDriver driver;

    @Before
    public void BeforeTest() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName","moto e5 plus");
        capabilities.setCapability("platformVersion","8.0.0");
        capabilities.setCapability("appPackage","com.google.android.calculator");
        capabilities.setCapability("appActivity","com.android.calculator2.Calculator");
        capabilities.setCapability("platformName","Android");

        //driver apunte a nuestro appiumdesktop
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);

        //implicit:
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }


    @Test
    public void verify_Calculator(){
        //click button 9

        driver.findElement(By.xpath("//android.widget.Button[@text='9']")).click();

        //click button +

        driver.findElement(By.xpath("//android.widget.Button[@content-desc=\"más\"]")).click();

        //click button 2

        driver.findElement(By.xpath("//android.widget.Button[@text='2']")).click();

        //click button =

        driver.findElement(By.xpath("//android.widget.Button[@content-desc=\"igual a\"]")).click();

        //resultado 11 --> verificacion

        String expectedResult = "11";
        //String actualResult = driver.findElement(By.id("com.google.android.calculator:id/result_final")).getText();
        String actualResult = driver.findElement(By.xpath("//android.widget.TextView[@resource-id='com.google.android.calculator:id/result_final']")).getText();
        Assert.assertEquals("ERROR RESULTADO INCORRECTO",expectedResult,actualResult);
    }


    @After
    public void afterTest(){
        driver.quit();
    }
}
