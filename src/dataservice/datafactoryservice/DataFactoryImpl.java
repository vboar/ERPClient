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
import dataservice.systemdateservice.SystemDataService;
import dataservice.userdataservice.UserDataService;

public class DataFactoryImpl implements DataFactory {

	private static DataFactoryImpl dataFactory = null;
	
	private DataFactoryImpl() {}
	
	public static DataFactoryImpl getInstance() {
		if(dataFactory == null) {
			dataFactory = new DataFactoryImpl();
		}
		return dataFactory;
	}
	
	@Override
	public AccountDataService getAccountData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CategoryDataService getCategoryData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommodityDataService getCommodityData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CustomerDataService getCustomerData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ExceptionDataService getExceptionData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WarningDataService getWarningData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InitialDataService getInitialData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LogDataService getLogData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MessageDataService getMessageData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CashDataService getCashDataService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PaymentDataService getPaymentData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PresentDataService getPresentData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PromotionDataService getPromotionData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PurchaseDataService getPurchaseData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SaleDataService getSaleDataService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StockDataService getStockData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDataService getUserData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SystemDataService getSystemData() {
		// TODO Auto-generated method stub
		return null;
	}

}
