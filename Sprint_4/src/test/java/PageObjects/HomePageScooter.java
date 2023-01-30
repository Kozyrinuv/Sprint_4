package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import static org.junit.Assert.assertEquals;

// Класс главной (начальной) страницы Самокат
public class HomePageScooter {

    private WebDriver driver;

    private int size = 8;//Количество вопросов на главной форме
    private String[] listAnswer;//Массив текстов ответов на главной форме
    private By[] dropdownList;//Массив локаторов выпадающих списков "Вопросы о важном"
    private By[] dropdownListText;//Массив локаторов текстов выпадающих списков "Вопросы о важном"


    //Локатор надписи "Самокат на пару дней" на главной странице
    //$x(".//div[@class='Home_Header__iJKdX']")
    private By homePageTitle = By.className("Home_Header__iJKdX");

    //Локатор кнопки "Заказать" в правом верхнем углу
    //$x(".//div/button[@class='Button_Button__ra12g']")
    private By orderTopButton = By.className("Button_Button__ra12g");

    //Локатор кнопки "Заказать" в центре снизу
    //$x(".//div[@class='Home_FinishButton__1_cWm']/button")
    private By orderBottomButton = By.xpath(".//div[@class='Home_FinishButton__1_cWm']/button");


    //Локатор кнопки "Статус заказа" в правом верхнем углу
    //$x(".//div/button[@class='Header_Link__1TAG7']")
    private By orderStatusButton = By.className("Header_Link__1TAG7");

    //Локаторы выпадающих списков "Вопросы о важном"
    //$x(".//div[@id='accordion__heading-0']")
    //$x(".//div[@id='accordion__heading-7']")
    private By dropdownList0 = By.id("accordion__heading-0");
    private By dropdownList1 = By.id("accordion__heading-1");
    private By dropdownList2 = By.id("accordion__heading-2");
    private By dropdownList3 = By.id("accordion__heading-3");
    private By dropdownList4 = By.id("accordion__heading-4");
    private By dropdownList5 = By.id("accordion__heading-5");
    private By dropdownList6 = By.id("accordion__heading-6");
    private By dropdownList7 = By.id("accordion__heading-7");


    //Локаторы текстов выпадающих списков "Вопросы о важном"
    //$x(".//div[@id='accordion__panel-0']/p")
    //$x(".//div[@id='accordion__panel-7']/p")
    private By dropdownListText0 = By.id("accordion__panel-0");
    private By dropdownListText1 = By.id("accordion__panel-1");
    private By dropdownListText2 = By.id("accordion__panel-2");
    private By dropdownListText3 = By.id("accordion__panel-3");
    private By dropdownListText4 = By.id("accordion__panel-4");
    private By dropdownListText5 = By.id("accordion__panel-5");
    private By dropdownListText6 = By.id("accordion__panel-6");
    private By dropdownListText7 = By.id("accordion__panel-7");


    //Конструктор класса HomePageScooterHomePageScooter
    public HomePageScooter(WebDriver driver) {
        this.driver = driver;

        //Массив локаторов выпадающих списков "Вопросы о важном"
        dropdownList = new By[]{dropdownList0,dropdownList1,dropdownList2,dropdownList3,dropdownList4,dropdownList5,dropdownList6,dropdownList7};

        //Массив локаторов текстов выпадающих списков "Вопросы о важном"
        dropdownListText = new By[]{dropdownListText0,dropdownListText1,dropdownListText2,dropdownListText3,dropdownListText4,dropdownListText5,dropdownListText6,dropdownListText7};

        listAnswer = new String[size];//Массив текстов ответов на главной форме
        //Заполняем массив текстов ответов главной формы
        listAnswer[0] = "Сутки — 400 рублей. Оплата курьеру — наличными или картой.";
        listAnswer[1] = "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.";
        listAnswer[2] = "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.";
        listAnswer[3] = "Только начиная с завтрашнего дня. Но скоро станем расторопнее.";
        listAnswer[4] = "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.";
        listAnswer[5] = "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.";
        listAnswer[6] = "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.";
        listAnswer[7] = "Да, обязательно. Всем самокатов! И Москве, и Московской области.";
    }//end Конструктор HomePageScooter
//------------------------------------------------------------
    //Методы нажатия на стрелку выпадающего списка
    private void clickDropdownList(By dropdownList){
        driver.findElement(dropdownList).click();
    }
//-------------------------------------------------------------
    //Методы получения текста ответа на вопрос выпадающего списка
    private String getDropdownListText(By dropdownListText) {
        return driver.findElement(dropdownListText).getText();
    }
 //-----------------------------------------------------------------
// метод  проверки текста i-го элемента выпадающего списка (считаем эл-ты с 0)
    public void checkListAnswer(int index) {
        //Прокрутка до i-го элемента выпадающего списка
        WebElement element = driver.findElement(dropdownList[index]);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);

        //Ожидание доступности нажатия i-го элемента списка
        new WebDriverWait(driver, Duration.ofSeconds(100))
                .until(ExpectedConditions.elementToBeClickable(dropdownList[index]));

        //Нажимаем на стрелку i-го элемента списка
        clickDropdownList(dropdownList[index]);

        //Ожидание видимости текста ответа
        new WebDriverWait(driver, Duration.ofSeconds(100))
                .until(ExpectedConditions.visibilityOfElementLocated(dropdownListText[index]));

        //Сравниваем тексты для i-го элемента списка
        assertEquals(listAnswer[index],getDropdownListText(dropdownListText[index]) );

    }//end public void checkListQuestion

//=================Методы нажатия кнопок "Заказать" для теста Flow==============================================
    //Метод нажатия на кнопку "Заказать" в правом верхнем углу
    public void clickOrderTop(){
    driver.findElement(orderTopButton).click();
}
//----------------------------------------------------------------------------------------
    //Метод нажатия на кнопку "Заказать" в центре страницы снизу
    public void clickOrderBottom(){
        //Прокрутка до кнопки "Заказать"
        WebElement element = driver.findElement(orderBottomButton);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        timesleep(5);//Задержка выполнения теста

        //Нажимаем на кнопку
        driver.findElement(orderBottomButton).click();
}
//-------------------------------------------------------------------------------------
    //Задержка для визуализации работы теста на экране
    public void timesleep(int sec) {
        try {TimeUnit.SECONDS.sleep(sec); }//Задержка в sec сек
        catch (InterruptedException e) {e.printStackTrace(); }
    }
//-------------------------------------------------------------------------------------------
}//end class HomePageScooter


