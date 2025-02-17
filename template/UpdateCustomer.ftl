<form method="post" action="<@ofbizUrl>updateCustomer</@ofbizUrl>">
    <fieldset>
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
        <button type="submit">${uiLabelMap.CommonUpdate}</button>
    </fieldset>
</form>