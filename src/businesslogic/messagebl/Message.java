package businesslogic.messagebl;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import po.MessagePO;
import po.UserPO;
import dataservice.datafactoryservice.DataFactoryImpl;
import businesslogic.logbl.Log;
import businesslogic.loginbl.Login;
import util.ResultMessage;
import util.Time;
import util.UserType;
import vo.MessageVO;
import vo.UserVO;

public class Message {
	public ResultMessage addLog(String content){
		Log l=new Log();
		return l.add(content);	
		
	}
	
	public void add(MessageVO vo){
		vo.id=createId();
		vo.time=Time.getCurrentTime();
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
	
	public ArrayList<MessageVO> show(){
		ArrayList<MessageVO> result=showByUser(new UserVO(Login.currentUserId,"",UserType.ADMINISTRATOR,0,Login.currentUserName));
		return result;
	}
	
	public String createId(){
		Date date=new Date();
		SimpleDateFormat myFmt=new SimpleDateFormat("yyyyMMdd");
		String time=myFmt.format(date);

		ArrayList<MessageVO> list=new ArrayList<MessageVO>();
		if(list.size()==0){
			return "XX-"+Time.getCurrentTime()+"-00001";
		}else{
			String maxId=list.get(list.size()-1).id;
			String day=maxId.substring(4,maxId.length()-5);
			if(day.compareTo(time)<0){
				return "XX-"+time+"-00001";
			}else{
				String oldMax=maxId.substring(maxId.length()-5);
				int maxInt=Integer.parseInt(oldMax);
				String pattern="00000";
				 java.text.DecimalFormat df = new java.text.DecimalFormat(pattern);
				 String maxStr=df.format(maxInt+1);
				 return "XX-"+time+"-"+maxStr;
			}
		}
	}
}
