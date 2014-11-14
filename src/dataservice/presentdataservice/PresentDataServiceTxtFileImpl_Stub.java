/**
 * 赠品单数据处理桩程序
 * @author JaneLDQ
 * @date 2014/10/25 
 */
package dataservice.presentdataservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.PresentLineItemPO;
import po.PresentPO;
import util.DocumentStatus;
import util.DocumentType;

public class PresentDataServiceTxtFileImpl_Stub implements PresentDataService{

	@Override
	public void insert(PresentPO po) throws RemoteException {
		System.out.println("Insert Succeed!");
	}

	@Override
	public void update(PresentPO po) throws RemoteException {
		if(po.getId().equals("ZSD-20141023-00001")){
			System.out.println("Update Succeed!"); return;
		}
		System.out.println("Update Failed!");
	}

	@Override
	public ArrayList<PresentPO> show() throws RemoteException {
		ArrayList<PresentPO> list = new ArrayList<PresentPO>();
		ArrayList<PresentLineItemPO> list2 = new ArrayList<PresentLineItemPO>();
		list2.add(new PresentLineItemPO("00001-00001", "飞利浦吊灯", "FLP01", 50));
		list.add(new PresentPO("ZSD-20141023-00001", "21:29:32", "00001",
				"金刚狼",list2, DocumentStatus.NONCHECKED, DocumentType.PRESENT,false));
		System.out.println("Show Succeed!");
		return list;
	}

	@Override
	public ArrayList<PresentPO> findById(String id) {
		ArrayList<PresentPO> list = new ArrayList<PresentPO>();
		ArrayList<PresentLineItemPO> list2 = new ArrayList<PresentLineItemPO>();
		list2.add(new PresentLineItemPO("00001-00001", "飞利浦吊灯", "FLP01", 50));
		list.add(new PresentPO(id, "21:29:32", "00001","金刚狼",list2,
				DocumentStatus.NONCHECKED, DocumentType.PRESENT,false));
		System.out.println("FindById Succeed!");
		return list;
	}

	@Override
	public ArrayList<PresentPO> findByOperator(String operator) {
		ArrayList<PresentPO> list = new ArrayList<PresentPO>();
		ArrayList<PresentLineItemPO> list2 = new ArrayList<PresentLineItemPO>();
		list2.add(new PresentLineItemPO("00001-00001", "飞利浦吊灯", "FLP01", 50));
		list.add(new PresentPO("ZSD-20141023-00001", "21:29:32", "00001",
				operator,list2, DocumentStatus.NONCHECKED, DocumentType.PRESENT,false));
		System.out.println("FindByOperator Succeed!");
		return list;
	}

	@Override
	public ArrayList<PresentPO> findByTime(String time1, String time2) {
		ArrayList<PresentPO> list = new ArrayList<PresentPO>();
		ArrayList<PresentLineItemPO> list2 = new ArrayList<PresentLineItemPO>();
		list2.add(new PresentLineItemPO("00001-00001", "飞利浦吊灯", "FLP01", 50));
		list.add(new PresentPO("ZSD-"+time1+"-00001", "21:29:32", "00001",
				"金刚狼",list2, DocumentStatus.NONCHECKED, DocumentType.PRESENT,false));
		System.out.println("FindByTime Succeed!");
		return list;
	}

}
