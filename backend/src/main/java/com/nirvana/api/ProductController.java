package com.nirvana.api;
import org.springframework.web.bind.annotation.*;
import java.util.*;
@RestController @RequestMapping("/api/products") @CrossOrigin(origins="http://localhost:5173")
public class ProductController {
 private final List<Map<String,Object>> products = new ArrayList<>(List.of(
  Map.of("id",1,"name","Essential Oversized Tee","category","Men","price",1299),
  Map.of("id",2,"name","Modern Elegance Dress","category","Women","price",3199),
  Map.of("id",3,"name","Studio Chrono Watch","category","Accessories","price",4299)
 ));
 @GetMapping public List<Map<String,Object>> all(){return products;}
 @GetMapping("/{id}") public Map<String,Object> one(@PathVariable int id){return products.stream().filter(p->Objects.equals(p.get("id"),id)).findFirst().orElseThrow(()->new NoSuchElementException("Product not found"));}
 @PostMapping public Map<String,Object> create(@RequestBody Map<String,Object> product){Map<String,Object> p=new HashMap<>(product);p.put("id",System.currentTimeMillis());products.add(p);return p;}
 @DeleteMapping("/{id}") public Map<String,String> delete(@PathVariable long id){products.removeIf(p->Objects.equals(String.valueOf(p.get("id")),String.valueOf(id)));return Map.of("message","Product removed from demo catalog");}
}