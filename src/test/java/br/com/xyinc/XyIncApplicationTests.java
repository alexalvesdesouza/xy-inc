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

  private MockMvc               mockMvc;

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
    mockMvc.perform(get(URL))
           .andExpect(status().isNotFound());
    System.out.println("--> PASSOU NO TESTE!!!");
  }

  @Test
  public void teste2validaCoordenadasNulas() throws Exception {
    System.out.println("--> TESTE 2 VALIDANDO TENTATIVA DE INSERÇÃO PONTO DE INTERESSE COM COORDENADAS NULAS!!!");

    PontoInteresse poi = new PontoInteresse(null,
                                            "Iaçã Açaí",
                                            null,
                                            null);
    poi.setErrosEncontrados(new ArrayList<String>());

    mockMvc.perform(post(URL).contentType(MediaType.APPLICATION_JSON)
                             .content(asJsonString(poi)))
           .andExpect(status().isConflict());

    System.out.println("--> PASSOU NO TESTE!!!");
  }

  @Test
  public void teste3validaCoordenadasNegativas() throws Exception {
    System.out.println("--> TESTE 3 VALIDANDO TENTATIVA DE INSERÇÃO DE PONTO DE INTERESSE COM COORDENADAS NEGATIVAS!!!");

    PontoInteresse poi = new PontoInteresse(null,
                                            "Iaçã Açaí",
                                            -20,
                                            -10);
    poi.setErrosEncontrados(new ArrayList<String>());

    mockMvc.perform(post(URL).contentType(MediaType.APPLICATION_JSON)
                             .content(asJsonString(poi)))
           .andExpect(status().isConflict());

    System.out.println("--> PASSOU NO TESTE!!!");
  }

  @Test
  public void teste4validaNomePontoIntresseNulo() throws Exception {
    System.out.println("--> TESTE 4 VALIDANDO TENTATIVA DE INSERÇÃO DE PONTO DE INTERESSE COM NOME NULO!!!");

    PontoInteresse poi = new PontoInteresse(null,
                                            null,
                                            10,
                                            10);
    poi.setErrosEncontrados(new ArrayList<String>());

    mockMvc.perform(post(URL).contentType(MediaType.APPLICATION_JSON)
                             .content(asJsonString(poi)))
           .andExpect(status().isConflict());

    System.out.println("--> PASSOU NO TESTE!!!");
  }

  @Test
  public void teste5validaTodosOsAtributosNulos() throws Exception {
    System.out.println("--> TESTE 5 VALIDANDO TENTATIVA DE INSERÇÃO DE PONTO DE INTERESSE COM TODOS OS ATRIBUTOS NULOS!!!");

    PontoInteresse poi = new PontoInteresse(null,
                                            null,
                                            null,
                                            null);
    poi.setErrosEncontrados(new ArrayList<String>());

    mockMvc.perform(post(URL).contentType(MediaType.APPLICATION_JSON)
                             .content(asJsonString(poi)))
           .andExpect(status().isConflict());

    System.out.println("--> PASSOU NO TESTE!!!");
  }

  @Test
  public void teste6ValidandoInsercaoCorretaDePontosInteresse() throws Exception {
    System.out.println("--> TESTE 6 VALIDANDO INSERÇÃO CORRETA DE PONTOS DE INTERESSE!!!");

    PontoInteresse poi = new PontoInteresse(null,
                                            "Iaçã Açaí",
                                            20,
                                            10);
    poi.setErrosEncontrados(new ArrayList<String>());

    mockMvc.perform(post(URL).contentType(MediaType.APPLICATION_JSON)
                             .content(asJsonString(poi)))
           .andExpect(status().isCreated());

    System.out.println("--> PASSOU NO TESTE!!!");
  }

  @Test
  public void teste7ValidandoBuscaDeTodosPontosDeInteresse() throws Exception {
    System.out.println("--> TESTE 7 VALIDANDO SERVIÇO DE BUSCA DE TODOS PONTOS DE INTERESSE!!!");
    mockMvc.perform(get(URL))
           .andExpect(status().isOk());
    System.out.println("--> PASSOU NO TESTE!!!");
  }

  @Test
  public void teste8ValidandoBuscaDePontosDeInteresseProximo() throws Exception {
    System.out.println("--> TESTE 8 VALIDANDO SERVIÇO DE BUSCA DE PONTOS DE INTERESSE PRÓXIMOS - SUCESSO!!!");
    mockMvc.perform(get(URL + "/proximos?coordenadaX=20&coordenadaY=10&distancia=10"))
           .andExpect(status().isOk());
    System.out.println("--> PASSOU NO TESTE!!!");
  }

  @Test
  public void teste9ValidandoBuscaDePontosDeInteresseProximo() throws Exception {
    System.out.println("--> TESTE 9 VALIDANDO SERVIÇO DE BUSCA DE PONTOS DE INTERESSE PRÓXIMOS - FALHA!!!");
    mockMvc.perform(get(URL + "/proximos?coordenadaX=7788&coordenadaY=8877&distancia=10"))
           .andExpect(status().isNotFound());
    System.out.println("--> PASSOU NO TESTE!!!");
  }

  public static String asJsonString(final Object obj) {
    try {
      return new ObjectMapper().writeValueAsString(obj);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}
