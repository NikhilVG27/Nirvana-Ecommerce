package com.nirvana.api.product;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.util.List;

@Component
public class ProductSeeder implements CommandLineRunner {
    private final ProductRepository repository;
    public ProductSeeder(ProductRepository repository){this.repository=repository;}
    @Override public void run(String... args){
        if(repository.count()>0) return;
        repository.saveAll(List.of(
            p("Essential Oversized Tee","Men","Clothing",1299,1699,4.8,"BESTSELLER","Jet Black","S,M,L,XL","https://images.unsplash.com/photo-1521572163474-6864f9cf17ab?w=900","A relaxed everyday essential crafted for comfort and effortless styling.",30),
            p("Urban Utility Jacket","Men","Clothing",3499,4299,4.7,"NEW DROP","Stone","S,M,L,XL","https://images.unsplash.com/photo-1551028719-00167b16eac5?w=900","A modern utility layer with clean lines and functional details.",12),
            p("Everyday Runner","Men","Footwear",2899,3499,4.6,"TRENDING","Ivory","7,8,9,10","https://images.unsplash.com/photo-1542291026-7eec264c27ff?w=900","Lightweight everyday sneakers designed for all-day comfort.",18),
            p("Modern Elegance Dress","Women","Clothing",3199,3999,4.9,"EDITOR PICK","Black","XS,S,M,L","https://images.unsplash.com/photo-1539109136881-3be0616acf4b?w=900","A confident silhouette for evenings and events.",10),
            p("Structured City Bag","Women","Accessories",2499,2999,4.7,"POPULAR","Tan","ONE SIZE","https://images.unsplash.com/photo-1584917865442-de89df76afd3?w=900","A versatile structured carryall for everyday looks.",14),
            p("Minimal Streetwear Set","Women","Clothing",2799,3299,4.6,"NEW DROP","Neutral","XS,S,M,L","https://images.unsplash.com/photo-1483985988355-763728e1935b?w=900","An easy coordinated look with a contemporary relaxed fit.",9),
            p("Studio Chrono Watch","Accessories","Accessories",4299,4999,4.8,"LIMITED","Gold","ONE SIZE","https://images.unsplash.com/photo-1524592094714-0f0654e20314?w=900","A refined everyday timepiece with a clean modern profile.",8),
            p("Sculpt Wireless Headphones","Electronics","Electronics",5999,6999,4.5,"FEATURED","Black","ONE SIZE","https://images.unsplash.com/photo-1509347528160-9a9e33742cdb?w=900","Immersive sound and a comfortable design for work and travel.",7)
        ));
    }
    private Product p(String n,String c,String t,int price,int old,double rating,String tag,String color,String sizes,String image,String desc,int stock){
        return new Product(n,c,t,BigDecimal.valueOf(price),BigDecimal.valueOf(old),BigDecimal.valueOf(rating),tag,color,sizes,image,desc,stock);
    }
}
