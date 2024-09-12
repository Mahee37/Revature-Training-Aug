package com.RevBookStore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.RevBookStore.dao.SellerDaoInterface;
import com.RevBookStore.entity.Products;
import com.RevBookStore.entity.Review;

@Service
public class SellerService implements SellerServiceInterface {
	
	@Autowired
	private SellerDaoInterface sellerdaoInterface;
	
	
	public boolean addProduct(Long userId,Products product) {
		return sellerdaoInterface.addProduct(userId,product);
		
	}


	@Override
	public List<Products> viewProducts() {
		// TODO Auto-generated method stub
		return sellerdaoInterface.viewProducts();
	}



	@Override
	public boolean deleteProductById(Long productId, Long sellerId) {
		// TODO Auto-generated method stub
		return sellerdaoInterface.deleteProducts(productId,sellerId);
	}



	@Override
	public List<Products> getProductsBySellerId(Long sellerId) {
		// TODO Auto-generated method stub
		return sellerdaoInterface.getProductsBySellerId(sellerId);
	}


	@Override
	public List<Review> getReviewsByProductId(List<Long> productIds) {
		// TODO Auto-generated method stub
		return sellerdaoInterface.ReviewProducts(productIds);
		
	}


	
		
	}

