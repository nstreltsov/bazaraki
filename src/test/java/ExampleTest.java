import com.bazaraki.autotests.drivers.WebDriverFacade;
import com.bazaraki.autotests.entity.Device;
import com.bazaraki.autotests.pages.LoginPage;
import com.bazaraki.autotests.pages.MainPage;
import com.bazaraki.autotests.pages.OfferPage;
import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.FileReader;
import java.util.List;

/**
 * @author Nikolay Streltsov on 16.05.2020
 */
@RunWith(Parallelized.class)
public class ExampleTest extends BaseTest{
    private final String text = "2 bedrooms flat";
    private final String priceMin = "200";
    private final String priceMax = "1,000";
    private final String district = "Limassol district";

    private Device device;

    @Parameterized.Parameters
    public static List<Device> getEnvironments() throws Exception {
        String fileDir = System.getProperty("user.dir")
                + System.getProperty("file.separator")
                + "src\\test\\resources\\devices.csv";
        CSVReader reader = new CSVReader(new FileReader(fileDir)
                , ';', '"', 1);
        ColumnPositionMappingStrategy<Device> beanStrategy = new ColumnPositionMappingStrategy<>();
        beanStrategy.setType(Device.class);
        beanStrategy.setColumnMapping(new String[] {"deviceName","platform", "platformVersion", "orientation", "browser", "systemPort", "driver"});
        CsvToBean<Device> csvToBean = new CsvToBean<>();
        List<Device> devices = csvToBean.parse(beanStrategy, reader);
        return devices;
    }

    public ExampleTest(Device device){
        this.device = device;
    }

    @Before
    public void before(){
        WebDriverFacade.setDevice(device);
    }

    @Test
    @DisplayName("Search offer")
    public void exampleTest(){
        OfferPage offerPage = new MainPage()
                .openFilters()
                .filter(text, priceMin, priceMax, district)
                .searchItem();
        String offerUrl = offerPage.getCurrentUrl();
        String offerId = offerPage.getId();
        Assert.assertTrue(String.format("Url [%s] не содержит идентификатор обхявления [%s]", offerUrl, offerId)
                , offerUrl.contains(offerId));
        LoginPage loginPage = offerPage.toFavorites();
        loginPage.confirmTerms();
        loginPage.submit();
        Assert.assertEquals("Некорректное сообщение об ошибке", "The phone number is empty or not valid", loginPage.getError());
    }

}
