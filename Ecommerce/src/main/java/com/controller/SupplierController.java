package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entity.Supplier;
import com.service.ISupplierService;

@Controller
public class SupplierController 
{
	@Autowired
	private ISupplierService supplierService;
	
	@GetMapping("/getSupplier")
	@ResponseBody
	public String getSupplierHandler()
	{
		Supplier supplier = supplierService.getSupplier(5);
		System.out.println("Fecthed - "+supplier);
		return "index";
	}
	
	@GetMapping("/getAllSupplier")
	public String getAllSupplierHandler()
	{
		List<Supplier> supplierList = supplierService.getAllSupplier();
		System.out.println(supplierList);
		return "index";
	}
	
	@RequestMapping(value="/addSupplier")//, method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public String addSupplierHandler()
	{
		Supplier supplier = new Supplier("Arvind fashion Limited","Chandi Chawk, Delhi");
		boolean addedSupplier = supplierService.addSupplier(supplier);
		System.out.println("addSupplierHandler - "+addedSupplier);
		return "index";
	}
	
	@GetMapping(value="/updateSupplier")
	public String updateSupplierHandler()
	{
		Supplier supplier = supplierService.getSupplier(5);
		supplier.setSupplierAddress(supplier.getSupplierAddress()+", Near HDFC bank");
		supplierService.updateSupplier(supplier);
		return "index";
	}
	@GetMapping(value="/deleteSupplier")
	public String deleteSupplierHandler()
	{
		Supplier supplier = supplierService.getSupplier(6);
		supplierService.deleteSupplier(supplier);
		return "index";
	}
}
