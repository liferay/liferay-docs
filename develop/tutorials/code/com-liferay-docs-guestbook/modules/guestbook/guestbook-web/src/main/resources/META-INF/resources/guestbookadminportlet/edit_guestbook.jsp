<%@include file = "../init.jsp" %>

        <%
                long guestbookId = ParamUtil.getLong(request, "guestbookId");
                
                Guestbook guestbook = null;

                if (guestbookId > 0) {
                        guestbook = GuestbookLocalServiceUtil.getGuestbook(guestbookId);
                }
        %>

        <portlet:renderURL var="viewURL">
                <portlet:param name="mvcPath" value="/guestbookadminportlet/view.jsp" />
        </portlet:renderURL>

        <portlet:actionURL name='<%= guestbook == null ? "addGuestbook" : "updateGuestbook" %>' var="editGuestbookURL" />

        <aui:form action="<%= editGuestbookURL %>" name="fm">
        
                <aui:model-context bean="<%= guestbook %>" model="<%= Guestbook.class %>" />

                <aui:input type="hidden" name="guestbookId"
                    value='<%= guestbook == null ? "" : guestbook.getGuestbookId() %>' />

                <aui:fieldset>
                     <aui:input name="name" />
                </aui:fieldset>

                <aui:button-row>
                     <aui:button type="submit" />
                     <aui:button onClick="<%= viewURL %>" type="cancel"  />
                </aui:button-row>
                
        <liferay-ui:asset-categories-error />
        <liferay-ui:asset-tags-error />
        
          <liferay-ui:panel defaultState="closed" extended="<%= false %>"
          id="guestbookCategorizationPanel" persistState="<%= true %>"
          title="categorization">
			<aui:fieldset>
				
				<liferay-asset:asset-categories-selector className="<%= Guestbook.class.getName() %>" classPK="<%= guestbookId %>" />
				
				<liferay-asset:asset-tags-selector className="<%= Guestbook.class.getName() %>" classPK="<%= guestbookId %>" />
            </aui:fieldset>
        </liferay-ui:panel>
        
         <liferay-ui:panel defaultState="closed" extended="<%= false %>"
          id="guestbookAssetLinksPanel" persistState="<%= true %>"
          title="related-assets">
          <aui:fieldset>
            <liferay-ui:input-asset-links
              className="<%= Guestbook.class.getName() %>"
              classPK="<%= guestbookId %>" />
          </aui:fieldset>
        </liferay-ui:panel>
        </aui:form>