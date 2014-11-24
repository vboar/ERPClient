package businesslogic.promotionbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.CommodityLineItemPO;
import po.SpecialOfferPO;
import util.ResultMessage;
import vo.CommodityLineItemVO;
import vo.SaleVO;
import vo.SpecialOfferVO;
import businesslogic.utilitybl.Utility;
import dataservice.datafactoryservice.DataFactoryImpl;

public class SpecialOffPromotion {
	
	public ResultMessage add(SpecialOfferVO vo) throws RemoteException{
		SpecialOfferPO po=voToPO(vo);
		if(Utility.checkTime(vo.startTime, vo.endTime)){
			return ResultMessage.TIME_ERROR;
		}
		DataFactoryImpl.getInstance().getSpecialOfferData().insert(po);
		return ResultMessage.SUCCESS;
	}
	
	public ResultMessage update(SpecialOfferVO vo) throws RemoteException{
		SpecialOfferPO po=voToPO(vo);
		if(Utility.checkTime(vo.startTime, vo.endTime)){
			return ResultMessage.TIME_ERROR;
		}
		DataFactoryImpl.getInstance().getSpecialOfferData().update(po);
		return ResultMessage.SUCCESS;
	}
	
	public ArrayList<SpecialOfferVO> show() throws RemoteException{
		ArrayList<SpecialOfferPO> poList=DataFactoryImpl.getInstance().getSpecialOfferData().show();
		ArrayList<SpecialOfferVO> voList=new ArrayList<SpecialOfferVO>();
		for(SpecialOfferPO po:poList){
			if(!po.isValid()){
				continue;
			}
			if(Utility.inTime(po.getStartTime(), po.getEndTime())){
				continue;
			}
			SpecialOfferVO vo=poToVo(po);
			voList.add(vo);
		}	
		return voList;
	}
	
	public SaleVO calBonus(SaleVO saleVO,SpecialOfferVO specialOfferVO){
		int validnumber=0;
		for(CommodityLineItemVO voCheck:specialOfferVO.commodityList){
			for(CommodityLineItemVO voBuy:saleVO.saleList){
				if(voBuy.id.equals(voCheck.id)&&voBuy.number>=voCheck.number){
					validnumber++;
				}
			}
		}
		if(validnumber!=specialOfferVO.commodityList.size()){
			saleVO.remark+="选购商品不满足促销条件";
		}
		//TODO
		//促销包怎么搞
		return saleVO;
		
	}
	
	

	private SpecialOfferPO voToPO(SpecialOfferVO vo){
		//String id,ArrayList<CombinationCommodityLineItemVO> commodityList
			//	,double total,String startTime,String endTime, boolean valid){
	String id;
		if(vo.id=="0000"){//要跟ui商量好
		id="";
		//TODO
	}else  id=vo.id;
		
		
		ArrayList<CommodityLineItemPO> giftInfo=null;//vo.giftInfo;
		//TODO
		double total=vo.total;
		String startTime=vo.startTime;
		String endTime=vo.endTime;
		boolean valid=vo.valid;
		SpecialOfferPO po=new SpecialOfferPO(id, giftInfo, total,startTime, endTime, valid);
		return po;
	}
	
	private SpecialOfferVO poToVo(SpecialOfferPO po){
		String id=po.getId();
		
		ArrayList<CommodityLineItemVO> giftInfo=null;//vo
		//TODO
		
		
		double total=po.getTotal();
		String startTime=po.getStartTime();
		String endTime=po.getEndTime();
		boolean valid=po.isValid();
		SpecialOfferVO vo=new SpecialOfferVO(id, giftInfo, total, startTime, endTime, valid);
		 return vo;
		
	}
}
