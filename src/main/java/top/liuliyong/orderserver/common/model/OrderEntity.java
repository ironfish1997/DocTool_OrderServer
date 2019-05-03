package top.liuliyong.orderserver.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Objects;

/**
 * 药物订单实体类
 *
 * @Author liyong.liu
 * @Date 2019-04-30
 **/
@Document("items")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderEntity {
    @Id
    private String id;
    //账户id
    @Indexed
    private String account_id;
    //销售员电话
    private long salesman_phone;
    @Indexed
    //销售员名称
    private String salesman_name;
    //额外信息
    private Object extra_meta;
    //证照情况
    private boolean certification_status;
    //供货单位
    private String supply_unit;
    //订单日期
    private long date;
    //订单内药物信息
    private List<MedicineDetailEntity> medicine_list;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderEntity that = (OrderEntity) o;
        return salesman_phone == that.salesman_phone && certification_status == that.certification_status && date == that.date && account_id.equals(that.account_id) && salesman_name.equals(that.salesman_name) && Objects.equals(extra_meta, that.extra_meta) && supply_unit.equals(that.supply_unit) && medicine_list.equals(that.medicine_list);
    }

    @Override
    public int hashCode() {
        return Objects.hash(account_id, salesman_phone, salesman_name, extra_meta, certification_status, supply_unit, date, medicine_list);
    }

    @Override
    public String toString() {
        return "OrderEntity{" + "id='" + id + '\'' + ", account_id='" + account_id + '\'' + ", salesman_phone=" + salesman_phone + ", salesman_name='" + salesman_name + '\'' + ", extra_meta=" + extra_meta + ", certification_status=" + certification_status + ", supply_unit='" + supply_unit + '\'' + ", date=" + date + ", medicine_list=" + medicine_list + '}';
    }
}
