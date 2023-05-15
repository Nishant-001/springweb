package com.ecommerce.services.vendor;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ecommerce.dto.CategoryDto;
import com.ecommerce.dto.ProductDto;
import com.ecommerce.dto.SecondProductDto;
import com.ecommerce.dto.VendorSingleProductDto;
import com.ecommerce.entity.Category;
import com.ecommerce.entity.Product;
import com.ecommerce.entity.User;
import com.ecommerce.repo.CategoryRepo;
import com.ecommerce.repo.ProductRepo;
import com.ecommerce.repo.UserRepo;
import com.ecommerce.responce.GeneralResponse;

@Service
public class VendorServiceImpl implements VendorService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private UserRepo userRepo;

    //Category operations

    @Override
    public GeneralResponse addCategory(CategoryDto categoryDto) {
        GeneralResponse response = new GeneralResponse();
        try {
            Category category = new Category();
            category.getCategoryEntity(categoryDto);
            categoryRepo.save(category);
            response.setMessage("Category Added Successfully");
            response.setStatus(HttpStatus.CREATED);
            return response;
        } catch (Exception e) {
            response.setStatus(HttpStatus.NOT_ACCEPTABLE);
            response.setMessage("Image Not processable");
            return response;
        }
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        return categoryRepo.findAll().stream().map(Category::getCategoryDto).collect(Collectors.toList());
    }

    //Product operations

    @Override
    public GeneralResponse addProduct(SecondProductDto secondProductDto) throws IOException {
    	
    	System.out.println();
        
        System.out.println("Here");
        System.out.println();
        
        GeneralResponse response = new GeneralResponse();
        Long categoryId = Long.parseLong(secondProductDto.getCategoryId());
        Optional<Category> optionalCategory = categoryRepo.findById(categoryId);
        
        System.out.println("CatergotyId:"+secondProductDto.getCategoryId());
        System.out.println("UserId:"+secondProductDto.getUserId().toString());
        
        Long userId = Long.parseLong(secondProductDto.getUserId());
        Optional<User> optionalUser = userRepo.findById(userId);
        
        System.out.println("UserId:"+optionalUser.toString());
        
        System.out.println();
        
        System.out.println("Here");
        System.out.println();
        
        
        if (optionalCategory.isPresent()) {
            Product product = new Product();
            product.setName(secondProductDto.getName());
            product.setCode(secondProductDto.getCode());
            product.setDiscount(secondProductDto.getDiscount());
            product.setPrice(secondProductDto.getPrice());
            product.setDescription(secondProductDto.getDescription());
            product.setImg(secondProductDto.getImg().getBytes());
            product.setCategory(optionalCategory.get());
            product.setUser(optionalUser.get());
            productRepo.save(product);
            response.setMessage("Product posted Successfully");
            response.setStatus(HttpStatus.CREATED);
            return response;
        } else {
            response.setStatus(HttpStatus.NOT_ACCEPTABLE);
            response.setMessage("Category or User not found!");
        }
        return response;
    }

    @Override
    public List<ProductDto> getAllProducts() {
        return productRepo.findAll().stream().map(Product::getProductDto).collect(Collectors.toList());
    }

    @Override
    public VendorSingleProductDto getProductById(Long productId) {
        VendorSingleProductDto vendorSingleProductDto = new VendorSingleProductDto();
        Optional<Product> optionalProduct = productRepo.findById(productId);
        if (optionalProduct.isPresent()) {
            vendorSingleProductDto.setProductDto(optionalProduct.get().getProductDto());
        }
        return vendorSingleProductDto;
    }

    @Override
    public GeneralResponse updateProduct(Long productId, ProductDto productDto) throws IOException {
        GeneralResponse response = new GeneralResponse();
        Long categoryId = (productDto.getCategoryId());
        Optional<Category> optionalCategory = categoryRepo.findById(categoryId);
        Optional<Product> optionalProduct = productRepo.findById(productId);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            if (productDto.getImg() != null) {
                product.setImg(productDto.getImg().getBytes());
            }
            product.setDiscount(productDto.getDiscount());
            product.setName(productDto.getName());
            product.setCode(productDto.getCode());
            product.setDescription(productDto.getDescription());
            product.setPrice(productDto.getPrice());
            product.setCategory(optionalCategory.get());
            productRepo.save(product);
            response.setMessage("Product updated Successfully");
            response.setStatus(HttpStatus.OK);
            return response;
        } else {
            response.setStatus(HttpStatus.NOT_ACCEPTABLE);
            response.setMessage("Product not found!");
        }
        return response;
    }

    @Override
    public void deleteProduct(Long productId) {
        productRepo.deleteById(productId);
    }

    @Override
    public List<ProductDto> searchProductByTitle(String title) {
        return productRepo.findAllByNameContaining(title).stream().map(Product::getProductDto).collect(Collectors.toList());
    }

	@Override
	public List<ProductDto> getProductsByVendorId(Long vendorId) {
        return productRepo.findAllByUserId(vendorId).stream().map(Product::getProductDto).collect(Collectors.toList());

	}

}
