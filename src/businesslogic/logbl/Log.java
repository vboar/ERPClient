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
		public ResultMessage add(String content) {
			String time=Time.getCurrentTime();
			
			String id=getLogId();
			
			String operator=getOperator();
			
			try {
				DataFactoryImpl.getInstance().getLogData().insert(new LogPO(time,id,operator,content));
			} catch (RemoteException e) {
				
				e.printStackTrace();
			}
			
			return ResultMessage.SUCCESS;
		}
		
		public ArrayList<LogPO> show() {
			try {
				return DataFactoryImpl.getInstance().getLogData().show();
			} catch (RemoteException e) {
				
				e.printStackTrace();
			}
			return null;
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
