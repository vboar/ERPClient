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
import dataservice.promotiondataservice.CustomerGiftDataService;
import dataservice.promotiondataservice.SpecialOfferDataService;
import dataservice.promotiondataservice.TotalGiftDataService;
import dataservice.purchasedataservice.PurchaseDataService;
import dataservice.saledataservice.SaleDataService;
import dataservice.stockdataservice.StockDataService;
import dataservice.systemdateservice.SystemDataService;
import dataservice.userdataservice.UserDataService;

/**
 * Lazy initialization holder class 单例模式
 *
 */
public class DataFactoryImpl implements DataFactory {
	
	private String url = "rmi://127.0.0.1:8888/";
	
	private DataFactoryImpl() {}
	
	public static DataFactoryImpl getInstance() {
		return DataFactoryImplHolder.dataFactory;
	}
	
	private static class DataFactoryImplHolder {
		private static DataFactoryImpl dataFactory = new DataFactoryImpl();
	}
	
	@Override
	public AccountDataService getAccountData() throws RemoteException {
		try {
			return (AccountDataService) Naming.lookup(url + "AccountDataService");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public CategoryDataService getCategoryData() throws RemoteException {
		try {
			return (CategoryDataService) Naming.lookup(url + "CategoryDataService");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public CommodityDataService getCommodityData() throws RemoteException {
		try {
			return (CommodityDataService) Naming.lookup(url + "CommodityDataService");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public CustomerDataService getCustomerData() throws RemoteException {
		try {
			return (CustomerDataService) Naming.lookup(url + "CustomerDataService");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ExceptionDataService getExceptionData() throws RemoteException {
		try {
			return (ExceptionDataService) Naming.lookup(url + "ExceptionDataService");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public WarningDataService getWarningData() throws RemoteException {
		try {
			return (WarningDataService) Naming.lookup(url + "WarningDataService");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public InitialDataService getInitialData() throws RemoteException {
		try {
			return (InitialDataService) Naming.lookup(url + "InitialDataService");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public LogDataService getLogData() throws RemoteException {
		try {
			return (LogDataService) Naming.lookup(url + "LogDataService");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public MessageDataService getMessageData() throws RemoteException {
		try {
			return (MessageDataService) Naming.lookup(url + "MessageDataService");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public CashDataService getCashDataService() throws RemoteException {
		try {
			return (CashDataService) Naming.lookup(url + "CashDataService");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public PaymentDataService getPaymentData() throws RemoteException {
		try {
			return (PaymentDataService) Naming.lookup(url + "PaymentDataService");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public PresentDataService getPresentData() throws RemoteException {
		try {
			return (PresentDataService) Naming.lookup(url + "PresentDataService");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public CustomerGiftDataService getCustomerGiftData() throws RemoteException {
		try {
			return (CustomerGiftDataService) Naming.lookup(url + "CustomerGiftDataService");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public TotalGiftDataService getTotalGiftData() throws RemoteException {
		try {
			return (TotalGiftDataService) Naming.lookup(url + "TotalGiftDataService");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public SpecialOfferDataService getSpecialOfferData() throws RemoteException {
		try {
			return (SpecialOfferDataService) Naming.lookup(url + "SpecialOfferDataService");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public PurchaseDataService getPurchaseData() throws RemoteException {
		try {
			return (PurchaseDataService) Naming.lookup(url + "PurchaseDataService");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public SaleDataService getSaleDataService() throws RemoteException {
		try {
			return (SaleDataService) Naming.lookup(url + "SaleDataService");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public StockDataService getStockData() throws RemoteException {
		try {
			return (StockDataService) Naming.lookup(url + "StockDataService");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public UserDataService getUserData() throws RemoteException {
		try {
			return (UserDataService) Naming.lookup(url + "UserDataService");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public SystemDataService getSystemData() throws RemoteException {
		try {
			return (SystemDataService) Naming.lookup(url + "SystemDataService");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		return null;
	}

}
