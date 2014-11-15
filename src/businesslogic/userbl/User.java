/**
 * @author chengcheng
 * @date 2014/11/14
 */

package businesslogic.userbl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dataservice.datafactoryservice.DataFactoryImpl;
import po.UserPO;
import util.ResultMessage;
import util.UserType;
import vo.UserVO;
import businesslogic.accountbl.MockLog;
import businesslogic.utilitybl.Utility;

public class User {
	public ResultMessage createLog(String content) {

		MockLog log = new MockLog(content);
		return log.add();
	}

	/**
	 * @author chengcheng
	 * vo转po
	 * @param vo
	 * @return
	 */
	public UserPO userVOToUserPO(UserVO vo) {
		/**
		 * 用户名
		 */
		String id = vo.id;

		/**
		 * 密码
		 */
		String password = vo.password;

		/**
		 * 类型
		 */
		UserType type = vo.type;

		/**
		 * 权限
		 */
		int permission = vo.permission;

		/**
		 * 姓名
		 */
		String name = vo.name;

		UserPO po = new UserPO(id, password, type, permission, name);
		return po;

	}

	/**
	 * 增加用户
	 * @author chengcheng
	 * @param vo
	 * @return
	 * @throws RemoteException
	 */
	public ResultMessage addUser(UserVO vo) throws RemoteException {
		UserPO po = userVOToUserPO(vo);
//		if (existPO(po.getId())) {
//			return ResultMessage.EXIST;
//		}
//		ResultMessage result = inputValid(po);
//		if (result == ResultMessage.SUCCESS) {
			DataFactoryImpl.getInstance().getUserData().insert(po);
			return ResultMessage.SUCCESS;
//			return result;
//		}
//		return result;
	}

	/**
	 * 删除用户
	 * @author chengcheng
	 * @param vo
	 * @return
	 * @throws RemoteException
	 */
	public ResultMessage delete(UserVO vo) throws RemoteException {
		UserPO po = userVOToUserPO(vo);
		if (!existPO(po.getId())) {
			return ResultMessage.NOT_FOUND;
		}
		DataFactoryImpl.getInstance().getUserData().delete(po);
		return ResultMessage.SUCCESS;
	}

	public ResultMessage update(UserVO vo) throws RemoteException {
		UserPO po = userVOToUserPO(vo);
		ResultMessage result = inputValid(po);
		if (result == ResultMessage.SUCCESS) {
			DataFactoryImpl.getInstance().getUserData().update(po);
			return result;
		}
		return result;
	}

	/**
	 * @author chengcheng
	 * 按id查找用户
	 * @param id
	 * @return 用户的id中与输入的id正则匹配
	 * @throws RemoteException
	 */
	public ArrayList<UserPO> findById(String id) throws RemoteException {
		ArrayList<UserPO> poList = show();
		ArrayList<UserPO> returnList = new ArrayList<UserPO>();
		for (UserPO po : poList) {
			String idCheck = po.getId();
			Pattern pat = Pattern.compile(id);
			Matcher mat = pat.matcher(idCheck);
			if (idCheck.equals(id)) {
				returnList.add(0, po);
			}
			if (mat.find()) {
				returnList.add(po);
			}

		}
		return returnList;
	}

	/**
	 * @author chengcheng
	 *按名称查找用户
	 * @param name
	 * @return  用户的name与输入的name正则匹配
	 * @throws RemoteException
	 */
	public ArrayList<UserPO> findByName(String name) throws RemoteException {
		ArrayList<UserPO> poList = show();
		ArrayList<UserPO> returnList = new ArrayList<UserPO>();
		for (UserPO po : poList) {
			String nameCheck = po.getName();
			Pattern pat = Pattern.compile(name);
			Matcher mat = pat.matcher(nameCheck);
			if (nameCheck.equals(name)) {
				returnList.add(0, po);
			}
			if (mat.find()) {
				returnList.add(po);
			}

		}
		return returnList;
	}

	/**
	 * @author chengcheng
	 * 按类型查找用户
	 * @param type
	 * @return 输入的类型的所有用户
	 * @throws RemoteException
	 */
	public ArrayList<UserPO> findByType(UserType type) throws RemoteException {
		ArrayList<UserPO> poList = show();
		ArrayList<UserPO> returnList = new ArrayList<UserPO>();
		for (UserPO po : poList) {
			if (type.equals(po.getType())) {
				returnList.add(po);
			}
		}
		return returnList;
	}

	/**
	 * @author chengcheng
	 * 查询所有的 userpo
	 * @author chengcheng
	 * @return poList
	 * @throws RemoteException
	 */
	public ArrayList<UserPO> show() throws RemoteException {
		ArrayList<UserPO> poList = new ArrayList<UserPO>();
		poList = DataFactoryImpl.getInstance().getUserData().show();
		return poList;
	}

	/**
	 * 检查po是否存在于数据中
	 * @author chengcheng
	 * @param po
	 * @return
	 * @throws RemoteException
	 */
	public boolean existPO(String id) throws RemoteException {
		ArrayList<UserPO> poList = show();
		for (UserPO poCheck : poList) {
			if (poCheck.getId().equals(id)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * @author chengcheng
	 * 判断name，id，password是否符合规范要求
	 * @param po
	 * @return
	 */
	public ResultMessage inputValid(UserPO po) {

		// 四个参数分别是：输入，最短长度，最长长度，是否允许中文
		ResultMessage idCheck = Utility.checkInputValid(po.getId(), 6, 14,
				false);
		if (idCheck != ResultMessage.SUCCESS) {
			return idCheck;
		}
		ResultMessage passwordCheck = Utility.checkInputValid(po.getPassword(),
				6, 14, false);
		if (passwordCheck != ResultMessage.SUCCESS) {
			return passwordCheck;
		}
		ResultMessage nameCheck = Utility.checkInputValid(po.getName(), 2, 14,
				true);
		if (nameCheck != ResultMessage.SUCCESS) {
			return nameCheck;
		}
		return ResultMessage.SUCCESS;
	}

}
