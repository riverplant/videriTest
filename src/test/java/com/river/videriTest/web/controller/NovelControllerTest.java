package com.river.videriTest.web.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.river.videriTest.App;

/**
 * 
 * @author riverplant
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes=App.class)
public class NovelControllerTest {

	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mockMvc;
		
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}
	
	@Test
	public void whenQuerySuccess() throws Exception {
		String result = mockMvc.perform(MockMvcRequestBuilders.get("/novel")
				.accept(MediaType.APPLICATION_JSON_UTF8))
	           .andExpect(MockMvcResultMatchers.status().isOk())
	           .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3))
	           .andReturn().getResponse().getContentAsString();
		System.out.println(result);
	}
	@Test
	public void whenGetInfoSuccess() throws Exception {
		String result = mockMvc.perform(MockMvcRequestBuilders.get("/novel/4")
			.accept(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$.name").value(" Notre-Dame de Paris"))
			.andReturn().getResponse().getContentAsString();
		System.out.println(result);
			
	}
	@Test
	public void whenCreateSuccess() throws Exception {
		String content = "{\"id\":null,\"name\":\"Toute la Lyre\", \"author\":\"1\"}";
		String result = mockMvc.perform(MockMvcRequestBuilders.post("/novel").content(content).contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$.id").value("6"))
			.andReturn().getResponse().getContentAsString();
		System.out.println(result);
	}
	
	@Test
	public void whenUpdateSuccess() throws Exception {
		String content = "{\"id\":5,\"name\":\"Toute la Lyre222\", \"author\":\"1\"}";
		mockMvc.perform(MockMvcRequestBuilders.put("/novel/5").content(content).contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Toute la Lyre222"));
	}
	
	@Test
	public void whenDeleteSuccess() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/book/4").contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
}
