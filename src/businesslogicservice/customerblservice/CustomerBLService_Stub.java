/**
 * 客户管理逻辑桩
 * date 2014/10/25
 * @author chengcheng
 */
package businesslogicservice.customerblservice;

import java.util.ArrayList;

import util.ResultMessage;
import vo.CustomerVO;

public class CustomerBLService_Stub implements CustomerBLService {

	@Override
	public ResultMessage add(CustomerVO vo) {
		
		return ResultMessage.SUCCESS;
	}

	@Override
	public ResultMessage delete(CustomerVO vo) {
		
		return ResultMessage.SUCCESS;
	}

	@Override
	public ResultMessage update(CustomerVO vo) {
		
		return ResultMessage.SUCCESS;
	}

	@Override
	public ArrayList<CustomerVO> findByName(String name) {
		ArrayList<CustomerVO> customerList=new ArrayList<CustomerVO>();
		customerList.add(new CustomerVO("00001", 0, 1, name, "12345678901", 
				"美国加利福尼州", "210000", "Ironman@gmail.com", 10000, 20000, 0, "美队", false));
		return customerList;
	}

	@Override
	public ArrayList<CustomerVO> findById(String id) {
		ArrayList<CustomerVO> customerList=new ArrayList<CustomerVO>();
		customerList.add(new CustomerVO(id, 0, 1, "金刚狼", "12345678901", 
				"美国加利福尼州", "210000", "Ironman@gmail.com", 10000, 20000, 0, "美队", false));
		return customerList;
	}

	@Override
	public ArrayList<CustomerVO> show() {
		ArrayList<CustomerVO> customerList=new ArrayList<CustomerVO>();
		customerList.add(new CustomerVO("00001", 0, 1, "金刚狼", "12345678901", 
				"美国加利福尼州", "210000", "Ironman@gmail.com", 10000, 20000, 0, "美队", false));
		return customerList;
	}

}
