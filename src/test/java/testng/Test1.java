package testng;

import com.codeborne.selenide.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;
import static testng.Locators.*;

public class Test1 {

    @Test
    public void test_Login() throws InterruptedException {
        //Configuration.browser = "firefox";  //browser istediğim browser olarak değiştirebilirim
        //hiçbir şey belirtmezsek default olarak chrome alır
        //WebDriver driver = WebDriverRunner.getWebDriver();
        Configuration.browserSize = "800x700";  //maximize etme

       //WebDriverRunner.setWebDriver();  artık ben kendi istediğim driverı göndermek istiyorum dediğimde
        //WebDriver driver = WebDriverRunner.getWebDriver(); //otomatikman drivera ulaşmış oluruz. artık dilediğimiz gibi selenium'a ulaşmış oluruz.
        Configuration.headless = true; // chrome açılmadan backround'da çalışması. chrome yine ram da çalışır. ekranda bazı şeyleri beklemediği
        //için daha hızlı çalışacaktır.
        Configuration.holdBrowserOpen = true; // browser açık kalsın mı? en son chrome kapanmaz artık
        open(url);
        $(lMyAccount).shouldBe(Condition.visible).click();  //shouldBe -- olmalı  condition --koşul
        $(lLoginLink).shouldBe(Condition.visible).click();
        $(lemail).shouldBe(Condition.visible).setValue("testngkurs@gmail.com");  //setvalue ---> değerini ata
        //setValue yerine sendkeys de diyebiliriz fakat setValue öncesi varsa onu siler ve yenisini yazar
        $(lInputPassword).shouldBe(Condition.enabled).setValue("testngkurs");
        $(lSubmitButton).click();

        // $$ -->driver.findelements() demek
        $$("ul.nav.navbar-nav>li").shouldBe(CollectionCondition.sizeGreaterThan(7));
        //Bunların size ı 7'den büyük olmalı

        $$("ul.nav.navbar-nav>li")  //default olarak css alır.
                .filter((Condition.matchText(".*Comp.*")))  //filtrele içinde comp olanlar
                // . karakter demek * demek comp un önünde sıfır veya daha fazla karakter olabilir. hiç olmayabilir de
                //herhangi bir yerinde comp geçecek. önünde sonunda sıfır veya daha fazla karsakter olabilir.
                // ".*Comp" --> comp ile bitecek anlamına gelir
                .shouldBe(CollectionCondition.sizeGreaterThan(1));


        //hiçbir şey yazmazsam css alacak.
        //$x("");  dersem xpath olduğunu söylemiş olurum
        $(By.xpath("//a[text()='Contact Us']")).scrollIntoView(true).screenshot();
        //istediğim elementin de screenshot'unu alabiliyorum. kendisi her hata gördüğünde ekran görüntüsü alacaktır.

        By lmenu = By.cssSelector("ul.nav.navbar-nav>li");
        int num = $$(lmenu).size();

        for (int i = 0; i < num; i++) {
            $(lmenu, i).hover();
            Selenide.refresh(); //her refresh den önce elemantı tekrardan buluyorum. i. sini istiyorum.
            
        }
        for (SelenideElement element : $$("ul.nav.navbar-nav>li")) {
            element.hover();
            Selenide.refresh(); //bu şekilde staleelement hatası verir.
            Thread.sleep(100);
        }
    }
    @Test
    public void test2(){
        open(url);
        $("input[name='search']").shouldBe(Condition.visible).setValue("mac").pressEnter();
        ElementsCollection list = $$("div.caption h4"); //bütün elementleri listeye at dedim. array'e attım
        int size = list.size(); //kaç tane ürün listelenmiş ona baktım.
        list.filter(Condition.matchText(".*mac.*")).shouldHave(CollectionCondition.size(size)); //sholdBe = shouldHave aynı şey
        //bu listede içinde mac olanları seç, seçtiğinde size' yukardaki size ile aynı olmalı. yani hepsinde mac geçiyor olsun
        $("select[name='category_id']").selectOption(3); //ya da string olanı seçebilirim
        $("#content #button-search").click();
        sleep(300);
    }
}
