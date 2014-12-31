/**
 * 单据接口
 * @author JanelDQ
 * @date 2014/10/25
 */
package vo;

import util.DocumentStatus;
import util.DocumentType;

public interface DocumentVO {

    /**
     * 获得单据Id
     * @return
     */
    public String getId();

    /**
     * 获得单据状态
     * @return
     */
    public DocumentStatus getStatus();

    /**
     * 获得单据类型
     * @return
     */
    public DocumentType getType();

    /**
     * 是否红冲单据
     * @return
     */
    public boolean isWriteoff();

    /**
     * 获得时间
     * @return
     */
    public String getTime();

    /**
     * 设置单据状态
     * @param status
     */
    public void setStatus(DocumentStatus status);

}
