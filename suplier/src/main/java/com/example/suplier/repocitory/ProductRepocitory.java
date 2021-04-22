package com.example.suplier.repocitory;

import org.springframework.data.repository.CrudRepository;

import com.example.suplier.model.Product;

public interface ProductRepocitory extends CrudRepository<Product, String>{

}
