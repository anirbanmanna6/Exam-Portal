package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dao.ICategoryDAO;
import com.entity.Category;
import com.service.ICategoryService;

@Controller
public class CategoryController {
	
	@Autowired
	ICategoryService categoryService;
	
	@GetMapping("/hey")
	@ResponseBody
	public String homepage()
	{
		Category category = categoryService.getCategory(1);
		System.out.println("Fecthed - "+category);
		return "index";
	}
	
	@RequestMapping(value="/category")
	public ModelAndView categoryHandler(Model m)
	{

		List<Category> categoryList = categoryService.getAllCategory();
		System.out.println(categoryList);
		m.addAttribute("categoryList", categoryList);
		return new ModelAndView("category");
	}
	
	@GetMapping("/getAllCategory")
	public String getAllCategoryHandler()
	{
		List<Category> categoryList = categoryService.getAllCategory();
		System.out.println(categoryList);
		return "category";
	}
	
	@RequestMapping(value="/addCategory")//, method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public ModelAndView  addCategoryHandler(Model m, @RequestParam("categoryName") String categoryName, @RequestParam("categoryDesc") String categoryDesc) 
	{
		Category category = new Category(categoryName,categoryDesc);
		boolean addedCategory = categoryService.addCategory(category);
		System.out.println("addCategoryHandler - "+addedCategory);
		
		List<Category> categoryList = categoryService.getAllCategory();
		System.out.println(categoryList);
		m.addAttribute("categoryList", categoryList);
		//return new ModelAndView("category", "list", list);
		return new ModelAndView("category");
	}
	
	@GetMapping(value="/updateCategoryInitiate/{categoryId}")
	public String categoryUpdatePageHandler(Model m, @PathVariable("categoryId") int categoryId)
	{
		Category category = categoryService.getCategory(categoryId);
		m.addAttribute("category",category);
		System.out.println(category);
		return "categoryUpdate";
	}
	@PostMapping(value="/updateCategoryProcess")
	public ModelAndView updateCategoryHandler(Model m, @RequestParam("categoryId") int categoryId, @RequestParam("categoryName") String categoryName, @RequestParam("categoryDesc") String categoryDesc)
	{
		Category category = categoryService.getCategory(categoryId);
		category.setCategoryName(categoryName);
		category.setCategoryDesc(categoryDesc);
		categoryService.updateCategory(category);
		List<Category> categoryList = categoryService.getAllCategory();
		m.addAttribute("categoryList", categoryList);
		return  new ModelAndView("category");
	}
	@GetMapping(value="/deleteCategory/{categoryId}")
	public String deleteCategoryHandler(Model m, @PathVariable("categoryId") int categoryId)
	{
		Category category = categoryService.getCategory(categoryId);
		categoryService.deleteCategory(category);
		List<Category> categoryList = categoryService.getAllCategory();
		m.addAttribute("categoryList", categoryList);
		return "category";
	}
	
	
}
