import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteCadastro {

	private WebDriver driver;
	private DSL dsl;
	
	@Before
	public void inicializa() {
		driver = new FirefoxDriver();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL(driver);
	}
	
	@Test
	public void deveFazerCadastroComSucesso() {
		dsl.escrever("elementosForm:nome", "Paulo");
		dsl.escrever("elementosForm:sobrenome", "Estanqueiro");		
		dsl.clicarRadio("elementosForm:sexo:0");
		dsl.clicarCheck("elementosForm:comidaFavorita:2");
		dsl.selecionarCombo("elementosForm:escolaridade", "Especializacao");
		dsl.selecionarCombo("elementosForm:esportes", "Futebol");
		dsl.clicarBotao("elementosForm:cadastrar");

		Assert.assertTrue(dsl.obterTexto("resultado").startsWith("Cadastrado!"));
		Assert.assertTrue(dsl.obterTexto("descNome").endsWith("Paulo"));
		Assert.assertEquals("Sobrenome: Estanqueiro", dsl.obterTexto(By.id("descSobrenome")));
		Assert.assertEquals("Sexo: Masculino", dsl.obterTexto(By.id("descSexo")));
		Assert.assertEquals("Comida: Pizza", dsl.obterTexto(By.id("descComida")));
		Assert.assertEquals("Escolaridade: especializacao", dsl.obterTexto(By.id("descEscolaridade")));
		Assert.assertEquals("Esportes: Futebol", dsl.obterTexto(By.id("descEsportes")));
		Assert.assertEquals("Sugestoes:", dsl.obterTexto(By.id("descSugestoes")));
	}
	
	@Test
	public void deveValidarNomeObrigatorio() {
		dsl.clicarBotao("elementosForm:cadastrar");
		Assert.assertEquals("Nome eh obrigatorio", dsl.alertaObterTextoEAceita());
	}
	
	@Test
	public void deveValidarSobrenomeObrigatorio() {
		dsl.escrever("elementosForm:nome", "Paulo");
		dsl.clicarBotao("elementosForm:cadastrar");
		Assert.assertEquals("Sobrenome eh obrigatorio", dsl.alertaObterTextoEAceita());
	}
	
	@Test
	public void deveValidarSexoObrigatorio() {
		dsl.escrever("elementosForm:nome", "Paulo");
		dsl.escrever("elementosForm:sobrenome", "Estanqueiro");		
		dsl.clicarBotao("elementosForm:cadastrar");
		Assert.assertEquals("Sexo eh obrigatorio", dsl.alertaObterTextoEAceita());
	}
	
	@Test
	public void deveValidarComidaVegetariana() {
		dsl.escrever("elementosForm:nome", "Paulo");
		dsl.escrever("elementosForm:sobrenome", "Estanqueiro");
		dsl.clicarRadio("elementosForm:sexo:0");
		dsl.clicarRadio("elementosForm:comidaFavorita:0");
		dsl.clicarRadio("elementosForm:comidaFavorita:3");
		dsl.clicarBotao("elementosForm:cadastrar");
		Assert.assertEquals("Tem certeza que voce eh vegetariano?", dsl.alertaObterTextoEAceita());
	}

	@Test
	public void deveValidarEsportistaIndeciso() {
		dsl.escrever("elementosForm:nome", "Paulo");
		dsl.escrever("elementosForm:sobrenome", "Estanqueiro");
		dsl.clicarRadio("elementosForm:sexo:0");
		dsl.clicarRadio("elementosForm:comidaFavorita:0");
		dsl.selecionarCombo("elementosForm:esportes", "Natacao");
		dsl.selecionarCombo("elementosForm:esportes", "O que eh esporte?");
		dsl.clicarBotao("elementosForm:cadastrar");
		Assert.assertEquals("Voce faz esporte ou nao?", dsl.alertaObterTextoEAceita());
	}
	
	@After
	public void finaliza() {
	driver.quit();
	}
}
