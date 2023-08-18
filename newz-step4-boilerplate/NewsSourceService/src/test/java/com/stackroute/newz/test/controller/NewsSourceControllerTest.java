package com.stackroute.newz.test.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.newz.controller.NewsSourceController;
import com.stackroute.newz.model.NewsSource;
import com.stackroute.newz.service.NewsSourceService;
import com.stackroute.newz.util.exception.NewsSourceAlreadyExistsException;
import com.stackroute.newz.util.exception.NewsSourceNotFoundException;

@SpringBootTest
class NewsControllerTest {

	private MockMvc mockMvc;
	private NewsSource newsSource;
	private List<NewsSource> newsSourceList;

	@Mock
	NewsSourceService newsSourceService;
	@InjectMocks
	NewsSourceController newsSourceController;

	@BeforeEach
	public void setUp() {
		
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(newsSourceController).build();
		newsSourceList = new ArrayList<NewsSource>();
		
		newsSource = new NewsSource(1, "ABC news", "Best sports news channel", "John", LocalDateTime.now());
		NewsSource newsSource1 = new NewsSource(2, "XYZ news", "Best sports news channel", "John", LocalDateTime.now());
		NewsSource newsSource2 = new NewsSource(3, "PQR news", "Best regional news channel", "John", LocalDateTime.now());
		newsSourceList.add(newsSource1);
		newsSourceList.add(newsSource2);
	}

	@Test
	public void getAllNewsSourcesSuccess() throws Exception {

		when(newsSourceService.listAllNewsSources()).thenReturn(newsSourceList);
		mockMvc.perform(get("/api/v1/newssource").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void getBySourceIdSuccess() throws Exception {

		when(newsSourceService.getNewsSourceById(1)).thenReturn(newsSource);
		mockMvc.perform(get("/api/v1/newssource?sourceId=1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andDo(MockMvcResultHandlers.print());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void getBySourceIdFailure() throws Exception {

		when(newsSourceService.getNewsSourceById(2)).thenThrow(NewsSourceNotFoundException.class);
		mockMvc.perform(get("/api/v1/newssource?sourceId=2").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound())
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void addNewsSourceSuccess() throws Exception {

		when(newsSourceService.addNewsSource(any())).thenReturn(newsSource);
		mockMvc.perform(post("/api/v1/newssource").contentType(MediaType.APPLICATION_JSON).content(asJsonString(newsSource)))
				.andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());

	}

	@SuppressWarnings("unchecked")
	@Test
	public void addNewsSourceFailure() throws Exception {

		when(newsSourceService.addNewsSource(any())).thenThrow(NewsSourceAlreadyExistsException.class);
		mockMvc.perform(post("/api/v1/newssource").contentType(MediaType.APPLICATION_JSON).content(asJsonString(newsSource)))
				.andExpect(status().isConflict()).andDo(MockMvcResultHandlers.print());

	}

	@Test
	public void updateNewsSourceSuccess() throws Exception {

		when(newsSourceService.updateNewsSource(any(), anyInt())).thenReturn(newsSource);
		
		mockMvc.perform(put("/api/v1/newssource/1").contentType(MediaType.APPLICATION_JSON).content(asJsonString(newsSource)))
				.andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());

	}


	@Test
	public void updateNewsSourceFailure() throws Exception {

		when(newsSourceService.updateNewsSource(any(),anyInt())).thenThrow(NewsSourceNotFoundException.class);
		mockMvc.perform(put("/api/v1/newssource/2").contentType(MediaType.APPLICATION_JSON).content(asJsonString(newsSource)))
				.andExpect(status().isNotFound()).andDo(MockMvcResultHandlers.print());

	}

	@Test
	public void deleteNewsSourceSuccess() throws Exception {
		doNothing().when(newsSourceService).deleteNewsSource(anyInt());
		mockMvc.perform(delete("/api/v1/newssource/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andDo(MockMvcResultHandlers.print());

	}

	@Test
	public void deleteNewsSourceFailure() throws Exception {
		doThrow(NewsSourceNotFoundException.class).doNothing().when(newsSourceService).deleteNewsSource(anyInt());
		mockMvc.perform(delete("/api/v1/newssource/2").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound()).andDo(MockMvcResultHandlers.print());

	}
	


	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
