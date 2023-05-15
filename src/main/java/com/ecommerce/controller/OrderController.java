package com.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.dto.OrderDto;
import com.ecommerce.responce.GeneralResponse;
import com.ecommerce.services.order.OrderService;

@RestController
@RequestMapping("/api/user/")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // User operations

    @GetMapping("search/{title}")
    public GeneralResponse searchProductByTitle(@PathVariable("title") String title) {
        GeneralResponse response = new GeneralResponse();
        try {
            response.setData(orderService.searchProductByTitle(title));
            response.setStatus(HttpStatus.OK);
            response.setMessage("Products Fetched successfully!");
            return response;
        } catch (Exception e) {
            response.setMessage("Something went wrong!");
            response.setStatus(HttpStatus.BAD_REQUEST);
            return response;
        }
    }

    @GetMapping("products")
    public GeneralResponse getAllProducts() {
        GeneralResponse response = new GeneralResponse();
        try {
            response.setData(orderService.getAllProducts());
            response.setStatus(HttpStatus.OK);
            return response;
        } catch (Exception ex) {
            response.setStatus(HttpStatus.BAD_REQUEST);
            response.setMessage("Sorry Something Wrong Happened.");
            return response;
        }
    }

    @PostMapping("order-product")
    public GeneralResponse addOrderProduct(@RequestBody OrderDto orderDto) {
        GeneralResponse response = new GeneralResponse();
        try {
            return orderService.addOrderProduct(orderDto);
        } catch (Exception ex) {
            response.setStatus(HttpStatus.BAD_REQUEST);
            response.setMessage("Sorry Something Wrong Happened.");
            return response;
        }
    }

    @GetMapping("myOrders/{userId}")
    public GeneralResponse getMyOrdersByUserId(@PathVariable("userId") Long userId) {
        GeneralResponse response = new GeneralResponse();
        try {
            response.setData(orderService.getMyOrdersByUserId(userId));
            response.setStatus(HttpStatus.OK);
            response.setMessage("My Orders Fetched successfully!");
            return response;
        } catch (Exception e) {
            response.setMessage("Something went wrong!");
            response.setStatus(HttpStatus.BAD_REQUEST);
            return response;
        }
    }


}
