package com.ecommerce.services.vendor;

import java.io.IOException;
import java.util.List;

import com.ecommerce.dto.CategoryDto;
import com.ecommerce.dto.ProductDto;
import com.ecommerce.dto.SecondProductDto;
import com.ecommerce.dto.VendorSingleProductDto;
import com.ecommerce.responce.GeneralResponse;

public interface VendorService {

    //Category operations

    GeneralResponse addCategory(CategoryDto categoryDto);

    List<CategoryDto> getAllCategories();

    //Product operations

    GeneralResponse addProduct(SecondProductDto secondProductDto) throws IOException;

    List<ProductDto> getAllProducts();

    VendorSingleProductDto getProductById(Long productId);

    GeneralResponse updateProduct(Long productId, ProductDto productDto) throws IOException;

    void deleteProduct(Long productId);

    List<ProductDto> searchProductByTitle(String title);

	List<ProductDto> getProductsByVendorId(Long vendorId);

}
