package com.adobe.prj.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.adobe.prj.entity.Product;

//@Repository
public class ProductDaoJpaImpl {
	@PersistenceContext
	private EntityManager em;
	
	public void addProduct(Product p) {
		em.persist(p);
	}
	
	public List<Product> getProducts() {
		String hql = "";
		TypedQuery<Product> query =  em.createQuery(hql, Product.class);
		return query.getResultList();
	}
}
