package com.ptit.graduation.controller.product;

import com.ptit.graduation.dto.request.product.ProductFilterRequest;
import com.ptit.graduation.dto.response.ResponseGeneral;
import com.ptit.graduation.dto.response.product.ProductListResponse;
import com.ptit.graduation.dto.response.product.ProductPageResponse;
import com.ptit.graduation.entity.product.ProductMongo;
import com.ptit.graduation.facade.ProductFacadeService;
import com.ptit.graduation.service.product.ProductMongoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import static com.ptit.graduation.constants.GraduationProjectConstants.MessageCode.SUCCESS;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/products")
public class ProductController {
  private final ProductMongoService productMongoService;
  private final ProductFacadeService productFacadeService;

  @PostMapping("/filter")
  public ResponseGeneral<ProductPageResponse> filter(
        @RequestBody ProductFilterRequest request
  ) {
    log.info("(filter) request: {}", request);

    return ResponseGeneral.ofSuccess(
          SUCCESS,
          productFacadeService.filter(request)
    );
  }

  @GetMapping("/get/{id}")
  public ResponseGeneral<ProductMongo> get(@PathVariable String id) {
    log.info("(get) id: {}", id);

    return ResponseGeneral.ofSuccess(
          SUCCESS,
          productMongoService.get(id)
    );
  }

  @GetMapping("")
  public ResponseGeneral<ProductListResponse> getAll(
        @RequestParam(defaultValue = "0") int skip,
        @RequestParam(defaultValue = "50") int limit
  ) {
    log.info("(getAll) skip: {}, limit: {}", skip, limit);

    return ResponseGeneral.ofSuccess(
          SUCCESS,
          productMongoService.getAll(skip, limit)
    );
  }
  
}
