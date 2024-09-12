package com.RevBookStore.dao;

import java.util.List;

import com.RevBookStore.entity.Products;
import com.RevBookStore.entity.Review;

public interface SellerDaoInterface {

	boolean addProduct(Long userId, Products product);

	List<Products> viewProducts();

	boolean deleteProducts(Long productId,Long sellerId);

	List<Review> ReviewProducts(List<Long> productIds);

	List<Products> getProductsBySellerId(Long sellerId);

}
