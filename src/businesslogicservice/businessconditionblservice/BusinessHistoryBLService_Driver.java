/**
 * 经营历程查看服务驱动程序
 * @author JanelDQ
 * @date 2014/10/25
 */
package businesslogicservice.businessconditionblservice;

import java.util.ArrayList;

import util.DocumentType;
import util.ResultMessage;
import vo.CashVO;
import vo.DocumentVO;
import vo.RequirementVO;

public class BusinessHistoryBLService_Driver {

	public void drive(DetailHistoryBLService businessHitoryBLService){
		
		ResultMessage result;
		
		System.out.println("经营历程查看结果：");
		ArrayList<DocumentVO> list = businessHitoryBLService.show(new RequirementVO(
				null,null,DocumentType.CASH,null,null,null,null));
		CashVO cashvo = (CashVO)list.get(0);
		System.out.println("单据编号："+cashvo.id+"；操作员："+cashvo.operator+"；银行账户："
				+cashvo.bankAccount+"；总额："+cashvo.total+"\n");
		
		System.out.println("导出Excel文件结果：");
		result = businessHitoryBLService.exportExcel("BusinessHistory.txt");
		if (result == ResultMessage.SUCCESS) System.out.println("导出成功！\n");
		else System.out.println("导出失败！\n");
		
	}
}
