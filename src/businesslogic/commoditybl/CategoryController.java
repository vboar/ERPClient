/**
 * categorycontroller
 * @author Vboar
 * @date 2014/11/14
 */

package businesslogic.commoditybl;

import java.util.ArrayList;

import util.ResultMessage;
import vo.CategoryVO;
import businesslogicservice.commodityblservice.CategoryBLService;

public class CategoryController implements CategoryBLService {

	@Override
	public ResultMessage add(CategoryVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage delete(CategoryVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage update(CategoryVO vo) {
		return ResultMessage.SUCCESS;
	}

	@Override
	public ArrayList<CategoryVO> findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<CategoryVO> findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<CategoryVO> show() {
		ArrayList<CategoryVO> list = new ArrayList<CategoryVO>();
		list.add(new CategoryVO("0001","a",4));
		list.add(new CategoryVO("0001-0001","aa",0));
		list.add(new CategoryVO("0001-0002","bb",0));
		list.add(new CategoryVO("0001-0002-00001","bbb",0));
		list.add(new CategoryVO("0001-0003","cc",0));
		list.add(new CategoryVO("0001-0004","dd",0));
		list.add(new CategoryVO("0003","e",0));
		list.add(new CategoryVO("0003-0001","ff",0));
		list.add(new CategoryVO("0003-0002","gg",0));

		return list;
	}

}
