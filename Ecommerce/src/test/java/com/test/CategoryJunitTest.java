package com.test;

import static org.junit.Assert.assertTrue;

import org.apache.catalina.core.ApplicationContext;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import com.dao.ICategoryDAO;
import com.daoImpl.CategoryDAOImpl;
import com.entity.Category;

//@WebAppConfiguration
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = {TestBeanConfig.class})
public class CategoryJunitTest {
	
	//@Autowired
	static ICategoryDAO categoryDAO ;//= new CategoryDAOImpl();
	
	@BeforeClass
	public static void executeFirst()
	{
		
		
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		//ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("src/main/resources/webapp/WEB-INF/spring-servlet.xml");
		
		//ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("E:\\jee-2022-03\\Ecommerce\\src\\main\\webapp\\WEB-INF\\spring-servlet.xml");
		
		//E:\jee-2022-03\Ecommerce\src\main\webapp\WEB-INF\spring-servlet.xml
		//context.scan("src/main/resources/webapp/WEB-INF");
		context.scan("com");
		context.refresh();
		
		categoryDAO = (ICategoryDAO) context.getBean("categoryDAO");
		//categoryDAO = context.getBean("categoryDAO", CategoryDAOImpl.class);
		
		//categoryDAO = (CategoryDAOImpl) context.getBean(CategoryDAOImpl.class);
		//categoryDAO = (CategoryDAOImpl) context.getBean(ICategoryDAO.class);
	}
	
	@Test
	public void addCategoryTest()
	{
		Category category = new Category();
		category.setCategoryName("Half Sleeve Shirt");
		category.setCategoryDesc("Beautiful Slim Fit Shirt with Collar and Pocket");
		
		//assertTrue(string,booolean)
		assertTrue("Category addition FAILED !! ", categoryDAO.addCategory(category));
	}
}
