/**
 * 系统数据实现
 * @author Vboar
 * @date 2014/11/15
 */

package dataservice.systemdateservice;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class SystemDataServiceImpl extends UnicastRemoteObject implements SystemDataService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SystemDataServiceImpl() throws RemoteException {
		super();
	}

}
