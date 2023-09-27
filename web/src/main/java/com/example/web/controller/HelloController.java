package com.example.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

  @GetMapping("/helloController")
  public String hello() {
    return "welcom to BeomKyung Hello World";
  }
}
