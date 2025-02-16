<div class="screenlet-body">
  <#if customersList?has_content>
    <table cellspacing=0 cellpadding=2 border=10 class="basic-table">
      <thead><tr>
        <th border=1>partyId</th>
        <th border=1>firstName</th>
        <th border=1>lastName</th>
        <th border=1>Email Address</th>
        <th border=1>Contact Number</th>
        <th border=1>Address</th>
        <th border=1>City</th>
        <th border=1>Postal Code</th>
      </tr></thead>
      <tbody>
        <#list customersList as customer>
          <tr>
            <td><a href="<@ofbizUrl>UpdateCustomer?customerId=${customer.partyId}</@ofbizUrl>">Update</a></td>
            <td>${customer.partyId}</td>
            <td>${customer.firstName?default("")}</td>
            <td>${customer.lastName?default("")}</td>
            <td>${customer.emailAddress?default("")}</td>
            <td>${customer.contactNumber?default("")}</td>
            <td>${customer.address1?default("")}</td>
            <td>${customer.city?default("")}</td>
            <td>${customer.postalCode?default("")}</td>
          </tr>
        </#list>
       </tbody>
    </table>
  </#if>
</div>