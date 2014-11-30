/**
 * category逻辑类
 * @author Vboar
 * @date 2014/11/12
 */

package businesslogic.commoditybl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;

import po.CategoryPO;
import util.ResultMessage;
import vo.CategoryVO;
import businesslogic.utilitybl.Utility;
import dataservice.datafactoryservice.DataFactoryImpl;

public class Category {

	public ResultMessage createLog(String content) {
		//TODO
		return null;
	}

	private CategoryPO CategoryVOToCategoryPO(CategoryVO vo) {
		String id = "";
		// TODO 系统保存的条目
		String name = vo.name;
		int number = vo.number;
		CategoryPO po = new CategoryPO(id, name, number);
		return po;
	}

	private CategoryVO CategoryPOToCategoryVO(CategoryPO po) {
		String id = po.getId();
		String name = po.getName();
		int number = po.getNumber();
		CategoryVO vo = new CategoryVO(name, number, findById(id).get(0));

		return vo;
	}

	public ResultMessage add(CategoryVO vo) {
		CategoryPO po = CategoryVOToCategoryPO(vo);
		CategoryVO father = vo.father;
		if (existPO(po.getId())) {
			return ResultMessage.EXIST;
		}
		ResultMessage nameCheck = Utility.checkInputValid(po.getName(), 2, 14,
				true);
		if (nameCheck != ResultMessage.SUCCESS) {
			return nameCheck;
		}
		father.number++;
		update(father);
		try {
			DataFactoryImpl.getInstance().getCategoryData().insert(po);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return ResultMessage.SUCCESS;
	}

	public ResultMessage delete(CategoryVO vo) {
		CategoryPO po = CategoryVOToCategoryPO(vo);
		if (!existPO(vo.id)) {
			return ResultMessage.NOT_FOUND;
		}
		if (vo.number > 0) {
			return ResultMessage.HAS_CHILDREN;
		}
		try {
			DataFactoryImpl.getInstance().getCategoryData().delete(po);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return ResultMessage.SUCCESS;
	}

	public ResultMessage update(CategoryVO vo) {
		// TODO 哪些不能改的

		CategoryPO po = CategoryVOToCategoryPO(vo);
		if (!existPO(po.getId())) {
			return ResultMessage.NOT_FOUND;
		}
		ResultMessage nameCheck = Utility.checkInputValid(po.getName(), 2, 14,
				true);
		if (nameCheck != ResultMessage.SUCCESS) {
			return nameCheck;
		}
		try {
			DataFactoryImpl.getInstance().getCategoryData().update(po);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return ResultMessage.SUCCESS;
	}

	// private CategoryPO getById(String id){
	// CategoryPO po=null;
	// try {
	// po = DataFactoryImpl.getInstance().getCategoryData().getById(id);
	// } catch (RemoteException e) {
	//
	// e.printStackTrace();
	// }
	// return po;
	// }

	public ArrayList<CategoryVO> findById(String id) {
		ArrayList<CategoryPO> poList = null;
		try {
			poList = DataFactoryImpl.getInstance().getCategoryData()
					.findById(id);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		ArrayList<CategoryVO> voList = new ArrayList<CategoryVO>();
		for (CategoryPO po : poList) {
			voList.add(CategoryPOToCategoryVO(po));
		}
		return voList;
	}

	public ArrayList<CategoryVO> findByName(String name) {
		ArrayList<CategoryPO> poList = null;
		try {
			poList = DataFactoryImpl.getInstance().getCategoryData()
					.findByName(name);
		} catch (RemoteException e) {

			e.printStackTrace();
		}
		ArrayList<CategoryVO> voList = new ArrayList<CategoryVO>();
		for (CategoryPO po : poList) {
			voList.add(CategoryPOToCategoryVO(po));
		}
		return voList;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<CategoryVO> show() {
		ArrayList<CategoryPO> poList = null;
		try {
			poList = DataFactoryImpl.getInstance().getCategoryData().show();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		ArrayList<CategoryVO> voList = new ArrayList<CategoryVO>();
		for (CategoryPO po : poList) {
			voList.add(CategoryPOToCategoryVO(po));
		}
		Collections.sort(voList,new SortById());		
		return voList;

	}
	
	 

	private boolean existPO(String id) {
		ArrayList<CategoryVO> voList = show();
		for (CategoryVO voCheck : voList) {
			if (voCheck.id.equals(id)) {
				return true;
			}
		}

		return false;
	}

}
