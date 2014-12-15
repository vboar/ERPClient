/**
 * 销售退货类
 * @author oneoneO
 * @date 2014/11/14
 */
package businesslogic.salebl;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import po.CommodityPO;
import po.SalePO;
import util.DocumentStatus;
import util.DocumentType;
import util.ResultMessage;
import vo.CommodityLineItemVO;
import vo.PresentLineItemVO;
import vo.PresentVO;
import vo.SaleVO;
import vo.SpecialOfferVO;
import businesslogic.commoditybl.Commodity;
import businesslogic.customerbl.Customer;
import businesslogic.presentbl.Present;
import businesslogic.promotionbl.SpecialOfferPromotion;
import businesslogic.utilitybl.Utility;
import dataservice.datafactoryservice.DataFactoryImpl;

public class SaleReturn {

	Sale sale=new Sale();
	
	
	public String createId(){
		return sale.createReturnId();
	}
	
	public ResultMessage add(SaleVO vo) {	
		Date date=new Date();
		SimpleDateFormat myFmt=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String time=myFmt.format(date);
		vo.time=time;
		SalePO po = sale.SaleVOToSalePO(vo);
		try {
			DataFactoryImpl.getInstance().getSaleDataService().insert(po);
		} catch (RemoteException e) {
			
			e.printStackTrace();
		}
		 date=new Date();
		SimpleDateFormat myFmt2=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		 time=myFmt2.format(date);
		 String id=new Present().createId();

		String customerId = vo.customerId;
		String customerName = vo.customerName;
		
		ArrayList<PresentLineItemVO> list = vo.giftList;
		
		
		DocumentStatus documentStatus = DocumentStatus.NONCHECKED;
		DocumentType documentType = DocumentType.PRESENTRETURN;

		PresentVO presentVO = new PresentVO(id, time, customerId,
				customerName, list, documentStatus, documentType, false);
		Present pr = new Present();
		pr.create(presentVO);

		return ResultMessage.SUCCESS;


	}
	
	public ResultMessage update(SaleVO vo)  {
		ResultMessage rs =sale.update(vo);
		return rs;
	}
		
	
	
	/**
	 * 
	 * @author chengcheng
	 *下面这些方法是给salereturn专用的
	 * 
	 * 
	 */
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	
	
	public ArrayList<SaleVO> findByTime(String time1, String time2){
		if(time1==null||time1.equals("")){
			time1="1970/1/1 00:00:00";
		}
		if(time2==null||time2.equals("")){
			time2=Utility.getCurrentTime();
		}

		ArrayList<SalePO> poList=null;
		
		try {
			poList = DataFactoryImpl.getInstance()
					.getSaleDataService().findByTime(time1, time2);
		} catch (RemoteException e) {
			
			e.printStackTrace();
		}
		ArrayList<SalePO> poList2=new ArrayList<SalePO>();
		for(SalePO po:poList){
			if(po.getDocumentType()==5){
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
			if(po.getDocumentType()==5){
				poList2.add(po);
			}
		}
		ArrayList<SaleVO> voList = sale.poListToVoList(poList2);
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
			if(po.getDocumentType()==5){
				poList2.add(po);
			}
		}
		ArrayList<SaleVO> voList = sale.poListToVoList(poList2);
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
			if(po.getDocumentType()==5){
				poList2.add(po);
			}
		}
		ArrayList<SaleVO> voList = sale.poListToVoList(poList2);
		return voList;

	}
	
	public ArrayList<SaleVO> findByStatus(int status){
		ArrayList<SaleVO> result=new ArrayList<SaleVO>();
		ArrayList<SalePO> temp=new ArrayList<SalePO>();
		Sale s=new Sale();
		try {
			temp=DataFactoryImpl.getInstance().getSaleDataService().findByStatus(status);
		} catch (RemoteException e) {
			
			e.printStackTrace();
		}
		
		for(int i=0;i<temp.size();i++){
			if(temp.get(i).getDocumentType()==DocumentType.SALERETURN.ordinal())
			result.add(s.SalePOToSaleVO(temp.get(i)));
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
			if(po.getDocumentType()==5){
				poList2.add(po);
			}
		}
		ArrayList<SaleVO> voList = sale.poListToVoList(poList2);
		return voList;

	}
	
	//TODO
	public ResultMessage approve(SaleVO vo) {		
		double total=vo.totalAfterDiscount-vo.voucher;

		Customer cus=new Customer();
		if(total>0){
		
		//CustomerVO cusvo=new Customer().getByid(vo.customerId);
		cus.updateBySaleReturn(vo.customerId, vo.totalAfterDiscount);
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
			return ResultMessage.SUCCESS;
	}	
	
	public ResultMessage addlog(String content){
		//TODO
		return null;
	}
}
