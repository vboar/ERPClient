/**
 * 商品分类数据驱动程序
 * @author Vboar
 * @date 2014/10/26
 */
package dataservice.commoditydataservice;

import java.rmi.RemoteException;

import po.CategoryPO;

public class CategoryDataServiceTxtFileImpl_Driver {
	
	public void drive(CategoryDataService categoryDataService) throws RemoteException {
		System.out.println("商品分类\n");
		categoryDataService.insert(new CategoryPO());
		categoryDataService.delete(new CategoryPO());
		categoryDataService.update(new CategoryPO());
		categoryDataService.findById("00001-00001");
		categoryDataService.findByName("吊灯");
		categoryDataService.show();
	}
	
}
