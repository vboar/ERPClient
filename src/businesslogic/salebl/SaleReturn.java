/**
 * 销售退货类
 * @author oneoneO
 * @date 2014/11/14
 */
package businesslogic.salebl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.CommodityPO;
import po.SalePO;
import util.DocumentStatus;
import util.DocumentType;
import util.ResultMessage;
import util.Time;
import vo.CommodityLineItemVO;
import vo.PresentLineItemVO;
import vo.PresentVO;
import vo.SaleVO;
import vo.SpecialOfferVO;
import businesslogic.commoditybl.Commodity;
import businesslogic.customerbl.Customer;
import businesslogic.presentbl.Present;
import businesslogic.promotionbl.SpecialOfferPromotion;
import dataservice.datafactoryservice.DataFactoryImpl;

public class SaleReturn {

	Sale sale = new Sale();

	public String createId() {
		return sale.createReturnId();
	}

	public ResultMessage add(SaleVO vo) {
		
		// 检查能否退货
		SaleVO svo = sale.getById(vo.saleId);
		if(svo==null||(svo.canReturn==false&&!vo.isWriteOff)){
			return ResultMessage.FAILED;
		}else{
			svo.canReturn = false;
			svo.canWriteOff = false;
			sale.update(svo);
		}
		String time = Time.getCurrentTime();
		vo.time = time;
		vo.presentId=" ";
				
		String customerId = vo.customerId;
		String customerName = vo.customerName;

		ArrayList<PresentLineItemVO> list = vo.giftList;

		DocumentStatus documentStatus = DocumentStatus.NONCHECKED;
		DocumentType documentType = DocumentType.PRESENTRETURN;
		if(list!=null&&!list.isEmpty()){

		String id = new Present().createId();
		PresentVO presentVO = new PresentVO(id, time, customerId, customerName,
				list, documentStatus, documentType, false, false);
		Present pr = new Present();
		pr.create(presentVO);
		vo.presentId=id;
		}
		
		SalePO po = sale.SaleVOToSalePO(vo);

		try {
			DataFactoryImpl.getInstance().getSaleData().insert(po);
		} catch (RemoteException e) {

			e.printStackTrace();
		}

		return ResultMessage.SUCCESS;

	}

	public ResultMessage update(SaleVO vo) {
		ResultMessage rs = sale.update(vo);
		return rs;
	}

	/**
	 * 
	 * @author chengcheng 下面这些方法是给salereturn专用的
	 * 
	 * 
	 */
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	public ArrayList<SaleVO> findByTime(String time1, String time2) {
		time1=Time.jdugeTime1(time1);
		time2=Time.jdugeTime2(time2);

		ArrayList<SalePO> poList = null;

		try {
			poList = DataFactoryImpl.getInstance().getSaleData()
					.findByTime(time1, time2);
		} catch (RemoteException e) {

			e.printStackTrace();
		}
		ArrayList<SalePO> poList2 = new ArrayList<SalePO>();
		for (SalePO po : poList) {
			if (po.getDocumentType() == 5) {
				poList2.add(po);
			}
		}
		ArrayList<SaleVO> voList = sale.poListToVoList(poList2);
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

	public ArrayList<SaleVO> findByCustomer(String customer) {
		ArrayList<SalePO> poList = null;
		try {
			poList = DataFactoryImpl.getInstance().getSaleData()
					.findByCustomer(customer);
		} catch (RemoteException e) {

			e.printStackTrace();
		}
		ArrayList<SalePO> poList2 = new ArrayList<SalePO>();
		for (SalePO po : poList) {
			if (po.getDocumentType() == 5) {
				poList2.add(po);
			}
		}
		ArrayList<SaleVO> voList = sale.poListToVoList(poList2);
		return voList;
	}

	public ArrayList<SaleVO> findBySalesman(String salesman) {
		ArrayList<SalePO> poList = null;
		try {
			poList = DataFactoryImpl.getInstance().getSaleData()
					.findBySalesman(salesman);
		} catch (RemoteException e) {

			e.printStackTrace();
		}

		ArrayList<SalePO> poList2 = new ArrayList<SalePO>();
		for (SalePO po : poList) {
			if (po.getDocumentType() == 5) {
				poList2.add(po);
			}
		}
		ArrayList<SaleVO> voList = sale.poListToVoList(poList2);
		return voList;
	}

	public ArrayList<SaleVO> findByStorage(String Storage) {
		ArrayList<SalePO> poList = null;
		try {
			poList = DataFactoryImpl.getInstance().getSaleData()
					.findByStorage(Storage);
		} catch (RemoteException e) {

			e.printStackTrace();
		}

		ArrayList<SalePO> poList2 = new ArrayList<SalePO>();
		for (SalePO po : poList) {
			if (po.getDocumentType() == 5) {
				poList2.add(po);
			}
		}
		ArrayList<SaleVO> voList = sale.poListToVoList(poList2);
		return voList;

	}

	public ArrayList<SaleVO> findByStatus(int status) {
		ArrayList<SaleVO> result = new ArrayList<SaleVO>();
		ArrayList<SalePO> temp = new ArrayList<SalePO>();
		Sale s = new Sale();
		try {
			temp = DataFactoryImpl.getInstance().getSaleData()
					.findByStatus(status);
		} catch (RemoteException e) {

			e.printStackTrace();
		}

		for (int i = 0; i < temp.size(); i++) {
			if (temp.get(i).getDocumentType() == DocumentType.SALERETURN
					.ordinal())
				result.add(s.SalePOToSaleVO(temp.get(i)));
		}

		return result;
	}

	public ArrayList<SaleVO> show() {

		ArrayList<SalePO> poList = null;
		try {
			poList = DataFactoryImpl.getInstance().getSaleData().show();
		} catch (RemoteException e) {

			e.printStackTrace();
		}

		ArrayList<SalePO> poList2 = new ArrayList<SalePO>();
		for (SalePO po : poList) {
			if (po.getDocumentType() == 5) {
				poList2.add(po);
			}
		}
		ArrayList<SaleVO> voList = sale.poListToVoList(poList2);
		return voList;

	}

	// TODO
	public ResultMessage approve(SaleVO vo) {
		double total = vo.totalAfterDiscount - vo.voucher;

		Customer cus = new Customer();
		if (total > 0) {

			// CustomerVO cusvo=new Customer().getByid(vo.customerId);
			cus.updateBySaleReturn(vo.customerId, total);
		}

		for (CommodityLineItemVO vo1 : vo.saleList) {

			Commodity commodity = new Commodity();
			String id = vo1.id;
			if (id.compareTo("99998") > 0) {
				SpecialOfferVO spevo = new SpecialOfferPromotion().getById(id
						.substring(6));
				ArrayList<CommodityLineItemVO> spList = spevo.commodityList;
				for (CommodityLineItemVO commodityLineItemvo : spList) {
					CommodityPO commoditypo = commodity
							.getById(commodityLineItemvo.id);
					commoditypo.setNumber(commoditypo.getNumber() + vo1.number);
					try {
						DataFactoryImpl.getInstance().getCommodityData()
								.update(commoditypo);
					} catch (RemoteException e) {
						e.printStackTrace();
					}
				}
				continue;
			}

			CommodityPO commoditypo = commodity.getById(vo1.id);
			commoditypo.setNumber(commoditypo.getNumber() + vo1.number);
			// commoditypo.setRecentSalePrice(vo1.price);
			try {
				DataFactoryImpl.getInstance().getCommodityData()
						.update(commoditypo);
			} catch (RemoteException e) {
				e.printStackTrace();
			}

		}
		String id=vo.presentId;
		if(id!=null&&id.length()>5){
			PresentVO prvo=new Present().getById(id);
			new Present().approve(prvo);
			prvo.documentStatus=DocumentStatus.PASSED;
			new Present().update(prvo);
		}
		return ResultMessage.SUCCESS;
	}

	public void writeoff(SaleVO vo) {
		double total = vo.totalAfterDiscount - vo.voucher;

		Customer cus = new Customer();
		if (total > 0) {
			// CustomerVO cusvo=new Customer().getByid(vo.customerId);
			cus.updateBySaleReturn(vo.customerId, 0-total);
		}

		for (CommodityLineItemVO vo1 : vo.saleList) {

			Commodity commodity = new Commodity();
			String id = vo1.id;
			if (id.compareTo("99998") > 0) {
				SpecialOfferVO spevo = new SpecialOfferPromotion().getById(id
						.substring(6));
				ArrayList<CommodityLineItemVO> spList = spevo.commodityList;
				for (CommodityLineItemVO commodityLineItemvo : spList) {
					CommodityPO commoditypo = commodity
							.getById(commodityLineItemvo.id);
					commoditypo.setNumber(commoditypo.getNumber() - vo1.number);
					try {
						DataFactoryImpl.getInstance().getCommodityData()
								.update(commoditypo);
					} catch (RemoteException e) {
						e.printStackTrace();
					}
				}
				continue;
			}

			CommodityPO commoditypo = commodity.getById(vo1.id);
			commoditypo.setNumber(commoditypo.getNumber() - vo1.number);
			// commoditypo.setRecentSalePrice(vo1.price);
			try {
				DataFactoryImpl.getInstance().getCommodityData()
						.update(commoditypo);
			} catch (RemoteException e) {
				e.printStackTrace();
			}

		}

	}
}
