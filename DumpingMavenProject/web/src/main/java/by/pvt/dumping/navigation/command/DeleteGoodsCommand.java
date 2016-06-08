package by.pvt.dumping.navigation.command;

import by.pvt.dumping.managers.MessageManager;
import by.pvt.dumping.managers.PathsManager;
import by.pvt.dumping.service.CatalogueService;

import javax.servlet.http.HttpServletRequest;



public class DeleteGoodsCommand implements Command {

	private static final String ATTR_GREMOVED = "gremoved";
	private static final String PAGE_MANAGER_HOME = "manager.home";
	private static final String ATTR_GNOTREMOVED = "gnotremoved";
	private static final String ATTR_LANGUAGE = "language";
	private static final String PARAM_ID = "id";

	@Override
	public String execute(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter(PARAM_ID));
		String lang = (String) request.getSession().getAttribute(ATTR_LANGUAGE);

		CatalogueService catService = new CatalogueService();
		if (catService.isDefaultInt(id)) {
			request.setAttribute(ATTR_GNOTREMOVED, MessageManager.getProperty(
					"message.goods_not_removed", lang));
			return PathsManager.getProperty(PAGE_MANAGER_HOME);
		} else {
			catService.removeGoods(id);
			request.setAttribute(ATTR_GREMOVED,
					MessageManager.getProperty("message.goods_removed", lang));
			return PathsManager.getProperty(PAGE_MANAGER_HOME);
		}

	}
	
}
