package com.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.model.OrderCommand;
import com.model.OrderItem;

//하나의 요청 주소로 2개 업무를 처리
// /order/order.do

//화면 출력은 GET, 로직 처리는 POST

@Controller
@RequestMapping("/order/order.do")
public class OrderController {
	
	@RequestMapping(method=RequestMethod.GET)
	public String form() {
		return "order/OrderForm"; //view 주소를 리턴
		
		// WWEB-INF/views/order/OrderForm.jsp 
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String submit(OrderCommand ordercommand) {
		
		/*
			OrderCommand ordercommand = new OrderCommand();
			private List<OrderItem> orderItem;

			List<OrderItem> itemlist = new ArrayList<>();			
			itemlist.add(new OrderItem(1,1, "A"));
			itemlist.add(new OrderItem(2,2, "B"));
			itemlist.add(new OrderItem(3,3, "C"));
			
			command.setOrderItem(itemlist);
	
		 */
		
		return "order/OrderCommited";
	}

}
