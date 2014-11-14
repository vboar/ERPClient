/**
 * 期初建账业务逻辑驱动程序
 * @author Vboar
 * @date 2014/10/26
 */
package businesslogicservice.initialblservice;

import java.util.ArrayList;

import util.ResultMessage;
import vo.AccountVO;
import vo.CommodityVO;
import vo.CustomerVO;
import vo.InitialVO;

public class InitialBLService_Driver {

	public void drive(InitialBLService initialBLService) {
		
		System.out.println("期初建账：\n");
		
		ArrayList<InitialVO> list = initialBLService.findById("INI-2014");
		System.out.println("查找的账本信息" + list.get(0).id + " " + list.get(0).name);
		
		list = initialBLService.show();
		System.out.println("第一条账本信息" + list.get(0).id + " " + list.get(0).name);
		
		ResultMessage result = initialBLService.initialAccount(new InitialVO("INI_2014", "2014期初"));
		if (result == ResultMessage.SUCCESS) System.out.println("期初建账成功！\n");
		else System.out.println("期初建账失败！\n");
		
		ArrayList<CommodityVO> list1 = initialBLService.showCommodity(new InitialVO("INI_2014", "2014期初"));
		System.out.println("第一个商品：" + list1.get(0).name + " " + list1.get(0).model);
		
		ArrayList<CustomerVO> list2 = initialBLService.showCustomer(new InitialVO("INI_2014", "2014期初"));
		System.out.println("第一个客户：" + list2.get(0).name + " " + list2.get(0).email);
		
		ArrayList<AccountVO> list3 = initialBLService.showAccount(new InitialVO("INI_2014", "2014期初"));
		System.out.println("第一个银行账户：" + list3.get(0).name + " " + list3.get(0).balance);
		
		
	}
	
}
