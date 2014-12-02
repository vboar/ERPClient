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

import po.SalePO;
import util.DocumentStatus;
import util.DocumentType;
import util.ResultMessage;
import vo.CommodityLineItemVO;
import vo.PresentLineItemVO;
import vo.PresentVO;
import vo.SaleVO;
import businesslogic.presentbl.Present;
import dataservice.datafactoryservice.DataFactoryImpl;

public class SaleReturn {

	Sale sale=new Sale();
	
	
	public ResultMessage add(SaleVO vo) {	
		Date date=new Date();
		SimpleDateFormat myFmt=new SimpleDateFormat("yyyy/MM/dd/HH/mm/ss");
		String time=myFmt.format(date);
		vo.time=time;
		SalePO po = sale.SaleVOToSalePO(vo);
		try {
			DataFactoryImpl.getInstance().getSaleDataService().insert(po);
		} catch (RemoteException e) {
			
			e.printStackTrace();
		}
		 date=new Date();
		SimpleDateFormat myFmt2=new SimpleDateFormat("yyyy/MM/dd/HH/mm/ss");
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
		return null;

	}	
	
	public ResultMessage addlog(String content){
		//TODO
		return null;
	}
}
