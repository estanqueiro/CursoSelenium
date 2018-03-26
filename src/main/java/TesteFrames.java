import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteFrames {
	@Test
	public void deveInteragirComFrames() {
		WebDriver driver = new ChromeDriver();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		
		driver.switchTo().frame("frame1");
		driver.findElement(By.id("frameButton")).click();
		
		Alert alert = driver.switchTo().alert();
		String alerta = alert.getText();
		Assert.assertEquals("Frame OK!", alerta);
		alert.accept();
	
		driver.switchTo().defaultContent();
		driver.findElement(By.id("elementosForm:nome")).sendKeys(alerta);;
	}
}
