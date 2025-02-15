import org.apache.ofbiz.base.*;
import org.apache.ofbiz.entity.*;
import org.apache.ofbiz.service.*;

customerList = delegator.findList("FindCustomerView", null, null , null, null, false);
context.customerList = customerList;
//context.put("customerList", customerList);

//ofbizDemoTypes = delegator.findList("OfbizDemoType", null, null, null, null, false);
//context.ofbizDemoTypes = ofbizDemoTypes;
//ofbizDemoList = delegator.findList("OfbizDemo", null, null, null, null, false);
//context.ofbizDemoList = ofbizDemoList;