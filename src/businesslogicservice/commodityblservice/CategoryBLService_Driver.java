/**
 * 商品分类管理业务逻辑驱动程序
 * @author Vboar
 * @date 2014/10/25
 */
package businesslogicservice.commodityblservice;

import java.util.ArrayList;

import util.ResultMessage;
import vo.CategoryVO;

public class CategoryBLService_Driver {
	
	public void drive(CategoryBLService categoryBLService) {
		
		ResultMessage result;
		
		System.out.println("添加商品分类的返回信息：");
		result = categoryBLService.add(new CategoryVO("日光灯", 0,null));
		if (result == ResultMessage.SUCCESS) System.out.println("商品分类添加成功！\n");
		else System.out.println("商品分类添加失败！\n");
		
		System.out.println("删除商品分类的返回信息：");
		result = categoryBLService.delete(new CategoryVO("吊灯", 2,null));
		if (result == ResultMessage.SUCCESS) System.out.println("商品分类删除成功！\n");
		else System.out.println("商品分类删除失败！\n");
		
		System.out.println("更新商品分类的返回信息：");
		result = categoryBLService.update(new CategoryVO("大吊灯", 2,null));
		if (result == ResultMessage.SUCCESS) System.out.println("商品分类更新成功！\n");
		else System.out.println("商品分类更新失败！\n");
		
		System.out.println("根据编号查找分类：");
		ArrayList<CategoryVO> list = categoryBLService.findById("00001-00001");
		System.out.println("分类编号： " + list.get(0).id + "； 分类名称： " + list.get(0).name + "\n");
		
		System.out.println("根据名称查找分类：");
		ArrayList<CategoryVO> list2 = categoryBLService.findByName("吊灯");
		System.out.println("分类编号： " + list2.get(0).id + "； 分类名称： " + list2.get(0).name + "\n");
		
		System.out.println("显示所有分类：");
		ArrayList<CategoryVO> list3 = categoryBLService.show();
		System.out.println("分类编号： " + list3.get(0).id + "； 分类名称： " + list3.get(0).name + "\n");
		
	}

}
