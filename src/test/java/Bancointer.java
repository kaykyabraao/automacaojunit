import static org.junit.Assert.*;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;


import javax.imageio.ImageIO;
import org.apache.commons.io.FileUtils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class Bancointer {
	
	
	WebDriver driver;
	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "./Driver1/chromedriver.exe");
		//codigo para indicar o navegador
		
	
		driver = new ChromeDriver();
		
		driver.get("https://www.4devs.com.br/gerador_de_pessoas");
		driver.manage().window().maximize();
		Thread.sleep(2000);
		
		
		
		//driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL +"t");
        //driver.get("https://www.bancointer.com.br/");
		driver.findElement(By.xpath("//*[@id=\"bt_gerar_pessoa\"]")).click();
		Thread.sleep(2000);
		String nome = driver.findElement(By.id("nome")).getText();
		System.out.println(nome);
		String celular = driver.findElement(By.id("celular")).getText();
		System.out.println(celular);
		String email = driver.findElement(By.id("email")).getText();
		System.out.println(email);
		String cpf = driver.findElement(By.id("cpf")).getText();
		System.out.println(cpf);
		String data_nasc = driver.findElement(By.id("data_nasc")).getText();
		System.out.println(data_nasc);




		
		//driver.quit();
		// Abrir uma nova aba
				((JavascriptExecutor) driver).executeScript("window.open()");
				ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
				driver.switchTo().window(tabs.get(1));
				driver.get("https://www.bancointer.com.br/");
		//comando para chamar o driver pra dentro do before
		/*
		try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_T);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyRelease(KeyEvent.VK_T);
        } catch (AWTException ex) {
            throw new WebDriverException("Erro ao digitar CTRL + T", ex);
        }

        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        List<String> windowHandles = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(windowHandles.get(1));
        */
		driver.get("https://www.bancointer.com.br/");
		//driver.switchTo().window(driver.getWindowHandles().toArray()[1].toString());
        //driver.get("https://www.bancointer.com.br/");
		//comando para maximizar a tela
		driver.manage().window().maximize();
		driver.findElement(By.id("onetrust-accept-btn-handler")).click();
		//comando para interagir com o elemento
		driver.findElement(By.cssSelector("#gatsby-focus-wrapper > div > header > section > div > div > div > div > div.styles__LogoNIcons-sc-1gbjc3e-6.gjJzHM > div.styles__SearchNFlags-sc-1gbjc3e-8.yVPnY > div.styles__ButtonsWrapper-sc-1gbjc3e-9.PKrxs > button:nth-child(1)")).click();
		Thread.sleep(3000);
		driver.findElement(By.name("nome")).sendKeys(nome);
		driver.findElement(By.id("celular")).sendKeys(celular);
		driver.findElement(By.id("email")).sendKeys(email);
		driver.findElement(By.id("cpf")).sendKeys(cpf);
		driver.findElement(By.id("dataNascimento")).sendKeys(data_nasc);
		driver.findElement(By.cssSelector("body > div.style__ModalContent-wuavw4-0.hOXgJK > div.style__Container-sc-138k8xa-0.dlLgSU.d-flex.align-items-center > div > div:nth-child(2) > form > div > div:nth-child(6) > div > label")).click();
	    driver.findElement(By.cssSelector("body > div.style__ModalContent-wuavw4-0.hOXgJK > div.style__Container-sc-138k8xa-0.dlLgSU.d-flex.align-items-center > div > div:nth-child(2) > form > div > div.col-12.text-center > button")).click();
	}

	@After
	public void tearDown() throws Exception {
		Robot robot = new Robot();
	      Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
	      BufferedImage screenshot = robot.createScreenCapture(screenRect);
	      ImageIO.write(screenshot, "png", new File("screenshot.png"));
	      String currentDirectory = System.getProperty("user.dir");
	      TakesScreenshot ts = (TakesScreenshot) driver;
	      File source = ts.getScreenshotAs(OutputType.FILE);
	      FileUtils.copyFile(source, new File("./screenshots/homePageLoad.png"));
	      
		driver.quit();
		
		
	}

	@Test
	public void test() throws InterruptedException {
		Thread.sleep(3000);
		String texto = driver.findElement(By.cssSelector("body > div.style__ModalContent-wuavw4-0.hOXgJK > div.style__Container-sc-138k8xa-0.dlLgSU.d-flex.align-items-center.sent > div > p")).getText();
	    System.out.println(texto);
	    assertEquals("Prontinho! Recebemos os seus dados.", texto);
	}

}