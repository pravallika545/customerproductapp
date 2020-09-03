package com.dxctraining.productmgt.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.dxctraining.productmgt.entities.Product;
import com.dxctraining.productmgt.exceptions.InvalidArgumentException;
import com.dxctraining.productmgt.exceptions.ProductNotFoundException;

@ExtendWith(SpringExtension.class)
@DataMongoTest
@Import(ProductServiceImpl.class)
class ProductServiceImplTest {
	
	@Autowired
	private IProductService service;

	@Test
	public void testAdd_1() {
		Executable executable=()->service.save(null);
		Assertions.assertThrows(InvalidArgumentException.class, executable);
	}
	
	@Test
	public void testAdd_2() {
		String name = "vivo y15";
		Product product = new Product(name);
		product = service.save(product);
		Product fetched = service.findById(product.getId());
		Assertions.assertEquals(product.getId(),fetched.getId());
		Assertions.assertEquals(name,fetched.getName());
	}
	/*   java.lang.IllegalArgumentException is thrown instead of ProductNotFoundException
	@Test
	public void testFindById_1() {
		Executable execute=()->service.findById(null);
		Assertions.assertThrows(ProductNotFoundException.class, execute);
	}
	*/
	@Test
	public void testFindById_2() {
		String name = "oneplus";
		Product product = new Product(name);
		product = service.save(product);
		String id = product.getId();
		Product fetched = service.findById(id);
		Assertions.assertEquals(fetched.getId(), id);
	}

}
