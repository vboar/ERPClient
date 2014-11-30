/**
 * Sale
 * @author oneoneO
 * @date 2014/11/14
 */
package businesslogic.salebl;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import po.CommodityLineItemPO;
import po.PresentLineItemPO;
import po.PresentPO;
import po.SalePO;
import util.DocumentStatus;
import util.DocumentType;
import util.ResultMessage;
import vo.CommodityLineItemVO;
import vo.CustomerGiftVO;
import vo.PresentLineItemVO;
import vo.PresentVO;
import vo.SaleVO;
import vo.SpecialOfferVO;
import vo.TotalGiftVO;
import businesslogic.presentbl.Present;
import businesslogic.promotionbl.CustomerGiftPromotion;
import businesslogic.promotionbl.SpecialOfferPromotion;
import businesslogic.promotionbl.TotalGiftPromotion;
import businesslogic.utilitybl.Utility;
import dataservice.datafactoryservice.DataFactoryImpl;

public class Sale {

	public ResultMessage add(SaleVO vo)  {

		SalePO po = SaleVOToSalePO(vo);
		try {
			DataFactoryImpl.getInstance().getSaleDataService().insert(po);
		} catch (RemoteException e) {
			
			e.printStackTrace();
		}
		Date date=new Date();
		SimpleDateFormat myFmt=new SimpleDateFormat("yyyy/MM/dd/HH:mm:ss");
		String time=myFmt.format(date);

		String customerId = vo.customerId;
		String customerName = vo.customerName;
		ArrayList<PresentLineItemVO> list = vo.giftList;
		DocumentStatus documentStatus = DocumentStatus.NONCHECKED;
		DocumentType documentType = DocumentType.PRESENT;

		PresentVO presentVO = new PresentVO(null, time, customerId,
				customerName, list, documentStatus, documentType, false);
		Present pr = new Present();
		pr.create(presentVO);

		return ResultMessage.SUCCESS;

	}

	public ArrayList<SaleVO> findByTime(String time1, String time2){
		ArrayList<SalePO> poList=null;
		
		try {
			poList = DataFactoryImpl.getInstance()
					.getSaleDataService().findByTime(time1, time2);
		} catch (RemoteException e) {
			
			e.printStackTrace();
		}
		ArrayList<SalePO> poList2=new ArrayList<SalePO>();
		for(SalePO po:poList){
			if(po.getDocumentType()==4){
				poList2.add(po);
			}
		}
		ArrayList<SaleVO> voList = poListToVoList(poList2);
		return voList;

	}

	// TODO
	public ArrayList<SaleVO> findByCommodityName(String commodityName) {

		return null;
	}

	public ArrayList<SaleVO> findByCustomer(String customer)
			 {
		ArrayList<SalePO> poList=null;
		try {
			poList = DataFactoryImpl.getInstance()
					.getSaleDataService().findByCustomer(customer);
		} catch (RemoteException e) {
			
			e.printStackTrace();
		}
		ArrayList<SalePO> poList2=new ArrayList<SalePO>();
		for(SalePO po:poList){
			if(po.getDocumentType()==4){
				poList2.add(po);
			}
		}
		ArrayList<SaleVO> voList = poListToVoList(poList2);
		return voList;
	}

	public ArrayList<SaleVO> findBySalesman(String salesman)
			 {
		ArrayList<SalePO> poList=null;
		try {
			poList = DataFactoryImpl.getInstance()
					.getSaleDataService().findBySalesman(salesman);
		} catch (RemoteException e) {
			
			e.printStackTrace();
		}
		
		ArrayList<SalePO> poList2=new ArrayList<SalePO>();
		for(SalePO po:poList){
			if(po.getDocumentType()==4){
				poList2.add(po);
			}
		}
		ArrayList<SaleVO> voList = poListToVoList(poList2);
		return voList;
	}

	public ArrayList<SaleVO> findByStorage(String Storage)
			 {
		ArrayList<SalePO> poList=null;
		try {
			poList = DataFactoryImpl.getInstance()
					.getSaleDataService().findByStorage(Storage);
		} catch (RemoteException e) {
			
			e.printStackTrace();
		}
		
		ArrayList<SalePO> poList2=new ArrayList<SalePO>();
		for(SalePO po:poList){
			if(po.getDocumentType()==4){
				poList2.add(po);
			}
		}
		ArrayList<SaleVO> voList = poListToVoList(poList2);
		return voList;

	}

	public ResultMessage update(SaleVO vo)  {
		SalePO po = SaleVOToSalePO(vo);
		try {
			DataFactoryImpl.getInstance().getSaleDataService().update(po);
		} catch (RemoteException e) {
			
			e.printStackTrace();
		}
		return ResultMessage.SUCCESS;
	}

	public SaleVO calPromotion(SaleVO vo1, CustomerGiftVO vo2) {
		CustomerGiftPromotion cgp = new CustomerGiftPromotion();
		SaleVO vo = cgp.calBonus(vo1, vo2);
		return vo;
	}

	public SaleVO calPromotion(SaleVO vo1, TotalGiftVO vo2) {
		TotalGiftPromotion tgp = new TotalGiftPromotion();
		SaleVO vo = tgp.calBonus(vo1, vo2);
		return vo;
	}

	public SaleVO calPromotion(SaleVO vo1, SpecialOfferVO vo2) {
		SpecialOfferPromotion sop = new SpecialOfferPromotion();
		SaleVO vo = sop.calBonus(vo1, vo2);
		return vo;
	}

	public ArrayList<SaleVO> show()  {

		ArrayList<SalePO> poList=null;
		try {
			poList = DataFactoryImpl.getInstance()
					.getSaleDataService().show();
		} catch (RemoteException e) {
			
			e.printStackTrace();
		}
		
		ArrayList<SalePO> poList2=new ArrayList<SalePO>();
		for(SalePO po:poList){
			if(po.getDocumentType()==4){
				poList2.add(po);
			}
		}
		ArrayList<SaleVO> voList = poListToVoList(poList2);
		return voList;

	}

	// TODO
	public ResultMessage approve(SaleVO vo) {
		return null;

	}

	public SaleVO SalePOToSaleVO(SalePO po) {
		// String id,String time,String customerId,String customerName,int
		// customerVIP,String salesman,
		// String operatorId,String storage,ArrayList<CommodityLineItemPO>
		// saleList,
		// ArrayList<PresentLineItemPO> giftList,double totalBeforeDiscount,
		// double discount,double voucher,double totalAfterDiscount,String
		// remark,
		// int documentStatus,boolean isWriteOff,int documentType
		String id = po.getId();
		String time = po.getTime();
		String customerId = po.getCustomerId();
		String customerName = po.getCustomerName();
		int customerVIP = po.getCustomerVIP();

		String salesman = po.getSalesman();
		
		String operatorId = po.getOperatorId();
		String storage = po.getStorage();
		ArrayList<CommodityLineItemVO> saleList = Utility.poListToVOList(po
				.getSaleList());
		String PresentId=po.getPresentId();
		
		
		Present presentTemp=new Present();
		PresentPO po2=presentTemp.getById(PresentId);
		ArrayList<PresentLineItemPO> prPOList=po2.getList();
		ArrayList<PresentLineItemVO> giftList=Utility.presentPOListToVOList(prPOList);
		
		
		double totalBeforeDiscount = po.getTotalBeforeDiscount();
		double discount = po.getDiscount();
		double voucher = po.getVoucher();
		double totalAfterDiscount = po.getTotalAfterDiscount();
		String remark = po.getRemark();
		DocumentStatus approvalState = DocumentStatus.values()[po
				.getDocumentStatus()];
		boolean isWriteOff = po.isWriteOff();
		DocumentType receiptType = DocumentType.values()[po.getDocumentType()];
		SaleVO vo = new SaleVO(id, time, customerId, customerName, customerVIP,
				salesman, operatorId, storage, saleList,
				PresentId,giftList, totalBeforeDiscount, discount, voucher,
				totalAfterDiscount, remark, approvalState, isWriteOff,
				receiptType);
		return vo;

	}

	public SalePO SaleVOToSalePO(SaleVO vo) {
		String id = vo.id;
		String time = vo.time;
		String customerId = vo.customerId;
		String customerName = vo.customerName;
		int customerVIP = vo.customerVIP;
		String salesman = vo.salesman;
		String operatorId = vo.operatorId;
		String storage = vo.storage;
		ArrayList<CommodityLineItemPO> saleList = Utility
				.voListToPOList(vo.saleList);
		String presentId=vo.presentId;
		double totalBeforeDiscount = vo.totalBeforeDiscount;
		double discount = vo.discount;
		double voucher = vo.voucher;
		double totalAfterDiscount = vo.totalAfterDiscount;
		String remark = vo.remark;
		int approvalState = vo.approvalState.ordinal();
		boolean isWriteOff = vo.isWriteOff;
		int receiptType = vo.receiptType.ordinal();
		SalePO po = new SalePO(id, time, customerId, customerName, customerVIP,
				salesman, operatorId, storage, saleList, presentId,
				totalBeforeDiscount, discount, voucher, totalAfterDiscount,
				remark, approvalState, isWriteOff, receiptType);
		return po;

	}

	public  ArrayList<SaleVO> poListToVoList(ArrayList<SalePO> poList) {
		ArrayList<SaleVO> voList = new ArrayList<SaleVO>();
		for (SalePO po : poList) {
			SaleVO vo = SalePOToSaleVO(po);
			voList.add(vo);
		}
		return voList;
	}
	
	public ResultMessage addlog(String content) {
		//TODO
		return null;
	}
}
