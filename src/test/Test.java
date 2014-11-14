package test;
import java.rmi.RemoteException;

import businesslogicservice.accountblservice.AccountBLService_Driver;
import businesslogicservice.accountblservice.AccountBLService_Stub;
import businesslogicservice.approvalblservice.ApprovalBLService_Driver;
import businesslogicservice.approvalblservice.ApprovalBLService_Stub;
import businesslogicservice.businessconditionblservice.BusinessConditionBLService_Driver;
import businesslogicservice.businessconditionblservice.BusinessConditionBLService_Stub;
import businesslogicservice.businessconditionblservice.BusinessHistoryBLService_Driver;
import businesslogicservice.businessconditionblservice.BusinessHistoryBLService_Stub;
import businesslogicservice.businessconditionblservice.SaleDetailsBLService_Driver;
import businesslogicservice.businessconditionblservice.SaleDetailsBLService_Stub;
import businesslogicservice.commodityblservice.CategoryBLService_Driver;
import businesslogicservice.commodityblservice.CategoryBLService_Stub;
import businesslogicservice.commodityblservice.CommodityBLService_Driver;
import businesslogicservice.commodityblservice.CommodityBLService_Stub;
import businesslogicservice.exceptionblservice.LossBLService_Driver;
import businesslogicservice.exceptionblservice.LossBLService_Stub;
import businesslogicservice.exceptionblservice.OverflowBLService_Driver;
import businesslogicservice.exceptionblservice.OverflowBLService_Stub;
import businesslogicservice.exceptionblservice.WarningBLService_Driver;
import businesslogicservice.exceptionblservice.WarningBLService_Stub;
import businesslogicservice.initialblservice.InitialBLService_Driver;
import businesslogicservice.initialblservice.InitialBLService_Stub;
import businesslogicservice.logblservice.LogBLService_Driver;
import businesslogicservice.logblservice.LogBLService_Stub;
import businesslogicservice.messageblservice.MessageBLService_Driver;
import businesslogicservice.messageblservice.MessageBLService_Stub;
import businesslogicservice.paymentblservice.CashBLService_Driver;
import businesslogicservice.paymentblservice.CashBLService_Stub;
import businesslogicservice.paymentblservice.PaymentBLService_Driver;
import businesslogicservice.paymentblservice.PaymentBLService_Stub;
import businesslogicservice.paymentblservice.ReceiptBLService_Driver;
import businesslogicservice.paymentblservice.ReceiptBLService_Stub;
import businesslogicservice.presentblservice.PresentBLService_Driver;
import businesslogicservice.presentblservice.PresentBLService_Stub;
import businesslogicservice.promotionblservice.PromotionBLService_Driver;
import businesslogicservice.promotionblservice.PromotionBLService_Stub;
import businesslogicservice.purchaseblservice.PurchaseBLService_Driver;
import businesslogicservice.purchaseblservice.PurchaseBLService_Stub;
import businesslogicservice.purchaseblservice.PurchaseReturnBLService_Driver;
import businesslogicservice.purchaseblservice.PurchaseReturnBLService_Stub;
import businesslogicservice.saleblservice.SaleBLService_Driver;
import businesslogicservice.saleblservice.SaleBLService_Stub;
import businesslogicservice.saleblservice.SaleReturnBLService_Driver;
import businesslogicservice.saleblservice.SaleReturnBLService_Stub;
import businesslogicservice.stockblservice.StockBLService_Driver;
import businesslogicservice.stockblservice.StockBLService_Stub;
import businesslogicservice.userblservice.UserBLService_Driver;
import businesslogicservice.userblservice.UserBLService_Stub;
import businesslogicservice.writeoffblservice.WriteoffBLService_Driver;
import businesslogicservice.writeoffblservice.WriteoffBLService_Stub;
import dataservice.accountdataservice.AccountDataServiceTxtFileImpl_Driver;
import dataservice.accountdataservice.AccountDataServiceTxtFileImpl_Stub;
import dataservice.commoditydataservice.CategoryDataServiceTxtFileImpl_Driver;
import dataservice.commoditydataservice.CategoryDataServiceTxtFileImpl_Stub;
import dataservice.commoditydataservice.CommodityDataServiceTxtFileImpl_Driver;
import dataservice.commoditydataservice.CommodityDataServiceTxtFileImpl_Stub;
import dataservice.customerdataservice.CustomerDataServiceTxtFileImpl_Driver;
import dataservice.customerdataservice.CustomerDataServiceTxtFileImpl_Stub;
import dataservice.exceptiondataservice.ExceptionDataServiceTxtFileImpl_Driver;
import dataservice.exceptiondataservice.ExceptionDataServiceTxtFileImpl_Stub;
import dataservice.exceptiondataservice.WarningDataServiceTxtFileImpl_Driver;
import dataservice.exceptiondataservice.WarningDataServiceTxtFileImpl_Stub;
import dataservice.initialdataservice.InitialDataServiceTxtFileImpl_Driver;
import dataservice.initialdataservice.InitialDataServiceTxtFileImpl_Stub;
import dataservice.logdataservice.LogDataServiceTxtFileImpl_Driver;
import dataservice.logdataservice.LogDataServiceTxtFileImpl_Stub;
import dataservice.messagedataservice.MessageDataServiceTxtFileImpl_Driver;
import dataservice.messagedataservice.MessageDataServiceTxtFileImpl_Stub;
import dataservice.paymentdataservice.CashDataServiceTxtFileImpl_Driver;
import dataservice.paymentdataservice.CashDataServiceTxtFileImpl_Stub;
import dataservice.paymentdataservice.PaymentDataServiceTxtFileImpl_Driver;
import dataservice.paymentdataservice.PaymentDataServiceTxtFileImpl_Stub;
import dataservice.presentdataservice.PresentDataServiceTxtFileImpl_Driver;
import dataservice.presentdataservice.PresentDataServiceTxtFileImpl_Stub;
import dataservice.promotiondataservice.PromotionDataServiceTxtFileImpl_Driver;
import dataservice.promotiondataservice.PromotionDataServiceTxtFileImpl_Stub;
import dataservice.purchasedataservice.PurchaseDataServiceTxtFileImpl_Driver;
import dataservice.purchasedataservice.PurchaseDataServiceTxtFileImpl_Stub;
import dataservice.saledataservice.SaleDataServiceTxtFileImpl_Driver;
import dataservice.saledataservice.SaleDataServiceTxtFileImpl_Stub;
import dataservice.stockdataservice.StockDataServiceTxtFileImpl_Driver;
import dataservice.stockdataservice.StockDataServiceTxtFileImpl_Stub;
import dataservice.userdataservice.UserDataServiceTxtFileImpl_Driver;
import dataservice.userdataservice.UserDataServiceTxtFileImpl_Stub;


public class Test {
	
	private Test() {}
	
	public static void main(String[] args) throws RemoteException {
		
		Test.testBLService();
		
		Test.testDataService();
		
	}
	
	public static void testBLService() {
		
		CategoryBLService_Driver d1 = new CategoryBLService_Driver();
		d1.drive(new CategoryBLService_Stub());
		System.out.println("-----------------------------------------------------\n");
		
		CommodityBLService_Driver d2 = new CommodityBLService_Driver();
		d2.drive(new CommodityBLService_Stub());
		System.out.println("-----------------------------------------------------\n");
		
		StockBLService_Driver d3 = new StockBLService_Driver();
		d3.drive(new StockBLService_Stub());
		System.out.println("-----------------------------------------------------\n");
		
		AccountBLService_Driver d4 = new AccountBLService_Driver();
		d4.drive(new AccountBLService_Stub());
		System.out.println("-----------------------------------------------------\n");
		
		CashBLService_Driver d5 = new CashBLService_Driver();
		d5.drive(new CashBLService_Stub());
		System.out.println("-----------------------------------------------------\n");
		
		PaymentBLService_Driver d6 = new PaymentBLService_Driver();
		d6.drive(new PaymentBLService_Stub());
		System.out.println("-----------------------------------------------------\n");
		
		ReceiptBLService_Driver d7 = new ReceiptBLService_Driver();
		d7.drive(new ReceiptBLService_Stub());
		System.out.println("-----------------------------------------------------\n");
		
		WriteoffBLService_Driver d8 = new WriteoffBLService_Driver();
		d8.drive(new WriteoffBLService_Stub());
		System.out.println("-----------------------------------------------------\n");
		
		System.out.println("9");
		UserBLService_Driver d9 = new UserBLService_Driver();
		d9.drive(new UserBLService_Stub());
		System.out.println("-----------------------------------------------------\n");
		
		MessageBLService_Driver d10 = new MessageBLService_Driver();
		d10.drive(new MessageBLService_Stub());
		System.out.println("-----------------------------------------------------\n");
		
		LogBLService_Driver d11 = new LogBLService_Driver();
		d11.drive(new LogBLService_Stub());
		System.out.println("-----------------------------------------------------\n");
		
		PresentBLService_Driver d12 = new PresentBLService_Driver();
		d12.drive(new PresentBLService_Stub());
		System.out.println("-----------------------------------------------------\n");
		
		OverflowBLService_Driver d13 = new OverflowBLService_Driver();
		d13.drive(new OverflowBLService_Stub());
		System.out.println("-----------------------------------------------------\n");
		
		LossBLService_Driver d14 = new LossBLService_Driver();
		d14.drive(new LossBLService_Stub());
		System.out.println("-----------------------------------------------------\n");
		
		WarningBLService_Driver d15 = new WarningBLService_Driver();
		d15.drive(new WarningBLService_Stub());
		System.out.println("-----------------------------------------------------\n");
		
		BusinessConditionBLService_Driver d16 = new BusinessConditionBLService_Driver();
		d16.drive(new BusinessConditionBLService_Stub());
		System.out.println("-----------------------------------------------------\n");
		
		BusinessHistoryBLService_Driver d17 = new BusinessHistoryBLService_Driver();
		d17.drive(new BusinessHistoryBLService_Stub());
		System.out.println("-----------------------------------------------------\n");
		
		SaleDetailsBLService_Driver d18 = new SaleDetailsBLService_Driver();
		d18.drive(new SaleDetailsBLService_Stub());
		System.out.println("-----------------------------------------------------\n");
		
		SaleBLService_Driver d19 = new SaleBLService_Driver();
		d19.drive(new SaleBLService_Stub());
		System.out.println("-----------------------------------------------------\n");
		
		SaleReturnBLService_Driver d20 = new SaleReturnBLService_Driver();
		d20.drive(new SaleReturnBLService_Stub());
		System.out.println("-----------------------------------------------------\n");
		
		PurchaseBLService_Driver d21 = new PurchaseBLService_Driver();
		d21.drive(new PurchaseBLService_Stub());
		System.out.println("-----------------------------------------------------\n");
		
		PurchaseReturnBLService_Driver d22 = new PurchaseReturnBLService_Driver();
		d22.drive(new PurchaseReturnBLService_Stub());
		System.out.println("-----------------------------------------------------\n");
		
		InitialBLService_Driver d23 = new InitialBLService_Driver();
		d23.drive(new InitialBLService_Stub());
		System.out.println("-----------------------------------------------------\n");
		
		ApprovalBLService_Driver d24 = new ApprovalBLService_Driver();
		d24.drive(new ApprovalBLService_Stub());
		System.out.println("-----------------------------------------------------\n");
		
		PromotionBLService_Driver d25 = new PromotionBLService_Driver();
		d25.drive(new PromotionBLService_Stub());
		System.out.println("-----------------------------------------------------\n");
		
	}
	
	public static void testDataService() throws RemoteException {
		
		CategoryDataServiceTxtFileImpl_Driver d1 = new CategoryDataServiceTxtFileImpl_Driver();
		d1.drive(new CategoryDataServiceTxtFileImpl_Stub());
		System.out.println("-----------------------------------------------------\n");
		
		AccountDataServiceTxtFileImpl_Driver d2 = new AccountDataServiceTxtFileImpl_Driver();
		d2.drive(new AccountDataServiceTxtFileImpl_Stub());
		System.out.println("-----------------------------------------------------\n");
		
		CashDataServiceTxtFileImpl_Driver d3 = new CashDataServiceTxtFileImpl_Driver();
		d3.drive(new CashDataServiceTxtFileImpl_Stub());
		System.out.println("-----------------------------------------------------\n");
		
		PaymentDataServiceTxtFileImpl_Driver d4 = new PaymentDataServiceTxtFileImpl_Driver();
		d4.drive(new PaymentDataServiceTxtFileImpl_Stub());
		System.out.println("-----------------------------------------------------\n");
		
		PresentDataServiceTxtFileImpl_Driver d5 = new PresentDataServiceTxtFileImpl_Driver();
		d5.drive(new PresentDataServiceTxtFileImpl_Stub());
		System.out.println("-----------------------------------------------------\n");
		
		CommodityDataServiceTxtFileImpl_Driver d6 = new CommodityDataServiceTxtFileImpl_Driver();
		d6.drive(new CommodityDataServiceTxtFileImpl_Stub());
		System.out.println("-----------------------------------------------------\n");
		
		ExceptionDataServiceTxtFileImpl_Driver d7 = new ExceptionDataServiceTxtFileImpl_Driver();
		d7.drive(new ExceptionDataServiceTxtFileImpl_Stub());
		System.out.println("-----------------------------------------------------\n");
		
		WarningDataServiceTxtFileImpl_Driver d8 = new WarningDataServiceTxtFileImpl_Driver();
		d8.drive(new WarningDataServiceTxtFileImpl_Stub());
		System.out.println("-----------------------------------------------------\n");
		
		StockDataServiceTxtFileImpl_Driver d9 = new StockDataServiceTxtFileImpl_Driver();
		d9.drive(new StockDataServiceTxtFileImpl_Stub());
		System.out.println("-----------------------------------------------------\n");
		
		InitialDataServiceTxtFileImpl_Driver d10 = new InitialDataServiceTxtFileImpl_Driver();
		d10.drive(new InitialDataServiceTxtFileImpl_Stub());
		System.out.println("-----------------------------------------------------\n");
		
		SaleDataServiceTxtFileImpl_Driver d11 = new SaleDataServiceTxtFileImpl_Driver();
		d11.drive(new SaleDataServiceTxtFileImpl_Stub());
		System.out.println("-----------------------------------------------------\n");
		
		PurchaseDataServiceTxtFileImpl_Driver d12 = new PurchaseDataServiceTxtFileImpl_Driver();
		d12.drive(new PurchaseDataServiceTxtFileImpl_Stub());
		System.out.println("-----------------------------------------------------\n");
		
		LogDataServiceTxtFileImpl_Driver d13 = new LogDataServiceTxtFileImpl_Driver();
		d13.drive(new LogDataServiceTxtFileImpl_Stub());
		System.out.println("-----------------------------------------------------\n");
		
		UserDataServiceTxtFileImpl_Driver d14 = new UserDataServiceTxtFileImpl_Driver();
		d14.drive(new UserDataServiceTxtFileImpl_Stub());
		System.out.println("-----------------------------------------------------\n");
		
		PromotionDataServiceTxtFileImpl_Driver d15 = new PromotionDataServiceTxtFileImpl_Driver();
		d15.drive(new PromotionDataServiceTxtFileImpl_Stub());
		System.out.println("-----------------------------------------------------\n");
		
		MessageDataServiceTxtFileImpl_Driver d16 = new MessageDataServiceTxtFileImpl_Driver();
		d16.drive(new MessageDataServiceTxtFileImpl_Stub());
		System.out.println("-----------------------------------------------------\n");
		
		CustomerDataServiceTxtFileImpl_Driver d17 = new CustomerDataServiceTxtFileImpl_Driver();
		d17.drive(new CustomerDataServiceTxtFileImpl_Stub());
		System.out.println("-----------------------------------------------------\n");
		
	}
}
