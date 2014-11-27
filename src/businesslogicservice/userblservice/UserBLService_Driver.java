/**
 * 用户业务逻辑驱动
 * @author chengcheng
 * @date 2014/10/25
 */

package businesslogicservice.userblservice;

import java.util.ArrayList;

import util.ResultMessage;
import util.UserType;
import vo.UserVO;

public class UserBLService_Driver {

	public void drive(UserBLService userBLService){
		ResultMessage result1=userBLService.add
				(new UserVO("xs001","123456",UserType.SALESMAN,0,"金刚浪"));
		System.out.println("下面是添加用户的返回信息");
		if(result1==ResultMessage.SUCCESS){
			System.out.println("添加成功");
		}else{
			System.out.println("用户名已存在，添加失败");
		}
		System.out.println();
		
		ResultMessage result2=userBLService.delete
				(new UserVO("xs001","123456",UserType.SALESMAN,0,"金刚浪"));
		System.out.println("下面是删除用户的返回信息");
		if(result2==ResultMessage.SUCCESS){
			System.out.println("添加成功");
		}
		System.out.println();
		
		ArrayList<UserVO> volist1=userBLService.findById("xs001");
		System.out.println("下面是查找到的用户id");
		System.out.println(volist1.get(0).id);
		System.out.println();
		
		ArrayList<UserVO> volist2=userBLService.findByName("xs001");
		System.out.println("下面是查找到的用户姓名");
		System.out.println(volist2.get(0).name);
		System.out.println();
		
		ArrayList<UserVO> volist3=userBLService.findByType(UserType.SALESMAN);
		System.out.println("下面是查找到的用户id");
		System.out.println(volist3.get(0).id);
		System.out.println();
		
		ArrayList<UserVO> volist4=userBLService.show();
		System.out.println("下面是查找到的用户id");
		System.out.println(volist4.get(0).id);
		System.out.println();

	}
}
