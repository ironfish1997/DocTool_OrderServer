package top.liuliyong.orderserver.repository.impl;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;
import top.liuliyong.orderserver.common.model.OrderEntity;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

/**
 * @Author liyong.liu
 * @Date 2019-05-02
 **/
@Repository
public class OrderRepository {
    private final MongoOperations mongoTemplate;

    public OrderRepository(MongoOperations mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    /**
     * 保存订单
     */
    public OrderEntity save(OrderEntity orderEntity) {
        return mongoTemplate.save(orderEntity);
    }

    /**
     * 通过patient_id_number查找该用户所有订单信息
     */
    public List<OrderEntity> findByPatientIdNumber(String patientIdNumber) {
        return mongoTemplate.find(query(where("account_id").is(patientIdNumber)), OrderEntity.class);
    }

    /**
     * 更新订单
     */
    public OrderEntity updateOrderItem(OrderEntity updateOrderEntity) {
        return mongoTemplate.findAndReplace(query(where("_id").is(new ObjectId(updateOrderEntity.getId()))), updateOrderEntity);
    }

    /**
     * 根据订单id查找订单信息
     */
    public OrderEntity findByOrderId(String id) {
        return mongoTemplate.findOne(query(where("_id").is(new ObjectId(id))), OrderEntity.class);
    }

    /**
     * 根据订单id删除订单
     */
    public OrderEntity deleteOrderItem(String orderId) {
        return mongoTemplate.findAndRemove(query(where("_id").is(new ObjectId(orderId))), OrderEntity.class);
    }
}
