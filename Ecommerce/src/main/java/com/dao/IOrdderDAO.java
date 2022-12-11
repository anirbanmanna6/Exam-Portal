package com.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.entity.Ordder;

public interface IOrdderDAO 
{
	boolean addOrdder(Ordder ordder);
	Ordder getOrdder(int ordderId);	
	boolean cancelOrdder(Ordder ordder);
	boolean updateOrdder(Ordder ordder);
	
	/* Fetching Order Details with Email*/
	Ordder getOrdder(String email);
	List<Ordder> getAllOrdder(String email);
}
