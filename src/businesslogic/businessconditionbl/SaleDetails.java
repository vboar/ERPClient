/**
 * 查看销售明细
 * @author JaneLDQ
 * @date 2014/11/14
 */
package businesslogic.businessconditionbl;

import java.util.ArrayList;

import util.ResultMessage;
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
		ArrayList<SaleVO> temp=new ArrayList<SaleVO>();

		if(vo.time1!=null&&vo.time2!=null){
			temp=s.findByTime(vo.time1,vo.time2);
			for(int i=0;i<temp.size();i++)
				all.add(temp.get(i));
		}
		
		if(vo.commodityName!=null){
			temp=s.findByCommodityName(vo.commodityName);
			for(int i=0;i<temp.size();i++)
				all.add(temp.get(i));
		}
		
		if(vo.customer!=null){
			temp=s.findByCustomer(vo.commodityName);
			for(int i=0;i<temp.size();i++)
				all.add(temp.get(i));
		}
		
		if(vo.operator!=null){
			temp=s.show();
			for(int i=0;i<temp.size();i++){
				if((temp.get(i).operatorId).equals(vo.operator))
					all.add(temp.get(i));
			}
		}
		
		if(vo.storage!=null){
			temp=s.findByStorage(vo.storage);
			for(int i=0;i<temp.size();i++)
				all.add(temp.get(i));
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
}
