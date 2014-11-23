package businesslogic.controllerfactory;

import businesslogic.accountbl.AccountController;
import businesslogic.commoditybl.CategoryController;
import businesslogic.commoditybl.CommodityController;
import businesslogic.customerbl.CustomerController;
import businesslogic.exceptionbl.LossController;
import businesslogic.exceptionbl.OverflowController;
import businesslogic.exceptionbl.WarningController;
import businesslogic.initialbl.InitialController;
import businesslogic.logbl.LogController;
import businesslogic.loginbl.LoginController;
import businesslogic.messagebl.MessageController;
import businesslogic.paymentbl.CashController;
import businesslogic.paymentbl.PaymentController;
import businesslogic.presentbl.PresentController;
import businesslogic.promotionbl.PromotionController;
import businesslogic.purchasebl.PurchaseController;
import businesslogic.salebl.SaleController;
import businesslogic.stockbl.StockController;
import businesslogic.userbl.UserController;
import businesslogicservice.controllerfactoryblservice.ControllerFactory;

public class ControllerFactoryImpl implements ControllerFactory{

	private ControllerFactoryImpl() {}
	
	public static ControllerFactoryImpl getInstance() {
		return ControllerFactoryImplHolder.controllerFactory;
	}
	
	private static class ControllerFactoryImplHolder {
		private static ControllerFactoryImpl controllerFactory = new ControllerFactoryImpl();
	}
	@Override
	public AccountController getAccountController() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CategoryController getCategoryController() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommodityController getCommodityController() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CustomerController getCustomerController() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LossController getLossController() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OverflowController getOverflowController() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WarningController getWarningController() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InitialController getInitialController() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LogController getLogController() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MessageController getMessageController() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CashController getCashController() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PaymentController getPaymentController() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PresentController getPresentController() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CustomerController getCustomerGiftController() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PromotionController getTotalGiftController() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PurchaseController getPurchaseController() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SaleController getSaleController() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StockController getStockController() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserController getUserController() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LoginController getLoginController() {
		return new LoginController();
	}

}
