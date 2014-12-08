/**
 * @author chengcheng
 * @date 2014/11/14
 */

package businesslogic.approvalbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import util.DocumentStatus;
import util.ResultMessage;
import vo.CashVO;
import vo.ExceptionVO;
import vo.PaymentVO;
import vo.PresentVO;
import vo.PurchaseVO;
import vo.SaleVO;
import vo.TransferLineItemVO;
import businesslogic.exceptionbl.Loss;
import businesslogic.exceptionbl.Overflow;
import businesslogic.logbl.Log;
import businesslogic.paymentbl.Cash;
import businesslogic.paymentbl.Payment;
import businesslogic.paymentbl.Receipt;
import businesslogic.presentbl.Present;
import businesslogic.purchasebl.Purchase;
import businesslogic.purchasebl.PurchaseReturn;
import businesslogic.salebl.Sale;
import businesslogic.salebl.SaleReturn;
import dataservice.datafactoryservice.DataFactoryImpl;
//审批通过后：1、发消息到收件箱  2、修改相应数据
public class Approval {
	
	public ResultMessage sendMessage(String content){
		//TODO
		return ResultMessage.SUCCESS;
	}

	public ResultMessage addLog(String content){
		Log l=new Log();
		return l.add(content);
	}
	public ResultMessage approvePayment(ArrayList<TransferLineItemVO> transferlist,String id,String customerId,double total){
		Payment p=new Payment();
		return p.approve(transferlist,id,customerId,total);
	}
	
	public ResultMessage approveReceipt(ArrayList<TransferLineItemVO> transferlist,String id,String customerId,double total){
		Receipt r=new Receipt();
		return r.approve(transferlist, id, customerId, total);
	}
	
	public ResultMessage approveSale(SaleVO vo){
		Sale s=new Sale();
		return s.approve(vo);
	}
	
	public ResultMessage approveSaleReturn(SaleVO vo){
		SaleReturn sr=new SaleReturn();
		return sr.approve(vo);
	}
	
	public ResultMessage approvePurchase(PurchaseVO vo){
		Purchase p=new Purchase();
		return p.approve(vo);
	}
	
	public ResultMessage approvePurchaseReturn(PurchaseVO vo){
		PurchaseReturn pr=new PurchaseReturn();
		return pr.approve(vo);
	}
	
	public ResultMessage approveOverflow(ExceptionVO vo){
		Overflow of=new Overflow();
		return of.approve(vo);
	}
	
	public ResultMessage approveLoss(ExceptionVO vo){
		Loss l=new Loss();
		return l.approve(vo);
	}
	
	public ResultMessage approveCash(CashVO vo){
		Cash c=new Cash();
		return c.approve(vo);
	}
	
	public ResultMessage approvePresent(PresentVO vo){
		Present p=new Present();
		return p.approve(vo);
	}
	
	public ArrayList<PresentVO> findPresent(int way,int status,String time1,String time2){
		ArrayList<PresentVO> result=new ArrayList<PresentVO>();
		Present p=new Present();
		switch(way){
		case 0:
			result=p.show();
			break;
		case 1:
			result=p.findByStatus(DocumentStatus.values()[status]);
			break;
		case 2:
			result=p.findByTime(time1, time2);
			break;
		}
		
		return result;
	}
	
	public ArrayList<PurchaseVO> findPurchase(int way,int status,String time1,String time2){
		ArrayList<PurchaseVO> result=new ArrayList<PurchaseVO>();
		Purchase p=new Purchase();
		switch(way){
		case 0:
			result=p.show();
			break;
		case 1:
			result=p.findByStatus(status);
			break;
		case 2:
			result=p.findByTime(time1, time2);
			break;
		}
		
		return result;
	}
	
	public ArrayList<PurchaseVO> findPurchaseReturn(int way,int status,String time1,String time2){
		ArrayList<PurchaseVO> result=new ArrayList<PurchaseVO>();
		PurchaseReturn pr=new PurchaseReturn();
		switch(way){
		case 0:
			result=pr.show();
			break;
		case 1:
			result=pr.findByStatus(status);
			break;
		case 2:
			result=pr.findByTime(time1, time2);
			break;
			}
		return result;
	}
	
	public ArrayList<SaleVO> findSale(int way,int status,String time1,String time2){
		ArrayList<SaleVO> result=new ArrayList<SaleVO>();
		Sale s=new Sale();
		switch(way){
		case 0:
			result=s.show();
			break;
		case 1:
			result=s.findByStatus(status);
			break;
		case 2:
			result=s.findByTime(time1, time2);
			break;
		}
		
		return result;
	}
	
	public ArrayList<SaleVO> findSaleReturn(int way,int status,String time1,String time2){
		ArrayList<SaleVO> result=new ArrayList<SaleVO>();
		SaleReturn sr=new SaleReturn();
		switch(way){
		case 0:
			result=sr.show();
			break;
		case 1:
			result=sr.findByStatus(status);
			break;
		case 2:
			result=sr.findByTime(time1, time2);
			break;
		}
		
		return result;
	}
	
	public ArrayList<PaymentVO> findPayment(int way,int status,String time1,String time2){
		ArrayList<PaymentVO> result=new ArrayList<PaymentVO>();
		Payment p=new Payment();
		switch(way){
		case 0:
			result=p.show();
			break;
		case 1:
			result=p.findByStatus(status);
			break;
		case 2:
			result=p.findByTime(time1, time2);
			break;
		}
		
		return result;
	}
	
	public ArrayList<PaymentVO> findReceipt(int way,int status,String time1,String time2){
		ArrayList<PaymentVO> result=new ArrayList<PaymentVO>();
		Receipt r=new Receipt();
		switch(way){
		case 0:
			result=r.show();
			break;
		case 1:
			result=r.findByStatus(status);
			break;
		case 2:
			result=r.findByTime(time1, time2);
			break;
		}
		
		return result;
	}
	
	public ArrayList<CashVO> fingCash(int way,int status,String time1,String time2){
		ArrayList<CashVO> result=new ArrayList<CashVO>();
		Cash c=new Cash();
		switch(way){
		case 0:
			result=c.show();
			break;
		case 1:
			result=c.findByStatus(status);
			break;
		case 2:
			result=c.findByTime(time1, time2);
			break;
		}

		return result;
	}
	
	public ArrayList<ExceptionVO> findLoss(int way,int status,String time1,String time2){
		ArrayList<ExceptionVO> result=new ArrayList<ExceptionVO>();
		Loss l=new Loss();
		switch(way){
		case 0:
			break;
		case 1:
			result=l.show(time1, time2);
			break;
		case 2:
			result=l.findByStatus(status);
		}
		return result;
	}
	
	public Object getById(String id){
		Object result=new Object();
		
		if(id.contains("ZPD")){
			Present p=new Present();
			try {
				result=p.poToVO(DataFactoryImpl.getInstance().getPresentData().getById(id));
			} catch (RemoteException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			return result;
		}
	
		if(id.contains("JHD")||id.contains("JHTHD")){
			Purchase p=new Purchase();
			try {
				result=p.poToVO(DataFactoryImpl.getInstance().getPurchaseData().getById(id));
			} catch (RemoteException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			return result;
		}
		
		if(id.contains("XSD")||id.contains("XSTHD")){
			Sale s=new Sale();
			try {
				result=s.SalePOToSaleVO(DataFactoryImpl.getInstance().getSaleDataService().getById(id));
			} catch (RemoteException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			return result;
		}
		
		if(id.contains("FKD")||id.contains("SKD")){
			Payment p=new Payment();
			try {
				result=p.poToVo(DataFactoryImpl.getInstance().getPaymentData().getById(id));
			} catch (RemoteException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			return result;
		}
		
		if(id.contains("XJFYD")){
			Cash c=new Cash();
			try {
				result=c.poToVo(DataFactoryImpl.getInstance().getCashDataService().getById(id));
			} catch (RemoteException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			return result;
		}
		
		if(id.contains("BYD")||id.contains("BSD")){
			Loss l=new Loss();
			try {
				result=l.poToVo(DataFactoryImpl.getInstance().getExceptionData().getById(id));
			} catch (RemoteException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
	
	return result;	
	}
}
