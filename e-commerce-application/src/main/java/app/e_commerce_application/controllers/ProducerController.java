package app.e_commerce_application.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import app.e_commerce_application.payloads.ProducersResponse;
import app.e_commerce_application.services.AuthorCategoryProducerService;
import jakarta.persistence.Entity;

@Entity
@RequestMapping("/api/producers")
public class ProducerController {

    @Autowired
    private AuthorCategoryProducerService producerService;

    @GetMapping("/")
    public ResponseEntity<ProducersResponse> getAll() {
        ProducersResponse producersReponse = new ProducersResponse();
        producersReponse.setProducerResposeSuccess(producerService.getAllProducers(), "Producers found");
        return new ResponseEntity<ProducersResponse>(producersReponse, HttpStatus.OK);
    }
    
}
