package com.RevBookStore.service;

import java.util.List;

import com.RevBookStore.entity.Products;
import com.RevBookStore.entity.Review;

public interface SellerServiceInterface {

	boolean addProduct(Long userId, Products product);

	List<Products> viewProducts();


	boolean deleteProductById(Long productId, Long sellerId);

	List<Review> getReviewsByProductId(List<Long> productIds);

	List<Products> getProductsBySellerId(Long sellerId);

	

}
