package com.mailing.shippingandhandling.fedex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mailing.shippingandhandling.fedex.service.FedexService;

@Controller
public class FedexController {

  @Autowired
  private FedexService fedexService;

  @PostMapping("fedex/mail/order")
  public ResponseEntity<String> mailOrders(@RequestBody String entity) throws JsonProcessingException {
    String response = fedexService.processPackage(entity);

    return new ResponseEntity<>(response, HttpStatus.OK);
  }

}
