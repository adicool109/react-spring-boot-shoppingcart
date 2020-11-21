package com.cart.model;

import javax.persistence.Entity;

@Entity
public class Book extends Product {
	
	private String genre;
	
	private String author;
	
	private String publications;

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublications() {
		return publications;
	}

	public void setPublications(String publications) {
		this.publications = publications;
	}
	
}
