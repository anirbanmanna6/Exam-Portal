package com.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.entity.Category;
import com.entity.Product;
import com.service.ICategoryService;
import com.service.IProductService;

@Controller
public class ProductController {
	
	@Autowired
	private IProductService productService;
	
	//VVI - calling OTHER services
	@Autowired
	private ICategoryService categoryService;
	
	@GetMapping("/getProduct")
	@ResponseBody
	public String getProductHandler()
	{
		Product product = productService.getProduct(5);
		System.out.println("Fecthed - "+product);
		return "index";
	}
	
	/* MANAGE PRODUCT - ADMIN */
	@GetMapping("/getAllProduct")
	public String getAllProductHandler(Model m)
	{
		List<Product> productList = productService.getAllProduct();
		System.out.println(productList);
		m.addAttribute("productList",productList);
		List<Category> categoryList = categoryService.getAllCategory();
		m.addAttribute(categoryList);
		return "product";
	}
	
	@RequestMapping(value="/addProduct", method = RequestMethod.POST)//, produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public ModelAndView addProductHandler(Model m, @ModelAttribute("product") Product product, @RequestParam("catName") String catName,
			@RequestParam("imgFile") MultipartFile file, HttpSession session
			) throws IOException
	{
		System.out.println("addProductHandler - catName = "+catName);
		
		/* Fetching category by Name */
		Category fecthedCategoryByName = categoryService.getCategoryByName(catName);
		System.out.println("addProductHandler - fecthedCategory = "+fecthedCategoryByName);
		
		/*adding the category object value to product*/
		product.setCategory(fecthedCategoryByName);
		
		/* Image upload starts */

		byte[] data = file.getBytes();
		String path = session.getServletContext().getRealPath("/")+"WEB-INF"+
				File.separator + "resources"+File.separator+"product_img"+ File.separator +file.getOriginalFilename();
		//Or we can define path in the below way 
		//String path = "E:\\jee-2022-03\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\sharemarket\\WEB-INF\\resources\\"+file.getOriginalFilename();								
		System.out.println(path);
		System.out.println("File Type - "+file.getContentType());

		if (!file.isEmpty()) 
		{
			if (file.getContentType().equals("image/jpeg") || file.getContentType().equals("image/png") || file.getContentType().equals("image/webp")) {
				
				product.setImgUrl(file.getOriginalFilename());
				//User updatedUser = contactService.addContactService(user);

				FileOutputStream fout = new FileOutputStream(path);
				fout.write(data);
				System.out.println("File upload successful");
				fout.close();
			} 
			else 
			{
				System.out.println("Incorrect File type ");
			} /* Image upload ends */
		} 
		
		
		System.out.println("addProductHandler - Product Details - "+product);
		
		/* add product object */
		boolean addedProduct = productService.addProduct(product);
		
		List<Product> productList = productService.getAllProduct();
		m.addAttribute("productList",productList);
		
		List<Category> categoryList = categoryService.getAllCategory();
		m.addAttribute(categoryList);
		
		return new ModelAndView("product");
	}
	
	@GetMapping(value="/updateProductInitiate/{productId}")
	public String updateProductHandler(@PathVariable("productId") int productId, Model m)
	{
		Product product = productService.getProduct(productId);
		m.addAttribute("product",product);
		List<Category> categoryList = categoryService.getAllCategory();
		m.addAttribute(categoryList);
		//product.setProductDesc(product.getProductDesc()+" - New");
		//productService.updateProduct(product);
		return "productUpdate";
	}
	@PostMapping(value="/updateProductProcess")
	public ModelAndView updateCategoryHandler(Model m, @ModelAttribute("product") Product product,
			@RequestParam("imgFile") MultipartFile file, HttpSession session, @RequestParam("catName") String catName
			, @RequestParam("productId") int productId) 
					throws IOException
	{
		/* fetching old product data */
		Product oldProduct = productService.getProduct(productId);
		
		if(catName!=null)  /* If category is selected */
		{
			/* Fetching category by Name */
			Category fecthedCategoryByName = categoryService.getCategoryByName(catName);
			System.out.println("addProductHandler - fecthedCategory = "+fecthedCategoryByName);
		
			/*adding the category object value to product*/
			product.setCategory(fecthedCategoryByName);	
		}
		else     /* If category is NOT selected */
		{
			product.setCategory(oldProduct.getCategory());
		}

		/* Image upload starts */
		if (!file.isEmpty()) 
		{
			System.out.println("File Type - "+file.getContentType());
			if (file.getContentType().equals("image/jpeg") || file.getContentType().equals("image/png") || file.getContentType().equals("image/webp") ) 
			{				
				byte[] data = file.getBytes();
				
				String oldImagePath = session.getServletContext().getRealPath("/")+"WEB-INF"+
						File.separator + "resources"+File.separator+"product_img"+ File.separator +oldProduct.getImgUrl();				
				
				String newImagePath = session.getServletContext().getRealPath("/")+"WEB-INF"+
						File.separator + "resources"+File.separator+"product_img"+ File.separator +file.getOriginalFilename();
				
				System.out.println("OLD IMAGE PATH - "+oldImagePath);
				System.out.println("NEW IMAGE PATH - "+newImagePath);
				
				/* delete old image from directory */
				File oldFile = new File(oldImagePath);
				boolean deletionResult = oldFile.delete();
				
				System.out.println("OLD IMAGE Deletion result - "+deletionResult);
				
				/* Set Img URL */
				product.setImgUrl(file.getOriginalFilename());
				
				/* save the new image in the directory */
				FileOutputStream fout = new FileOutputStream(newImagePath);
				fout.write(data);
				System.out.println("Image change successful");
				fout.close();
			} 
			else 
			{
				product.setImgUrl(oldProduct.getImgUrl());  /* Set old image file if file type is wrong*/
				System.out.println("Incorrect File type ");
			} 
		}
		else
		{
			product.setImgUrl(oldProduct.getImgUrl());   /* Set old image file if No file is selected */
		} /* Image upload ends */
		
		/* update product object */
		productService.updateProduct(product);	
		
		List<Product> productList = productService.getAllProduct();
		m.addAttribute("productList",productList);
		
		List<Category> categoryList = categoryService.getAllCategory();
		m.addAttribute(categoryList);
		
		return  new ModelAndView("product");
	}
	
	@GetMapping(value="/deleteProduct/{productId}")
	public String deleteProductHandler(Model m, @PathVariable("productId") int productId)
	{
		Product product = productService.getProduct(productId);
		productService.deleteProduct(product);
		List<Product> productList = productService.getAllProduct();
		m.addAttribute("productList",productList);
		return "product";
	}
	
	
	@GetMapping(value="/deleteProductIndividual/{productId}")
	public String deleteProductIndividualHandler(Model m, @PathVariable("productId") int productId)
	{
		Product product = productService.getProduct(productId);
		productService.deleteProduct(product);
		Product productAfterDeletion = productService.getProduct(productId);
		m.addAttribute("product",productAfterDeletion);
		return "productDisplayIndividual";
	}
	
	/* PRODUCT CATALOG -> To Show all products */
	@RequestMapping(value="/productDisplay")
	public String productDisplayHandler(Model m)
	{
		List<Product> productList = productService.getAllProduct();
		m.addAttribute("productList",productList);
		return "productDisplay";
	}
	
	/* PRODUCT CATALOG -> To Show all products */
	@RequestMapping(value="/productDisplayAdmin")
	public String productDisplayAdminHandler(Model m)
	{
		List<Product> productList = productService.getAllProduct();
		m.addAttribute("productList",productList);
		return "productDisplayAdmin";
	}
	
	@RequestMapping(value="/productDisplayIndividual/{productId}")
	public String productDisplayIndividualHandler(Model m, @PathVariable("productId") int productId)
	{
		Product product = productService.getProduct(productId);
		m.addAttribute("product",product);
		return "productDisplayIndividual";
	}
	
	@RequestMapping(value="/productDisplayIndividualAdmin/{productId}")
	public String productDisplayIndividualAdminHandler(Model m, @PathVariable("productId") int productId)
	{
		Product product = productService.getProduct(productId);
		m.addAttribute("product",product);
		return "productDisplayIndividualAdmin";
	}
}
