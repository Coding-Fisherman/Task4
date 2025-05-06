package com.project.likelion13thbe.domain.product.controller;


import com.project.likelion13thbe.domain.product.dto.request.ProductReqDTO;
import com.project.likelion13thbe.domain.product.dto.response.ProductResDTO;
import com.project.likelion13thbe.domain.product.repository.ProductRepository;
import com.project.likelion13thbe.domain.product.service.command.ProductCommandService;
import com.project.likelion13thbe.domain.product.service.query.ProductQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "상품 관련", description = "상품 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/Products")
public class ProductController {

    private final ProductCommandService productCommandService;
    private final ProductQueryService productQueryService;
    private final ProductRepository productRepository;


    //상품 목록 조회
    @Operation(summary = "상품 목록 조회 API", description = "offset 기반 상품 목록 조회 API입니다.")
    @GetMapping("/api/v1/users/products/offset")
    public ResponseEntity<ProductResDTO.ProductOffsetResDTO> getProductList(
            @RequestParam Integer offset,
            @RequestParam Integer size
    ) {
        return ResponseEntity.ok(productQueryService.getProductOffset(offset, size));
    }

    //상품 상세 조회
    @Operation(summary = "상품 상세 조회 API", description = "상품 상세 조회 API입니다.")
    @GetMapping("/api/v1/products/{productId}")
    @Parameters({
            @Parameter(name = "productId", description = "상품 아이디", example = "1")
    })
    public ResponseEntity<ProductResDTO.ProductPreviewResDTO> getProduct(@PathVariable long productId) {
        return ResponseEntity.ok(productQueryService.getProduct());
    }

    //상품 추가
    @Operation(summary = "상품 추가 API", description = "상품 추가 API입니다.")
    @PostMapping("/api/v1/products")
    public ResponseEntity<ProductResDTO.ProductCreateResDTO> createProduct(
            @RequestBody ProductReqDTO.ProductCreateReqDTO productCreateReqDTO) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(productCommandService.createProduct(productCreateReqDTO));
    }

    //상품 삭제
    @Operation(summary = "상품 삭제 API", description = "상품 삭제 API")
    @DeleteMapping("/api/v1/products/{productId}")
    @Parameters({
            @Parameter(name = "productId", description = "상품 아이디", example = "1")
    })
    public ProductResDTO.ProductResponseDTO deleteProduct(@PathVariable long productId) { return null;}
}
