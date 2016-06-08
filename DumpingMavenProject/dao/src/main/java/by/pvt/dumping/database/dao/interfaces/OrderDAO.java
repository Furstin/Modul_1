package by.pvt.dumping.database.dao.interfaces;

import by.pvt.dumping.entity.Goods;

import java.util.List;


public interface OrderDAO {

	int getOrderId(int accountId);

	List<Goods> getUnpaidGoods(int orderId);

	Goods getGoodsById(int id);

	boolean addGoodsToOrder(int goodsId, int orderId);

	boolean payForOrder(int id);

	boolean isDefaultInt(int accountId);
	
}
