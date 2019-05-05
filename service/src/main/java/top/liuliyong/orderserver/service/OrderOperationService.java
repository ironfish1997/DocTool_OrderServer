package top.liuliyong.orderserver.service.impl;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.stereotype.Service;
import top.liuliyong.account.client.AccountClient;
import top.liuliyong.account.common.response.AccountOperationResponse;
import top.liuliyong.orderserver.common.enums.StatusEnum;
import top.liuliyong.orderserver.common.exception.OrderOperationException;
import top.liuliyong.orderserver.common.model.OrderEntity;
import top.liuliyong.orderserver.repository.impl.OrderRepository;

import java.util.List;

/**
 * @Author liyong.liu
 * @Date 2019-05-02
 **/
@Slf4j
@Service
@NoArgsConstructor
@AllArgsConstructor
public class OrderOperationService {

    private OrderRepository orderRepository;
    private AccountClient accountClient;


    /**
     * 保存药物订单
     */
    public OrderEntity saveOrderItem(String session_id, OrderEntity orderEntity) throws OrderOperationException {
        if (orderEntity == null) {
            log.error("未传入药物订单数据");
            throw new OrderOperationException(StatusEnum.LACK_OF_INFORMATION);
        }
        AccountOperationResponse checkAccount = null;
        try {
            checkAccount = accountClient.findAccountByAccountId(session_id, orderEntity.getAccount_id());
        } catch (Exception e) {
            log.error("feign查询账户信息出错", e);
        }
        if (checkAccount == null || checkAccount.getRtn() != 0 || checkAccount.getData() == null) {
            log.error("未找到对应的账户,id===>{}", orderEntity.getAccount_id());
            throw new OrderOperationException(StatusEnum.NOT_FOUND);
        }
        return orderRepository.save(orderEntity);
    }


    /**
     * 根据账户id找到所有订单
     */
    public List<OrderEntity> findOrdersWithAccountId(String account_id) {
        if (account_id == null || account_id.trim().length() == 0) {
            log.error("未传入account_id");
            throw new OrderOperationException(StatusEnum.LACK_OF_INFORMATION);
        }
        return orderRepository.findByPatientIdNumber(account_id);
    }

    /**
     * 更新药物订单信息
     */
    public OrderEntity updateOrderItem(OrderEntity updateOrderEntity) {
        if (updateOrderEntity == null) {
            log.error("未传入更新数据实体");
            throw new OrderOperationException(StatusEnum.LACK_OF_INFORMATION);
        }
        //1.查找mongo中是否已经存在该订单
        OrderEntity oriOrder = orderRepository.findByOrderId(updateOrderEntity.getId());
        if (oriOrder == null) {
            log.error("mongo中不存在该订单:{}", updateOrderEntity.toString());
            throw new OrderOperationException(StatusEnum.NOT_FOUND);
        }
        //2.把更新信息合并进原始订单对象
        BeanMap beanMap = BeanMap.create(updateOrderEntity);
        BeanMap resultMap = BeanMap.create(oriOrder);
        for (Object key : beanMap.keySet()) {
            String keyStr = (String) key;
            Object fieldObj = beanMap.get(keyStr);
            if (fieldObj != null) {
                resultMap.put(keyStr, fieldObj);
            }
        }
        //3.执行更新
        orderRepository.updateOrderItem(oriOrder);
        return oriOrder;
    }

    /**
     * 删除单笔订单
     */
    public OrderEntity deleteOrderItem(String orderId) {
        if (orderId == null || orderId.trim().length() == 0) {
            log.error("删除订单需要传入orderId，而你没有传,所以我要报错");
            throw new OrderOperationException(StatusEnum.LACK_OF_INFORMATION);
        }
        return orderRepository.deleteOrderItem(orderId);
    }
}
