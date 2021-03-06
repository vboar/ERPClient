package businesslogicservice.controllerfactoryblservice;

import businesslogicservice.accountblservice.AccountBLService;
import businesslogicservice.approvalblservice.ApprovalBLService;
import businesslogicservice.businessconditionblservice.BusinessConditionBLService;
import businesslogicservice.businessconditionblservice.HistoryBLService;
import businesslogicservice.businessconditionblservice.SaleDetailsBLService;
import businesslogicservice.commodityblservice.CategoryBLService;
import businesslogicservice.commodityblservice.CommodityBLService;
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
import businesslogicservice.writeoffblservice.WriteoffBLService;

public interface ControllerFactory {

	public AccountBLService getAccountController() ;
	
	public CategoryBLService getCategoryController() ;
	
	public CommodityBLService getCommodityController() ;
	
	public CustomerBLService getCustomerController() ;
	
	public ExceptionBLService getLossController() ;
	
	public ExceptionBLService getOverflowController() ;
	
	public WarningBLService getWarningController() ;
	
	public InitialBLService getInitialController() ;
	
	public LogBLService getLogController() ;
	
	public MessageBLService getMessageController() ;
	
	public CashBLService getCashController() ;
	
	public PaymentBLService getPaymentController() ;
	
	public PaymentBLService getReceiptController();
	
	public PresentBLService getPresentController() ;
	
	public CustomerGiftBLService getCustomerGiftController() ;
	
	public SpecialOfferBLService getSpecialOfferController();
	
	public TotalGiftBLService getTotalGiftController();
	
	public PurchaseBLService getPurchaseController();

	public PurchaseBLService getPurchaseReturnController();
	
	public SaleBLService getSaleController();
	
	public StockBLService getStockController();
	
	public UserBLService getUserController();
	
	public LoginBLService getLoginController();
	
	public SaleDetailsBLService getSaleDetailsController();
	
	public HistoryBLService getHistoryController();
	
	public BusinessConditionBLService getBusinessConditionController();

	public ApprovalBLService getApprovalController();

	public SaleBLService getSaleReturnController();
	
	public WriteoffBLService getWriteoffController();
	
}
