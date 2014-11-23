package businesslogicservice.loginblservice;

import util.ResultMessage;

public interface LoginBLService {
    
    /**
     * 用户登录
     * @param id
     * @param password
     * @return 结果消息
     */
    public ResultMessage login(String id, String password);
}
