/**
 * @author chengcheng
 * @date 2014/11/14
 */

package businesslogic.customerbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.CustomerPO;
import util.ResultMessage;
import vo.CustomerVO;
import businesslogic.accountbl.MockLog;
import businesslogic.logbl.Log;
import dataservice.datafactoryservice.DataFactoryImpl;

public class Customer {
    Log l=new Log();
	
    public ResultMessage createLog(String content){
		MockLog log = new MockLog(content);
		return log.add();	
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
	
	//因为审批付款单引起的更新
	public ResultMessage updatePaybles(String customerId,double total){
		try {
			CustomerPO cpo=DataFactoryImpl.getInstance().getCustomerData().getById(customerId);
			cpo.setPaybles(cpo.getPaybles()-total);
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
		result=poToVo(po);
		
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
		result=poToVo(po);
		
		l.add("Find customer by id successfully");
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
		result=poToVo(po);
		
		l.add("show successfully");
		return result;
	}
	
	//TODO
	public String createId(){
		return null;
	}
	
	public ArrayList<CustomerVO> poToVo(ArrayList<CustomerPO> po){
		ArrayList<CustomerVO> result=new ArrayList<CustomerVO>();
	
		for(int i=0;i<po.size();i++){
			CustomerPO temp=po.get(i);
			result.add(new CustomerVO(temp.getId(),temp.getCategory(),temp.getLevel(),
					temp.getName(),temp.getPhoneNumber(),temp.getAddress(),
					temp.getPostalCode(),temp.getEmail(),temp.getCreditLimit(),
					temp.getReceivables(),temp.getPaybles(),temp.getSalesman(),temp.isDeletable()));
		}
		
		return result;
	}
	
}
