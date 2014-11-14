/**
 * 系统日志业务逻辑驱动
 */
package businesslogicservice.logblservice;

import java.util.ArrayList;

import util.ResultMessage;
import vo.LogVO;

public class LogBLService_Driver {

	public void drive(LogBLService logBLService){
		ArrayList<LogVO> logvolist=logBLService.show("2014/10/12", "2014/10/15");
		System.out.println("下面显示系统日志信息");
		System.out.println(logvolist.get(0).id);
		System.out.println(logvolist.get(1).id);

		ResultMessage result=logBLService.add(new LogVO("LOG-20141023-00001","23:47:12","CW001-精钢狼","新建一张付款单"));
		if(result==ResultMessage.SUCCESS){
			System.out.println("添加成功");
		}
	}
}
