package com.liferay.docs.exampleserviceconsumerportlet;

import java.io.IOException;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.bookmarks.service.BookmarksFolderLocalService;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.service.UserLocalService;

@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=Example Service Consumer Portlet",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class ExampleServiceConsumerPortlet extends MVCPortlet {

	@Override
	public void doView(RenderRequest request, RenderResponse response)
			throws IOException, PortletException {

		int userCount = getUserLocalService().getUsersCount();
		request.setAttribute("USER_COUNT", userCount);

		int bookmarksFolderCount =
			getBookmarksFolderLocalService().getBookmarksFoldersCount();
		request.setAttribute("BOOKMARKS_FOLDER_COUNT", bookmarksFolderCount);

		super.doView(request, response);
	}

	public BookmarksFolderLocalService getBookmarksFolderLocalService() {
		return _bookmarksFolderLocalService;
	}

	public UserLocalService getUserLocalService() {
		return _userLocalService;
	}

	@Reference
	public void setBookmarksFolderLocalService(
			BookmarksFolderLocalService bookmarksFolderLocalService) {

		_bookmarksFolderLocalService = bookmarksFolderLocalService;
	}

	@Reference
	public void setUserLocalService(UserLocalService userLocalService) {
		_userLocalService = userLocalService;
	}

	private UserLocalService _userLocalService;
	private BookmarksFolderLocalService _bookmarksFolderLocalService;

}
