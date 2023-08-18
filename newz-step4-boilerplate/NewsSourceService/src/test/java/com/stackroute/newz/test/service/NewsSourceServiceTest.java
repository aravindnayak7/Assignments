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

import com.stackroute.newz.model.NewsSource;
import com.stackroute.newz.repository.NewsSourceRepository;
import com.stackroute.newz.service.NewsSourceServiceImpl;
import com.stackroute.newz.util.exception.NewsSourceAlreadyExistsException;
import com.stackroute.newz.util.exception.NewsSourceNotFoundException;


class NewsServiceTest {

	@Mock
	private NewsSourceRepository newsSourceRepository;
	
	@InjectMocks
	private NewsSourceServiceImpl newsSourceService;

	private NewsSource newsSource;

	private List<NewsSource> newsSourceList;

	@BeforeEach
	public void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);
		newsSourceList = new ArrayList<NewsSource>();
		newsSource = new NewsSource(1, "ABC news", "Best sports news channel", "John", LocalDateTime.now());
		NewsSource newsSource1 = new NewsSource(2, "XYZ news", "Best sports news channel", "John", LocalDateTime.now());
		NewsSource newsSource2 = new NewsSource(3, "PQR news", "Best regional news channel", "John", LocalDateTime.now());
		newsSourceList.add(newsSource1);
		newsSourceList.add(newsSource2);

	}

	@AfterEach
	public void tearDown() throws Exception {
		newsSource = null;
		newsSourceList = null;
	}

	@Test
	@Rollback(true)
	public void testAddNewsSourceSuccess() throws NewsSourceAlreadyExistsException {

		when(newsSourceRepository.findById(any())).thenReturn(Optional.ofNullable(null));
		when(newsSourceRepository.save(any())).thenReturn(newsSource);

		assertEquals(newsSource, newsSourceService.addNewsSource(newsSource));

		verify(newsSourceRepository, times(1)).findById(any());
		verify(newsSourceRepository, times(1)).save(any());

	}

	@Test
	@Rollback(true)
	public void testAddNewsSourceFailure() throws NewsSourceAlreadyExistsException {

		when(newsSourceRepository.findById(any())).thenReturn(Optional.ofNullable(newsSource));

		assertThrows(NewsSourceAlreadyExistsException.class, () -> newsSourceService.addNewsSource(newsSource));

		verify(newsSourceRepository, times(1)).findById(any());
		verify(newsSourceRepository, times(0)).save(any());

	}

	@Test
	@Rollback(true)
	public void testGetNewsSourceSuccess() throws NewsSourceNotFoundException {

		when(newsSourceRepository.findById(any())).thenReturn(Optional.ofNullable(newsSource));

		assertEquals(newsSource, newsSourceService.getNewsSourceById(newsSource.getSourceId()));

		verify(newsSourceRepository, times(1)).findById(any());

	}

	@Test
	@Rollback(true)
	public void testGetNewsSourceFailure() throws NewsSourceNotFoundException {

		when(newsSourceRepository.findById(any())).thenReturn(Optional.empty());

		assertThrows(NewsSourceNotFoundException.class, () -> newsSourceService.getNewsSourceById(newsSource.getSourceId()));

		verify(newsSourceRepository, times(1)).findById(any());

	}

	@Test
	@Rollback(true)
	public void testGetAllNewsSourceSuccess() {

		when(newsSourceRepository.findAll()).thenReturn(newsSourceList);

		assertEquals(newsSourceList, newsSourceService.listAllNewsSources());

		verify(newsSourceRepository, times(1)).findAll();

	}

	@Test
	@Rollback(true)
	public void testUpdateNewsSourceSuccess() throws NewsSourceNotFoundException {

		when(newsSourceRepository.findById(any())).thenReturn(Optional.ofNullable(newsSource));
		when(newsSourceRepository.save(any())).thenReturn(newsSource);

		assertEquals(newsSource, newsSourceService.updateNewsSource(newsSource,newsSource.getSourceId()));

		verify(newsSourceRepository, times(1)).findById(any());
		verify(newsSourceRepository, times(1)).save(any());

	}

	@Test
	@Rollback(true)
	public void testUpdateNewsSourceFailure() throws NewsSourceNotFoundException {

		when(newsSourceRepository.findById(any())).thenReturn(Optional.ofNullable(null));

		assertThrows(NewsSourceNotFoundException.class, () -> newsSourceService.updateNewsSource(newsSource, newsSource.getSourceId()));

		verify(newsSourceRepository, times(1)).findById(any());
		verify(newsSourceRepository, times(0)).save(any());

	}

	@Test
	@Rollback(true)
	public void testDeleteNewsSourceSuccess() throws NewsSourceNotFoundException {

		when(newsSourceRepository.findById(any())).thenReturn(Optional.ofNullable(newsSource));
		newsSourceService.deleteNewsSource(newsSource.getSourceId());

		verify(newsSourceRepository, times(1)).findById(any());
		verify(newsSourceRepository, times(1)).deleteById(any());

	}

	@Test
	@Rollback(true)
	public void testDeleteNewsSourceFailure() throws NewsSourceNotFoundException {

		when(newsSourceRepository.findById(any())).thenReturn(Optional.ofNullable(null));

		assertThrows(NewsSourceNotFoundException.class, () -> newsSourceService.deleteNewsSource(newsSource.getSourceId()));

		verify(newsSourceRepository, times(1)).findById(any());
		verify(newsSourceRepository, times(0)).deleteById(any());

	}
	
//	@Test
//	@Rollback(true)
//	public void testListNewsByAuthorSuccess() {
//
//		when(newsRepository.findByAuthor(any())).thenReturn(newsSourceList);
//
//		assertEquals(newsSourceList, newsService.listNewsByAuthor(newsSource.getAuthor()));
//
//		verify(newsRepository, times(1)).findByAuthor((any()));
//
//	}
//	
//	@Test
//	@Rollback(true)
//	public void testGetNewsBySourceSuccess() {
//
//		when(newsRepository.findByNewsSource(any())).thenReturn(newsSourceList);
//
//		assertEquals(newsSourceList, newsService.listNewsByNewsSource((newsSource.getNewsSource()).getSourceName()));
//
//		verify(newsRepository, times(1)).findByNewsSource(any());
//
//	}
//
//	@Test
//	@Rollback(true)
//	public void testGetNewsBySourceFailure() {
//
//		when(newsRepository.findByNewsSource(any())).thenReturn(null);
//
//		assertEquals(null, newsService.listNewsByNewsSource((newsSource.getNewsSource()).getSourceName()));
//
//		verify(newsRepository, times(1)).findByNewsSource(any());
//
//	}

}
