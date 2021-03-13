package basicAppium;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BasicWhenDo {

    private AppiumDriver driver;

    @Before
    public void BeforeTest() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "moto e5 plus");
        capabilities.setCapability("platformVersion", "8.0.0");
        capabilities.setCapability("appPackage", "com.vrproductiveapps.whendo");
        capabilities.setCapability("appActivity", ".ui.HomeActivity");
        capabilities.setCapability("platformName", "Android");

        //driver apunte a nuestro appiumdesktop
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

        //implicit:
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void crear_Tarea(){
        driver.findElement(By.id("com.vrproductiveapps.whendo:id/fab")).click();

        //crear titulo de la tarea
        String tarea = "escribir test case movil" ;
        driver.findElement(By.xpath("//android.widget.EditText[@text='Título']")).sendKeys(tarea);

        //creando la nota
        String nota = "detallar los apk a instalar en el movil";
        driver.findElement(By.id("com.vrproductiveapps.whendo:id/noteTextNotes")).sendKeys(nota);

        //guardamos la tarea creada.
        driver.findElement(By.xpath("//android.widget.TextView[@content-desc='Guardar']")).click();

    }

    @After
    public void afterTest(){
        driver.quit();
    }



}
