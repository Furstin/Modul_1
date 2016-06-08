package by.pvt.dumping.controller;

import by.pvt.dumping.database.ConnectionPool;
import by.pvt.dumping.navigation.CommandFactory;
import by.pvt.dumping.navigation.command.Command;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet {
	
	private static final Logger LOGGER = Logger.getLogger(Controller.class);

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		String log4jLocation = config.getInitParameter("log4j-location");
		String path = getServletContext().getRealPath(log4jLocation);
		PropertyConfigurator.configure(path);
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		performAction(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		performAction(request, response);
	}

	private void performAction(HttpServletRequest request,
			HttpServletResponse response) {
		String page = request.getParameter("page");
		if (page != null) {
			Command command = CommandFactory.getCommand(page);
			String nextPage = command.execute(request);
			RequestDispatcher requestDispatcher = request
					.getRequestDispatcher(nextPage);

			try {
				requestDispatcher.forward(request, response);
			} catch (ServletException | IOException e) {
				LOGGER.error("request dispatcher forward exception", e);
			}
		}
	}

	@Override
	public void destroy() {
		super.destroy();
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		connectionPool.cleanUp();
	}
}