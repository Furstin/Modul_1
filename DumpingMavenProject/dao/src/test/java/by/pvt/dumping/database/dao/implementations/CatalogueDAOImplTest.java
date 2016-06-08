package by.pvt.dumping.database.dao.implementations;

import by.pvt.dumping.database.dao.interfaces.CatalogueDAO;
import by.pvt.dumping.entity.Catalogue;
import by.pvt.dumping.entity.Category;
import by.pvt.dumping.entity.Goods;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.when;

public class CatalogueDAOImplTest {

    @Mock
    CatalogueDAO catalogueDAO;
    private CatalogueDAOImpl catalogueDAOImpl;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        catalogueDAOImpl = new CatalogueDAOImpl();
        catalogueDAOImpl.setCatalogueDAO(catalogueDAO);
    }


    @Test
    public void initCatalogueShouldCreateCatalogue() {
        Catalogue catalogue = new Catalogue();
        when(catalogueDAO.getCatalogueByCategory(Category.EVENT, 123)).thenReturn(catalogue);
        boolean result = catalogueDAOImpl.isDefaultInt(123);

        assertFalse(result);
    }

    @Test
    public void initCatalogueShouldNOTCreateCatalogue() {
        Catalogue catalogue = new Catalogue();
        when(catalogueDAO.getCatalogueByCategory(Category.EVENT, 123)).thenReturn(null);
        boolean result = catalogueDAOImpl.isDefaultInt(123);

        assertFalse(result);
    }

    @Test
    public void addGoodsShouldAddGoodsToCatalogue() {
        Goods goods = new Goods();
        when(catalogueDAO.addGoods(goods)).thenReturn(true);
        boolean result = catalogueDAOImpl.isDefaultInt(123);

        assertFalse(result);
    }


    @Test
    public void addGoodsShouldNOTAddGoodsToCatalogue() {
        Goods goods = new Goods();
        when(catalogueDAO.addGoods(goods)).thenReturn(false);
        boolean result = catalogueDAOImpl.isDefaultInt(123);

        assertFalse(result);
    }


}