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
	public ResultMessage add(CustomerVO vo) throws RemoteException{
		if(DataFactoryImpl.getInstance().getCustomerData().findByName(vo.name).size()==0){
			l.add("Customer Add Error:customer exists");
			return ResultMessage.EXIST;
		}else{
			//todo
			String id=createId();
			DataFactoryImpl.getInstance().getCustomerData().insert(new CustomerPO(id,
					vo.category,vo.level,vo.name,vo.phoneNumber,vo.address,
					vo.postalCode,vo.email,vo.creditLimit,vo.receivables,vo.paybles,
					vo.defaultOperator,vo.isDeletable));
		}
		
		l.add("Add customer Successfully");
		return ResultMessage.SUCCESS;
	}
	
	public ResultMessage delete(CustomerVO vo) throws RemoteException{
		if(DataFactoryImpl.getInstance().getCustomerData().findByName(vo.name).get(0).isDeletable()){
			DataFactoryImpl.getInstance().getCustomerData().delete(new CustomerPO(vo.id,vo.category,vo.level,vo.name,vo.phoneNumber,vo.address,
					vo.postalCode,vo.email,vo.creditLimit,vo.receivables,vo.paybles,
					vo.defaultOperator,vo.isDeletable));
		}else{
			//该客户不能删除
			l.add("Fail to delete customer:customer is undeletable");
			return ResultMessage.FAILED;
		}
		
		l.add("Delete customer successfully");
		return ResultMessage.SUCCESS;
	}
	
	public ResultMessage update(CustomerVO vo) throws RemoteException{
		DataFactoryImpl.getInstance().getCustomerData().update(new CustomerPO(vo.id,vo.category,vo.level,vo.name,vo.phoneNumber,vo.address,
					vo.postalCode,vo.email,vo.creditLimit,vo.receivables,vo.paybles,
					vo.defaultOperator,vo.isDeletable));
		
		l.add("Update customer successfully");
		return ResultMessage.SUCCESS;
	}
	
	public ArrayList<CustomerVO> findByname(String name) throws RemoteException{
		ArrayList<CustomerVO> result=new ArrayList<CustomerVO>();
		ArrayList<CustomerPO> po=DataFactoryImpl.getInstance().getCustomerData().findByName(name);
		result=poToVo(po);
		
		l.add("Find customer by name successfully");
		return result;
	}
	
	public ArrayList<CustomerVO> findById(String id) throws RemoteException{
		ArrayList<CustomerVO> result=new ArrayList<CustomerVO>();
		ArrayList<CustomerPO> po=DataFactoryImpl.getInstance().getCustomerData().findById(id);
		result=poToVo(po);
		
		l.add("Find customer by id successfully");
		return result;
	}
	
	public ArrayList<CustomerVO> show() throws RemoteException{
		ArrayList<CustomerVO> result=new ArrayList<CustomerVO>();
		ArrayList<CustomerPO> po=DataFactoryImpl.getInstance().getCustomerData().show();
		result=poToVo(po);
		
		l.add("show successfully");
		return result;
	}
	
	//todo
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
