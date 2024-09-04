package com.Aaron.MFM.web.admin.controller.order;

import com.Aaron.MFM.common.result.Result;
import com.Aaron.MFM.web.admin.service.IOrderInfoService;
import com.Aaron.MFM.web.admin.vo.order.OrderInfoVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
@RequestMapping("/admin/order")
@Tag(name = "后台订单管理")
public class OrderInfoController {

    @Autowired
    private IOrderInfoService orderInfoService;


    @Operation(summary = "获取订单列表")
    @GetMapping("/getOrderList")
    public Result<List<OrderInfoVo>> getOrderList() {
        List<OrderInfoVo> list = orderInfoService.getOrderList();
        System.out.println(list);
        return Result.ok(list);
    }

    @GetMapping("/getOrderById")
    @Operation(summary = "根据订单id获取订单信息")
    public Result<OrderInfoVo> getOrderById(@RequestParam Integer id) {
        OrderInfoVo orderInfoVo = orderInfoService.getOrderById(id);
        return Result.ok(orderInfoVo);
    }

    @DeleteMapping("/deleteOrder")
    @Operation(summary = "删除订单")
    public Result<String> deleteOrder(@RequestParam Integer id) {
        orderInfoService.removeById(id);
        return Result.ok("删除成功");
    }

    @Operation(summary = "修改订单状态")
    @GetMapping("/upOrderStatus")
    public Result<String> upOrderStatus(@RequestParam Integer id,@RequestParam Integer statusId) {
        orderInfoService.upOrderStatus(id, statusId);
        return Result.ok("修改成功");
    }

    @GetMapping("/searchByDate")
    @Operation(summary = "根据日期搜索订单")
    public Result<List<OrderInfoVo>> search(@RequestParam String dataString) {
        List<OrderInfoVo> list = orderInfoService.searchByDate(dataString);
        return Result.ok(list);
    }
}
