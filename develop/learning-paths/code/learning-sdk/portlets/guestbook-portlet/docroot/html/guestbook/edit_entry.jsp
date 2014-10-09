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
var generateButton = A.one('#generateMessagesButton');
var hideButton = A.one('#hideMessagesButton');

var message1Div = A.one('#message1Div');
var message2Div = A.one('#message2Div');
var message3Div = A.one('#message3Div');

var message1 = A.one('#message1');
var message2 = A.one('#message2');
var message3 = A.one('#message3');

var message = A.one('#<portlet:namespace/>message');

generateButton.on('click', function(event) {
	if(!message1Div.hasChildNodes()) {
		message1Div.append('<p class="message" id="message1">Congratulations!</p><p class="use-message" id="use-message1"><input onclick="set1();" type="button" value="Use" /></p>');
	}
	
	if(!message2Div.hasChildNodes()) {
		message2Div.append('<p class="message" id="message2">Best wishes!</p><p class="use-message" id="use-message2"><input onclick="set2();" type="button" value="Use" /></p>');
	}
	
	if(!message3Div.hasChildNodes()) {
		message3Div.append('<p class="message" id="message3">Have lots of fun!</p><p class="use-message" id="use-message3"><input onclick="set3();" type="button" value="Use" /></p>');
	}
});

hideButton.on('click', function(event) {
	A.one('#message1Div').removeChild(A.one('#message1'));
	A.one('#message1Div').removeChild(A.one('#use-message1'));
	A.one('#message2Div').removeChild(A.one('#message2'));
	A.one('#message2Div').removeChild(A.one('#use-message2'));
	A.one('#message3Div').removeChild(A.one('#message3'));
	A.one('#message3Div').removeChild(A.one('#use-message3'));
});

set1 = function() {
	message.val(A.one('#message1Div').one('#message1').html());
};

set2 = function() {
	message.val(A.one('#message2Div').one('#message2').html());
};

set3 = function() {
	message.val(A.one('#message3Div').one('#message3').html());
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
	            	<aui:button id="hideMessagesButton" value="Hide Sample Messages"></aui:button>
	            </aui:button-row>
            </div>
            
            <div id="messages">
	            <aui:layout>
		            <aui:column>
		            	<div id="message1Div"></div>
		            </aui:column>
		            
		            <aui:column>
		           		<div id="message2Div"></div>
		            </aui:column>
		            
		            <aui:column>
		            	<div id="message3Div"></div>
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
