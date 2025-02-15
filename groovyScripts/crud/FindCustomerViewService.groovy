import org.apache.ofbiz.base.*;
import org.apache.ofbiz.entity.*;
import org.apache.ofbiz.service.*;

customerList = delegator.findList("FindCustomerView", null, null , null, null, false);
context.customerList = customerList;
