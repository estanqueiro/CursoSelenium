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
		
		WebElement escolaridade = driver.findElement(By.id("elementosForm:escolaridade"));
		Select comboEscolaridade = new Select(escolaridade);
		comboEscolaridade.selectByVisibleText("Especializacao");
		
		WebElement esportes = driver.findElement(By.id("elementosForm:esportes"));
		Select comboEsportes = new Select(esportes);
		comboEsportes.selectByVisibleText("Futebol");
		
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		
		WebElement cadastro = driver.findElement(By.id("resultado"));
		Assert.assertEquals("Cadastrado!", actual);
		Assert.assertEquals("Nome: Paulo", actual);
		Assert.assertEquals("Sobrenome: Estanqueiro", actual);
		Assert.assertEquals("Sexo: Masculino", actual);
		Assert.assertEquals("Comida: Pizza", actual);
		Assert.assertEquals("Escolaridade: Especializacao", actual);
		Assert.assertEquals("Esportes: Futebol", actual);
		Assert.assertEquals("Sugestoes:", actual);
	}
}
