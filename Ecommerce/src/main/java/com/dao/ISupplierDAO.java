package com.dao;

import java.util.List;

import com.entity.Supplier;

public interface ISupplierDAO {
	boolean addSupplier(Supplier supplier);
	Supplier getSupplier(int supplierId);
	List<Supplier> getAllSupplier();
	boolean deleteSupplier(Supplier supplier);
	boolean updateSupplier(Supplier supplier);
}
