package com.adobe.prj.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.adobe.prj.entity.Product;
import com.adobe.prj.service.OrderService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("api/products")
public class ProductController {
	@Autowired
	private OrderService service;
	
	//http://localhost:8080/api/products GET
	//http://localhost:8080/api/products?page=0&size=4 GET 
	@ApiOperation(value = "get all products")
	@GetMapping()
	public @ResponseBody List<Product> getProducts(@RequestParam(name="page", defaultValue = "-1") int page,
			@RequestParam(name="size", defaultValue = "-1") int size) {
		if(page == -1 && size == -1) {
			return service.getProducts();
		} else {
			return service.paginateProduct(page, size).getContent();
		}
		
	}
	
	//http://localhost:8080/api/products/4  GET
	@GetMapping("/{pid}")
	public @ResponseBody  Product  getProduct(@PathVariable("pid") int id) throws NotFoundException {
		try {
			return service.getProduct(id);
		} catch (Exception e) {
			throw new NotFoundException("Product with id " + id + " doesn't exist!!!");
		}
	}
	
	@PostMapping()
	@ResponseStatus(code = HttpStatus.CREATED)
	public @ResponseBody  Product addProduct(@RequestBody @Valid Product p) {
		return service.addProduct(p);
	}
	
	@PutMapping("/{pid}")
	public @ResponseBody Product updateProduct(@PathVariable("pid") int id, @RequestBody Product p) throws NotFoundException {
		service.modifyProductPrice(id, p.getPrice());
		return getProduct(id);
	}
	
	
}
