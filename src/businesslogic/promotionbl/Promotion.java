/**
 * @author chengcheng
 * @date 2014/11/14
 */

package businesslogic.promotionbl;

import java.util.ArrayList;

import vo.CustomerGiftVO;

public class Promotion {
	public ArrayList<CustomerGiftVO> findByVip(int level,ArrayList<CustomerGiftVO> list){
		ArrayList<CustomerGiftVO> list2 =new ArrayList<CustomerGiftVO>();
		for(int i=0;i<list.size();i++){
			if(list.get(i).vip==level){
				list2.add(list.get(i));
			}
		}
		return list2;		
	}

}
