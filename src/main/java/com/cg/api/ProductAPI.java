package com.cg.api;

import java.util.*;
import com.cg.exception.DataInputException;
import com.cg.model.Category;
import com.cg.model.Product;
import com.cg.model.dto.product.*;
import com.cg.service.category.ICategoryService;
import com.cg.service.product.IProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class    ProductAPI {

    @Autowired
    private IProductService productService;

    @Autowired
    private ICategoryService categoryService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<ProductDTO> productDTOList = productService.findAllProductDTO();

        return new ResponseEntity<>(productDTOList, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @PostMapping
    public ResponseEntity<?> create(@ModelAttribute ProductCreateReqDTO productCreateReqDTO) {

        Category category = categoryService.findById(productCreateReqDTO.getCategoryId()).orElseThrow(() -> {
            throw new DataInputException("Danh mục không tồn tại");
        });

        Product product = productService.create(productCreateReqDTO, category);
        ProductCreateResDTO productCreateResDTO = product.toProductCreateResDTO();

        return new ResponseEntity<>(productCreateResDTO, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @PatchMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @ModelAttribute ProductUpdateReqDTO productUpdateReqDTO, BindingResult bindingResult) {

        new ProductUpdateReqDTO().validate(productUpdateReqDTO, bindingResult);


        Long productId = Long.parseLong(id);

        Optional<Product> productOptional = productService.findById(productId);

        Category category = categoryService.findById(productUpdateReqDTO.getCategoryId()).orElseThrow(() -> {
            throw new DataInputException("Danh mục không tồn tại");
        });

        if (productUpdateReqDTO.getAvatar() == null) {
            Product product = productUpdateReqDTO.toProductNoChangeImage(category);
            product.setId(productOptional.get().getId());
            product.setProductAvatar(productOptional.get().getProductAvatar());
            productService.save(product);
            return new ResponseEntity<>(product.toProductCreateResDTO(), HttpStatus.OK);
        }

        if (productOptional.isPresent()) {
            Product product = productService.update(productOptional.get().getId() ,productUpdateReqDTO, category);
            ProductUpdateResDTO productUpdateResDTO = product.toProductUpdateResDTO();

            return new ResponseEntity<>(productUpdateResDTO, HttpStatus.OK);
        } else {
            throw new DataInputException("Invalid product information");
        }
}}
