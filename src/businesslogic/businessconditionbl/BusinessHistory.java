/**
 * 查看经营历程
 * @author JaneLDQ
 * @date 2014/11/14
 */
package businesslogic.businessconditionbl;

import java.io.FileOutputStream;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import util.DocumentType;
import util.ResultMessage;
import util.Time;
import vo.CashVO;
import vo.ClauseLineItemVO;
import vo.CommodityLineItemVO;
import vo.ExceptionLineItemVO;
import vo.ExceptionVO;
import vo.PaymentVO;
import vo.PurchaseVO;
import vo.RequirementVO;
import vo.SaleVO;
import vo.TransferLineItemVO;
import vo.WarningLineItemVO;
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
	
	public ResultMessage exportSale(String path,RequirementVO vo){
		HSSFWorkbook wb=new HSSFWorkbook();
		HSSFSheet sheet=wb.createSheet("销售历史表");
		HSSFRow row=sheet.createRow(0);
		HSSFCellStyle style=wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		
		HSSFCell cell=row.createCell(0);
		cell.setCellValue("单据编号");
		cell.setCellStyle(style);
		
		cell=row.createCell(1);
		cell.setCellValue("客户");
		cell.setCellStyle(style);
		
		cell=row.createCell(2);
		cell.setCellValue("业务员");
		cell.setCellStyle(style);
		
		cell=row.createCell(3);
		cell.setCellValue("操作员");
		cell.setCellStyle(style);
		
		cell=row.createCell(4);
		cell.setCellValue("仓库");
		cell.setCellStyle(style);
		
		cell=row.createCell(5);
		cell.setCellValue("出货商品清单");
		cell.setCellStyle(style);
		
		cell=row.createCell(13);
		cell.setCellValue("折让前总额");
		cell.setCellStyle(style);
		
		cell=row.createCell(14);
		cell.setCellValue("折让");
		cell.setCellStyle(style);
		
		cell=row.createCell(15);
		cell.setCellValue("使用代金券金额");
		cell.setCellStyle(style);
		
		cell=row.createCell(16);
		cell.setCellValue("折让后总额");
		cell.setCellStyle(style);
		
		cell=row.createCell(17);
		cell.setCellValue("备注");
		cell.setCellStyle(style);
		
		row=sheet.createRow(1);
		cell=row.createCell(6);
		cell.setCellValue("编号");
		cell.setCellStyle(style);
		
		cell=row.createCell(7);
		cell.setCellValue("名称");
		cell.setCellStyle(style);
		
		cell=row.createCell(8);
		cell.setCellValue("型号");
		cell.setCellStyle(style);
		
		cell=row.createCell(9);
		cell.setCellValue("数量");
		cell.setCellStyle(style);
		
		cell=row.createCell(10);
		cell.setCellValue("单价");
		cell.setCellStyle(style);
		
		cell=row.createCell(11);
		cell.setCellValue("金额");
		cell.setCellStyle(style);
		
		cell=row.createCell(12);
		cell.setCellValue("商品备注");
		cell.setCellStyle(style);
		
		ArrayList<SaleVO> list=new ArrayList<SaleVO>();
		if(vo.type==DocumentType.SALE){
			list=showSale(vo);
		}else{
			list=showSaleReturn(vo);
		}
		for(int i=0;i<list.size();i++){
			SaleVO temp=list.get(i);
			row=sheet.createRow(i+1);
			
			row.createCell(0).setCellValue(temp.id);
			row.createCell(1).setCellValue(temp.customerId+" "+temp.customerName);
			row.createCell(2).setCellValue(temp.salesmanId);
			row.createCell(3).setCellValue(temp.operatorId);
			row.createCell(4).setCellValue(temp.storage);
			row.createCell(13).setCellValue(temp.totalBeforeDiscount);
			row.createCell(14).setCellValue(temp.discount);
			row.createCell(15).setCellValue(temp.voucher);
			row.createCell(16).setCellValue(temp.totalAfterDiscount);
			row.createCell(17).setCellValue(temp.remark);
			
			ArrayList<CommodityLineItemVO> c=new ArrayList<CommodityLineItemVO>();
			for(int j=0;j<c.size();j++){
				CommodityLineItemVO t=c.get(j);
				row=sheet.createRow(j+2);
				
				row.createCell(6).setCellValue(t.id);
				row.createCell(7).setCellValue(t.name);
				row.createCell(8).setCellValue(t.model);
				row.createCell(9).setCellValue(t.number);
				row.createCell(10).setCellValue(t.price);
				row.createCell(11).setCellValue(t.total);
				row.createCell(12).setCellValue(t.remark);
			}
		}
	
		try{
			FileOutputStream fout=new FileOutputStream(path);
			wb.write(fout);
			fout.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return ResultMessage.SUCCESS;
	}
	
	public ResultMessage exportPurchase(String path,RequirementVO vo){
		HSSFWorkbook wb=new HSSFWorkbook();
		HSSFSheet sheet=wb.createSheet("进货历史表");
		HSSFRow row=sheet.createRow(0);
		HSSFCellStyle style=wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		
		HSSFCell cell=row.createCell(0);
		cell.setCellValue("单据编号");
		cell.setCellStyle(style);
		
		cell=row.createCell(1);
		cell.setCellValue("供应商");
		cell.setCellStyle(style);
		
		cell=row.createCell(2);
		cell.setCellValue("仓库");
		cell.setCellStyle(style);
		
		cell=row.createCell(3);
		cell.setCellValue("操作员");
		cell.setCellStyle(style);
		
		cell=row.createCell(4);
		cell.setCellValue("入库商品列表");
		cell.setCellStyle(style);
		
		cell=row.createCell(13);
		cell.setCellValue("备注");
		cell.setCellStyle(style);
		
		cell=row.createCell(14);
		cell.setCellValue("总额合计");
		cell.setCellStyle(style);
		
		row=sheet.createRow(1);
		cell=row.createCell(6);
		cell.setCellValue("商品编号");
		cell.setCellStyle(style);
		
		cell=row.createCell(7);
		cell.setCellValue("名称");
		cell.setCellStyle(style);
		
		cell=row.createCell(8);
		cell.setCellValue("型号");
		cell.setCellStyle(style);
		
		cell=row.createCell(9);
		cell.setCellValue("数量");
		cell.setCellStyle(style);
		
		cell=row.createCell(10);
		cell.setCellValue("单价");
		cell.setCellStyle(style);
		
		cell=row.createCell(11);
		cell.setCellValue("金额");
		cell.setCellStyle(style);
		
		cell=row.createCell(12);
		cell.setCellValue("商品备注");
		cell.setCellStyle(style);
		
		ArrayList<PurchaseVO> list=new ArrayList<PurchaseVO>();
		if(vo.type==DocumentType.PURCHASE){
			list=showPurchase(vo);
			}else{
				list=showPurchaseReturn(vo);
			}
		for(int i=0;i<list.size();i++){
			PurchaseVO temp=list.get(i);
			row=sheet.createRow(i+1);
			
			row.createCell(0).setCellValue(temp.id);
			row.createCell(1).setCellValue(temp.customerId+" "+temp.customerName);
			row.createCell(2).setCellValue(temp.storage);
			row.createCell(3).setCellValue(temp.operatorId);
			row.createCell(13).setCellValue(temp.remark);
			row.createCell(14).setCellValue(temp.total);

			
			ArrayList<CommodityLineItemVO> c=new ArrayList<CommodityLineItemVO>();
			for(int j=0;j<c.size();j++){
				CommodityLineItemVO t=c.get(j);
				row=sheet.createRow(j+2);
				
				row.createCell(6).setCellValue(t.id);
				row.createCell(7).setCellValue(t.name);
				row.createCell(8).setCellValue(t.model);
				row.createCell(9).setCellValue(t.number);
				row.createCell(10).setCellValue(t.price);
				row.createCell(11).setCellValue(t.total);
				row.createCell(12).setCellValue(t.remark);
			}
		}
	
		try{
			FileOutputStream fout=new FileOutputStream(path);
			wb.write(fout);
			fout.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return ResultMessage.SUCCESS;
	}
	
	public ResultMessage exportPayment(String path,RequirementVO vo){
		HSSFWorkbook wb=new HSSFWorkbook();
		HSSFSheet sheet=wb.createSheet("付款单历史");
		HSSFRow row=sheet.createRow(0);
		HSSFCellStyle style=wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		
		HSSFCell cell=row.createCell(0);
		cell.setCellValue("单据编号");
		cell.setCellStyle(style);
		
		cell=row.createCell(1);
		cell.setCellValue("客户");
		cell.setCellStyle(style);
		
		cell=row.createCell(2);
		cell.setCellValue("操作员");
		cell.setCellStyle(style);
		
		cell=row.createCell(3);
		cell.setCellValue("转账列表");
		cell.setCellStyle(style);
		
		cell=row.createCell(7);
		cell.setCellValue("总额汇总");
		cell.setCellStyle(style);
		
		row=sheet.createRow(1);
		cell=row.createCell(4);
		cell.setCellValue("银行账户");
		cell.setCellStyle(style);
		
		cell=row.createCell(5);
		cell.setCellValue("转账金额");
		cell.setCellStyle(style);
		
		cell=row.createCell(6);
		cell.setCellValue("备注");
		cell.setCellStyle(style);
		
		ArrayList<PaymentVO> list=new ArrayList<PaymentVO>();
		if(vo.type==DocumentType.PAYMENT){
			list=showPayment(vo);
		}else{
			list=showReceipt(vo);
		}
		for(int i=0;i<list.size();i++){
			PaymentVO temp=list.get(i);
			row=sheet.createRow(i+1);
			
			row.createCell(0).setCellValue(temp.id);
			row.createCell(1).setCellValue(temp.customerId+" "+temp.customerName);
			row.createCell(2).setCellValue(temp.operatorId);
			row.createCell(7).setCellValue(temp.total);
			
			ArrayList<TransferLineItemVO> c=new ArrayList<TransferLineItemVO>();
			for(int j=0;j<c.size();j++){
				TransferLineItemVO t=c.get(j);
				row=sheet.createRow(j+2);
				
				row.createCell(4).setCellValue(t.bankAccount);
				row.createCell(5).setCellValue(t.account);
				row.createCell(6).setCellValue(t.remark);
			}
		}
	
		try{
			FileOutputStream fout=new FileOutputStream(path);
			wb.write(fout);
			fout.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return ResultMessage.SUCCESS;
	}
	
	public ResultMessage exportCash(String path,RequirementVO vo){
		HSSFWorkbook wb=new HSSFWorkbook();
		HSSFSheet sheet=wb.createSheet("现金费用单历史");
		HSSFRow row=sheet.createRow(0);
		HSSFCellStyle style=wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		
		HSSFCell cell=row.createCell(0);
		cell.setCellValue("单据编号");
		cell.setCellStyle(style);
		
		cell=row.createCell(1);
		cell.setCellValue("操作员");
		cell.setCellStyle(style);
		
		cell=row.createCell(2);
		cell.setCellValue("银行账户");
		cell.setCellStyle(style);
		
		cell=row.createCell(3);
		cell.setCellValue("条目清单");
		cell.setCellStyle(style);
		
		cell=row.createCell(7);
		cell.setCellValue("总额");
		cell.setCellStyle(style);
		
		row=sheet.createRow(1);
		
		cell=row.createCell(4);
		cell.setCellValue("条目名");
		cell.setCellStyle(style);
		
		cell=row.createCell(5);
		cell.setCellValue("金额");
		cell.setCellStyle(style);
		
		cell=row.createCell(6);
		cell.setCellValue("备注");
		cell.setCellStyle(style);
		
		ArrayList<CashVO> list=showCash(vo);
		for(int i=0;i<list.size();i++){
			CashVO temp=list.get(i);
			row=sheet.createRow(i+1);
			
			row.createCell(0).setCellValue(temp.id);
			row.createCell(1).setCellValue(temp.operator);
			row.createCell(2).setCellValue(temp.bankAccount);
			row.createCell(7).setCellValue(temp.total);

			ArrayList<ClauseLineItemVO> c=temp.clauseList;
			for(int j=0;j<c.size();j++){
				ClauseLineItemVO t=c.get(j);
				row=sheet.createRow(j+2);
				
				row.createCell(3).setCellValue(t.name);
				row.createCell(3).setCellValue(t.account);
				row.createCell(3).setCellValue(t.remark);
			}
		}
		
		try{
			FileOutputStream fout=new FileOutputStream(path);
			wb.write(fout);
			fout.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return ResultMessage.SUCCESS;
	}
	
	public ResultMessage exportException(String path,RequirementVO vo){
		HSSFWorkbook wb=new HSSFWorkbook();
		HSSFSheet sheet=wb.createSheet("库存异常历史");
		HSSFRow row=sheet.createRow(0);
		HSSFCellStyle style=wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		
		HSSFCell cell=row.createCell(0);
		cell.setCellValue("单据编号");
		cell.setCellStyle(style);
		
		cell=row.createCell(1);
		cell.setCellValue("时间");
		cell.setCellStyle(style);
		
		cell=row.createCell(2);
		cell.setCellValue("商品列表");
		cell.setCellStyle(style);
		
		row=sheet.createRow(1);
		cell=row.createCell(3);
		cell.setCellValue("商品编号");
		cell.setCellStyle(style);
		
		cell=row.createCell(4);
		cell.setCellValue("商品名称");
		cell.setCellStyle(style);
		
		cell=row.createCell(5);
		cell.setCellValue("商品型号");
		cell.setCellStyle(style);
		
		cell=row.createCell(6);
		cell.setCellValue("系统库存数量");
		cell.setCellStyle(style);
		
		cell=row.createCell(7);
		cell.setCellValue("实际库存数量");
		cell.setCellStyle(style);
		
		ArrayList<ExceptionVO> list=new ArrayList<ExceptionVO>();
		if(vo.type==DocumentType.OVERFLOW){
			list=showOverFlow(vo);
		}else{
			list=showLoss(vo);
		}
		for(int i=0;i<list.size();i++){
			ExceptionVO temp=list.get(i);
			row=sheet.createRow(i+1);
			
			row.createCell(0).setCellValue(temp.id);
			row.createCell(1).setCellValue(temp.time);
			
			ArrayList<ExceptionLineItemVO> e=temp.list;
			for(int j=0;j<e.size();j++){
				ExceptionLineItemVO t=e.get(j);
				row=sheet.createRow(j+2);
				
				row.createCell(3).setCellValue(t.id);
				row.createCell(4).setCellValue(t.name);
				row.createCell(5).setCellValue(t.model);
				row.createCell(6).setCellValue(t.systemNumber);
				row.createCell(7).setCellValue(t.actualNumber);
			}
		}
		
		try{
			FileOutputStream fout=new FileOutputStream(path);
			wb.write(fout);
			fout.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return ResultMessage.SUCCESS;
	}
	
	public ResultMessage exportWarning(String path,RequirementVO vo){
		HSSFWorkbook wb=new HSSFWorkbook();
		HSSFSheet sheet=wb.createSheet("报警单历史");
		HSSFRow row=sheet.createRow(0);
		HSSFCellStyle style=wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		
		HSSFCell cell=row.createCell(0);
		cell.setCellValue("单据编号");
		cell.setCellStyle(style);
		
		cell=row.createCell(1);
		cell.setCellValue("时间");
		cell.setCellStyle(style);
		
		cell=row.createCell(2);
		cell.setCellValue("商品列表");
		cell.setCellStyle(style);
		
		row=sheet.createRow(1);
		cell=row.createCell(3);
		cell.setCellValue("商品编号");
		cell.setCellStyle(style);
		
		cell=row.createCell(4);
		cell.setCellValue("商品名称");
		cell.setCellStyle(style);
		
		cell=row.createCell(5);
		cell.setCellValue("商品型号");
		cell.setCellStyle(style);
		
		cell=row.createCell(6);
		cell.setCellValue("库存数量");
		cell.setCellStyle(style);
		
		cell=row.createCell(7);
		cell.setCellValue("警戒数量");
		cell.setCellStyle(style);
		
		ArrayList<WarningVO> list=showWarning(vo);
		for(int i=0;i<list.size();i++){
			WarningVO temp=list.get(i);
			row=sheet.createRow(i+1);
			
			row.createCell(0).setCellValue(temp.id);
			row.createCell(1).setCellValue(temp.time);
			
			ArrayList<WarningLineItemVO> e=temp.list;
			for(int j=0;j<e.size();j++){
				WarningLineItemVO t=e.get(j);
				row=sheet.createRow(j+2);
				
				row.createCell(3).setCellValue(t.id);
				row.createCell(4).setCellValue(t.name);
				row.createCell(5).setCellValue(t.model);
				row.createCell(6).setCellValue(t.stockNumber);
				row.createCell(7).setCellValue(t.warningNumber);
			}
		}
		
		try{
			FileOutputStream fout=new FileOutputStream(path);
			wb.write(fout);
			fout.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return ResultMessage.SUCCESS;
	}
}
