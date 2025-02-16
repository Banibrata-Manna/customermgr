<#if customer?has_content>
    <form method="post" action="<@ofbizUrl>updateCustomer</@ofbizUrl>">
        <fieldset>
            <label for="firstName">${uiLabelMap.CustomermgrFirstName}</label>
            <input type="text" name="firstName" value=${customer.partyId}/>
            </br>
            </br>
            <label for="lastName">${uiLabelMap.CustomermgrLastName}</label>
            <input type="text" name="lastName" value=${customer.firstName}/>
            </br>
            </br>
            <label for="emailAddress">${uiLabelMap.CustomermgrEmailAddress}</label>
            <input type="text" name="emailAddress" value=${customer.lastName}/>
            </br>
            </br>
            <label for="contactNumber">${uiLabelMap.CustomermgrContactNumber}</label>
            <input type="text" name="contactNumber" value=${customer.contactNumber}/>
            </br>
            </br>
            <label for="address1">${uiLabelMap.CustomermgrAddress}</label>
            <input type="text" name="address1" value=${customer.address1}/>
            </br>
            </br>
            <label for="city">${uiLabelMap.CustomermgrCity}</label>
            <input type="text" name="city" value=${customer.city}/>
            </br>
            </br>
            <label for="postalCode">${uiLabelMap.CustomermgrPostalCode}</label>
            <input type="text" name="postalCode" value=${customer.postalCode}/>
            </br>
            </br>
            <!-- Submit Button -->
            <button type="submit">${uiLabelMap.CommonFind}</button>
        </fieldset>
    </form>
</#if>