package com.mailing.shippingandhandling.fedex.service;

import java.util.Map;
import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.mailing.shippingandhandling.fedex.entity.Fedex;
import com.mailing.shippingandhandling.fedex.repository.FedexRepository;
import com.mailing.shippingandhandling.mailing.status.MailingStatus;

@Service
public class FedexService {

  private final ObjectMapper objectMapper;
  private final FedexRepository fedexRepository;

  FedexService(ObjectMapper objectMapper, FedexRepository fedexRepository) {
    this.objectMapper = objectMapper;
    this.fedexRepository = fedexRepository;
  }

  public String processPackage(String payload) throws JsonProcessingException {
    boolean isCreated = false;
    try {
      Fedex packageInfo = objectMapper.readValue(payload, Fedex.class);
      fedexRepository.save(packageInfo);
      isCreated = true;

    } catch (JsonProcessingException e) {
      // Handle exceptions
      e.printStackTrace();
    }
    String trackingInfo = this.genTrackingInfo();
    MailingStatus status = isCreated ? MailingStatus.LABEL_CREATED : MailingStatus.LABEL_NOT_CREATED;

    Map<String, Object> jsonObject = new HashMap<>();
    jsonObject.put("deliveryStatus", status);
    jsonObject.put("trackingNumber", trackingInfo);

    // Convert the map to a JSON string
    String jsonString = objectMapper.writeValueAsString(jsonObject);
    return jsonString;
  }

  public String genTrackingInfo() {

    int StringLength = 14;

    String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
        + "0123456789"
        + "abcdefghijklmnopqrstuvxyz";

    // create StringBuffer size of AlphaNumericString
    StringBuilder trackingInfo = new StringBuilder(StringLength);

    for (int i = 0; i < StringLength; i++) {

      // generate a random number between
      // 0 to AlphaNumericString variable length
      int index = (int) (AlphaNumericString.length()
          * Math.random());

      // add Character one by one in end of sb
      trackingInfo.append(AlphaNumericString
          .charAt(index));
    }

    return trackingInfo.toString();

  }
}
