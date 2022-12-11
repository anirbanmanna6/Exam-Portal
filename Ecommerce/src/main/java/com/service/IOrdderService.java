package com.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.entity.Ordder;


public interface IOrdderService 
{
	boolean addOrdder(Ordder ordder);
	Ordder getOrdder(int ordderId);	
	boolean cancelOrdder(Ordder ordder);
	boolean updateOrdder(Ordder ordder);
	
	/* Fetching Ordder Details with Email*/
	Ordder getOrdder(String email);
	List<Ordder> getAllOrdder(String email);
}
