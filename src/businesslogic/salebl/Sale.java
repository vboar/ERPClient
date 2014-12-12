/**
 * Sale
 * @author oneoneO
 * @date 2014/11/14
 */
package businesslogic.salebl;

import businesslogic.commoditybl.Commodity;
import businesslogic.customerbl.Customer;
import businesslogic.loginbl.Login;
import businesslogic.presentbl.Present;
import businesslogic.promotionbl.CustomerGiftPromotion;
import businesslogic.promotionbl.SpecialOfferPromotion;
import businesslogic.promotionbl.TotalGiftPromotion;
import businesslogic.utilitybl.Utility;
import dataservice.datafactoryservice.DataFactoryImpl;
import po.*;
import util.DocumentStatus;
import util.DocumentType;
import util.ResultMessage;
import vo.*;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class Sale {
	
	public SaleVO SalePOToSaleVO(SalePO po) {
		String id = po.getId();
		String time = po.getTime();
		String customerId = po.getCustomerId();
		String customerName = po.getCustomerName();
		int customerVIP = po.getCustomerVIP();

		String salesman = po.getSalesmanId();
		
		String operatorId = po.getOperatorId();
		String storage = po.getStorage();
		ArrayList<CommodityLineItemVO> saleList = Utility.poListToVOList(po
				.getSaleList());
		String PresentId=po.getPresentId();
		
		ArrayList<PresentLineItemVO> giftList=new ArrayList<PresentLineItemVO>();
		if(PresentId.length()>4){//4,5都可以啦
		Present presentTemp=new Present();
		PresentVO vo2=presentTemp.getById(PresentId);
		PresentPO po2=presentTemp.voToPO(vo2);
		ArrayList<PresentLineItemPO> prPOList=po2.getList();
	    giftList=Utility.presentPOListToVOList(prPOList);
		}
		
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
		String salesman = vo.salesmanId;
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

	
	public String createId(){
		Date date = new Date();
		SimpleDateFormat myFmt = new SimpleDateFormat("yyyyMMdd");
		String time = myFmt.format(date);
		ArrayList<SaleVO> presentList = bigShow();
		if (presentList.isEmpty()) {
			return "XSD-" + time + "-00001";
		} else {
			String max = presentList.get(presentList.size() - 1).id;
			String day = max.substring(4, max.length() - 5);
			if (day.compareTo(time) < 0) {
				return "XSD-" + time + "-00001";
			}
			String oldMax = max.substring(max.length() - 5);
			int maxInt = Integer.parseInt(oldMax);
			String pattern = "00000";
			java.text.DecimalFormat df = new java.text.DecimalFormat(pattern);
			String maxStr = df.format(maxInt + 1);
			return "XSD-" + time + "-" + maxStr;
		}

	}
	
	public String createReturnId(){
		Date date = new Date();
		SimpleDateFormat myFmt = new SimpleDateFormat("yyyyMMdd");
		String time = myFmt.format(date);
		ArrayList<SaleVO> presentList = bigShow();
		if (presentList.isEmpty()) {
			return "XSTHD-" + time + "-00001";
		} else {
			String max = presentList.get(presentList.size() - 1).id;
			String day = max.substring(4, max.length() - 5);
			if (day.compareTo(time) < 0) {
				return "XSTHD-" + time + "-00001";
			}
			String oldMax = max.substring(max.length() - 5);
			int maxInt = Integer.parseInt(oldMax);
			String pattern = "00000";
			java.text.DecimalFormat df = new java.text.DecimalFormat(pattern);
			String maxStr = df.format(maxInt + 1);
			return "XSTHD-" + time + "-" + maxStr;
		}

	}

	public ResultMessage add(SaleVO vo)  {
		Date date=new Date();
		SimpleDateFormat myFmt=new SimpleDateFormat("yyyy/MM/dd/HH/mm/ss");
		String time=myFmt.format(date);
		vo.operatorId=Login.currentUserId;
		vo.time=time;
		vo.presentId="";
		
		
				
		SimpleDateFormat myFmt2=new SimpleDateFormat("yyyy/MM/dd/HH/mm/ss");
	    time=myFmt2.format(date);

		String customerId = vo.customerId;
		String customerName = vo.customerName;
		ArrayList<PresentLineItemVO> list = vo.giftList;
		DocumentStatus documentStatus = DocumentStatus.NONCHECKED;
		DocumentType documentType = DocumentType.PRESENT;

		if(list!=null) {
			String id = new Present().createId();
			vo.presentId=id;
			PresentVO presentVO = new PresentVO(id, time, customerId,
					customerName, list, documentStatus, documentType, false);
			Present pr = new Present();
			pr.create(presentVO);
		}

		SalePO po = SaleVOToSalePO(vo);
		System.out.println("salebl:161 salesmanId "+po.getSalesmanId());
		System.out.println("salebl:162 operatorId "+po.getOperatorId());
		try {
			DataFactoryImpl.getInstance().getSaleDataService().insert(po);
		} catch (RemoteException e) {
			
			e.printStackTrace();
		}
		return ResultMessage.SUCCESS;

	}
	
	public ResultMessage update(SaleVO vo)  {
		String time=getById(vo.id).time;
		vo.time=time;
		SalePO po = SaleVOToSalePO(vo);
		try {
			DataFactoryImpl.getInstance().getSaleDataService().update(po);
		} catch (RemoteException e) {
			
			e.printStackTrace();
		}
		return ResultMessage.SUCCESS;
	}
	
	public SaleVO getById(String id) {
		SalePO po = null;
		try {
			po = DataFactoryImpl.getInstance().getSaleDataService().getById(id);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		SaleVO vo = SalePOToSaleVO(po);
		return vo;

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

	public ArrayList<SaleVO> findByCommodityName(String commodityName) {

		ArrayList<SaleVO> voList = show();
		ArrayList<SaleVO> result = new ArrayList<SaleVO>();

		for (SaleVO vo : voList) {
			for (CommodityLineItemVO commodity : vo.saleList) {
				if (commodity.name.equals(commodityName)) {
					result.add(vo);
				}
			}
		}
		return result;
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
	
	public ArrayList<SaleVO> findByStatus(int status){
		ArrayList<SaleVO> result=new ArrayList<SaleVO>();
		ArrayList<SalePO> temp=new ArrayList<SalePO>();
		try {
			temp=DataFactoryImpl.getInstance().getSaleDataService().findByStatus(status);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		for(int i=0;i<temp.size();i++){
			if(temp.get(i).getDocumentType()==DocumentType.SALE.ordinal())
			result.add(SalePOToSaleVO(temp.get(i)));
		}
		
		return result;
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
	
	public ArrayList<SaleVO> bigShow() {
		ArrayList<SalePO> poList = null;
		try {
			poList = DataFactoryImpl.getInstance().getSaleDataService().show();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		ArrayList<SaleVO> voList = poListToVoList(poList);

		return voList;

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

	

	// TODO
	public ResultMessage approve(SaleVO vo) {
		//改商品
		//改客户
		//促销的商品？？
		//TODO
		double total=vo.totalAfterDiscount-vo.voucher;

		Customer cus=new Customer();
		if(total>0){
		
		//CustomerVO cusvo=new Customer().getByid(vo.customerId);
		cus.updateByPurchase(vo.customerId, vo.totalAfterDiscount);
		}
		for(CommodityLineItemVO vo1:vo.saleList){
			
			Commodity commodity=new Commodity();
			String id=vo1.id;
			if(id.compareTo("99998")>0){
				SpecialOfferVO spevo=new SpecialOfferPromotion().getById(id);
				ArrayList<CommodityLineItemVO> spList=spevo.commodityList;
				for(CommodityLineItemVO commodityLineItemvo:spList){
					CommodityPO commoditypo=commodity.getById(commodityLineItemvo.id);
					commoditypo.setNumber(commoditypo.getNumber()-vo1.number);
					try {
						DataFactoryImpl.getInstance().getCommodityData().update(commoditypo);
					} catch (RemoteException e) {
						e.printStackTrace();
					}
				}
				continue;
			}
			
			CommodityPO commoditypo=commodity.getById(vo1.id);
			commoditypo.setNumber(commoditypo.getNumber()-vo1.number);
			commoditypo.setRecentSalePrice(vo1.price);
			try {
				DataFactoryImpl.getInstance().getCommodityData().update(commoditypo);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			
			
		}		
		return null;
	}
	public ArrayList<PromotionVO> calCustomerPromotion(int VIP){
		CustomerGiftPromotion bl=new CustomerGiftPromotion();
		ArrayList<PromotionVO> result=new ArrayList<PromotionVO>();
		ArrayList<CustomerGiftVO>voList=bl.show();
		for(CustomerGiftVO oldVO:voList){
			if(oldVO.vip<=VIP){
			PromotionVO vo=new PromotionVO(oldVO.id, oldVO.giftInfo, oldVO.discount, oldVO.voucher);
			result.add(vo);
			}
		}
		
		return result;
	}
	
	public ArrayList<PromotionVO> calTotalGiftPromotion(double price) {
		TotalGiftPromotion bl=new TotalGiftPromotion();
		ArrayList<PromotionVO> result=new ArrayList<PromotionVO>();

		ArrayList<TotalGiftVO> voList=bl.show();
		for(TotalGiftVO oldVO:voList){
			if(oldVO.total<=price){
				PromotionVO vo=new PromotionVO(oldVO.id, oldVO.giftInfo, 1, oldVO.voucher);
				result.add(vo);
			}
		}
		return result;
	}
	
	public SaleVO calAfterPrice(String customerGiftId, String totalGiftId,
			SaleVO vo) {
		if(customerGiftId==null&&totalGiftId==null){
			vo.remark+="没有促销策略";
			return vo;
		}if(customerGiftId==null){
			TotalGiftVO totalVO=new TotalGiftPromotion().getById(totalGiftId);
			vo.voucher=totalVO.voucher;
			vo.giftList=totalVO.giftInfo;
			vo.remark+="$使用id是"+totalGiftId+"的基于总价的促销策略";
			return vo;
		}
		if(totalGiftId==null){
			CustomerGiftVO cusVO=new CustomerGiftPromotion().getById(customerGiftId);
			vo.voucher=cusVO.voucher;
			vo.discount=cusVO.discount;
			vo.totalAfterDiscount=vo.totalBeforeDiscount*vo.discount;
			vo.giftList=cusVO.giftInfo;
			vo.remark+="$使用id是"+customerGiftId+"的vip促销策略";
			return vo;
		}
		CustomerGiftVO cusVO=new CustomerGiftPromotion().getById(customerGiftId);
		TotalGiftVO totalVO=new TotalGiftPromotion().getById(totalGiftId);
		vo.voucher=cusVO.voucher+totalVO.voucher;
		vo.discount=cusVO.discount;
		vo.totalAfterDiscount=vo.totalBeforeDiscount*vo.discount;

		
		vo.giftList=toOne(cusVO.giftInfo,totalVO.giftInfo);
		return vo;
	}


	private ArrayList<PresentLineItemVO> toOne(ArrayList<PresentLineItemVO> list1,ArrayList<PresentLineItemVO> list2){
		ArrayList<PresentLineItemVO> result=new ArrayList<PresentLineItemVO>();
		for(int i=0;i<list1.size();i++){
			PresentLineItemVO father=list1.get(i);
			Iterator<PresentLineItemVO> itr=list2.iterator();
			while(itr.hasNext()){
				PresentLineItemVO beadded=itr.next();
				if(beadded.id.equals(father)){
					father.number+=beadded.number;
					list1.set(i,father);					
				}else{
					result.add(beadded);
				}
			}
			
		}
		result.addAll(list1);
		return result;
	}
		
	public ResultMessage addlog(String content) {
		//TODO
		return null;
	}
	
	
	}
