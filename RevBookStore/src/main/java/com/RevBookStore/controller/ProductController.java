package com.RevBookStore.controller;




import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.RevBookStore.entity.Products;
import com.RevBookStore.entity.Review;
import com.RevBookStore.service.ReviewServiceInterface;
import com.RevBookStore.service.SellerServiceInterface;


@Controller

@RequestMapping("product")
public class ProductController {
	
	@Autowired
	private SellerServiceInterface sellerServiceInterface;
	
//	@Autowired
//	private ReviewServiceInterface reviewServiceInterface;
//	
	@PostMapping
	public ModelAndView addProduct(@RequestParam("name") String name,
			                       @RequestParam("description") String description,
			                       @RequestParam("category") String category,
			                       @RequestParam("price") String price,
			                       @RequestParam("discount_price") String discount_price,
			                       @RequestParam("imageUrl") String imageUrl) {
		System.out.println(name);
		System.out.println(description);
		System.out.println(category);
		System.out.println(price);
		System.out.println(imageUrl);
		//Long userId = (Long) request.getSession(true).getAttribute("id");
		ModelAndView mv = new ModelAndView();
		Long userId =1l;
		//if user-id is null 
//		if(userId == null) {
//			mv.setViewName("login.jsp");
//			return mv;
//		}
		
		double parsedPrice;
		try {
			parsedPrice = Double.parseDouble(price);
		}catch(NumberFormatException e) {
			String err = "invalid price format";
			mv.addObject("error",err);
			mv.setViewName("add.jsp");
			return mv;
		}
		
		Products product = new Products();
		product.setName(name);
		product.setDescription(description);
		product.setCategory(category);
		product.setPrice(parsedPrice);
		
		if(discount_price!=null) {
			try {
				double discountPrice = Double.parseDouble(discount_price);
				product.setDiscount_price(discountPrice);
				}catch(NumberFormatException e) {
					String err = "invalid price format";
					mv.addObject("error",err);
					mv.setViewName("add.jsp");
					return mv;
				}
		}
		product.setImageUrl(imageUrl);
		
		boolean productAdded = sellerServiceInterface.addProduct(userId,product);
		if(productAdded) {
			List<Products> products = sellerServiceInterface.viewProducts();
			mv.addObject("product_list",products);
			mv.setViewName("/inventory.jsp");
			return mv;
			
		}else {
			String err = "Something went wrong";
			mv.addObject("error",err);
			mv.setViewName("add.jsp");
			return mv;
		}
		
}
	

	@RequestMapping("viewProducts")
	public ModelAndView viewProducts() {
		
		List<Products> products = sellerServiceInterface.viewProducts();
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("product_list",products);
		mv.setViewName("/inventory.jsp");
		return mv;
	}
	
	
	
	
	@PostMapping("deleteProduct")
	public ModelAndView deleteProduct(@RequestParam("id") Long productId, @RequestParam("sellerId") Long sellerId) {
	    ModelAndView mv = new ModelAndView();
	    
	    boolean productDeleted = sellerServiceInterface.deleteProductById(productId, sellerId);

	    if (productDeleted) {
	        List<Products> products = sellerServiceInterface.viewProducts();
	        mv.addObject("product_list", products);
	        mv.setViewName("/inventory.jsp");
	    } else {
	        String err = "Failed to delete the product or unauthorized action";
	        mv.addObject("error", err);
	        mv.setViewName("error.jsp");
	    }

	    return mv;
	}
	
	
	//Process as follows First fetch the SellerProducts-IDS Based Upon their SellerID
	//next then using the ProductIDs follow up to find the ProductId mapped Reviews
	@GetMapping("/ProductReviews")
	public ModelAndView viewProductReviews(/* @RequestParam("sellerId") Long sellerId */) {
	    ModelAndView mv = new ModelAndView();

	    Long sellerId = 1L;
	   
	    List<Products> products = sellerServiceInterface.getProductsBySellerId(sellerId);
	    System.out.println("retrievedProducts: " + products);

	    if (products.isEmpty()) {
	        mv.addObject("message", "No products available.");
	        mv.setViewName("/productReviews.jsp");
	        return mv;
	    }

	   
	    List<Long> productIds = products.stream().map(Products::getId).toList();
	    System.out.println("productIdsFound: " + productIds);

	   
	    List<Review> reviews = sellerServiceInterface.getReviewsByProductId(productIds);
	    System.out.println("reviewsFound: " + reviews);

	    
	    mv.addObject("products", products);
	    mv.addObject("reviews", reviews);
	    mv.setViewName("/productReviews.jsp");

	    return mv;
	}

	}