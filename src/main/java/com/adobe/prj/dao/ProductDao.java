package com.adobe.prj.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.adobe.prj.entity.Product;

public interface ProductDao extends JpaRepository<Product, Integer> {
	@Query("from Product where price >= :lo and price <= :h")
	List<Product> fetchByRange(@Param("lo") double low, @Param("h") double high);
	
	List<Product> findByPrice(double price);
	
	@Modifying
	@Query("update Product set price = :pr where id = :id")
	void updateProduct(@Param("id") int id, @Param("pr") double price);
}

// @Repository
// class ProductDaoJpaImpl implements ProductDao {
// save()
// findById()
// findAll()
// delete()
//}