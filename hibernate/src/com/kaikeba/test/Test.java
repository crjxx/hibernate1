package com.kaikeba.test;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;

import com.kaikeba.entity.Book;
import com.kaikeba.entity.Category;
import com.kaikeba.entity.User;
import com.kaikeba.util.HibernateUtils;

public class Test {
	public static void main(String[] args) {
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
	public static void demo1(){
		//增
		//测试使用
		//使用hibernate插入一条数据
		//1.创建一个对象
		Configuration configuration = new Configuration().configure();
		//2.创建一个sessionFactory对象
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		//3.用session对象去操作数据库
		Session session = sessionFactory.openSession();
		//4.获取务对象
		//一.开启事务
		Transaction beginTransaction = session.beginTransaction();
		//二.执行保存操作
		User user = new User();
		user.setUsername("bbb");
		user.setUserpass("123456");
		session.save(user);
//		user= (User) session.get(User.class, 2);//id为2 这里
//		user.setUsername("bbbb");
//		System.out.println(user.getUsername());
		//事务提交
		beginTransaction.commit();
		//释放资源
		session.close();
		sessionFactory.close();
	}
	public static void demo2(){
		//删
		//测试使用
		//使用hibernate插入一条数据
		//1.创建一个对象
		Configuration configuration = new Configuration().configure();
		//2.创建一个sessionFactory对象
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		//3.用session对象去操作数据库
		Session session = sessionFactory.openSession();
		//4.获取事务对象
		//一.开启事务
		Transaction beginTransaction = session.beginTransaction();
		//二.执行保存操作
		User user = new User();
		//user.setId(2);
		session.delete(user);
		//事务提交
		beginTransaction.commit();
		//释放资源
		session.close();
		sessionFactory.close();
	}
	public static void demo3(){
		//改
		
		//测试使用
		//使用hibernate插入一条数据
		//1.创建一个对象
		Configuration configuration = new Configuration().configure();
		//2.创建一个sessionFactory对象
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		//3.用session对象去操作数据库
		Session session = sessionFactory.openSession();
		//4.获取事务对象
		//一.开启事务
		Transaction beginTransaction = session.beginTransaction();
		//二.执行保存操作
		//1.先查
		User user = (User) session.get(User.class, 4);
		//2.改
		user.setUsername("ccc");
		session.update(user);
		//事务提交
		beginTransaction.commit();
		//释放资源
		session.close();
		sessionFactory.close();
	}
	
	public static void demo4(){
		//查多个
		//hql
		
		
		
		//测试使用
		//使用hibernate插入一条数据
		//1.创建一个对象
		Configuration configuration = new Configuration().configure();
		//2.创建一个sessionFactory对象
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		//3.用session对象去操作数据库
		Session session = sessionFactory.openSession();
		//4.获取事务对象
		//一.开启事务
		Transaction beginTransaction = session.beginTransaction();
		//二.执行保存操作
		User user = new User();
		user= (User) session.get(User.class, 3);//id为2 这里
		System.out.println(user.getUsername());
		//事务提交
		beginTransaction.commit();
		//释放资源
		session.close();
		sessionFactory.close();
		}
	public static void demo5(){
		//1.创建一个对象
				Configuration configuration = new Configuration().configure();
				//2.创建一个sessionFactory对象
				SessionFactory sessionFactory = configuration.buildSessionFactory();
				//3.用session对象去操作数据库
				Session session = sessionFactory.openSession();
				//4.获取事务对象
				//一.开启事务
				Transaction beginTransaction = session.beginTransaction();
				//二.执行保存操作
				String hql="from User";
				Query query = session.createQuery(hql);
				List<User> users = query.list();
				for(int i =0; i<users.size(); i++ ){
					System.out.println(users.get(i).getUsername());
				}
				System.out.println(users);
				//事务提交
				beginTransaction.commit();
				//释放资源
				session.close();
				sessionFactory.close();
	}
	public static void demo6(){
		//1.创建一个对象
		Configuration configuration = new Configuration().configure();
		//2.创建一个sessionFactory对象
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		//3.用session对象去操作数据库
		Session session = sessionFactory.openSession();
		//4.获取事务对象
		//一.开启事务
		Transaction beginTransaction = session.beginTransaction();
		//二.执行保存操作
		String hql="from User";
		Query query = session.createQuery(hql);
		List<User> users = query.setMaxResults(3).setFirstResult(2).list();
		for(int i =0; i<users.size(); i++ ){
			System.out.println(users.get(i).getUsername());
		}
		//System.out.println(users);
		//事务提交
		beginTransaction.commit();
		//释放资源
		session.close();
		sessionFactory.close();
	}
	public static void demo7(){
		//1.创建一个对象
				Configuration configuration = new Configuration().configure();
				//2.创建一个sessionFactory对象
				SessionFactory sessionFactory = configuration.buildSessionFactory();
				//3.用session对象去操作数据库
				Session session = sessionFactory.openSession();
				//4.获取事务对象
				//一.开启事务
				Transaction beginTransaction = session.beginTransaction();
				//二.执行保存操作
				String sql="select * from users";
				 SQLQuery sqlQuery = session.createSQLQuery(sql);
				List<User> users = sqlQuery.addEntity(User.class).list();
				for(int i =0; i<users.size(); i++ ){
					System.out.println(users.get(i).getUsername());
				}
				//System.out.println(users);
				//事务提交
				beginTransaction.commit();
				//释放资源
				session.close();
				sessionFactory.close();
	}
	public static void demo8(){
		
		//3.用session对象去操作数据库
		Session session = HibernateUtils.getSession();
		//4.获取事务对象
		//一.开启事务
		Transaction beginTransaction = session.beginTransaction();
		//二.执行保存操作
		Criteria criteria = session.createCriteria(User.class);
		//根据密码或者用户名查询
		//String hql = "from users where password = 123456";
		//这里的password指的是实体类的User当中的属性
		criteria.add(Restrictions.eq("userpass", "123456"));
		
		//这里表示主键查询
		//criteria.add(Restrictions.idEq(5));
		
		List<User> users = criteria.list();
		for(User user : users){
			System.out.println(user.getUsername());
		}
		//System.out.println(users);
		//事务提交
		beginTransaction.commit();
		//释放资源
		session.close();
		HibernateUtils.close();
	}
	public static void demo9(){
		
		//3.用session对象去操作数据库
		Session session = HibernateUtils.getSession();
		//4.获取事务对象
		//一.开启事务
		Transaction beginTransaction = session.beginTransaction();
		//二.执行保存操作
		User user = new User();
		//user.setId(9);
		user.setUsername("abc");
		user.setUserpass("321");
		session.saveOrUpdate(user);
		//事务提交
		beginTransaction.commit();
		//释放资源
		session.close();
		HibernateUtils.close();
	}
}
