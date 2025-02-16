package com.ptit.graduation.controller.product;

import com.ptit.graduation.dto.response.ResponseGeneral;
import com.ptit.graduation.entity.product.LocationMongo;
import com.ptit.graduation.service.product.LocationService;
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
@RequestMapping("api/v1/locations")
public class LocationController {
  private final LocationService locationService;

  @GetMapping("")
  public ResponseGeneral<List<LocationMongo>> list() {
    log.info("(list) start");

    return ResponseGeneral.ofSuccess(
          SUCCESS,
          locationService.list()
    );
  }

  @GetMapping("/get/{id}")
  public ResponseGeneral<LocationMongo> get(@PathVariable String id) {
    log.info("(get) id: {}", id);

    return ResponseGeneral.ofSuccess(
          SUCCESS,
          locationService.get(id)
    );
  }

  @PostMapping("/create")
  public ResponseGeneral<LocationMongo> create(
      @RequestBody LocationMongo location
  ) {
    log.info("(create) location: {}", location);

    return ResponseGeneral.ofCreated(
        SUCCESS,
        locationService.create(location)
    );
  }

  @PutMapping("/update")
  public ResponseGeneral<LocationMongo> update(
      @RequestBody LocationMongo location
  ) {
    log.info("(update) location: {}", location);

    return ResponseGeneral.ofSuccess(
        SUCCESS,
        locationService.update(location)
    );
  }

  @DeleteMapping("/delete/{id}")
  public ResponseGeneral<String> delete(@PathVariable String id) {
    log.info("(delete) id: {}", id);
    locationService.delete(id);

    return ResponseGeneral.ofSuccess(
        SUCCESS,
        "Deleted location with id: " + id
    );
  }
  
}
