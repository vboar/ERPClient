/**
 * @author chengcheng
 * @date 2014/11/14
 */

package businesslogic.approvalbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import util.DocumentStatus;
import util.DocumentType;
import util.ResultMessage;
import util.Time;
import vo.CashVO;
import vo.DocumentVO;
import vo.ExceptionVO;
import vo.MessageVO;
import vo.PaymentVO;
import vo.PresentVO;
import vo.PurchaseVO;
import vo.SaleVO;
import businesslogic.exceptionbl.Loss;
import businesslogic.exceptionbl.Overflow;
import businesslogic.logbl.Log;
import businesslogic.messagebl.Message;
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
	
	public ResultMessage sendMessage(MessageVO vo){
		Message m=new Message();
		m.add(vo);
		return ResultMessage.SUCCESS;
	}

	public ResultMessage addLog(String content){
		Log l=new Log();
		return l.add(content);
	}
	
	public ResultMessage approvePayment(PaymentVO vo){
		String content;
		if(vo.approvalState==DocumentStatus.PASSED)content="单据"+vo.id+"审批通过";
		else content="单据"+vo.id+"审批不通过";
		MessageVO message=new MessageVO(null,Time.getCurrentTime(),0,"财务人员",content);
		sendMessage(message);
		
		Payment p=new Payment();
		if(vo.approvalState==DocumentStatus.PASSED){
			p.update(vo);
		return p.approve(vo.transferList,vo.id,vo.customerId,vo.total);
		}else{
			return p.update(vo);
		}
	}
	
	public ResultMessage approveReceipt(PaymentVO vo){	
		String content;
		if(vo.approvalState==DocumentStatus.PASSED)content="单据"+vo.id+"审批通过";
		else content="单据"+vo.id+"审批不通过";
		MessageVO message=new MessageVO(null,Time.getCurrentTime(),0,"财务人员",content);
		sendMessage(message);
		
		Receipt r=new Receipt();
		if(vo.approvalState==DocumentStatus.PASSED){
			r.update(vo);
		return r.approve(vo.transferList, vo.id, vo.customerId, vo.total);
		}else{
			return r.update(vo);
		}
	}
	
	public ResultMessage approveSale(SaleVO vo){
		String content;
		if(vo.approvalState==DocumentStatus.PASSED)content="单据"+vo.id+"审批通过";
		else content="单据"+vo.id+"审批不通过";
		MessageVO message=new MessageVO(null,Time.getCurrentTime(),0,"进货销售人员",content);
		sendMessage(message);
			
		Sale s=new Sale();
		if(vo.approvalState==DocumentStatus.PASSED){
			s.update(vo);
		return s.approve(vo);
		}else{
			return s.update(vo);
		}
	}
	
	public ResultMessage approveSaleReturn(SaleVO vo){
		if(vo.isWriteOff==false){
			String content;
			if(vo.approvalState==DocumentStatus.PASSED)content="单据"+vo.id+"审批通过";
			else content="单据"+vo.id+"审批不通过";
			MessageVO message=new MessageVO(null,Time.getCurrentTime(),0,"进货销售人员",content);
			sendMessage(message);
		}
		
		SaleReturn sr=new SaleReturn();
		if(vo.approvalState==DocumentStatus.PASSED){
			sr.update(vo);
		return sr.approve(vo);
		}else{
			return sr.update(vo);
		}
	}
	
	public ResultMessage approvePurchase(PurchaseVO vo){
		String content;
		if(vo.documentStatus==DocumentStatus.PASSED)content="单据"+vo.id+"审批通过";
		else content="单据"+vo.id+"审批不通过";
		MessageVO message=new MessageVO(null,Time.getCurrentTime(),0,"进货销售人员",content);
		sendMessage(message);
		
		Purchase p=new Purchase();
		if(vo.documentStatus==DocumentStatus.PASSED){
			p.update(vo);
		return p.approve(vo);
		}else{
			return p.update(vo);
		}
	}
	
	public ResultMessage approvePurchaseReturn(PurchaseVO vo){
		String content;
		if(vo.documentStatus==DocumentStatus.PASSED)content="单据"+vo.id+"审批通过";
		else content="单据"+vo.id+"审批不通过";
		MessageVO message=new MessageVO(null,Time.getCurrentTime(),0,"进货销售人员",content);
		sendMessage(message);
		
		
		PurchaseReturn pr=new PurchaseReturn();
		if(vo.documentStatus==DocumentStatus.PASSED){
			pr.update(vo);
		return pr.approve(vo);
		}else{
			return pr.update(vo);
		}
	}
	
	public ResultMessage approveOverflow(ExceptionVO vo){
		String content;
		if(vo.status==DocumentStatus.PASSED)content="单据"+vo.id+"审批通过";
		else content="单据"+vo.id+"审批不通过";
		MessageVO message=new MessageVO(null,Time.getCurrentTime(),0,"库存管理人员",content);
		sendMessage(message);
		
		Overflow of=new Overflow();
		if(vo.status==DocumentStatus.PASSED){
			of.update(vo);
		return of.approve(vo);
		}else{
			return of.update(vo);
		}
	}
	
	public ResultMessage approveLoss(ExceptionVO vo){
		String content;
		if(vo.status==DocumentStatus.PASSED)content="单据"+vo.id+"审批通过";
		else content="单据"+vo.id+"审批不通过";
		MessageVO message=new MessageVO(null,Time.getCurrentTime(),0,"库存管理人员",content);
		sendMessage(message);
		
		Loss l=new Loss();
		if(vo.status==DocumentStatus.PASSED){
			l.update(vo);
		return l.approve(vo);
		}else{
			return l.update(vo);
		}
	}
	
	public ResultMessage approveCash(CashVO vo){
		String content;
		if(vo.approvalState==DocumentStatus.PASSED)content="单据"+vo.id+"审批通过";
		else content="单据"+vo.id+"审批不通过";
		MessageVO message=new MessageVO(null,Time.getCurrentTime(),0,"财务人员",content);
		sendMessage(message);
		
		
		Cash c=new Cash();
		if(vo.approvalState==DocumentStatus.PASSED){
			c.update(vo);
		return c.approve(vo);
		}else{
			return c.update(vo);
		}
	}
	
	public ResultMessage approvePresent(PresentVO vo){
		String content;
		if(vo.documentStatus==DocumentStatus.PASSED)content="单据"+vo.id+"审批通过";
		else content="单据"+vo.id+"审批不通过";
		MessageVO message=new MessageVO(null,Time.getCurrentTime(),0,"库存管理人员",content);
		sendMessage(message);
		
		
		Present p=new Present();
		if(vo.documentStatus==DocumentStatus.PASSED){
			p.update(vo);
		return p.approve(vo);
		}else{
			return p.update(vo);
		}
	}
	
	public ResultMessage approveDocument(DocumentVO vo){
		DocumentType type=vo.getType();
		switch(type){
		case PRESENT:
			Present pr=new Present();
			PresentVO present=pr.getById(vo.getId());
			present.setStatus(vo.getStatus());
			approvePresent(present);
			break;
		case PURCHASE:
			Purchase pu=new Purchase();
			PurchaseVO purchase=pu.getById(vo.getId());
			purchase.setStatus(vo.getStatus());
			approvePurchase(purchase);
			break;			
		case PURCHASERETURN:
			PurchaseReturn pre=new PurchaseReturn();
			PurchaseVO purchasere=pre.getById(vo.getId());
			purchasere.setStatus(vo.getStatus());
			approvePurchaseReturn(purchasere);
			break;
		case SALE:
			Sale s=new Sale();
			SaleVO sale=s.getById(vo.getId());
			sale.setStatus(vo.getStatus());
			approveSale(sale);
			break;
		case SALERETURN:
			Sale s1=new Sale();
			SaleVO sre=s1.getById(vo.getId());
			sre.setStatus(vo.getStatus());
			approveSaleReturn(sre);
			break;
		case PAYMENT:
			Payment p=new Payment();
			PaymentVO payment=p.getById(vo.getId());
			payment.setStatus(vo.getStatus());
			approvePayment(payment);
			break;
		case RECEIPT:
			Receipt r=new Receipt();
			PaymentVO receipt=r.getById(vo.getId());
			receipt.setStatus(vo.getStatus());
			approveReceipt(receipt);
			break;
		case OVERFLOW:
			Overflow of=new Overflow();
			ExceptionVO overflow=of.getById(vo.getId());
			overflow.setStatus(vo.getStatus());
			approveOverflow(overflow);
			break;
		case LOSS:
			Overflow of1=new Overflow();
			ExceptionVO loss=of1.getById(vo.getId());
			loss.setStatus(vo.getStatus());
			approveLoss(loss);
			break;
		case CASH:
			Cash c=new Cash();
			CashVO cash=c.getById(vo.getId());
			cash.setStatus(vo.getStatus());
			approveCash(cash);
			break;
		case PRESENTRETURN:
		case WARNING:
		}
		return ResultMessage.SUCCESS;
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
			for(int i=0;i<result.size();i++){
				if(result.get(i).documentStatus!=status){
					result.remove(i);
					i--;
					}
			}
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
			for(int i=0;i<result.size();i++){
				if(result.get(i).documentStatus!=status){
					result.remove(i);
					i--;
					}
			}
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
			for(int i=0;i<result.size();i++){
				if(result.get(i).documentStatus!=status){
					result.remove(i);
					i--;
					}
			}
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
			for(int i=0;i<result.size();i++){
				if(result.get(i).approvalState!=status){
					result.remove(i);
					i--;
					}
			}
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
			for(int i=0;i<result.size();i++){
				if(result.get(i).approvalState!=status){
					result.remove(i);
					i--;
					}
			}
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
			for(int i=0;i<result.size();i++){
				if(result.get(i).approvalState!=status){
					result.remove(i);
					i--;
					}
			}
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
			for(int i=0;i<result.size();i++){
				if(result.get(i).approvalState!=status){
					result.remove(i);
					i--;
					}
			}
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
			for(int i=0;i<result.size();i++){
				if(result.get(i).approvalState!=status){
					result.remove(i);
					i--;
					}
			}
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
			for(int i=0;i<result.size();i++){
				if(result.get(i).status!=status){
					result.remove(i);
					i--;
					}
			}
			break;
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
			for(int i=0;i<result.size();i++){
				if(result.get(i).status!=status){
					result.remove(i);
					i--;
					}
			}
			break;
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
