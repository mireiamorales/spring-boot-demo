package com.concretepage.client;

import java.net.URI;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.concretepage.entity.Article;

public class RestClientUtil {

	public void getArticleByIdDemo() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8080/article/get/{id}";
		HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
		ResponseEntity<Article> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity,
				Article.class, 1);
		Article article = responseEntity.getBody();
		System.out.println("Id:" + article.getArticleId() + ", Title:" + article.getTitle() + ", Category:"
				+ article.getCategory());
	}

	public void getAllArticlesDemo() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8080/article/list";
		HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
		ResponseEntity<Article[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity,
				Article[].class);
		Article[] articles = responseEntity.getBody();
		for (Article article : articles)
			System.out.println("Id:" + article.getArticleId() + ", Title:" + article.getTitle() + ", Category:"
					+ article.getCategory());
	}

	public void addArticleDemo() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8080/article/add";
		Article objArticle = new Article();
		objArticle.setTitle("Spring REST Security using Java");
		objArticle.setCategory("java");
		HttpEntity<Article> requestEntity = new HttpEntity<Article>(objArticle, headers);
		URI uri = restTemplate.postForLocation(url, requestEntity);
		System.out.println(uri.getPath());
	}

	public void updateArticleDemo() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8080/article/update";
		Article objArticle = new Article();
		objArticle.setArticleId(1);
		objArticle.setTitle("Update:Java Concurrency");
		objArticle.setCategory("Java");
		HttpEntity<Article> requestEntity = new HttpEntity<Article>(objArticle, headers);
		restTemplate.put(url, requestEntity);
		System.out.println("Se ha ejecutado el update correctamente");
	}

	public void deleteArticleDemo() {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8080/article/delete/{id}";
		HttpEntity<Article> requestEntity = new HttpEntity<Article>(headers);
		restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, Void.class, 16);
		System.out.println("Se ha ejecutado el delete correctamente");
	}

	public static void main(String artgs[]) {
		RestClientUtil util = new RestClientUtil();

		util.getArticleByIdDemo();

		util.getAllArticlesDemo();

		//util.addArticleDemo();

		util.updateArticleDemo();

		// util.deleteArticleDemo();

	}

}
