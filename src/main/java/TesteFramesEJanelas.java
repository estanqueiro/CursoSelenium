import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteFramesEJanelas {
	private WebDriver driver = new FirefoxDriver();
	
	@Before
	public void inicializa() {
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
	}
	
	@Test
	public void deveInteragirComFrames() {	
		driver.switchTo().frame("frame1");
		driver.findElement(By.id("frameButton")).click();
		
		Alert alert = driver.switchTo().alert();
		String alerta = alert.getText();
		Assert.assertEquals("Frame OK!", alerta);
		alert.accept();
	
		driver.switchTo().defaultContent();
		driver.findElement(By.id("elementosForm:nome")).sendKeys(alerta);;
	}
	
	@Test
	public void deveInteragirComJanelas() {	
		driver.findElement(By.id("buttonPopUpEasy")).click();
		driver.switchTo().window("Popup");
		driver.findElement(By.tagName("textarea")).sendKeys("Deu certo?");
		driver.close();
		
		driver.switchTo().window("");
		driver.findElement(By.tagName("textarea")).sendKeys("e agora?");
	}
	
	@Test
	public void deveInteragirComJanelasSemId() {	
		driver.findElement(By.id("buttonPopUpHard")).click();
		System.out.println(driver.getWindowHandle());
		System.out.println(driver.getWindowHandles());	
		driver.switchTo().window((String) driver.getWindowHandles().toArray()[1]);
		driver.findElement(By.tagName("textarea")).sendKeys("Deu certo?");
		driver.switchTo().window((String) driver.getWindowHandles().toArray()[0]);
		driver.findElement(By.tagName("textarea")).sendKeys("E agora?");
	}
	
	@After
	public void finaliza() {
		driver.quit();
	}
}

