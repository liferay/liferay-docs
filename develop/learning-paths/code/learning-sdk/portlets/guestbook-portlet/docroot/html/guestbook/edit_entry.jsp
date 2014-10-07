

<%@include file = "/html/init.jsp" %>

<portlet:renderURL var="viewURL">
	<portlet:param name="mvcPath" value="/html/guestbook/view.jsp"></portlet:param>
</portlet:renderURL>

<portlet:actionURL name="addEntry" var="addEntryURL"></portlet:actionURL>

<aui:script>
AUI().use(
  'aui-char-counter',
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
var button = A.one('#genMess');
var messageDiv = A.one('#messages');
var use = A.one('#use');
var message = A.one('#<portlet:namespace/>message');


button.on('click', function(event) {

messageDiv.append('<p id="con">Congratulations!</p><input type="button" value="use" id="use" onclick="set();"/><br>');
messageDiv.append('<p id="con2">Best Wishes!</p><input type="button" value="use" id="use" onclick="set2();"/><br>');
messageDiv.append('<p id="con3">Had a lot of fun!</p><input type="button" value="use" id="use" onclick="set3();"/><br>');


});
set = function(){
	message.val(messageDiv.one('#con').html());

};
set2 = function(){
	message.val(messageDiv.one('#con2').html());

};
set3 = function(){
	message.val(messageDiv.one('#con3').html());

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
            <aui:input id="message" cssClass="message" type="textarea" name="message">
            	<aui:validator name="required" errorMessage="Leave a message please." />
            </aui:input>
            <div id="messages"></div>
            <div id="buttonWrapper">
            <aui:button id="genMess" value="generate sample messages"></aui:button>
            </div>
            <aui:input name='guestbookId' type='hidden' value='<%= ParamUtil.getString(renderRequest, "guestbookId") %>'/>
            <aui:input name="entryId" type="hidden" />
			<div id="counterContainer">
            <span id="counter"></span> character(s) remaining
            </div>
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
