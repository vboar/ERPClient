/**
 * 客户管理逻辑驱动
 * date 2014/10/25
 * @author chengcheng
 */
package businesslogicservice.customerblservice;

import java.util.ArrayList;

import util.ResultMessage;
import vo.CustomerVO;

public class CustomerBLService_Driver {
	public void drive(CustomerBLService customerBLService){
		CustomerVO vo=new CustomerVO("00001", 0, 1, "金刚狼", "12345678901", 
				"美国加利福尼州", "210000", "Ironman@gmail.com", 10000, 20000, 0, "美队", false);
		
		ResultMessage result;
		result=customerBLService.add(vo);
		if(result==ResultMessage.SUCCESS)
		System.out.println("添加成功");
		
		result=customerBLService.delete(vo);
		if(result==ResultMessage.SUCCESS)
			System.out.println("删除成功");
		
		result=customerBLService.update(vo);
		if(result==ResultMessage.SUCCESS)
			System.out.println("更新成功");
		
		ArrayList<CustomerVO> voList=customerBLService.findById("00001");
		System.out.println(voList.get(0).name);
		
		ArrayList<CustomerVO> voList1=customerBLService.findByName("金刚狼");
		System.out.println(voList1.get(0).id);
		
		voList=customerBLService.show();
		System.out.println(voList.get(0).name);
		
	}

}
