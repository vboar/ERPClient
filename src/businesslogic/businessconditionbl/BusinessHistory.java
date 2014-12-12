/**
 * 查看经营历程
 * @author JaneLDQ
 * @date 2014/11/14
 */
package businesslogic.businessconditionbl;

import java.util.ArrayList;

import util.ResultMessage;
import util.Time;
import vo.CashVO;
import vo.ExceptionVO;
import vo.PaymentVO;
import vo.PurchaseVO;
import vo.RequirementVO;
import vo.SaleVO;
import vo.WarningVO;
import businesslogic.exceptionbl.Loss;
import businesslogic.exceptionbl.Overflow;
import businesslogic.exceptionbl.Warning;
import businesslogic.logbl.Log;
import businesslogic.paymentbl.Cash;
import businesslogic.paymentbl.Payment;
import businesslogic.paymentbl.Receipt;
import businesslogic.purchasebl.Purchase;
import businesslogic.purchasebl.PurchaseReturn;
import businesslogic.salebl.Sale;
import businesslogic.salebl.SaleReturn;

public class BusinessHistory {
	public ResultMessage addLog(String content){
		Log l=new Log();
		return l.add(content);
	}
	
	public ArrayList<SaleVO> showSale(RequirementVO vo){
		Sale s=new Sale();
		ArrayList<SaleVO> result=new ArrayList<SaleVO>();
		
		result=s.show();		
		for(int i=0;i<result.size();i++)
			if(judgeTime(vo.time1,vo.time2,result.get(i).time)==false)
				result.remove(i);
		
		if(vo.customer!=null){
			for(int i=0;i<result.size();i++)
				if(result.get(i).customerName!=vo.customer)
					result.remove(i);
		}
		
		if(vo.operator!=null){
			for(int i=0;i<result.size();i++)
				if(result.get(i).operatorId!=vo.operator)
					result.remove(i);
		}
		
		if(vo.storage!=null){
			for(int i=0;i<result.size();i++)
				if(result.get(i).storage!=vo.storage)
					result.remove(i);
		}
		
		return result;
	}
	
	public ArrayList<SaleVO> showSaleReturn(RequirementVO vo){
		SaleReturn sr=new SaleReturn();
		ArrayList<SaleVO> result=new ArrayList<SaleVO>();
		
		result=sr.show();		
		for(int i=0;i<result.size();i++)
			if(judgeTime(vo.time1,vo.time2,result.get(i).time)==false)
				result.remove(i);
		
		if(vo.customer!=null){
			for(int i=0;i<result.size();i++)
				if(result.get(i).customerName!=vo.customer)
					result.remove(i);
		}
		
		if(vo.operator!=null){
			for(int i=0;i<result.size();i++)
				if(result.get(i).operatorId!=vo.operator)
					result.remove(i);
		}
		
		if(vo.storage!=null){
			for(int i=0;i<result.size();i++)
				if(result.get(i).storage!=vo.storage)
					result.remove(i);
		}
		
		return result;
	}
	
	public ArrayList<PurchaseVO> showPurchase(RequirementVO vo){
		Purchase p=new Purchase();
		ArrayList<PurchaseVO> result=new ArrayList<PurchaseVO>();

		result=p.show();
		for(int i=0;i<result.size();i++)
			if(judgeTime(vo.time1,vo.time2,result.get(i).time)==false)
				result.remove(i);
		
		if(vo.customer!=null){
			for(int i=0;i<result.size();i++)
				if(result.get(i).customerName!=vo.customer)
					result.remove(i);
		}
		
		if(vo.operator!=null){
			for(int i=0;i<result.size();i++)
				if(result.get(i).operatorId!=vo.operator)
					result.remove(i);
		}
		
		if(vo.storage!=null){
			for(int i=0;i<result.size();i++)
				if(result.get(i).storage!=vo.storage)
					result.remove(i);
		}
		
		return result;
	}
	
	public ArrayList<PurchaseVO> showPurchaseReturn(RequirementVO vo){
		PurchaseReturn pr=new PurchaseReturn();
		ArrayList<PurchaseVO> result=new ArrayList<PurchaseVO>();

		result=pr.show();
		for(int i=0;i<result.size();i++)
			if(judgeTime(vo.time1,vo.time2,result.get(i).time)==false)
				result.remove(i);
		
		if(vo.customer!=null){
			for(int i=0;i<result.size();i++)
				if(result.get(i).customerName!=vo.customer)
					result.remove(i);
		}
		
		if(vo.operator!=null){
			for(int i=0;i<result.size();i++)
				if(result.get(i).operatorId!=vo.operator)
					result.remove(i);
		}
		
		if(vo.storage!=null){
			for(int i=0;i<result.size();i++)
				if(result.get(i).storage!=vo.storage)
					result.remove(i);
		}
		
		return result;
	}
	
	public ArrayList<PaymentVO> showPayment(RequirementVO vo){
		Payment p=new Payment();
		ArrayList<PaymentVO> result=new ArrayList<PaymentVO>();
		
		result=p.show();
		for(int i=0;i<result.size();i++)
			if(judgeTime(vo.time1,vo.time2,result.get(i).time)==false)
				result.remove(i);
		
		if(vo.customer!=null){
			for(int i=0;i<result.size();i++)
				if(result.get(i).customerName!=vo.customer)
					result.remove(i);
		}
		
		if(vo.operator!=null){
			for(int i=0;i<result.size();i++)
				if(result.get(i).operatorId!=vo.operator)
					result.remove(i);
		}	
		return result;
	}
	
	public ArrayList<PaymentVO> showReceipt(RequirementVO vo){
		Receipt r=new Receipt();
		ArrayList<PaymentVO> result=new ArrayList<PaymentVO>();
	
		result=r.show();
		for(int i=0;i<result.size();i++)
			if(judgeTime(vo.time1,vo.time2,result.get(i).time)==false)
				result.remove(i);
		
		if(vo.customer!=null){
			for(int i=0;i<result.size();i++)
				if(result.get(i).customerName!=vo.customer)
					result.remove(i);
		}
		
		if(vo.operator!=null){
			for(int i=0;i<result.size();i++)
				if(result.get(i).operatorId!=vo.operator)
					result.remove(i);
		}
		return result;
	}
	
	public ArrayList<CashVO> showCash(RequirementVO vo){
		Cash c=new Cash();
		ArrayList<CashVO> result=new ArrayList<CashVO>();
		
		result=c.show();
		for(int i=0;i<result.size();i++)
			if(judgeTime(vo.time1,vo.time2,result.get(i).time)==false)
				result.remove(i);

		return result;
	}
	
	public ArrayList<ExceptionVO> showOverFlow(RequirementVO vo){
		Overflow of=new Overflow();
		ArrayList<ExceptionVO> result=new ArrayList<ExceptionVO>();
		
		result=of.findByTime("1970/1/1/0/0/0",Time.getCurrentTime());
		for(int i=0;i<result.size();i++)
			if(judgeTime(vo.time1,vo.time2,result.get(i).time)==false)
				result.remove(i);
		
		return result;
	}
	
	public ArrayList<ExceptionVO> showLoss(RequirementVO vo){
		Loss l=new Loss();
		ArrayList<ExceptionVO> result=new ArrayList<ExceptionVO>();
		
		result=l.show("1970/1/1/0/0/0",Time.getCurrentTime());
		for(int i=0;i<result.size();i++)
			if(judgeTime(vo.time1,vo.time2,result.get(i).time)==false)
				result.remove(i);
		
		return result;
	}
	
	public ArrayList<WarningVO> showWarning(RequirementVO vo){
		Warning w=new Warning(); 
		ArrayList<WarningVO> result=new ArrayList<WarningVO>();
		
		result=w.findByTime("1970/1/1/0/0/0",Time.getCurrentTime());
		for(int i=0;i<result.size();i++)
			if(judgeTime(vo.time1,vo.time2,result.get(i).time)==false)
				result.remove(i);
		
		return result;
	}
	
	private boolean judgeTime(String time1, String time2, String time) {
		boolean result=false;
		if(time1==null&&time2==null){
			result=true;
			return result;
		}
		
		if(time1==null&&time2!=null){
			if(time.compareTo(time2)<=0)
				result=true;
			return result;
		}
		
		if(time1!=null&&time2==null){
			if(time.compareTo(time1)>=0&&time.compareTo(Time.getCurrentTime())<=0)
				result=true;
			return result;
		}
		
		if(time1!=null&&time2!=null){
			if(time.compareTo(time1)>=0&&time.compareTo(time2)<=0)
				result=true;
		}
		
		return result;
	}
}
