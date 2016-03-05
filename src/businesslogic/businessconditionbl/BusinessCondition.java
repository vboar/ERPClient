/**
 * 查看经营情况
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

import util.ResultMessage;
import util.Time;
import vo.BusinessConditionVO;
import vo.CommodityLineItemVO;
import vo.CommodityVO;
import vo.ExceptionLineItemVO;
import vo.ExceptionVO;
import vo.PresentLineItemVO;
import vo.PresentVO;
import vo.RequirementVO;
import vo.SaleVO;
import businesslogic.commoditybl.Commodity;
import businesslogic.exceptionbl.Loss;
import businesslogic.exceptionbl.Overflow;
import businesslogic.logbl.Log;
import businesslogic.presentbl.Present;
import businesslogic.salebl.Sale;
import businesslogic.salebl.SaleReturn;

public class BusinessCondition {
	public ResultMessage addLog(String content){
		Log l=new Log();
		return l.add(content);
	}

	public BusinessConditionVO show(String time1,String time2){
	double[] saleIncome=saleIncome(time1,time2);
	double saleReturnIncome=saleReturnIncome(time1,time2);
	double costAdjustIncome=costAdjustIncome(time1,time2);
	double over=OverflowIncome(time1,time2);
	double adjust=costAdjustIncome(time1,time2);
	double sale=saleIncome[0];
	double saleAfterDiscount=saleIncome[1]+over+adjust;
	
	double saleCost=saleCost(time1,time2);
	double loss=LossCost(time1,time2);
	double present=presentCost(time1,time2);
	double totalCost=saleCost+loss+present;
	double profit=saleAfterDiscount-totalCost;
	BusinessConditionVO result=new BusinessConditionVO(sale,saleAfterDiscount,
			saleIncome[0]-saleIncome[1],over,adjust,saleIncome[2],saleCost,loss,present,totalCost,
			profit);
		System.out.println("businesscondition "+result.saleIncome+" "+result.incomeAfterDiscount);

	return result;
	}
	
	//0:销售收入
	//1:折让后收入
	//2：代金券与实际收款差额
	public double[] saleIncome(String time1,String time2){
		double[] result=new double[3];
		Sale s=new Sale();
		ArrayList<SaleVO> list=s.findByTime(time1, time2);
		for(int i=0;i<list.size();i++){
			
			SaleVO temp=list.get(i);
			if(temp.isWriteOff){
				result[0]-=temp.totalBeforeDiscount;
				result[1]-=temp.totalAfterDiscount;
				result[2]-=temp.voucher;

			}else{
			result[0]+=temp.totalBeforeDiscount;
			result[1]+=temp.totalAfterDiscount;
			result[2]+=temp.voucher;
			}
		}
		ArrayList<SaleVO> list2=new SaleReturn().findByTime(time1, time2);
		for(int i=0;i<list2.size();i++){

			SaleVO temp=list2.get(i);
			if(temp.isWriteOff){
				result[0]+=temp.totalBeforeDiscount;
				result[1]+=temp.totalAfterDiscount;
				result[2]+=temp.voucher;

			}else{
				result[0]-=temp.totalBeforeDiscount;
				result[1]-=temp.totalAfterDiscount;
				result[2]-=temp.voucher;
			}
		}
		System.out.println("businesscondition result="+result[0]+result[1]+result[2]);
		return result;
	}
	
	//进货退货差价
	public double saleReturnIncome(String time1,String time2){
		double result=0;
		SaleReturn sr=new SaleReturn();
		time1=Time.jdugeTime1(time1);
		time2=Time.jdugeTime2(time2);
		ArrayList<SaleVO> list=sr.findByTime(time1, time2);
		ArrayList<CommodityLineItemVO> commodity=new ArrayList<CommodityLineItemVO>();
		
		for(int i=0;i<list.size();i++){
			commodity=list.get(i).saleList;
			for(int j=0;j<commodity.size();j++){
				CommodityLineItemVO temp=commodity.get(j);
				Commodity c=new Commodity();
				CommodityVO t=c.commodityPOToCommodityVO(c.getById(temp.id));
				
				result=result+(t.recentPurchasePrice*temp.number-temp.total);
			}
		}
		return result;
	}
	
	public double costAdjustIncome(String time1,String time2){
		return 0;
	}
	
	//报溢收入
	public double OverflowIncome(String time1,String time2){
		double result=0;
		Overflow of=new Overflow();
		time1=Time.jdugeTime1(time1);
		time2=Time.jdugeTime2(time2);
		ArrayList<ExceptionVO> list=of.findByTime(time1,time2);
		ArrayList<ExceptionLineItemVO> commodity=new ArrayList<ExceptionLineItemVO>();
		
		for(int i=0;i<list.size();i++){
			commodity=list.get(i).list;
			for(int j=0;j<commodity.size();j++){
				ExceptionLineItemVO temp=commodity.get(j);
				Commodity c=new Commodity();
				CommodityVO t=c.commodityPOToCommodityVO(c.getById(temp.id));
				
				result=result+t.purchasePrice*(temp.actualNumber-temp.systemNumber);
			}
		}
		return result;
	}
	
	//报损成本
	public double LossCost(String time1,String time2){
		double result=0;
		Loss l=new Loss();
		time1=Time.jdugeTime1(time1);
		time2=Time.jdugeTime2(time2);
		ArrayList<ExceptionVO> list=l.show(time1,time2);
		ArrayList<ExceptionLineItemVO> commodity=new ArrayList<ExceptionLineItemVO>();
		
		for(int i=0;i<list.size();i++){
			commodity=list.get(i).list;
			for(int j=0;j<commodity.size();j++){
				ExceptionLineItemVO temp=commodity.get(j);
				Commodity c=new Commodity();
				CommodityVO t=c.commodityPOToCommodityVO(c.getById(temp.id));
				
				result=result+t.purchasePrice*(temp.systemNumber-temp.actualNumber);
			}
		}
		return result;
	}
	
	//赠品成本
	public double presentCost(String time1,String time2){
		double result=0;
		Present p=new Present();
		ArrayList<PresentVO> list=p.findByTime(time1, time2);
		ArrayList<PresentLineItemVO> present=new ArrayList<PresentLineItemVO>();
		
		for(int i=0;i<list.size();i++){
			present=list.get(i).list;
			for(int j=0;j<present.size();j++){
				PresentLineItemVO temp=present.get(j);
				Commodity c=new Commodity();
				CommodityVO t=c.commodityPOToCommodityVO(c.getById(temp.id));
				result=result+t.recentPurchasePrice*temp.number;
			}
		}
		return result;
	}
	
	//销售成本
	public double saleCost(String time1,String time2){
		double result=0;
		Sale s=new Sale();
		ArrayList<SaleVO> list=s.findByTime(time1, time2);
		ArrayList<CommodityLineItemVO> commodity=new ArrayList<CommodityLineItemVO>();
		
		for(int i=0;i<list.size();i++){
			commodity=list.get(i).saleList;
			for(int j=0;j<commodity.size();j++){
				CommodityLineItemVO temp=commodity.get(j);
				Commodity c=new Commodity();
				CommodityVO vo=c.commodityPOToCommodityVO(c.getById(temp.id));
				result=result+temp.number*vo.recentPurchasePrice;
				}
		}
		return result;
	}
	
	public ResultMessage exportExcel(String path,RequirementVO vo){
		HSSFWorkbook wb=new HSSFWorkbook();
		HSSFSheet sheet=wb.createSheet("经营情况表");
		HSSFRow row=sheet.createRow(0);
		HSSFCellStyle style=wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		
		HSSFCell cell=row.createCell(0);
		cell.setCellValue("销售收入");
		cell.setCellStyle(style);
		
		cell=row.createCell(1);
		cell.setCellValue("折让后收入");
		cell.setCellStyle(style);
		
		cell=row.createCell(2);
		cell.setCellValue("折让");
		cell.setCellStyle(style);
		
		cell=row.createCell(3);
		cell.setCellValue("商品报溢收入");
		cell.setCellStyle(style);
		
		cell=row.createCell(4);
		cell.setCellValue("成本调价收入");
		cell.setCellStyle(style);
		
		cell=row.createCell(5);
		cell.setCellValue("代金券与实收差额");
		cell.setCellStyle(style);
		
		cell=row.createCell(6);
		cell.setCellValue("销售成本");
		cell.setCellStyle(style);
		
		cell=row.createCell(7);
		cell.setCellValue("商品报损成本");
		cell.setCellStyle(style);
		
		cell=row.createCell(8);
		cell.setCellValue("商品赠送成本");
		cell.setCellStyle(style);
		
		cell=row.createCell(9);
		cell.setCellValue("总支出");
		cell.setCellStyle(style);
		
		cell=row.createCell(10);
		cell.setCellValue("利润");
		cell.setCellStyle(style);

		BusinessConditionVO v=show(vo.time1,vo.time2);
		row=sheet.createRow(1);
		row.createCell(0).setCellValue(v.saleIncome);
		row.createCell(1).setCellValue(v.incomeAfterDiscount);
		row.createCell(2).setCellValue(v.discount);
		row.createCell(3).setCellValue(v.commodityOverIncome);
		row.createCell(4).setCellValue(v.costAdjustIncome);
		row.createCell(5).setCellValue(v.voucherIncome);
		row.createCell(6).setCellValue(v.saleCost);
		row.createCell(7).setCellValue(v.costByLoss);
		row.createCell(8).setCellValue(v.costBySending);
		row.createCell(9).setCellValue(v.saleCost);
		row.createCell(10).setCellValue(v.profit);

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
