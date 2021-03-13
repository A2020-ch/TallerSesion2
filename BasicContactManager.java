package basicAppium;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class BasicContactManager {


    private AppiumDriver driver;

    @Before
    public void BeforeTest() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName","moto e5 plus");
        capabilities.setCapability("platformVersion","8.0.0");
        capabilities.setCapability("appPackage","com.google.android.contacts");
        capabilities.setCapability("appActivity","com.android.contacts.activities.PeopleActivity");
        capabilities.setCapability("platformName","Android");

        //driver apunte a nuestro appiumdesktop
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);

        //implicit:
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void crear_Contacto(){
        // click en agregar contacto
        driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc='Crear contacto'] ")).click();

        //creando contacto nuevo

        String nombre = "aaaaron" ;

        driver.findElement(By.xpath("//android.widget.EditText[@text='Nombre']")).sendKeys(nombre);

        driver.findElement(By.xpath("//android.widget.EditText[@text='Teléfono']")).sendKeys("789456123");
        driver.findElement(By.xpath("//android.widget.TextView[@text='Guardar']")).click();

        //verificar el nombre de usuario

        int numberContact=driver.findElements(By.xpath("//android.widget.TextView[@text='"+nombre+"']")).size();
        Assert.assertEquals("ERROR en contacto",1,numberContact);

        //driver.findElement(By.xpath("//android.widget.TextView[@text='"+nombre+"']")).isDisplayed();


        // volver a la lista de contactos
        //driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc=\"Navegar hacia arriba\"]")).click();

        // *** verificacion en la lista de contactos

        /// Explicit wait es para un control especifico --> condiciones
        WebDriverWait explicitWait= new WebDriverWait(driver,20);
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='"+nombre+"']")));
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.TextView[@text='"+nombre+"']")))  ;

        boolean isDisplayed=driver.findElement(By.xpath("//android.widget.TextView[@text='"+nombre+"']")).isDisplayed();
        Assert.assertTrue("ERROR el contacto no es mostrado en la lista",isDisplayed);

        //swipe
        TouchAction action = new TouchAction(driver);

        WebElement startControl=driver.findElement(By.xpath("//*[@resource-id='android:id/list']/android.view.ViewGroup[11]"));
        WebElement endControl=driver.findElement(By.xpath("//*[@resource-id='android:id/list']/android.view.ViewGroup[1]"));

        // X Y del startControl
        int startX=startControl.getLocation().getX();
        int startY=startControl.getLocation().getY();
        // X Y del endControl
        int endX=endControl.getLocation().getX();
        int endY=endControl.getLocation().getY();

        action.press(PointOption.point(startX,startY)).
                waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1))).
                moveTo(PointOption.point(endX,endY)).release().perform();





    }

    @After
    public void afterTest(){
        driver.quit();
    }
}
