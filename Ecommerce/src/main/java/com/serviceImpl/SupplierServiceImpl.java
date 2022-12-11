package com.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.ISupplierDAO;
import com.entity.Supplier;
import com.service.ISupplierService;

@Service
public class SupplierServiceImpl implements ISupplierService {

	@Autowired
	private ISupplierDAO supplierDAO;
	
	@Override
	public boolean addSupplier(Supplier supplier) {
		return supplierDAO.addSupplier(supplier);
	}

	@Override
	public Supplier getSupplier(int supplierId) {
		// TODO Auto-generated method stub
		return supplierDAO.getSupplier(supplierId);
	}

	@Override
	public List<Supplier> getAllSupplier() {
		// TODO Auto-generated method stub
		return supplierDAO.getAllSupplier();
	}

	@Override
	public boolean deleteSupplier(Supplier supplier) {
		// TODO Auto-generated method stub
		return supplierDAO.deleteSupplier(supplier);
	}

	@Override
	public boolean updateSupplier(Supplier supplier) {
		// TODO Auto-generated method stub
		return supplierDAO.updateSupplier(supplier);
	}

}
