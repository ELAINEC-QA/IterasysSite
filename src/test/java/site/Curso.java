// 1- pacote
package site;

//2-bibliotecas

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

// 3- classe
public class Curso<webDrive> {
    // 3.1- atributos var
    WebDriver driver;
    String url;



    //3.2- metodos ou funçoes

    @Before
    public void iniciar() {
        url = "https:www.iterasys.com.br";
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe"); // onde está o driver
        driver = new ChromeDriver();// instanciar o selenium como um controlador do chrome
        //driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
    }

    @After
    public void finalizar () {
        driver.quit(); // finalizar
    }


    @Test
    public void consultarTesteLink () throws InterruptedException {
        driver.get(url);
        //driver.findElement(By.id("searchtext")).sendKeys("testeLink"+ Keys.ENTER);
        driver.findElement(By.id("searchtext")).sendKeys("testLink");
       // Thread.sleep(2000);
        driver.findElement(By.id("btn_form_search")).click();
       Thread.sleep(2000);
        driver.findElement(By.cssSelector("span.comprar")).click();

        // validar nome do curso
        String resultadoEsperado= "TestLink";
        String resultadoAtual= driver.findElement(By.cssSelector("span.item-title")).getText();
        assertEquals(resultadoEsperado,resultadoAtual);

        // validar o preço

       // assertEquals("R$ 79,99",driver.findElement(By.cssSelector("span.new-price")).getText());

       assertEquals("SUBTOTAL R$ 79,99", driver.findElement(By.cssSelector("div.subtotal")).getText());
       //assertEquals("ou em 12 x de R$ 351,42 * no cartão", driver.findElement(By.cssSelector("div.ou-parcele")).getText());
        // validar o valor das parcelas
        assertTrue( driver.findElement(By.cssSelector("div.ou-parcele")).getText().contains("ou em 12 x de R$ 8,03"));
    }




}
