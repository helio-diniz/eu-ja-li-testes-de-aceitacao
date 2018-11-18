package org.coursera.eujalitestedeaceitacao.test;

import java.util.regex.Pattern;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.BeforeClass;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;
import org.junit.AfterClass;
import org.junit.Before;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class LivroTest {
	private JdbcDatabaseTester jdt;
	private static WebDriver driver;
	private static String baseUrl;
	private boolean acceptNextAlert = true;
	private static StringBuffer verificationErrors = new StringBuffer();

	@BeforeClass
	public static void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\helio\\selenium-2.53.0\\chromedriver.exe");
		driver = new ChromeDriver();
		baseUrl = "http://localhost:8080/";
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	}

	@Before
	public void inicializaTeste() throws Exception {
		jdt = new JdbcDatabaseTester("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/portal-livros", "root", "");
		FlatXmlDataFileLoader loader = new FlatXmlDataFileLoader();
		jdt.setDataSet(loader.load("/inicio.xml"));
		jdt.onSetup();
	}	
	
	@Test
	public void whenLivroId1ThenTituloOQueEhDevOps() throws Exception {
		driver.get(baseUrl + "EuJaLi/");	    
	    driver.findElement(By.id("j_username")).clear();
	    driver.findElement(By.id("j_username")).sendKeys("jose.silva");
	    driver.findElement(By.id("j_password")).clear();
	    driver.findElement(By.id("j_password")).sendKeys("123");
	    driver.findElement(By.id("botaoAccessar")).click();
		
	    driver.findElement(By.id("frmHome:livros:0:j_idt44")).click();
		assertEquals(driver.findElement(By.id("frmLivro:titulo")).getText(), "");
		try {
			assertEquals(driver.findElement(By.id("frmLivro:titulo")).getAttribute("value"), "O que é DevOps?: Colaboração como caminho para entregar valor ao negócio");
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		assertEquals(driver.findElement(By.id("frmLivro:editora")).getText(), "");
		try {
			assertEquals(driver.findElement(By.id("frmLivro:editora")).getAttribute("value"), "KINDLE");
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		assertEquals(driver.findElement(By.id("frmLivro:estilo")).getText(), "");
		try {
			assertEquals(driver.findElement(By.id("frmLivro:estilo")).getAttribute("value"), "INFORMATICA");
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		assertEquals(driver.findElement(By.id("frmLivro:paginas")).getText(), "");
		try {
			assertEquals(driver.findElement(By.id("frmLivro:paginas")).getAttribute("value"), "26");
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		driver.findElement(By.id("frmLivro:anoPublicacao")).click();
		assertEquals(driver.findElement(By.id("frmLivro:anoPublicacao")).getText(), "");
		try {
			assertEquals(driver.findElement(By.id("frmLivro:anoPublicacao")).getAttribute("value"), "2016");
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		driver.findElement(By.xpath("//a[@id='j_idt17:sair']/span")).click();
		
	}
	
	
	@Test
	public void whenLivroId5ThenTituloScrumGestaoAgilParaProjetosDeSucesso() throws Exception {
		driver.get(baseUrl + "EuJaLi/");	    
	    driver.findElement(By.id("j_username")).clear();
	    driver.findElement(By.id("j_username")).sendKeys("jose.silva");
	    driver.findElement(By.id("j_password")).clear();
	    driver.findElement(By.id("j_password")).sendKeys("123");
	    driver.findElement(By.id("botaoAccessar")).click();
		
	    driver.findElement(By.id("frmHome:livros:4:j_idt44")).click();
		assertEquals(driver.findElement(By.id("frmLivro:titulo")).getText(), "");
		try {
			assertEquals(driver.findElement(By.id("frmLivro:titulo")).getAttribute("value"), "Scrum: Gestão ágil para projetos de sucesso");
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		assertEquals(driver.findElement(By.id("frmLivro:editora")).getText(), "");
		try {
			assertEquals(driver.findElement(By.id("frmLivro:editora")).getAttribute("value"), "Casa do Código");
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		assertEquals(driver.findElement(By.id("frmLivro:estilo")).getText(), "");
		try {
			assertEquals(driver.findElement(By.id("frmLivro:estilo")).getAttribute("value"), "INFORMATICA");
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		assertEquals(driver.findElement(By.id("frmLivro:paginas")).getText(), "");
		try {
			assertEquals(driver.findElement(By.id("frmLivro:paginas")).getAttribute("value"), "297");
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		driver.findElement(By.id("frmLivro:anoPublicacao")).click();
		assertEquals(driver.findElement(By.id("frmLivro:anoPublicacao")).getText(), "");
		try {
			assertEquals(driver.findElement(By.id("frmLivro:anoPublicacao")).getAttribute("value"), "2015");
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		driver.findElement(By.xpath("//a[@id='j_idt17:sair']/span")).click();
	}
	
	
	@Test
	public void whenLivroId9ThenTituloRisotos() throws Exception {
		driver.get(baseUrl + "EuJaLi/");	    
	    driver.findElement(By.id("j_username")).clear();
	    driver.findElement(By.id("j_username")).sendKeys("jose.silva");
	    driver.findElement(By.id("j_password")).clear();
	    driver.findElement(By.id("j_password")).sendKeys("123");
	    driver.findElement(By.id("botaoAccessar")).click();
	    
	    driver.findElement(By.id("frmHome:livros:8:j_idt44")).click();
		assertEquals(driver.findElement(By.id("frmLivro:titulo")).getText(), "");
		try {
			assertEquals(driver.findElement(By.id("frmLivro:titulo")).getAttribute("value"), "Risotos");
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		assertEquals(driver.findElement(By.id("frmLivro:editora")).getText(), "");
		try {
			assertEquals(driver.findElement(By.id("frmLivro:editora")).getAttribute("value"), "Senac SP");
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		assertEquals(driver.findElement(By.id("frmLivro:estilo")).getText(), "");
		try {
			assertEquals(driver.findElement(By.id("frmLivro:estilo")).getAttribute("value"), "CULINARIA");
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		assertEquals(driver.findElement(By.id("frmLivro:paginas")).getText(), "");
		try {
			assertEquals(driver.findElement(By.id("frmLivro:paginas")).getAttribute("value"), "114");
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		driver.findElement(By.id("frmLivro:anoPublicacao")).click();
		assertEquals(driver.findElement(By.id("frmLivro:anoPublicacao")).getText(), "");
		try {
			assertEquals(driver.findElement(By.id("frmLivro:anoPublicacao")).getAttribute("value"), "2013");
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		driver.findElement(By.xpath("//a[@id='j_idt17:sair']/span")).click();
	}

	@Test
	public void whenLivroId14ThenTituloMemoriaPostumasDeBrasCubas() throws Exception {
		driver.get(baseUrl + "EuJaLi/");	    
	    driver.findElement(By.id("j_username")).clear();
	    driver.findElement(By.id("j_username")).sendKeys("jose.silva");
	    driver.findElement(By.id("j_password")).clear();
	    driver.findElement(By.id("j_password")).sendKeys("123");
	    driver.findElement(By.id("botaoAccessar")).click();
	    
	    driver.findElement(By.linkText("2")).click();
	    driver.findElement(By.id("frmHome:livros:13:j_idt44")).click();
	    
		assertEquals(driver.findElement(By.id("frmLivro:titulo")).getText(), "");
		try {
			assertEquals(driver.findElement(By.id("frmLivro:titulo")).getAttribute("value"), "Memórias Póstumas de Brás Cubas");
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		assertEquals(driver.findElement(By.id("frmLivro:editora")).getText(), "");
		try {
			assertEquals(driver.findElement(By.id("frmLivro:editora")).getAttribute("value"), "Rafael Vidal");
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		assertEquals(driver.findElement(By.id("frmLivro:estilo")).getText(), "");
		try {
			assertEquals(driver.findElement(By.id("frmLivro:estilo")).getAttribute("value"), "LITERATURA CLASSICA");
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		assertEquals(driver.findElement(By.id("frmLivro:paginas")).getText(), "");
		try {
			assertEquals(driver.findElement(By.id("frmLivro:paginas")).getAttribute("value"), "218");
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		driver.findElement(By.id("frmLivro:anoPublicacao")).click();
		assertEquals(driver.findElement(By.id("frmLivro:anoPublicacao")).getText(), "");
		try {
			assertEquals(driver.findElement(By.id("frmLivro:anoPublicacao")).getAttribute("value"), "2016");
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		driver.findElement(By.xpath("//a[@id='j_idt17:sair']/span")).click();
	}
	
	@Test
	public void whenLivroId17ThenTituloQuincasBorba() throws Exception {
		driver.get(baseUrl + "EuJaLi/");	    
	    driver.findElement(By.id("j_username")).clear();
	    driver.findElement(By.id("j_username")).sendKeys("jose.silva");
	    driver.findElement(By.id("j_password")).clear();
	    driver.findElement(By.id("j_password")).sendKeys("123");
	    driver.findElement(By.id("botaoAccessar")).click();
	    
	    driver.findElement(By.linkText("2")).click();
	    driver.findElement(By.id("frmHome:livros:16:j_idt44")).click();
	    
		assertEquals(driver.findElement(By.id("frmLivro:titulo")).getText(), "");
		try {
			assertEquals(driver.findElement(By.id("frmLivro:titulo")).getAttribute("value"), "Quincas Borba");
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		assertEquals(driver.findElement(By.id("frmLivro:editora")).getText(), "");
		try {
			assertEquals(driver.findElement(By.id("frmLivro:editora")).getAttribute("value"), "Wohnrecht");
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		assertEquals(driver.findElement(By.id("frmLivro:estilo")).getText(), "");
		try {
			assertEquals(driver.findElement(By.id("frmLivro:estilo")).getAttribute("value"), "LITERATURA CLASSICA");
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		assertEquals(driver.findElement(By.id("frmLivro:paginas")).getText(), "");
		try {
			assertEquals(driver.findElement(By.id("frmLivro:paginas")).getAttribute("value"), "306");
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		driver.findElement(By.id("frmLivro:anoPublicacao")).click();
		assertEquals(driver.findElement(By.id("frmLivro:anoPublicacao")).getText(), "");
		try {
			assertEquals(driver.findElement(By.id("frmLivro:anoPublicacao")).getAttribute("value"), "2014");
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		driver.findElement(By.xpath("//a[@id='j_idt17:sair']/span")).click();
	}
	
	@AfterClass
	public static void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	private String closeAlertAndGetItsText() {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}

}
