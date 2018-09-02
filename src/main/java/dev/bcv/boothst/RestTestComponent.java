package dev.bcv.boothst;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
class RestTestComponent {
    @GetMapping("/test")
    public String test() {
        return "Hello World!";
    }
}