/**
 * @author chengcheng
 * @date 2014/11/14
 */
package businesslogic.logbl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;

import po.LogPO;
import dataservice.datafactoryservice.DataFactoryImpl;
import util.ResultMessage;


//oneoneO
public class Log {
		public ResultMessage add(String content) throws RemoteException{
			Calendar c=Calendar.getInstance();
			String year=String.valueOf(c.get(Calendar.YEAR));
			String month=String.valueOf(c.get(Calendar.MONTH));
			String date=String.valueOf(c.get(Calendar.DATE));
			String hour=String.valueOf(c.get(Calendar.HOUR_OF_DAY));
			String minute=String.valueOf(c.get(Calendar.MINUTE));
			String second=String.valueOf(c.get(Calendar.SECOND));
			String time=year+"/"+month+"/"+date+" "+hour+":"+minute+":"+second;
			
			String id=getLogId();
			
			String operator=getOperator();
			
			DataFactoryImpl.getInstance().getLogData().insert(new LogPO(time,id,operator,content));
			
			return ResultMessage.SUCCESS;
		}
		
		public ArrayList<LogPO> show() throws RemoteException{
			return DataFactoryImpl.getInstance().getLogData().show();
		}
		
		//TODO
		public String getLogId(){
			return null;
		}
		
		//TODO
		public String getOperator(){
			return null;
		}

}
