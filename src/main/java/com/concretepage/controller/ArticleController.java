package com.concretepage.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.concretepage.entity.Article;
import com.concretepage.service.IArticleService;

@RestController
@RequestMapping("/article")
public class ArticleController {

	@Autowired
	private IArticleService articleService;

	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Resource<Article> getArticleById(@PathVariable("id") Integer id) {
		Article article = articleService.getArticleById(id);
		Resource<Article> resource = new Resource<Article>(article);
		resource.add(linkTo(methodOn(ArticleController.class).getArticleById(id)).withSelfRel());
		return resource;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Resource<Article>> getAllArticles() {

		Collection<Article> articlelist = articleService.getAllArticles();
		List<Resource<Article>> resources = new ArrayList<Resource<Article>>();
		Resource<Article> resource;
		for (Article article : articlelist) {
			resource = new Resource<Article>(article);
			resource.add(
					linkTo(methodOn(ArticleController.class).getArticleById(article.getArticleId())).withSelfRel());
			resources.add(resource);
		}
		return resources;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<Void> addArticle(@RequestBody Article article) {
		articleService.addArticle(article);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ResponseEntity<Article> updateArticle(@RequestBody Article article) {
		articleService.updateArticle(article);
		return new ResponseEntity<Article>(HttpStatus.OK);
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteArticle(@PathVariable Integer id) {
		articleService.deleteArticle(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

}
