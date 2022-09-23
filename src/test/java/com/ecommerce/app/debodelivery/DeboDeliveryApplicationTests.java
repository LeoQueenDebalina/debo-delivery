package com.ecommerce.app.debodelivery;

import com.ecommerce.app.debodelivery.exception.DataNotFoundException;
import com.ecommerce.app.debodelivery.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DeboDeliveryApplicationTests {
	@Autowired
	private ProductService productService;
	@Test
	void contextLoads() throws DataNotFoundException {
		System.out.println(productService.getProductByGivenRange(0,3));
	}

}
