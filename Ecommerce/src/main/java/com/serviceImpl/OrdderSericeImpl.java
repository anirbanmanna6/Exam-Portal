package com.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.IOrdderDAO;
import com.entity.Ordder;
import com.service.IOrdderService;

@Service
public class OrdderSericeImpl implements IOrdderService {

	@Autowired
	private IOrdderDAO ordderDAO;
	
	@Override
	public boolean addOrdder(Ordder ordder) {
		// TODO Auto-generated method stub
		return ordderDAO.addOrdder(ordder);
	}

	@Override
	public Ordder getOrdder(int ordderId) {
		// TODO Auto-generated method stub
		return ordderDAO.getOrdder(ordderId);
	}

	@Override
	public boolean cancelOrdder(Ordder ordder) {
		// TODO Auto-generated method stub
		return ordderDAO.cancelOrdder(ordder);
	}

	@Override
	public boolean updateOrdder(Ordder ordder) {
		// TODO Auto-generated method stub
		return ordderDAO.updateOrdder(ordder);
	}

	@Override
	public Ordder getOrdder(String email) {
		// TODO Auto-generated method stub
		return ordderDAO.getOrdder(email);
	}

	@Override
	public List<Ordder> getAllOrdder(String email) {
		// TODO Auto-generated method stub
		return ordderDAO.getAllOrdder(email);
	}

}
