package com.kaikeba.entity;


import java.util.HashSet;
import java.util.Set;

public class Category {
	private String cid;
	private String cname;
	
	private Set<Book> books = new HashSet<Book>() ;

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public Set<Book> getBooks() {
		return books;
	}

	public void setBooks(Set<Book> books) {
		this.books = books;
	}

	
	
}
