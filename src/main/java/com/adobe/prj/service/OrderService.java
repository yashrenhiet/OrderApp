package com.adobe.prj.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.adobe.prj.dao.CustomerDao;
import com.adobe.prj.dao.OrderDao;
import com.adobe.prj.dao.ProductDao;
import com.adobe.prj.entity.Customer;
import com.adobe.prj.entity.Item;
import com.adobe.prj.entity.Order;
import com.adobe.prj.entity.Product;

@Service
public class OrderService {
	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private OrderDao orderDao;
	
	@Transactional
	public Order placeOrder(Order o) {
		List<Item> items = o.getItems();
		double total = 0.0;
		for(Item i : items) {
			Product p = productDao.findById(i.getProduct().getId()).get();
			if(p.getQuantity() < i.getQty()) {
				throw new IllegalArgumentException("no products to add...");
			}
			p.setQuantity(p.getQuantity() - i.getQty()); // dirty checking ==> update
			i.setAmount(i.getQty() * p.getPrice());
			total += i.getAmount();
		}
		o.setTotal(total);
		orderDao.save(o); // cascade saves items also
		return o;
	}
	
	public List<Order> getOrders() {
		return orderDao.findAll();
	}
	
	public Customer addCustomer(Customer c) {
		return customerDao.save(c);
	}
	
	public Product addProduct(Product p) {
		return productDao.save(p);
	}
	
	public List<Product> getProducts() {
		return productDao.findAll();
	}
	
	public Product getProduct(int id) {
		return productDao.findById(id).get();
	}
	
	public List<Product> getByRange(double low, double high) {
		return productDao.fetchByRange(low, high);
	}
	
	public Page<Product> paginateProduct(int page, int size) {
		return productDao.findAll(PageRequest.of(page, size));
	}
	
	@Transactional
	public void modifyProductPrice(int id, double price) {
		productDao.updateProduct(id, price);
	}

	public Order getOrder(int id) {
		return orderDao.findById(id).get();
	}
}
