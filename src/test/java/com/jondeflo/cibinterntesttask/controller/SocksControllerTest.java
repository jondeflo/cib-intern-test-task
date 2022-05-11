package com.jondeflo.cibinterntesttask.controller;

import com.jondeflo.cibinterntesttask.model.Socks;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SocksControllerTest {

	private final MockMvc mvc;
	private final ObjectMapper mapper = new ObjectMapper();

	@Autowired
	public SocksControllerTest(MockMvc mvc) {
		this.mvc = mvc;
	}

	@Test
	public void getSuccessWithString() throws Exception {

		mvc.perform(get("/api/socks?color=yellow&operation=equal&cottonPart=90"))
				.andExpect(status().isOk());
	}

	@Test
	public void getSuccessWithParams() throws Exception {

		mvc.perform(get("/api/socks")
						.param("operation", String.valueOf("equal"))
						.param("color", "red")
						.param("cottonPart", "10"))
				.andExpect(status().isOk());
	}

	@Test
	public void getBadGetRequest() throws Exception {

		mvc.perform(get("/api/socks?color=yellow&operation=equal&cottonPart="))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void getBadPostRequest() throws Exception {

		mvc.perform(post("/api/socks/income")
						.contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(new Socks("brown", 75, -1)))
						.characterEncoding("UTF-8"))
				.andExpect(status().isBadRequest());
	}
}
