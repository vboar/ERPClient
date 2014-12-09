/**
 * 查看经营历程
 * @author JaneLDQ
 * @date 2014/11/14
 */
package businesslogic.businessconditionbl;

import java.util.ArrayList;

import util.ResultMessage;
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
		ArrayList<SaleVO> temp=new ArrayList<SaleVO>();
		
		if(vo.time1!=null&&vo.time2!=null){
			temp=s.findByTime(vo.time1,vo.time2);
			for(int i=0;i<temp.size();i++)
				result.add(temp.get(i));
		}
		
		if(vo.customer!=null){
			temp=s.findByCustomer(vo.customer);
			for(int i=0;i<temp.size();i++)
				result.add(temp.get(i));
		}
		
		if(vo.operator!=null){
			temp=s.show();
			for(int i=0;i<temp.size();i++){
				if((temp.get(i).operatorId).equals(vo.operator))
					result.add(temp.get(i));
			}
		}
		
		if(vo.storage!=null){
			temp=s.findByStorage(vo.storage);
			for(int i=0;i<temp.size();i++)
				result.add(temp.get(i));
		}
		return result;
	}
	
	public ArrayList<SaleVO> showSaleReturn(RequirementVO vo){
		SaleReturn sr=new SaleReturn();
		ArrayList<SaleVO> result=new ArrayList<SaleVO>();
		ArrayList<SaleVO> temp=new ArrayList<SaleVO>();
		
		if(vo.time1!=null&&vo.time2!=null){
			temp=sr.findByTime(vo.time1,vo.time2);
			for(int i=0;i<temp.size();i++)
				result.add(temp.get(i));
		}
		
		if(vo.customer!=null){
			temp=sr.findByCustomer(vo.customer);
			for(int i=0;i<temp.size();i++)
				result.add(temp.get(i));
		}
		
		if(vo.operator!=null){
			temp=sr.show();
			for(int i=0;i<temp.size();i++){
				if((temp.get(i).operatorId).equals(vo.operator))
					result.add(temp.get(i));
			}
		}
		
		if(vo.storage!=null){
			temp=sr.findByStorage(vo.storage);
			for(int i=0;i<temp.size();i++)
				result.add(temp.get(i));
		}
		return result;
	}
	
	public ArrayList<PurchaseVO> showPurchase(RequirementVO vo){
		Purchase p=new Purchase();
		ArrayList<PurchaseVO> result=new ArrayList<PurchaseVO>();
		ArrayList<PurchaseVO> temp=new ArrayList<PurchaseVO>();
		
		if(vo.time1!=null&&vo.time2!=null){
			temp=p.findByTime(vo.time1,vo.time2);
			for(int i=0;i<temp.size();i++)
				result.add(temp.get(i));
		}
		
		if(vo.customer!=null){
			temp=p.findByCustomer(vo.customer);
			for(int i=0;i<temp.size();i++)
				result.add(temp.get(i));
		}
		
		if(vo.operator!=null){
			temp=p.show();
			for(int i=0;i<temp.size();i++){
				if((temp.get(i).operatorId).equals(vo.operator))
					result.add(temp.get(i));
			}
		}
		
		if(vo.storage!=null){
			temp=p.findByStorage(vo.storage);
			for(int i=0;i<temp.size();i++)
				result.add(temp.get(i));
		}
		return result;
	}
	
	public ArrayList<PurchaseVO> showPurchaseReturn(RequirementVO vo){
		PurchaseReturn pr=new PurchaseReturn();
		ArrayList<PurchaseVO> result=new ArrayList<PurchaseVO>();
		ArrayList<PurchaseVO> temp=new ArrayList<PurchaseVO>();
		
		if(vo.time1!=null&&vo.time2!=null){
			temp=pr.findByTime(vo.time1,vo.time2);
			for(int i=0;i<temp.size();i++)
				result.add(temp.get(i));
		}
		
		if(vo.customer!=null){
			temp=pr.findByCustomer(vo.customer);
			for(int i=0;i<temp.size();i++)
				result.add(temp.get(i));
		}
		
		if(vo.operator!=null){
			temp=pr.show();
			for(int i=0;i<temp.size();i++){
				if((temp.get(i).operatorId).equals(vo.operator))
					result.add(temp.get(i));
			}
		}
		
		if(vo.storage!=null){
			temp=pr.findByStorage(vo.storage);
			for(int i=0;i<temp.size();i++)
				result.add(temp.get(i));
		}
		return result;
	}
	
	public ArrayList<PaymentVO> showPayment(RequirementVO vo){
		Payment p=new Payment();
		ArrayList<PaymentVO> result=new ArrayList<PaymentVO>();
		ArrayList<PaymentVO> temp=new ArrayList<PaymentVO>();
		
		if(vo.time1!=null&&vo.time2!=null){
			temp=p.findByTime(vo.time1,vo.time2);
			for(int i=0;i<temp.size();i++)
				result.add(temp.get(i));
		}
		
		if(vo.customer!=null){
			temp=p.findByCustomer(vo.customer);
			for(int i=0;i<temp.size();i++)
				result.add(temp.get(i));	
		}
		
		if(vo.operator!=null){
			temp=p.show();
			for(int i=0;i<temp.size();i++){
				if((temp.get(i).operatorId).equals(vo.operator))
					result.add(temp.get(i));	
			}
		}		
		return result;
	}
	
	public ArrayList<PaymentVO> showReceipt(RequirementVO vo){
		Receipt r=new Receipt();
		ArrayList<PaymentVO> result=new ArrayList<PaymentVO>();
		ArrayList<PaymentVO> temp=new ArrayList<PaymentVO>();
		
		if(vo.time1!=null&&vo.time2!=null){
			temp=r.findByTime(vo.time1,vo.time2);
			for(int i=0;i<temp.size();i++)
				result.add(temp.get(i));
		}
		
		if(vo.customer!=null){
			temp=r.findByCustomer(vo.customer);
			for(int i=0;i<temp.size();i++)
				result.add(temp.get(i));	
		}
		
		if(vo.operator!=null){
			temp=r.show();
			for(int i=0;i<temp.size();i++){
				if((temp.get(i).operatorId).equals(vo.operator))
					result.add(temp.get(i));	
			}
		}
		return result;
	}
	
	public ArrayList<CashVO> showCash(RequirementVO vo){
		Cash c=new Cash();
		ArrayList<CashVO> result=new ArrayList<CashVO>();
		ArrayList<CashVO> temp=new ArrayList<CashVO>();
		
		if(vo.time1!=null&&vo.time2!=null){
			temp=c.findByTime(vo.time1,vo.time2);
			for(int i=0;i<temp.size();i++)
				result.add(temp.get(i));
		}

		return result;
	}
	
	public ArrayList<ExceptionVO> showOverFlow(RequirementVO vo){
		Overflow of=new Overflow();
		ArrayList<ExceptionVO> result=new ArrayList<ExceptionVO>();
		ArrayList<ExceptionVO> temp=new ArrayList<ExceptionVO>();
		
		if(vo.time1!=null&&vo.time2!=null){
			temp=of.findByTime(vo.time1,vo.time2);
			for(int i=0;i<temp.size();i++)
				result.add(temp.get(i));
		}
		return result;
	}
	
	public ArrayList<ExceptionVO> showLoss(RequirementVO vo){
		Loss l=new Loss();
		ArrayList<ExceptionVO> result=new ArrayList<ExceptionVO>();
		ArrayList<ExceptionVO> temp=new ArrayList<ExceptionVO>();
		
		if(vo.time1!=null&&vo.time2!=null){
			temp=l.show(vo.time1,vo.time2);
			for(int i=0;i<temp.size();i++)
				result.add(temp.get(i));
		}
		return result;
	}
	
	public ArrayList<WarningVO> showWarning(RequirementVO vo){
		Warning w=new Warning(); 
		ArrayList<WarningVO> result=new ArrayList<WarningVO>();
		ArrayList<WarningVO> temp=new ArrayList<WarningVO>();
		
		if(vo.time1!=null&&vo.time2!=null){
			temp=w.findByTime(vo.time1,vo.time2);
			for(int i=0;i<temp.size();i++)
				result.add(temp.get(i));
		}
		return result;
	}
}
