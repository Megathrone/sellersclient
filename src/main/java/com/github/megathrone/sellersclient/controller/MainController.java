package com.github.megathrone.sellersclient.controller;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import jsonobject.Seller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import processor.SellersProcessor;

@Controller
public class MainController {

  @Autowired SellersProcessor sellersProcessor;
  @Autowired Gson gson;

  @GetMapping("")
  public String index() {
    return "index";
  }

  @PostMapping("/loadfromurl")
  public String loadFromUrl(@RequestParam("jsonurl") String url, Model model) {
    String rawJson = sellersProcessor.loadJsonStringFromUrl(url);

    model.addAttribute("jsondata", rawJson);
    model.addAttribute("jsonurl", url);
    return "json";
  }

  @PostMapping("/listdomains")
  public String listdomains(@RequestParam("jsonfile") MultipartFile file, Model model) {

    if (file.isEmpty()) {
      model.addAttribute("jsondata", "Empty!");
      model.addAttribute("jsonfile", "Empty!");
      return "domains";
    }
    try {
      Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), "UTF-8"));
      List<String> domains = sellersProcessor.listDomainsFromFile(reader);
      model.addAttribute("jsondata", gson.toJson(domains));
      model.addAttribute("jsonfile", file.getOriginalFilename());
    } catch (IOException ioe) {
      System.out.println(ioe.getCause().getMessage());
    }

    return "domains";
  }

  @PostMapping("/commonSellers")
  public String commonSellers(@RequestParam("jsonfiles") MultipartFile[] files, Model model) {
    if (files.length != 2) {
      model.addAttribute("jsondata", "Empty!");
      model.addAttribute("jsonfileOne", "Empty!");
      model.addAttribute("jsonfileTwo", "Empty!");
      model.addAttribute("size", "0");
      return "commonSeller";
    }
    try {
      Reader[] readers = new Reader[2];
      for (int i = 0; i < files.length; i++) {
        readers[i] = new BufferedReader(new InputStreamReader(files[i].getInputStream(), "UTF-8"));
      }

      List<Seller> commonSellers = sellersProcessor.listCommonSellers(readers[0], readers[1]);
      if (commonSellers.isEmpty()) {
        model.addAttribute("jsondata", "No common sellers");
        model.addAttribute("size", "0");
      } else {
        model.addAttribute("jsondata", gson.toJson(commonSellers));
        model.addAttribute("size", commonSellers.size());
      }

      model.addAttribute("jsonfileOne", files[0].getOriginalFilename());
      model.addAttribute("jsonfileTwo", files[1].getOriginalFilename());
    } catch (IOException ioe) {
      System.out.println(ioe.getMessage());
    }

    return "commonSeller";
  }

}
