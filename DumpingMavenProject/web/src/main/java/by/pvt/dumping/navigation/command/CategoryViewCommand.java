package by.pvt.dumping.navigation.command;

import by.pvt.dumping.entity.Catalogue;
import by.pvt.dumping.entity.Category;
import by.pvt.dumping.managers.PathsManager;
import by.pvt.dumping.service.CatalogueService;

import javax.servlet.http.HttpServletRequest;


public class CategoryViewCommand implements Command{

	private static final String PAGE_VIEW_CATEGORY_CUSTOMER = "customer.page-view-category";
	private static final String PAGE_VIEW_CATEGORY_COMMON = "common.page-view-category";
	private static final String ATTR_CATALOGUE = "catalogue";
	private static final String ATTR_PAGES = "pages";
	private static final String PAGE_GUEST_LOOK = "common.page-guest-look";
	public static final String ATTR_CATEGORY = "type";
	private static final String ATTR_USER = "user";
	private static final String PARAM_NUM = "num";

	@Override
	public String execute(HttpServletRequest request) {
		String strCategory = request.getParameter(ATTR_CATEGORY);
		request.setAttribute(ATTR_CATEGORY, strCategory);

		CatalogueService catService = new CatalogueService();
		if (catService.isEmpty(strCategory)) {
			return PathsManager.getProperty(PAGE_GUEST_LOOK);
		} else {
			Category category = Category.valueOf(strCategory.toUpperCase());
			int numberOfPages = catService.getNumberOfPages(category);
			request.setAttribute(ATTR_PAGES, numberOfPages);

			int offset = Integer.parseInt(request.getParameter(PARAM_NUM)) * 10 - 10;
			Catalogue catalogue = catService.getCatalogueByCategory(category,
					offset);
			request.setAttribute(ATTR_CATALOGUE, catalogue.getCatalogue());

			if (request.getSession().getAttribute(ATTR_USER) == null) {
				return PathsManager.getProperty(PAGE_VIEW_CATEGORY_COMMON);
			} else {
				return PathsManager.getProperty(PAGE_VIEW_CATEGORY_CUSTOMER);
			}
		}
	}
	
}
