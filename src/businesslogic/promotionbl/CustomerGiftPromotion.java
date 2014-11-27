/**
 * @author chengcheng
 * @date 2014/11/14
 */

package businesslogic.promotionbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.CustomerGiftPO;
import po.PresentLineItemPO;
import util.ResultMessage;
import vo.CustomerGiftVO;
import vo.PresentLineItemVO;
import vo.SaleVO;
import businesslogic.utilitybl.Utility;
import dataservice.datafactoryservice.DataFactoryImpl;

public class CustomerGiftPromotion {
	public ArrayList<CustomerGiftVO> findByVip(int level,ArrayList<CustomerGiftVO> list){
		ArrayList<CustomerGiftVO> list2 =new ArrayList<CustomerGiftVO>();
		for(int i=0;i<list.size();i++){
			if(list.get(i).vip==level){
				list2.add(list.get(i));
			}
		}
		return list2;		
	}
	public ResultMessage add(CustomerGiftVO vo) throws RemoteException{
		CustomerGiftPO po=voToPO(vo);
		if(Utility.checkTime(vo.startTime, vo.endTime)){
			return ResultMessage.TIME_ERROR;
		}
		DataFactoryImpl.getInstance().getCustomerGiftData().insert(po);
		return ResultMessage.SUCCESS;
	}
	
	
	public ResultMessage update(CustomerGiftVO vo) throws RemoteException{
		CustomerGiftPO po=voToPO(vo);
		if(Utility.checkTime(vo.startTime, vo.endTime)){
			return ResultMessage.TIME_ERROR;
		}
		DataFactoryImpl.getInstance().getCustomerGiftData().update(po);
		return ResultMessage.SUCCESS;
	}
	
	public ArrayList<CustomerGiftVO> show() throws RemoteException{
		ArrayList<CustomerGiftPO> poList=DataFactoryImpl.getInstance().getCustomerGiftData().show();
		ArrayList<CustomerGiftVO> voList=new ArrayList<CustomerGiftVO>();
		for(CustomerGiftPO po:poList){
			if(!po.isValid()){
				continue;
			}
			if(Utility.inTime(po.getStartTime(), po.getEndTime())){
				continue;
			}
			CustomerGiftVO vo=poToVo(po);
			voList.add(vo);
		}	
		return voList;
	}
	public SaleVO calBonus(SaleVO saleVO,CustomerGiftVO customerGiftVO){
		if(saleVO.customerVIP<=customerGiftVO.vip){
			saleVO.remark+=" vip等级不够，无法享受促销优惠";
			return saleVO;
		}
		//代金券怎么搞？？
		//TODO
		saleVO.discount=saleVO.totalBeforeDiscount*customerGiftVO.discount;
		saleVO.totalAfterDiscount=saleVO.totalBeforeDiscount*(1-customerGiftVO.discount);
		saleVO.giftList=customerGiftVO.giftInfo;
		
		return saleVO;
	}
	
	private CustomerGiftPO voToPO(CustomerGiftVO vo){
		//(String id,int VIP,ArrayList<CombinationCommodityLineItemVO> giftInfo
			//	,double discount,double voucher,String startTime,String endTime,boolean valid
	String id;
		if(vo.id=="0000"){//要跟ui商量好
		id="";
		//TODO
	}else  id=vo.id;
		
		int VIP=vo.vip;
		ArrayList<PresentLineItemPO> giftInfo=null;//vo.giftInfo;
		//TODO
		double discount=vo.discount;
		double voucher=vo.voucher;
		String startTime=vo.startTime;
		String endTime=vo.endTime;
		boolean valid=vo.valid;
		CustomerGiftPO po=new CustomerGiftPO(id, VIP, giftInfo, discount, voucher, startTime, endTime, valid);
		return po;
	}
	
	private CustomerGiftVO poToVo(CustomerGiftPO po){
		String id=po.getId();
		int VIP=po.getVIP();
		ArrayList<PresentLineItemVO> giftInfo=null;//vo
		//TODO
		
		double discount=po.getDiscount();
		double voucher=po.getVoucher();
		String startTime=po.getStartTime();
		String endTime=po.getEndTime();
		boolean valid=po.isValid();
		CustomerGiftVO vo=new CustomerGiftVO(id, VIP, giftInfo, discount, voucher, startTime, endTime, valid);
		 return vo;
		
	}

}
