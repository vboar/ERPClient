package ui.util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import util.DocumentStatus;
import util.ResultMessage;
import vo.CashVO;
import vo.ExceptionVO;
import vo.PaymentVO;
import vo.PresentVO;
import vo.PurchaseVO;
import vo.SaleVO;
import businesslogic.controllerfactory.ControllerFactoryImpl;
import businesslogicservice.approvalblservice.ApprovalBLService;
import businesslogicservice.writeoffblservice.WriteoffBLService;
import config.DialogConfig;
import config.ERPConfig;

@SuppressWarnings("serial")
public class DocumentShowDialog extends JDialog {

	private int type;

	private MyButton passBtn;
	private MyButton rejectBtn;
	private MyButton copyBtn;
	private MyButton writeoffBtn;
	private MyButton cancelBtn;

	private JFrame frame;
	private JScrollPane jsp;
	
	private DialogConfig cfg;
	private DocumentShowDialogExtra extra;
	private WriteoffBLService writeoffCtrl;
	private UpdateTableData updateTableData;

	public DocumentShowDialog(JFrame frame, DocumentShowDialogExtra extra, UpdateTableData updateTableData, int type) {
		super(frame, true);
		((JComponent) this.getContentPane()).setOpaque(true);
		this.updateTableData = updateTableData;
		this.extra = extra;
		this.type = type;
		this.cfg = ERPConfig.getSHOWDOCUMENT_DIALOG_CONFIG();
		this.writeoffCtrl = ControllerFactoryImpl.getInstance().getWriteoffController();
		// 设置对话框基本属性
		this.setTitle(cfg.getTitle());
		this.setBounds(cfg.getX(), cfg.getW(), cfg.getW(), cfg.getH());
        this.setLayout(null);
        this.setResizable(false);
        this.setLocation(frame.getX()+this.cfg.getX(), frame.getY()+this.cfg.getY());
		this.initComponent();
		this.setVisible(true);
	}
	
	private void initComponent() {
		this.initButtons();
		jsp = new JScrollPane();
		jsp.setBounds(0, 0, this.getWidth()-5, this.getHeight()-90);
		jsp.setViewportView((JPanel)this.extra);
		this.add(jsp);
	}
	
	public void initButtons() {
		// 取消按钮
		this.cancelBtn = new MyButton(cfg.getButtons().element("cancel"));
		this.cancelBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = MyOptionPane.showConfirmDialog(frame, "是否取消当前操作？",
						"确认提示", MyOptionPane.YES_NO_OPTION,
						MyOptionPane.QUESTION_MESSAGE);
				if (result == MyOptionPane.YES_OPTION) {
					DocumentShowDialog.this.dispose();
				}
			}
		});
		// 复制按钮
		this.copyBtn = new MyButton(cfg.getButtons().element("copy"));
		this.copyBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (extra.checkCompleted()) {
					int result = MyOptionPane.showConfirmDialog(frame, "确认创建？",
							"确认提示", MyOptionPane.YES_NO_OPTION,
							MyOptionPane.QUESTION_MESSAGE);
					if (result == MyOptionPane.YES_OPTION) {
						// 创建一张复制单据
						if (writeoffCtrl.create(extra.getDocumentType(),
								extra.getDocumentID()) == ResultMessage.SUCCESS) {
							extra.createCopyDocument();
							dispose();
						} else {
							MyOptionPane.showMessageDialog(frame, "红冲操作失败！");
						}
					}
				} else {
					MyOptionPane.showMessageDialog(frame, "请填入完整单据信息！");
				}
			}
		});
		// 审批通过按钮
		this.passBtn = new MyButton(cfg.getButtons().element("check"));
		this.passBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 审批通过
				ApprovalBLService controller = ControllerFactoryImpl.getInstance().getApprovalController();
				switch (extra.getDocumentType()) {
					case PRESENT: {
						PresentVO presentVO = ControllerFactoryImpl.getInstance().
								getPresentController().getById(extra.getDocumentID());
						presentVO.documentStatus = DocumentStatus.PASSED;
						controller.approvePresent(presentVO);
						break;
					}
					case OVERFLOW: {
						ExceptionVO exceptionVO = ControllerFactoryImpl.getInstance().getOverflowController().
								getById(extra.getDocumentID());
						exceptionVO.status = DocumentStatus.PASSED;
						controller.approveOverflow(exceptionVO);
						break;
					}
					case LOSS: {
						ExceptionVO exceptionVO = ControllerFactoryImpl.getInstance().getLossController().
								getById(extra.getDocumentID());
						exceptionVO.status = DocumentStatus.PASSED;
						controller.approveLoss(exceptionVO);
						break;
					}
					case SALE: {
						SaleVO saleVO = ControllerFactoryImpl.getInstance().
								getSaleController().getById(extra.getDocumentID());
						saleVO.approvalState = DocumentStatus.PASSED;
						controller.approveSale(saleVO);
						break;
					}
					case SALERETURN: {
						SaleVO saleVO = ControllerFactoryImpl.getInstance().
								getSaleReturnController().getById(extra.getDocumentID());
						saleVO.approvalState = DocumentStatus.PASSED;
						controller.approveSaleReturn(saleVO);
						break;
					}
					case PURCHASE: {
						PurchaseVO purchaseVO = ControllerFactoryImpl.getInstance().getPurchaseController()
								.getById(extra.getDocumentID());
						purchaseVO.documentStatus = DocumentStatus.PASSED;
						controller.approvePurchase(purchaseVO);
						break;
					}
					case PURCHASERETURN: {
						PurchaseVO purchaseVO = ControllerFactoryImpl.getInstance().getPurchaseReturnController()
								.getById(extra.getDocumentID());
						purchaseVO.documentStatus = DocumentStatus.PASSED;
						controller.approvePurchaseReturn(purchaseVO);
						break;
					}
					case RECEIPT: {
						PaymentVO paymentVO = ControllerFactoryImpl.
								getInstance().getReceiptController().getById(extra.getDocumentID());
						paymentVO.approvalState = DocumentStatus.PASSED;
						controller.approveReceipt(paymentVO);
						break;
					}
					case PAYMENT: {
						PaymentVO paymentVO = ControllerFactoryImpl.getInstance().
								getPaymentController().getById(extra.getDocumentID());
						paymentVO.approvalState = DocumentStatus.PASSED;
						controller.approvePayment(paymentVO);
						break;
					}
					case CASH: {
						CashVO cashVO = ControllerFactoryImpl.getInstance().
								getCashController().getById(extra.getDocumentID());
						cashVO.approvalState = DocumentStatus.PASSED;
						controller.approveCash(cashVO);
						break;
					}
				}
				updateTableData.updateTableData();
				dispose();
			}
		});
		// 审批不通过按钮
		this.rejectBtn = new MyButton(cfg.getButtons().element("reject"));
		this.rejectBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ApprovalBLService controller = ControllerFactoryImpl.getInstance().getApprovalController();
				switch (extra.getDocumentType()) {
					case PRESENT: {
						PresentVO presentVO = ControllerFactoryImpl.getInstance().
								getPresentController().getById(extra.getDocumentID());
						presentVO.documentStatus = DocumentStatus.FAILED;
						controller.approvePresent(presentVO);
						break;
					}
					case OVERFLOW: {
						ExceptionVO exceptionVO = ControllerFactoryImpl.getInstance().getOverflowController().
								getById(extra.getDocumentID());
						exceptionVO.status = DocumentStatus.FAILED;
						controller.approveOverflow(exceptionVO);
						break;
					}
					case LOSS: {
						ExceptionVO exceptionVO = ControllerFactoryImpl.getInstance().getLossController().
								getById(extra.getDocumentID());
						exceptionVO.status = DocumentStatus.FAILED;
						controller.approveLoss(exceptionVO);
						break;
					}
					case SALE: {
						SaleVO saleVO = ControllerFactoryImpl.getInstance().
								getSaleController().getById(extra.getDocumentID());
						saleVO.approvalState = DocumentStatus.FAILED;
						controller.approveSale(saleVO);
						break;
					}
					case SALERETURN: {
						SaleVO saleVO = ControllerFactoryImpl.getInstance().
								getSaleReturnController().getById(extra.getDocumentID());
						saleVO.approvalState = DocumentStatus.FAILED;
						controller.approveSaleReturn(saleVO);
						break;
					}
					case PURCHASE: {
						PurchaseVO purchaseVO = ControllerFactoryImpl.getInstance().getPurchaseController()
								.getById(extra.getDocumentID());
						purchaseVO.documentStatus = DocumentStatus.FAILED;
						controller.approvePurchase(purchaseVO);
						break;
					}
					case PURCHASERETURN: {
						PurchaseVO purchaseVO = ControllerFactoryImpl.getInstance().getPurchaseReturnController()
								.getById(extra.getDocumentID());
						purchaseVO.documentStatus = DocumentStatus.FAILED;
						controller.approvePurchaseReturn(purchaseVO);
						break;
					}
					case RECEIPT: {
						PaymentVO paymentVO = ControllerFactoryImpl.
								getInstance().getReceiptController().getById(extra.getDocumentID());
						paymentVO.approvalState = DocumentStatus.FAILED;
						controller.approveReceipt(paymentVO);
						break;
					}
					case PAYMENT: {
						PaymentVO paymentVO = ControllerFactoryImpl.getInstance().
								getPaymentController().getById(extra.getDocumentID());
						paymentVO.approvalState = DocumentStatus.FAILED;
						controller.approvePayment(paymentVO);
						break;
					}
					case CASH: {
						CashVO cashVO = ControllerFactoryImpl.getInstance().
								getCashController().getById(extra.getDocumentID());
						cashVO.approvalState = DocumentStatus.FAILED;
						controller.approveCash(cashVO);
						break;
					}
				}
				updateTableData.updateTableData();
				dispose();
			}

		});
		// 红冲按钮
		this.writeoffBtn = new MyButton(cfg.getButtons().element("writeoff"));
		this.writeoffBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 红冲
				ResultMessage result = writeoffCtrl.create(extra.getDocumentType(),
						extra.getDocumentID());
				if(result==ResultMessage.SUCCESS){
					MyOptionPane.showMessageDialog(frame, "红冲成功！");
				}else{
					MyOptionPane.showMessageDialog(frame, "红冲失败！");
				}
				updateTableData.updateTableData();
				DocumentShowDialog.this.dispose();
			}
		});
		if (type == 0) {
			this.add(passBtn);
			this.add(rejectBtn);
			this.add(cancelBtn);
		} else if (type == 1) {
			this.add(writeoffBtn);
			this.add(cancelBtn);
		} else {
			this.add(copyBtn);
			this.add(cancelBtn);
		}
	}
}
