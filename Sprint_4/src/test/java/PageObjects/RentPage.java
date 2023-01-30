package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

// Класс страницы "Про аренду"
public class RentPage {
    private WebDriver driver;

    //Локатор поля ввода Даты аренды
    //$x(".//input[@placeholder='* Когда привезти самокат']")
    private By inputDateRental = By.xpath(".//input[@placeholder='* Когда привезти самокат']");

     //Локатор поля ввода Срока аренды
    //$x(".//div[@class='Dropdown-placeholder']")
    private By inputPeriodRental = By.className("Dropdown-placeholder");

    //Локатор выпадающей строки поля ввода Срока аренды
    //$x(".//div[@class='Dropdown-option' and text() = 'трое суток']")
    private By inputDropdownPeriodRental = By.xpath(".//div[@class='Dropdown-option' and text() = 'трое суток']");

    //Локатор поля выбора "Черного" цвета самоката
    //$x(".//input[@id='black']")
    private By inputColorBlack = By.id("black");

    //Локатор поля выбора "Серого" цвета самоката
    //$x(".//input[@id='grey']")
    private By inputColorGrey = By.id("grey");

    //Локатор поля ввода "Комментарий для курьера"
    //$x(".//input[@placeholder='Комментарий для курьера']")
    private By inputComent = By.xpath(".//input[@placeholder='Комментарий для курьера']");

    //Локатор кнопки "Заказать" под полями ввода данных аренды
    //$x(".//div[@class='Order_Content__bmtHS']/div/button[@class='Button_Button__ra12g Button_Middle__1CSJM']")
    private By buttonDoRent = By.xpath(".//div[@class='Order_Content__bmtHS']/div/button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    //Локатор формы подтверждения заказа "Хотите оформить заказ?"
    //$x(".//div[@class='Order_Modal__YZ-d3']")
    private By formDoRent = By.className("Order_Modal__YZ-d3");

    //Локатор кнопки "ДА" формы подтверждения заказа "Хотите оформить заказ?"
    //$x(".//div[@class='Order_Modal__YZ-d3']/div/button[@class='Button_Button__ra12g Button_Middle__1CSJM']")
    private By buttonYesRent = By.xpath(".//div[@class='Order_Modal__YZ-d3']/div/button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    //Локатор кнопки "Посмотреть статус" на форме подтверждения окончания оформления заказа "Заказ оформлен"
    //$x(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text() = 'Посмотреть статус']")
    private By buttonViewStatus = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text() = 'Посмотреть статус']");
//-----------------------------------------------------------------------------------
    //Конструктор класса RentPage
    public RentPage(WebDriver driver) {
        this.driver = driver;
    }
//----------------------------------------------------------------------------------
    //Метод Заполняк формы Аренды самоката и подтверждаем Заказ
    public void setDate(String date) {
         //Локатор поля даты элемента Календарь формируем динамически по знчению поля Даты заказа (String date)
         //$x(".//div[@class='react-datepicker__week']/div[@class='react-datepicker__day react-datepicker__day--023 react-datepicker__day--selected']")
         String dateOrder = "0" + date.substring(0, 2);
         String xpathIsx = ".//div[@class='react-datepicker__week']/div[contains(@class,'023')]";//исходный локатор
         String xpath =  xpathIsx.replace("023",dateOrder);//Замена даты в пути
         By inputCalendar = By.xpath(xpath);//Динамический локатор для значения даты на форме календаря

       //Ждем появления формы с полем ввода даты аренды самоката
         new WebDriverWait(driver, Duration.ofSeconds(50))
            .until(ExpectedConditions.visibilityOfElementLocated(inputDateRental));
        //Вводим в календарь Дату
        driver.findElement(inputDateRental).sendKeys(date);

        timesleep(2);//Задержка выполнения теста

        //Ждем появления формы календаря с полем ввода даты аренды самоката
        new WebDriverWait(driver, Duration.ofSeconds(50))
                .until(ExpectedConditions.elementToBeClickable(inputCalendar));
        //Нажимаем на введенную дату на форме календаря
        driver.findElement(inputCalendar).click();

        timesleep(2);//Задержка выполнения теста
//---------------------------------------------------------------------
        //Ждем доступность поля сроков аренды
        new WebDriverWait(driver, Duration.ofSeconds(50))
                .until(ExpectedConditions.elementToBeClickable(inputPeriodRental));
        //Нажать на поле ввода Срока аренды
        driver.findElement(inputPeriodRental).click();

        timesleep(2);//Задержка выполнения теста

        //выбрать из выпадающего списка срок аренды
        driver.findElement(inputDropdownPeriodRental).click();

        timesleep(2);//Задержка выполнения теста
//---------------------------------------------------------------------
        //Задаем цвета самоката и коментарий к заказу
        driver.findElement(inputColorBlack).click();
        driver.findElement(inputColorGrey).click();
        driver.findElement(inputComent).sendKeys("Комментарий к заказу самоката");

        timesleep(3);//Задержка выполнения теста
//---------------------------------------------------------------------
        //Ждем доступность кнопки "Заказать"
        new WebDriverWait(driver, Duration.ofSeconds(50))
                .until(ExpectedConditions.elementToBeClickable(buttonDoRent));
        //Нажать кнопку Заказать
        driver.findElement(buttonDoRent).click();

        timesleep(5);//Задержка выполнения теста
//------------------------------------------------------------------------
        //Ждем видимость формы подтверждения заказа
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(formDoRent));
        //Ждем доступность кнопки "ДА" формы подтверждения заказа
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(buttonYesRent));
        //Нажать на кнопку "ДА" формы подтверждения заказа
        driver.findElement(buttonYesRent).click();

        timesleep(3);//Задержка выполнения теста
//----------------------------------------------------------------------
        //Ждем доступность кнопки "Посмотреть статус" формы подтверждения окончания оформления заказа "Заказ оформлен"
        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.elementToBeClickable(buttonViewStatus));
        //Нажать на кнопку "Посмотреть статус" формы подтверждения окончания оформления заказа "Заказ оформлен"
        driver.findElement(buttonViewStatus).click();
    }
//---------------------------------------------------------------------------
//Задержка для визуализации работы теста на экране
public void timesleep(int sec) {
    try {TimeUnit.SECONDS.sleep(sec); }//Задержка в sec сек
    catch (InterruptedException e) {e.printStackTrace(); }
}
//--------------------------------------------------------------------------
}//end public class RentPage

