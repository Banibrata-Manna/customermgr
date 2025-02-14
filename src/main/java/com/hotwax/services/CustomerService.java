package com.hotwax.services;

import java.time.LocalDateTime;
import java.util.Map;

import org.apache.ofbiz.base.util.*;
import java.sql.Timestamp;
import org.apache.ofbiz.base.util.Debug;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.service.LocalDispatcher;
import org.apache.ofbiz.service.DispatchContext;
import org.apache.ofbiz.service.ServiceUtil;

public class CustomerService{
  public static final String MODULE = CustomerService.class.getName();

  public static Map<String, Object> createCustomer(DispatchContext dispatchContext, Map<String, ? extends Object> context) {
    Map<String, Object> result = ServiceUtil.returnSuccess();
    Delegator delegator = dispatchContext.getDelegator();
    Timestamp now = Timestamp.valueOf(LocalDateTime.now());
    try{
      GenericValue party = delegator.makeValue("Party");
      party.setNextSeqId();
      party.setFields(UtilMisc.toMap("partyTypeId", "PERSON"));
      party = delegator.create(party);

      GenericValue person = delegator.makeValue("Person");
      person.setPKFields(UtilMisc.toMap("partyId", party.get("partyId")));
      person.setNonPKFields(context);
      person = delegator.create(person);

      GenericValue partyRole = delegator.makeValue("PartyRole");
      partyRole.setFields(UtilMisc.toMap("partyId", party.get("partyId")
      , "roleTypeId", "CUSTOMER"));
      partyRole = delegator.create(partyRole);

      GenericValue contactMech = delegator.makeValue("ContactMech");
      contactMech.setNextSeqId();
      contactMech.setNonPKFields(UtilMisc.toMap("infoString", context.get("emailAddress")));
      contactMech = delegator.create(contactMech);

      GenericValue partyContactMech = delegator.makeValue("PartyContactMech");
      partyContactMech.setPKFields(UtilMisc.toMap("partyId", party.get("partyId"),
        "contactMechId", contactMech.get("contactMechId"), "fromDate", now,
          "roleTypeId", "CUSTOMER"));
      partyContactMech = delegator.create(partyContactMech);

      GenericValue partyContactMechPurpose = delegator.makeValue("PartyContactMechPurpose");
      partyContactMechPurpose.setPKFields(UtilMisc.toMap("partyId", party.get("partyId"),
          "contactMechId", contactMech.get("contactMechId"), "fromDate", now,
          "contactMechPurposeTypeId", "PRIMARY_EMAIL"));
      partyContactMechPurpose = delegator.create(partyContactMechPurpose);
    } catch (Exception e) {
      Debug.logError(e, MODULE);
      return ServiceUtil.returnError("Failed to create record " + MODULE);
    }
    return result;
  }
}

//public static Map<String, Object> createOfbizDemo(DispatchContext dispatchContext, Map<String, ? extends Object> context) {
//  Map<String, Object> result = ServiceUtil.returnSuccess();
//  Delegator delegator = dispatchContext.getDelegator();
//  try {
//    GenericValue ofbizDemo = delegator.makeValue("OfbizDemo");
//    ofbizDemo.setNextSeqId();
//    ofbizDemo.setNonPKFields(context);
//    ofbizDemo = delegator.create(ofbizDemo);
//    result.put("ofbizDemoId", ofbizDemo.getString("ofbizDemoId"));
//    Debug.log("/////////////////////////////////////////////////////////Record "
//        + ofbizDemo.getString("ofbizDemoId") + " Created");
//
//  } catch (GenericEntityException e) {
//    Debug.logError(e, MODULE);
//    return ServiceUtil.returnError("Failed to create record" + MODULE);
//  }
//  return result;
//}