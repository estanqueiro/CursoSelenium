import java.util.List;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import junit.framework.Assert;

public class TesteElementosBasicos {

	@Test
	public void testeTextField() {
		WebDriver driver = new FirefoxDriver();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Teste de escrita");
		String textField = driver.findElement(By.id("elementosForm:nome")).getAttribute("value");
		Assert.assertEquals("Teste de escrita", textField);
		driver.quit();	
	}
	
	@Test
	public void deveInteragirComTextArea() throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("Teste");
		String textArea = driver.findElement(By.id("elementosForm:sugestoes")).getAttribute("value");
		Assert.assertEquals("Teste",textArea);
		driver.quit();
	}
	
	@Test
	public void deveInteragirComRadioButton() throws InterruptedException {
		WebDriver driver = new FirefoxDriver();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		driver.findElement(By.id("elementosForm:sexo:0")).click();
		boolean masculino = driver.findElement(By.id("elementosForm:sexo:0")).isSelected();
		Assert.assertTrue(masculino);
		driver.quit();
	}
	
	@Test
	public void deveInteragirComCheckBox() throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		driver.findElement(By.id("elementosForm:comidaFavorita:2")).click();
		boolean comidaFavorita = driver.findElement(By.id("elementosForm:comidaFavorita:2")).isSelected();
		Assert.assertTrue(comidaFavorita);
		driver.quit();
	}
	
	@Test
	public void deveInteragirComComboBox() throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
		Select combo = new Select(element);
		//combo.selectByIndex(2);
		//combo.selectByValue("superior");
		combo.selectByVisibleText("2o grau completo");
		Assert.assertEquals("2o grau completo",combo.getFirstSelectedOption().getText());
		driver.quit();
	}
	
	@Test
	public void deveVerificarValoresCombo() throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
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
		
		driver.quit();
	}
	
	@Test
	public void deveVerificarValoresComboMultiplo() throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
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
		
		driver.quit();
	}
	
	@Test
	public void deveInteragirComBotoes() throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		WebElement botao = driver.findElement(By.id("buttonSimple"));
		botao.click();
		
		Assert.assertEquals("Obrigado!",botao.getAttribute("value"));
		driver.quit();
	}	

	@Test
	public void deveInteragirComLinks() throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		WebElement link = driver.findElement(By.linkText("Voltar"));
		Assert.assertEquals("Voltar",link.getText());
		link.click();

		WebElement resultado = driver.findElement(By.id("resultado"));	
		Assert.assertEquals("Voltou!", resultado.getText());
		driver.quit();
	}
	
	@Test
	public void deveBuscarTextoNaPagina() throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		
		//Assert.assertTrue(driver.findElement(By.tagName("body")).getText().contains("Campo de Treinamento"));
				
		WebElement titulo = driver.findElement(By.tagName("h3"));
		Assert.assertEquals("Campo de Treinamento", titulo.getText());
		
		WebElement cuidado = driver.findElement(By.className("facilAchar"));
		Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", cuidado.getText());
		driver.quit();
	}	
}
