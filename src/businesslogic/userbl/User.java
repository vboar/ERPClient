/**
 * @author chengcheng
 * @date 2014/11/14
 */

package businesslogic.userbl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;

import po.UserPO;
import util.ResultMessage;
import util.UserType;
import vo.UserVO;
import businesslogic.logbl.Log;
import businesslogic.loginbl.Login;
import businesslogic.utilitybl.Utility;
import dataservice.datafactoryservice.DataFactoryImpl;

public class User {
	public ResultMessage createLog(String content) {
		Log l=new Log();
		return l.add(content);
	}

	/**
	 * @author chengcheng vo转po
	 * @param vo
	 * @return
	 */
	private UserPO userVOToUserPO(UserVO vo) {
		String id = vo.id;
		String password = vo.password;
		UserType type = vo.type;
		int permission = vo.permission;
		String name = vo.name;
		UserPO po = new UserPO(id, password, type.ordinal(), permission, name);
		return po;

	}

	/**
	 * @author chengcheng vo转po
	 * @param vo
	 * @return
	 */
	private UserVO userPOToUservo(UserPO po) {
		String id = po.getId();
		String password = po.getPassword();
		UserType type = UserType.values()[po.getType()];
		int permission = po.getPermission();
		String name = po.getName();
		UserVO vo = new UserVO(id, password, type, permission, name);
		return vo;

	}
	
	public UserVO getById(String id){
		UserPO po=null;
		try {
			po = DataFactoryImpl.getInstance().getUserData().getById(id);
		} catch (RemoteException e) {
						e.printStackTrace();
		}
		UserVO vo=userPOToUservo(po);
		return vo;
	}

	/**
	 * 增加用户
	 * 
	 * @author chengcheng
	 * @param vo
	 * @return
	 * @throws RemoteException
	 */
	public ResultMessage addUser(UserVO vo)  {
		UserPO po = userVOToUserPO(vo);
		if (existPO(po.getId())) {
			return ResultMessage.EXIST;
		}

		ResultMessage result = Utility.checkInputValid(po.getName(), 2, 14,
				true);
		if (result != ResultMessage.SUCCESS) {
			return result;
		}
		result = Utility.checkInputValid(po.getPassword(), 5, 14, false);
		if (result != ResultMessage.SUCCESS) {
			return result;
		}
		try {
			DataFactoryImpl.getInstance().getUserData().insert(po);
		} catch (RemoteException e) {
						e.printStackTrace();
		}
		return ResultMessage.SUCCESS;
	}

	/**
	 * 删除用户
	 * 
	 * @author chengcheng
	 * @param vo
	 * @return
	 * @throws RemoteException
	 */
	public ResultMessage delete(UserVO vo)  {
		UserPO po = userVOToUserPO(vo);
		if (!existPO(po.getId())) {
			return ResultMessage.NOT_FOUND;
		}
		try {
			DataFactoryImpl.getInstance().getUserData().delete(po);
		} catch (RemoteException e) {
			
			e.printStackTrace();
		}
		return ResultMessage.SUCCESS;
	}

	public ResultMessage update(UserVO vo)  {
		UserPO po = userVOToUserPO(vo);
		ResultMessage result = Utility.checkInputValid(po.getName(), 2, 14,
				true);
		if (result != ResultMessage.SUCCESS) {

			return result;
		}

		result = Utility.checkInputValid(po.getPassword(), 6, 14, false);
		if (result != ResultMessage.SUCCESS) {
			return result;
		}

		try {
			DataFactoryImpl.getInstance().getUserData().update(po);
		} catch (RemoteException e) {
			
			e.printStackTrace();
		}
		return ResultMessage.SUCCESS;
	}

	/**
	 * @author chengcheng 按id查找用户
	 * @param id
	 * @return 用户的id中与输入的id正则匹配
	 * @throws RemoteException
	 */
	public ArrayList<UserVO> findById(String id)  {
		ArrayList<UserPO> poList=null;
		try {
			poList = DataFactoryImpl.getInstance().getUserData()
					.findById(id);
		} catch (RemoteException e) {
			
			e.printStackTrace();
		}
		ArrayList<UserVO> voList = new ArrayList<UserVO>();
		for (UserPO po : poList) {
			voList.add(userPOToUservo(po));
		}
		return voList;

	}

	/**
	 * @author chengcheng 按名称查找用户
	 * @param name
	 * @return 用户的name与输入的name正则匹配
	 * @throws RemoteException
	 */
	public ArrayList<UserVO> findByName(String name)  {
		ArrayList<UserPO> poList=null;
		try {
			poList = DataFactoryImpl.getInstance().getUserData()
					.findByName(name);
		} catch (RemoteException e) {
			
			e.printStackTrace();
		}
		ArrayList<UserVO> voList = new ArrayList<UserVO>();
		for (UserPO po : poList) {
			voList.add(userPOToUservo(po));
		}
		return voList;
	}

	/**
	 * @author chengcheng 按类型查找用户
	 * @param type
	 * @return 输入的类型的所有用户
	 * @throws RemoteException
	 */
	public ArrayList<UserVO> findByType(UserType type)  {
		ArrayList<UserPO> poList=null;
		try {
			poList = DataFactoryImpl.getInstance().getUserData()
					.findByType(type.ordinal());
		} catch (RemoteException e) {
			
			e.printStackTrace();
		}
		ArrayList<UserVO> voList = new ArrayList<UserVO>();
		for (UserPO po : poList) {
			voList.add(userPOToUservo(po));
		}
		return voList;

	}
	
	public ArrayList<UserVO> fuzzyFind(String keyWord){
		ArrayList<UserVO> voList1=findByName(keyWord);
		ArrayList<UserVO> voList2=findById(keyWord);
		ArrayList<UserVO> voList=new ArrayList<UserVO>();
		voList.addAll(voList1);
		for(UserVO vo2:voList2){
			boolean add=true;
			for(UserVO vo1:voList1){
				if(vo1.id.equals(vo2.id)){
					add=false;
				}
			}
			if(add)
			voList.add(vo2);
		}

		return voList;
	}
	
public ArrayList<UserVO> fuzzyFindOperator(String keyWord) {
		
	ArrayList<UserVO> voList1=findByName(keyWord);
	ArrayList<UserVO> voList2=findById(keyWord);
	ArrayList<UserVO> voList=new ArrayList<UserVO>();
	voList.addAll(voList1);
	for(UserVO vo2:voList2){
		boolean add=true;
		for(UserVO vo1:voList1){
			if(vo1.id.equals(vo2.id)){
				add=false;
			}
		}
		if(add)
		voList.add(vo2);
	}

	Iterator<UserVO> itr=voList.iterator();
	while(itr.hasNext()){
		UserVO tobechecked=itr.next();
		if(tobechecked.type!=UserType.SALESMAN){
			itr.remove();
		}
	}
	
	return voList;
	}
	

	/**
	 * @author chengcheng 查询所有的 userpo
	 * @author chengcheng
	 * @return poList
	 * @throws RemoteException
	 */
	public ArrayList<UserVO> show()  {
		ArrayList<UserPO> poList = null;
		try {
			poList = DataFactoryImpl.getInstance().getUserData()
					.show();
		} catch (RemoteException e) {
			
			e.printStackTrace();
		}
		ArrayList<UserVO> voList = new ArrayList<UserVO>();
		for (UserPO po : poList) {
			voList.add(userPOToUservo(po));
		}
		return voList;

	}

	/**
	 * 检查po是否存在于数据中
	 * 
	 * @author chengcheng
	 * @param po
	 * @return
	 * @throws RemoteException
	 */
	private boolean existPO(String id)  {
		ArrayList<UserVO> voList = show();
		for (UserVO voCheck : voList) {
			if (voCheck.id.equals(id)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * 用户自行修改密码
	 * @param oldword
	 * @param newword
	 * @return
	 */
	public ResultMessage updatePassword(String oldword, String newword) {
		UserVO vo = this.getById(Login.currentUserId);
		if(!vo.password.equals(oldword)){
			return ResultMessage.FAILED;
		}
		vo.password = newword;
		return this.update(vo);
	}

}
