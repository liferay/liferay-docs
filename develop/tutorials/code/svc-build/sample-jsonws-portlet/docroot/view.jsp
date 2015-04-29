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

<p>Click the button below to create a new user by invoking Liferay's JSON web services.</p>

<aui:button-row>
    <aui:button id="create-user" value="Create User">
    </aui:button>
</aui:button-row>

<aui:script use="node, event">
var createUserButton = A.one('#create-user');

createUserButton.on('click', function(event) {
    Liferay.Service(
        '/user/add-user',
        {
            companyId: Liferay.ThemeDisplay.getCompanyId(),
            autoPassword: false,
            password1: 'test',
            password2: 'test',
            autoScreenName: false,
            screenName: 'joe.bloggs',
            emailAddress: 'joe.bloggs@liferay.com',
            facebookId: 0,
            openId: '',
            locale: 'en_US',
            firstName: 'Joe',
            middleName: 'B',
            lastName: 'Bloggs',
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
        }
    );
});
</aui:script>