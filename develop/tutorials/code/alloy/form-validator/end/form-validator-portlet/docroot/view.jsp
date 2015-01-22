<%
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
%>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib prefix="aui" uri="http://liferay.com/tld/aui" %>

<portlet:defineObjects />

<form name="myForm">
    <div class="control-group">
        <label class="control-label" for="firstname">First Name:</label>
        <div class="controls">
            <input name="firstname" id="firstname" type="text">
        </div>
    </div>
    <div class="control-group">
        <label class="control-label" for="email">E-mail:</label>
        <div class="controls">
            <input name="email" id="email" type="text">
        </div>
    </div>
    <div class="control-group">
        <label class="control-label" for="age">Age:</label>
        <div class="controls">
            <input name="age" id="age" type="text">
        </div>
    </div>

    <input class="btn btn-info" type="submit" value="Submit">

</form>

<aui:script>
var rules = {
	firstname: {
		required: true, /* Default - This field is required. */
		rangeLength: [2,20], /* Default - Please enter a value between 2 and 20 characters long. */
		alpha: true /* Default - Please enter only alpha characters. */
	},
	email: {
		required: true
	},
	age: {
		number: true /* Default - Please enter a valid number. */
	}
}

var fieldStrings = {
	firstname: {
        required: "The Force is strong with you, but we still need a name.",
        rangeLength: "2 to 20 characters Padawan."  
    }
}

AUI().use(
    'aui-form-validator',
    function(A) {
        new A.FormValidator(
            {
                boundingBox: myForm,
        		fieldStrings: fieldStrings,
                rules: rules,
        		showAllMessages: true
            }
        )
    }
);
</aui:script>
