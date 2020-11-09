package com.example.suplier.util;

import java.util.ArrayDeque;
import java.util.Deque;

import com.example.suplier.model.Product;

public class Database {
	
	public static Deque<Product> listOfProducts = new ArrayDeque<>();
	
	static {
		listOfProducts.push(new Product("Coockies", 8.5, true));
		listOfProducts.push(new Product("Choclet", 100.0, true));
		listOfProducts.push(new Product("Juice", 7.2, true));
		listOfProducts.push(new Product("Watter", 2.5, true));
		listOfProducts.push(new Product("Drugs", 978.5, false));
	}

}
