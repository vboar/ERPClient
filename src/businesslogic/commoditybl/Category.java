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
	
	private  CategoryPO CategoryVOToCategoryPO(CategoryVO vo){
		String id="";
		//todo
		String name=vo.name;
		int number=vo.number;
		CategoryPO po=new CategoryPO(id,name,number);
		return po;
	}
	
	private  CategoryVO CategoryPOToCategoryVO(CategoryPO po) throws RemoteException{
		String id=po.getId();
		String name=po.getName();
		int number=po.getNumber();
		CategoryVO vo=new CategoryVO(name,number,findById(id).get(0));
				
		return vo;
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
	
	
	
	public ArrayList<CategoryVO> findById(String id) throws RemoteException{
		ArrayList<CategoryPO> poList= DataFactoryImpl.getInstance().getCategoryData().findById(id);
		 ArrayList<CategoryVO> voList=new ArrayList<CategoryVO>();
		 for(CategoryPO po:poList){
			 voList.add(CategoryPOToCategoryVO(po));
		 }
		 return voList;
	}
	
	public ArrayList<CategoryVO> findByName(String name) throws RemoteException{
		ArrayList<CategoryPO> poList= DataFactoryImpl.getInstance().getCategoryData().findByName(name);
		 ArrayList<CategoryVO> voList=new ArrayList<CategoryVO>();
		 for(CategoryPO po:poList){
			 voList.add(CategoryPOToCategoryVO(po));
		 }
		 return voList;
	}		
	
	public ArrayList<CategoryVO> show() throws RemoteException{
		ArrayList<CategoryPO> poList= DataFactoryImpl.getInstance().getCategoryData().show();
		 ArrayList<CategoryVO> voList=new ArrayList<CategoryVO>();
		 for(CategoryPO po:poList){
			 voList.add(CategoryPOToCategoryVO(po));
		 }
		 return voList;

	}
	
	
	
	private boolean existPO(String id) throws RemoteException {
		ArrayList<CategoryVO> voList = show();
		for (CategoryVO voCheck : voList) {
			if (voCheck.id.equals(id)) {
				return true;
			}
		}

		return false;
	}


}
