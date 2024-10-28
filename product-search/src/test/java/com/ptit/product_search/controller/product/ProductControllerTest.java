package com.ptit.product_search.controller.product;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ptit.product_search.controller.product.ProductController;
import com.ptit.product_search.dto.response.product.ProductListResponse;
import com.ptit.product_search.dto.response.product.ProductResponse;
import com.ptit.product_search.entity.product.ProductEntity;
import com.ptit.product_search.service.product.ProductService;


@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @MockBean
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    public void testList() throws Exception {
        List<ProductEntity> products = Arrays.asList(new ProductEntity(), new ProductEntity());
        ProductListResponse response = ProductListResponse.ofSuccess("List products", products, products.size());

        when(productService.list()).thenReturn(products);

        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreate() throws Exception {
        ProductEntity product = new ProductEntity();
        ProductResponse response = ProductResponse.ofCreated("Product created", product);

        when(productService.create(product)).thenReturn(product);

        mockMvc.perform(post("/api/products/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isCreated());
    }

    @Test
    public void testUpdate() throws Exception {
        String id = "1";
        ProductEntity product = new ProductEntity();
        product.setId(id);
        ProductResponse response = ProductResponse.ofSuccess("Product updated", product);

        when(productService.update(product)).thenReturn(product);

        mockMvc.perform(put("/api/products/update/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isOk());
    }

    @Test
    public void testGet() throws Exception {
        String id = "1";
        ProductEntity product = new ProductEntity();
        ProductResponse response = ProductResponse.ofSuccess("Product found", product);

        when(productService.get(id)).thenReturn(product);

        mockMvc.perform(get("/api/products/get/{id}", id))
                .andExpect(status().isOk());
    }

    @Test
    public void testDelete() throws Exception {
        String id = "1";

        mockMvc.perform(delete("/api/products/delete/{id}", id))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateMany() throws Exception {
        int number = 5;
        List<ProductEntity> products = ProductController.generateProducts(number);

        mockMvc.perform(get("/api/products/create-many/{number}", number))
                .andExpect(status().isOk());
    }
}