package com.github.megathrone.sellersclient;

import jsonobject.Sellers;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import processor.SellersProcessor;


@SpringBootApplication
public class SellersclientApplication {

    public static void main(String[] args) {

        SpringApplication.run(SellersclientApplication.class, args);

    }



}
