package com.ecommerce.services.order;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ecommerce.dto.OrderDto;
import com.ecommerce.dto.ProductDto;
import com.ecommerce.entity.Order;
import com.ecommerce.entity.Product;
import com.ecommerce.entity.User;
import com.ecommerce.repo.OrderRepo;
import com.ecommerce.repo.ProductRepo;
import com.ecommerce.repo.UserRepo;
import com.ecommerce.responce.GeneralResponse;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ProductRepo productRepo;

    @Override
    public List<ProductDto> searchProductByTitle(String title) {
        return productRepo.findAllByNameContaining(title).stream().map(Product::getProductDto).collect(Collectors.toList());
    }

    @Override
    public GeneralResponse addOrderProduct(OrderDto orderDto) {
        GeneralResponse response = new GeneralResponse();
        Product product = null;
        User user = null;
        Optional<Product> optionalProduct = productRepo.findById(orderDto.getProductId());
        Optional<User> optionalUser = userRepo.findById(orderDto.getUserId());
        if (optionalProduct.isPresent() && optionalUser.isPresent()) {
            Order order = new Order();
            product = optionalProduct.get();
            user = optionalUser.get();
            order.setDate(new Date());
            order.setProduct(product);
            order.setUser(user);
            orderRepo.save(order);
            response.setMessage("Order placed Successfully");
            response.setStatus(HttpStatus.CREATED);
            return response;
        } else {
            response.setStatus(HttpStatus.NOT_ACCEPTABLE);
            response.setMessage("Product or User not found!");
        }
        return response;
    }

    @Override
    public List<OrderDto> getMyOrdersByUserId(Long userId) {
        return orderRepo.findAllByUserId(userId).stream().map(Order::getOrderDto).collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> getAllProducts() {
        return productRepo.findAll().stream().map(Product::getProductDto).collect(Collectors.toList());
    }


}

