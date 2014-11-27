/**
 * 抽象工厂实现
 * @author Vboar
 * @date 2014/10/26
 */

package dataservice.datafactoryservice;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import dataservice.accountdataservice.AccountDataService;
import dataservice.accountdataservice.AccountDataServiceImpl;
import dataservice.commoditydataservice.CategoryDataService;
import dataservice.commoditydataservice.CategoryDataServiceImpl;
import dataservice.commoditydataservice.CommodityDataService;
import dataservice.commoditydataservice.CommodityDataServiceImpl;
import dataservice.customerdataservice.CustomerDataService;
import dataservice.customerdataservice.CustomerDataServiceImpl;
import dataservice.exceptiondataservice.ExceptionDataService;
import dataservice.exceptiondataservice.ExceptionDataServiceImpl;
import dataservice.exceptiondataservice.WarningDataService;
import dataservice.exceptiondataservice.WarningDataServiceImpl;
import dataservice.initialdataservice.InitialDataService;
import dataservice.initialdataservice.InitialDataServiceImpl;
import dataservice.logdataservice.LogDataService;
import dataservice.logdataservice.LogDataServiceImpl;
import dataservice.messagedataservice.MessageDataService;
import dataservice.messagedataservice.MessageDataServiceImpl;
import dataservice.paymentdataservice.CashDataService;
import dataservice.paymentdataservice.CashDataServiceImpl;
import dataservice.paymentdataservice.PaymentDataService;
import dataservice.paymentdataservice.PaymentDataServiceImpl;
import dataservice.presentdataservice.PresentDataService;
import dataservice.presentdataservice.PresentDataServiceImpl;
import dataservice.promotiondataservice.CustomerGiftDataService;
import dataservice.promotiondataservice.CustomerGiftDataServiceImpl;
import dataservice.promotiondataservice.SpecialOfferDataService;
import dataservice.promotiondataservice.SpecialOfferDataServiceImpl;
import dataservice.promotiondataservice.TotalGiftDataService;
import dataservice.promotiondataservice.TotalGiftDataServiceImpl;
import dataservice.purchasedataservice.PurchaseDataService;
import dataservice.purchasedataservice.PurchaseDataServiceImpl;
import dataservice.saledataservice.SaleDataService;
import dataservice.saledataservice.SaleDataServiceImpl;
import dataservice.stockdataservice.StockDataService;
import dataservice.stockdataservice.StockDataServiceImpl;
import dataservice.systemdateservice.SystemDataService;
import dataservice.systemdateservice.SystemDataServiceImpl;
import dataservice.userdataservice.UserDataService;
import dataservice.userdataservice.UserDataServiceImpl;

/**
 * 单例模式
 */
public class DataFactoryImpl extends UnicastRemoteObject implements DataFactory {
	
	private static final long serialVersionUID = 1L;

	private static DataFactory dataFactory;
	
	private static String url = "rmi://127.0.0.1:8888/";
	
	public DataFactoryImpl() throws RemoteException {
		super();
	}
	
	public static DataFactory getInstance() {
		if(dataFactory == null) {
			try {
				dataFactory = (DataFactory) Naming.lookup(url + "DataFactory");
			} catch (MalformedURLException | RemoteException
					| NotBoundException e) {
				e.printStackTrace();
			}
		}
		return dataFactory;
	}
	
	@Override
	public AccountDataService getAccountData() throws RemoteException {
		return new AccountDataServiceImpl();
	}

	@Override
	public CategoryDataService getCategoryData() throws RemoteException {
		return new CategoryDataServiceImpl();
	}

	@Override
	public CommodityDataService getCommodityData() throws RemoteException {
		return new CommodityDataServiceImpl();
	}

	@Override
	public CustomerDataService getCustomerData() throws RemoteException {
		return new CustomerDataServiceImpl();
	}

	@Override
	public ExceptionDataService getExceptionData() throws RemoteException {
		return new ExceptionDataServiceImpl();
	}

	@Override
	public WarningDataService getWarningData() throws RemoteException {
		return new WarningDataServiceImpl();
	}

	@Override
	public InitialDataService getInitialData() throws RemoteException {
		return new InitialDataServiceImpl();
	}

	@Override
	public LogDataService getLogData() throws RemoteException {
		return new LogDataServiceImpl();
	}

	@Override
	public MessageDataService getMessageData() throws RemoteException {
		return new MessageDataServiceImpl();
	}

	@Override
	public CashDataService getCashDataService() throws RemoteException {
		return new CashDataServiceImpl();
	}

	@Override
	public PaymentDataService getPaymentData() throws RemoteException {
		return new PaymentDataServiceImpl();
	}

	@Override
	public PresentDataService getPresentData() throws RemoteException {
		return new PresentDataServiceImpl();
	}
	
	@Override
	public CustomerGiftDataService getCustomerGiftData() throws RemoteException {
		return new CustomerGiftDataServiceImpl();
	}

	@Override
	public TotalGiftDataService getTotalGiftData() throws RemoteException {
		return new TotalGiftDataServiceImpl();
	}

	@Override
	public SpecialOfferDataService getSpecialOfferData() throws RemoteException {
		return new SpecialOfferDataServiceImpl();
	}

	@Override
	public PurchaseDataService getPurchaseData() throws RemoteException {
		return new PurchaseDataServiceImpl();
	}

	@Override
	public SaleDataService getSaleDataService() throws RemoteException {
		return new SaleDataServiceImpl();
	}

	@Override
	public StockDataService getStockData() throws RemoteException {
		return new StockDataServiceImpl();
	}

	@Override
	public UserDataService getUserData() throws RemoteException {
		return new UserDataServiceImpl();
	}

	@Override
	public SystemDataService getSystemData() throws RemoteException {
		return new SystemDataServiceImpl();
	}
	
}
