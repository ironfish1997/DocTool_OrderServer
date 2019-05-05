package top.liuliyong.orderserver.web.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 订单模块返回信息实体
 *
 * @Author liyong.liu
 * @Date 2019-05-02
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderOperationResponse {
    private int rtn;
    private String msg;
    private Object data;

}
