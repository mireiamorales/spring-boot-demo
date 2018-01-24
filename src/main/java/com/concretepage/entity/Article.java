package com.concretepage.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "articles")
public class Article implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "article_id")
	@ApiModelProperty(notes = "El identificador del artículo es generado por la base de datos")
	private int articleId;

	@Column(name = "title")
	@ApiModelProperty(notes = "El título del artículo")
	private String title;

	@Column(name = "category")
	@ApiModelProperty(notes = "La categoría del artículo")
	private String category;

	public int getArticleId() {
		return articleId;
	}

	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public static long getSerialversionid() {
		return serialVersionUID;
	}

}
