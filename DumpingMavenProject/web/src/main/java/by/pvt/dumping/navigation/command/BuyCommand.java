package by.pvt.dumping.navigation.command;

import by.pvt.dumping.entity.Order;
import by.pvt.dumping.managers.PathsManager;
import by.pvt.dumping.service.OrderService;

import javax.servlet.http.HttpServletRequest;


public class BuyCommand implements Command {

	private static final String PAGE_CUSTOMER_CART = "customer.cart";
	private static final String PAGE_GUEST_LOOK = "common.page-guest-look";
	private static final String PAGE_LOGIN = "common.page-login";
	private static final String ATTR_USER = "user";
	private static final String PARAM_ID = "id";
	private static final String PARAM_ACCOUNT_ID = "account_id";
	private static final String ATTR_ORDER = "order";

	@Override
	public String execute(HttpServletRequest request) {
		if (request.getSession().getAttribute(ATTR_USER) == null) {
			return PathsManager.getProperty(PAGE_LOGIN);
		} else {
			int id = Integer.parseInt(request.getParameter(PARAM_ID));
			int accountId = Integer.parseInt(request
					.getParameter(PARAM_ACCOUNT_ID));

			Order order = new Order();
			OrderService orderService = new OrderService();
			if (orderService.isDefaultInt(id)
					|| orderService.isDefaultInt(accountId)) {
				return PathsManager.getProperty(PAGE_GUEST_LOOK);
			} else {
				order.setId(orderService.getOrderId(accountId));
				orderService.addGoodsToOrder(id, order.getId());
				order.setGoods(orderService.getUnpaidGoods(order.getId()));
				request.setAttribute(ATTR_ORDER, order);
				return PathsManager.getProperty(PAGE_CUSTOMER_CART);
			}

		}
	}

	
}
