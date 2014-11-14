/**
 * 账户管理驱动程序
 * @author JanelDQ
 * @date 2014/10/25
 */
package businesslogicservice.accountblservice;

import java.util.ArrayList;

import util.ResultMessage;
import vo.AccountVO;

public class AccountBLService_Driver {

	public void drive(AccountBLService accountBLService){
		
		ResultMessage result;
		System.out.println("添加账户的返回信息：");
		result = accountBLService.add(new AccountVO("工商银行账户1","6111013200067574123", 10000));
		if (result == ResultMessage.SUCCESS) System.out.println("账户添加成功！\n");
		else System.out.println("账户添加失败！\n");
		
		System.out.println("删除账户的返回信息：");
		result = accountBLService.delete(new AccountVO("工商银行账户1", "6111013200067574123", 10000));
		if (result == ResultMessage.SUCCESS) System.out.println("账户删除成功！\n");
		else System.out.println("账户删除失败！\n");
		
		System.out.println("更新账户的返回信息：");
		result = accountBLService.update(new AccountVO("工商银行账户2", "6111013200067575798", 20000));
		if (result == ResultMessage.SUCCESS) System.out.println("账户更新成功！\n");
		else System.out.println("账户更新失败！\n");
		
		System.out.println("根据名称查找账户：");
		ArrayList<AccountVO> list1 = accountBLService.find("工商银行账户1");
		System.out.println("账户名称： " + list1.get(0).name + "；银行卡号："+list1.get(0).account+
				"； 账户余额： " + list1.get(0).balance + "\n");

		System.out.println("显示所有账户：");
		ArrayList<AccountVO> list2 = accountBLService.show();
		System.out.println("账户名称： " + list2.get(0).name + "；银行卡号："+list2.get(0).account+
				"； 账户余额： " + list2.get(0).balance + "\n");
		System.out.println("账户名称： " + list2.get(1).name + "；银行卡号："+list1.get(0).account+
				"； 账户余额： " + list2.get(1).balance + "\n");
		
	}
}
