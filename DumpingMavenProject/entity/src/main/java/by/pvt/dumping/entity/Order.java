package by.pvt.dumping.entity;

import java.util.ArrayList;
import java.util.List;


public class Order extends AbstractEntity {

	private int id;
	private String payment;
	private List<Goods> goods = new ArrayList<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Goods> getGoods() {
		return goods;
	}

	public void setGoods(List<Goods> goods) {
		this.goods = goods;
	}

	public void addGoods(Goods goodsToAdd) {
		goods.add(goodsToAdd);
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}
	
}
