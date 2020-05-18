package com.bazaraki.autotests.drivers;

import com.bazaraki.autotests.entity.Device;
import com.bazaraki.autotests.properties.EnvironmentProperties;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * @author Nikolay Streltsov on 16.05.2020
 */
public class WebDriverFacade {

    private static ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();

    private static ThreadLocal<Device> device = new ThreadLocal<>();

    private WebDriverFacade(){ }

    public static WebDriver getDriver(){
        if (Objects.isNull(driver.get())){
           initDriver();
        }
        return driver.get();
    }

    public static void setDevice(Device data){
        device.set(data);
    }

    private static void initDriver(){
        Properties properties = EnvironmentProperties.getInstance().getProperties();
        try {
            driver.set(new AppiumDriver<>(
                    new URL(properties.getProperty("appium.server")),
                    capabilities()));
            driver.get().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.get().get(properties.getProperty("app.url"));
        }catch (MalformedURLException e){
            e.printStackTrace();
        }
    }

    private static DesiredCapabilities capabilities(){
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.UDID, device.get().getDeviceName());
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, device.get().getPlatform());
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, device.get().getPlatformVersion());
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, device.get().getDeviceName());
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UIAutomator2");
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, device.get().getBrowser());
        capabilities.setCapability(AndroidMobileCapabilityType.SYSTEM_PORT, device.get().getPlatformVersion());
        capabilities.setCapability(MobileCapabilityType.ORIENTATION, device.get().getOrientation());
        capabilities.setCapability("chromedriverExecutable"
                , System.getProperty("user.dir") + System.getProperty("file.separator") + "drivers" + System.getProperty("file.separator") + device.get().getDriver());
        capabilities.setCapability("appium:chromeOptions", ImmutableMap.of("w3c", false));
        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 0);
        capabilities.setCapability("uiautomator2ServerInstallTimeout", 200000);//при меньшем значении не ставился на эмуляторе
        capabilities.setCapability("acceptAllAlert", true);
        return capabilities;
    }
}