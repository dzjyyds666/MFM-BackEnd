package com.Aaron.MFM.web.admin.controller.order;

import com.Aaron.MFM.common.result.Result;
import com.Aaron.MFM.model.entity.OrderStatus;
import com.Aaron.MFM.web.admin.aop.LimitAccess;
import com.Aaron.MFM.web.admin.service.IOrderStatusService;
import com.Aaron.MFM.web.admin.vo.order.OrderInfoVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * <p>
 * 订单状态表 前端控制器
 * </p>
 *
 * @author Aaron
 * @since 2024-08-30
 */
@RestController
@RequestMapping("/admin/order")
@Tag(name = "后台订单状态管理")
public class OrderStatusController {

    @Autowired
    private IOrderStatusService orderStatusService;

    @GetMapping("/getOrderStatusList")
    @Operation(summary = "获取订单状态列表")
    @LimitAccess
    public Result<List<OrderStatus>> getOrderStatusList() {
        List<OrderStatus> list = orderStatusService.list();
        return Result.ok(list);
    }


    @Operation(summary = "添加或修改订单状态")
    @PostMapping("/addOrUpdateOrderStatus")
    @LimitAccess
    public Result<String> addOrUpdateOrderStatus(@RequestBody OrderStatus orderStatus) {
        if(StringUtils.hasText(orderStatus.getStatusName()) == false){
            return Result.fail(201,"订单状态不能为空");
        }
        orderStatusService.saveOrUpdate(orderStatus);
        return Result.ok("成功");
    }

    @Operation(summary = "删除订单状态")
    @DeleteMapping("/deleteOrderStatus")
    @LimitAccess
    public Result<String> deleteOrderStatus(@RequestParam Integer id) {
        orderStatusService.removeById(id);
        return Result.ok("成功");
    }

}
