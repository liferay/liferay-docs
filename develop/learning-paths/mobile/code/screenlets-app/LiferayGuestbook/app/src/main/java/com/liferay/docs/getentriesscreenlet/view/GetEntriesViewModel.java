package com.liferay.docs.getentriesscreenlet.view;

import com.liferay.docs.model.EntryModel;
import com.liferay.mobile.screens.base.view.BaseViewModel;

import java.util.List;

public interface GetEntriesViewModel extends BaseViewModel {

    void showFinishOperation(String userAction, List<EntryModel> entries);
}