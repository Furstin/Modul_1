package by.pvt.dumping.database.dao.implementations;


import by.pvt.dumping.database.dao.interfaces.OrderDAO;
import by.pvt.dumping.entity.Goods;
import by.pvt.dumping.entity.Order;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

public class OrderDAOImplTest {

    @Mock
    OrderDAO orderDAO;
    private OrderDAOImpl orderDAOImpl;

    @Ignore
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        orderDAOImpl = new OrderDAOImpl();
        orderDAOImpl.setOrderDAO(orderDAO);
    }

    @Test
    public void addGoodsToOrderShouldAddGoods() {
        Order order = new Order();
        when(orderDAO.addGoodsToOrder(1,1)).thenReturn(false);
        boolean result = orderDAOImpl.addGoodsToOrder(1,1);

        assertFalse(result);
    }

    @Test
    public void addGoodsToOrderShouldNOTAddGoods() {
        Order order = new Order();
        when(orderDAO.addGoodsToOrder(1,1)).thenReturn(true);
        boolean result = orderDAOImpl.addGoodsToOrder(1,1);

        assertFalse(result);
    }

    @Test
    public void getGoodsByIdShouldgetGoods() {
        Goods goods = new Goods();
        when(orderDAO.getGoodsById(123)).thenReturn(goods);
        boolean result = orderDAOImpl.addGoodsToOrder(123,1);

        assertFalse(result);
    }

    @Test
    public void getOrderIdShouldgetOrderId() {
        Order order = new Order();
        when(orderDAO.getOrderId(123)).thenReturn(new Integer(1));
        boolean result = orderDAOImpl.payForOrder(123);

        assertTrue(result);
    }

}