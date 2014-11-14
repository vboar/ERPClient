package businesslogicservice.purchaseblservice;

import java.util.ArrayList;

import util.DocumentStatus;
import util.DocumentType;
import util.ResultMessage;
import vo.CommodityLineItemVO;
import vo.PurchaseVO;

public class PurchaseReturnBLService_Stub implements PurchaseBLService{

	@Override
	public ResultMessage add(PurchaseVO vo) {
		if(vo.name == null)
			return ResultMessage.FAILED;
		
		return ResultMessage.SUCCESS;
	}


	@Override
	public ArrayList<PurchaseVO> findByTime(String time1, String time2) {
		ArrayList<CommodityLineItemVO> commodity=new ArrayList<CommodityLineItemVO>();
		commodity.add(new CommodityLineItemVO("00001-00001-00001-00001","飞利浦吊灯","FLP01",50,-20,-1000,"自提"));
	    ArrayList<PurchaseVO> list=new ArrayList<PurchaseVO>();
	    list.add(new PurchaseVO("JHD-20141023-00001","00002","雷神托尔","XS001-浩克","1",commodity,-1750,
	    		"自提",DocumentStatus.NONCHECKED,false,DocumentType.SALE));
	 
	    return list;
	}

	@Override
	public ArrayList<PurchaseVO> findByCommodityName(String commodityName) {
		ArrayList<CommodityLineItemVO> commodity=new ArrayList<CommodityLineItemVO>();
		commodity.add(new CommodityLineItemVO("00001-00001-00001-00001","飞利浦吊灯","FLP01",50,-20,-1000,"自提"));
	    ArrayList<PurchaseVO> list=new ArrayList<PurchaseVO>();
	    list.add(new PurchaseVO("JHD-20141023-00001","00002","雷神托尔","XS001-浩克","1",commodity,-1750,
	    		"自提",DocumentStatus.NONCHECKED,false,DocumentType.SALE));
	 
	    return list;
	}

	@Override
	public ArrayList<PurchaseVO> findByCustomer(String customer) {
		ArrayList<CommodityLineItemVO> commodity=new ArrayList<CommodityLineItemVO>();
		commodity.add(new CommodityLineItemVO("00001-00001-00001-00001","飞利浦吊灯","FLP01",50,-20,-1000,"自提"));
	    ArrayList<PurchaseVO> list=new ArrayList<PurchaseVO>();
	    list.add(new PurchaseVO("JHD-20141023-00001","00002","雷神托尔","XS001-浩克","1",commodity,-1750,
	    		"自提",DocumentStatus.NONCHECKED,false,DocumentType.SALE));
	 
	    return list;
	}

	@Override
	public ArrayList<PurchaseVO> findBySalesman(String salesman) {
		ArrayList<CommodityLineItemVO> commodity=new ArrayList<CommodityLineItemVO>();
		commodity.add(new CommodityLineItemVO("00001-00001-00001-00001","飞利浦吊灯","FLP01",50,-20,-1000,"自提"));
	    ArrayList<PurchaseVO> list=new ArrayList<PurchaseVO>();
	    list.add(new PurchaseVO("JHD-20141023-00001","00002","雷神托尔","XS001-浩克","1",commodity,-1750,
	    		"自提",DocumentStatus.NONCHECKED,false,DocumentType.SALE));
	 
	    return list;
	}

	@Override
	public ArrayList<PurchaseVO> findByStorage(String Storage) {
		ArrayList<CommodityLineItemVO> commodity=new ArrayList<CommodityLineItemVO>();
		commodity.add(new CommodityLineItemVO("00001-00001-00001-00001","飞利浦吊灯","FLP01",50,-20,-1000,"自提"));
	    ArrayList<PurchaseVO> list=new ArrayList<PurchaseVO>();
	    list.add(new PurchaseVO("JHD-20141023-00001","00002","雷神托尔","XS001-浩克","1",commodity,-1750,
	    		"自提",DocumentStatus.NONCHECKED,false,DocumentType.SALE));
	 
	    return list;
	}


	@Override
	public ArrayList<PurchaseVO> show() {
		ArrayList<CommodityLineItemVO> commodity=new ArrayList<CommodityLineItemVO>();
		commodity.add(new CommodityLineItemVO("00001-00001-00001-00001","飞利浦吊灯","FLP01",50,-20,-1000,"自提"));
	    ArrayList<PurchaseVO> list=new ArrayList<PurchaseVO>();
	    list.add(new PurchaseVO("JHD-20141023-00001","00002","雷神托尔","XS001-浩克","1",commodity,-1750,
	    		"自提",DocumentStatus.NONCHECKED,false,DocumentType.SALE));
	 
	    return list;
	}


	@Override
	public ResultMessage updateCommodityByPurchase(
			ArrayList<CommodityLineItemVO> list) {
		// TODO 自动生成的方法存根
		return null;
	}

}
