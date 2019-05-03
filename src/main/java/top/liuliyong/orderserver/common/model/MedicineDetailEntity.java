package top.liuliyong.orderserver.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 订单内药物信息实体文档
 *
 * @Author liyong.liu
 * @Date 2019-04-30
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicineDetailEntity {
    //药物名称
    private String medicine_name;
    //药物规格
    private String size;
    //剂型
    private String formulation;
    //生产厂家名称
    private String produce_company;
    //批注文号
    private String approval_number;
    //批号
    private String batch_number;
    //单价
    private double unit_price;
    //单位
    private String unit;
    //数量
    private long count;
    //有效期
    private long expire_date;
}
