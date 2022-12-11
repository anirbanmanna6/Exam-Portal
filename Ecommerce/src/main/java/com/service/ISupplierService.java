package com.service;

import java.util.List;

import com.entity.Supplier;

public interface ISupplierService {
	boolean addSupplier(Supplier supplier);
	Supplier getSupplier(int supplierId);
	List<Supplier> getAllSupplier();
	boolean deleteSupplier(Supplier supplier);
	boolean updateSupplier(Supplier supplier);
}
