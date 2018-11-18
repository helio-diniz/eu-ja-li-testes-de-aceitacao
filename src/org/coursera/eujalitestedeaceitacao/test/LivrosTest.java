package org.coursera.eujalitestedeaceitacao.test;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LivrosTest {
	  private static WebDriver driver;
	  private static String baseUrl;
	  private static StringBuffer verificationErrors = new StringBuffer();

	  @BeforeClass
	  public static void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver","C:\\Users\\helio\\selenium-2.53.0\\chromedriver.exe");
		driver = new ChromeDriver();  
		baseUrl = "http://localhost:8080/";
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  }

	  @Test
	  public void whenJoseSilvaLogouThenExibir20Livros() throws Exception {
		driver.get(baseUrl + "EuJaLi/");	    
	    driver.findElement(By.id("j_username")).clear();
	    driver.findElement(By.id("j_username")).sendKeys("jose.silva");
	    driver.findElement(By.id("j_password")).clear();
	    driver.findElement(By.id("j_password")).sendKeys("123");
	    driver.findElement(By.id("botaoAccessar")).click();
	    
	    boolean pararExecucao = true;
	    while(true){
	    	pararExecucao = true;
		    try {
			    driver.findElement(By.linkText("1")).click();
			    assertEquals("1", driver.findElement(By.cssSelector("tr.ui-widget-content.ui-datatable-even > td")).getText());
			    assertEquals("2", driver.findElement(By.cssSelector("tr.ui-widget-content.ui-datatable-odd > td")).getText());
			    assertEquals("3", driver.findElement(By.xpath("//tbody[@id='frmHome:livros_data']/tr[3]/td")).getText());
			    assertEquals("4", driver.findElement(By.xpath("//tbody[@id='frmHome:livros_data']/tr[4]/td")).getText());
			    assertEquals("5", driver.findElement(By.xpath("//tbody[@id='frmHome:livros_data']/tr[5]/td")).getText());
			    assertEquals("6", driver.findElement(By.xpath("//tbody[@id='frmHome:livros_data']/tr[6]/td")).getText());
			    assertEquals("7", driver.findElement(By.xpath("//tbody[@id='frmHome:livros_data']/tr[7]/td")).getText());
			    assertEquals("8", driver.findElement(By.xpath("//tbody[@id='frmHome:livros_data']/tr[8]/td")).getText());
			    assertEquals("9", driver.findElement(By.xpath("//tbody[@id='frmHome:livros_data']/tr[9]/td")).getText());
			    assertEquals("10", driver.findElement(By.xpath("//tbody[@id='frmHome:livros_data']/tr[10]/td")).getText());

		    	driver.findElement(By.linkText("2")).click();
		    	assertTrue(isElementPresent(By.cssSelector("tr.ui-widget-content.ui-datatable-even > td")));
		    	assertEquals("11", driver.findElement(By.cssSelector("tr.ui-widget-content.ui-datatable-even > td")).getText());
				assertEquals("12", driver.findElement(By.cssSelector("tr.ui-widget-content.ui-datatable-odd > td")).getText());
				assertEquals("13", driver.findElement(By.xpath("//tbody[@id='frmHome:livros_data']/tr[3]/td")).getText());
				assertEquals("14", driver.findElement(By.xpath("//tbody[@id='frmHome:livros_data']/tr[4]/td")).getText());
				assertEquals("15", driver.findElement(By.xpath("//tbody[@id='frmHome:livros_data']/tr[5]/td")).getText());
				assertEquals("16", driver.findElement(By.xpath("//tbody[@id='frmHome:livros_data']/tr[6]/td")).getText());
				assertEquals("17", driver.findElement(By.xpath("//tbody[@id='frmHome:livros_data']/tr[7]/td")).getText());
				assertEquals("18", driver.findElement(By.xpath("//tbody[@id='frmHome:livros_data']/tr[8]/td")).getText());
				assertEquals("19", driver.findElement(By.xpath("//tbody[@id='frmHome:livros_data']/tr[9]/td")).getText());
				assertEquals("20", driver.findElement(By.xpath("//tbody[@id='frmHome:livros_data']/tr[10]/td")).getText());
			} catch (Exception e) {
			     if (e.getMessage().contains("element is not attached")) {
			    	 pararExecucao = false;
		            }
			}
		    if (pararExecucao){
		    	break;
		    }
	    }

	    driver.findElement(By.xpath("//a[@id='j_idt16:sair']/span")).click();
	  }	  
	  
	  @Test
	  public void whenPesquisouPorTituloDevOpsThenRetornou02Livros() throws Exception {	
	    driver.get(baseUrl + "EuJaLi/");	    
	    driver.findElement(By.id("j_username")).clear();
	    driver.findElement(By.id("j_username")).sendKeys("jose.silva");
	    driver.findElement(By.id("j_password")).clear();
	    driver.findElement(By.id("j_password")).sendKeys("123");
	    driver.findElement(By.id("botaoAccessar")).click();
	    
	    driver.findElement(By.id("frmHome:titulo")).clear();
	    driver.findElement(By.id("frmHome:titulo")).sendKeys("DevOps");
	    driver.findElement(By.id("frmHome:pesquisar")).click();
	    boolean pararExecucao = true;
	    while(true){
	    	pararExecucao = true;
		    try {
		    	assertTrue(isElementPresent(By.xpath("//tbody[@id='frmHome:livros_data']/tr/td[2]")));
				assertEquals(driver.findElement(By.xpath("//tbody[@id='frmHome:livros_data']/tr/td[2]")).getText(), "O que é DevOps?: Colaboração como caminho para entregar valor ao negócio");
				assertTrue(isElementPresent(By.xpath("//tbody[@id='frmHome:livros_data']/tr[2]/td[2]")));
				assertEquals(driver.findElement(By.xpath("//tbody[@id='frmHome:livros_data']/tr[2]/td[2]")).getText(), "DevOps na prática: entrega de software confiável e automatizada");
			} catch (Exception e) {
				pararExecucao = false;
			}   	    	
		    if (pararExecucao){
		    	break;
		    }
	    }
	    driver.findElement(By.id("j_idt16:sair")).click();
	  }  
	  
	  @Test
	  public void whenPesquisouPorEditoraCentaurThenRetornou03Livros() throws Exception {		  
		driver.get(baseUrl + "EuJaLi/");	    
	    driver.findElement(By.id("j_username")).clear();
	    driver.findElement(By.id("j_username")).sendKeys("jose.silva");
	    driver.findElement(By.id("j_password")).clear();
	    driver.findElement(By.id("j_password")).sendKeys("123");
	    driver.findElement(By.id("botaoAccessar")).click();  
	 
	    driver.findElement(By.id("frmHome:editora")).clear();
	    driver.findElement(By.id("frmHome:editora")).sendKeys("Centaur");
	    driver.findElement(By.id("frmHome:pesquisar")).click();
	    boolean pararExecucao = true;
	    while(true){
	    	pararExecucao = true;
	    	try {
		        assertTrue(isElementPresent(By.xpath("//tbody[@id='frmHome:livros_data']/tr/td[2]")));
			    assertEquals("Memórias de um Sargento de Milícias", driver.findElement(By.xpath("//tbody[@id='frmHome:livros_data']/tr/td[2]")).getText());
			    assertTrue(isElementPresent(By.xpath("//tbody[@id='frmHome:livros_data']/tr[2]/td[2]")));
			    assertEquals("Triste Fim de Policarpo Quaresma", driver.findElement(By.xpath("//tbody[@id='frmHome:livros_data']/tr[2]/td[2]")).getText());
			    assertTrue(isElementPresent(By.xpath("//tbody[@id='frmHome:livros_data']/tr[3]/td[2]")));
			    assertEquals("Os Maias", driver.findElement(By.xpath("//tbody[@id='frmHome:livros_data']/tr[3]/td[2]")).getText());	    

		    } catch (Exception e) {
		        pararExecucao = false;
		    }	    
		    if (pararExecucao){
		    	break;
		    }
	    }

	    driver.findElement(By.id("j_idt16:sair")).click();
	  } 	  
	  
	  @Test
	  public void whenPesquisouPorEstiloCulinariaThenRetornou06Livros() throws Exception {		  
		driver.get(baseUrl + "EuJaLi/");	    
	    driver.findElement(By.id("j_username")).clear();
	    driver.findElement(By.id("j_username")).sendKeys("jose.silva");
	    driver.findElement(By.id("j_password")).clear();
	    driver.findElement(By.id("j_password")).sendKeys("123");
	    driver.findElement(By.id("botaoAccessar")).click();  
	 
	    driver.findElement(By.xpath("//table[@id='frmHome:estilo']/tbody/tr/td[2]/div/div[2]/span")).click();
	    driver.findElement(By.id("frmHome:pesquisar")).click();
	    boolean pararExecucao = true;
	    while(true){
	    	pararExecucao = true;
	    	try {
	    		assertEquals("Culinária Francesa Bistrô Clássico", driver.findElement(By.xpath("//tbody[@id='frmHome:livros_data']/tr/td[2]")).getText());
	    		assertEquals("Risotos", driver.findElement(By.xpath("//tbody[@id='frmHome:livros_data']/tr[2]/td[2]")).getText());
	    		assertEquals("Massas Gourmet", driver.findElement(By.xpath("//tbody[@id='frmHome:livros_data']/tr[3]/td[2]")).getText());
	    		assertEquals("Tortas Deliciosas (Minicozinha)", driver.findElement(By.xpath("//tbody[@id='frmHome:livros_data']/tr[4]/td[2]")).getText());
	    		assertEquals("Carnes Deliciosas (Minicozinha)", driver.findElement(By.xpath("//tbody[@id='frmHome:livros_data']/tr[5]/td[2]")).getText());
	    		assertEquals("Diário de Olivier-As Receitas da Bocaina", driver.findElement(By.xpath("//tbody[@id='frmHome:livros_data']/tr[6]/td[2]")).getText()); 
		    } catch (Exception e) {
		        pararExecucao = false;
		    }	    
		    if (pararExecucao){
		    	break;
		    }
	    }

	    driver.findElement(By.id("j_idt16:sair")).click();
	  } 
	  
	  @Test
	  public void whenPesquisouPorAnoDePublicacao2015ThenRetornou05Livros() throws Exception {		  
		driver.get(baseUrl + "EuJaLi/");	    
	    driver.findElement(By.id("j_username")).clear();
	    driver.findElement(By.id("j_username")).sendKeys("jose.silva");
	    driver.findElement(By.id("j_password")).clear();
	    driver.findElement(By.id("j_password")).sendKeys("123");
	    driver.findElement(By.id("botaoAccessar")).click();  
	 
	    driver.findElement(By.id("frmHome:anoPublicacao")).clear();
	    driver.findElement(By.id("frmHome:anoPublicacao")).sendKeys("2015");
	    driver.findElement(By.id("frmHome:pesquisar")).click();
	    boolean pararExecucao = true;
	    while(true){
	    	pararExecucao = true;
	    	try {
	    	    assertEquals("DevOps na prática: entrega de software confiável e automatizada", driver.findElement(By.xpath("//tbody[@id='frmHome:livros_data']/tr/td[2]")).getText());
	    	    assertEquals("Scrum: Gestão ágil para projetos de sucesso", driver.findElement(By.xpath("//tbody[@id='frmHome:livros_data']/tr[2]/td[2]")).getText());
	    	    assertEquals("Introdução ao jQuery", driver.findElement(By.xpath("//tbody[@id='frmHome:livros_data']/tr[3]/td[2]")).getText());
	    	    assertEquals("Massas Gourmet", driver.findElement(By.xpath("//tbody[@id='frmHome:livros_data']/tr[4]/td[2]")).getText());
	    	    assertEquals("O cortiço", driver.findElement(By.xpath("//tbody[@id='frmHome:livros_data']/tr[5]/td[2]")).getText());
	    	} catch (Exception e) {
		        pararExecucao = false;
		    }	    
		    if (pararExecucao){
		    	break;
		    }
	    }

	    driver.findElement(By.id("j_idt16:sair")).click();
	  }
	  
	  private boolean isElementPresent(By by) {
		    try {
		      driver.findElement(by);
		      return true;
		    } catch (NoSuchElementException e) {
		      return false;
		    }
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
