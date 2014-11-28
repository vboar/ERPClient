/**
 * @author chengcheng
 * @date 2014/11/14
 */

package businesslogic.customerbl;

import java.util.ArrayList;

import util.ResultMessage;
import vo.CustomerVO;
import businesslogicservice.customerblservice.CustomerBLService;

public class CustomerController implements CustomerBLService {
	Customer c=new Customer();
	@Override
	public ResultMessage add(CustomerVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage delete(CustomerVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage update(CustomerVO vo) {
		// TODO Auto-generated method stub
		return null;
	}
	
	//方法重载，由于收款单审批通过引起的更新
	public ResultMessage update(String customerId,double total){
		return c.update(customerId,total);
	}

	@Override
	public ArrayList<CustomerVO> findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<CustomerVO> findById(String Id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<CustomerVO> show() {
		// TODO Auto-generated method stub
		return null;
	}

}
