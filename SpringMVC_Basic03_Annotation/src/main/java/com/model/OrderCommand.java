package com.model;

import java.util.List;

//주문내역 클래스
//하나의 주문은 여러 개의 상품을 가질 수 있다

//게시판 중에서는 board와 reply의 관계다
//하나의 게시글은 여러 개의 댓글을 가질 수 있다

//하나의 은행으 여러 개의 계좌를 가질 수 있다
public class OrderCommand {
	
	private List<OrderItem> orderItem;

	public List<OrderItem> getOrderItem() {
		return orderItem;
	}

	public void setOrderItem(List<OrderItem> orderItem) {
		this.orderItem = orderItem;
	}	
	
}

/*

	주문이 발생했다고 가정하면
	OrderCommand command = new OrderCommand();
	
	List<OrderItem> itemlist = new ArrayList<>();
	itemlist.add(new OrderItem(1, 10, "파손주의"));
	itemlist.add(new OrderItem(10, 1, "리모콘 별도 구매"));
	
	command.setOrderItem(itemlist);

*/