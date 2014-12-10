package businesslogic.controllerfactory;

import businesslogic.accountbl.AccountController;
import businesslogic.approvalbl.ApprovalController;
import businesslogic.businessconditionbl.BusinessConditionController;
import businesslogic.businessconditionbl.BusinessHistoryController;
import businesslogic.businessconditionbl.SaleDetailsController;
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
import businesslogic.paymentbl.ReceiptController;
import businesslogic.presentbl.PresentController;
import businesslogic.promotionbl.CustomerGiftController;
import businesslogic.promotionbl.SpecialOfferController;
import businesslogic.promotionbl.TotalGiftController;
import businesslogic.purchasebl.PurchaseController;
import businesslogic.purchasebl.PurchaseReturnController;
import businesslogic.salebl.SaleController;
import businesslogic.salebl.SaleReturnController;
import businesslogic.stockbl.StockController;
import businesslogic.userbl.UserController;
import businesslogicservice.accountblservice.AccountBLService;
import businesslogicservice.approvalblservice.ApprovalBLService;
import businesslogicservice.businessconditionblservice.BusinessConditionBLService;
import businesslogicservice.businessconditionblservice.HistoryBLService;
import businesslogicservice.businessconditionblservice.SaleDetailsBLService;
import businesslogicservice.commodityblservice.CommodityBLService;
import businesslogicservice.controllerfactoryblservice.ControllerFactory;
import businesslogicservice.customerblservice.CustomerBLService;
import businesslogicservice.exceptionblservice.ExceptionBLService;
import businesslogicservice.exceptionblservice.WarningBLService;
import businesslogicservice.initialblservice.InitialBLService;
import businesslogicservice.logblservice.LogBLService;
import businesslogicservice.loginblservice.LoginBLService;
import businesslogicservice.messageblservice.MessageBLService;
import businesslogicservice.paymentblservice.CashBLService;
import businesslogicservice.paymentblservice.PaymentBLService;
import businesslogicservice.presentblservice.PresentBLService;
import businesslogicservice.promotionblservice.CustomerGiftBLService;
import businesslogicservice.promotionblservice.SpecialOfferBLService;
import businesslogicservice.promotionblservice.TotalGiftBLService;
import businesslogicservice.purchaseblservice.PurchaseBLService;
import businesslogicservice.saleblservice.SaleBLService;
import businesslogicservice.stockblservice.StockBLService;
import businesslogicservice.userblservice.UserBLService;

public class ControllerFactoryImpl implements ControllerFactory{

	private ControllerFactoryImpl() {}
	
	public static ControllerFactoryImpl getInstance() {
		return ControllerFactoryImplHolder.controllerFactory;
	}
	
	private static class ControllerFactoryImplHolder {
		private static ControllerFactoryImpl controllerFactory = new ControllerFactoryImpl();
	}
	@Override
	public AccountBLService getAccountController() {
		return new AccountController();
	}

	@Override
	public CategoryController getCategoryController() {
		return new CategoryController();
	}

	@Override
	public CommodityBLService getCommodityController() {
		return new CommodityController();
	}

	@Override
	public CustomerBLService getCustomerController() {
		return new CustomerController();
	}

	@Override
	public ExceptionBLService getLossController() {
		return new LossController();
	}

	@Override
	public ExceptionBLService getOverflowController() {
		return new OverflowController();
	}

	@Override
	public WarningBLService getWarningController() {
		return new WarningController();
	}

	@Override
	public InitialBLService getInitialController() {
		return new InitialController();
	}

	@Override
	public LogBLService getLogController() {
		return new LogController();
	}

	@Override
	public MessageBLService getMessageController() {
		return new MessageController();
	}

	@Override
	public CashBLService getCashController() {
		return new CashController();
	}

	@Override
	public PaymentBLService getPaymentController() {
		return new PaymentController();
	}

	@Override
	public PresentBLService getPresentController() {
		return new PresentController();
	}

	@Override
	public PurchaseBLService getPurchaseController() {
		return new PurchaseController();
	}

	@Override
	public SaleBLService getSaleController() {
		return new SaleController();
	}

	@Override
	public StockBLService getStockController() {
		return new StockController();
	}

	@Override
	public UserBLService getUserController() {
		return new UserController();
	}

	@Override
	public LoginBLService getLoginController() {
		return new LoginController();
	}

	@Override
	public PaymentBLService getReceiptController() {
		return new ReceiptController();
	}

	@Override
	public CustomerGiftBLService getCustomerGiftController() {
		return new CustomerGiftController();
	}

	@Override
	public SpecialOfferBLService getSpecialOfferController() {
		return new SpecialOfferController();
	}

	@Override
	public TotalGiftBLService getTotalGiftController() {
		return new TotalGiftController();
	}

	@Override
	public SaleDetailsBLService getSaleDetailsBLController() {
		return new SaleDetailsController();
	}

	@Override
	public HistoryBLService getHistoryBLService() {
		return new BusinessHistoryController();
	}

	@Override
	public BusinessConditionBLService getBusinessConditionBLService() {
		return new BusinessConditionController();
	}

	@Override
	public ApprovalBLService getApprovalBLService() {
		return new ApprovalController();
	}

	@Override
	public SaleBLService getSaleReturnController(){
		return new SaleReturnController();
	}

	@Override
	public PurchaseBLService getPurchaseReturnController() {
		return new PurchaseReturnController();
	}
}
