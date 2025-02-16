package com.hotwax.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ofbiz.base.util.*;
import java.sql.Timestamp;
import org.apache.ofbiz.base.util.Debug;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.condition.EntityCondition;
import org.apache.ofbiz.entity.condition.EntityOperator;
import org.apache.ofbiz.service.LocalDispatcher;
import org.apache.ofbiz.service.DispatchContext;
import org.apache.ofbiz.service.ServiceUtil;

public class CustomerService{
  public static final String MODULE = CustomerService.class.getName();

  public static Map<String, Object> findCustomers(DispatchContext dispatchContext, Map<String, ? extends Object> context) {
    Map<String, Object> result = ServiceUtil.returnSuccess();
    Delegator delegator = dispatchContext.getDelegator();
    Timestamp now = Timestamp.valueOf(LocalDateTime.now());
    Debug.logInfo("///////////////////////// Reached ////////////////////////", MODULE);
    try {
      List<EntityCondition> filteringConditions = new ArrayList<EntityCondition>();

      if(!UtilValidate.isEmpty(context.get("firstName"))){
        filteringConditions.add(
            EntityCondition.makeCondition("firstName", context.get("firstName"))
        );
      }
      if(!UtilValidate.isEmpty(context.get("lastName"))){
        filteringConditions.add(
            EntityCondition.makeCondition("lastName", context.get("lastName"))
        );
      }
      if(!UtilValidate.isEmpty(context.get("emailAddress"))){
        filteringConditions.add(
            EntityCondition.makeCondition("emailAddress", context.get("emailAddress"))
        );
      }
      if(!UtilValidate.isEmpty(context.get("contactNumber"))){
        filteringConditions.add(
            EntityCondition.makeCondition("contactNumber", context.get("contactNumber"))
        );
      }
      if(!UtilValidate.isEmpty(context.get("address1"))){
        filteringConditions.add(
            EntityCondition.makeCondition("address1", context.get("address1"))
        );
      }
      if(!UtilValidate.isEmpty(context.get("city"))){
        filteringConditions.add(
            EntityCondition.makeCondition("city", context.get("city"))
        );
      }
      if(!UtilValidate.isEmpty(context.get("postalCode"))){
        filteringConditions.add(
            EntityCondition.makeCondition("postalCode", context.get("postalCode"))
        );
      }

      if(!filteringConditions.isEmpty()){
        List<GenericValue> customers = delegator.findList(
            "FindCustomerView",
            EntityCondition.makeCondition(filteringConditions, EntityOperator.AND),
            null, null, null, false);
        result.put("customersList", customers);
        Debug.logInfo("////////////////////////if///"+ customers.toString() + "////////////////////////////", MODULE);
      } else{
        List<GenericValue> customers = delegator.findList(
            "FindCustomerView", null,null, null, null, false);
        result.put("customersList", customers);
        Debug.logInfo("///////////////////////else////${customers}////////////////////////////", MODULE);
      }
    } catch (Exception e) {
      Debug.logError(e, MODULE);
      return ServiceUtil.returnError("Failed to create record " + MODULE);
    }
    return result;
  }


    public static Map<String, Object> createCustomer(DispatchContext dispatchContext, Map<String, ? extends Object> context) {
    Map<String, Object> result = ServiceUtil.returnSuccess();
    Delegator delegator = dispatchContext.getDelegator();
    LocalDispatcher dispatcher = dispatchContext.getDispatcher();
    Timestamp now = Timestamp.valueOf(LocalDateTime.now());
    try{
      Map<String, Object> ifExists = dispatcher.runSync("findCustomers",
          UtilMisc.toMap("emailAddress",
              context.get("emailAddress")));

      List<GenericValue> existingParty = (List)ifExists.get("customersList");

      if(!existingParty.isEmpty()) return result;

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
      contactMech.setNonPKFields(UtilMisc.toMap("infoString", context.get("emailAddress"),
          "contactMechTypeId", "EMAIL_ADDRESS"));
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

      if(!UtilValidate.isEmpty(context.get("postalCode"))){
        Debug.logInfo((String) context.get("postalCode"), MODULE);
        Debug.logInfo("///////////////////////// Reached postal ////////////////////////", MODULE);
        contactMech = delegator.makeValue("ContactMech");
        contactMech.setNextSeqId();
        contactMech.setNonPKFields(UtilMisc.toMap("contactMechTypeId", "POSTAL_ADDRESS"));
        contactMech = delegator.create(contactMech);

        GenericValue postalAddress = delegator.makeValue("PostalAddress");
        postalAddress.setPKFields(UtilMisc.toMap("contactMechId",
            contactMech.get("contactMechId")));
        postalAddress.setNonPKFields(context);

        postalAddress = delegator.create(postalAddress);

        Debug.logInfo(postalAddress.toString(), MODULE);

        partyContactMech = delegator.makeValue("PartyContactMech");
        partyContactMech.setPKFields(UtilMisc.toMap("partyId", party.get("partyId"),
            "contactMechId", contactMech.get("contactMechId"), "fromDate", now,
            "roleTypeId", "CUSTOMER"));
        partyContactMech = delegator.create(partyContactMech);

        partyContactMechPurpose = delegator.makeValue("PartyContactMechPurpose");
        partyContactMechPurpose.setPKFields(UtilMisc.toMap("partyId", party.get("partyId"),
            "contactMechId", contactMech.get("contactMechId"), "fromDate", now,
            "contactMechPurposeTypeId", "PRIMARY_LOCATION"));
        partyContactMechPurpose = delegator.create(partyContactMechPurpose);
      }

      if(!UtilValidate.isEmpty(context.get("contactNumber"))){
        Debug.logInfo((String) context.get("contactNumber"), MODULE);
        Debug.logInfo("///////////////////////// Reached Telecom ////////////////////////", MODULE);

        contactMech = delegator.makeValue("ContactMech");
        contactMech.setNextSeqId();
        contactMech.setNonPKFields(UtilMisc.toMap("contactMechTypeId", "TELECOM_NUMBER"));
        contactMech = delegator.create(contactMech);

        GenericValue telecomNumber = delegator.makeValue("TelecomNumber");
        telecomNumber.setPKFields(UtilMisc.toMap("contactMechId",
            contactMech.get("contactMechId")));
        telecomNumber.setNonPKFields(context);

        telecomNumber = delegator.create(telecomNumber);

        Debug.logInfo(telecomNumber.toString(), MODULE);

        partyContactMech = delegator.makeValue("PartyContactMech");
        partyContactMech.setPKFields(UtilMisc.toMap("partyId", party.get("partyId"),
            "contactMechId", contactMech.get("contactMechId"), "fromDate", now,
            "roleTypeId", "CUSTOMER"));
        partyContactMech = delegator.create(partyContactMech);

        partyContactMechPurpose = delegator.makeValue("PartyContactMechPurpose");
        partyContactMechPurpose.setPKFields(UtilMisc.toMap("partyId", party.get("partyId"),
            "contactMechId", contactMech.get("contactMechId"), "fromDate", now,
            "contactMechPurposeTypeId", "PRIMARY_PHONE"));
        partyContactMechPurpose = delegator.create(partyContactMechPurpose);
      }
    } catch (Exception e) {
      Debug.logError(e, MODULE);
      return ServiceUtil.returnError("Failed to create record " + MODULE);
    }
    return result;
  }
}