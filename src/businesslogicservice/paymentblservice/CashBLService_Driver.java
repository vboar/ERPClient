/**
 * 现金费用单处理驱动程序
 * @author JanelDQ
 * @date 2014/10/25
 */
package businesslogicservice.paymentblservice;

import java.util.ArrayList;

import util.DocumentStatus;
import util.ResultMessage;
import vo.CashVO;
import vo.ClauseLineItemVO;

public class CashBLService_Driver {

	public void drive(CashBLService cashBLService){
		
		ResultMessage result;
		ArrayList<ClauseLineItemVO> clauseList = new ArrayList<ClauseLineItemVO>();
		clauseList.add(new ClauseLineItemVO("浩克",700,"无"));
		
		System.out.println("创建现金费用单返回信息：");
		result = cashBLService.create(new CashVO("XJFYD-20141024-00001","金刚狼","工商银行账户1",
				clauseList,700,DocumentStatus.PASSED));
		if (result == ResultMessage.SUCCESS) System.out.println("现金费用单创建成功！\n");
		else System.out.println("现金费用单创建失败！\n");
		
		System.out.println("更新现金费用单返回信息：");
		result = cashBLService.update(new CashVO("XJFYD-20141024-00002","钢铁侠","工商银行账户1",
				clauseList,800,DocumentStatus.PASSED));
		if (result == ResultMessage.SUCCESS) System.out.println("现金费用单更新成功！\n");
		else System.out.println("现金费用单更新失败！\n");
		
		System.out.println("根据审批状态查找现金费用单：");
		ArrayList<CashVO> list1 = cashBLService.findByStatus(DocumentStatus.PASSED);
		System.out.println("单据编号："+list1.get(0).id+"；操作员："+list1.get(0).operator+
				"；银行账户："+list1.get(0).bankAccount+"；金额："+list1.get(0).total+"\n");
		
		System.out.println("根据单据编号查找现金费用单：");
		ArrayList<CashVO> list2 = cashBLService.findById("XJFYD-20141025-00001");
		System.out.println("单据编号："+list2.get(0).id+"；操作员："+list2.get(0).operator+
				"；银行账户："+list2.get(0).bankAccount+"；金额："+list2.get(0).total+"\n");
		
		System.out.println("查看一段时间内的现金费用单：");
		ArrayList<CashVO> list3 = cashBLService.show("20141023","20141025");
		System.out.println("单据编号："+list3.get(0).id+"；操作员："+list3.get(0).operator+
				"；银行账户："+list3.get(0).bankAccount+"；金额："+list3.get(0).total+"\n");
		
	}
}
