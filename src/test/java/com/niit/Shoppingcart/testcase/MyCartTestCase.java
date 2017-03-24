package com.niit.Shoppingcart.testcase;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.shoppingcart.dao.MyCartDAO;
import com.niit.shoppingcart.domain.MyCart;

import junit.framework.Assert;

public class MyCartTestCase {

	@Autowired
	private static MyCart myCart;
	
	@Autowired
	private static MyCartDAO myCartDAO;
	
	@BeforeClass
	public static void init(){
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();
		
		myCart = (MyCart) context.getBean("myCart");
		
		myCartDAO = (MyCartDAO) context.getBean("myCartDAO");
		
	}
	
	// TEST CASES
	
	@Test
	public void createCartTestCase(){
		myCart.setUserId("Cart001");
		myCart.setStatus('N');
		myCart.setQuantity("2");
		myCart.setProductName("Product Name");
		myCart.setPrice("500");
		myCart.setId(100L);
		myCart.setDateAdded("21/3/17");
		
		boolean flag = myCartDAO.save(myCart);
		
		Assert.assertEquals(true, flag);
	}
	
}
