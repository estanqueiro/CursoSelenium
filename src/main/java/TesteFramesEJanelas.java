import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteFramesEJanelas {
	private WebDriver driver;
	private DSL dsl;
	
	@Before
	public void inicializa() {
		driver = new FirefoxDriver();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL(driver);
	}
	
	@Test
	public void deveInteragirComFrames() {	
		dsl.entrarFrame("frame1");
		dsl.clicarBotao("frameButton");
		String texto = dsl.alertaObterTextoEAceita();
		Assert.assertEquals("Frame OK!", texto);
	
		dsl.sairFrame();
		dsl.escrever("elementosForm:nome", texto);
	}
	
	@Test
	public void deveInteragirComJanelas() {	
		dsl.clicarBotao("buttonPopUpEasy");
		dsl.trocarJanela("Popup");
		dsl.escrever(By.tagName("textarea"), "Deu certo?");
		driver.close();
		dsl.trocarJanela("");
		dsl.escrever(By.tagName("textarea"), "e agora?");
	}
	
	@Test
	public void deveInteragirComJanelasSemId() {	
		dsl.clicarBotao("buttonPopUpHard");
		dsl.trocarJanela((String) driver.getWindowHandles().toArray()[1]);
		dsl.escrever(By.tagName("textarea"), "Deu certo?");
		dsl.trocarJanela((String) driver.getWindowHandles().toArray()[0]);
		dsl.escrever(By.tagName("textarea"), "E agora?");
	}
	
	@After
	public void finaliza() {
		driver.quit();
	}
}

