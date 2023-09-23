package br.com.gabriel;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {

    private final static String template = "Hello, %s";
    private final AtomicLong counter = new AtomicLong();


    @GetMapping("/greeting")
    public ResponseEntity<Greeting> greeting(@RequestParam(value = "name",
            defaultValue = "World") String name){
        return ResponseEntity.ok().body(new Greeting(counter.incrementAndGet(), String.format(template, name)));
    }


}
