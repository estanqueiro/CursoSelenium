import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class TesteRegrasNegocio {
	private WebDriver driver = new FirefoxDriver();
	
	@Before
	public void inicializa() {
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
	}
	
	@Test
	public void deveValidarNomeObrigatorio() {
		driver.findElement(By.id("elementosForm:nome"));
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Nome eh obrigatorio",alert.getText());
	}
	
	@Test
	public void deveValidarSobrenomeObrigatorio() {
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Paulo");
		driver.findElement(By.id("elementosForm:sobrenome"));
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Sobrenome eh obrigatorio",alert.getText());
	}

	@Test
	public void deveValidarSexoObrigatorio() {
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Paulo");
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Estanqueiro");
		driver.findElement(By.id("elementosForm:sexo:0"));
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Sexo eh obrigatorio",alert.getText());
	}
	
	@Test
	public void deveValidarComidaVegetariana() {
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Paulo");
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Estanqueiro");
		driver.findElement(By.id("elementosForm:sexo:0")).click();
		driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
		driver.findElement(By.id("elementosForm:comidaFavorita:3")).click();
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Tem certeza que voce eh vegetariano?",alert.getText());
	}
	
	@Test
	public void deveValidarEsportistaIndeciso() {
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Paulo");
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Estanqueiro");
		driver.findElement(By.id("elementosForm:sexo:0")).click();
		driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
		
		Select combo = new Select(driver.findElement(By.id("elementosForm:esportes")));	
		combo.selectByVisibleText("Natacao");
		combo.selectByVisibleText("O que eh esporte?");
		
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Voce faz esporte ou nao?",alert.getText());
	}
	
	@After
	public void finaliza() {
		driver.quit();
	}
}
