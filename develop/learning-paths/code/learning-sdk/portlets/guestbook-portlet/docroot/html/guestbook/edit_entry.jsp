<%@include file = "/html/init.jsp" %>

<portlet:renderURL var="viewURL">
	<portlet:param name="mvcPath" value="/html/guestbook/view.jsp"></portlet:param>
</portlet:renderURL>

<portlet:actionURL name="addEntry" var="addEntryURL"></portlet:actionURL>

<aui:script use="aui-char-counter">
AUI().use(
  function(A) {
    new A.CharCounter(
      {
        counter: '#counter',
        input: '#<portlet:namespace />message',
        maxLength: 70
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
	
	var rand1 = Math.floor(Math.random() * entryMessages.length);

	if (!message1Div.hasChildNodes()) {	
		message1Div.append('<p class="message" id="message1">' + entryMessages[rand1] + '</p><p id="use-message1"><input class="btn" onclick="useMessage1();" type="button" value="Use Message" /></p>');
	}
	
	entryMessages.splice(rand1, 1);
	
	var rand2 = Math.floor(Math.random() * entryMessages.length);
	
	if (!message2Div.hasChildNodes()) {
		message2Div.append('<p class="message" id="message2">' + entryMessages[rand2] + '</p><p id="use-message2"><input class="btn" onclick="useMessage2();" type="button" value="Use Message" /></p>');
	}
	
	entryMessages.splice(rand2, 1);
	
	var rand3 = Math.floor(Math.random() * entryMessages.length);
	
	if (!message3Div.hasChildNodes()) {
		message3Div.append('<p class="message" id="message3">' + entryMessages[rand3] + '</p><p id="use-message3"><input class="btn" onclick="useMessage3();" type="button" value="Use Message" /></p>');
	}
	
	var buttonHolder = A.one('.button-holder');

	if (A.one('#buttonWrapper').contains(buttonHolder)) {
		if (buttonHolder.get('children').size() < 2) {
			buttonHolder.append('<button class="btn" id="hideMessagesButton" type="button">Hide Sample Messages</button>');
		}
	}
	
	var hideButton = A.one('#hideMessagesButton');
	
	hideButton.on('click', function(event) {
		A.one('#message1-div').removeChild(A.one('#message1'));
		A.one('#message1-div').removeChild(A.one('#use-message1'));
		A.one('#message2-div').removeChild(A.one('#message2'));
		A.one('#message2-div').removeChild(A.one('#use-message2'));
		A.one('#message3-div').removeChild(A.one('#message3'));
		A.one('#message3-div').removeChild(A.one('#use-message3'));
		
		buttonHolder = A.one('.button-holder');
		
		if (A.one('#buttonWrapper').contains(buttonHolder)) {
			buttonHolder.removeChild(A.one('#hideMessagesButton'));
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
            	<aui:validator name="required"/>
            </aui:input>
            
            <aui:input name="email" >
            	<aui:validator name="email"/>
            	<aui:validator name="required"/>
            </aui:input>
            
            <div id="counterContainer"><p><span id="counter"></span> character(s) remaining</p></div>
            <aui:input id="message" cssClass="message" type="textarea" name="message">
            	<aui:validator name="required" errorMessage="Please enter a message." />
            </aui:input>
           
            <div id="buttonWrapper">
	            <aui:button-row>
	            	<aui:button id="generateMessagesButton" value="Generate Sample Messages"></aui:button>
	            </aui:button-row>
            </div>
            
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
            
            <aui:input name='guestbookId' type='hidden' value='<%= ParamUtil.getString(renderRequest, "guestbookId") %>'/>
            
            <aui:input name="entryId" type="hidden" />
        </aui:fieldset>
        
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
				<liferay-ui:input-asset-links
					className="<%= Entry.class.getName() %>"
					classPK="<%= entryId %>"
				/>
			</aui:fieldset>
		</liferay-ui:panel>

        <aui:button-row>
			<aui:button type="submit" id="save"></aui:button>
			
			<aui:button type="cancel" onClick="<%= viewURL %>"></aui:button>
        </aui:button-row>
</aui:form>
