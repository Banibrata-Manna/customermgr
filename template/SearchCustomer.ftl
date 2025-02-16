<form method="post" action="<@ofbizUrl>findCustomers</@ofbizUrl>">
    <fieldset>
        <label for="firstName">${uiLabelMap.CustomermgrFirstName}</label>
        <input type="text" name="firstName"/>
        </br>
        </br>
        <label for="lastName">${uiLabelMap.CustomermgrLastName}</label>
        <input type="text" name="lastName"/>
        </br>
        </br>
        <label for="emailAddress">${uiLabelMap.CustomermgrEmailAddress}</label>
        <input type="text" name="emailAddress"/>
        </br>
        </br>
        <label for="contactNumber">${uiLabelMap.CustomermgrContactNumber}</label>
        <input type="text" name="contactNumber"/>
        </br>
        </br>
        <label for="address1">${uiLabelMap.CustomermgrAddress}</label>
        <input type="text" name="address1"/>
        </br>
        </br>
        <label for="city">${uiLabelMap.CustomermgrCity}</label>
        <input type="text" name="city"/>
        </br>
        </br>
        <label for="postalCode">${uiLabelMap.CustomermgrPostalCode}</label>
        <input type="text" name="postalCode"/>
        </br>
        </br>
        <!-- Submit Button -->
        <button type="submit">${uiLabelMap.CommonFind}</button>
    </fieldset>
</form>
