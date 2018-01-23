package com.concretepage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.concretepage.entity.Article;
import com.concretepage.service.IArticleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.models.Model;

@RestController
@RequestMapping("/article")
@Api(value="onlinestore", description="Operaciones pertenecientes a los articulos")
public class ArticleController {

	@Autowired
	private IArticleService articleService;

	@ApiOperation(value="Busca el artículo con un ID", response=Article.class)
	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	public Article getArticleById(@PathVariable("id") Integer id, Model model) {
		Article article = articleService.getArticleById(id);
		return article;
	}
	@ApiOperation(value="Muestra la lista de los artículos disponibles", response=Iterable.class)
	@ApiResponses(value={
			@ApiResponse(code=200, message="Obtenida lista correctamente"),
			@ApiResponse(code=401, message="No está autorizado a acceder a este contenido"),
			@ApiResponse(code=403, message="Se prohíbe el acceso al recurso que intentaba obtener"),
			@ApiResponse(code=404, message="No se encuentra el recurso al que intenta acceder")
	})
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Iterable<Article> getAllArticles(Model model) {
		Iterable<Article> articlelist = articleService.getAllArticles();
		return articlelist;
	}

	@ApiOperation(value="Añade un artículo")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<Void> addArticle(@RequestBody Article article) {
		articleService.addArticle(article);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@ApiOperation(value="Actualiza un artículo")
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ResponseEntity<Article> updateArticle(@RequestBody Article article) {
		articleService.updateArticle(article);
		return new ResponseEntity<Article>(HttpStatus.OK);
	}

	@ApiOperation(value="Elimina un artículo")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteArticle(@PathVariable Integer id) {
		articleService.deleteArticle(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

}
