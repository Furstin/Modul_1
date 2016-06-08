package by.pvt.dumping.database.dao.implementations;

import by.pvt.dumping.database.ConnectionPool;
import by.pvt.dumping.database.dao.DAOUtils;
import by.pvt.dumping.database.dao.interfaces.OrderDAO;
import by.pvt.dumping.entity.Category;
import by.pvt.dumping.entity.Goods;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO{

	private static final Logger LOGGER = Logger.getLogger(OrderDAOImpl.class);
	private static final String SQL_QUERY_CREATE_ORDER = "INSERT INTO orders(account_id) VALUES (?);";
	private static final String SQL_QUERY_GET_ORDER_ID = "SELECT id FROM orders WHERE (account_id=? AND payment='N')";
	private static final String SQL_QUERY_GET_UNPAID_GOODS = "SELECT goods_id, category, title, price, description, photo FROM orders_goods LEFT JOIN goods ON goods_id=id WHERE order_id=?";
	private static final String SQL_QUERY_GET_GOODS_BY_ID = "SELECT id, category, title, price, description, photo FROM goods WHERE id=?";
	private static final String SQL_QUERY_ADD_GOODS_TO_ORDER = "INSERT INTO orders_goods VALUES (?, ?)";
	private static final String SQL_QUERY_PAY_ORDER = "UPDATE orders SET payment = 'Y' WHERE id = ?";

	private OrderDAO orderDAO;
	public OrderDAO getOrderDAO() {
		return orderDAO;
	}

	public void setOrderDAO(OrderDAO orderDAO) {
		this.orderDAO = orderDAO;
	}


	@Override
	public boolean addGoodsToOrder(int goodsId, int orderId) {
		ConnectionPool pool = null;
		Connection connection = null;
		PreparedStatement statement = null;

		try {
			pool = ConnectionPool.getInstance();
			connection = pool.getConnection();
			statement = connection
					.prepareStatement(SQL_QUERY_ADD_GOODS_TO_ORDER);
			statement.setInt(1, orderId);
			statement.setInt(2, goodsId);
			statement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("dao exception", e);
			return false;
		} finally {
			DAOUtils.closeStatement(statement);
			pool.close(connection);
		}
		return true;
	}

	@Override
	public Goods getGoodsById(int id) {
		ConnectionPool pool = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Goods goods = null;

		try {
			pool = ConnectionPool.getInstance();
			connection = pool.getConnection();
			statement = connection.prepareStatement(SQL_QUERY_GET_GOODS_BY_ID);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();
			goods = initGoods(resultSet);
		} catch (SQLException e) {
			LOGGER.error("dao exception", e);
		} finally {
			DAOUtils.closeStatement(statement);
			pool.close(connection);
		}
		return goods;
	}

	private Goods initGoods(ResultSet resultSet) throws SQLException {
		Goods goods = null;
		while (resultSet.next()) {
			goods = new Goods();

			goods.setGoodsId(resultSet.getInt(1));
			goods.setCategory(Category.valueOf(resultSet.getString(2)
					.toUpperCase()));
			goods.setTitle(resultSet.getString(3));
			goods.setPrice(resultSet.getInt(4));
			goods.setDescription(resultSet.getString(5));
			goods.setPhoto(resultSet.getString(6));
		}
		return goods;
	}

	@Override
	public List<Goods> getUnpaidGoods(int orderId) {
		ConnectionPool pool = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<Goods> list = null;

		try {
			pool = ConnectionPool.getInstance();
			connection = pool.getConnection();
			statement = connection.prepareStatement(SQL_QUERY_GET_UNPAID_GOODS);
			statement.setInt(1, orderId);
			resultSet = statement.executeQuery();
			list = initList(resultSet);
		} catch (SQLException e) {
			LOGGER.error("dao exception", e);
		} finally {
			DAOUtils.closeStatement(statement);
			pool.close(connection);
		}
		return list;
	}

	private List<Goods> initList(ResultSet resultSet) throws SQLException {
		List<Goods> list = new ArrayList<>();
		Goods goods = null;
		while (resultSet.next()) {
			goods = new Goods();

			goods.setGoodsId(resultSet.getInt(1));
			goods.setCategory(Category.valueOf(resultSet.getString(2)
					.toUpperCase()));
			goods.setTitle(resultSet.getString(3));
			goods.setPrice(resultSet.getInt(4));
			goods.setDescription(resultSet.getString(5));
			goods.setPhoto(resultSet.getString(6));

			list.add(goods);
		}
		return list;
	}

	@Override
	public int getOrderId(int accountId) {
		ConnectionPool pool = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		int orderId = 0;

		try {
			pool = ConnectionPool.getInstance();
			connection = pool.getConnection();
			statement = connection.prepareStatement(SQL_QUERY_GET_ORDER_ID);
			statement.setInt(1, accountId);
			resultSet = statement.executeQuery();
			orderId = initOrderId(resultSet);
			DAOUtils.closeStatement(statement);

			if (orderId == 0) {
				statement = connection.prepareStatement(SQL_QUERY_CREATE_ORDER);
				statement.setInt(1, accountId);
				statement.executeUpdate();
				DAOUtils.closeStatement(statement);

				statement = connection.prepareStatement(SQL_QUERY_GET_ORDER_ID);
				statement.setInt(1, accountId);
				resultSet = statement.executeQuery();
				orderId = initOrderId(resultSet);
			}
		} catch (SQLException e) {
			LOGGER.error("dao exception", e);
		} finally {
			DAOUtils.closeStatement(statement);
			pool.close(connection);
		}
		return orderId;
	}

	private int initOrderId(ResultSet resultSet) throws SQLException {
		int orderId = 0;

		while (resultSet.next()) {
			orderId = resultSet.getInt(1);
		}
		return orderId;
	}

	@Override
	public boolean payForOrder(int id) {
		ConnectionPool pool = null;
		Connection connection = null;
		PreparedStatement statement = null;

		try {
			pool = ConnectionPool.getInstance();
			connection = pool.getConnection();
			statement = connection.prepareStatement(SQL_QUERY_PAY_ORDER);
			statement.setInt(1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("dao exception", e);
			return false;
		} finally {
			DAOUtils.closeStatement(statement);
			pool.close(connection);
		}
		return true;
	}

	@Override
	public boolean isDefaultInt(int accountId) {
		return accountId == 0;
	}

	
}
