/**
 * @author chengcheng
 * @date 2014/11/14
 */

package businesslogic.customerbl;

import businesslogic.logbl.Log;
import dataservice.datafactoryservice.DataFactoryImpl;
import po.CustomerPO;
import util.ResultMessage;
import vo.CustomerVO;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class Customer {
    Log l=new Log();
	
    public ResultMessage createLog(String content){
		//TODO
		return null;	
	}
    
	//客户名唯一
	public ResultMessage add(CustomerVO vo) {
		try {
			if(DataFactoryImpl.getInstance().getCustomerData().findByName(vo.name).size()==0){
				l.add("Customer Add Error:customer exists");
				return ResultMessage.EXIST;
			}else{
				//TODO
				String id=createId();
				DataFactoryImpl.getInstance().getCustomerData().insert(new CustomerPO(id,
						vo.category,vo.level,vo.name,vo.phoneNumber,vo.address,
						vo.postalCode,vo.email,vo.creditLimit,vo.receivables,vo.paybles,
						vo.salesman,vo.isDeletable));
			}
		} catch (RemoteException e) {
			
			e.printStackTrace();
		}
		
		l.add("Add customer Successfully");
		return ResultMessage.SUCCESS;
	}
	
	public ResultMessage delete(CustomerVO vo) {
		try {
			if(DataFactoryImpl.getInstance().getCustomerData().findByName(vo.name).get(0).isDeletable()){
				DataFactoryImpl.getInstance().getCustomerData().delete(new CustomerPO(vo.id,vo.category,vo.level,vo.name,vo.phoneNumber,vo.address,
						vo.postalCode,vo.email,vo.creditLimit,vo.receivables,vo.paybles,
						vo.salesman,vo.isDeletable));
			}else{
				//该客户不能删除
				l.add("Fail to delete customer:customer is undeletable");
				return ResultMessage.FAILED;
			}
		} catch (RemoteException e) {
			
			e.printStackTrace();
		}
		
		l.add("Delete customer successfully");
		return ResultMessage.SUCCESS;
	}
	
	public ResultMessage update(CustomerVO vo) {
		//chencheng change
		CustomerPO po=null;
		try {
			po = DataFactoryImpl.getInstance().getCustomerData().getById(vo.id);
		} catch (RemoteException e) {
			
			e.printStackTrace();
		}
		if(po==null){
			return ResultMessage.NOT_FOUND;
		}
		
		try {
			DataFactoryImpl.getInstance().getCustomerData().update(new CustomerPO(vo.id,vo.category,vo.level,vo.name,vo.phoneNumber,vo.address,
						vo.postalCode,vo.email,vo.creditLimit,po.getReceivables(),po.getPaybles(),
						vo.salesman,po.isDeletable()));
		} catch (RemoteException e) {
			
			e.printStackTrace();
		}
		
		l.add("Update customer successfully");
		return ResultMessage.SUCCESS;
	}
	
	//因为审批付款单引起的应收减少
	public ResultMessage updateByPayment(String customerId,double total){
		try {
			CustomerPO cpo=DataFactoryImpl.getInstance().getCustomerData().getById(customerId);
			cpo.setReceivables(cpo.getReceivables()-total);
			DataFactoryImpl.getInstance().getCustomerData().update(cpo);
		} catch (RemoteException e) {		
			e.printStackTrace();
		}
		
		return ResultMessage.SUCCESS;
	}
	
	//付款单通过审批，应付减少
	public ResultMessage updateByReceipt(String customerId,double total){
		try {
			CustomerPO cpo=DataFactoryImpl.getInstance().getCustomerData().getById(customerId);
			cpo.setPaybles(cpo.getPaybles()-total);
			DataFactoryImpl.getInstance().getCustomerData().update(cpo);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		return ResultMessage.SUCCESS;
	}
	
	//销售单通过审批，客户应付增加
	public ResultMessage updateBySale(String customerId,double total){
		try {
			CustomerPO cpo=DataFactoryImpl.getInstance().getCustomerData().getById(customerId);
			cpo.setPaybles(cpo.getPaybles()+total);
			DataFactoryImpl.getInstance().getCustomerData().update(cpo);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		return ResultMessage.SUCCESS;
	}
	
	// 销售退货单通过审批，客户应付减少
	public ResultMessage updateBySaleReturn(String customerId,double total){
		try {
			CustomerPO cpo=DataFactoryImpl.getInstance().getCustomerData().getById(customerId);
			cpo.setPaybles(cpo.getPaybles()-total);
			DataFactoryImpl.getInstance().getCustomerData().update(cpo);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		return ResultMessage.SUCCESS;
	}
	
	//进货单通过审批，客户应收增加
	public ResultMessage updateByPurchase(String customerId,double total){
		try {
			CustomerPO cpo=DataFactoryImpl.getInstance().getCustomerData().getById(customerId);
			cpo.setReceivables(cpo.getReceivables()+total);
			DataFactoryImpl.getInstance().getCustomerData().update(cpo);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		return ResultMessage.SUCCESS;
	}
	
	//进货退货单通过审批,客户应收减少
	public ResultMessage updateByPurchaseReturn(String customerId,double total){
		try {
			CustomerPO cpo=DataFactoryImpl.getInstance().getCustomerData().getById(customerId);
			cpo.setReceivables(cpo.getReceivables()-total);
			DataFactoryImpl.getInstance().getCustomerData().update(cpo);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		return ResultMessage.SUCCESS;
	}
	
	public ArrayList<CustomerVO> findByname(String name) {
		ArrayList<CustomerVO> result=new ArrayList<CustomerVO>();
		ArrayList<CustomerPO> po=null;
		try {
			po = DataFactoryImpl.getInstance().getCustomerData().findByName(name);
		} catch (RemoteException e) {
			
			e.printStackTrace();
		}
		
		for(int i=0;i<po.size();i++){
			result.add(poToVo(po.get(i)));
		}
		
		l.add("Find customer by name successfully");
		return result;
	}
	
	public ArrayList<CustomerVO> findById(String id) {
		ArrayList<CustomerVO> result=new ArrayList<CustomerVO>();
		ArrayList<CustomerPO> po=null;
		try {
			po = DataFactoryImpl.getInstance().getCustomerData().findById(id);
		} catch (RemoteException e) {
			
			e.printStackTrace();
		}
		
		for(int i=0;i<po.size();i++){
			result.add(poToVo(po.get(i)));
		}
		
		l.add("Find customer by id successfully");
		return result;
	}
	
	public ArrayList<CustomerVO> fuzzyFind(String keyword){
		ArrayList<CustomerVO> result=new ArrayList<CustomerVO>();
		ArrayList<CustomerPO> temp = null;
		try {
			temp = DataFactoryImpl.getInstance().getCustomerData().show();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		for(int i=0;i<temp.size();i++){
			CustomerPO t=temp.get(i);
			String allkeywords=t.getId()+";"+t.getCategory()+";"+String.valueOf(t.getLevel())+";"
			                   +t.getName()+";"+t.getPhoneNumber()+";"+t.getAddress()+";"
					           +t.getPostalCode()+";"+t.getEmail()+";"+String.valueOf(t.getCreditLimit())+";"
			                   +String.valueOf(t.getReceivables())+";"+String.valueOf(t.getPaybles())+";"
			                   +t.getSalesman();
			if(allkeywords.contains(keyword))
				result.add(poToVo(t));
		}
		
		return result;
	}
	
	public ArrayList<CustomerVO> show() {
		ArrayList<CustomerVO> result=new ArrayList<CustomerVO>();
		ArrayList<CustomerPO> po=null;
		try {
			po = DataFactoryImpl.getInstance().getCustomerData().show();
		} catch (RemoteException e) {
			
			e.printStackTrace();
		}
		
		for(int i=0;i<po.size();i++){
			result.add(poToVo(po.get(i)));
		}
		
		l.add("show successfully");
		return result;
	}
	
	//TODO
	public String createId(){
		return null;
	}
	
	public CustomerVO poToVo(CustomerPO po){
			CustomerVO result=new CustomerVO(po.getId(),po.getCategory(),po.getLevel(),
					po.getName(),po.getPhoneNumber(),po.getAddress(),
					po.getPostalCode(),po.getEmail(),po.getCreditLimit(),
					po.getReceivables(),po.getPaybles(),po.getSalesman(),po.isDeletable());
		
		return result;
	}
	
}
