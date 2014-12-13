/**
 * 查看销售明细
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
import vo.CommodityLineItemVO;
import vo.RequirementVO;
import vo.SaleDetailsVO;
import vo.SaleVO;
import businesslogic.logbl.Log;
import businesslogic.salebl.Sale;

public class SaleDetails {
	public ResultMessage addLog(String content){
		Log l=new Log();
		return l.add(content);
	}
	
	public ArrayList<SaleDetailsVO> show(RequirementVO vo){
		Sale s=new Sale();
		ArrayList<SaleDetailsVO> result=new ArrayList<SaleDetailsVO>();
		ArrayList<SaleVO> all=new ArrayList<SaleVO>();//存放全部符合条件的SaleVO
		
		all=s.show();
		for(int i=0;i<all.size();i++)
			if(judgeTime(vo.time1,vo.time2,all.get(i).time)==false)
				all.remove(i);
		if(!vo.commodityName.equals("")){
			for(int i=0;i<all.size();i++){
				ArrayList<CommodityLineItemVO> temp=all.get(i).saleList;
				boolean isContain=false;
				for(int j=0;j<temp.size();j++){				
					if(temp.get(j).name.equals(vo.commodityName))
						isContain=true;
				}
				if(isContain==false)
					all.remove(i);
			}
		}

		System.out.println(all.size());
		if(vo.customer!=null){
			for(int i=0;i<all.size();i++){
				System.out.println(all.get(i).customerId);
				System.out.println(vo.customer.equals(all.get(i).customerId));
				if(!all.get(i).customerId.equals(vo.customer))
					all.remove(i);
			}
		}
		System.out.println("after customer: "+all.size());
		if(vo.operator!=null){
			for(int i=0;i<all.size();i++)
				if(!all.get(i).operatorId.equals(vo.operator))
					all.remove(i);
		}
		if(vo.storage!=null){
			for(int i=0;i<all.size();i++){
				if(!all.get(i).storage.equals(vo.storage))
					all.remove(i);
			}
		}
		for(int i=0;i<all.size();i++){
			ArrayList<CommodityLineItemVO> list=new ArrayList<CommodityLineItemVO>();
			list=all.get(i).saleList;
			String time=all.get(i).time;
			SaleDetailsVO sd=new SaleDetailsVO("","","",0,0,0);
			for(int j=0;j<list.size();j++){
				sd.time=time;
				sd.name=list.get(j).name;
				sd.model=list.get(j).model;
				sd.number=list.get(j).number;
				sd.price=list.get(j).price;
				sd.total=list.get(j).total;
				result.add(sd);
			}
		}
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

	public ResultMessage exportExcel(String path,RequirementVO vo){
		HSSFWorkbook wb=new HSSFWorkbook();
		HSSFSheet sheet=wb.createSheet("销售明细查看");
		HSSFRow row=sheet.createRow(0);
		HSSFCellStyle style=wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		
		HSSFCell cell=row.createCell(0);
		cell.setCellValue("时间");
		cell.setCellStyle(style);
		
		cell=row.createCell(1);
		cell.setCellValue("商品名");
		cell.setCellStyle(style);
		
		cell=row.createCell(2);
		cell.setCellValue("型号");
		cell.setCellStyle(style);
		
		cell=row.createCell(3);
		cell.setCellValue("数量");
		cell.setCellStyle(style);
		
		cell=row.createCell(4);
		cell.setCellValue("单价");
		cell.setCellStyle(style);
		
		cell=row.createCell(5);
		cell.setCellValue("总额");
		cell.setCellStyle(style);
		
		ArrayList<SaleDetailsVO> sd=show(vo);
		for(int i=0;i<sd.size();i++){
			SaleDetailsVO temp=sd.get(i);
			row=sheet.createRow(i+1);
			
			row.createCell(0).setCellValue(temp.time.substring(0,10));
			row.createCell(1).setCellValue(temp.name);
			row.createCell(2).setCellValue(temp.model);
			row.createCell(3).setCellValue(temp.number);
			row.createCell(4).setCellValue(temp.price);
			row.createCell(5).setCellValue(temp.total);
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
