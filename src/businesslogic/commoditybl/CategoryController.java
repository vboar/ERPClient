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
	public ArrayList<CategoryVO> show() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
<<<<<<< f04e48507f537e2ff2ef7542110d1deea21c80e0
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
=======
	public ArrayList<CategoryVO> fuzzyFind(String name) {
		// TODO Auto-generated method stub
		return null;
>>>>>>> c4814e2bb3704fa240d881edba572c01c4c76c1a
	}

}
