package com.nirvana.api;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
@RestController @CrossOrigin(origins="http://localhost:5173") public class HealthController { @GetMapping("/api/health") public Map<String,String> health(){return Map.of("status","ok","application","Nirvana API");} }