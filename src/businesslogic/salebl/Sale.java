/**
 * Sale
 * @author oneoneO
 * @date 2014/11/14
 */
package businesslogic.salebl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.CommodityLineItemPO;
import po.PresentLineItemPO;
import po.SalePO;
import util.DocumentStatus;
import util.DocumentType;
import util.ResultMessage;
import vo.CommodityLineItemVO;
import vo.CustomerGiftVO;
import vo.PresentLineItemVO;
import vo.SaleVO;
import vo.SpecialOfferVO;
import vo.TotalGiftVO;
import businesslogic.promotionbl.CustomerGiftPromotion;
import businesslogic.promotionbl.SpecialOfferPromotion;
import businesslogic.promotionbl.TotalGiftPromotion;
import businesslogic.utilitybl.Utility;
import dataservice.datafactoryservice.DataFactoryImpl;

public class Sale {

	public ResultMessage add(SaleVO vo) throws RemoteException {

		SalePO po = SaleVOToSalePO(vo);
		DataFactoryImpl.getInstance().getSaleDataService().insert(po);
		// 创建赠品单
		// TODO

		return ResultMessage.SUCCESS;
	}

	public ArrayList<SaleVO> findByTime(String time1, String time2)
			throws RemoteException {
		ArrayList<SalePO> poList = DataFactoryImpl.getInstance()
				.getSaleDataService().findByTime(time1, time2);
		ArrayList<SaleVO> voList = poListToVoList(poList);
		return voList;

	}

	// TODO
	public ArrayList<SaleVO> findByCommodityName(String commodityName) {

		return null;
	}

	public ArrayList<SaleVO> findByCustomer(String customer)
			throws RemoteException {
		ArrayList<SalePO> poList = DataFactoryImpl.getInstance()
				.getSaleDataService().findByCustomer(customer);
		ArrayList<SaleVO> voList = poListToVoList(poList);
		return voList;
	}

	public ArrayList<SaleVO> findBySalesman(String salesman)
			throws RemoteException {
		ArrayList<SalePO> poList = DataFactoryImpl.getInstance()
				.getSaleDataService().findBySalesman(salesman);
		ArrayList<SaleVO> voList = poListToVoList(poList);
		return voList;
	}

	public ArrayList<SaleVO> findByStorage(String Storage)
			throws RemoteException {
		ArrayList<SalePO> poList = DataFactoryImpl.getInstance()
				.getSaleDataService().findByStorage(Storage);
		ArrayList<SaleVO> voList = poListToVoList(poList);
		return voList;

	}

	public ResultMessage update(SaleVO vo) throws RemoteException {
		SalePO po = SaleVOToSalePO(vo);
		DataFactoryImpl.getInstance().getSaleDataService().insert(po);
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

	public ArrayList<SaleVO> show() throws RemoteException {

		ArrayList<SalePO> poList = DataFactoryImpl.getInstance()
				.getSaleDataService().show();
		ArrayList<SaleVO> voList = poListToVoList(poList);
		return voList;

	}
//TODO
	public ResultMessage approveSale(SaleVO vo) {
		return null;

	}

	private SaleVO SalePOToSaleVO(SalePO po) {
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
		String operatorName = "";
		//TODO
		String operatorId = po.getOperatorId();
		String storage = po.getStorage();
		ArrayList<CommodityLineItemVO> saleList = Utility.poListToVOList(po
				.getSaleList());
		ArrayList<PresentLineItemVO> giftList = Utility
				.presentPOListToVOList(po.getGiftList());
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
				salesman, operatorName, operatorId, storage, saleList, giftList,
				totalBeforeDiscount, discount, voucher, totalAfterDiscount,
				remark, approvalState, isWriteOff, receiptType);
		return vo;

	}

	private SalePO SaleVOToSalePO(SaleVO vo) {
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
		ArrayList<PresentLineItemPO> giftList = Utility
				.presentVOListToVOlist(vo.giftList);
		double totalBeforeDiscount = vo.totalBeforeDiscount;
		double discount = vo.discount;
		double voucher = vo.voucher;
		double totalAfterDiscount = vo.totalAfterDiscount;
		String remark = vo.remark;
		int approvalState = vo.approvalState.ordinal();
		boolean isWriteOff = vo.isWriteOff;
		int receiptType = vo.receiptType.ordinal();
		SalePO po = new SalePO(id, time, customerId, customerName, customerVIP,
				salesman, operatorId, storage, saleList,
				giftList, totalBeforeDiscount, discount, voucher,
				totalAfterDiscount, remark, approvalState, isWriteOff,
				receiptType);
		return po;

	}

	private ArrayList<SaleVO> poListToVoList(ArrayList<SalePO> poList) {
		ArrayList<SaleVO> voList = new ArrayList<SaleVO>();
		for (SalePO po : poList) {
			SaleVO vo = SalePOToSaleVO(po);
			voList.add(vo);
		}
		return voList;
	}

	// /////////////////////////////////////////////////////////////////////////////////////////////////////
	public ResultMessage updateCommodityBySale(
			ArrayList<CommodityLineItemVO> list) {
		MockCommodity mc = new MockCommodity();

		return mc.updateCommodityBySale(list);
	}

	public ResultMessage updateCustomerBySale(String name, double total) {
		MockCustomer ma = new MockCustomer();

		return ma.updateCustomerBySale(name, total);
	}

	public ResultMessage addlog(String content) {
		MockLog ml = new MockLog();
		return ml.addlog(content);
	}
}
