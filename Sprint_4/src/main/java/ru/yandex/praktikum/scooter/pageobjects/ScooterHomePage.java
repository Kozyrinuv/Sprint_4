package ru.yandex.praktikum.scooter.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

// Класс главной (начальной) страницы Самокат
public class ScooterHomePage {
    private final WebDriver driver;

    private final By[] dropdownList;//Массив локаторов выпадающих списков "Вопросы о важном"
    private final By[] dropdownListText;//Массив локаторов текстов выпадающих списков "Вопросы о важном"

    //Локатор надписи "Самокат на пару дней" на главной странице
    //$x(".//div[@class='Home_Header__iJKdX']")
    private final By homePageTitle = By.className("Home_Header__iJKdX");

    //Локатор кнопки "Заказать" в правом верхнем углу
    //$x(".//div/button[@class='Button_Button__ra12g']")
    private final By orderTopButton = By.className("Button_Button__ra12g");

    //Локатор кнопки "Заказать" в центре снизу
    //$x(".//div[@class='Home_FinishButton__1_cWm']/button")
    private final By orderBottomButton = By.xpath(".//div[@class='Home_FinishButton__1_cWm']/button");

    //Локатор кнопки "Статус заказа" в правом верхнем углу
    //$x(".//div/button[@class='Header_Link__1TAG7']")
    private final By orderStatusButton = By.className("Header_Link__1TAG7");

    //Локаторы выпадающих списков "Вопросы о важном"
    //$x(".//div[@id='accordion__heading-0']")
    //$x(".//div[@id='accordion__heading-7']")
    private final By dropdownList0 = By.id("accordion__heading-0");
    private final By dropdownList1 = By.id("accordion__heading-1");
    private final By dropdownList2 = By.id("accordion__heading-2");
    private final By dropdownList3 = By.id("accordion__heading-3");
    private final By dropdownList4 = By.id("accordion__heading-4");
    private final By dropdownList5 = By.id("accordion__heading-5");
    private final By dropdownList6 = By.id("accordion__heading-6");
    private final By dropdownList7 = By.id("accordion__heading-7");

    //Локаторы текстов выпадающих списков "Вопросы о важном"
    //$x(".//div[@id='accordion__panel-0']/p")
    //$x(".//div[@id='accordion__panel-7']/p")
    private final By dropdownListText0 = By.id("accordion__panel-0");
    private final By dropdownListText1 = By.id("accordion__panel-1");
    private final By dropdownListText2 = By.id("accordion__panel-2");
    private final By dropdownListText3 = By.id("accordion__panel-3");
    private final By dropdownListText4 = By.id("accordion__panel-4");
    private final By dropdownListText5 = By.id("accordion__panel-5");
    private final By dropdownListText6 = By.id("accordion__panel-6");
    private final By dropdownListText7 = By.id("accordion__panel-7");

    //Конструктор класса ScooterHomePage
    public ScooterHomePage(WebDriver driver) {
        this.driver = driver;

        //Массив локаторов выпадающих списков "Вопросы о важном"
        dropdownList = new By[]{dropdownList0, dropdownList1, dropdownList2, dropdownList3, dropdownList4, dropdownList5, dropdownList6, dropdownList7};

        //Массив локаторов текстов выпадающих списков "Вопросы о важном"
        dropdownListText = new By[]{dropdownListText0, dropdownListText1, dropdownListText2, dropdownListText3, dropdownListText4, dropdownListText5, dropdownListText6, dropdownListText7};
    }//end Конструктор ScooterHomePage

    //------------------------------------------------------------
    //Методы нажатия на стрелку выпадающего списка
    private void clickDropdownList(By dropdownList) {
        driver.findElement(dropdownList).click();
    }

    //-------------------------------------------------------------
    //Методы получения текста ответа на вопрос выпадающего списка
    private String getDropdownListText(By dropdownListText) {
        return driver.findElement(dropdownListText).getText();
    }

    //-----------------------------------------------------------------
    // метод  чтения текста i-го элемента выпадающего списка (считаем эл-ты с 0)
    public String getListAnswer(int index) {
        //Прокрутка до i-го элемента выпадающего списка
        WebElement element = driver.findElement(dropdownList[index]);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);

        //Ожидание доступности нажатия i-го элемента списка
        new WebDriverWait(driver, Duration.ofSeconds(100))
                .until(ExpectedConditions.elementToBeClickable(dropdownList[index]));

        //Нажимаем на стрелку i-го элемента списка
        clickDropdownList(dropdownList[index]);

        //Ожидание видимости текста ответа
        new WebDriverWait(driver, Duration.ofSeconds(100))
                .until(ExpectedConditions.visibilityOfElementLocated(dropdownListText[index]));

        //Возвращаем строку текста ответа на вопрос i-го элемента выпадающего списка
        return getDropdownListText(dropdownListText[index]);

    }//end public void checkListQuestion

    //=================Методы нажатия кнопок "Заказать" для теста Flow==============================================
    //Метод нажатия на кнопку "Заказать" в правом верхнем углу
    public void clickOrderTop() {
        driver.findElement(orderTopButton).click();
    }

    //----------------------------------------------------------------------------------------
    //Метод нажатия на кнопку "Заказать" в центре страницы снизу
    public void clickOrderBottom() {
        //Прокрутка до кнопки "Заказать"
        WebElement element = driver.findElement(orderBottomButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        timesleep(5);//Задержка выполнения теста

        //Нажимаем на кнопку
        driver.findElement(orderBottomButton).click();
    }

    //-------------------------------------------------------------------------------------
    //Задержка для визуализации работы теста на экране
    public void timesleep(int sec) {
        try {
            TimeUnit.SECONDS.sleep(sec);
        }//Задержка в sec сек
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
//-------------------------------------------------------------------------------------------
}//eend class HomePageScooter



