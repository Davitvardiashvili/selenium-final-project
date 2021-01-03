import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;
import java.util.Random;

public class Final_project {
    private WebDriver driver;

    @Parameters({"browser"})
    @BeforeMethod()
    public void setup(String browser) {
        if (browser.equalsIgnoreCase("Edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        } else if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
    }


    @Test
    public void task1() throws InterruptedException {
        driver.manage().window().maximize();
        driver.navigate().to("http://automationpractice.com/index.php");
        Actions movingToElement = new Actions(driver);
        WebElement Women = driver.findElement(By.xpath("//div[@id = 'block_top_menu']/child::ul/child::li"));
        movingToElement.moveToElement(Women).perform();

        driver.findElement(By.xpath("//a[@title ='T-shirts']")).click();
        JavascriptExecutor js= (JavascriptExecutor) driver;
        WebElement product = driver.findElement(By.className("left-block"));
        js.executeScript("arguments[0].scrollIntoView();",product);
        movingToElement.moveToElement(product).perform();
        driver.findElement(By.xpath("//span[text()='Quick view']")).click();



        WebElement frame = driver.findElement(By.className("fancybox-iframe"));
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame));

        WebElement Main_img = driver.findElement(By.id("bigpic"));
        String src_before = Main_img.getAttribute("src");

        WebElement second_model = driver.findElement(By.id("thumbnail_2"));
        movingToElement.moveToElement(second_model).perform();

        WebElement third_model = driver.findElement(By.id("thumbnail_3"));
        movingToElement.moveToElement(third_model).perform();

        WebElement fourth_model = driver.findElement(By.id("thumbnail_4"));
        movingToElement.moveToElement(fourth_model).perform();

        String src_after = Main_img.getAttribute("src");
        System.out.println("image unchanging is "+ src_before.equals(src_after));

        Select dropdown = new Select(driver.findElement(By.id("group_1")));
        dropdown.selectByVisibleText("M");

        driver.findElement(By.className("icon-plus")).click();
        driver.findElement(By.name("Submit")).click();
        driver.switchTo().defaultContent();
        WebElement close_window = driver.findElement(By.className("cross"));
        new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(close_window)).click();
        WebElement header = driver.findElement(By.id("header_logo"));
        js.executeScript("arguments[0].scrollIntoView();",header);
        header.click();
        WebElement Dresses = driver.findElement(By.xpath("//ul[@class = 'sf-menu clearfix menu-content sf-js-enabled sf-arrows']/child::li/following-sibling::li"));
        movingToElement.moveToElement(Dresses).perform();

        Dresses.findElement(By.cssSelector("a[title = 'Casual Dresses']")).click();
        WebElement product_2 = driver.findElement(By.cssSelector("div.left-block"));
        js.executeScript("arguments[0].scrollIntoView();",product_2);
        movingToElement.moveToElement(product_2).perform();
        driver.findElement(By.cssSelector("a[title = 'Add to cart']")).click();

        WebElement button_continue = driver.findElement(By.xpath("//div[@class = 'button-container']/child::span"));
        new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(button_continue)).click();

        WebElement cart = driver.findElement(By.className("shopping_cart"));
        js.executeScript("arguments[0].scrollIntoView();",cart);
        WebElement cart_2 = driver.findElement(By.xpath("//a[@title = 'View my shopping cart']"));
        movingToElement.moveToElement(cart_2).perform();
        WebElement check_out = cart.findElement(By.id("button_order_cart"));
        new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(check_out)).click();


        String desc_1 = driver.findElement(By.xpath("//a[contains(text(),'Faded Short Sleeve T-shirt')]")).getText();
        String desc_2 = driver.findElement(By.xpath("//*[@id=\"product_3_13_0_0\"]/td[2]/p/a")).getText();
        String quantity_1 = driver.findElement(By.xpath("//input[@class = 'cart_quantity_input form-control grey']")).getAttribute("value");
        String quantity_2 = driver.findElement(By.xpath("//input[@name = 'quantity_3_13_0_0']")).getAttribute("value");
        Assert.assertEquals("Faded Short Sleeve T-shirts",desc_1);
        Assert.assertEquals("2",quantity_1);
        Assert.assertEquals("Printed Dress",desc_2);
        Assert.assertEquals("1",quantity_2);
        System.out.println(desc_1 + " - " + quantity_1 + "\n" + desc_2 + " - " + quantity_2 );

        WebElement Proceed = driver.findElement(By.xpath("//span[text() = 'Proceed to checkout']"));
        js.executeScript("arguments[0].scrollIntoView();",Proceed);
        Proceed.click();

        Random rand = new Random();
        int rand_int1 = rand.nextInt(1000);
        driver.findElement(By.id("email_create")).sendKeys("tbc"+rand_int1 + "@gmail.com");
        driver.findElement(By.id("SubmitCreate")).click();


        wait.until(ExpectedConditions.elementToBeClickable(By.id("uniform-id_gender1"))).click();
        driver.findElement(By.id("customer_firstname")).sendKeys("mathew");
        driver.findElement(By.id("customer_lastname")).sendKeys("smith");
        driver.findElement(By.id("passwd")).sendKeys("123456");
        Select days = new Select(driver.findElement(By.id("days")));
        days.selectByValue("15");
        Select months = new Select(driver.findElement(By.id("months")));
        months.selectByValue("10");
        Select years = new Select(driver.findElement(By.id("years")));
        years.selectByValue("1975");
        WebElement company_name = driver.findElement(By.id("company"));
        company_name.sendKeys("tbc_bank");
        js.executeScript("arguments[0].scrollIntoView();",company_name);
        driver.findElement(By.id("address1")).sendKeys("tbilisi/georgia");
        driver.findElement(By.id("address2")).sendKeys("beliashvili st 41");
        driver.findElement(By.id("city")).sendKeys("tbilisi");
        driver.findElement(By.id("postcode")).sendKeys("00000");
        driver.findElement(By.id("other")).sendKeys("Happy new year");
        driver.findElement(By.id("phone")).sendKeys("2400400");
        Select state = new Select(driver.findElement(By.id("id_state")));
        state.selectByValue("3");
        driver.findElement(By.id("submitAccount")).click();



        WebElement proceed_to = driver.findElement(By.xpath("//span[text() = 'Proceed to checkout']"));
        js.executeScript("arguments[0].scrollIntoView();",proceed_to);
        new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(proceed_to)).click();

        WebElement proceed_to3 = driver.findElement(By.xpath("//*[@id='form']/p/button/span"));
        Actions action = new Actions(driver);
        proceed_to3.click();
        action.click().perform();
        driver.findElement(By.className("checker")).click();
        proceed_to3.click();
        WebElement payment = driver.findElement(By.className("payment_module"));
        js.executeScript("arguments[0].scrollIntoView();",payment);
        payment.click();
        driver.findElement(By.xpath("//span[text() = 'I confirm my order']")).click();

        driver.findElement(By.xpath("//a[@href = 'http://automationpractice.com/index.php?controller=contact']")).click();
        Select heading = new Select(driver.findElement(By.xpath("//select[@id = 'id_contact']")));
        heading.selectByValue("2");
        Select order = new Select(driver.findElement(By.cssSelector("[name='id_order']")));
        order.selectByIndex(1);
        driver.findElement(By.xpath("//*[@id='message']")).sendKeys("i have 3 little daughters and i will hope you bring all this dresses quickly");

        WebElement file_upload = driver.findElement(By.cssSelector("#fileUpload"));
        File img = new File(System.getProperty("user.dir")+"/src/main/resources/kaldub.jpg");
        String path = img.getAbsolutePath();
        file_upload.sendKeys(path);
        driver.findElement(By.xpath("//button[@name='submitMessage']")).click();

    }
    @AfterClass
    public void teardown() {
        driver.quit();
    }
}
