package com.niit.shoppingcart.dao.impl;

import java.util.List;

import javax.transaction.Transactional;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.shoppingcart.dao.MyCartDAO;
import com.niit.shoppingcart.domain.MyCart;

@Transactional
@Repository("myCartDAO")
public class MyCartDAOImpl implements MyCartDAO {
	
	//private static Logger log = LoggerFactory.getLogger(MyCartDAOImpl.class);
	
	private static Logger log = LoggerFactory.getLogger(MyCartDAOImpl.class);
	@Autowired
	private SessionFactory sessionFactory;

	public MyCartDAOImpl() {

	}

	public MyCartDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Transactional
	public List<MyCart> list(String userID) {
		log.debug("Starting of the method list");
		String hql = "from MyCart where userID=" + "'" + userID + "'  and status = " + "'N'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		log.debug("Ending of the method list");
		return query.list();

	}


	@Transactional
	public Long getTotalAmount(String userID) {
		log.debug("Starting of the method getTotalAmount");
		String hql = "select sum(price*quantity) from MyCart where userID=" + "'" + userID + "' " + "  and status = " + "'N'";
		log.debug("hql" + hql);

		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		log.debug("Ending of the method getTotalAmount");
		return (Long) query.uniqueResult();

	}

	public MyCart getCart(String id) {
		
		return (MyCart) sessionFactory.getCurrentSession().get(MyCart.class, id);
	}

	
	public boolean delete(MyCart myCart) {
		myCart.setStatus('X');
		
		return update(myCart);
		
	}

	
	public boolean save(MyCart myCart) {
		
		log.debug("Starting of method save");
		
		myCart.setId(getMaxId());
		try {
			sessionFactory.getCurrentSession().save(myCart);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

	
	public boolean update(MyCart myCart){
		try {
			sessionFactory.getCurrentSession().update(myCart);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

	private Long getMaxId() {
		log.debug("->->Starting of the method getMaxId");

		Long maxID = 100L;
		try {
			String hql = "select max(id) from MyCart";
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			maxID = (Long) query.uniqueResult();
		} catch (HibernateException e) {
			log.debug("It seems this is first record. setting initial id is 100 :");
			maxID = 100L;
			e.printStackTrace();
		}
		log.debug("Max id :" + maxID);
		return maxID+1;
	}

	
}
