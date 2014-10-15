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

<portlet:defineObjects />

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
        </aui:fieldset>
      </aui:form>