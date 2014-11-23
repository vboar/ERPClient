package businesslogicservice.controllerfactoryblservice;

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

public interface ControllerFactory {

	public AccountController getAccountController() ;
	
	public CategoryController getCategoryController() ;
	
	public CommodityController getCommodityController() ;
	
	public CustomerController getCustomerController() ;
	
	public LossController getLossController() ;
	
	public OverflowController getOverflowController() ;
	
	public WarningController getWarningController() ;
	
	public InitialController getInitialController() ;
	
	public LogController getLogController() ;
	
	public MessageController getMessageController() ;
	
	public CashController getCashController() ;
	
	public PaymentController getPaymentController() ;
	
	public PresentController getPresentController() ;
	
	public CustomerController getCustomerGiftController() ;
	
	public PromotionController getTotalGiftController();
	
	public PurchaseController getPurchaseController();
	
	public SaleController getSaleController();
	
	public StockController getStockController();
	
	public UserController getUserController();
	
	public LoginController getLoginController();
	
}
