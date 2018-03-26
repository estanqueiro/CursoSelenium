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

	private WebDriver driver = new FirefoxDriver();
	
	@Before
	public void inicializa() {
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
	}
	
	@Test
	public void testeTextField() {
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Teste de escrita");
		String textField = driver.findElement(By.id("elementosForm:nome")).getAttribute("value");
		Assert.assertEquals("Teste de escrita", textField);
	}
	
	@Test
	public void deveInteragirComTextArea() throws InterruptedException {
		driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("Teste");
		String textArea = driver.findElement(By.id("elementosForm:sugestoes")).getAttribute("value");
		Assert.assertEquals("Teste",textArea);
	}
	
	@Test
	public void deveInteragirComRadioButton() throws InterruptedException {
		driver.findElement(By.id("elementosForm:sexo:0")).click();
		boolean masculino = driver.findElement(By.id("elementosForm:sexo:0")).isSelected();
		Assert.assertTrue(masculino);
	}
	
	@Test
	public void deveInteragirComCheckBox() throws InterruptedException {
		driver.findElement(By.id("elementosForm:comidaFavorita:2")).click();
		boolean comidaFavorita = driver.findElement(By.id("elementosForm:comidaFavorita:2")).isSelected();
		Assert.assertTrue(comidaFavorita);
	}
	
	@Test
	public void deveInteragirComComboBox() throws InterruptedException {
		WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
		Select combo = new Select(element);
		//combo.selectByIndex(2);
		//combo.selectByValue("superior");
		combo.selectByVisibleText("2o grau completo");
		Assert.assertEquals("2o grau completo",combo.getFirstSelectedOption().getText());
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
