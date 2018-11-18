package org.coursera.eujalitestedeaceitacao.test;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginTest {
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
	  public void whenUsuarioJoseSilvaESenha123ThenPermiteAcesso() throws Exception {
	    driver.get(baseUrl + "EuJaLi/");
	    
	    driver.findElement(By.id("j_username")).clear();
	    driver.findElement(By.id("j_username")).sendKeys("jose.silva");
	    driver.findElement(By.id("j_password")).clear();
	    driver.findElement(By.id("j_password")).sendKeys("123");
	    driver.findElement(By.id("botaoAccessar")).click();
	    
	    String login = driver.findElement(By.xpath("/html/body/header/div[2]/span")).getText();
	    Assert.assertEquals("Olá José Silva!", login);
	    driver.findElement(By.xpath("//a[@id='j_idt16:sair']/span")).click();
	  }
	  
	  @Test
	  public void whenUsuarioJoseSilvaESenha124ThenProibeAcesso() throws Exception {
	    driver.get(baseUrl + "EuJaLi/");
	    
	    driver.findElement(By.id("j_username")).clear();
	    driver.findElement(By.id("j_username")).sendKeys("jose.silva");
	    driver.findElement(By.id("j_password")).clear();
	    driver.findElement(By.id("j_password")).sendKeys("124");
	    driver.findElement(By.id("botaoAccessar")).click();
	    
	    assertTrue(driver.getPageSource().contains("Usuário ou senha inválido!"));
	  }
	  
	  @Test
	  public void whenUsuarioInvalidoJoaoMarinsESenha124ThenProibeAcesso() throws Exception {
	    driver.get(baseUrl + "EuJaLi/");
	    
	    driver.findElement(By.id("j_username")).clear();
	    driver.findElement(By.id("j_username")).sendKeys("joao.marins");
	    driver.findElement(By.id("j_password")).clear();
	    driver.findElement(By.id("j_password")).sendKeys("123");
	    driver.findElement(By.id("botaoAccessar")).click();
	    
	    assertTrue(driver.getPageSource().contains("Usuário ou senha inválido!"));
	  }

	  @Test
	  public void whenUsuarioJoseSilvaLogaESaiEMariaMartinsThenTrocaUsuario() throws Exception {
	    driver.get(baseUrl + "EuJaLi/");
	    
	    driver.findElement(By.id("j_username")).clear();
	    driver.findElement(By.id("j_username")).sendKeys("jose.silva");
	    driver.findElement(By.id("j_password")).clear();
	    driver.findElement(By.id("j_password")).sendKeys("123");
	    driver.findElement(By.id("botaoAccessar")).click();
	    
	    String login = driver.findElement(By.xpath("/html/body/header/div[2]/span")).getText();
	    Assert.assertEquals("Olá José Silva!", login);
	    
	    driver.findElement(By.xpath("//a[@id='j_idt16:sair']/span")).click();
	    driver.findElement(By.id("j_username")).clear();
	    driver.findElement(By.id("j_username")).sendKeys("maria.martins");
	    driver.findElement(By.id("j_password")).clear();
	    driver.findElement(By.id("j_password")).sendKeys("123");
	    driver.findElement(By.id("botaoAccessar")).click();
	    
	    login = driver.findElement(By.xpath("/html/body/header/div[2]/span")).getText();
	    Assert.assertEquals("Olá Maria Martins!", login);
	    driver.findElement(By.xpath("//a[@id='j_idt16:sair']/span")).click();
	    
	  }
	 
	  @Test
	  public void whenAcessaLivroSemLogarThenAcessoProibidoELogin() throws Exception {
	    driver.get(baseUrl + "/EuJaLi/Livro.xhtml");
	    assertEquals("Login", driver.getTitle());
	    assertEquals("Login", driver.findElement(By.cssSelector("h1")).getText());
	  }
	  
	  @Test
	  public void whenAcessaLeiturasUsuarioSemLogarThenAcessoProibidoELogin() throws Exception {
	    driver.get(baseUrl + "/EuJaLi/LeiturasUsuario.xhtml");
	    assertEquals("Login", driver.getTitle());
	    assertEquals("Login", driver.findElement(By.cssSelector("h1")).getText());
	  }
	  
	  @Test
	  public void whenAcessaRankingUsuariosSemLogarThenAcessoProibidoELogin() throws Exception {
	    driver.get(baseUrl + "/EuJaLi/RankingUsuarios.xhtml");
	    assertEquals("Login", driver.getTitle());
	    assertEquals("Login", driver.findElement(By.cssSelector("h1")).getText());
	  }	  
	  
	  @Test
	  public void whenUsuarioJoseSilvaLogarThenPoderaAcessarTelaLivroELeituraERanking() throws Exception {
	    driver.get(baseUrl + "EuJaLi/");
	    
	    driver.findElement(By.id("j_username")).clear();
	    driver.findElement(By.id("j_username")).sendKeys("jose.silva");
	    driver.findElement(By.id("j_password")).clear();
	    driver.findElement(By.id("j_password")).sendKeys("123");
	    driver.findElement(By.id("botaoAccessar")).click();
	    
	    String login = driver.findElement(By.xpath("/html/body/header/div[2]/span")).getText();
	    Assert.assertEquals("Olá José Silva!", login);
	    
	    driver.findElement(By.id("frmHome:livros:0:j_idt44")).click();
	    assertEquals("Livro", driver.getTitle());
	    assertEquals("Livro", driver.findElement(By.cssSelector("h1")).getText());
	    driver.findElement(By.cssSelector("a > img")).click();

	    WebDriverWait wait = new  WebDriverWait(driver, 30);
	    
	    driver.findElement(By.cssSelector("span.ui-icon.ui-icon-triangle-1-s")).click();
	    driver.findElement(By.cssSelector("span.ui-icon.ui-icon-triangle-1-s")).click();
	    WebElement leituras = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@href, '/EuJaLi/LeiturasUsuario.xhtml')]")));
	    leituras.click();
	    assertEquals("Leituras do Usuario", driver.getTitle());
	    assertEquals("Leituras do Usuario", driver.findElement(By.cssSelector("h1")).getText());
	    
	    
	    driver.findElement(By.cssSelector("span.ui-icon.ui-icon-triangle-1-s")).click();
	    driver.findElement(By.cssSelector("span.ui-icon.ui-icon-triangle-1-s")).click();
	    WebElement ranking = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@href, '/EuJaLi/RankingUsuarios.xhtml')]")));
	    ranking.click();
	    assertEquals("Ranking de Usuarios", driver.getTitle());
	    assertEquals("Ranking de Usuarios", driver.findElement(By.cssSelector("h1")).getText());
	    
	    driver.findElement(By.cssSelector("a > img")).click();  
	    
	    driver.findElement(By.xpath("//a[@id='j_idt16:sair']/span")).click();
	    
	    
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
