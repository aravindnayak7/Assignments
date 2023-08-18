package com.stackroute.newz.commander.controller;

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
import com.stackroute.newz.controller.NewsController;
import com.stackroute.newz.model.News;
import com.stackroute.newz.model.NewsSource;
import com.stackroute.newz.service.NewsService;
import com.stackroute.newz.util.exception.NewsAlreadyExistsException;
import com.stackroute.newz.util.exception.NewsNotFoundException;

@SpringBootTest
class NewsControllerTest {

	private MockMvc mockMvc;
	private News news;
	private List<News> newsList;

	@Mock
	NewsService newsService;
	@InjectMocks
	NewsController newsController;

	@BeforeEach
	public void setUp() {
		
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(newsController).build();
		newsList = new ArrayList<News>();
		news = new News(1, "sample title", "johnsmith", "sample description", LocalDateTime.now(), "sample content",
				null, null, null, new NewsSource("nd", "NewsDaily"));
		News news1 = new News(2, "sample title2", "johnsmith", "sample description2", LocalDateTime.now(),
				"sample content2", null, null, null, new NewsSource("nd", "NewsDaily"));
		News news2 = new News(3, "sample title3", "johnsmith", "sample description3", LocalDateTime.now(),
				"sample content3", null, null, null, new NewsSource("nd", "NewsDaily"));
		newsList.add(news1);
		newsList.add(news2);
	}

	@Test
	public void getAllNewsSuccess() throws Exception {

		when(newsService.listAllNews()).thenReturn(newsList);
		mockMvc.perform(get("/api/v1/news").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void getByNewsIdSuccess() throws Exception {

		when(newsService.getNewsById(1)).thenReturn(news);
		mockMvc.perform(get("/api/v1/news?newsId=1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andDo(MockMvcResultHandlers.print());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void getByNewsIdFailure() throws Exception {

		when(newsService.getNewsById(2)).thenThrow(NewsNotFoundException.class);
		mockMvc.perform(get("/api/v1/news?newsId=2").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound())
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void addNewsSuccess() throws Exception {

		when(newsService.addNews(any())).thenReturn(news);
		mockMvc.perform(post("/api/v1/news").contentType(MediaType.APPLICATION_JSON).content(asJsonString(news)))
				.andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());

	}

	@SuppressWarnings("unchecked")
	@Test
	public void addNewsFailure() throws Exception {

		when(newsService.addNews(any())).thenThrow(NewsAlreadyExistsException.class);
		mockMvc.perform(post("/api/v1/news").contentType(MediaType.APPLICATION_JSON).content(asJsonString(news)))
				.andExpect(status().isConflict()).andDo(MockMvcResultHandlers.print());

	}

	@Test
	public void updateNewsSuccess() throws Exception {

		when(newsService.updateNews(any())).thenReturn(news);
		mockMvc.perform(put("/api/v1/news/1").contentType(MediaType.APPLICATION_JSON).content(asJsonString(news)))
				.andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());

	}

	@SuppressWarnings("unchecked")
	@Test
	public void updateNewsFailure() throws Exception {

		when(newsService.updateNews(any())).thenThrow(NewsNotFoundException.class);
		mockMvc.perform(put("/api/v1/news/2").contentType(MediaType.APPLICATION_JSON).content(asJsonString(news)))
				.andExpect(status().isNotFound()).andDo(MockMvcResultHandlers.print());

	}

	@Test
	public void deleteNewsSuccess() throws Exception {
		doNothing().when(newsService).deleteNews(anyInt());
		mockMvc.perform(delete("/api/v1/news/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andDo(MockMvcResultHandlers.print());

	}

	@Test
	public void deleteNewsFailure() throws Exception {
		doThrow(NewsNotFoundException.class).doNothing().when(newsService).deleteNews(anyInt());
		mockMvc.perform(delete("/api/v1/news/2").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound()).andDo(MockMvcResultHandlers.print());

	}
	
	
	@Test
	public void getBySourceNameSuccess() throws Exception {

		when(newsService.listNewsByNewsSource(any())).thenReturn(newsList);
		mockMvc.perform(get("/api/v1/news?sourceName=newsdaily").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	public void getBySourceNameFailure() throws Exception {

		when(newsService.listNewsByNewsSource(any())).thenReturn(null);
		mockMvc.perform(get("/api/v1/news?sourceName=freshnews").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andDo(MockMvcResultHandlers.print());
	}

	

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
