/**
 * 经营情况查看服务桩程序
 * @author JanelDQ
 * @date 2014/10/25
 */
package businesslogicservice.businessconditionblservice;

import util.ResultMessage;
import vo.BusinessConditionVO;

public class BusinessConditionBLService_Driver {

	public void drive(BusinessConditionBLService businessConditionBLService){
		
		ResultMessage result;
		
		System.out.println("查询一段时间经营情况结果：");
		BusinessConditionVO vo = businessConditionBLService.show("2014/9/23", "2014/10/25");
		System.out.println("销售收入："+vo.saleIncome+"；商品报溢收入："+vo.commodityOverIncome 
				+"；成本调价收入："+vo.costAdjustIncome+"；代金券与实际差额收入："+vo.voucherIncome
				+"；折让后收入："+vo.incomeAfterDiscount+"；折让："+vo.discount+"；销售成本：" 
				+vo.saleCost+"；商品报损支出："+vo.costByLoss+"；商品赠出支出"+vo.costBySending
				+"；总支出："+vo.totalCost+"；利润："+vo.profit+"\n");
		
		System.out.println("导出Excel文件结果:");
		result = businessConditionBLService.exportExcel("BusinessCondition.txt");
		if (result == ResultMessage.SUCCESS) System.out.println("导出成功！\n");
		else System.out.println("导出失败！\n");
	}
}
