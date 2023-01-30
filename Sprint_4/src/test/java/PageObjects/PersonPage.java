package PageObjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

// Класс страницы "Для кого самокат"
public class PersonPage {
    private WebDriver driver;

    //Локатор поля ввода "Имя"
    //$x(".//input[@placeholder='* Имя']")
    private By inputName = By.xpath(".//input[@placeholder='* Имя']");

    //Локатор поля ввода "Фамилия"
    //$x(".//input[@placeholder='* Фамилия']")
    private By inputSurname = By.xpath(".//input[@placeholder='* Фамилия']");

    //Локатор поля ввода "Адрес: куда привезти заказ"
    //$x(".//input[@placeholder='* Адрес: куда привезти заказ']")
    private By inputAddress = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");

    //Локатор поля выбора "Станция метро"
    //$x(".//input[@placeholder='* Станция метро']")
    private By inputMetro = By.xpath(".//input[@placeholder='* Станция метро']");

    //Локатор поля ввода "Телефон: на него позвонит курьер"
    //$x(".//input[@placeholder='* Телефон: на него позвонит курьер']")
    private By inputTelephone = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");

    //Локатор кнопки "Далее"
    //$x(".//div[@class = 'Order_NextButton__1_rCA']/button[@class='Button_Button__ra12g Button_Middle__1CSJM']")
    private By buttonNextToRent = By.xpath(".//div[@class = 'Order_NextButton__1_rCA']/button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

//-------------------------------------------------------------------------
    //Конструктор класса HomePageScooter
    public PersonPage(WebDriver driver) {
        this.driver = driver;
    }
//-----------------------------------------------------------------------------
 //Метод ввода имени Заказчика самоката
    private void setName(String name) {
        new WebDriverWait(driver, Duration.ofSeconds(100))
                .until(ExpectedConditions.visibilityOfElementLocated(inputName));
        driver.findElement(inputName).clear();
        driver.findElement(inputName).sendKeys(name);
    }
//---------------------------------------------------------------------------
//Метод ввода фамилии Заказчика самоката
    private void setSurname(String Surname) {
    new WebDriverWait(driver, Duration.ofSeconds(100))
            .until(ExpectedConditions.visibilityOfElementLocated(inputSurname));
    driver.findElement(inputSurname).clear();
    driver.findElement(inputSurname).sendKeys(Surname);
}
//------------------------------------------------------------------------
//Метод ввода адреса Заказчика самоката
    private void setAddress(String Address) {
    new WebDriverWait(driver, Duration.ofSeconds(100))
            .until(ExpectedConditions.visibilityOfElementLocated(inputAddress));
    driver.findElement(inputAddress).clear();
    driver.findElement(inputAddress).sendKeys(Address);
}
//-------------------------------------------------------------------------
//Метод ввода телефона Заказчика самоката
    private void setTelephone(String Telephone) {
    new WebDriverWait(driver, Duration.ofSeconds(100))
            .until(ExpectedConditions.visibilityOfElementLocated(inputTelephone));
    driver.findElement(inputTelephone).clear();
    driver.findElement(inputTelephone).sendKeys(Telephone);
}
//-------------------------------------------------------------------------
//Метод ввода станции метро Заказчика самоката
    private void setMetro() {
        new WebDriverWait(driver, Duration.ofSeconds(100))
            .until(ExpectedConditions.visibilityOfElementLocated(inputMetro));
        driver.findElement(inputMetro).sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
 }
 //--------------------------------------------------------------------------
//Заполнение данных Заказчика и переход на форму данных аренды самоката
 public void setPerson(String name,String Surname,String Address,String Telephone) {
     new WebDriverWait(driver, Duration.ofSeconds(100))
             .until(ExpectedConditions.elementToBeClickable(buttonNextToRent));
     setName(name);//Имя
     setSurname(Surname);//Фамилия
     setAddress(Address);//Адрес доставки
     setMetro();//Станция метро
     setTelephone(Telephone);//Ntktajy

     timesleep(5);//Задержка выполнения теста

     //Ожидание доступности кнопки "Далее" для перехода на страницу заполнения данных аренды
     new WebDriverWait(driver, Duration.ofSeconds(100))
             .until(ExpectedConditions.elementToBeClickable(buttonNextToRent));

    // Нажимаем кнопку "Далее" для перехода на страницу заполнения данных аренды
     driver.findElement(buttonNextToRent).click();
 }
//------------------------------------------------------------------------------
    //Задержка для визуализации работы теста на экране
    public void timesleep(int sec) {
        try {TimeUnit.SECONDS.sleep(sec); }//Задержка в sec сек
        catch (InterruptedException e) {e.printStackTrace(); }
    }
}// end public class PersonPage
