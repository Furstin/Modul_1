package by.pvt.dumping.navigation.command;

import by.pvt.dumping.entity.Order;
import by.pvt.dumping.managers.PathsManager;
import by.pvt.dumping.service.OrderService;

import javax.servlet.http.HttpServletRequest;


public class CartCommand implements Command {

	private static final String PAGE_CUSTOMER_CART = "customer.cart";
	private static final String ATTR_ORDER = "order";
	private static final String PAGE_GUEST_LOOK = "common.page-guest-look";
	private static final String PARAM_ACCOUNT_ID = "account_id";

	@Override
	public String execute(HttpServletRequest request) {
		int accountId = Integer
				.parseInt(request.getParameter(PARAM_ACCOUNT_ID));

		OrderService orderService = new OrderService();
		if (orderService.isDefaultInt(accountId)) {
			return PathsManager.getProperty(PAGE_GUEST_LOOK);
		} else {
			Order order = new Order();
			order.setId(orderService.getOrderId(accountId));
			order.setGoods(orderService.getUnpaidGoods(order.getId()));
			request.setAttribute(ATTR_ORDER, order);
			return PathsManager.getProperty(PAGE_CUSTOMER_CART);
		}

	}

	
}
