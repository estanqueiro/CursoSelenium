import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import junit.framework.Assert;

public class TesteCadastro {

	@Test
	public void deveFazerCadastroComSucesso() {
		WebDriver driver = new ChromeDriver();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Paulo");
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Estanqueiro");
		driver.findElement(By.id("elementosForm:sexo:0")).click();
		driver.findElement(By.id("elementosForm:comidaFavorita:2")).click();
		
		WebElement escola = driver.findElement(By.id("elementosForm:escolaridade"));
		Select comboEscolaridade = new Select(escola);
		comboEscolaridade.selectByVisibleText("Especializacao");
		
		WebElement esportes = driver.findElement(By.id("elementosForm:esportes"));
		Select comboEsportes = new Select(esportes);
		comboEsportes.selectByVisibleText("Futebol");
		
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		
		String cadastro = driver.findElement(By.xpath("//div[@id='resultado']/span")).getText();
		String nome = driver.findElement(By.xpath("//div[@id='descNome']")).getText();
		String sobrenome = driver.findElement(By.xpath("//div[@id='descSobrenome']")).getText();
		String sexo = driver.findElement(By.id("descSexo")).getText();
		String comida = driver.findElement(By.id("descComida")).getText();
		String escolaridade = driver.findElement(By.id("descEscolaridade")).getText();
		String esporte = driver.findElement(By.id("descEsportes")).getText();
		String sugestoes = driver.findElement(By.id("descSugestoes")).getText();
		
		Assert.assertEquals("Cadastrado!", cadastro);
		Assert.assertEquals("Nome: Paulo", nome);
		Assert.assertEquals("Sobrenome: Estanqueiro", sobrenome);
		Assert.assertEquals("Sexo: Masculino", sexo);
		Assert.assertEquals("Comida: Pizza", comida);
		Assert.assertEquals("Escolaridade: especializacao", escolaridade);
		Assert.assertEquals("Esportes: Futebol", esporte);
		Assert.assertEquals("Sugestoes:", sugestoes);
		
		driver.quit();
	}
}
