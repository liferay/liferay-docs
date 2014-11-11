<%@include file = "/html/init.jsp" %>

<portlet:renderURL var="viewURL">
	<portlet:param name="mvcPath" value="/html/guestbook/view.jsp"></portlet:param>
</portlet:renderURL>

<portlet:actionURL name="addEntry" var="addEntryURL"></portlet:actionURL>

<%
long entryId = ParamUtil.getLong(renderRequest, "entryId");

Entry entry = null;

if (entryId > 0) {
	entry = EntryLocalServiceUtil.getEntry(entryId);
}
%>

<aui:form action="<%= addEntryURL %>" name="<portlet:namespace />fm">
	<aui:model-context bean="<%= entry %>" model="<%= Entry.class %>" />
	<aui:fieldset>
	    <aui:input name="name" >
	    	<aui:validator name="required" errorMessage="Please enter your name." />
	    </aui:input>

		<aui:input name="email" >
			<aui:validator name="email" />
			<aui:validator name="required" />
		</aui:input>

		<aui:input id="message" type="textarea" name="message">
			<aui:validator name="required" errorMessage="Please enter a message." />
		</aui:input>
		<div id="counterContainer"><p>Message: <span id="counter"></span> character(s) remaining</p></div>

	    <aui:input name='guestbookId' type='hidden' value='<%= ParamUtil.getString(renderRequest, "guestbookId") %>'/>

	    <aui:input name="entryId" type="hidden" />
	</aui:fieldset>

	<liferay-ui:panel defaultState="closed" extended="<%= false %>" id="populatePanel" persistState="<%= true %>" title="populate">
		<c:if test="<%= themeDisplay.isSignedIn() %>">
			<aui:button-row>
				<aui:button id="useNameButton" value="Use My Full Name"></aui:button>
			</aui:button-row>
		</c:if>
		
		<c:if test="<%= themeDisplay.isSignedIn() %>">
			<aui:button-row>
				<aui:button id="useEmailButton" value="Use My Email Address"></aui:button>
			</aui:button-row>
		</c:if>
	
		<aui:button-row>
			<aui:button id="generateMessagesButton" value="Generate Sample Messages"></aui:button>
		</aui:button-row>

		<div id="messages">
			<aui:layout>
				<aui:column>
					<div id="message1-div"></div>
				</aui:column>
				
				<aui:column>
					<div id="message2-div"></div>
				</aui:column>
				
				<aui:column>
					<div id="message3-div"></div>
				</aui:column>
			</aui:layout>
		</div>
	</liferay-ui:panel>

	<liferay-ui:asset-categories-error />
	<liferay-ui:asset-tags-error />
	<liferay-ui:panel defaultState="closed" extended="<%= false %>" id="entryCategorizationPanel" persistState="<%= true %>" title="categorization">
		<aui:fieldset>
			<aui:input name="categories" type="assetCategories" />
			
			<aui:input name="tags" type="assetTags" />
		</aui:fieldset>
	</liferay-ui:panel>
	
	<liferay-ui:panel defaultState="closed" extended="<%= false %>" id="entryAssetLinksPanel" persistState="<%= true %>" title="related-assets">
		<aui:fieldset>
			<liferay-ui:input-asset-links className="<%= Entry.class.getName() %>" classPK="<%= entryId %>"
			/>
		</aui:fieldset>
	</liferay-ui:panel>
	
	<aui:button-row>
		<aui:button type="submit" id="save"></aui:button>
		
		<aui:button type="cancel" onClick="<%= viewURL %>"></aui:button>
	</aui:button-row>
</aui:form>

<c:if test="<%= themeDisplay.isSignedIn() %>">
<%
String fullName = user.getFullName();

String emailAddress = user.getEmailAddress();
%>

<aui:script use="node, event">
var fullName = '<%= fullName %>';

var useNameButton = A.one('#useNameButton');

useNameButton.on('click', function(event) {
	var name = A.one('#<portlet:namespace/>name');
	
	name.val(fullName);
});

var emailAddress = '<%= emailAddress %>';

var useEmailButton = A.one('#useEmailButton');

useEmailButton.on('click', function(event) {
	var email = A.one('#<portlet:namespace/>email');
	
	email.val(emailAddress);
});
</aui:script>
</c:if>

<aui:script use="aui-char-counter">
AUI().use(
  function(A) {
    new A.CharCounter(
      {
        counter: '#counter',
        input: '#<portlet:namespace />message',
        maxLength: 140
      }
    );
  }
);
</aui:script>

<aui:script use="node, event">
var generateMessagesButton = A.one('#generateMessagesButton');

var message1Div = A.one('#message1-div');
var message2Div = A.one('#message2-div');
var message3Div = A.one('#message3-div');

generateMessagesButton.on('click', function(event) {
	var entryMessages = [
		'Amazing!',
		'Be careful!',
		'Best wishes!',
		'Bravo!',
		'Congratulations!',
		'Great job!',
		'Have fun!',
		"How's it going?",
		'You did it!',
		"Wow!"
	];

	if (message1Div.hasChildNodes()) {
		message1Div.get('children').remove(true);
	}
	
	var rand1 = Math.floor(Math.random() * entryMessages.length);
	message1Div.append('<p class="message" id="message1">' + entryMessages[rand1] + '</p><p id="use-message1"><input class="btn" onclick="useMessage1();" type="button" value="Use Message" /></p>');
	entryMessages.splice(rand1, 1);
	
	if (message2Div.hasChildNodes()) {
		message2Div.get('children').remove(true);
	}
	
	var rand2 = Math.floor(Math.random() * entryMessages.length);
	message2Div.append('<p class="message" id="message2">' + entryMessages[rand2] + '</p><p id="use-message2"><input class="btn" onclick="useMessage2();" type="button" value="Use Message" /></p>');
	entryMessages.splice(rand2, 1);
	
	if (message3Div.hasChildNodes()) {
		message3Div.get('children').remove(true);
	}
	
	var rand3 = Math.floor(Math.random() * entryMessages.length);
	message3Div.append('<p class="message" id="message3">' + entryMessages[rand3] + '</p><p id="use-message3"><input class="btn" onclick="useMessage3();" type="button" value="Use Message" /></p>');

	var parentNode = generateMessagesButton.get('parentNode');

	if (parentNode.get('children').size() < 2) {
		parentNode.append('<button class="btn" id="hideMessagesButton" type="button">Hide Sample Messages</button>');
	}
	
	var hideMessagesButton = A.one('#hideMessagesButton');
	
	hideMessagesButton.on('click', function(event) {
		A.one('#message1-div').get('children').remove(true);
		A.one('#message2-div').get('children').remove(true);
		A.one('#message3-div').get('children').remove(true);
		
		if (parentNode.contains(hideMessagesButton)) {
			parentNode.removeChild(hideMessagesButton);
		}
	});
});

var message = A.one('#<portlet:namespace/>message');

useMessage1 = function() {
	message.val(A.one('#message1-div').one('#message1').html());
};

useMessage2 = function() {
	message.val(A.one('#message2-div').one('#message2').html());
};

useMessage3 = function() {
	message.val(A.one('#message3-div').one('#message3').html());
};
</aui:script>