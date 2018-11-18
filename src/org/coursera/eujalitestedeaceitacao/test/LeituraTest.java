package org.coursera.eujalitestedeaceitacao.test;


import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.dbunit.JdbcDatabaseTester;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LeituraTest {

	private static WebDriver driver;
	private JdbcDatabaseTester jdt;
	private static String baseUrl;
	private static StringBuffer verificationErrors = new StringBuffer();

	@BeforeClass
	public static void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\helio\\selenium-2.53.0\\chromedriver.exe");
		driver = new ChromeDriver();  
	    baseUrl = "http://localhost:8080/";
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	@Before
	public void inicializaTeste() throws Exception {
		jdt = new JdbcDatabaseTester("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/portal-livros", "root", "");
		FlatXmlDataFileLoader loader = new FlatXmlDataFileLoader();
		jdt.setDataSet(loader.load("/LeituraTrofeus.xml"));
		jdt.onSetup();
	}
	
	@Test
	public void whenJoseSilvaNaoLeuNenhumLivroThenObteve00PontosSemTrofeusConquistados() throws Exception {
		driver.get(baseUrl + "EuJaLi/");
		driver.findElement(By.id("j_username")).clear();
		driver.findElement(By.id("j_username")).sendKeys("jose.silva");
		driver.findElement(By.id("j_password")).clear();
		driver.findElement(By.id("j_password")).sendKeys("123");
		driver.findElement(By.id("botaoAccessar")).click();    

		WebDriverWait wait = new  WebDriverWait(driver, 30);
		driver.findElement(By.cssSelector("span.ui-icon.ui-icon-triangle-1-s")).click();    
		driver.findElement(By.cssSelector("span.ui-icon.ui-icon-triangle-1-s")).click();    
		WebElement leituras = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@href, '/EuJaLi/LeiturasUsuario.xhtml')]")));    
		leituras.click(); 
		assertEquals("Leituras do Usuario", driver.getTitle());	
		assertEquals(driver.findElement(By.id("frmLeiturasUsuario:nome")).getAttribute("value"), "José Silva");
		assertEquals(driver.findElement(By.id("frmLeiturasUsuario:pontos")).getAttribute("value"), "0");
		assertEquals(driver.findElement(By.id("frmLeiturasUsuario:quantidadeTrofeus")).getAttribute("value"), "0");
		assertEquals("Nenhum trofeu encontrado", driver.findElement(By.cssSelector("tr.ui-widget-content.ui-datatable-empty-message > td")).getText());    
		
		driver.get(baseUrl + "EuJaLi/j_spring_security_logout");   
	  }
	
	@Test
	public void whenMariaMartinsLeu04LivrosDe01EstiloThenObtevePontosSemTrofeusConquistados() throws Exception {
		driver.get(baseUrl + "EuJaLi/");
		driver.findElement(By.id("j_username")).clear();
		driver.findElement(By.id("j_username")).sendKeys("maria.martins");
		driver.findElement(By.id("j_password")).clear();
		driver.findElement(By.id("j_password")).sendKeys("123");
		driver.findElement(By.id("botaoAccessar")).click();    

		WebDriverWait wait = new  WebDriverWait(driver, 30);
		driver.findElement(By.cssSelector("span.ui-icon.ui-icon-triangle-1-s")).click();    
		driver.findElement(By.cssSelector("span.ui-icon.ui-icon-triangle-1-s")).click();    
		WebElement leituras = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@href, '/EuJaLi/LeiturasUsuario.xhtml')]")));    
		leituras.click(); 
		boolean pararExecucao = true;
		while(true){
			pararExecucao = true;
			try {
				assertEquals("Leituras do Usuario", driver.getTitle());	
				assertEquals(driver.findElement(By.id("frmLeiturasUsuario:nome")).getAttribute("value"), "Maria Martins");
				assertEquals(driver.findElement(By.id("frmLeiturasUsuario:pontos")).getAttribute("value"), "13");
				assertEquals(driver.findElement(By.id("frmLeiturasUsuario:quantidadeTrofeus")).getAttribute("value"), "0");
	   
				assertEquals("INFORMATICA", driver.findElement(By.xpath("//tbody[@id='frmLeiturasUsuario:trofeus_data']/tr/td[2]")).getText());
				assertEquals("CONCORRENDO", driver.findElement(By.xpath("//tbody[@id='frmLeiturasUsuario:trofeus_data']/tr/td[3]")).getText());

				driver.findElement(By.id("frmLeiturasUsuario:trofeus:0:j_idt37")).click();
				assertEquals("DevOps na prática: entrega de software confiável e automatizada", driver.findElement(By.xpath("//tbody[@id='frmLeiturasUsuario:livrosTrofeu_data']/tr/td[2]")).getText());
				assertEquals("Descomplicando o Docker", driver.findElement(By.xpath("//tbody[@id='frmLeiturasUsuario:livrosTrofeu_data']/tr[2]/td[2]")).getText());
				assertEquals("Scrum e Métodos Ágeis: Um Guia Prático", driver.findElement(By.xpath("//tbody[@id='frmLeiturasUsuario:livrosTrofeu_data']/tr[3]/td[2]")).getText());
				assertEquals("Programação de Software em Java", driver.findElement(By.xpath("//tbody[@id='frmLeiturasUsuario:livrosTrofeu_data']/tr[4]/td[2]")).getText());
				driver.findElement(By.xpath("//div[@id='frmLeiturasUsuario:mostraLivrosTrofeuDialog']/div/a")).click();
			} catch (Exception e) {
			     if (e.getMessage().contains("element is not attached")) {
			    	 pararExecucao = false;
		            }
			}
		    if (pararExecucao){
		    	break;
		    }			
		}	

		
		driver.get(baseUrl + "EuJaLi/j_spring_security_logout");   
	  }
	
	@Test
	public void whenCarlosSilvaLeu03LivrosDe03EstilosDistintosThenObteve13PontosSemTrofeusConquistados() throws Exception {
		driver.get(baseUrl + "EuJaLi/");
		driver.findElement(By.id("j_username")).clear();
		driver.findElement(By.id("j_username")).sendKeys("carlos.silva");
		driver.findElement(By.id("j_password")).clear();
		driver.findElement(By.id("j_password")).sendKeys("123");
		driver.findElement(By.id("botaoAccessar")).click();    

		WebDriverWait wait = new  WebDriverWait(driver, 30);
		driver.findElement(By.cssSelector("span.ui-icon.ui-icon-triangle-1-s")).click();    
		driver.findElement(By.cssSelector("span.ui-icon.ui-icon-triangle-1-s")).click();    
		WebElement leituras = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@href, '/EuJaLi/LeiturasUsuario.xhtml')]")));    
		leituras.click(); 
		
		assertEquals("Leituras do Usuario", driver.getTitle());	
		
		boolean pararExecucao = true;
		while(true){
			pararExecucao = true;
			try {
				assertEquals(driver.findElement(By.id("frmLeiturasUsuario:nome")).getAttribute("value"), "Carlos Silva");
				assertEquals(driver.findElement(By.id("frmLeiturasUsuario:pontos")).getAttribute("value"), "7");
				assertEquals(driver.findElement(By.id("frmLeiturasUsuario:quantidadeTrofeus")).getAttribute("value"), "0");
	   
				assertEquals("INFORMATICA", driver.findElement(By.xpath("//tbody[@id='frmLeiturasUsuario:trofeus_data']/tr/td[2]")).getText());
				assertEquals("CONCORRENDO", driver.findElement(By.xpath("//tbody[@id='frmLeiturasUsuario:trofeus_data']/tr/td[3]")).getText());
				driver.findElement(By.id("frmLeiturasUsuario:trofeus:0:j_idt37")).click();
				assertEquals("O que é DevOps?: Colaboração como caminho para entregar valor ao negócio", driver.findElement(By.xpath("//tbody[@id='frmLeiturasUsuario:livrosTrofeu_data']/tr/td[2]")).getText());
				driver.findElement(By.xpath("//div[@id='frmLeiturasUsuario:mostraLivrosTrofeuDialog']/div/a")).click();
				
				assertEquals("CULINARIA", driver.findElement(By.xpath("//tbody[@id='frmLeiturasUsuario:trofeus_data']/tr[2]/td[2]")).getText());
				assertEquals("CONCORRENDO", driver.findElement(By.xpath("//tbody[@id='frmLeiturasUsuario:trofeus_data']/tr[2]/td[3]")).getText());
				driver.findElement(By.id("frmLeiturasUsuario:trofeus:1:j_idt37")).click();
				assertEquals("Culinária Francesa Bistrô Clássico", driver.findElement(By.xpath("//tbody[@id='frmLeiturasUsuario:livrosTrofeu_data']/tr/td[2]")).getText());
				driver.findElement(By.xpath("//div[@id='frmLeiturasUsuario:mostraLivrosTrofeuDialog']/div/a")).click();
	   
				assertEquals("LITERATURA CLASSICA", driver.findElement(By.xpath("//tbody[@id='frmLeiturasUsuario:trofeus_data']/tr[3]/td[2]")).getText());
				assertEquals("CONCORRENDO", driver.findElement(By.xpath("//tbody[@id='frmLeiturasUsuario:trofeus_data']/tr[3]/td[3]")).getText());
				driver.findElement(By.id("frmLeiturasUsuario:trofeus:2:j_idt37")).click();
				assertEquals("Memórias de um Sargento de Milícias", driver.findElement(By.xpath("//tbody[@id='frmLeiturasUsuario:livrosTrofeu_data']/tr/td[2]")).getText());
				driver.findElement(By.xpath("//div[@id='frmLeiturasUsuario:mostraLivrosTrofeuDialog']/div/a")).click();
			
			} catch (Exception e) {
			     if (e.getMessage().contains("element is not attached")) {
			    	 pararExecucao = false;
		            }
			}
		    if (pararExecucao){
		    	break;
		    }
		}
		
		driver.get(baseUrl + "EuJaLi/j_spring_security_logout");   
	  }
	
	@Test
	public void whenLuciaAmaroLeu05LivrosDeInformaticaThenObteve15Pontos01TrofeuConquistado() throws Exception {
		driver.get(baseUrl + "EuJaLi/");
		driver.findElement(By.id("j_username")).clear();
		driver.findElement(By.id("j_username")).sendKeys("lucia.amaro");
		driver.findElement(By.id("j_password")).clear();
		driver.findElement(By.id("j_password")).sendKeys("123");
		driver.findElement(By.id("botaoAccessar")).click();    

		WebDriverWait wait = new  WebDriverWait(driver, 30);
		driver.findElement(By.cssSelector("span.ui-icon.ui-icon-triangle-1-s")).click();    
		driver.findElement(By.cssSelector("span.ui-icon.ui-icon-triangle-1-s")).click();    
		WebElement leituras = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@href, '/EuJaLi/LeiturasUsuario.xhtml')]")));    
		leituras.click(); 
		
		assertEquals("Leituras do Usuario", driver.getTitle());	
		
		boolean pararExecucao = true;
		while(true){
			pararExecucao = true;
			try {
				assertEquals(driver.findElement(By.id("frmLeiturasUsuario:nome")).getAttribute("value"), "Lúcia Amaro");
				assertEquals(driver.findElement(By.id("frmLeiturasUsuario:pontos")).getAttribute("value"), "15");
				assertEquals(driver.findElement(By.id("frmLeiturasUsuario:quantidadeTrofeus")).getAttribute("value"), "1");
				
				assertEquals("INFORMATICA", driver.findElement(By.xpath("//tbody[@id='frmLeiturasUsuario:trofeus_data']/tr/td[2]")).getText());
				assertEquals("CONQUISTADO", driver.findElement(By.xpath("//tbody[@id='frmLeiturasUsuario:trofeus_data']/tr/td[3]")).getText());

				driver.findElement(By.id("frmLeiturasUsuario:trofeus:0:j_idt37")).click();
				assertEquals("O que é DevOps?: Colaboração como caminho para entregar valor ao negócio", driver.findElement(By.xpath("//tbody[@id='frmLeiturasUsuario:livrosTrofeu_data']/tr/td[2]")).getText());
				assertEquals("DevOps na prática: entrega de software confiável e automatizada", driver.findElement(By.xpath("//tbody[@id='frmLeiturasUsuario:livrosTrofeu_data']/tr[2]/td[2]")).getText());
				assertEquals("Descomplicando o Docker", driver.findElement(By.xpath("//tbody[@id='frmLeiturasUsuario:livrosTrofeu_data']/tr[3]/td[2]")).getText());
				assertEquals("Scrum e Métodos Ágeis: Um Guia Prático", driver.findElement(By.xpath("//tbody[@id='frmLeiturasUsuario:livrosTrofeu_data']/tr[4]/td[2]")).getText());
				assertEquals("Programação de Software em Java", driver.findElement(By.xpath("//tbody[@id='frmLeiturasUsuario:livrosTrofeu_data']/tr[5]/td[2]")).getText());
				driver.findElement(By.xpath("//div[@id='frmLeiturasUsuario:mostraLivrosTrofeuDialog']/div/a")).click();
				
			} catch (Exception e) {
			     if (e.getMessage().contains("element is not attached")) {
			    	 pararExecucao = false;
		            }
			}
		    if (pararExecucao){
		    	break;
		    }
		}
		
		driver.get(baseUrl + "EuJaLi/j_spring_security_logout");   
	  }
	
	@Test
	public void whenMarcosMartinsLeu05LivrosDeInformatica02LivrosLiteraturaThenObteve26Pontos01TrofeuConquistado() throws Exception {
		driver.get(baseUrl + "EuJaLi/");
		driver.findElement(By.id("j_username")).clear();
		driver.findElement(By.id("j_username")).sendKeys("marcos.martins");
		driver.findElement(By.id("j_password")).clear();
		driver.findElement(By.id("j_password")).sendKeys("123");
		driver.findElement(By.id("botaoAccessar")).click();    

		WebDriverWait wait = new  WebDriverWait(driver, 30);
		driver.findElement(By.cssSelector("span.ui-icon.ui-icon-triangle-1-s")).click();    
		driver.findElement(By.cssSelector("span.ui-icon.ui-icon-triangle-1-s")).click();    
		WebElement leituras = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@href, '/EuJaLi/LeiturasUsuario.xhtml')]")));    
		leituras.click(); 
		
		assertEquals("Leituras do Usuario", driver.getTitle());	
		
		boolean pararExecucao = true;
		while(true){
			pararExecucao = true;
			try {
				assertEquals(driver.findElement(By.id("frmLeiturasUsuario:nome")).getAttribute("value"), "Marcos Martins");
				assertEquals(driver.findElement(By.id("frmLeiturasUsuario:pontos")).getAttribute("value"), "26");
				assertEquals(driver.findElement(By.id("frmLeiturasUsuario:quantidadeTrofeus")).getAttribute("value"), "1");
				
				assertEquals("INFORMATICA", driver.findElement(By.xpath("//tbody[@id='frmLeiturasUsuario:trofeus_data']/tr/td[2]")).getText());
				assertEquals("CONQUISTADO", driver.findElement(By.xpath("//tbody[@id='frmLeiturasUsuario:trofeus_data']/tr/td[3]")).getText());

				driver.findElement(By.id("frmLeiturasUsuario:trofeus:0:j_idt37")).click();
				assertEquals("O que é DevOps?: Colaboração como caminho para entregar valor ao negócio", driver.findElement(By.xpath("//tbody[@id='frmLeiturasUsuario:livrosTrofeu_data']/tr/td[2]")).getText());
				assertEquals("DevOps na prática: entrega de software confiável e automatizada", driver.findElement(By.xpath("//tbody[@id='frmLeiturasUsuario:livrosTrofeu_data']/tr[2]/td[2]")).getText());
				assertEquals("Descomplicando o Docker", driver.findElement(By.xpath("//tbody[@id='frmLeiturasUsuario:livrosTrofeu_data']/tr[3]/td[2]")).getText());
				assertEquals("Scrum e Métodos Ágeis: Um Guia Prático", driver.findElement(By.xpath("//tbody[@id='frmLeiturasUsuario:livrosTrofeu_data']/tr[4]/td[2]")).getText());
				assertEquals("Programação de Software em Java", driver.findElement(By.xpath("//tbody[@id='frmLeiturasUsuario:livrosTrofeu_data']/tr[5]/td[2]")).getText());
				driver.findElement(By.xpath("//div[@id='frmLeiturasUsuario:mostraLivrosTrofeuDialog']/div/a")).click();
				
				assertEquals("LITERATURA CLASSICA", driver.findElement(By.xpath("//tbody[@id='frmLeiturasUsuario:trofeus_data']/tr[2]/td[2]")).getText());
				assertEquals("CONCORRENDO", driver.findElement(By.xpath("//tbody[@id='frmLeiturasUsuario:trofeus_data']/tr[2]/td[3]")).getText());
				driver.findElement(By.id("frmLeiturasUsuario:trofeus:1:j_idt37")).click();
				assertEquals("Memórias de um Sargento de Milícias", driver.findElement(By.xpath("//tbody[@id='frmLeiturasUsuario:livrosTrofeu_data']/tr/td[2]")).getText());
				assertEquals("Os Maias", driver.findElement(By.xpath("//tbody[@id='frmLeiturasUsuario:livrosTrofeu_data']/tr[2]/td[2]")).getText());
				driver.findElement(By.xpath("//div[@id='frmLeiturasUsuario:mostraLivrosTrofeuDialog']/div/a")).click();
			} catch (Exception e) {
			     if (e.getMessage().contains("element is not attached")) {
			    	 pararExecucao = false;
		            }
			}
		    if (pararExecucao){
		    	break;
		    }
		}
		
		driver.get(baseUrl + "EuJaLi/j_spring_security_logout");   
	  }

	@Test
	public void whenEdvaldoPereiraLeu05LivrosDeInformatica06LivrosLiteraturaThenObteve15Pontos02TrofeusConquistados() throws Exception {
		driver.get(baseUrl + "EuJaLi/");
		driver.findElement(By.id("j_username")).clear();  
		driver.findElement(By.id("j_username")).sendKeys("edivaldo.pereira");
		driver.findElement(By.id("j_password")).clear();
		driver.findElement(By.id("j_password")).sendKeys("123");
		driver.findElement(By.id("botaoAccessar")).click();    

		WebDriverWait wait = new  WebDriverWait(driver, 30);
		driver.findElement(By.cssSelector("span.ui-icon.ui-icon-triangle-1-s")).click();    
		driver.findElement(By.cssSelector("span.ui-icon.ui-icon-triangle-1-s")).click();    
		WebElement leituras = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@href, '/EuJaLi/LeiturasUsuario.xhtml')]")));    
		leituras.click(); 
		
		assertEquals("Leituras do Usuario", driver.getTitle());	
		
		boolean pararExecucao = true;
		while(true){
			pararExecucao = true;
			try {
				assertEquals(driver.findElement(By.id("frmLeiturasUsuario:nome")).getAttribute("value"), "Edvaldo Pereira");
				assertEquals(driver.findElement(By.id("frmLeiturasUsuario:pontos")).getAttribute("value"), "42");
				assertEquals(driver.findElement(By.id("frmLeiturasUsuario:quantidadeTrofeus")).getAttribute("value"), "2");
				
				assertEquals("INFORMATICA", driver.findElement(By.xpath("//tbody[@id='frmLeiturasUsuario:trofeus_data']/tr/td[2]")).getText());
				assertEquals("CONQUISTADO", driver.findElement(By.xpath("//tbody[@id='frmLeiturasUsuario:trofeus_data']/tr/td[3]")).getText());
				driver.findElement(By.id("frmLeiturasUsuario:trofeus:0:j_idt37")).click();
				assertEquals("O que é DevOps?: Colaboração como caminho para entregar valor ao negócio", driver.findElement(By.xpath("//tbody[@id='frmLeiturasUsuario:livrosTrofeu_data']/tr/td[2]")).getText());
				assertEquals("DevOps na prática: entrega de software confiável e automatizada", driver.findElement(By.xpath("//tbody[@id='frmLeiturasUsuario:livrosTrofeu_data']/tr[2]/td[2]")).getText());
				assertEquals("Descomplicando o Docker", driver.findElement(By.xpath("//tbody[@id='frmLeiturasUsuario:livrosTrofeu_data']/tr[3]/td[2]")).getText());
				assertEquals("Scrum e Métodos Ágeis: Um Guia Prático", driver.findElement(By.xpath("//tbody[@id='frmLeiturasUsuario:livrosTrofeu_data']/tr[4]/td[2]")).getText());
				assertEquals("Programação de Software em Java", driver.findElement(By.xpath("//tbody[@id='frmLeiturasUsuario:livrosTrofeu_data']/tr[5]/td[2]")).getText());
				driver.findElement(By.xpath("//div[@id='frmLeiturasUsuario:mostraLivrosTrofeuDialog']/div/a")).click();
				
				assertEquals("LITERATURA CLASSICA", driver.findElement(By.xpath("//tbody[@id='frmLeiturasUsuario:trofeus_data']/tr[2]/td[2]")).getText());
				assertEquals("CONQUISTADO", driver.findElement(By.xpath("//tbody[@id='frmLeiturasUsuario:trofeus_data']/tr[2]/td[3]")).getText());
				driver.findElement(By.id("frmLeiturasUsuario:trofeus:1:j_idt37")).click();
				assertEquals("Memórias Póstumas de Brás Cubas", driver.findElement(By.xpath("//tbody[@id='frmLeiturasUsuario:livrosTrofeu_data']/tr/td[2]")).getText());
				assertEquals("Dom Casmurro", driver.findElement(By.xpath("//tbody[@id='frmLeiturasUsuario:livrosTrofeu_data']/tr[2]/td[2]")).getText());
				assertEquals("O cortiço", driver.findElement(By.xpath("//tbody[@id='frmLeiturasUsuario:livrosTrofeu_data']/tr[3]/td[2]")).getText());
				assertEquals("Quincas Borba", driver.findElement(By.xpath("//tbody[@id='frmLeiturasUsuario:livrosTrofeu_data']/tr[4]/td[2]")).getText());
				assertEquals("Memórias de um Sargento de Milícias", driver.findElement(By.xpath("//tbody[@id='frmLeiturasUsuario:livrosTrofeu_data']/tr[5]/td[2]")).getText());				
				driver.findElement(By.xpath("//div[@id='frmLeiturasUsuario:mostraLivrosTrofeuDialog']/div/a")).click();
				
				assertEquals("LITERATURA CLASSICA", driver.findElement(By.xpath("//tbody[@id='frmLeiturasUsuario:trofeus_data']/tr[3]/td[2]")).getText());
				assertEquals("CONCORRENDO", driver.findElement(By.xpath("//tbody[@id='frmLeiturasUsuario:trofeus_data']/tr[3]/td[3]")).getText());
				driver.findElement(By.id("frmLeiturasUsuario:trofeus:2:j_idt37")).click();
				assertEquals("Os Maias", driver.findElement(By.xpath("//tbody[@id='frmLeiturasUsuario:livrosTrofeu_data']/tr/td[2]")).getText());
				driver.findElement(By.xpath("//div[@id='frmLeiturasUsuario:mostraLivrosTrofeuDialog']/div/a")).click();
				
			} catch (Exception e) {
			     if (e.getMessage().contains("element is not attached")) {
			    	 pararExecucao = false;
		            }
			}
		    if (pararExecucao){
		    	break;
		    }
		}
  
	    driver.get(baseUrl + "EuJaLi/j_spring_security_logout");
	  }
	
	@AfterClass
	public static void tearDown() throws Exception {
	    driver.quit();
	    String verificationErrorString = verificationErrors.toString();
	    if (!"".equals(verificationErrorString)) {
	      fail(verificationErrorString);
	    }
	}

}
