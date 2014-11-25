/**
 * @author chengcheng
 * @date 2014/11/14
 */
package businesslogic.logbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.LogPO;
import util.ResultMessage;
import util.Time;
import dataservice.datafactoryservice.DataFactoryImpl;


//oneoneO
public class Log {
		public ResultMessage add(String content) throws RemoteException{
			String time=Time.getCurrentTime();
			
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
