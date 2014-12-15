/**
 * @author chengcheng
 * @date 2014/11/14
 */

package businesslogic.approvalbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import util.DocumentStatus;
import util.ResultMessage;
import util.Time;
import vo.CashVO;
import vo.DocumentVO;
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
	
	public ResultMessage approvePayment(PaymentVO vo){
		Payment p=new Payment();
		if(vo.approvalState==DocumentStatus.PASSED){
		return p.approve(vo.transferList,vo.id,vo.customerId,vo.total);
		}else{
			return p.update(vo);
		}
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
	
	public ArrayList<PresentVO> findPresent(int way,DocumentStatus status,String time1,String time2){
		ArrayList<PresentVO> result=new ArrayList<PresentVO>();
		time1=Time.jdugeTime1(time1);
		time2=Time.jdugeTime2(time2);
		Present p=new Present();
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
	
	public ArrayList<PurchaseVO> findPurchase(int way,DocumentStatus status,String time1,String time2){
		ArrayList<PurchaseVO> result=new ArrayList<PurchaseVO>();
		time1=Time.jdugeTime1(time1);
		time2=Time.jdugeTime2(time2);
		Purchase p=new Purchase();
		switch(way){
		case 0:
			result=p.show();
			break;
		case 1:
			result=p.findByStatus(status.ordinal());
			break;
		case 2:
			result=p.findByTime(time1, time2);
			break;
		}
		
		return result;
	}
	
	public ArrayList<PurchaseVO> findPurchaseReturn(int way,DocumentStatus status,String time1,String time2){
		ArrayList<PurchaseVO> result=new ArrayList<PurchaseVO>();
		time1=Time.jdugeTime1(time1);
		time2=Time.jdugeTime2(time2);
		PurchaseReturn pr=new PurchaseReturn();
		switch(way){
		case 0:
			result=pr.show();
			break;
		case 1:
			result=pr.findByStatus(status.ordinal());
			break;
		case 2:
			result=pr.findByTime(time1, time2);
			break;
			}
		return result;
	}
	
	public ArrayList<SaleVO> findSale(int way,DocumentStatus status,String time1,String time2){
		ArrayList<SaleVO> result=new ArrayList<SaleVO>();
		time1=Time.jdugeTime1(time1);
		time2=Time.jdugeTime2(time2);
		Sale s=new Sale();
		switch(way){
		case 0:
			result=s.show();
			break;
		case 1:
			result=s.findByStatus(status.ordinal());
			break;
		case 2:
			result=s.findByTime(time1, time2);
			break;
		}
		
		return result;
	}
	
	public ArrayList<SaleVO> findSaleReturn(int way,DocumentStatus status,String time1,String time2){
		ArrayList<SaleVO> result=new ArrayList<SaleVO>();
		time1=Time.jdugeTime1(time1);
		time2=Time.jdugeTime2(time2);
		SaleReturn sr=new SaleReturn();
		switch(way){
		case 0:
			result=sr.show();
			break;
		case 1:
			result=sr.findByStatus(status.ordinal());
			break;
		case 2:
			result=sr.findByTime(time1, time2);
			break;
		}
		
		return result;
	}
	
	public ArrayList<PaymentVO> findPayment(int way,DocumentStatus status,String time1,String time2){
		ArrayList<PaymentVO> result=new ArrayList<PaymentVO>();
		time1=Time.jdugeTime1(time1);
		time2=Time.jdugeTime2(time2);
		Payment p=new Payment();
		switch(way){
		case 0:
			result=p.show();
			break;
		case 1:
			result=p.findByStatus(status.ordinal());
			break;
		case 2:
			result=p.findByTime(time1, time2);
			break;
		}
		
		return result;
	}
	
	public ArrayList<PaymentVO> findReceipt(int way,DocumentStatus status,String time1,String time2){
		ArrayList<PaymentVO> result=new ArrayList<PaymentVO>();
		time1=Time.jdugeTime1(time1);
		time2=Time.jdugeTime2(time2);
		Receipt r=new Receipt();
		switch(way){
		case 0:
			result=r.show();
			break;
		case 1:
			result=r.findByStatus(status.ordinal());
			break;
		case 2:
			result=r.findByTime(time1, time2);
			break;
		}
		
		return result;
	}
	
	public ArrayList<CashVO> findCash(int way,DocumentStatus status,String time1,String time2){
		ArrayList<CashVO> result=new ArrayList<CashVO>();
		time1=Time.jdugeTime1(time1);
		time2=Time.jdugeTime2(time2);
		Cash c=new Cash();
		switch(way){
		case 0:
			result=c.show();
			break;
		case 1:
			result=c.findByStatus(status.ordinal());
			break;
		case 2:
			result=c.findByTime(time1, time2);
			break;
		}

		return result;
	}
	
	public ArrayList<ExceptionVO> findLoss(int way,DocumentStatus status,String time1,String time2){
		ArrayList<ExceptionVO> result=new ArrayList<ExceptionVO>();
		time1=Time.jdugeTime1(time1);
		time2=Time.jdugeTime2(time2);
		Loss l=new Loss();
		switch(way){
		case 0:
			break;
		case 1:
			result=l.show(time1, time2);
			break;
		case 2:
			result=l.findByStatus(status.ordinal());
		}
		return result;
	}
	
	public ArrayList<ExceptionVO> findOverflow(int way,DocumentStatus status,String time1,String time2){
		ArrayList<ExceptionVO> result=new ArrayList<ExceptionVO>();
		time1=Time.jdugeTime1(time1);
		time2=Time.jdugeTime2(time2);
		Overflow o=new Overflow();
		switch(way){
		case 0:
			break;
		case 1:
			result=o.findByTime(time1, time2);
			break;
		case 2:
			result=o.findByStatus(status);
		}
		return result;
	}
	
	public DocumentVO getById(String id){
		
		if(id.contains("ZPD")){
			Present p=new Present();
			try {
			DocumentVO result=p.poToVO(DataFactoryImpl.getInstance().getPresentData().getById(id));
			return result;
			} catch (RemoteException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			
		}
	
		if(id.contains("JHD")||id.contains("JHTHD")){
			Purchase p=new Purchase();
			try {
				DocumentVO result=p.poToVO(DataFactoryImpl.getInstance().getPurchaseData().getById(id));
				return result;
			} catch (RemoteException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		
		if(id.contains("XSD")||id.contains("XSTHD")){
			Sale s=new Sale();
			try {
				DocumentVO result=s.SalePOToSaleVO(DataFactoryImpl.getInstance().getSaleDataService().getById(id));
				return result;
			} catch (RemoteException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		
		if(id.contains("FKD")||id.contains("SKD")){
			Payment p=new Payment();
			try {
				DocumentVO result=p.poToVo(DataFactoryImpl.getInstance().getPaymentData().getById(id));
				return result;
			} catch (RemoteException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		
		if(id.contains("XJFYD")){
			Cash c=new Cash();
			try {
				DocumentVO result=c.poToVo(DataFactoryImpl.getInstance().getCashDataService().getById(id));
				return result;
			} catch (RemoteException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		
		if(id.contains("BYD")||id.contains("BSD")){
			Loss l=new Loss();
			try {
				DocumentVO result=l.poToVo(DataFactoryImpl.getInstance().getExceptionData().getById(id));
				return result;
			} catch (RemoteException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}

		return null;
	}
	
	public ArrayList<DocumentVO> show(DocumentStatus status,
			  String time1, String time2){
		ArrayList<DocumentVO> result=new ArrayList<DocumentVO>();
		time1=Time.jdugeTime1(time1);
		time2=Time.jdugeTime2(time2);
		
		ArrayList<PresentVO> temp1=findPresent(1,status,time1,time2);
		for(int i=0;i<temp1.size();i++)
			result.add(temp1.get(i));
		
		ArrayList<PurchaseVO> temp2=findPurchase(1,status,time1,time2);
		for(int i=0;i<temp2.size();i++)
			result.add(temp2.get(i));
		
		ArrayList<PurchaseVO> temp3=findPurchaseReturn(1,status,time1,time2);
		for(int i=0;i<temp3.size();i++)
			result.add(temp3.get(i));
		
		ArrayList<SaleVO> temp4=findSale(1,status,time1,time2);
		for(int i=0;i<temp4.size();i++)
			result.add(temp4.get(i));
		
		ArrayList<SaleVO> temp5=findSaleReturn(1,status,time1,time2);
		for(int i=0;i<temp5.size();i++)
			result.add(temp5.get(i));
		
		ArrayList<PaymentVO> temp6=findPayment(1,status,time1,time2);
		for(int i=0;i<temp6.size();i++)
			result.add(temp6.get(i));
		
		ArrayList<PaymentVO> temp7=findReceipt(1,status,time1,time2);
		for(int i=0;i<temp7.size();i++)
			result.add(temp7.get(i));
		
		ArrayList<CashVO> temp8=findCash(1,status,time1,time2);
		for(int i=0;i<temp8.size();i++)
			result.add(temp8.get(i));
		
		ArrayList<ExceptionVO> temp9=findLoss(1,status,time1,time2);
		for(int i=0;i<temp9.size();i++)
			result.add(temp9.get(i));
		
		ArrayList<ExceptionVO> temp10=findOverflow(1,status,time1,time2);
		for(int i=0;i<temp10.size();i++)
			result.add(temp10.get(i));
		
		if(status!=null){
			for(int i=0;i<result.size();i++){
				DocumentVO t=result.get(i);
				if(t.getStatus()!=status){
				result.remove(i);
				i--;
				}
			}
		}
		return result;
	}
}
