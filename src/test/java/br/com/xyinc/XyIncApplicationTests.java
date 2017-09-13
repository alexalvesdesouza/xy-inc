package br.com.xyinc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.xyinc.entities.PontoInteresse;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class XyIncApplicationTests {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setup() throws Exception {

		this.mockMvc = webAppContextSetup(webApplicationContext).build();
	

	}

	private static final String URL = "http://localhost:8080/pontos-interesse";

	@Test
	public void teste1nenhumPontoInteresseEncontrado() throws Exception {
		System.out.println("--> TESTE NÃO ENCONTROU NENHUM PONTO DE INTERESSE!!!");
		mockMvc.perform(get(URL)).andExpect(status().isNotFound());
		System.out.println("--> PASSOU NO TESTE!!!");
	}

	@Test
	public void teste2validaCoordenadasNulas() throws Exception {
		System.out.println("--> TESTE VALIDANDO PONTOS NULOS!!!");

		PontoInteresse poi = new PontoInteresse(null, "Iaçã Açaí", null, 10);
		poi.setErrosEncontrados(new ArrayList<String>());

		mockMvc.perform(post(URL).contentType(MediaType.APPLICATION_JSON).content(asJsonString(poi)))
				.andExpect(status().isConflict());

		System.out.println("--> PASSOU NO TESTE!!!");
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	
	//
	// @Test
	// public void validaCoordenadasNulas() {
	// // TODO Ajustar nome do teste e ordem de execução
	// }
	//
	// @Test
	// public void validaCoordenadasNegativas() {
	// // TODO Ajustar nome do teste e ordem de execução
	// }
	//
	// @Test
	// public void validaNomePontoInteresseNulo() {
	// // TODO Ajustar nome do teste e ordem de execução
	// }
	//
	// @Test
	// public void inserindoPontosInteresse() {
	// // TODO Ajustar nome do teste e ordem de execução
	// }
	//
	// @Test
	// public void localizandoTodosPontosInteresse() {
	// // TODO Ajustar nome do teste e ordem de execução
	// }
	//
	// @Test
	// public void localizandoPontosInteressePorCoordenadasEDistancia() {
	// // TODO Ajustar nome do teste e ordem de execução
	// }

}
