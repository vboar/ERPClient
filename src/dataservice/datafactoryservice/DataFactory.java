/**
 * 抽象工厂接口
 * @author Vboar
 * @date 2014/10/26
 */
package dataservice.datafactoryservice;

import java.rmi.Remote;
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

public interface DataFactory extends Remote {

	public AccountDataService getAccountData() throws RemoteException;
	
	public CategoryDataService getCategoryData() throws RemoteException;
	
	public CommodityDataService getCommodityData() throws RemoteException;
	
	public CustomerDataService getCustomerData() throws RemoteException;
	
	public ExceptionDataService getExceptionData() throws RemoteException;
	
	public WarningDataService getWarningData() throws RemoteException;
	
	public InitialDataService getInitialData() throws RemoteException;
	
	public LogDataService getLogData() throws RemoteException;
	
	public MessageDataService getMessageData() throws RemoteException;
	
	public CashDataService getCashData() throws RemoteException;
	
	public PaymentDataService getPaymentData() throws RemoteException;
	
	public PresentDataService getPresentData() throws RemoteException;
	
	public CustomerGiftDataService getCustomerGiftData() throws RemoteException;
	
	public TotalGiftDataService getTotalGiftData() throws RemoteException;
	
	public SpecialOfferDataService getSpecialOfferData() throws RemoteException;
	
	public PurchaseDataService getPurchaseData() throws RemoteException;
	
	public SaleDataService getSaleData() throws RemoteException;
	
	public StockDataService getStockData() throws RemoteException;
	
	public UserDataService getUserData() throws RemoteException;
	
	public SystemDataService getSystemData() throws RemoteException;
}
