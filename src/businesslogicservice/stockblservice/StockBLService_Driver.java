/**
 * 库存管理业务逻辑驱动程序
 * @author Vboar
 * @date 2014/10/25
 */
package businesslogicservice.stockblservice;

import java.util.ArrayList;

import util.ResultMessage;
import vo.StockInfoVO;
import vo.StockVO;

public class StockBLService_Driver {

	public void drive(StockBLService stockBLService) {
		
		System.out.println("商品库存查看：");
		ArrayList<StockInfoVO> list = 
				stockBLService.showStockInfo("2014/10/25/16/26/01", "2014/10/25/18/26/01");
		System.out.println("商品：" + list.get(0).id + " " + list.get(0).name + " " + list.get(0).model + " "
				 + "入库/出库数量：" + list.get(0).inNumber + " " + list.get(0).outNumber + " " 
				 + "入库/出库金额：" + list.get(0).inMoney + " " + list.get(0).outMoney + "\n");
		
		System.out.println("增加库存快照信息：");
		ArrayList<StockVO> list2 = new ArrayList<StockVO>();
		list2.add(new StockVO("00001-00001", "飞利浦吊灯", "FLP01", 50, 20, "2014/10/25", "00001"));
		System.out.println("商品：" + list2.get(0).commodityId + " " + list2.get(0).commodityName + " " + 
				list2.get(0).commodityModel + " 数量：" + list2.get(0).number + " 均价：" + list2.get(0).avgPrice +
				" 批次批号：" + list2.get(0).batch + " " + list2.get(0).batchNumber + "\n");
		
		System.out.println("当天商品库存快照查看：");
		list2 = stockBLService.showCheck();
		System.out.println("商品：" + list2.get(0).commodityId + " " + list2.get(0).commodityName + " " + 
				list2.get(0).commodityModel + " 数量：" + list2.get(0).number + " 均价：" + list2.get(0).avgPrice +
				" 批次批号：" + list2.get(0).batch + " " + list2.get(0).batchNumber + "\n");
		
		System.out.println("根据批次和批号查找库存快照：");
		list2 = stockBLService.findByDate("2014/10/26", "00002");
		System.out.println("商品：" + list2.get(0).commodityId + " " + list2.get(0).commodityName + " " + 
				list2.get(0).commodityModel + " 数量：" + list2.get(0).number + " 均价：" + list2.get(0).avgPrice +
				" 批次批号：" + list2.get(0).batch + " " + list2.get(0).batchNumber + "\n");
		
		System.out.println("导出至Excel：");
		ResultMessage result = stockBLService.exportExcel("D:\test.xsl");
		if (result == ResultMessage.SUCCESS) System.out.println("导出成功！");
		else System.out.println("导出失败！");
		
	}
	
}
