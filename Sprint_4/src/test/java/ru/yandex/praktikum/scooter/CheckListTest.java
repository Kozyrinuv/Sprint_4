package ru.yandex.praktikum.scooter;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.yandex.praktikum.scooter.pageobjects.ScooterHomePage;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.assertEquals;

// Класс с автотестом проверки "Выпадающий список в разделе «Вопросы о важном»"
@RunWith(Parameterized.class)
public class CheckListTest {
    private final int size = 8;//Количество вопросов на главной форме
    private final int indexQuestion;//Номер вопроса (считаем с 0)
    private final String[] listAnswer = new String[size];//Массив текстов ответов на главной форме
    private WebDriver driver;

    //-------Конструктор class CheckListTest---------------------
    public CheckListTest(int indexQuestion) {
        this.indexQuestion = indexQuestion;
        //Заполняем массив текстов ответов главной формы
        listAnswer[0] = "Сутки — 400 рублей. Оплата курьеру — наличными или картой.";
        listAnswer[1] = "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.";
        listAnswer[2] = "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.";
        listAnswer[3] = "Только начиная с завтрашнего дня. Но скоро станем расторопнее.";
        listAnswer[4] = "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.";
        listAnswer[5] = "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.";
        listAnswer[6] = "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.";
        listAnswer[7] = "Да, обязательно. Всем самокатов! И Москве, и Московской области.";
    }//end Конструктор

    //--------------------------------------------------------------
    // Метод для передачи(получения) тестовых данных в конструктор public CheckListTest(...)
    @Parameterized.Parameters(name = "Номер вопроса = {0}")
    public static Object[] getParameters() {
        return new Object[][]{
                {0}, {1}, {2}, {3}, {4}, {5}, {6}, {7},
        };
    }//end public static Object[]

    //----------------------------------------------------------------------
    @Test
    //Тест "Выпадающий список в разделе «Вопросы о важном»"
    public void checkListQuestion() {
        // драйвер для браузера Chrome
        driver = new ChromeDriver();
        // драйвер для браузера Firefox
        //driver = new FirefoxDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
        // создай объект класса главной (начальной) страницы Самокат
        ScooterHomePage objScooterHomePage = new ScooterHomePage(driver);
        //Читаем и сравниваем тексты для i-го элемента списка
        assertEquals(listAnswer[indexQuestion], objScooterHomePage.getListAnswer(indexQuestion));
        timesleep(4);//Задержка выполнения теста
    }//end public void checkListQuestion()

    //------------------------------------------------------
    @After //Закрываем браузер
    public void teardown() {
        // Закрой браузер
        driver.quit();
    }

    //----------------------------------------------------------
    //Задержка для визуализации работы теста на экране
    public void timesleep(int sec) {
        try {
            TimeUnit.SECONDS.sleep(sec);
        }//Задержка в sec сек
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

//-----------------------------------------------------------
}//end public class Praktikum


