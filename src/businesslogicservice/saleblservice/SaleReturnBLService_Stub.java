package businesslogicservice.saleblservice;

import java.util.ArrayList;

import util.ResultMessage;
import vo.CommodityLineItemVO;
import vo.SaleVO;

public class SaleReturnBLService_Stub implements SaleBLService{

	@Override
	public ResultMessage add(SaleVO vo) {
		if(vo.time == null)
			return ResultMessage.FAILED;
		
		return ResultMessage.SUCCESS;
	}

	@Override
	public ArrayList<SaleVO> findByTime(String time1, String time2) {
		ArrayList<CommodityLineItemVO> commodity=new ArrayList<CommodityLineItemVO>();
		commodity.add(new CommodityLineItemVO("00001-00001-00001-00001","飞利浦吊灯","FLP01",-5,-40,-200,"自提"));
	    ArrayList<SaleVO> list=new ArrayList<SaleVO>();
	    list.add(new SaleVO("XSD-20141023-00001",5));
	 
	    return list;
	}
	
	

	@Override
	public ArrayList<SaleVO> findByCommodityName(String commodityName) {
		ArrayList<CommodityLineItemVO> commodity=new ArrayList<CommodityLineItemVO>();
		commodity.add(new CommodityLineItemVO("00001-00001-00001-00001","飞利浦吊灯","FLP01",-5,-40,-200,"自提"));
	    ArrayList<SaleVO> list=new ArrayList<SaleVO>();
	    list.add(new SaleVO("XSD-20141023-00001",5));
	 
	    return list;
	}
	

	@Override
	public ArrayList<SaleVO> findByCustomer(String customer) {
		ArrayList<CommodityLineItemVO> commodity=new ArrayList<CommodityLineItemVO>();
		commodity.add(new CommodityLineItemVO("00001-00001-00001-00001","飞利浦吊灯","FLP01",-5,-40,-200,"自提"));
	    ArrayList<SaleVO> list=new ArrayList<SaleVO>();
	    list.add(new SaleVO("XSD-20141023-00001",5));
	 
	    return list;
	}
	

	@Override
	public ArrayList<SaleVO> findBySalesman(String salesman) {
		ArrayList<CommodityLineItemVO> commodity=new ArrayList<CommodityLineItemVO>();
		commodity.add(new CommodityLineItemVO("00001-00001-00001-00001","飞利浦吊灯","FLP01",-5,-40,-200,"自提"));
	    ArrayList<SaleVO> list=new ArrayList<SaleVO>();
	    list.add(new SaleVO("XSD-20141023-00001",5));
	 
	    return list;
	}
	

	@Override
	public ArrayList<SaleVO> findByStorage(String Storage) {
		ArrayList<CommodityLineItemVO> commodity=new ArrayList<CommodityLineItemVO>();
		commodity.add(new CommodityLineItemVO("00001-00001-00001-00001","飞利浦吊灯","FLP01",-5,-40,-200,"自提"));
	    ArrayList<SaleVO> list=new ArrayList<SaleVO>();
	    list.add(new SaleVO("XSD-20141023-00001",5));
	 
	    return list;
	}
	

	@Override
	public ArrayList<SaleVO> show() {
		ArrayList<CommodityLineItemVO> commodity=new ArrayList<CommodityLineItemVO>();
		commodity.add(new CommodityLineItemVO("00001-00001-00001-00001","飞利浦吊灯","FLP01",-5,-40,-200,"自提"));
	    ArrayList<SaleVO> list=new ArrayList<SaleVO>();
	    list.add(new SaleVO("XSD-20141023-00001",5));
	 
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
