package by.pvt.dumping.navigation.command;

import by.pvt.dumping.entity.Goods;
import by.pvt.dumping.managers.PathsManager;
import by.pvt.dumping.service.OrderService;

import javax.servlet.http.HttpServletRequest;



public class GoodsDetailsCommand implements Command {

	private static final String PAGE_GOODS_DETAILS_CUSTOMER = "customer.page-goods-details";
	private static final String PAGE_GOODS_DETAILS = "common.page-goods-details";
	private static final String ATTR_GOODS = "goods";
	private static final String PAGE_GUEST_LOOK = "common.page-guest-look";
	public static final String PARAM_GOODS_ID = "id";
	private static final String ATTR_USER = "user";

	@Override
	public String execute(HttpServletRequest request) {
		int goodsId = Integer.parseInt(request.getParameter(PARAM_GOODS_ID));

		OrderService orderService = new OrderService();
		if (orderService.isDefaultInt(goodsId)) {
			return PathsManager.getProperty(PAGE_GUEST_LOOK);
		} else {
			Goods currGoods = orderService.getGoodsById(goodsId);
			request.setAttribute(ATTR_GOODS, currGoods);
			if (request.getSession().getAttribute(ATTR_USER) == null) {
				return PathsManager.getProperty(PAGE_GOODS_DETAILS);
			} else {
				return PathsManager.getProperty(PAGE_GOODS_DETAILS_CUSTOMER);
			}
		}

	}
	
}
