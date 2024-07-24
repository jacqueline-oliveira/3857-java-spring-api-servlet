package br.com.alura.codechella_servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.time.LocalDate;

@SpringBootTest
@AutoConfigureMockMvc
class CodechellaServletApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void cadastraNovoEvento() throws Exception {
		EventoDto dto = new EventoDto(null, TipoEvento.SHOW, "Kiss",
				LocalDate.parse("2025-01-01"), "Show da melhor banda que existe");

		mockMvc.perform(post("/eventos")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(dto)))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.id").isNotEmpty())
				.andExpect(jsonPath("$.tipo").value(dto.tipo().toString()))
				.andExpect(jsonPath("$.nome").value(dto.nome()))
				.andExpect(jsonPath("$.data").value(dto.data().toString()))
				.andExpect(jsonPath("$.descricao").value(dto.descricao()));
	}

	@Test
	void buscarEvento() throws Exception {
		EventoDto dto = new EventoDto(13L, TipoEvento.SHOW, "The Weeknd",
				LocalDate.parse("2025-11-02"), "Um show eletrizante ao ar livre com muitos efeitos especiais.");

		mockMvc.perform(get("/eventos"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[12].id").value(dto.id()))
				.andExpect(jsonPath("$[12].tipo").value(dto.tipo().toString()))
				.andExpect(jsonPath("$[12].nome").value(dto.nome()))
				.andExpect(jsonPath("$[12].data").value(dto.data().toString()))
				.andExpect(jsonPath("$[12].descricao").value(dto.descricao()));
	}

}
