package com.stackroute.newz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.newz.model.News;
import com.stackroute.newz.service.NewsService;
import com.stackroute.newz.util.exception.NewsAlreadyExistsException;
import com.stackroute.newz.util.exception.NewsNotFoundException;

/*
 * As in this assignment, we are working with creating RESTful web service, hence annotate
 * the class with @RestController annotation.A class annotated with @Controller annotation
 * has handler methods which returns a view. However, if we use @ResponseBody annotation along
 * with @Controller annotation, it will return the data directly in a serialized 
 * format. Starting from Spring 4 and above, we can use @RestController annotation which 
 * is equivalent to using @Controller and @ResposeBody annotation
 * 
 * Please note that the default path to use this controller should be "/api/v1/news"
 */
@RestController
@RequestMapping("/api/v1")
public class NewsController {
	
	/*
	 * Autowiring should be implemented for the NewsService. Please note that we
	 * should not create any object using the new keyword
	 */
	
	
	@Autowired
	private NewsService newService;
	private ResponseEntity<?> responseEntity;
	/*
	 * Define a handler method which will get us all news elements.
	 * 
	 * This handler method should return any one of the status messages basis on
	 * different situations: 
	 * 1. 200(OK) - If all news found successfully. 
	 * 
	 *
	 * This handler method should map to the URL "/api/v1/news" using HTTP GET
	 * method.
	 */
	
	@GetMapping("/news")
	public ResponseEntity<?> getAllNewsHandler(){
		List<News> newsobj=this.newService.listAllNews();
		responseEntity=new ResponseEntity<>(newsobj,HttpStatus.OK);
		return responseEntity;
	}
	
	
	/*
	 * Define a handler method which will create a news by reading the Serialized
	 * news object from request body and save the mews in news table in database.
	 * Please note that the newsId has to be unique.
	 * 
	 * This handler method should return any one of the status messages basis on
	 * different situations: 
	 * 1. 201(CREATED - In case of successful creation of the news 
	 * 2. 409(CONFLICT) - In case of duplicate newsId
	 * 
	 * This handler method should map to the URL "/api/v1/news" using HTTP POST
	 * method".
	 */
	@PostMapping("/news")
	public ResponseEntity<?> addNewsHandler(@RequestBody News news){
		News adnews=null;
		try {
			adnews=this.newService.addNews(news);
			responseEntity=new ResponseEntity<>(adnews,HttpStatus.CREATED);
		}catch(NewsAlreadyExistsException e) {
		responseEntity=new ResponseEntity<>(adnews,HttpStatus.CONFLICT);
		e.printStackTrace();
		}
		return responseEntity;
	}
	
	
	

	/*
	 * Define a handler method which will delete a news from the database.
	 * 
	 * This handler method should return any one of the status messages basis on
	 * different situations: 
	 * 1. 200(OK) - If the news deleted successfully. 
	 * 2.404(NOT FOUND) - If the news with specified newsId is not found.
	 * 
	 * This handler method should map to the URL "/api/v1/news/{newsId}" using HTTP
	 * Delete method" where "newsId" should be replaced by a valid newsId without {}
	 */
	@DeleteMapping("/news/{newsId}")
	public ResponseEntity<?> delNewsHanlder(@PathVariable int newsId)
	{
		try {
			this.newService.deleteNews(newsId);
			responseEntity=new ResponseEntity<>("News deleted",HttpStatus.OK);
		}catch(NewsNotFoundException e) {
			responseEntity=new ResponseEntity<>("news not found",HttpStatus.NOT_FOUND);
			e.printStackTrace();
		}
		return responseEntity;
	}
	
	
	

	/*
	 * Define a handler method which will update a specific news by reading the
	 * Serialized object from request body and save the updated news details in
	 * a news table in database and handle NewsNotFoundException as well. 
	 * 
	 * This handler method should return any one of the status
	 * messages basis on different situations: 
	 * 1. 200(OK) - If the news is updated successfully. 
	 * 2. 404(NOT FOUND) - If the news with specified newsId is not found. 
	 * 
	 * This handler method should map to the URL "/api/v1/news/{newsId}" using HTTP PUT
	 * method, where "newsId" should be replaced by a valid newsId without {}
	 */
	@PutMapping("/news/{newsId}")
	public ResponseEntity<?> updateNewsHandler(@RequestBody News news,@PathVariable int newsId){
		News nws=null;
		try {
			nws=this.newService.updateNews(news);
			responseEntity=new ResponseEntity<>(nws,HttpStatus.OK);
		}catch(NewsNotFoundException e) {
			responseEntity = new ResponseEntity<>(nws,HttpStatus.NOT_FOUND);
			e.printStackTrace();
		}
		return responseEntity;
	}
	


	/*
	 * Define a handler method which will get us the news by a newsId.
	 * 
	 * This handler method should return any one of the status messages basis on
	 * different situations: 
	 * 1. 200(OK) - If the news found successfully. 
	 * 2. 404(NOT FOUND) - If the news with specified newsId is not found. 
	 * 
	 * 
	 * This handler method should map to the URL "/api/v1/news?newsId={newsId}" using HTTP GET
	 * method, where "newsId" is a request parameter and should be replaced by a valid newsId without {}
	 */
	
	@GetMapping("/news/newsId={newsId}")
	public ResponseEntity<?> getNewsByIdHandler(@RequestParam int newsId){
		News nws=null;
		try {
			nws=this.newService.getNewsById(newsId);
		}catch(Exception NewsAlreadyExistsException) {
			responseEntity=new ResponseEntity<>(nws,HttpStatus.NOT_FOUND);
		}
		responseEntity=new ResponseEntity<>(nws,HttpStatus.OK);
		return responseEntity;
	}
	
	
	/*
	 * Define a handler method which will get us the news by a sourceName.
	 * 
	 * This handler method should return any one of the status messages basis on
	 * different situations: 
	 * 1. 200(OK) - If the news found successfully. 
	 * 
	 * This handler method should map to the URL "/api/v1/news?sourceName={sourceName}" using HTTP GET
	 * method, where "sourceName" is a request parameter and should be replaced by a valid sourceName without {}
	 */
	@GetMapping("/news/sourceName={sourceName}")
	public ResponseEntity<?> getNewsBySourceNameHandler(@RequestParam String sourceName){
		List<News> nlist=this.newService.listNewsByNewsSource(sourceName);
		responseEntity=new ResponseEntity<>(nlist,HttpStatus.OK);
		return responseEntity;
	}
	

}
