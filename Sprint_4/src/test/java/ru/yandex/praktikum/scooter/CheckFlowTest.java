package ru.yandex.praktikum.scooter;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.yandex.praktikum.scooter.pageobjects.ScooterHomePage;
import ru.yandex.praktikum.scooter.pageobjects.ScooterPersonPage;
import ru.yandex.praktikum.scooter.pageobjects.ScooterRentPage;
import java.util.concurrent.TimeUnit;

// Класс с автотестом проверки "Всего флоу позитивного сценария"
@RunWith(Parameterized.class)
public class CheckFlowTest {
    private final String name;
    private final String surname;
    private final String address;
    private final String telephone;
    private final String date;
    private WebDriver driver;

    //-------Конструктор class CheckFlow---------------------
    public CheckFlowTest(String name, String surname, String address, String telephone, String date) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.telephone = telephone;
        this.date = date;
    }//end Конструктор

    //--------------------------------------------------------------------------------------
    // Метод для передачи(получения) тестовых данных в конструктор public CheckFlowTest(...)
    @Parameterized.Parameters(name = "Тест Flow-{1} {0} Дата: {4}")
    public static Object[] getParameters() {
        return new Object[][]{
                {"Владимир", "Иванов", "Москва, Доватора 27-4-32", "75465768798", "23.02.2023"},
                {"Сергей", "Никитин", "Сочи, Усиевича ул., дом 46, кв. 98", "86479346846", "08.03.2023"},
        };
    }//end public static Object[]

    //------------------------------------------------------------------------
    @Test
    //Тест проверки "Всего флоу позитивного сценария
    public void checkAllFlow() {
        // драйвер для браузера Chrome
        driver = new ChromeDriver();
        // драйвер для браузера Firefox
        //driver = new FirefoxDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");

        // создай объект класса главной (начальной) страницы Самокат
        ScooterHomePage objScooterHomePage = new ScooterHomePage(driver);
        // создай объект класса страницы "Для кого самокат"
        ScooterPersonPage objScooterPersonPage = new ScooterPersonPage(driver);
        // создай объект класса страницы ""Про аренду""
        ScooterRentPage objScooterRentPage = new ScooterRentPage(driver);

    //==============  1 - Тестируем ЗАКАЗ по кнопке "Заказать"  вверху справа  =========================
        // Нажимаем кнопку "Заказать" в верху справа на главной странице
        objScooterHomePage.clickOrderTop();
        timesleep(1);//Задержка выполнения теста
    //--------------------------------------------------------------------------------------
        //заполняем форму заказа данными о Заказчике
        objScooterPersonPage.setPerson(name, surname, address, telephone);
        timesleep(5);//Задержка выполнения теста
    //---------------------------------------------------------------------------------------
        //Заполнякм форму Аренды самоката и подтверждаем Заказ
        objScooterRentPage.setRentDate(date);
        timesleep(5);//Задержка выполнения теста
    }//end public void checkAllFlow()

    //=========================================================================================
    @Test
    //Тест проверки "Всего флоу позитивного сценария" по кнопке "Заказать" в центре снизу страницы
    public void checkAllFlow_2() {
        // драйвер для браузера Chrome
        driver = new ChromeDriver();
        // драйвер для браузера Firefox
        //driver = new FirefoxDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");

        // создай объект класса главной (начальной) страницы Самокат
        ScooterHomePage objScooterHomePage = new ScooterHomePage(driver);
        // создай объект класса страницы "Для кого самокат"
        ScooterPersonPage objScooterPersonPage = new ScooterPersonPage(driver);
        // создай объект класса страницы ""Про аренду""
        ScooterRentPage objScooterRentPage = new ScooterRentPage(driver);
    //============== 2 - Тестируем ЗАКАЗ по кнопке "Заказать"  в центре снизу страницы   ============================
        //Нажимаем кнопку "Заказать" в центре снизу на главной странице
        objScooterHomePage.clickOrderBottom();
        timesleep(2);//Задержка выполнения теста
    //--------------------------------------------------------------------------------------
        //заполняем форму заказа данными о Заказчике
        objScooterPersonPage.setPerson(name, surname, address, telephone);
        timesleep(5);//Задержка выполнения теста
    //---------------------------------------------------------------------------------------
        //Заполнякм форму Аренды самоката
        objScooterRentPage.setRentDate(date);
        timesleep(3);//Задержка выполнения теста
    //----------------------------------------------------------------------
    }//end public void checkAllFlow()

    //=============================================================================
    @After //Закрываем браузер
    public void teardown() {
        // Закрой браузер
        driver.quit();
    }

    //-----------------------------------------------------------------
    //Задержка для визуализации работы теста на экране
    public void timesleep(int sec) {
        try {
            TimeUnit.SECONDS.sleep(sec);
        }//Задержка в sec сек
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
//-------------------------------------------------------------
}//end public class CheckFlow


