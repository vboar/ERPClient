/**
 * category逻辑类
 * @author Vboar
 * @date 2014/11/12
 */

package businesslogic.commoditybl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.CategoryPO;
import util.ResultMessage;
import vo.CategoryVO;
import businesslogic.utilitybl.Utility;
import dataservice.datafactoryservice.DataFactoryImpl;

public class Category {

	public ResultMessage createLog(String content) {
		MockLog ml = new MockLog(content);
		return ml.create();
	}
	
	public CategoryPO CategoryVOToCategoryPO(CategoryVO vo){
		String id="";
		//todo
		String name=vo.name;
		int number=vo.number;
		CategoryPO po=new CategoryPO(id,name,number);
		return po;
	}
	
	
	public ResultMessage add(CategoryVO vo) throws RemoteException{
		CategoryPO po=CategoryVOToCategoryPO(vo);
		CategoryVO father=vo.father;
		if(existPO(po.getId())){
			return ResultMessage.EXIST;
		}
		ResultMessage nameCheck = Utility.checkInputValid(po.getName(), 2, 14,
				true);
		if (nameCheck != ResultMessage.SUCCESS) {
			return nameCheck;
		}
		father.number++;
		update(father);
		DataFactoryImpl.getInstance().getCategoryData().insert(po);
		return ResultMessage.SUCCESS;
	}
	
	public ResultMessage delete(CategoryVO vo) throws RemoteException{
		CategoryPO po=CategoryVOToCategoryPO(vo);
		if (!existPO(vo.id)) {
			return ResultMessage.NOT_FOUND;
		}
		if(vo.number>0){
			return ResultMessage.HAS_CHILDREN;
		}
		DataFactoryImpl.getInstance().getCategoryData().delete(po);
		return ResultMessage.SUCCESS;
	}
	public ResultMessage update(CategoryVO vo) throws RemoteException{
		CategoryPO po=CategoryVOToCategoryPO(vo);
		if(!existPO(po.getId())){
			return ResultMessage.EXIST;
		}
		ResultMessage nameCheck = Utility.checkInputValid(po.getName(), 2, 14,
				true);
		if (nameCheck != ResultMessage.SUCCESS) {
			return nameCheck;
		}
		DataFactoryImpl.getInstance().getCategoryData().update(po);
		return ResultMessage.SUCCESS;
	}
	
	
	
	
	public ArrayList<CategoryPO> show(){
		return null;
	}
	
	public boolean existPO(String id) throws RemoteException {
		ArrayList<CategoryPO> poList = show();
		for (CategoryPO poCheck : poList) {
			if (poCheck.getId().equals(id)) {
				return true;
			}
		}

		return false;
	}


}
