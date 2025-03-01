package com.ptit.graduation.controller.product;

import com.ptit.graduation.dto.response.ResponseGeneral;
import com.ptit.graduation.entity.product.CategoryMongo;
import com.ptit.graduation.service.product.CategoryService;
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
@RequiredArgsConstructor
@RequestMapping("api/v1/categories")
public class CategoryController {
  private final CategoryService categoryService;

  @GetMapping("")
  public ResponseGeneral<List<CategoryMongo>> list() {
    log.info("(list) start");

    List<CategoryMongo> categories = categoryService.list();

    return ResponseGeneral.ofSuccess(
          SUCCESS,
          categories
    );
  }

  @GetMapping("/get/{id}")
  public ResponseGeneral<CategoryMongo> get(@PathVariable String id) {
    log.info("(get) id: {}", id);

    return ResponseGeneral.ofSuccess(
          SUCCESS,
          categoryService.get(id)
    );
  }

  @PostMapping("/create")
  public ResponseGeneral<CategoryMongo> create(
      @RequestBody CategoryMongo category
  ) {
    log.info("(create) category: {}", category);

    return ResponseGeneral.ofCreated(
        SUCCESS,
        categoryService.create(category)
    );
  }

  @PutMapping("/update")
  public ResponseGeneral<CategoryMongo> update(
      @RequestBody CategoryMongo category
  ) {
    log.info("(update) category: {}", category);

    return ResponseGeneral.ofSuccess(
        SUCCESS,
        categoryService.update(category)
    );
  }

  @DeleteMapping("/delete/{id}")
  public ResponseGeneral<String> delete(@PathVariable String id) {
    log.info("(delete) id: {}", id);
    categoryService.delete(id);

    return ResponseGeneral.ofSuccess(
        SUCCESS,
        "Deleted category with id: " + id
    );
  }

}
