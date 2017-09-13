package br.com.xyinc;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class XyIncApplicationTests {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setup() throws Exception {

		this.mockMvc = webAppContextSetup(webApplicationContext).build();

	}

	@Test
	public void nenhumPontoInteresseEncontrado() {
		// TODO Ajustar nome do teste e ordem de execução
	}

	@Test
	public void validaCoordenadasNulas() {
		// TODO Ajustar nome do teste e ordem de execução
	}

	@Test
	public void validaCoordenadasNegativas() {
		// TODO Ajustar nome do teste e ordem de execução
	}

	@Test
	public void validaNomePontoInteresseNulo() {
		// TODO Ajustar nome do teste e ordem de execução
	}

	@Test
	public void inserindoPontosInteresse() {
		// TODO Ajustar nome do teste e ordem de execução
	}

	@Test
	public void localizandoTodosPontosInteresse() {
		// TODO Ajustar nome do teste e ordem de execução
	}

	@Test
	public void localizandoPontosInteressePorCoordenadasEDistancia() {
		// TODO Ajustar nome do teste e ordem de execução
	}

}
