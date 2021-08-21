package com.adobe.prj;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;

import com.adobe.prj.dao.OrderDao;
import com.adobe.prj.dto.ReportDTO;
import com.adobe.prj.entity.Customer;
import com.adobe.prj.entity.Item;
import com.adobe.prj.entity.Order;
import com.adobe.prj.entity.Product;
import com.adobe.prj.service.OrderService;

@SpringBootApplication
public class OrderappApplication implements CommandLineRunner {
	@Autowired(required = false)
	private OrderService service;
	
	@Autowired(required = false)
	private OrderDao orderDao;
	
	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(OrderappApplication.class, args);
//		String[] names = ctx.getBeanDefinitionNames();
//		for(String name : names) {
//			System.out.println(name);
//		}
	}

	@Override
	public void run(String... args) throws Exception {
//		printProducts();
//		getById();
//		addProduct();
		
//		addCustomer();
		
//		newOrder();
		
		
//		pagenate();
//		getByRange();
//		modifyProduct();
//		getReport();
		
	}

	private void getReport() {
		List<ReportDTO> reports = orderDao.getReport();
		for(ReportDTO report : reports) {
			System.out.println(report.getFirstName() +", " + report.getOrderDate() +", " + report.getTotal());
		}
	}

	private void modifyProduct() {
		service.modifyProductPrice(5, 31999.99);
	}

	private void getByRange() {
		List<Product> products = service.getByRange(1000, 50_000);
		for(Product p : products) {
			System.out.println(p);
		}
	}

	private void pagenate() {
		Page<Product> productPage = service.paginateProduct(0, 3);
		System.out.println("Total Pages: " + productPage.getTotalPages());
		System.out.println("Current Page: " +productPage.getNumber());
		List<Product> products = productPage.getContent();
		for(Product p : products) {
			System.out.println(p);
		}
	}

	private void newOrder() {
		Order o = new Order();
		Customer c = new Customer();
		c.setEmail("danny@adobe.com");
		o.setCustomer(c);
		
		Item i1 = new Item();
		Product p1 = new Product();
		p1.setId(5);
		i1.setProduct(p1);
		i1.setQty(1);
		
		Item i2 = new Item();
		Product p2 = new Product();
		p2.setId(1);
		i2.setProduct(p2);
		i2.setQty(2);
		
		o.getItems().add(i1);
		o.getItems().add(i2);
		
		service.placeOrder(o);
	}

	private void addCustomer() {
		Customer c = new Customer();
		c.setEmail("danny@adobe.com");
		c.setFirstName("Danny");
		
		service.addCustomer(c);
	}

	private void addProduct() {
		Product p = new Product();
		p.setName("PenDrive");
		p.setPrice(2300);
		p.setQuantity(300);
		
		service.addProduct(p);
	}

	private void getById() {
		Product p = service.getProduct(3);
		System.out.println(p);
	}

	private void printProducts() {
		List<Product> products = service.getProducts();
		for(Product p : products) {
			System.out.println(p);
		}
	}

}
