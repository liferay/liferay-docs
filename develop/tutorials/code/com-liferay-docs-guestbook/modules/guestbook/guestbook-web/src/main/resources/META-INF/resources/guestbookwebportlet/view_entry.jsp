<%@ include file="../init.jsp"%>

        <%
          long entryId = ParamUtil.getLong(renderRequest, "entryId");

          long guestbookId = ParamUtil.getLong(renderRequest, "guestbookId");

          Entry entry = null;

          if (entryId > 0) {
            entry = EntryLocalServiceUtil.getEntry(entryId);

            entryId = entry.getEntryId();
          }

          entry = EntryLocalServiceUtil.getEntry(entryId);
          entry = entry.toEscapedModel();

          AssetEntry assetEntry = 
          AssetEntryLocalServiceUtil.getEntry(Entry.class.getName(), 
          entry.getEntryId());
                    
           String currentURL = PortalUtil.getCurrentURL(request);
        PortalUtil.addPortletBreadcrumbEntry(request, entry.getMessage(),
        currentURL);
        
        PortalUtil.setPageSubtitle(entry.getMessage(), request);
          PortalUtil.setPageDescription(entry.getMessage(), request);


        
        %>
        
         <liferay-portlet:renderURL varImpl="viewEntryURL">
          <portlet:param name="mvcPath"
            value="/guestbookwebportlet/view_entry.jsp" />
          <portlet:param name="entryId" value="<%=String.valueOf(entryId)%>" />
        </liferay-portlet:renderURL>

        <liferay-portlet:renderURL varImpl="viewURL">
          <portlet:param name="mvcPath"
            value="/guestbookwebportlet/view.jsp" />
        </liferay-portlet:renderURL>

        <liferay-ui:header backURL="<%=viewURL.toString()%>"
          title="<%=entry.getName()%>" 
        />
        
        <dl>
          <dt>Guestbook</dt>
          <dd><%=GuestbookLocalServiceUtil.getGuestbook(entry.getGuestbookId()).getName()%></dd>
          <dt>Name</dt>
          <dd><%=entry.getName()%></dd>
          <dt>Message</dt>
          <dd><%=entry.getMessage()%></dd>
        </dl>
                <c:if test="<%= themeDisplay.isSignedIn() %>">
        
        <liferay-ui:panel-container extended="<%=false%>"
          id="guestbookCollaborationPanelContainer" persistState="<%=true%>">
          <liferay-ui:panel collapsible="<%=true%>" extended="<%=true%>"
            id="guestbookCollaborationPanel" persistState="<%=true%>"
            title="Collaboration">
        
        <liferay-ui:ratings className="<%=Entry.class.getName()%>"
              classPK="<%=entry.getEntryId()%>" type="stars" />

            <br />
            
              <%
                Discussion discussion = CommentManagerUtil.getDiscussion(user.getUserId(), scopeGroupId, Entry.class.getName(), entry.getEntryId(), new ServiceContextFunction(request));
                %>

                <c:if test="<%= discussion != null %>">
                              <h2>
            <strong><liferay-ui:message arguments="<%= discussion.getDiscussionCommentsCount() %>" key='<%= (discussion.getDiscussionCommentsCount() == 1) ? "x-comment" : "x-comments" %>' /></strong>

                    <liferay-comment:discussion
                        className="<%= Entry.class.getName() %>"
                        classPK="<%= entry.getEntryId() %>"
                        discussion="<%= discussion %>"
                        formName="fm2"
                        ratingsEnabled="true"
                        redirect="<%= currentURL %>"
                        userId="<%= entry.getUserId() %>"
                    />
                            </c:if>
          </liferay-ui:panel>
          
        </liferay-ui:panel-container>
                </c:if>
        
