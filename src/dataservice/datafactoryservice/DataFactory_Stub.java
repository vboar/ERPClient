/**
 * 抽象工厂桩程序
 * @author Vboar
 * @date 2014/10/26
 */
package dataservice.datafactoryservice;

import dataservice.accountdataservice.AccountDataService;
import dataservice.accountdataservice.AccountDataServiceTxtFileImpl_Stub;
import dataservice.commoditydataservice.CategoryDataService;
import dataservice.commoditydataservice.CategoryDataServiceTxtFileImpl_Stub;
import dataservice.commoditydataservice.CommodityDataService;
import dataservice.commoditydataservice.CommodityDataServiceTxtFileImpl_Stub;
import dataservice.customerdataservice.CustomerDataService;
import dataservice.customerdataservice.CustomerDataServiceTxtFileImpl_Stub;
import dataservice.exceptiondataservice.ExceptionDataService;
import dataservice.exceptiondataservice.ExceptionDataServiceTxtFileImpl_Stub;
import dataservice.exceptiondataservice.WarningDataService;
import dataservice.exceptiondataservice.WarningDataServiceTxtFileImpl_Stub;
import dataservice.initialdataservice.InitialDataService;
import dataservice.initialdataservice.InitialDataServiceTxtFileImpl_Stub;
import dataservice.logdataservice.LogDataService;
import dataservice.logdataservice.LogDataServiceTxtFileImpl_Stub;
import dataservice.messagedataservice.MessageDataService;
import dataservice.messagedataservice.MessageDataServiceTxtFileImpl_Stub;
import dataservice.paymentdataservice.CashDataService;
import dataservice.paymentdataservice.CashDataServiceTxtFileImpl_Stub;
import dataservice.paymentdataservice.PaymentDataService;
import dataservice.paymentdataservice.PaymentDataServiceTxtFileImpl_Stub;
import dataservice.presentdataservice.PresentDataService;
import dataservice.presentdataservice.PresentDataServiceTxtFileImpl_Stub;
import dataservice.promotiondataservice.PromotionDataService;
import dataservice.promotiondataservice.PromotionDataServiceTxtFileImpl_Stub;
import dataservice.purchasedataservice.PurchaseDataService;
import dataservice.purchasedataservice.PurchaseDataServiceTxtFileImpl_Stub;
import dataservice.saledataservice.SaleDataService;
import dataservice.saledataservice.SaleDataServiceTxtFileImpl_Stub;
import dataservice.stockdataservice.StockDataService;
import dataservice.stockdataservice.StockDataServiceTxtFileImpl_Stub;
import dataservice.userdataservice.UserDataService;
import dataservice.userdataservice.UserDataServiceTxtFileImpl_Stub;

public class DataFactory_Stub implements DataFactory {

	@Override
	public AccountDataService getAccountData() {
		return new AccountDataServiceTxtFileImpl_Stub();
	}

	@Override
	public CategoryDataService getCategoryData() {
		return new CategoryDataServiceTxtFileImpl_Stub();
	}

	@Override
	public CommodityDataService getCommodityData() {
		return new CommodityDataServiceTxtFileImpl_Stub();
	}

	@Override
	public CustomerDataService getCustomerData() {
		return new CustomerDataServiceTxtFileImpl_Stub();
	}

	@Override
	public ExceptionDataService getExceptionData() {
		return new ExceptionDataServiceTxtFileImpl_Stub();
	}

	@Override
	public WarningDataService getWarningData() {
		return new WarningDataServiceTxtFileImpl_Stub();
	}

	@Override
	public InitialDataService getInitialData() {
		return new InitialDataServiceTxtFileImpl_Stub();
	}

	@Override
	public LogDataService getLogData() {
		return new LogDataServiceTxtFileImpl_Stub();
	}

	@Override
	public MessageDataService getMessageData() {
		return new MessageDataServiceTxtFileImpl_Stub();
	}

	@Override
	public CashDataService getCashDataService() {
		return new CashDataServiceTxtFileImpl_Stub();
	}

	@Override
	public PaymentDataService getPaymentData() {
		return new PaymentDataServiceTxtFileImpl_Stub();
	}

	@Override
	public PresentDataService getPresentData() {
		return new PresentDataServiceTxtFileImpl_Stub();
	}

	@Override
	public PromotionDataService getPromotionData() {
		return new PromotionDataServiceTxtFileImpl_Stub();
	}
	
	@Override
	public PurchaseDataService getPurchaseData() {
		return new PurchaseDataServiceTxtFileImpl_Stub();
	}

	@Override
	public SaleDataService getSaleDataService() {
		return new SaleDataServiceTxtFileImpl_Stub();
	}

	@Override
	public StockDataService getStockData() {
		return new StockDataServiceTxtFileImpl_Stub();
	}

	@Override
	public UserDataService getUserData() {
		return new UserDataServiceTxtFileImpl_Stub();
	}

}
