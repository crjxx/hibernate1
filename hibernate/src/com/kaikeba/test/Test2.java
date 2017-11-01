package com.kaikeba.test;

import org.hibernate.Transaction;
import org.hibernate.classic.Session;

import com.kaikeba.entity.Book;
import com.kaikeba.entity.Category;
import com.kaikeba.util.HibernateUtils;

public class Test2 {
	public static void main(String[] args) {
		
	}
	public static void demo1(){
		//Hibernate在做一对多添加的时候再添加多的那个时,先添加完所有表数据,再去修改外键值
		Session session = HibernateUtils.getSession();
		Transaction transaction = session.beginTransaction();
		//用来存储图书分类的信息
		Category category = (Category) session.get(Category.class, "java2");
		//存放书本信息
		Book book1 = new Book();
		Book book2 = new Book();
		book1.setBname("javaEE");
		book2.setBname("javaSE");
		book1.setCategory(category);
		book2.setCategory(category);
		category.getBooks().add(book1);
		category.getBooks().add(book2);
		session.save(category);
//		session.save(book1);
//		session.save(book2);
		transaction.commit();
		HibernateUtils.close();
	}
	public static void demo2(){
		
	}
}
