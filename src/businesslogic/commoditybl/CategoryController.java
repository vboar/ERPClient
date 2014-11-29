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
		
		return ResultMessage.SUCCESS;
	}

	@Override
	public ResultMessage delete(CategoryVO vo) {
		// TODO Auto-generated method stub
		return ResultMessage.SUCCESS;
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

	public ArrayList<CategoryVO> fuzzyFind(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
