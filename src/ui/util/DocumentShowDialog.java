package ui.util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import businesslogicservice.approvalblservice.ApprovalBLService;
import ui.exceptionui.ShowExceptionPanel;
import ui.paymentui.ShowCashPanel;
import ui.paymentui.ShowPaymentPanel;
import ui.purchaseui.ShowPurchasePanel;
import ui.saleui.ShowSalePanel;
import util.DocumentStatus;
import util.ResultMessage;
import businesslogic.controllerfactory.ControllerFactoryImpl;
import businesslogicservice.writeoffblservice.WriteoffBLService;
import config.DialogConfig;
import config.ERPConfig;
import vo.*;

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
	private DocumentWriteoffAndCopy panel;
	private WriteoffBLService writeoffCtrl;

	public DocumentShowDialog(JFrame frame, DocumentWriteoffAndCopy panel, int type) {
		super(frame, true);
		this.panel = panel;
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
		jsp.setViewportView((JPanel)this.panel);
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
				if (panel.checkCompleted()) {
					int result = MyOptionPane.showConfirmDialog(frame, "确认创建？",
							"确认提示", MyOptionPane.YES_NO_OPTION,
							MyOptionPane.QUESTION_MESSAGE);
					if (result == MyOptionPane.YES_OPTION) {
						// 创建一张复制单据
						if (writeoffCtrl.create(panel.getDocumentType(),
								panel.getDocumentID()) == ResultMessage.SUCCESS) {
							panel.createCopyDocument();
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
				switch (panel.getDocumentType()) {
					case PRESENT: {
						PresentVO presentVO = ControllerFactoryImpl.getInstance().
								getPresentController().getById(panel.getDocumentID());
						presentVO.documentStatus = DocumentStatus.PASSED;
						controller.approvePresent(presentVO);
						break;
					}
					case OVERFLOW: {
						ExceptionVO exceptionVO = ControllerFactoryImpl.getInstance().getOverflowController().
								getById(panel.getDocumentID());
						exceptionVO.status = DocumentStatus.PASSED;
						controller.approveOverflow(exceptionVO);
						break;
					}
					case LOSS: {
						ExceptionVO exceptionVO = ControllerFactoryImpl.getInstance().getLossController().
								getById(panel.getDocumentID());
						exceptionVO.status = DocumentStatus.PASSED;
						controller.approveLoss(exceptionVO);
						break;
					}
					case SALE: {
						SaleVO saleVO = ControllerFactoryImpl.getInstance().
								getSaleController().getById(panel.getDocumentID());
						saleVO.approvalState = DocumentStatus.PASSED;
						controller.approveSale(saleVO);
						break;
					}
					case SALERETURN: {
						SaleVO saleVO = ControllerFactoryImpl.getInstance().
								getSaleReturnController().getById(panel.getDocumentID());
						saleVO.approvalState = DocumentStatus.PASSED;
						controller.approveSaleReturn(saleVO);
						break;
					}
					case PURCHASE: {
						PurchaseVO purchaseVO = ControllerFactoryImpl.getInstance().getPurchaseController()
								.getById(panel.getDocumentID());
						purchaseVO.documentStatus = DocumentStatus.PASSED;
						controller.approvePurchase(purchaseVO);
						break;
					}
					case PURCHASERETURN: {
						PurchaseVO purchaseVO = ControllerFactoryImpl.getInstance().getPurchaseReturnController()
								.getById(panel.getDocumentID());
						purchaseVO.documentStatus = DocumentStatus.PASSED;
						controller.approvePurchaseReturn(purchaseVO);
						break;
					}
					case RECEIPT: {
						PaymentVO paymentVO = ControllerFactoryImpl.
								getInstance().getReceiptController().getById(panel.getDocumentID());
						paymentVO.approvalState = DocumentStatus.PASSED;
						controller.approveReceipt(paymentVO);
						break;
					}
					case PAYMENT: {
						PaymentVO paymentVO = ControllerFactoryImpl.getInstance().
								getPaymentController().getById(panel.getDocumentID());
						paymentVO.approvalState = DocumentStatus.PASSED;
						controller.approvePayment(paymentVO);
						break;
					}
					case CASH: {
						CashVO cashVO = ControllerFactoryImpl.getInstance().
								getCashController().getById(panel.getDocumentID());
						cashVO.approvalState = DocumentStatus.PASSED;
						controller.approveCash(cashVO);
						break;
					}
				}
				dispose();
			}
		});
		// 审批不通过按钮
		this.rejectBtn = new MyButton(cfg.getButtons().element("reject"));
		this.rejectBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 审批不通过
			}
		});
		// 红冲按钮
		this.writeoffBtn = new MyButton(cfg.getButtons().element("writeoff"));
		this.writeoffBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 红冲
				writeoffCtrl.create(panel.getDocumentType(),
						panel.getDocumentID());
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
