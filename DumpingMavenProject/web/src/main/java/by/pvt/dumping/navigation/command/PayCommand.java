package by.pvt.dumping.navigation.command;

import by.pvt.dumping.managers.PathsManager;
import by.pvt.dumping.service.OrderService;

import javax.servlet.http.HttpServletRequest;



public class PayCommand implements Command {

	private static final String PAGE_CUSTOMER_CART = "customer.cart";
	private static final String PAGE_GUEST_LOOK = "common.page-guest-look";
	private static final String PARAM_ORDER_ID = "order_id";

	@Override
	public String execute(HttpServletRequest request) {
		OrderService orderService = new OrderService();
		int id = Integer.parseInt(request.getParameter(PARAM_ORDER_ID));
		if (orderService.isDefaultInt(id)) {
			return PathsManager.getProperty(PAGE_GUEST_LOOK);
		} else {
			orderService.payForOrder(id);
			return PathsManager.getProperty(PAGE_CUSTOMER_CART);
		}

	}
	
}
