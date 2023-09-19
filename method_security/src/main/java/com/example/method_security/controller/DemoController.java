package com.example.method_security.controller;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DemoController {

    //@PreAuthorize
    @GetMapping("/demo")
    @PreAuthorize("hasAuthority('read')")
    public String demo() {
        return "Demo hello";
    }

    @GetMapping("/demo2")
    @PreAuthorize("@authorizationCondition.decide()") //pass bean with methid call
    public String demo2() {
        return "Demo2 hello";
    }

    //@PostAuthorize

    @GetMapping("/demo3")
    @PostAuthorize("returnObject == 'Demo2 hello'") // is mainly used when we want restrict access to some returned value
    // never apply @PostAuthorize on method which change data
    public String demo3() {
        return "Demo3 hello";
    }

    //PreFilter

    @GetMapping("/demo4")
    @PreFilter("filterObject.contains('a')")
    public String demo4(@RequestBody List<String> strings) {// parameter value should be mutable
        System.out.println(strings);
        return "Demo4";
    }

    //POstFilter

    @GetMapping("/demo5")
    @PostFilter("filterObject.contains('a')")
    public List<String> demo5() {//return value should be mutable
        return List.of("asdf", "zxcv", "qwer", "asdfasdf");
    }
}
