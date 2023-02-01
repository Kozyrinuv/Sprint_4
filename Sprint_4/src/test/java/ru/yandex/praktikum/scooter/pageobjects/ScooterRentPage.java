package ru.yandex.praktikum.scooter.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

// Класс страницы "Про аренду"
public class ScooterRentPage {
    private final WebDriver driver;

    //Локатор поля ввода Даты аренды
    //$x(".//input[@placeholder='* Когда привезти самокат']")
    private final By inputDateRental = By.xpath(".//input[@placeholder='* Когда привезти самокат']");

    //Локатор поля ввода Срока аренды
    //$x(".//div[@class='Dropdown-placeholder']")
    private final By inputPeriodRental = By.className("Dropdown-placeholder");

    //Локатор выпадающей строки поля ввода Срока аренды
    //$x(".//div[@class='Dropdown-option' and text() = 'трое суток']")
    private final By inputDropdownPeriodRental = By.xpath(".//div[@class='Dropdown-option' and text() = 'трое суток']");

    //Локатор поля выбора "Черного" цвета самоката
    //$x(".//input[@id='black']")
    private final By inputColorBlack = By.id("black");

    //Локатор поля выбора "Серого" цвета самоката
    //$x(".//input[@id='grey']")
    private final By inputColorGrey = By.id("grey");

    //Локатор поля ввода "Комментарий для курьера"
    //$x(".//input[@placeholder='Комментарий для курьера']")
    private final By inputComent = By.xpath(".//input[@placeholder='Комментарий для курьера']");

    //Локатор кнопки "Заказать" под полями ввода данных аренды
    //$x(".//div[@class='Order_Content__bmtHS']/div/button[@class='Button_Button__ra12g Button_Middle__1CSJM']")
    private final By buttonDoRent = By.xpath(".//div[@class='Order_Content__bmtHS']/div/button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    //Локатор формы подтверждения заказа "Хотите оформить заказ?"
    //$x(".//div[@class='Order_Modal__YZ-d3']")
    private final By formDoRent = By.className("Order_Modal__YZ-d3");

    //Локатор кнопки "ДА" формы подтверждения заказа "Хотите оформить заказ?"
    //$x(".//div[@class='Order_Modal__YZ-d3']/div/button[@class='Button_Button__ra12g Button_Middle__1CSJM']")
    private final By buttonYesRent = By.xpath(".//div[@class='Order_Modal__YZ-d3']/div/button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    //Локатор кнопки "Посмотреть статус" на форме подтверждения окончания оформления заказа "Заказ оформлен"
    //$x(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text() = 'Посмотреть статус']")
    private final By buttonViewStatus = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text() = 'Посмотреть статус']");

    //Локатор кнопки "Отменить заказ" на форме  "Заказ оформлен"
    //$x(".//button[text()='Отменить заказ']")
    private final By buttonCancelOrder = By.xpath(".//button[text()='Отменить заказ']");

    //-----------------------------------------------------------------------------------
    //Конструктор класса ScooterRentPage
    public ScooterRentPage(WebDriver driver) {
        this.driver = driver;
    }

    //----------------------------------------------------------------------------------
    //  *************  private методы (шаги) для заполнения формы аренды самоката  **********
    //1 - Заполняем дату заказа
    private void setDate(String date) {
        //Локатор поля даты элемента Календарь формируем динамически по знчению поля Даты заказа (String date)
        //$x(".//div[@class='react-datepicker__week']/div[@class='react-datepicker__day react-datepicker__day--023 react-datepicker__day--selected']")
        String dateOrder = "0" + date.substring(0, 2);
        String xpathIsx = ".//div[@class='react-datepicker__week']/div[contains(@class,'023')]";//исходный локатор
        String xpath = xpathIsx.replace("023", dateOrder);//Замена даты в пути
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
    }

    //------------------------------------------------------------------------------
    //2 - Заполняем поле сроков аренды
    private void setPeriod() {
        //Ждем доступность поля сроков аренды
        new WebDriverWait(driver, Duration.ofSeconds(50))
                .until(ExpectedConditions.elementToBeClickable(inputPeriodRental));
        //Нажать на поле ввода Срока аренды
        driver.findElement(inputPeriodRental).click();
        timesleep(1);//Задержка выполнения теста
        //выбрать из выпадающего списка срок аренды
        driver.findElement(inputDropdownPeriodRental).click();
        timesleep(1);//Задержка выполнения теста
    }

    //----------------------------------------------------------------
    //3 - Задаем цвета самоката к заказу
    private void setColorScooter() {
        driver.findElement(inputColorBlack).click();
        driver.findElement(inputColorGrey).click();
    }

    //---------------------------------------------------------------
    //4 - Задаем коментарий к заказу
    private void setComentRenta() {
        driver.findElement(inputComent).sendKeys("Комментарий к заказу самоката");
    }

    //------------------------------------------------------------------
    //5 - нажимаем кнопку "Заказать" самокат
    private void clickButtonOrder() {
        //Ждем доступность кнопки "Заказать"
        new WebDriverWait(driver, Duration.ofSeconds(50))
                .until(ExpectedConditions.elementToBeClickable(buttonDoRent));
        //Нажать кнопку Заказать
        driver.findElement(buttonDoRent).click();
    }

    //------------------------------------------------------------------
    //6 - нажимаем кнопку "ДА" на форме подтверждениязаказа
    private void clickButtonYes() {
        //Ждем видимость формы подтверждения заказа
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(formDoRent));
        //Ждем доступность кнопки "ДА" формы подтверждения заказа
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(buttonYesRent));
        //Нажать на кнопку "ДА" формы подтверждения заказа
        driver.findElement(buttonYesRent).click();
    }

    //-------------------------------------------------------------------
    //7 - нажимаем кнопку "Посмотреть статус" формы подтверждения окончания оформления заказа "Заказ оформлен"
    private void clickButtonStatus() {
        //Ждем доступность кнопки "Посмотреть статус" формы подтверждения окончания оформления заказа "Заказ оформлен"
        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.elementToBeClickable(buttonViewStatus));
        //Нажать на кнопку "Посмотреть статус" формы подтверждения окончания оформления заказа "Заказ оформлен"
        driver.findElement(buttonViewStatus).click();
        //Ждем доступность кнопки "Отменить заказ" формы "Заказ оформлен"
        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.elementToBeClickable(buttonCancelOrder));
    }

    //--------------------------------------------------------------------------------
    //*******  Итоговый public Метод Заполняк формы Аренды самоката и подтверждаем Заказ ********
    public void setRentDate(String date) {
        setDate(date);//Заполняем дату заказа
        setPeriod();//Заполняем поле сроков аренды
        setColorScooter();//Задаем цвета самоката
        setComentRenta();//Задаем коментарий к заказу
        timesleep(3);//Задержка выполнения теста
        clickButtonOrder();//Нажимаем кнопку "Заказать" самокат
        timesleep(3);//Задержка выполнения теста
        clickButtonYes();//Нажимаем кнопку "ДА" на форме подтверждениязаказа
        timesleep(3);//Задержка выполнения теста
        clickButtonStatus();//Нажимаем кнопку "Посмотреть статус"
    }

    //---------------------------------------------------------------------------
//Задержка для визуализации работы теста на экране
    public void timesleep(int sec) {
        try {
            TimeUnit.SECONDS.sleep(sec);
        }//Задержка в sec сек
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
//--------------------------------------------------------------------------
}//end public class RentPage



