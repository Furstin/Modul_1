package by.pvt.dumping.database.dao.implementations;

import by.pvt.dumping.database.ConnectionPool;
import by.pvt.dumping.database.dao.DAOUtils;
import by.pvt.dumping.database.dao.interfaces.CatalogueDAO;
import by.pvt.dumping.entity.Catalogue;
import by.pvt.dumping.entity.Category;
import by.pvt.dumping.entity.Goods;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CatalogueDAOImpl implements CatalogueDAO {

	private static final Logger LOGGER = Logger
			.getLogger(CatalogueDAOImpl.class);
	private static final String SQL_QUERY_ADD_GOODS = "INSERT INTO goods (category,title,price,description,photo) VALUES (?,?,?,?,?)";
	private static final String SQL_QUERY_REMOVE_GOODS = "DELETE FROM goods WHERE id=?";
	private static final String SQL_QUERY_COUNT_ALL = "SELECT COUNT(id) FROM goods";
	private static final String SQL_QUERY_COUNT_CATEGORY = "SELECT COUNT(id) FROM goods WHERE category=?";
	private static final String SQL_QUERY_SELECT_ALL = "SELECT * FROM goods LIMIT 10 OFFSET ?";
	private static final String SQL_QUERY_SELECT_CATEGORY = "SELECT * FROM goods WHERE category=? LIMIT 10 OFFSET ?";

	private CatalogueDAO catalogueDAO;

	public CatalogueDAO getCatalogueDAO() {
		return catalogueDAO;
	}

	public void setCatalogueDAO (CatalogueDAO catalogueDAO) {
		this.catalogueDAO = catalogueDAO;
	}


	@Override
	public Catalogue getCatalogueByCategory(Category category, int offset) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Catalogue catalogue = null;
		ConnectionPool pool = null;

		pool = ConnectionPool.getInstance();
		connection = pool.getConnection();
		try {
			if (category != Category.ALL) {
				statement = connection
						.prepareStatement(SQL_QUERY_SELECT_CATEGORY);
				statement.setString(1, category.toString().toLowerCase());
				statement.setInt(2, offset);
				resultSet = statement.executeQuery();
				catalogue = initCatalogue(resultSet);
			} else {
				statement = connection.prepareStatement(SQL_QUERY_SELECT_ALL);
				statement.setInt(1, offset);
				resultSet = statement.executeQuery();
				catalogue = initCatalogue(resultSet);
			}
		} catch (SQLException e) {
			LOGGER.error("dao exception", e);
		} finally {
			DAOUtils.closeStatement(statement);
			pool.close(connection);
		}
		return catalogue;
	}

	private Catalogue initCatalogue(ResultSet resultSet) throws SQLException {
		Catalogue catalogue = new Catalogue();
		while (resultSet.next()) {
			Goods goods = makeGoods(resultSet);
			catalogue.addGoods(goods);
		}
		return catalogue;
	}

	private Goods makeGoods(ResultSet resultSet) throws SQLException {

		Goods goods = new Goods();
		goods.setGoodsId(resultSet.getInt(1));
		goods.setCategory(Category
				.valueOf(resultSet.getString(2).toUpperCase()));
		goods.setTitle(resultSet.getString(3));
		goods.setPrice(resultSet.getInt(4));
		goods.setDescription(resultSet.getString(5));
		goods.setPhoto(resultSet.getString(6));
		return goods;
	}

	@Override
	public int getNumberOfPages(Category category) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		ConnectionPool pool = null;
		int number = 0;

		pool = ConnectionPool.getInstance();
		connection = pool.getConnection();
		try {

			if (category != Category.ALL) {
				statement = connection
						.prepareStatement(SQL_QUERY_COUNT_CATEGORY);
				statement.setString(1, category.toString().toLowerCase());
				resultSet = statement.executeQuery();
				number = initNumber(resultSet);
			} else {
				statement = connection.prepareStatement(SQL_QUERY_COUNT_ALL);
				resultSet = statement.executeQuery();
				number = initNumber(resultSet);
			}
		} catch (SQLException e) {
			LOGGER.error("dao exception", e);
		} finally {
			DAOUtils.closeStatement(statement);
			pool.close(connection);
		}
		return number;
	}

	private int initNumber(ResultSet resultSet) throws SQLException {
		int number = 0;
		while (resultSet.next()) {
			number = resultSet.getInt(1);
		}
		if (number % 10 != 0) {
			return number / 10 + 1;
		} else {
			return number / 10;
		}
	}

	@Override
	public boolean removeGoods(int id) {
		Connection connection = null;
		PreparedStatement removeGoodsStatement = null;
		ConnectionPool pool = null;

		try {
			pool = ConnectionPool.getInstance();
			connection = pool.getConnection();
			removeGoodsStatement = connection
					.prepareStatement(SQL_QUERY_REMOVE_GOODS);

			removeGoodsStatement.setInt(1, id);
			removeGoodsStatement.executeUpdate();

		} catch (SQLException e) {
			LOGGER.error("dao exception", e);
			return false;
		} finally {
			DAOUtils.closeStatement(removeGoodsStatement);
			pool.close(connection);
		}
		return true;
	}

	@Override
	public boolean addGoods(Goods goods) {
		Connection connection = null;
		PreparedStatement addGoodsStatement = null;
		ConnectionPool pool = null;

		try {
			pool = ConnectionPool.getInstance();
			connection = pool.getConnection();

			addGoodsStatement = connection
					.prepareStatement(SQL_QUERY_ADD_GOODS);

			addGoodsStatement.setString(1, goods.getCategory().toString()
					.toLowerCase());
			addGoodsStatement.setString(2, goods.getTitle());
			addGoodsStatement.setInt(3, goods.getPrice());
			addGoodsStatement.setString(4, goods.getDescription());
			addGoodsStatement.setString(5, goods.getPhoto());

			addGoodsStatement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("dao exception", e);
			return false;
		} finally {
			DAOUtils.closeStatement(addGoodsStatement);
			pool.close(connection);
		}
		return true;
	}

	@Override
	public boolean isEmpty(String parameter) {
		return "".equals(parameter);
	}

	@Override
	public boolean isDefaultInt(int price) {
		return price == 0;
	}
}
	
	

