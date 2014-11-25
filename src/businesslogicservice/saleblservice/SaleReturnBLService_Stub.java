package businesslogicservice.saleblservice;

import java.util.ArrayList;

import util.DocumentStatus;
import util.DocumentType;
import util.ResultMessage;
import vo.CommodityLineItemVO;
import vo.SaleVO;

public class SaleReturnBLService_Stub implements SaleBLService{

	@Override
	public ResultMessage add(SaleVO vo) {
		if(vo.name == null)
			return ResultMessage.FAILED;
		
		return ResultMessage.SUCCESS;
	}

	@Override
	public ArrayList<SaleVO> findByTime(String time1, String time2) {
		ArrayList<CommodityLineItemVO> commodity=new ArrayList<CommodityLineItemVO>();
		commodity.add(new CommodityLineItemVO("00001-00001-00001-00001","飞利浦吊灯","FLP01",-5,-40,-200,"自提"));
	    ArrayList<SaleVO> list=new ArrayList<SaleVO>();
	    list.add(new SaleVO("XSD-20141023-00001","00001","钢铁侠",time2, 5,"美队","XS001-浩克","1",time2, time2, commodity,commodity,-350
                            ,-70,0,-280,"自提",DocumentStatus.NONCHECKED,false,DocumentType.SALE));
	 
	    return list;
	}
	
	

	@Override
	public ArrayList<SaleVO> findByCommodityName(String commodityName) {
		ArrayList<CommodityLineItemVO> commodity=new ArrayList<CommodityLineItemVO>();
		commodity.add(new CommodityLineItemVO("00001-00001-00001-00001","飞利浦吊灯","FLP01",-5,-40,-200,"自提"));
	    ArrayList<SaleVO> list=new ArrayList<SaleVO>();
	    list.add(new SaleVO("XSD-20141023-00001","00001","钢铁侠",commodityName, 5,"美队","XS001-浩克","1",commodityName, commodityName, commodity,commodity,-350
                            ,-70,0,-280,"自提",DocumentStatus.NONCHECKED,false,DocumentType.SALE));
	 
	    return list;
	}
	

	@Override
	public ArrayList<SaleVO> findByCustomer(String customer) {
		ArrayList<CommodityLineItemVO> commodity=new ArrayList<CommodityLineItemVO>();
		commodity.add(new CommodityLineItemVO("00001-00001-00001-00001","飞利浦吊灯","FLP01",-5,-40,-200,"自提"));
	    ArrayList<SaleVO> list=new ArrayList<SaleVO>();
	    list.add(new SaleVO("XSD-20141023-00001","00001","钢铁侠",customer, 5,"美队","XS001-浩克","1",customer, customer, commodity,commodity,-350
                            ,-70,0,-280,"自提",DocumentStatus.NONCHECKED,false,DocumentType.SALE));
	 
	    return list;
	}
	

	@Override
	public ArrayList<SaleVO> findBySalesman(String salesman) {
		ArrayList<CommodityLineItemVO> commodity=new ArrayList<CommodityLineItemVO>();
		commodity.add(new CommodityLineItemVO("00001-00001-00001-00001","飞利浦吊灯","FLP01",-5,-40,-200,"自提"));
	    ArrayList<SaleVO> list=new ArrayList<SaleVO>();
	    list.add(new SaleVO("XSD-20141023-00001","00001","钢铁侠",salesman, 5,"美队","XS001-浩克","1",salesman, salesman, commodity,commodity,-350
                            ,-70,0,-280,"自提",DocumentStatus.NONCHECKED,false,DocumentType.SALE));
	 
	    return list;
	}
	

	@Override
	public ArrayList<SaleVO> findByStorage(String Storage) {
		ArrayList<CommodityLineItemVO> commodity=new ArrayList<CommodityLineItemVO>();
		commodity.add(new CommodityLineItemVO("00001-00001-00001-00001","飞利浦吊灯","FLP01",-5,-40,-200,"自提"));
	    ArrayList<SaleVO> list=new ArrayList<SaleVO>();
	    list.add(new SaleVO("XSD-20141023-00001","00001","钢铁侠",Storage, 5,"美队","XS001-浩克","1",Storage, Storage, commodity,commodity,-350
                            ,-70,0,-280,"自提",DocumentStatus.NONCHECKED,false,DocumentType.SALE));
	 
	    return list;
	}
	

	@Override
	public ArrayList<SaleVO> show() {
		ArrayList<CommodityLineItemVO> commodity=new ArrayList<CommodityLineItemVO>();
		commodity.add(new CommodityLineItemVO("00001-00001-00001-00001","飞利浦吊灯","FLP01",-5,-40,-200,"自提"));
	    ArrayList<SaleVO> list=new ArrayList<SaleVO>();
	    list.add(new SaleVO("XSD-20141023-00001","00001","钢铁侠",null, 5,"美队","XS001-浩克","1",null, null, commodity,commodity,-350
                            ,-70,0,-280,"自提",DocumentStatus.NONCHECKED,false,DocumentType.SALE));
	 
	    return list;
	
	}

	@Override
	public ResultMessage updateCommodityBySale(
			ArrayList<CommodityLineItemVO> list) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public ResultMessage updateAccountBySale(String name,double total) {
		// TODO 自动生成的方法存根
		return null;
	}

}
