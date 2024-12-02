package com.than.spring_shop.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
  @GetMapping
  public String greet() {
    return "Hi22222333445566";
  }
}
