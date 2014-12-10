/**
 * 单据接口
 * @author JanelDQ
 * @date 2014/10/25
 */
package vo;

import util.DocumentStatus;
import util.DocumentType;

public interface DocumentVO {

    public String getId();

    public DocumentStatus getStatus();

    public DocumentType getType();

    public boolean isWriteoff();

    public String getTime();

    public void setStatus(DocumentStatus status);

}
