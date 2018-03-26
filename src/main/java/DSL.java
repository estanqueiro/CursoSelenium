import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class DSL {

	private WebDriver driver;
	
	public DSL(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public void escrever(String id_campo, String texto) {
		driver.findElement(By.id(id_campo)).sendKeys(texto);
	}
	
	public String obterValorCampo(String id_campo) {
		return driver.findElement(By.id(id_campo)).getAttribute("value");
	}
	
	public void clicarRadioOuCheck(String id) {
		driver.findElement(By.id(id)).click();
	}
	
	public boolean isRadioOuCheckMarcado(String id) {
		return driver.findElement(By.id(id)).isSelected();
	}
	
	public void selecionarCombo(String id, String valor) {
		Select combo = new Select(driver.findElement(By.id(id)));
		combo.selectByVisibleText(valor);
	}
	
	public String obterValorCombo(String id) {
		Select combo = new Select(driver.findElement(By.id(id)));
		return combo.getFirstSelectedOption().getText();
	}
}
