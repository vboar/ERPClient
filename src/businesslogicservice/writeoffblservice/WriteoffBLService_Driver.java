/**
 * 红冲服务驱动程序
 * @author JanelDQ
 * @date 2014/10/25
 */
package businesslogicservice.writeoffblservice;

import java.util.ArrayList;

import util.DocumentStatus;
import util.ResultMessage;
import vo.CashVO;
import vo.ClauseLineItemVO;

public class WriteoffBLService_Driver {

	public void drive(WriteoffBLService writeoffBLService){
		
		ResultMessage result;
		
		ArrayList<ClauseLineItemVO> clauseList = new ArrayList<ClauseLineItemVO>();
		clauseList.add(new ClauseLineItemVO("浩克",700,"无"));
		
		System.out.println("红冲操作返回信息");
		result = writeoffBLService.create(new CashVO("XJFYD-20141025-00001","金刚狼","工商银行账户1",
				clauseList,700,DocumentStatus.PASSED));
		if (result == ResultMessage.SUCCESS) System.out.println("红冲操作成功！\n");
		else System.out.println("红冲操作失败失败！\n");
		
		System.out.println("红冲并复制操作返回信息");
		result = writeoffBLService.copy(new CashVO("XJFYD-20141025-00002","金刚狼","工商银行账户1",
				clauseList,800,DocumentStatus.PASSED));
		if (result == ResultMessage.SUCCESS) System.out.println("红冲操作成功！\n");
		else System.out.println("红冲并复制操作失败失败！\n");
		
	}
}
