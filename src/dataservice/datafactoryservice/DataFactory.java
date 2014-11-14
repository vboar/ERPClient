/**
 * 抽象工厂接口
 * @author Vboar
 * @date 2014/10/26
 */
package dataservice.datafactoryservice;

import dataservice.accountdataservice.AccountDataService;
import dataservice.commoditydataservice.CategoryDataService;
import dataservice.commoditydataservice.CommodityDataService;
import dataservice.customerdataservice.CustomerDataService;
import dataservice.exceptiondataservice.ExceptionDataService;
import dataservice.exceptiondataservice.WarningDataService;
import dataservice.initialdataservice.InitialDataService;
import dataservice.logdataservice.LogDataService;
import dataservice.messagedataservice.MessageDataService;
import dataservice.paymentdataservice.CashDataService;
import dataservice.paymentdataservice.PaymentDataService;
import dataservice.presentdataservice.PresentDataService;
import dataservice.promotiondataservice.PromotionDataService;
import dataservice.purchasedataservice.PurchaseDataService;
import dataservice.saledataservice.SaleDataService;
import dataservice.stockdataservice.StockDataService;
import dataservice.userdataservice.UserDataService;

public interface DataFactory {

	public AccountDataService getAccountData();
	
	public CategoryDataService getCategoryData();
	
	public CommodityDataService getCommodityData();
	
	public CustomerDataService getCustomerData();
	
	public ExceptionDataService getExceptionData();
	
	public WarningDataService getWarningData();
	
	public InitialDataService getInitialData();
	
	public LogDataService getLogData();
	
	public MessageDataService getMessageData();
	
	public CashDataService getCashDataService();
	
	public PaymentDataService getPaymentData();
	
	public PresentDataService getPresentData();
	
	public PromotionDataService getPromotionData();
	
	public PurchaseDataService getPurchaseData();
	
	public SaleDataService getSaleDataService();
	
	public StockDataService getStockData();
	
	public UserDataService getUserData();
}
