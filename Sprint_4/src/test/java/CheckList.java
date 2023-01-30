import PageObjects.HomePageScooter;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.junit.Test;
import org.junit.After;
import java.util.concurrent.TimeUnit;

// Класс с автотестом проверки "Выпадающий список в разделе «Вопросы о важном»"
public class CheckList {

    private WebDriver driver;

    @Test
    //Тест "Выпадающий список в разделе «Вопросы о важном»"
    public void checkListQuestion() {
        // драйвер для браузера Chrome
        driver = new ChromeDriver();
        // драйвер для браузера Firefox
        //driver = new FirefoxDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
        // создай объект класса главной (начальной) страницы Самокат
        HomePageScooter objHomePageScooter = new HomePageScooter(driver);

        // метод  проверки текста 1-го элемента выпадающего списка
        objHomePageScooter.checkListAnswer(0);
        timesleep(3);//Задержка выполнения теста

        // метод  проверки текста 2-го элемента выпадающего списка
        objHomePageScooter.checkListAnswer(1);
        timesleep(3);//Задержка выполнения теста

        // метод  проверки текста 3-го элемента выпадающего списка
        objHomePageScooter.checkListAnswer(2);
        timesleep(3);//Задержка выполнения теста

        // метод  проверки текста 4-го элемента выпадающего списка
        objHomePageScooter.checkListAnswer(3);
        timesleep(3);//Задержка выполнения теста

        // метод  проверки текста 5-го элемента выпадающего списка
        objHomePageScooter.checkListAnswer(4);
        timesleep(3);//Задержка выполнения теста

        // метод  проверки текста 6-го элемента выпадающего списка
        objHomePageScooter.checkListAnswer(5);
        timesleep(3);//Задержка выполнения теста

        // метод  проверки текста 7-го элемента выпадающего списка
        objHomePageScooter.checkListAnswer(6);
        timesleep(3);//Задержка выполнения теста

        // метод  проверки текста 8-го элемента выпадающего списка
        objHomePageScooter.checkListAnswer(7);
        timesleep(3);//Задержка выполнения теста

    }//end public void checkActivity()

    @After //Закрываем браузер
    public void teardown() {
        // Закрой браузер
        driver.quit();
    }
//----------------------------------------------------------
    //Задержка для визуализации работы теста на экране
    public void timesleep(int sec) {
        try {TimeUnit.SECONDS.sleep(sec); }//Задержка в sec сек
        catch (InterruptedException e) {e.printStackTrace(); }
    }
//-----------------------------------------------------------
}//end public class Praktikum
