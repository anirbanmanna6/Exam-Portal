package com.daoImpl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.dao.ICartDAO;
import com.entity.Cart;
import com.entity.Category;
import com.entity.Product;

@Repository
@Transactional
public class CartDAOImpl implements ICartDAO {

	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Override
	public boolean addCart(Cart cart) {
		try {
			int cartId = (Integer) hibernateTemplate.save(cart);
			if (cartId != 0)
				return true;
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Cart getCart(int cartId) // only fetches records if order not done (i.e ordder_order_id is null)
	{
		Cart fetchedCart = null;
		try {

			String query = "select c.cart_id from Cart c where c.cart_id=:x and c.ordder_order_id is null";
			Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
			NativeQuery nq = session.createSQLQuery(query);
			nq.setParameter("x", cartId);
			int cartIdFiltered = (Integer) nq.uniqueResultOptional().orElse(0);
			System.out.println("Inside getCart()- " + cartIdFiltered);
			if (cartIdFiltered != 0)
				fetchedCart = hibernateTemplate.get(Cart.class, cartIdFiltered);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return fetchedCart;
	}

	@Override
	public boolean deleteCart(Cart cart) {
		// TODO Auto-generated method stub
		try {
			hibernateTemplate.delete(cart);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean updateCart(Cart cart) {
		// TODO Auto-generated method stub
		try {
			hibernateTemplate.update(cart);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Cart> getAllCart(String email) {
		List<Cart> cartList = new ArrayList<Cart>();
		try {
			List<Integer> cartIdList = this.fetchAllCartIdByEmail(email);
			for (Integer cartId : cartIdList) {
				System.out.println(cartId);
				cartList.add(this.getCart(cartId)); // calling getCart() method and populate the cartList
			}
			return cartList;

		} catch (Exception e) {
			e.printStackTrace();
			return cartList;
		}

	}

	@Override
	public Cart getCart(String email) // only fetches records if order not done (i.e ordder_order_id is null)
	{
		Cart fetchedCart = null;
		try {

			String query = "select c.cart_id from Cart c where c.user_user_email=:x and c.ordder_order_id is null";
			Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
			NativeQuery nq = session.createSQLQuery(query);
			nq.setParameter("x", email);
			int cartIdFiltered = (Integer) nq.uniqueResultOptional().orElse(0);
			System.out.println("Inside getCart(email)- " + cartIdFiltered);
			if (cartIdFiltered != 0)
				fetchedCart = hibernateTemplate.get(Cart.class, cartIdFiltered);

			return fetchedCart;

		} catch (Exception e) {
			return fetchedCart;
		}

	}

	// Only child class method
	public List<Integer> fetchAllCartIdByEmail(String email) {
		String query = "select c.cart_id from Cart c where c.user_user_email=:x and c.ordder_order_id is null";
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		NativeQuery nq = session.createSQLQuery(query);
		nq.setParameter("x", email);
		List<Integer> cartIdList = nq.list();
		return cartIdList;
	}

	@Override
	public boolean updateOrderId(String email, int ordderId) {
		try {
			// fetch all cart Id
			// List<Integer> cartIdList = this.fetchAllCartIdByEmail(email);

			// update ordder_order_id in Cart table
			String query = "update Cart c set c.ordder_order_id =:x, c.cart_payment_status='Done' where c.user_user_email=:y and c.ordder_order_id is null";
			Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
			NativeQuery nq = session.createSQLQuery(query);
			nq.setParameter("x", ordderId);
			nq.setParameter("y", email);
			int rows = nq.executeUpdate(); // update the records
			if (rows > 0)
				return true;
			else
				return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Cart> getCartUsingOrdderId(int ordderId) {
		// TODO Auto-generated method stub
		List<Cart> cartList = new ArrayList<Cart>();
		try {
			List<Integer> cartIdList = this.fetchAllCartIdByOrdderId(ordderId);
			for (Integer cartId : cartIdList) {
				// System.out.println(cartId);
				cartList.add(hibernateTemplate.get(Cart.class, cartId));
			}
			return cartList;

		} catch (Exception e) {
			e.printStackTrace();
			return cartList;
		}
	}

	// only child method
	public List<Integer> fetchAllCartIdByOrdderId(int ordderId) {
		try {
			String query = "select c.cart_id from Cart c where c.ordder_order_id=:x";
			Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
			NativeQuery nq = session.createSQLQuery(query);
			nq.setParameter("x", ordderId);
			List<Integer> cartIdList = nq.list();
			return cartIdList;
		} catch (Exception e) {
			return null;
		}
	}
}
