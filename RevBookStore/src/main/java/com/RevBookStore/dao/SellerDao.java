package com.RevBookStore.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.hibernate.Session;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.RevBookStore.entity.Products;
import com.RevBookStore.entity.Review;



@Repository
public class SellerDao implements SellerDaoInterface {

	@Autowired
	private SessionFactory sf;
	
	public boolean addProduct(Long userId, Products product) {
		
		System.out.println(userId);
		System.out.println(product.getName());
		Session ss = sf.openSession(); // for hiberate initialization
		Transaction et = ss.getTransaction();  //transaction initialization
		et.begin(); // begin the transaction
		
		ss.persist(product); // hibernate
		
		et.commit(); // commit the transaction
		return true;
	}

	@Override
	public List<Products> viewProducts() {
		Session ss = sf.openSession();
		Query q = ss.createQuery("from com.RevBookStore.entity.Products p");
		List<Products> list = q.getResultList();
		return list;
	}

	@Override
	public boolean deleteProducts(Long productId, Long sellerId) {
		// TODO Auto-generated method stub
		System.out.println(productId);
		Session ss = sf.openSession();
		Transaction et = ss.getTransaction();
		et.begin();
		
		Products product = ss.get(Products.class, productId);
		
		if(product != null)
		{
			ss.delete(product);
		}else
		{
			 System.out.println("Product not found with ID: " + productId);
			 return false;
		}
		et.commit();
		return true;
	}
	
	
	@Override
	@Transactional
	public List<Products> getProductsBySellerId(Long sellerId) {
	    List<Products> products = new ArrayList<>();
	    try (Session session = sf.openSession()) {
	        TypedQuery<Products> query = session.createQuery("FROM Products WHERE sellerId = :sellerId", Products.class);
	        query.setParameter("sellerId", sellerId);
	        products = query.getResultList();
	    } catch (Exception e) {
	        e.printStackTrace();
	        // Handle exception
	    }
	    return products;
	}

	
	
	@Override
	@Transactional
	public List<Review> ReviewProducts(List<Long> productIds) {
	    List<Review> reviews = new ArrayList<>();
	    try (Session session = sf.openSession()) {
	        TypedQuery<Review> query = session.createQuery("FROM Review r WHERE r.product.id IN :productIds", Review.class);
	        query.setParameter("productIds", productIds);
	        reviews = query.getResultList();
	    } catch (Exception e) {
	        e.printStackTrace();
	        // Handle exception
	    }
	    return reviews;
	}


	
	}


