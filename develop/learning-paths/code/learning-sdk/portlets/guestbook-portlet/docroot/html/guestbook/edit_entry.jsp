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
var generateButton = A.one('#generateMessageButton');
var messageDiv = A.one('#messages');
var message1Div = A.one('#message1');
var message2Div = A.one('#message2');
var message3Div = A.one('#message3');
var message = A.one('#<portlet:namespace/>message');

generateButton.on('click', function(event) {
	message1Div.append('<p class="message" id="message1">Congratulations!</p><p><input onclick="set1();" class="use-message" type="button" value="Use" /></p>');
	message2Div.append('<p class="message" id="message2">Best wishes!</p><p><input onclick="set2();" class="use-message" type="button" value="Use" /></p>');
	message3Div.append('<p class="message" id="message3">Have lots of fun!</p><p><input onclick="set3();" class="use-message" type="button" value="Use" /></p>');
});

set1 = function() {
	message.val(message1Div.one('#message1').html());
};

set2 = function() {
	message.val(message2Div.one('#message2').html());
};

set3 = function() {
	message.val(message3Div.one('#message3').html());
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
	            	<aui:button id="generateMessageButton" value="Generate Sample Messages"></aui:button>
	            	<aui:button id="hideMessagesButton" value="Hide Sample Messages"></aui:button>
	            </aui:button-row>
            </div>
            
            <div id="messages">
	            <aui:layout>
		            <aui:column>
		            	<div id="message1"></div>
		            </aui:column>
		            
		            <aui:column>
		           		<div id="message2"></div>
		            </aui:column>
		            
		            <aui:column>
		            	<div id="message3"></div>
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
