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
	
	public String createId() {
		ArrayList<CustomerGiftVO> voList = show();

		if (voList.size() == 0) {
			return "CUG-00000";
		} else {
			String max = voList.get(voList.size() - 1).id;
			String oldMax = max.substring(max.length() - 5);
			int maxInt = Integer.parseInt(oldMax);
			String pattern = "00000";
			java.text.DecimalFormat df = new java.text.DecimalFormat(pattern);
			String maxStr = df.format(maxInt + 1);
			return "CUG-" + maxStr;
		}

	}

		public ResultMessage add(CustomerGiftVO vo) {
		CustomerGiftPO po=voToPO(vo);
		if(!Utility.checkTime(vo.startTime, vo.endTime)){
			return ResultMessage.TIME_ERROR;
		}
		
		try {
			DataFactoryImpl.getInstance().getCustomerGiftData().insert(po);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return ResultMessage.SUCCESS;
	}
	
	
	public ResultMessage update(CustomerGiftVO vo) {
		CustomerGiftPO po=voToPO(vo);
		if(!Utility.checkTime(vo.startTime, vo.endTime)){
			return ResultMessage.TIME_ERROR;
		}
		try {
			DataFactoryImpl.getInstance().getCustomerGiftData().update(po);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return ResultMessage.SUCCESS;
	}
	
	public ArrayList<CustomerGiftVO> findByVip(int level,ArrayList<CustomerGiftVO> list){
		ArrayList<CustomerGiftVO> list2 =new ArrayList<CustomerGiftVO>();
		for(int i=0;i<list.size();i++){
			if(list.get(i).vip==level){
				list2.add(list.get(i));
			}
		}
		return list2;		
	}
	
	public CustomerGiftVO getById(String id){
		ArrayList<CustomerGiftVO> list=showAll();
		for(CustomerGiftVO vo:list){
			if(vo.id.equals(id)){
				return vo;
			}
		}
		return null;
	}
	
	public ArrayList<CustomerGiftVO> showAll() {
		ArrayList<CustomerGiftPO> poList=null;
		try {
			poList = DataFactoryImpl.getInstance().getCustomerGiftData().show();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		ArrayList<CustomerGiftVO> voList=new ArrayList<CustomerGiftVO>();
		for(CustomerGiftPO po:poList){
			CustomerGiftVO vo=poToVo(po);
			voList.add(vo);
		}	
		return voList;
	}


	
	public ArrayList<CustomerGiftVO> show() {
		ArrayList<CustomerGiftPO> poList=null;
		try {
			poList = DataFactoryImpl.getInstance().getCustomerGiftData().show();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
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
	String id=vo.id;
				
		int VIP=vo.vip;
		ArrayList<PresentLineItemPO> giftInfo=Utility.presentVOListToPOlist(vo.giftInfo);
		
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
		ArrayList<PresentLineItemVO> giftInfo=Utility.presentPOListToVOList(po.getGiftInfo());
		
		
		double discount=po.getDiscount();
		double voucher=po.getVoucher();
		String startTime=po.getStartTime();
		String endTime=po.getEndTime();
		boolean valid=po.isValid();
		CustomerGiftVO vo=new CustomerGiftVO(id, VIP, giftInfo, discount, voucher, startTime, endTime, valid);
		 return vo;
		
	}

}
