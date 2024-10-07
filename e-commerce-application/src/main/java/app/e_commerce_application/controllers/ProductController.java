package app.e_commerce_application.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import app.e_commerce_application.entities.Product;
import app.e_commerce_application.repositories.ProductRepository;
import app.e_commerce_application.services.ProductService;


@Controller
@RequestMapping("/products")
@EnableMongoRepositories(basePackages = "app.e_commerce_application.repositories")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    // Hiển thị danh sách sản phẩm
    @GetMapping("")
    public String products(Model model) {
        List<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "products";
    }

    // Trang tạo sản phẩm mới
    @GetMapping("/add")
    public String showCreateForm(Model model) {
        model.addAttribute("product", new Product());
        return "product-form";
    }

    // Lưu sản phẩm
    @PostMapping("/save")
    public String saveProduct(@ModelAttribute Product product) {
        System.out.println("------------------saveProduct-------------------");
        // if(product.getId() == null || product.getId().equals("")) {
        //     // product.setId(null);
        //     product.setId(productRepository.count() + 1 + "");
        // }
        productService.save(product);
        return "redirect:/products";
    }

    // Trang chỉnh sửa sản phẩm
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") String id, Model model) {
        Optional<Product> product = productRepository.findById(id);
        System.out.println("-------------------showEditForm------------------");
        System.out.println(product);
        if (product.isPresent()) {
            model.addAttribute("product", product);
            return "product-form";
        } else {
            return "redirect:/products";
        }
    }

    // Xóa sản phẩm
    @GetMapping("/delete/{id}")
    public String deleteProductById(@PathVariable String id) {
        productRepository.deleteById(id);
        return "redirect:/products";
    }
    
    // Tìm kiếm sản phẩm
    @GetMapping("/search")
    public String searchProducts(@RequestParam("keyword") String keyword, Model model) {
        List<Product> products = productRepository.findItemByNameLike(keyword);
        model.addAttribute("products", products);
        return "products";
    }

}
