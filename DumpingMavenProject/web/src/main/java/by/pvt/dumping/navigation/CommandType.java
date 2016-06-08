package by.pvt.dumping.navigation;


import by.pvt.dumping.navigation.command.AddGoodsCommand;
import by.pvt.dumping.navigation.command.AdminHomeCommand;
import by.pvt.dumping.navigation.command.BanCutomerCommand;
import by.pvt.dumping.navigation.command.BuyCommand;
import by.pvt.dumping.navigation.command.CartCommand;
import by.pvt.dumping.navigation.command.CategoryViewCommand;
import by.pvt.dumping.navigation.command.Command;
import by.pvt.dumping.navigation.command.CreateManagerCommand;
import by.pvt.dumping.navigation.command.CustomerHomeCommand;
import by.pvt.dumping.navigation.command.CustomerRegisterCommand;
import by.pvt.dumping.navigation.command.DeleteGoodsCommand;
import by.pvt.dumping.navigation.command.DeleteManagerCommand;
import by.pvt.dumping.navigation.command.GoodsDetailsCommand;
import by.pvt.dumping.navigation.command.LoginCommand;
import by.pvt.dumping.navigation.command.ManagerHomeCommand;
import by.pvt.dumping.navigation.command.PayCommand;
import by.pvt.dumping.navigation.command.SelectLanguageCommand;
import by.pvt.dumping.navigation.command.SignOutCommand;
import by.pvt.dumping.navigation.command.ViewDebtorsCommand;
import by.pvt.dumping.navigation.command.ViewManagersCommand;
import by.pvt.dumping.navigation.command.WantHomeCommand;
import by.pvt.dumping.navigation.command.WantLoginCommand;
import by.pvt.dumping.navigation.command.WantRegisterCommand;

public enum CommandType {
	LANGUAGE(new SelectLanguageCommand()),
	WANT_REGISTER(new WantRegisterCommand()),
	WANT_LOGIN(new WantLoginCommand()),
	CATEGORY(new CategoryViewCommand()),
	GOODS_DETAILS(new GoodsDetailsCommand()),
	WANT_HOME(new WantHomeCommand()),
	LOGIN(new LoginCommand()),
	ADMIN_WANT_HOME(new AdminHomeCommand()),
	WANT_SIGN_OUT(new SignOutCommand()),
	CUSTOMER_REGISTER(new CustomerRegisterCommand()),
	CUSTOMER_WANT_HOME(new CustomerHomeCommand()),
	CREATE_MANAGER(new CreateManagerCommand()),
	DELETE_MANAGER(new DeleteManagerCommand()),
	VIEW_MANAGERS(new ViewManagersCommand()),
	MANAGER_WANT_HOME(new ManagerHomeCommand()),
	VIEW_DEBTORS(new ViewDebtorsCommand()),
	DELETE_GOODS(new DeleteGoodsCommand()),
	ADD_GOODS(new AddGoodsCommand()),
	BAN_CUSTOMER(new BanCutomerCommand()),
	WANT_BUY(new BuyCommand()),
	CART(new CartCommand()),
	PAY(new PayCommand());
	
	private final Command command;

	private CommandType(Command command) {
		this.command = command;
	}

	public Command getCommand() {
		return command;
	}

	
	
}
