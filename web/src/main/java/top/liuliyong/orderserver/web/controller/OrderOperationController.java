package top.liuliyong.orderserver.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.liuliyong.orderserver.common.model.OrderEntity;
import top.liuliyong.orderserver.service.OrderOperationService;
import top.liuliyong.orderserver.web.response.OrderOperationResponse;

import java.util.List;

/**
 * 订单操作controller
 *
 * @Author liyong.liu
 * @Date 2019-05-02
 **/
@RestController
@Api(value = "order", description = "药物订单服务")
@RequestMapping(path = "/order")
@Validated
@CrossOrigin
public class OrderOperationController {

    private final OrderOperationService orderOperationService;

    public OrderOperationController(OrderOperationService orderOperationService) {
        this.orderOperationService = orderOperationService;
    }

    @PostMapping
    @ApiOperation(value = "新增一项药物订单")
    @ApiImplicitParams({@ApiImplicitParam(name = "orderEntity", value = "订单实体", required = true, dataType = "OrderEntity"),})
    public OrderOperationResponse addOrderItem(@RequestHeader("session_id") String session_id, @RequestBody OrderEntity orderEntity) {
        OrderEntity result = orderOperationService.saveOrderItem(session_id, orderEntity);
        return new OrderOperationResponse(0, "ok", result);
    }

    @GetMapping
    @ApiOperation(value = "根据账号查找所有药物订单")
    @ApiImplicitParams({@ApiImplicitParam(name = "account_id", value = "账户id", required = true, dataType = "String"),})
    public OrderOperationResponse getOrderItem(@RequestParam("account_id") String account_id) {
        List<OrderEntity> result = orderOperationService.findOrdersWithAccountId(account_id);
        return new OrderOperationResponse(0, "ok", result);
    }

    @PutMapping
    @ApiOperation(value = "更新药物订单")
    @ApiImplicitParams({@ApiImplicitParam(name = "updateOrderEntity", value = "更新药物订单实体", required = true, dataType = "OrderEntity"),})
    public OrderOperationResponse updateOrderItem(@RequestBody OrderEntity updateOrderEntity) {
        OrderEntity result = orderOperationService.updateOrderItem(updateOrderEntity);
        return new OrderOperationResponse(0, "ok", result);
    }

    @DeleteMapping
    @ApiOperation(value = "删除药物订单")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "需要删除的订单id", required = true, dataType = "String"),})
    public OrderOperationResponse deleteOrderItem(@RequestParam("id") String orderId) {
        OrderEntity result = orderOperationService.deleteOrderItem(orderId);
        return new OrderOperationResponse(0, "ok", result);
    }
}
