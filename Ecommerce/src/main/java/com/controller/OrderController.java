package com.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.constant.MyConstant;
import com.entity.Cart;
import com.entity.Ordder;
import com.entity.User;

import com.service.ICartService;
import com.service.IOrdderService;
import com.service.IUserService;

@Controller
public class OrderController 
{
	
	@Autowired
	IOrdderService orderService;
	
	@Autowired
	IUserService userService;
	
	@Autowired
	ICartService cartService;
	
	List<Cart> cartList = new ArrayList<Cart>();
	
	
	@RequestMapping(value = "/checkOutAddressChange", method=RequestMethod.POST)
	public String checkOutAddressChangeHandler(Model m, HttpSession session, 
			@RequestParam("deliveryAddress") String deliveryAddress,
			@RequestParam("grandTotal") String grandTotal
			)
	{
		String userEmail = (String)session.getAttribute("userEmail");
		User user = userService.getUser(userEmail);
		Ordder ordder = new Ordder();
		ordder.setDeliveryAddress(deliveryAddress);
		ordder.setOrderAmount(Double.parseDouble(grandTotal));
		ordder.setUser(user);
		if(orderService.getOrdder(userEmail)==null)
		{
			orderService.addOrdder(ordder);
		}
		else
		{
			//If we omit this line then updation fails as new order id is taken into consideration
			ordder.setOrderId(orderService.getOrdder(userEmail).getOrderId());
			orderService.updateOrdder(ordder);
		}
		
		System.out.println("OrderController - checkOutAddressChangeHandler() - Grand total = "+grandTotal);
		List<Cart> cartList = this.cartListHandler(session);
		m.addAttribute("cartList", cartList);
		m.addAttribute("grandTotal",grandTotal);
		return "payment";
	}
	
	//Fetches the List of All cart Items using email
	public List<Cart> cartListHandler(HttpSession session)
	{		
		cartList = cartService.getAllCart((String)session.getAttribute("userEmail"));
		return cartList;
	}
	
	
	@RequestMapping(value = "/paymentSuccess", method=RequestMethod.POST)
	public String paymentSuccessHandler(Model m, HttpSession session)
	{
		String userEmail = (String)session.getAttribute("userEmail");
		Ordder ordder = orderService.getOrdder(userEmail) ;
		
		// populate ordder_order_id in 'cart' table
		boolean updateCartByOrderId = cartService.updateOrderId(userEmail, ordder.getOrderId());
		System.out.println(updateCartByOrderId);
		
		//update order status as CON in 'order' table
		ordder.setOrderStatus(MyConstant.ORDER_CONFIRMED);
		orderService.updateOrdder(ordder);
		
		
		m.addAttribute("ordder",ordder);
		return "paymentSuccess";
	}
	
	@RequestMapping(value="/order")
	public String orderDisplayHandler(Model m, HttpSession session)
	{
		String userEmail = (String)session.getAttribute("userEmail");
		List<Ordder> ordderList = orderService.getAllOrdder(userEmail);
		
		m.addAttribute("ordderList",ordderList);
		
		return "order";
	}
	
	@RequestMapping(value="/orderIndividual/{ordderId}")
	public String orderIndividualHandler(Model m, HttpSession session, @PathVariable("ordderId") int ordderId)
	{
		List<Cart> cartList = cartService.getCartUsingOrdderId(ordderId);
		m.addAttribute("cartList",cartList);
		m.addAttribute("grandTotal",orderService.getOrdder(ordderId).getOrderAmount());
		m.addAttribute("ordder",orderService.getOrdder(ordderId));
		System.out.println(orderService.getOrdder(ordderId).getOrderStatus());
		return "orderIndividual";
	}
	
	@RequestMapping(value="/downloadInvoice/{ordderId}")
	public String downloadInvoiceHandler(Model m, HttpSession session, @PathVariable("ordderId") int ordderId) throws IOException
	{
		List<Cart> cartList = cartService.getCartUsingOrdderId(ordderId);
		
		/*
		Document document = new Document();
		
		String fileName = "invoice_"+ordderId;
		String path = session.getServletContext().getRealPath("/")+"WEB-INF"+
				File.separator + "resources"+File.separator+"invoice"+ File.separator +fileName;
		PdfWriter.getInstance(document, new FileOutputStream(path));

		document.open();
		Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
		Chunk chunk = new Chunk("Hello World", font);

		document.add(chunk);
		document.close();
		
		*/
		
		
		
		m.addAttribute("cartList",cartList);
		m.addAttribute("grandTotal",orderService.getOrdder(ordderId).getOrderAmount());
		m.addAttribute("ordder",orderService.getOrdder(ordderId));
		System.out.println(orderService.getOrdder(ordderId).getOrderStatus());
		return "orderIndividual";
		
	}
	
}
