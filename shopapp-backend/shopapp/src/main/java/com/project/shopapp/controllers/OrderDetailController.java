package com.project.shopapp.controllers;
import com.project.shopapp.components.LocalizationUtils;
import com.project.shopapp.exceptions.DataNotFoundException;
import com.project.shopapp.models.dtos.OrderDetailDTO;
import com.project.shopapp.entities.OrderDetail;
import com.project.shopapp.models.responses.OrderDetailResponse;
import com.project.shopapp.services.OrderDetailServiceImpl;
import com.project.shopapp.utils.MessageKeys;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/order_details")
@RequiredArgsConstructor
public class OrderDetailController {
    private final OrderDetailServiceImpl orderDetailServiceImpl;
    private final LocalizationUtils localizationUtils;
    //Thêm mới 1 order detail
    @PostMapping("")
    public ResponseEntity<?> createOrderDetail(
            @Valid  @RequestBody OrderDetailDTO orderDetailDTO) {
        try {
            OrderDetail newOrderDetail = orderDetailServiceImpl.createOrderDetail(orderDetailDTO);
            return ResponseEntity.ok().body(OrderDetailResponse.fromOrderDetail(newOrderDetail));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderDetail(
            @Valid @PathVariable("id") Long id) throws DataNotFoundException {
        OrderDetail orderDetail = orderDetailServiceImpl.getOrderDetail(id);
        return ResponseEntity.ok().body(OrderDetailResponse.fromOrderDetail(orderDetail));
    }
    //lấy ra danh sách các order_details của 1 order nào đó
    @GetMapping("/order/{orderId}")
    public ResponseEntity<?> getOrderDetails(
            @Valid @PathVariable("orderId") Long orderId
    ) {
        List<OrderDetail> orderDetails = orderDetailServiceImpl.findByOrderId(orderId);
        List<OrderDetailResponse> orderDetailResponses = orderDetails
                .stream()
                .map(OrderDetailResponse::fromOrderDetail)
                .toList();
        return ResponseEntity.ok(orderDetailResponses);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrderDetail(
            @Valid @PathVariable("id") Long id,
            @RequestBody OrderDetailDTO orderDetailDTO) {
        try {
            OrderDetail orderDetail = orderDetailServiceImpl.updateOrderDetail(id, orderDetailDTO);
            return ResponseEntity.ok().body(orderDetail);
        } catch (DataNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrderDetail(
            @Valid @PathVariable("id") Long id) {
        orderDetailServiceImpl.deleteById(id);
        return ResponseEntity.ok()
                .body(localizationUtils
                        .getLocalizedMessage(MessageKeys.DELETE_ORDER_DETAIL_SUCCESSFULLY));
    }
}
