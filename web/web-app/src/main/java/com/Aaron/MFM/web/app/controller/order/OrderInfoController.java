package com.Aaron.MFM.web.app.controller.order;

import com.Aaron.MFM.common.Login.LoginHolder;
import com.Aaron.MFM.common.result.Result;

import com.Aaron.MFM.model.entity.OrderInfo;
import com.Aaron.MFM.model.entity.UserInfo;
import com.Aaron.MFM.web.app.aop.LimitAccess;
import com.Aaron.MFM.web.app.service.IOrderStatusService;
import com.Aaron.MFM.web.app.vo.order.OrderInfoVo;
import com.Aaron.MFM.web.app.vo.order.OrderStatusVo;
import com.Aaron.MFM.web.app.vo.order.OrderVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 订单表 前端控制器
 * </p>
 *
 * @author Aaron
 * @since 2024-08-30
 */
@RestController
@RequestMapping("/app/order")
@Tag(name = "app订单管理")
public class OrderInfoController {

    @Autowired
    private IOrderStatusService orderStatusService;

    @PostMapping("/createOrder")
    @Operation(summary = "创建订单")
    @LimitAccess
    public Result<String> createOrder(@RequestBody OrderVo orderInfo) {
        String orderNumber = orderStatusService.addOrder(orderInfo);
        return Result.ok(orderNumber);
    }

    @PostMapping("/updateOrder")
    @Operation(summary = "更新订单")
    @LimitAccess(maxAccessCount = 1)
    public Result<String> updateOrder(@RequestBody OrderStatusVo orderStatusVo) {
        orderStatusService.updateOrder(orderStatusVo);
        return Result.ok("更新成功");
    }

    @GetMapping("/GetOrderById")
    @Operation(summary = "根据订单id获取订单信息")
    @LimitAccess
    public Result<List<OrderInfoVo>> getOrderById(@RequestParam(required = false) Integer id){
        UserInfo loginUser = LoginHolder.getLoginUser();
        return Result.ok(orderStatusService.getOrderById(loginUser.getId(),id));
    }

}
