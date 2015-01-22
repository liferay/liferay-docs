<%
/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */
%>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://alloy.liferay.com/tld/aui" prefix="aui" %>
<portlet:defineObjects />
<aui:script use="event, node">
          var btnSubmit = A.one("#submit");
          var verb = A.one("#<portlet:namespace />verb");
          var adj = A.one("#<portlet:namespace />adj");
          var mam = A.one("#<portlet:namespace />ani"); 
          var adv = A.one("#<portlet:namespace />adv");
          var loc = A.one("#<portlet:namespace />loc");
          var container = A.one("#container");
          
          btnSubmit.on('click', function(event){
				ver = verb.val();
        		adje = adj.val();
        		adve = adv.val();
        		mama = mam.val();
        		loca = loc.val();
        		container.append('<p id="phrase"></p>');
        		var title = A.one("#phraseTitle");
        		
        		phrase= A.one("#phrase");
        		phrase.addClass('phrase');
        		
        		if (ver != '' && adje != '' && adve != '' && mama != '' && loca != '') {
			phrase.html('Your silly phrase of the day is:<br>' + '"' + ver + 
			' your ' + adje + ' ' + mama + ' ' + adve + ' in the ' + loca + '."');	
		}
        });
        
        var title = A.one("#phraseTitle");
        var button = A.one("#button");
        var hide = true;
        button.on('click', function(event){
        
        if(hide){
        title.hide();
        hide = false;
  		button.text('Show Title');
        }
        else if(!hide){
        title.show();
        hide = true;
        button.text('Hide Title');
        }
        });
          
</aui:script>

<p id="phraseTitle">Silly Phrase Generator</p>
<div id="container"></div>
     <aui:form>
     <aui:fieldset>
		<aui:input name="adjective" id="adj" type="text">
            <aui:validator name="required"/>
        </aui:input>
        <aui:input name="verb" id="verb" type="text">
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
        <aui:button type="submit" class="btn btn-primary" id="submit" value="Generate"/>
        <aui:button id="button" value="Hide Title"/>
        </aui:fieldset>
      </aui:form>