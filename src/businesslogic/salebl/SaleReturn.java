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

import businesslogic.presentbl.Present;
import po.SalePO;
import dataservice.datafactoryservice.DataFactoryImpl;
import util.DocumentStatus;
import util.DocumentType;
import util.ResultMessage;
import vo.CommodityLineItemVO;
import vo.PresentLineItemVO;
import vo.PresentVO;
import vo.SaleVO;

public class SaleReturn {

	Sale sale=new Sale();
	
	
	public ResultMessage add(SaleVO vo) {
		ArrayList<CommodityLineItemVO> voListTemp=vo.saleList;
		for(CommodityLineItemVO covotemp:voListTemp){
			covotemp.number=0-covotemp.number;
		}
		vo.saleList=voListTemp;
		
		SalePO po = sale.SaleVOToSalePO(vo);
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
		for(PresentLineItemVO votemp :list){
			votemp.number=0-votemp.number;
		}
		
		DocumentStatus documentStatus = DocumentStatus.NONCHECKED;
		DocumentType documentType = DocumentType.PRESENT;

		PresentVO presentVO = new PresentVO(null, time, customerId,
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
		return null;

	}

	
	
	
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////

	
	
	
	
	
	
	
	
	
	
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	public ResultMessage updateCommodityBySale(
			ArrayList<CommodityLineItemVO> list) {
		MockCommodity mc=new MockCommodity();
	
		return mc.updateCommodityBySale(list);
	}


	public ResultMessage updateCustomerBySale(String name,double total) {
		MockCustomer ma=new MockCustomer();
		return ma.updateCustomerBySale(name,total);
	}

	public ResultMessage addlog(String content){
		MockLog ml=new MockLog();
		
		return ml.addlog(content);
	}
}
