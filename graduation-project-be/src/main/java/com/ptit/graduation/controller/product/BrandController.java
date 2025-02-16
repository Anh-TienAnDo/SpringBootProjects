package com.ptit.graduation.controller.product;

import com.ptit.graduation.dto.response.ResponseGeneral;
import com.ptit.graduation.entity.product.BrandMongo;
import com.ptit.graduation.service.product.BrandService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.ptit.graduation.constants.GraduationProjectConstants.MessageCode.SUCCESS;

@Slf4j
@RestController
@RequestMapping("/api/v1/brands")
@RequiredArgsConstructor
public class BrandController {
  private final BrandService brandService;

  @GetMapping
  public ResponseGeneral<List<BrandMongo>> list() {
    log.info("(list) start");

    List<BrandMongo> brands = brandService.list();

    return ResponseGeneral.ofSuccess(
          SUCCESS,
          brands
    );
  }

  @GetMapping("/get/{id}")
  public ResponseGeneral<BrandMongo> get(@PathVariable String id) {
    log.info("(get) id: {}", id);

    return ResponseGeneral.ofSuccess(
          SUCCESS,
          brandService.get(id)
    );
  }

  @PostMapping("/create")
  public ResponseGeneral<BrandMongo> create(
      @RequestBody BrandMongo brand
  ) {
    log.info("(create) brand: {}", brand);

    return ResponseGeneral.ofCreated(
        SUCCESS,
        brandService.create(brand)
    );
  }

  @PutMapping("/update")
  public ResponseGeneral<BrandMongo> update(
      @RequestBody BrandMongo brand
  ) {
    log.info("(update) brand: {}", brand);

    return ResponseGeneral.ofSuccess(
        SUCCESS,
        brandService.update(brand)
    );
  }

  @DeleteMapping("/delete/{id}")
  public ResponseGeneral<String> delete(@PathVariable String id) {
    log.info("(delete) id: {}", id);
    brandService.delete(id);

    return ResponseGeneral.ofSuccess(
        SUCCESS,
        "Deleted brand with id: " + id
    );
  }

}
