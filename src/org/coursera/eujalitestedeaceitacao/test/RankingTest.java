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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RankingTest {
	private static WebDriver driver;
	private  JdbcDatabaseTester jdt;
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
	public void inicializaTeste() throws Exception{
	    jdt = new JdbcDatabaseTester("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/portal-livros", "root", "");
		FlatXmlDataFileLoader loader = new FlatXmlDataFileLoader();
		jdt.setDataSet(loader.load("/inicio.xml"));
		jdt.onSetup();
	}
	
	@Test
	public void whenNinguemPontouThenRankingOrdenadoPorOrdemAlfabeticaDeUsuarios() throws Exception {
		logar("jose.silva", "123");   
	    verificarRanking(new String[] {"Ana Paula Félix", "Carla Ferrari", "Carlos Silva", "Edvaldo Pereira", "José Silva", "Lucas Souza", "Luciana Campos", "Lúcia Amaro", "Lúcio Duarte", "Marcelo Meireles"}, 
	    	new String[] {"0","0","0","0","0","0","0","0","0","0"});
		logout();   
	  }

	@Test
	public void whenJoseCom02PontosThenJoseEOutrosEmOrdemAlfabetica() throws Exception {
		logar("jose.silva", "123");   
		marcarLivro("1", "0");
	    verificarRanking(new String[] {"José Silva", "Ana Paula Félix", "Carla Ferrari", "Carlos Silva", "Edvaldo Pereira", "Lucas Souza", "Luciana Campos", "Lúcia Amaro", "Lúcio Duarte", "Marcelo Meireles"}, 
	    	new String[] {"2","0","0","0","0","0","0","0","0","0"});
		logout();   
	  }
	
	@Test
	public void whenCarlosEJoseCom02E02PontosThenRankingComCarlosEJoseEOutrosEmOrdemAlfabetica() throws Exception {
		logar("jose.silva", "123");   
		marcarLivro("1", "0");
		logout();  
		
		logar("carlos.silva", "123");
		marcarLivro("1", "0");

	    verificarRanking(new String[] {"Carlos Silva", "José Silva", "Ana Paula Félix", "Carla Ferrari", "Edvaldo Pereira", "Lucas Souza", "Luciana Campos", "Lúcia Amaro", "Lúcio Duarte", "Marcelo Meireles"}, 
	    	new String[] {"2","2","0","0","0","0","0","0","0","0"});
		logout();   
	  }
	
	@Test
	public void whenLucasECarlosEJoseCom04E02E02PontosThenRankingComLucasECarlosEJoseEOutrosEmOrdemAlfabetica() throws Exception {
		logar("jose.silva", "123");   
		marcarLivro("1", "0");
		logout();  
		
		logar("carlos.silva", "123");
		marcarLivro("1", "0");
		logout();
		
		logar("lucas.souza", "123");
		marcarLivro("1", "4");

	    verificarRanking(new String[] {"Lucas Souza", "Carlos Silva", "José Silva", "Ana Paula Félix", "Carla Ferrari", "Edvaldo Pereira", "Luciana Campos", "Lúcia Amaro", "Lúcio Duarte", "Marcelo Meireles"}, 
	    	new String[] {"4","2","2","0","0","0","0","0","0","0"});
		logout();   
	  }
	
	@Test
	public void whenJoseELucasECarlosCom06E04E2PontosThenRankingComJoseECarlosELucasEOutrosEmOrdemAlfabetica() throws Exception {
		logar("jose.silva", "123");   
		marcarLivro("1", "0");
		marcarLivro("1", "1");
		logout();  
		
		logar("carlos.silva", "123");
		marcarLivro("1", "0");
		logout();
		
		logar("lucas.souza", "123");
		marcarLivro("1", "4");

	    verificarRanking(new String[] {"José Silva", "Lucas Souza", "Carlos Silva", "Ana Paula Félix", "Carla Ferrari", "Edvaldo Pereira", "Luciana Campos", "Lúcia Amaro", "Lúcio Duarte", "Marcelo Meireles"}, 
	    	new String[] {"6","4","2","0","0","0","0","0","0","0"});
		logout();   
	  }
	
	@Test
	public void whenMarceloJoseELucasECarlosCom07E06E04E2PontosThenRankingComMarceloEJoseECarlosELucasEOutrosEmOrdemAlfabetica() throws Exception {
		logar("jose.silva", "123");   
		marcarLivro("1", "0");
		marcarLivro("1", "1");
		logout();  
		
		logar("carlos.silva", "123");
		marcarLivro("1", "0");
		logout();
		
		logar("lucas.souza", "123");
		marcarLivro("1", "4");
		logout();

		logar("marcelo.meireles", "123");
		marcarLivro("2", "19");

	    verificarRanking(new String[] {"Marcelo Meireles", "José Silva", "Lucas Souza", "Carlos Silva", "Ana Paula Félix", "Carla Ferrari", "Edvaldo Pereira", "Luciana Campos", "Lúcia Amaro", "Lúcio Duarte"}, 
	    	new String[] {"7","6","4","2","0","0","0","0","0","0"});
		logout();   
	  }
	
	@Test
	public void whenMarianaMarceloJoseELucasECarlosCom09E07E04E04E2PontosThenRankingComMarianaEMarceloEJoseECarlosELucasEOutrosEmOrdemAlfabetica() throws Exception {
		logar("jose.silva", "123");   
		marcarLivro("1", "0");
		marcarLivro("1", "1");
		logout();  
		
		logar("carlos.silva", "123");
		marcarLivro("1", "0");
		logout();
		
		logar("lucas.souza", "123");
		marcarLivro("1", "4");
		logout();
		
		logar("mariana.zambonaro", "123");
		marcarLivro("2", "10");
		marcarLivro("2", "11");
		marcarLivro("2", "16");
		logout();
		
		logar("marcelo.meireles", "123");
		marcarLivro("2", "19");

	    verificarRanking(new String[] {"Mariana Zambonaro", "Marcelo Meireles", "José Silva", "Lucas Souza", "Carlos Silva", "Ana Paula Félix", "Carla Ferrari", "Edvaldo Pereira", "Luciana Campos", "Lúcia Amaro"}, 
	    	new String[] {"9","7","6","4","2","0","0","0","0","0"});
		logout();   
	  }
	
	@AfterClass
	public static void tearDown() throws Exception {
	    driver.quit();
	    String verificationErrorString = verificationErrors.toString();
	    if (!"".equals(verificationErrorString)) {
	      fail(verificationErrorString);
	    }
	 }
	
	private void logar(String usuario, String senha) {
		driver.get(baseUrl + "EuJaLi/");
		driver.findElement(By.id("j_username")).clear();
		driver.findElement(By.id("j_username")).sendKeys(usuario);
		driver.findElement(By.id("j_password")).clear();
		driver.findElement(By.id("j_password")).sendKeys(senha);
		driver.findElement(By.id("botaoAccessar")).click();
	}
	
	private void acessarRanking() {
		WebDriverWait wait = new  WebDriverWait(driver, 30);
		driver.findElement(By.cssSelector("span.ui-icon.ui-icon-triangle-1-s")).click();    
		driver.findElement(By.cssSelector("span.ui-icon.ui-icon-triangle-1-s")).click();    
		WebElement ranking = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@href, '/EuJaLi/RankingUsuarios.xhtml')]")));    
		ranking.click();
		assertEquals("Ranking de Usuarios", driver.getTitle());
		assertEquals("Ranking de Usuarios", driver.findElement(By.cssSelector("h1")).getText());
	}
	
	private void verificarRanking(String[] usuarios, String[] pontos) {
		acessarRanking();
		assertEquals(usuarios[0], driver.findElement(By.xpath("//tbody[@id='frmRanking:ranking_data']/tr/td")).getText());
		assertEquals(pontos[0], driver.findElement(By.xpath("//tbody[@id='frmRanking:ranking_data']/tr/td[2]")).getText());
		assertEquals(usuarios[1], driver.findElement(By.xpath("//tbody[@id='frmRanking:ranking_data']/tr[2]/td")).getText());
		assertEquals(pontos[1], driver.findElement(By.xpath("//tbody[@id='frmRanking:ranking_data']/tr[2]/td[2]")).getText());
		assertEquals(usuarios[2], driver.findElement(By.xpath("//tbody[@id='frmRanking:ranking_data']/tr[3]/td")).getText());
		assertEquals(pontos[2], driver.findElement(By.xpath("//tbody[@id='frmRanking:ranking_data']/tr[3]/td[2]")).getText());
		assertEquals(usuarios[3], driver.findElement(By.xpath("//tbody[@id='frmRanking:ranking_data']/tr[4]/td")).getText());
		assertEquals(pontos[3], driver.findElement(By.xpath("//tbody[@id='frmRanking:ranking_data']/tr[4]/td[2]")).getText());
		assertEquals(usuarios[4], driver.findElement(By.xpath("//tbody[@id='frmRanking:ranking_data']/tr[5]/td")).getText());
		assertEquals(pontos[4], driver.findElement(By.xpath("//tbody[@id='frmRanking:ranking_data']/tr[5]/td[2]")).getText());
		assertEquals(usuarios[5], driver.findElement(By.xpath("//tbody[@id='frmRanking:ranking_data']/tr[6]/td")).getText());
		assertEquals(pontos[5], driver.findElement(By.xpath("//tbody[@id='frmRanking:ranking_data']/tr[6]/td[2]")).getText());
		assertEquals(usuarios[6], driver.findElement(By.xpath("//tbody[@id='frmRanking:ranking_data']/tr[7]/td")).getText());
		assertEquals(pontos[6], driver.findElement(By.xpath("//tbody[@id='frmRanking:ranking_data']/tr[7]/td[2]")).getText());
		assertEquals(usuarios[7], driver.findElement(By.xpath("//tbody[@id='frmRanking:ranking_data']/tr[8]/td")).getText());
		assertEquals(pontos[7], driver.findElement(By.xpath("//tbody[@id='frmRanking:ranking_data']/tr[8]/td[2]")).getText());
		assertEquals(usuarios[8], driver.findElement(By.xpath("//tbody[@id='frmRanking:ranking_data']/tr[9]/td")).getText());
		assertEquals(pontos[8], driver.findElement(By.xpath("//tbody[@id='frmRanking:ranking_data']/tr[9]/td[2]")).getText());
	    assertEquals(usuarios[9], driver.findElement(By.xpath("//tbody[@id='frmRanking:ranking_data']/tr[10]/td")).getText());
	    assertEquals(pontos[9], driver.findElement(By.xpath("//tbody[@id='frmRanking:ranking_data']/tr[10]/td[2]")).getText());
	}
	
	private void marcarLivro(String indiceGuia, String indiceLivro) {
		WebElement guiaSelecionada  = driver.findElement(By.linkText(indiceGuia));
	    JavascriptExecutor jse = (JavascriptExecutor)driver;
	    jse.executeScript("arguments[0].scrollIntoView()", guiaSelecionada);
	    guiaSelecionada.click();
	    driver.findElement(By.id("frmHome:livros:" + indiceLivro + ":j_idt44")).click();
		driver.findElement(By.id("frmLivro:botaoMarcarLido")).click();
	    assertEquals("Leitura de livro marcada pelo usuario com sucesso!", driver.findElement(By.cssSelector("span.ui-messages-info-summary")).getText());
	    driver.get(baseUrl + "EuJaLi/Home.xhtml");	
	}
	
	
	private void logout() {
		driver.get(baseUrl + "EuJaLi/j_spring_security_logout");
	}
}
