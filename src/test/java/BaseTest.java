import com.bazaraki.autotests.drivers.WebDriverFacade;
import io.qameta.allure.Attachment;
import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

/**
 * @author Nikolay Streltsov on 18.05.2020
 */
public class BaseTest {

    @Rule
    public TestWatcher screenshotOnFailure = new TestWatcher() {
        @Override
        protected void failed(Throwable e, Description description) {
            makeScreenshotOnFailure();
        }

        @Attachment("Screenshot on failure")
        public byte[] makeScreenshotOnFailure() {
            return ((TakesScreenshot) WebDriverFacade.getDriver()).getScreenshotAs(OutputType.BYTES);
        }

        @Override
        protected void finished(Description description) {
            WebDriverFacade.getDriver().quit();
        }
    };
}
