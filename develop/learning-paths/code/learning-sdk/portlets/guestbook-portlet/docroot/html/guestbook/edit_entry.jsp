

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

<aui:script use="event, node">
    var btnSubmit = A.one("#submit");
    var btnSave = A.one("#save");
    var verb = A.one("#<portlet:namespace />verb");
    var adj = A.one("#<portlet:namespace />adj");
	var mam = A.one("#<portlet:namespace />ani");
	var adv = A.one("#<portlet:namespace />adv");
	var loc = A.one("#<portlet:namespace />loc");
	var container = A.one("#container");
	var title = A.one("#phraseTitle");
	var checkbox = A.one("#<portlet:namespace/>showCheckbox");
    var silly = A.one("#silly");
    
    
    btnSubmit.on('click', function(event){
        
        ver = (verb.get('value'));
        adje = (adj.get('value'));
        adve = (adv.get('value'));
        mama = (mam.get('value'));
        loca = (loc.get('value'));
        container.append('<p id="phrase"></p>');
        phrase= A.one("#phrase");
        
        if (ver != '' && adje != '' && adve != '' && mama != '' && loca != '') {
			phrase.html('Your silly phrase of the day is:<br>' + '"' + ver + 
			' your ' + adje + ' ' + mama + ' ' + adve + ' in the ' + loca + '."');	
		}
		
});

checkbox.on('change', function(event){
var checked = checkbox.attr('checked');
if(checked){
	silly.attr('style', 'display:inline');
}
else if(!checked){	
	silly.attr('style', 'display:none');
}
	
});

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
            
            <aui:input name='guestbookId' type='hidden' value='<%= ParamUtil.getString(renderRequest, "guestbookId") %>'/>
            <aui:input name="entryId" type="hidden" />
			<div id="counterContainer">
            <span id="counter"></span> character(s) remaining
            </div>
            <div>
            <div id="cbox"><aui:input id="show" type="checkbox" name="include" label="Show Silly Phrase Generator" checked="false"/></div>
            <div id="ttip"><liferay-ui:icon-help message="Check the box to create a silly phrase or uncheck it to hide the form. Fill out the fields below with the correct parts of speech to generate a unique and silly phrase."/></div>
            </div>
        </aui:fieldset>

        <aui:button-row>

			<aui:button type="submit" id="save"></aui:button>
			<aui:button type="cancel" onClick="<%= viewURL %>"></aui:button>

        </aui:button-row>
</aui:form>
<div id="silly">
<p id="phraseTitle">Silly Phrase Generator</p>
<div id="container"></div>
<aui:form name="<portlet:namespace />silly">

		<aui:fieldset>
			<aui:input name="adjective" id="adj" type="text" >
            	<aui:validator name="required"/>
            </aui:input>
            <aui:input name="verb" id="verb" type="text" >
            	<aui:validator name="required"/>
            </aui:input>
            <aui:input name="adverb" id="adv" type="text">
            	<aui:validator name="required"/>
            </aui:input>
            <aui:input name="animal" id="ani" type="text">
            	<aui:validator name="required"/>
            </aui:input>
            <aui:input name="location" id="loc" type="text">
            	<aui:validator name="required"/>
            </aui:input>
        </aui:fieldset>
        
        <aui:button type="submit" class="btn btn-primary" id="submit" value="Generate"/>

</aui:form>
</div>