package com.daoImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.dao.ISupplierDAO;
import com.entity.Supplier;

@Repository
@Transactional
public class SupplierDaoImpl implements ISupplierDAO {

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@Override
	public boolean addSupplier(Supplier supplier) {
		// TODO Auto-generated method stub
		int supplierId = (Integer) hibernateTemplate.save(supplier);
		if (supplierId != 0)
			return true;
		return false;
	}

	@Override
	public Supplier getSupplier(int supplierId) {
		Supplier fetchedSupplier = hibernateTemplate.get(Supplier.class, supplierId);
		return fetchedSupplier;
	}

	@Override
	public List<Supplier> getAllSupplier() {
		return hibernateTemplate.loadAll(Supplier.class);
	}

	@Override
	public boolean deleteSupplier(Supplier supplier) {
		try {
			hibernateTemplate.delete(supplier);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateSupplier(Supplier supplier) {

		try {
			hibernateTemplate.update(supplier);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
