package businesslogic.messagebl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.MessagePO;
import po.UserPO;
import dataservice.datafactoryservice.DataFactoryImpl;
import businesslogic.logbl.Log;
import businesslogic.loginbl.Login;
import util.ResultMessage;
import util.UserType;
import vo.MessageVO;
import vo.UserVO;

public class Message {
	public ResultMessage addLog(String content){
		Log l=new Log();
		return l.add(content);	
		
	}
	
	public void add(MessageVO vo){
		try {
			DataFactoryImpl.getInstance().getMessageData().insert(voToPo(vo));
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

	private MessagePO voToPo(MessageVO vo) {
		MessagePO result=new MessagePO(vo.id,vo.time,vo.state,vo.receiver,vo.content);
		return result;
	}
	
	public void delete(MessageVO vo){
		try {
			DataFactoryImpl.getInstance().getMessageData().delete(voToPo(vo));
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	
	public void update(MessageVO vo){
		try {
			DataFactoryImpl.getInstance().getMessageData().update(voToPo(vo));
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	
	public ArrayList<MessageVO> showByUser(UserVO vo){
		ArrayList<MessageVO> result=new ArrayList<MessageVO>();
		ArrayList<MessagePO> temp=new ArrayList<MessagePO>();
		
		try {
			temp=DataFactoryImpl.getInstance().getMessageData().showByUser(UserVOToPo(vo));
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		for(int i=0;i<temp.size();i++)
			result.add(poToVo(temp.get(i)));
		
		return result;
	}

	private MessageVO poToVo(MessagePO po) {
		MessageVO result=new MessageVO(po.getId(),po.getTime(),po.getState(),
				po.getReceiverId(),po.getContent());
		return result;
	}

	private UserPO UserVOToPo(UserVO vo) {
		UserPO result=new UserPO(vo.id,vo.password,vo.type.ordinal(),vo.permission,vo.name);
		return result;
	}
	
	public ArrayList<MessageVO> showByStatus(int status){
		ArrayList<MessageVO> result=new ArrayList<MessageVO>();
		ArrayList<MessageVO> temp=showByUser(new UserVO(Login.currentUserId,"",UserType.ADMINISTRATOR,0,Login.currentUserName));
		
		for(int i=0;i<temp.size();i++)
			if(temp.get(i).state==status)
				result.add(temp.get(i));
		
		return result;
	}
}
