package com.stackroute.newz.test.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.annotation.Rollback;

import com.stackroute.newz.model.News;
import com.stackroute.newz.model.NewsSource;
import com.stackroute.newz.repository.NewsRepository;
import com.stackroute.newz.repository.NewsSourceRepository;
import com.stackroute.newz.service.NewsServiceImpl;
import com.stackroute.newz.util.exception.NewsAlreadyExistsException;
import com.stackroute.newz.util.exception.NewsNotFoundException;

class NewsServiceTest {

	@Mock
	private NewsRepository newsRepository;
	
	@Mock
	private NewsSourceRepository newsSourceRepository;
	
	@InjectMocks
	private NewsServiceImpl newsService;

	private News news;

	private List<News> newsList;

	@BeforeEach
	public void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);
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

	@AfterEach
	public void tearDown() throws Exception {
		news = null;
		newsList = null;
	}

	@Test
	@Rollback(true)
	public void testAddNewsSuccess() throws NewsAlreadyExistsException {

		when(newsRepository.findById(any())).thenReturn(Optional.ofNullable(null));
		when(newsRepository.save(any())).thenReturn(news);

		assertEquals(news, newsService.addNews(news));

		verify(newsRepository, times(1)).findById(any());
		verify(newsRepository, times(1)).save(any());

	}

	@Test
	@Rollback(true)
	public void testAddNewsFailure() throws NewsAlreadyExistsException {

		when(newsRepository.findById(any())).thenReturn(Optional.ofNullable(news));

		assertThrows(NewsAlreadyExistsException.class, () -> newsService.addNews(news));

		verify(newsRepository, times(1)).findById(any());
		verify(newsRepository, times(0)).save(any());

	}

	@Test
	@Rollback(true)
	public void testGetNewsSuccess() throws NewsNotFoundException {

		when(newsRepository.findById(any())).thenReturn(Optional.ofNullable(news));

		assertEquals(news, newsService.getNewsById(news.getNewsId()));

		verify(newsRepository, times(1)).findById(any());

	}

	@Test
	@Rollback(true)
	public void testGetNewsFailure() throws NewsNotFoundException {

		when(newsRepository.findById(any())).thenReturn(Optional.empty());

		assertThrows(NewsNotFoundException.class, () -> newsService.getNewsById(news.getNewsId()));

		verify(newsRepository, times(1)).findById(any());

	}

	@Test
	@Rollback(true)
	public void testGetAllNewsSuccess() {

		when(newsRepository.findAll()).thenReturn(newsList);

		assertEquals(newsList, newsService.listAllNews());

		verify(newsRepository, times(1)).findAll();

	}

	@Test
	@Rollback(true)
	public void testUpdateNewsSuccess() throws NewsNotFoundException {

		when(newsRepository.findById(any())).thenReturn(Optional.ofNullable(news));
		when(newsRepository.save(any())).thenReturn(news);

		assertEquals(news, newsService.updateNews(news));

		verify(newsRepository, times(1)).findById(any());
		verify(newsRepository, times(1)).save(any());

	}

	@Test
	@Rollback(true)
	public void testUpdateNewsFailure() throws NewsNotFoundException {

		when(newsRepository.findById(any())).thenReturn(Optional.ofNullable(null));

		assertThrows(NewsNotFoundException.class, () -> newsService.updateNews(news));

		verify(newsRepository, times(1)).findById(any());
		verify(newsRepository, times(0)).save(any());

	}

	@Test
	@Rollback(true)
	public void testDeleteNewsSuccess() throws NewsNotFoundException {

		when(newsRepository.findById(any())).thenReturn(Optional.ofNullable(news));
		newsService.deleteNews(news.getNewsId());

		verify(newsRepository, times(1)).findById(any());
		verify(newsRepository, times(1)).deleteById(any());

	}

	@Test
	@Rollback(true)
	public void testDeleteNewsFailure() throws NewsNotFoundException {

		when(newsRepository.findById(any())).thenReturn(Optional.ofNullable(null));

		assertThrows(NewsNotFoundException.class, () -> newsService.deleteNews(news.getNewsId()));

		verify(newsRepository, times(1)).findById(any());
		verify(newsRepository, times(0)).deleteById(any());

	}
	
	@Test
	@Rollback(true)
	public void testListNewsByAuthorSuccess() {

		when(newsRepository.findByAuthor(any())).thenReturn(newsList);

		assertEquals(newsList, newsService.listNewsByAuthor(news.getAuthor()));

		verify(newsRepository, times(1)).findByAuthor((any()));

	}
	
	@Test
	@Rollback(true)
	public void testGetNewsBySourceSuccess() {

		when(newsRepository.findByNewsSource(any())).thenReturn(newsList);

		assertEquals(newsList, newsService.listNewsByNewsSource((news.getNewsSource()).getSourceName()));

		verify(newsRepository, times(1)).findByNewsSource(any());

	}

	@Test
	@Rollback(true)
	public void testGetNewsBySourceFailure() {

		when(newsRepository.findByNewsSource(any())).thenReturn(null);

		assertEquals(null, newsService.listNewsByNewsSource((news.getNewsSource()).getSourceName()));

		verify(newsRepository, times(1)).findByNewsSource(any());

	}

}
