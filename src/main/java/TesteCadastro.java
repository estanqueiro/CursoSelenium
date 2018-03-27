import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteCadastro {

	private WebDriver driver;
	private DSL dsl;
	private CampoTreinamentoPage page;
	
	@Before
	public void inicializa() {
		driver = new FirefoxDriver();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL(driver);
		page = new CampoTreinamentoPage(driver);
	}
	
	@Test
	public void deveFazerCadastroComSucesso() {
		page.setNome("Paulo");
		page.setSobrenome("Estanqueiro");
		page.setSexoMasculino();
		page.setComidaFavoritaPizza();
		page.setEscolaridade("Especializacao");
		page.setEsporte("Futebol");
		page.cadastrar();

		Assert.assertTrue(page.obterResultadoCadastro().startsWith("Cadastrado!"));
		Assert.assertTrue(page.obterNomeCadastro().endsWith("Paulo"));
		Assert.assertEquals("Sobrenome: Estanqueiro", page.obterSobrenomeCadastro());
		Assert.assertEquals("Sexo: Masculino", page.obterSexoCadastro());
		Assert.assertEquals("Comida: Pizza", page.obterComidaCadastro());
		Assert.assertEquals("Escolaridade: especializacao", page.obterEscolaridadeCadastro());
		Assert.assertEquals("Esportes: Futebol", page.obterEsporteCadastro());
		Assert.assertEquals("Sugestoes:", page.obterSugestoesCadastro());
	}
	
	@Test
	public void deveValidarNomeObrigatorio() {
		page.cadastrar();
		Assert.assertEquals("Nome eh obrigatorio", dsl.alertaObterTextoEAceita());
	}
	
	@Test
	public void deveValidarSobrenomeObrigatorio() {
		page.setNome("Paulo");
		page.cadastrar();
		Assert.assertEquals("Sobrenome eh obrigatorio", dsl.alertaObterTextoEAceita());
	}
	
	@Test
	public void deveValidarSexoObrigatorio() {
		page.setNome("Paulo");
		page.setSobrenome("Estanqueiro");		
		page.cadastrar();
		Assert.assertEquals("Sexo eh obrigatorio", dsl.alertaObterTextoEAceita());
	}
	
	@Test
	public void deveValidarComidaVegetariana() {
		page.setNome("Paulo");
		page.setSobrenome("Estanqueiro");
		page.setSexoMasculino();
		page.setComidaFavoritaCarne();
		page.setComidaFavoritaVegetariana();
		page.cadastrar();
		Assert.assertEquals("Tem certeza que voce eh vegetariano?", dsl.alertaObterTextoEAceita());
	}

	@Test
	public void deveValidarEsportistaIndeciso() {
		page.setNome("Paulo");
		page.setSobrenome("Estanqueiro");
		page.setSexoMasculino();
		page.setComidaFavoritaCarne();
		page.setEsporte("Natacao", "O que eh esporte?");
		page.setEsporte("O que eh esporte?");
		page.cadastrar();
		Assert.assertEquals("Voce faz esporte ou nao?", dsl.alertaObterTextoEAceita());
	}
	
	@After
	public void finaliza() {
	driver.quit();
	}
}
