package com.daoImpl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.dao.IOrdderDAO;
import com.entity.Cart;
import com.entity.Ordder;

@Repository
@Transactional
public class OrdderDAOImpl implements IOrdderDAO {

	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Override
	public boolean addOrdder(Ordder ordder) {
		try {

			int ordderId = (Integer) hibernateTemplate.save(ordder);
			if (ordderId != 0)
				return true;
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Ordder getOrdder(int ordderId) {
		// TODO Auto-generated method stub
		Ordder orrder = null;
		try {
			orrder = hibernateTemplate.get(Ordder.class, ordderId);
			return orrder;
		} catch (Exception e) {
			return orrder;
		}

	}

	@Override
	public boolean cancelOrdder(Ordder ordder) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateOrdder(Ordder ordder) {
		try {
			hibernateTemplate.update(ordder);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Ordder getOrdder(String email) // Most recent order
	{
		// TODO Auto-generated method stub
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		String query = "select o.order_id from Ordder o where o.user_user_email=:x and o.order_status is null";
		NativeQuery nq = session.createSQLQuery(query);
		nq.setParameter("x", email);
		List<Integer> ordderListId = nq.list();
		if(ordderListId.size()>0)  		// java.lang.IndexOutOfBoundsException: Index -1 out of bounds for length 0
				return this.getOrdder(ordderListId.get(ordderListId.size() - 1)); // Most recent order
		return null;
	}

	@Override
	public List<Ordder> getAllOrdder(String email) {
		List<Ordder> ordderList = new ArrayList<Ordder>();
		try {
			List<Integer> ordderIdList = this.fetchAllOrdderIdByEmail(email);
			for (Integer ordderId : ordderIdList) {
				System.out.println(ordderId);
				ordderList.add(this.getOrdder(ordderId)); // calling getCart() method and populate the cartList
			}
			return ordderList;

		} catch (Exception e) {
			e.printStackTrace();
			return ordderList;
		}
	}

	// Only child method
	public List<Integer> fetchAllOrdderIdByEmail(String email) {
		String query = "select o.order_id from Ordder o where o.user_user_email=:x and (o.order_status=:con or o.order_status=:del)";
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		NativeQuery nq = session.createSQLQuery(query);
		nq.setParameter("x", email);
		nq.setParameter("con", "Confirmed");
		nq.setParameter("del", "Delivered");
		List<Integer> ordderIdList = nq.list();
		return ordderIdList;
	}
}
