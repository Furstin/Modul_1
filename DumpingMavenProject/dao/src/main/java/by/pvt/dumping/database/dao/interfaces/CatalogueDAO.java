package by.pvt.dumping.database.dao.interfaces;

import by.pvt.dumping.entity.Catalogue;
import by.pvt.dumping.entity.Category;
import by.pvt.dumping.entity.Goods;

public interface CatalogueDAO {

	boolean addGoods(Goods goods);

	Catalogue getCatalogueByCategory(Category category, int offset);

	boolean removeGoods(int id);

	boolean isEmpty(String category);

	boolean isDefaultInt(int price);

	int getNumberOfPages(Category category);
	
}
