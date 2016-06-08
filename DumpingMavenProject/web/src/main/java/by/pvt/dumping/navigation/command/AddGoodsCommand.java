package by.pvt.dumping.navigation.command;

import by.pvt.dumping.entity.Category;
import by.pvt.dumping.entity.Goods;
import by.pvt.dumping.managers.MessageManager;
import by.pvt.dumping.managers.PathsManager;
import by.pvt.dumping.service.CatalogueService;

import javax.servlet.http.HttpServletRequest;

public class AddGoodsCommand implements Command {

	private static final String PAGE_MANAGER_HOME = "manager.home";
	private static final String ATTR_GOODS_NOT_ADDED = "gnotadded";
	private static final String ATTR_GOODS_ADDED = "gadded";
	private static final String IMAGE_PACK = "/images/goods/";
	private static final String PARAM_CATEGORY = "category";
	private static final String PARAM_TITLE = "title";
	private static final String PARAM_DESCRIPTION = "description";
	private static final String PARAM_IMAGE = "image";
	private static final String PARAM_PRICE = "price";
	private static final String PARAM_LANGUAGE = "language";

	@Override
	public String execute(HttpServletRequest request) {
		String category = request.getParameter(PARAM_CATEGORY);
		String title = request.getParameter(PARAM_TITLE);
		int price = Integer.parseInt(request.getParameter(PARAM_PRICE));
		String description = request.getParameter(PARAM_DESCRIPTION);
		String image = IMAGE_PACK + request.getParameter(PARAM_IMAGE);
		String lang = (String) request.getSession()
				.getAttribute(PARAM_LANGUAGE);

		CatalogueService catService = new CatalogueService();
		if (catService.isEmpty(category) || catService.isEmpty(title)
				|| catService.isDefaultInt(price)
				|| catService.isEmpty(description) || catService.isEmpty(image)) {
			request.setAttribute(ATTR_GOODS_ADDED,
					MessageManager.getProperty("message.goods_added", lang));
			return PathsManager.getProperty(PAGE_MANAGER_HOME);
		} else {
			Goods goods = new Goods();
			goods.setCategory(Category.valueOf(category.toUpperCase()));
			goods.setTitle(title);
			goods.setPrice(price);
			goods.setDescription(description);
			goods.setPhoto(image);
			catService.addGoods(goods);
			request.setAttribute(ATTR_GOODS_NOT_ADDED,
					MessageManager.getProperty("message.goods_not_added", lang));
			return PathsManager.getProperty(PAGE_MANAGER_HOME);
		}
	}
	
}
