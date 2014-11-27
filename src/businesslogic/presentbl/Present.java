/**
 * present逻辑类
 * @author Vboar
 * @date 2014/11/12
 */

package businesslogic.presentbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.PresentLineItemPO;
import po.PresentPO;
import util.DocumentStatus;
import util.DocumentType;
import util.ResultMessage;
import vo.PresentLineItemVO;
import vo.PresentVO;
import businesslogic.utilitybl.Utility;
import dataservice.datafactoryservice.DataFactoryImpl;

public class Present {

	public ResultMessage create(PresentVO vo) {
		PresentPO po = voToPO(vo);
		try {
			DataFactoryImpl.getInstance().getPresentData().insert(po);
		} catch (RemoteException e) {

			e.printStackTrace();
		}
		return ResultMessage.SUCCESS;

	}

	public ResultMessage update(PresentVO vo) {
		PresentPO po = voToPO(vo);
		try {
			DataFactoryImpl.getInstance().getPresentData().update(po);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return ResultMessage.SUCCESS;
	}
	
	public ArrayList<PresentVO> findById(String id){
		try {
			ArrayList<PresentPO> poList=DataFactoryImpl.getInstance().getPresentData().findById(id);
			ArrayList<PresentVO> voList=poListToVOList(poList);
			return voList;
		} catch (RemoteException e) {
			
			e.printStackTrace();
		}
		return null;
				
	}

	private PresentPO voToPO(PresentVO vo) {
		// String id, String time, String customerId, String customerName,
		// ArrayList<PresentLineItemPO> list, int documentStatus, int
		// documentType,
		// boolean isWriteoff

		String id = vo.id;
		String time = vo.time;
		String customerId = vo.customerId;
		String customerName = vo.customerName;
		ArrayList<PresentLineItemPO> list = Utility
				.presentVOListToPOlist(vo.list);
		int documentStatus = vo.documentStatus.ordinal();
		int documentType = vo.documentType.ordinal();
		boolean isWriteoff = vo.isWriteoff;
		PresentPO po = new PresentPO(id, time, customerId, customerName, list,
				documentStatus, documentType, isWriteoff);
		return po;
	}

	private PresentVO poToVO(PresentPO po) {
		String id = po.getId();
		String time = po.getTime();
		String customerId = po.getCustomerId();
		;
		String customerName = po.getCustomerId();
		ArrayList<PresentLineItemVO> list = Utility.presentPOListToVOList(po
				.getList());
		DocumentStatus documentStatus = DocumentStatus.values()[po
				.getDocumentStatus()];
		DocumentType documentType = DocumentType.values()[po.getDocumentType()];
		boolean isWriteoff = po.isWriteoff();
		PresentVO vo = new PresentVO(id, time, customerId, customerName, list,
				documentStatus, documentType, isWriteoff);
		return vo;
	}
	
	private ArrayList<PresentVO> poListToVOList(ArrayList<PresentPO> poList){
		ArrayList<PresentVO> voList=new ArrayList<PresentVO>();
		for(PresentPO po:poList){
			PresentVO vo=poToVO(po);
			voList.add(vo);
		}
		return voList;
	}

	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	private MockCommodity mc;

	public Present() {
	}

	public Present(MockCommodity mc) {
		this.mc = mc;
	}

	public ResultMessage afterApproval(int number) {
		return mc.updateNum(number);
	}

	public ResultMessage createLog(String content) {
		MockLog ml = new MockLog(content);
		return ml.create();
	}

}
