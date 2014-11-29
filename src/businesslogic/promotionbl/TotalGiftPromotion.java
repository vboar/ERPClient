package businesslogic.promotionbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.PresentLineItemPO;
import po.TotalGiftPO;
import util.ResultMessage;
import vo.PresentLineItemVO;
import vo.SaleVO;
import vo.TotalGiftVO;
import businesslogic.utilitybl.Utility;
import dataservice.datafactoryservice.DataFactoryImpl;

public class TotalGiftPromotion {
	public ResultMessage add(TotalGiftVO vo)  {
		TotalGiftPO po=voToPO(vo);
		if(Utility.checkTime(vo.startTime, vo.endTime)){
			return ResultMessage.TIME_ERROR;
		}
		try {
			DataFactoryImpl.getInstance().getTotalGiftData().insert(po);
		} catch (RemoteException e) {
			
			e.printStackTrace();
		}
		return ResultMessage.SUCCESS;
	}

	public ResultMessage update(TotalGiftVO vo)  {
		TotalGiftPO po=voToPO(vo);
		if(Utility.checkTime(vo.startTime, vo.endTime)){
			return ResultMessage.TIME_ERROR;
		}
		try {
			DataFactoryImpl.getInstance().getTotalGiftData().update(po);
		} catch (RemoteException e) {
		
			e.printStackTrace();
		}
		return ResultMessage.SUCCESS;
	}

	public ArrayList<TotalGiftVO> show()  {
		ArrayList<TotalGiftPO> poList=null;
		try {
			poList = DataFactoryImpl.getInstance().getTotalGiftData().show();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		ArrayList<TotalGiftVO> voList=new ArrayList<TotalGiftVO>();
		for(TotalGiftPO po:poList){
			if(!po.isValid()){
				continue;
			}
			if(Utility.inTime(po.getStartTime(), po.getEndTime())){
				continue;
			}
			TotalGiftVO vo=poToVo(po);
			voList.add(vo);
		}	
		return voList;

	}

	public SaleVO calBonus(SaleVO saleVO, TotalGiftVO totalGiftVO) {
		if(saleVO.totalBeforeDiscount<=totalGiftVO.total){
			saleVO.remark+=" 总额不够，无法享受促销优惠";
			return saleVO;
		}
		//TODO
		//代金券没弄
		saleVO.giftList=totalGiftVO.giftInfo;
		return saleVO;
	}
	private TotalGiftPO voToPO(TotalGiftVO vo){
		//String id,double total,ArrayList<CommodityLineItemVO> giftInfo,
		//double discount,double voucher,String startTime,String endTime,boolean valid)
		String id;
		if(vo.id==null){
			id="";
			//TODO 新生成的单子，id还没有生成
		}
		id=vo.id;
		double total=vo.total;
		ArrayList<PresentLineItemPO> giftInfo=Utility.presentVOListToPOlist(vo.giftInfo);
	
		
		double voucher=vo.voucher;
		String startTime=vo.startTime;
		String endTime=vo.endTime;
		boolean valid=vo.valid;
		TotalGiftPO po=new TotalGiftPO(id, total, giftInfo, voucher, startTime, endTime, valid);
		
		return po;
		
	}
	private TotalGiftVO poToVo(TotalGiftPO po){
		String id=po.getId();
		double total=po.getTotal();
		ArrayList<PresentLineItemVO> giftInfo=Utility.presentPOListToVOList(po.getGiftInfo());	
		double voucher=po.getVoucher();
		String startTime=po.getStartTime();
		String endTime=po.getEndTime();
		boolean valid=po.isValid();
		TotalGiftVO vo=new TotalGiftVO(id, total, giftInfo, voucher, startTime, endTime, valid);
		return vo;
		
	}
}
