package com.Aaron.MFM.web.app.controller.order;

import com.Aaron.MFM.common.result.Result;
import com.Aaron.MFM.web.app.service.ISalesPromotionService;
import com.Aaron.MFM.web.app.vo.order.SalesPromotionVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import java.util.List;

/**
 * <p>
 * 促销表 前端控制器
 * </p>
 *
 * @author Aaron
 * @since 2024-08-30
 */
@RestController
@RequestMapping("/app/salePromotion")
@Tag(name = "app促销管理")
public class SalesPromotionController {

    @Autowired
    private ISalesPromotionService salesPromotionService;


    @GetMapping("/getSalePromotionList")
    @Operation(summary = "获取促销列表")
    public Result<List<SalesPromotionVo>> getSalePromotionList() {
        return Result.ok(salesPromotionService.getSalePromotionList());
    }

    @GetMapping("/snapped")
    @Operation(summary = "抢购订单")
    public Result<String> snapped(Integer id) {
        salesPromotionService.snapped(id);
        return Result.ok("抢购成功,请稍后到订单页面查看");
    }

}
