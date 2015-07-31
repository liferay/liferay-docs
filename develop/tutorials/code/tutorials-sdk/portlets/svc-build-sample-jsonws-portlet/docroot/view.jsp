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

<%@ taglib uri="http://alloy.liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<portlet:defineObjects />

<portlet:renderURL var="successURL">
    <portlet:param name="mvcPath" value="/success.jsp"/>
</portlet:renderURL>

<portlet:renderURL var="failureURL">
    <portlet:param name="mvcPath" value="/failure.jsp"/>
</portlet:renderURL>

<aui:form name="<portlet:namespace />fm">
    <aui:fieldset>
        <aui:input label="First Name" name="first-name"></aui:input>
        <aui:input label="Middle Name" name="middle-name"></aui:input>
        <aui:input label="Last Name" name="last-name"></aui:input>
        <aui:input label="Screen Name" name="screen-name"></aui:input>
        <aui:input label="Email Address" name="email-address"></aui:input>
    </aui:fieldset>

	<p>Click the button below to add a new user by invoking Liferay's JSON web services.</p>
	
	<aui:button-row>
	    <aui:button id="add-user" value="Add User">
	    </aui:button>
	</aui:button-row>
</aui:form>

<aui:script use="node, event">
var addUserButton = A.one('#add-user');

var firstNameNode = A.one('#<portlet:namespace />first-name');
var middleNameNode = A.one('#<portlet:namespace />middle-name');
var lastNameNode = A.one('#<portlet:namespace />last-name');
var screenNameNode = A.one('#<portlet:namespace />screen-name');
var emailAddressNode = A.one('#<portlet:namespace />email-address');

addUserButton.on('click', function(event) {
	var firstName = firstNameNode.get('value');
	var middleName = middleNameNode.get('value');
	var lastName = lastNameNode.get('value');
	var screenName = screenNameNode.get('value');
	var emailAddress = emailAddressNode.get('value');

    var user = Liferay.Service(
        '/user/add-user',
        {
            companyId: Liferay.ThemeDisplay.getCompanyId(),
            autoPassword: false,
            password1: 'test',
            password2: 'test',
            autoScreenName: false,
            screenName: screenName,
            emailAddress: emailAddress,
            facebookId: 0,
            openId: '',
            locale: 'en_US',
            firstName: firstName,
            middleName: middleName,
            lastName: lastName,
            prefixId: 0,
            suffixId: 0,
            male: true,
            birthdayMonth: 1,
            birthdayDay: 1,
            birthdayYear: 1970,
            jobTitle: 'Tester',
            groupIds: null,
            organizationIds: null,
            roleIds: null,
            userGroupIds: null,
            sendEmail: false,
            serviceContext: {assetTagNames: ['test']}
        },
        function(obj) {
            console.log(obj);
            
            if (obj.hasOwnProperty('createDate')) {
                window.open('<%= successURL %>', '_self');
            }
            else {
                window.open('<%= failureURL %>', '_self');
            }
        }
    );
});
</aui:script>