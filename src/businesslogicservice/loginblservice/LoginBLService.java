package businesslogicservice.loginblservice;

import util.ResultMessage;
import util.UserType;

public interface LoginBLService {
    
    /**
     * 用户登录
     * @param id
     * @param password
     * @return 结果消息
     */
    public ResultMessage login(int type, String id, String password);
    
    public String getUserName();
    
    public String getUserId();
    
    public UserType getUserType();
}
