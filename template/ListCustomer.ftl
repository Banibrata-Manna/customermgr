<div class="screenlet-body">
  <#if customerList?has_content>
    <table cellspacing=0 cellpadding=2 border=0 class="basic-table">
      <thead><tr>
        <th>partyId</th>
        <th>firstName</th>
        <th>lastName</th>
        <th>Email Address</th>
        <th>Contact Number</th>
        <th>Address</th>
        <th>City</th>
        <th>Postal Code</th>
      </tr></thead>
      <tbody>
        <#list customerList as customer>
          <tr>
            <td>${customer.partyId}</td>
            <td>${customer.firstName?default("NA")}</td>
            <td>${customer.lastName?default("NA")}</td>
            <td>${customer.emailAddress}</td>
            <td>${customer.contactNumber}</td>
            <td>${customer.address1}</td>
            <td>${customer.city}</td>
            <td>${customer.postalCode}</td>
          </tr>
        </#list>
       </tbody>
    </table>
  </#if>
</div>