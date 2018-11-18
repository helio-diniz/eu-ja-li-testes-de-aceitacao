package org.coursera.eujalitestedeaceitacao.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.dbunit.JdbcDatabaseTester;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PontuacaoTest {
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
		jdt.setDataSet(loader.load("/inicio.xml"));
		jdt.onSetup();
	}
	
	@Test
	public void whenJoseSilvaLeuLivro81PaginasThenObteve02Pontos() throws Exception {
		logar("jose.silva", "123");    
	    acessarLeituras();
	    verificarPontuacao("José Silva", "0");    
	    acessarLivro("2", "11");
	    marcarLivro();
	    acessarLeituras();
	    verificarPontuacao("José Silva", "2");
		logout();   
	  }
	
	@Test
	public void whenCarlosSilvaLeuLivro142PaginasThenObteve03Pontos() throws Exception {
		logar("carlos.silva", "123");    
	    acessarLeituras();
	    verificarPontuacao("Carlos Silva", "0");
	    acessarLivro("1", "6");
	    marcarLivro();
	    acessarLeituras();
	    verificarPontuacao("Carlos Silva", "3");
		logout();   
	  }

	@Test
	public void whenMariaMartinsLeuLivro201PaginasThenObteve04Pontos() throws Exception {
		logar("maria.martins", "123");    
	    acessarLeituras();
	    verificarPontuacao("Maria Martins", "0");
	    acessarLivro("2", "17");
	    marcarLivro();
	    acessarLeituras();
	    verificarPontuacao("Maria Martins", "4");
		logout();   
	  }
	
	@Test
	public void whenLuciaAmaroLeuLivro200PaginasThenObteve03Pontos() throws Exception {
		logar("lucia.amaro", "123");    
	    acessarLeituras();
	    verificarPontuacao("Lúcia Amaro", "0");
	    acessarLivro("2", "14");
	    marcarLivro();
	    acessarLeituras();
	    verificarPontuacao("Lúcia Amaro", "3");
		logout();   
	  }
	
	@Test
	public void whenMarcosMartinsLeuLivro599PaginasThenObteve07Pontos() throws Exception {
		logar("marcos.martins", "123");    
	    acessarLeituras();
	    verificarPontuacao("Marcos Martins", "0");
	    acessarLivro("2", "19");
	    marcarLivro();
	    acessarLeituras();
	    verificarPontuacao("Marcos Martins", "7");
		logout();   
	  }
	
	@Test
	public void whenNiloSilverioLeuLivro160DuasVezesPaginasThenObteve02Pontos() throws Exception {
		logar("nilo.silverio", "123");    
	    acessarLeituras();
	    verificarPontuacao("Nilo Silvério", "0");
	    acessarLivro("1", "5");
	    marcarLivro();
	    acessarLeituras();
	    verificarPontuacao("Nilo Silvério", "3");
	    acessarLivro("1", "5");
	    marcarLivroJaLido();
		logout();   
	  }
	
	private void marcarLivro() {
		driver.findElement(By.id("frmLivro:botaoMarcarLido")).click();
	    assertEquals("Leitura de livro marcada pelo usuario com sucesso!", driver.findElement(By.cssSelector("span.ui-messages-info-summary")).getText());
	}

	private void marcarLivroJaLido() {
		driver.findElement(By.id("frmLivro:botaoMarcarLido")).click();                 
	    assertEquals("Livro já lido pelo usuário!", driver.findElement(By.cssSelector("span.ui-messages-error-summary")).getText());
	}
	
	private void acessarLivro(String indiceGuia, String indiceLivro) {
		WebElement guiaSelecionada  = driver.findElement(By.linkText(indiceGuia));
	    JavascriptExecutor jse = (JavascriptExecutor)driver;
	    jse.executeScript("arguments[0].scrollIntoView()", guiaSelecionada);
	    guiaSelecionada.click();
	    driver.findElement(By.id("frmHome:livros:" + indiceLivro + ":j_idt44")).click();
	}

	private void logout() {
		driver.get(baseUrl + "EuJaLi/j_spring_security_logout");
	}

	private void verificarPontuacao(String usuario, String pontuacao) {
		assertEquals(driver.findElement(By.id("frmLeiturasUsuario:nome")).getAttribute("value"), usuario);
		assertEquals(driver.findElement(By.id("frmLeiturasUsuario:pontos")).getAttribute("value"), pontuacao);
		driver.get(baseUrl + "EuJaLi/Home.xhtml");
	}

	private void logar(String usuario, String senha) {
		driver.get(baseUrl + "EuJaLi/");
		driver.findElement(By.id("j_username")).clear();
		driver.findElement(By.id("j_username")).sendKeys(usuario);
		driver.findElement(By.id("j_password")).clear();
		driver.findElement(By.id("j_password")).sendKeys(senha);
		driver.findElement(By.id("botaoAccessar")).click();
	}

	private void acessarLeituras() {
		WebDriverWait wait = new  WebDriverWait(driver, 30);
		driver.findElement(By.cssSelector("span.ui-icon.ui-icon-triangle-1-s")).click();    
		driver.findElement(By.cssSelector("span.ui-icon.ui-icon-triangle-1-s")).click();    
		WebElement leituras = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@href, '/EuJaLi/LeiturasUsuario.xhtml')]")));    
		leituras.click();
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
