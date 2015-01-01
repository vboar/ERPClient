/**
 * @author chengcheng
 * @date 2014/11/14
 */
package businesslogic.logbl;

import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import businesslogic.loginbl.Login;
import po.LogPO;
import util.ResultMessage;
import util.Time;
import vo.LogVO;
import dataservice.datafactoryservice.DataFactoryImpl;


//oneoneO
public class Log {
		public ResultMessage add(String content) {
			String time=Time.getCurrentTime();
			
			String id=getLogId();
			
			String operator=getOperator();
			
			try {
				DataFactoryImpl.getInstance().getLogData().insert(new LogPO(id,time,operator,content));
			} catch (RemoteException e) {
				
				e.printStackTrace();
			}
			
			return ResultMessage.SUCCESS;
		}
		
		public ArrayList<LogVO> findByTime(String time1,String time2){
			ArrayList<LogVO> result=new ArrayList<LogVO>();
			ArrayList<LogPO> temp=new ArrayList<LogPO>();
			time1 = Time.jdugeTime1(time1);
			time2 = Time.jdugeTime2(time2);

			try {
				temp=DataFactoryImpl.getInstance().getLogData().findByTime(time1, time2);
			} catch (RemoteException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			
			for(int i=0;i<temp.size();i++){
				result.add(poToVo(temp.get(i)));
			}

			return result;
		}
		
		public ArrayList<LogVO> findByOperator(String operatorId){
			ArrayList<LogVO> result=new ArrayList<LogVO>();
			ArrayList<LogPO> temp=new ArrayList<LogPO>();
			
			try {
				temp=DataFactoryImpl.getInstance().getLogData().show();
			} catch (RemoteException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			
			for(int i=0;i<temp.size();i++){
				if(temp.get(i).getOperatorId().equals(operatorId))
					result.add(poToVo(temp.get(i)));
			}
			return result;
		}
		
		public ArrayList<LogVO> find(String time1,String time2,String operatorId){
			ArrayList<LogVO> result=new ArrayList<LogVO>();
			ArrayList<LogVO> temp=new ArrayList<LogVO>();
			
			temp=findByTime(time1,time2);
			System.out.println(operatorId+" "+temp.get(0).operator);
			if(operatorId==null||operatorId.equals(" "))
				result=temp;
			else
				for(int i=0;i<temp.size();i++)
					if(temp.get(i).operator.equals(operatorId))
						result.add(temp.get(i));
			
			return result;
		}
		
		public ArrayList<LogVO> show() {
			ArrayList<LogVO> result=new ArrayList<LogVO>();
			ArrayList<LogPO> temp=new ArrayList<LogPO>();
			try {
				temp=DataFactoryImpl.getInstance().getLogData().show();
			} catch (RemoteException e) {
				
				e.printStackTrace();
			}
			for(int i=0;i<temp.size();i++){
				result.add(poToVo(temp.get(i)));
			}
			return result;
		}
		
		public LogVO poToVo(LogPO po){
			return new LogVO(po.getId(),po.getTime(),po.getOperatorId(),po.getContent());
		}
		
		public LogPO voToPo(LogVO vo){
			return new LogPO(vo.id,vo.time,vo.operator,vo.content);
		}
		
		//TODO
		public String getLogId(){
			String id="RZ-";
			String time=Time.getCurrentYear()+Time.getCurrentMonth()+Time.getCurrentDay();
			DecimalFormat df=new DecimalFormat("00000");
			String number="";
			try {
				number=df.format(DataFactoryImpl.getInstance().getLogData().show().size()+1);
			} catch (RemoteException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			id=id+time+"-"+number;
			
			return id;
		}
		
		//TODO
		public String getOperator(){
			String operator=Login.currentUserId;
			return operator;
		}

}
