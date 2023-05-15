package com.ecommerce.services.order;

import java.util.List;

import com.ecommerce.dto.OrderDto;
import com.ecommerce.dto.ProductDto;
import com.ecommerce.responce.GeneralResponse;

public interface OrderService {

    List<ProductDto> searchProductByTitle(String title);

    GeneralResponse addOrderProduct(OrderDto orderDto);

    List<OrderDto> getMyOrdersByUserId(Long userId);

    List<ProductDto> getAllProducts();
}
