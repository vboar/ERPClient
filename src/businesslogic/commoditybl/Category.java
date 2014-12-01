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
import vo.CommodityVO;
import businesslogic.logbl.Log;
import businesslogic.utilitybl.Utility;
import dataservice.datafactoryservice.DataFactoryImpl;

public class Category {

	Log log = new Log();

	/**
	 * @author chengcheng vo转换为po
	 * @param vo
	 * @return
	 */
	private CategoryPO CategoryVOToCategoryPO(CategoryVO vo) {
		String id = vo.id;
		String name = vo.name;
		int number = vo.number;
		CategoryPO po = new CategoryPO(id, name, number);
		return po;
	}

	/**
	 * @author chengcheng po转换vo的方法
	 * @param po
	 * @return
	 */
	protected CategoryVO CategoryPOToCategoryVO(CategoryPO po) {
		String id = po.getId();
		String name = po.getName();
		int number = po.getNumber();
		CategoryVO vo = new CategoryVO(name, number, findById(id).get(0));

		return vo;
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	private boolean existPO(String id) {
		ArrayList<CategoryVO> voList = show();
		for (CategoryVO voCheck : voList) {
			if (voCheck.id.equals(id)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 创建分类的时候新建id
	 * @param fatherId
	 * @return
	 */
	public  String createId(String fatherId){
		ArrayList<CategoryVO> voList= findById(fatherId);
		if(voList.size()==0){
			return fatherId+"-00000";
		}else{
		
		String max=voList.get(voList.size()-1).id;
		String oldMax=max.substring(max.length()-5);
		int maxInt=Integer.parseInt(oldMax);
		String pattern="00000";
		 java.text.DecimalFormat df = new java.text.DecimalFormat(pattern);
		 String maxStr=df.format(maxInt+1);
		 return fatherId+"-"+maxStr;
		 
		}
		
		
		
	}
	/**
	 * 添加操作
	 * 
	 * @param vo
	 * @return .UNVALID .TOOLONG .TOOSHORT .EXIT
	 */
	public ResultMessage add(CategoryVO vo) {

		CategoryPO po = CategoryVOToCategoryPO(vo);
		CategoryVO father = vo.father;
		// 检查是否存在
		if (existPO(po.getId())) {
			return ResultMessage.EXIST;
		}
		// 检查输入合法
		ResultMessage nameCheck = Utility.checkInputValid(po.getName(), 2, 14,
				true);
		if (nameCheck != ResultMessage.SUCCESS) {
			return nameCheck;
		}
		//是否有商品
		ArrayList<CommodityVO> commodity=new Commodity().findById(father.id);
		if(!commodity.isEmpty()){
			return ResultMessage.HAS_COMMODITY;
		}
		// 改父分类的number
		CategoryPO fatherPO = getById(father.id);
		CategoryVO fatherVO = CategoryPOToCategoryVO(fatherPO);
		fatherVO.number++;
		update(fatherVO);
		// 添加到data
		try {
			DataFactoryImpl.getInstance().getCategoryData().insert(po);
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		return ResultMessage.SUCCESS;
	}

	/**
	 * 分类删除
	 * 
	 * @param vo
	 * @return
	 */
	public ResultMessage delete(CategoryVO vo) {
		CategoryPO po = CategoryVOToCategoryPO(vo);
		CategoryVO father = vo.father;
		// 检查是否不存在
		if (!existPO(vo.id)) {
			return ResultMessage.NOT_FOUND;
		}
		// 是否存在子女
		if (vo.number > 0) {
			return ResultMessage.HAS_CHILDREN;
		}
		// 删除data
		try {
			DataFactoryImpl.getInstance().getCategoryData().delete(po);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		// 修改父分类的数量
		CategoryPO fatherPO = getById(father.id);
		CategoryVO fatherVO = CategoryPOToCategoryVO(fatherPO);
		fatherVO.number--;
		update(fatherVO);

		return ResultMessage.SUCCESS;
	}

	/**
	 * 分类的更新
	 * 
	 * @param vo
	 * @return
	 */
	public ResultMessage update(CategoryVO vo) {
		CategoryPO newPO=null;
		if(vo.father==null){
		// 得到原来的
		CategoryPO oldPO = getById(vo.id);
		CategoryVO oldVO = CategoryPOToCategoryVO(oldPO);
		// 把名字改了
		oldVO.name = vo.name;
		 newPO = CategoryVOToCategoryPO(oldVO);
		}else{
			 newPO = CategoryVOToCategoryPO(vo);
		}
		// 检查名字是否合法
		ResultMessage nameCheck = Utility.checkInputValid(vo.name, 2, 14, true);
		if (nameCheck != ResultMessage.SUCCESS) {
			return nameCheck;
		}
		// 改data
		try {
			DataFactoryImpl.getInstance().getCategoryData().update(newPO);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return ResultMessage.SUCCESS;
	}

	/**
	 * 精确查找
	 * @param id
	 * @return
	 */
	public CategoryPO getById(String id) {
		CategoryPO po = null;
		try {
			po = DataFactoryImpl.getInstance().getCategoryData().getById(id);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return po;
	}

	/**
	 * 模糊查找by id
	 * @param id
	 * @return
	 */
	protected ArrayList<CategoryVO> findById(String id) {
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

	/**
	 * 模糊查找by name
	 * @param name
	 * @return
	 */
	private ArrayList<CategoryVO> findByName(String name) {
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
	
	/**
	 * 真正会被调用的fuzzyfind
	 * @param keyword
	 * @return
	 */
	
	public ArrayList<CategoryVO> fuzzyFind(String keyword) {
		ArrayList<CategoryVO> voList1=findByName(keyword);
		ArrayList<CategoryVO> voList2=findById(keyword);
		ArrayList<CategoryVO> voList=new ArrayList<CategoryVO>();
		voList.addAll(voList1);
		for(CategoryVO vo2:voList2){
			boolean add=true;
			for(CategoryVO vo1:voList1){
				if(vo1.id.equals(vo2.id)){
					add=false;
				}
			}
			if(add)
			voList.add(vo2);
		}
		return voList;
	}

	/**
	 * 按顺序全部显示
	 * @return
	 */
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
		//排序
		Collections.sort(voList, new SortById());
		return voList;

	}
	
//	public static void main(String[] args) {
//		String id=new Category().createId("00000-00000");
//		System.out.println(id);
	// }

}
