package com.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.dto.CategoryDto;
import com.ecommerce.dto.ProductDto;
import com.ecommerce.dto.SecondProductDto;
import com.ecommerce.responce.GeneralResponse;
import com.ecommerce.services.vendor.VendorService;

@RestController
@RequestMapping("/api/admin/")
public class VendorController {

    @Autowired
    private VendorService vendorService;

    //Category operations

    @PostMapping("category")
    public GeneralResponse addCategory(@RequestBody CategoryDto categoryDto) {
        GeneralResponse response = new GeneralResponse();
        try {
            return vendorService.addCategory(categoryDto);
        } catch (Exception ex) {
            response.setStatus(HttpStatus.BAD_REQUEST);
            response.setMessage("Sorry Something Wrong Happened.");
            return response;
        }
    }

    @GetMapping("categories")
    public GeneralResponse getAllCategories() {
        GeneralResponse response = new GeneralResponse();
        try {
            response.setData(vendorService.getAllCategories());
            response.setStatus(HttpStatus.OK);
            return response;
        } catch (Exception ex) {
            response.setStatus(HttpStatus.BAD_REQUEST);
            response.setMessage("Sorry Something Wrong Happened.");
            return response;
        }
    }

    //Product operations

    @PostMapping("product")
    public GeneralResponse addProduct(@ModelAttribute SecondProductDto secondProductDto) {
        GeneralResponse response = new GeneralResponse();
        try {
            return vendorService.addProduct(secondProductDto);
        } catch (Exception ex) {
            response.setStatus(HttpStatus.BAD_REQUEST);
            response.setMessage("Sorry Something Wrong Happened.");
            return response;
        }
    }

    @GetMapping("products/{vendorId}")
    public GeneralResponse getAllProducts(@PathVariable Long vendorId) {
        GeneralResponse response = new GeneralResponse();
        try {
            response.setData(vendorService.getProductsByVendorId(vendorId));
            response.setStatus(HttpStatus.OK);
            return response;
        } catch (Exception ex) {
            response.setStatus(HttpStatus.BAD_REQUEST);
            response.setMessage("Sorry Something Wrong Happened.");
            return response;
        }
    }

    @GetMapping("product/{productId}")
    public GeneralResponse getCarById(@PathVariable Long productId) {
        GeneralResponse response = new GeneralResponse();
        try {
            response.setData(vendorService.getProductById(productId));
            response.setStatus(HttpStatus.OK);
            return response;
        } catch (Exception ex) {
            response.setStatus(HttpStatus.BAD_REQUEST);
            response.setMessage("Sorry Something Wrong Happened.");
            return response;
        }
    }

    @PutMapping("product/{productId}")
    public GeneralResponse updateProduct(@PathVariable Long productId,@ModelAttribute ProductDto productDto) {
        GeneralResponse response = new GeneralResponse();
        try {
            return (vendorService.updateProduct(productId,productDto));
        } catch (Exception e) {
            response.setStatus(HttpStatus.BAD_REQUEST);
            response.setMessage("Sorry Something went wrong!");
            return response;
        }
    }

    @DeleteMapping("product/{productId}")
    public GeneralResponse deleteProduct(@PathVariable Long productId) {
        GeneralResponse response = new GeneralResponse();
        try {
            vendorService.deleteProduct(productId);
            response.setStatus(HttpStatus.OK);
            response.setMessage("Product Deleted Successfully");
            return response;
        } catch (Exception ex) {
            response.setStatus(HttpStatus.BAD_REQUEST);
            response.setMessage("Sorry Something Wrong Happened.");
            return response;
        }
    }

    @GetMapping("/search/{title}")
    public GeneralResponse searchProductByTitle(@PathVariable("title") String title) {
        GeneralResponse response = new GeneralResponse();
        try {
            response.setData(vendorService.searchProductByTitle(title));
            response.setStatus(HttpStatus.OK);
            response.setMessage("Products Fetched successfully!");
            return response;
        } catch (Exception e) {
            response.setMessage("Something went wrong!");
            response.setStatus(HttpStatus.BAD_REQUEST);
            return response;
        }
    }

}
