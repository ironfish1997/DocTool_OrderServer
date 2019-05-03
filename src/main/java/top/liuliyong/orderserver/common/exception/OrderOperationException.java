package top.liuliyong.orderserver.common.exception;

import lombok.Getter;
import top.liuliyong.orderserver.common.enums.StatusEnum;

/**
 * @Author liyong.liu
 * @Date 2019-05-02
 **/
@Getter
public class OrderOperationException extends RuntimeException {
    private int rtn;
    private String msg;

    public OrderOperationException(int rtn, String msg) {
        this.rtn = rtn;
        this.msg = msg;
    }

    public OrderOperationException(StatusEnum status) {
        this.rtn = status.getCode();
        this.msg = status.getMsg();
    }
}
