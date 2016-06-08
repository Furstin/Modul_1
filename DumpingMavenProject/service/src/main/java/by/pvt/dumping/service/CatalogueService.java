package by.pvt.dumping.service;

import by.pvt.dumping.database.dao.implementations.CatalogueDAOImpl;
import by.pvt.dumping.database.dao.interfaces.CatalogueDAO;
import by.pvt.dumping.entity.Catalogue;
import by.pvt.dumping.entity.Category;
import by.pvt.dumping.entity.Goods;

public class CatalogueService {

	private CatalogueDAO catDAO;

	public CatalogueService() {
		catDAO = new CatalogueDAOImpl();
	}

	public boolean addGoods(Goods goods) {
		return catDAO.addGoods(goods);
	}

	public Catalogue getCatalogueByCategory(Category category, int offset) {
		return catDAO.getCatalogueByCategory(category, offset);
	}

	public boolean removeGoods(int id) {
		return catDAO.removeGoods(id);
	}

	public boolean isEmpty(String category) {
		return catDAO.isEmpty(category);
	}

	public boolean isDefaultInt(int price) {
		return catDAO.isDefaultInt(price);
	}

	public int getNumberOfPages(Category category) {
		return catDAO.getNumberOfPages(category);
	}
	
}
