/**
 * 商品管理业务逻辑驱动程序
 * @author Vboar
 * @date 2014/10/25
 */
package businesslogicservice.commodityblservice;

import java.util.ArrayList;

import util.ResultMessage;
import vo.CommodityVO;

public class CommodityBLService_Driver {

	public void drive(CommodityBLService commodityBLService) {
		
		ResultMessage result;
		
		System.out.println("添加商品的返回信息：");
		result = commodityBLService.add(
				new CommodityVO("00001-00001-00001", "飞利浦吊灯", "FLP01", 233, 20 ,40 ,0 ,0, 50, false,null));
		if (result == ResultMessage.SUCCESS) System.out.println("商品添加成功！\n");
		else System.out.println("商品添加失败！\n");
		
		System.out.println("删除商品的返回信息：");
		result = commodityBLService.delete(
				new CommodityVO("00001-00001-00001", "飞利浦吊灯", "FLP01", 233, 20 ,40 ,0 ,0, 50, false,null));
		if (result == ResultMessage.SUCCESS) System.out.println("商品删除成功！\n");
		else System.out.println("商品删除失败！\n");
		
		System.out.println("更新商品的返回信息：");
		result = commodityBLService.update(
				new CommodityVO("00001-00001-00001", "飞利浦吊灯", "FLP01", 233, 20 ,40 ,10 ,20, 50, true,null));
		if (result == ResultMessage.SUCCESS) System.out.println("商品更新成功！\n");
		else System.out.println("商品更新失败！\n");
		
		System.out.println("根据编号查找商品：");
		ArrayList<CommodityVO> list = commodityBLService.findById("00001-00001-00002");
		System.out.println("商品编号： " + list.get(0).id + "； 名称： " + list.get(0).name + 
				"； 型号： " + list.get(0).model + "\n");
		
		System.out.println("根据名称查找商品：");
		ArrayList<CommodityVO> list2 = commodityBLService.findByName("飞利浦日光灯");
		System.out.println("商品编号： " + list2.get(0).id + "； 名称： " + list2.get(0).name + 
				"； 型号： " + list2.get(0).model + "\n");
		
		System.out.println("根据型号查找商品：");
		ArrayList<CommodityVO> list3 = commodityBLService.findByModel("FLP02");
		System.out.println("商品编号： " + list3.get(0).id + "； 名称： " + list3.get(0).name + 
				"； 型号： " + list3.get(0).model + "\n");
		
		System.out.println("显示所有商品：");
		ArrayList<CommodityVO> list4 = commodityBLService.show();
		System.out.println("商品编号： " + list4.get(0).id + "； 名称： " + list4.get(0).name + 
				"； 型号： " + list4.get(0).model + "\n");
	}
}
