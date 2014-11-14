/**
 * 销售明细查看服务驱动程序
 * @author JanelDQ
 * @date 2014/10/25
 */
package businesslogicservice.businessconditionblservice;

import java.util.ArrayList;

import util.ResultMessage;
import vo.DocumentVO;
import vo.RequirementVO;

public class SaleDetailsBLService_Driver {

	public void drive(DetailHistoryBLService saleDetailsBLService){
		
		ResultMessage result;
		
		System.out.println("查看一段时间内销售明细：");
		ArrayList<DocumentVO> list = saleDetailsBLService.show(
				new RequirementVO("2014/9/23","2014/10/25","CW001", "00001", null,null));
		// TODO 具体输出list中第一项的数据
		System.out.println(list.isEmpty()+"\n");
		
		System.out.println("导出Excel文件结果：");
		result = saleDetailsBLService.exportExcel("SaleDetails.txt");
		if (result == ResultMessage.SUCCESS) System.out.println("导出成功！\n");
		else System.out.println("导出失败！\n");
		
	}
}
