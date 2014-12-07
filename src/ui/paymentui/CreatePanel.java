package ui.paymentui;

import vo.TransferLineItemVO;

import java.util.ArrayList;

public interface CreatePanel {
	
	public void addAccount(TransferLineItemVO vo);
	
	public void deleteAccount();

	public ArrayList<TransferLineItemVO> getLists();
	
}
