package Objects;

import java.util.*;

public class Product {
	String name = "Product";
	ArrayList<ProductPart> parts = new ArrayList<ProductPart>();
	
	public Product(){
		
	}
	
	public Product(ProductPart[] p){
		for(int i=0; i < p.length; i++){
			parts.add(p[i]);
		}
		System.out.println(String.format("Added {0} parts to product {1}", p.length, this.name));
	}
	
	public void addPart(ProductPart p){
		parts.add(p);
		System.out.println(String.format("Added part {0} to product {1}", p.name, this.name));
	}

}