package by.pvt.dumping.customtags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

import by.pvt.dumping.database.ConnectionPool;



public class InfoTag extends TagSupport {

	private static Logger LOGGER = Logger.getLogger(ConnectionPool.class);

	private String id;
	private String title;
	private String price;
	private String description;

	@Override
	public int doStartTag() throws JspException {
		try {
			pageContext.getOut().write(
					"<table width=\"300px\" align=\"left\"><tr><td>ID" + id
							+ " " + title + "<br/>" + price
							+ "</td></tr><tr><td>" + description
							+ "</td></tr></table>");
		} catch (IOException e) {
			LOGGER.error("IOException in custom tag");
		}
		return SKIP_BODY;
	}
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	
}
