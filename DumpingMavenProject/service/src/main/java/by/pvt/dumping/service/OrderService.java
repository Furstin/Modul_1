package by.pvt.dumping.service;

import by.pvt.dumping.database.dao.implementations.OrderDAOImpl;
import by.pvt.dumping.database.dao.interfaces.OrderDAO;
import by.pvt.dumping.entity.Goods;

import java.util.List;


public class OrderService {

	private OrderDAO orderDAO;

	public OrderService() {
		orderDAO = new OrderDAOImpl();
	}

	public int getOrderId(int accountId) {
		return orderDAO.getOrderId(accountId);
	}

	public List<Goods> getUnpaidGoods(int orderId) {
		return orderDAO.getUnpaidGoods(orderId);
	}

	public Goods getGoodsById(int id) {
		return orderDAO.getGoodsById(id);
	}

	public boolean addGoodsToOrder(int goodsId, int orderId) {
		return orderDAO.addGoodsToOrder(goodsId, orderId);
	}

	public boolean payForOrder(int id) {
		return orderDAO.payForOrder(id);
	}

	public boolean isDefaultInt(int accountId) {
		return orderDAO.isDefaultInt(accountId);
	}
	
}
