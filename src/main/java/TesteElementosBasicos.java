import java.util.List;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class TesteElementosBasicos {
	
	private WebDriver driver;
	private DSL dsl;
	
	@Before
	public void inicializa() {
		driver = new FirefoxDriver();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL(driver);
	}
	
	@Test
	public void testeTextField() {
		dsl.escrever("elementosForm:nome", "Teste de escrita");
		Assert.assertEquals("Teste de escrita", dsl.obterValorCampo("elementosForm:nome"));
	}
	
	@Test
	public void deveInteragirComTextArea() throws InterruptedException {
		dsl.escrever("elementosForm:sugestoes", "Teste");
		Assert.assertEquals("Teste de escrita", dsl.obterValorCampo("elementosForm:sugestoes"));
	}
	
	@Test
	public void deveInteragirComRadioButton() throws InterruptedException {
		dsl.clicarRadioOuCheck("elementosForm:sexo:0");
		Assert.assertTrue(dsl.isRadioOuCheckMarcado("elementosForm:sexo:0"));
	}
	
	@Test
	public void deveInteragirComCheckBox() throws InterruptedException {
		dsl.clicarRadioOuCheck("elementosForm:comidaFavorita:2");
		Assert.assertTrue(dsl.isRadioOuCheckMarcado("elementosForm:comidaFavorita:2"));
	}
	
	@Test
	public void deveInteragirComComboBox() throws InterruptedException {
		dsl.selecionarCombo("elementosForm:escolaridade", "2o grau completo");
		Assert.assertEquals("2o grau completo", dsl.obterValorCombo("elementosForm:escolaridade"));
	}
	
	@Test
	public void deveVerificarValoresCombo() throws InterruptedException {
		WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
		Select combo = new Select(element);
		List<WebElement> options = combo.getOptions();
		Assert.assertEquals(8,options.size());
		
		boolean encontrou = false;
		for(WebElement option: options) {
			if(option.getText().equals("Mestrado")) {
				encontrou = true;
				break;
			}
		}
		Assert.assertTrue(encontrou);
	}
	
	@Test
	public void deveVerificarValoresComboMultiplo() throws InterruptedException {
		WebElement element = driver.findElement(By.id("elementosForm:esportes"));
		Select combo = new Select(element);
		List<WebElement> options = combo.getOptions();
		Assert.assertEquals(5,options.size());
		
		combo.selectByVisibleText("Natacao");
		combo.selectByVisibleText("Corrida");
		combo.selectByVisibleText("O que eh esporte?");
		
		List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
		Assert.assertEquals(3, allSelectedOptions.size());
		
		combo.deselectByVisibleText("O que eh esporte?");
		allSelectedOptions = combo.getAllSelectedOptions();
		Assert.assertEquals(2, allSelectedOptions.size());
	}
	
	@Test
	public void deveInteragirComBotoes() throws InterruptedException {
		WebElement botao = driver.findElement(By.id("buttonSimple"));
		botao.click();
		
		Assert.assertEquals("Obrigado!",botao.getAttribute("value"));
	}	

	@Test
	public void deveInteragirComLinks() throws InterruptedException {
		WebElement link = driver.findElement(By.linkText("Voltar"));
		Assert.assertEquals("Voltar",link.getText());
		link.click();

		WebElement resultado = driver.findElement(By.id("resultado"));	
		Assert.assertEquals("Voltou!", resultado.getText());
	}
	
	@Test
	public void deveBuscarTextoNaPagina() throws InterruptedException {
		//Assert.assertTrue(driver.findElement(By.tagName("body")).getText().contains("Campo de Treinamento"));
				
		WebElement titulo = driver.findElement(By.tagName("h3"));
		Assert.assertEquals("Campo de Treinamento", titulo.getText());
		
		WebElement cuidado = driver.findElement(By.className("facilAchar"));
		Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", cuidado.getText());
	}	
	
	@After
	public void finaliza() {
		driver.quit();
	}
}
