package com.liferay.docs.portlet.portlet;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.editor.configuration.BaseEditorConfigContributor;
import com.liferay.portal.kernel.editor.configuration.EditorConfigContributor;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;

import java.util.Map;
import java.util.Objects;
import com.liferay.portal.kernel.util.Validator;

@Component(
    immediate = true,
    property = {
      "editor.name=alloyeditor",
      "service.ranking:Integer=100"
    },
    service = EditorConfigContributor.class  
)
public class MyButtonEditorConfigContributor extends BaseEditorConfigContributor {

  @Override
  public void populateConfigJSONObject(
         JSONObject jsonObject, Map<String, Object> inputEditorTaglibAttributes,
         ThemeDisplay themeDisplay,
         RequestBackedPortletURLFactory requestBackedPortletURLFactory) {
           
           JSONObject toolbarsJSONObject = jsonObject.getJSONObject("toolbars");
           
           if (toolbarsJSONObject == null) {
                   toolbarsJSONObject = JSONFactoryUtil.createJSONObject();
           }
           
           JSONObject stylesToolbar = toolbarsJSONObject.getJSONObject("styles");

          if (stylesToolbar == null) {
                  stylesToolbar = JSONFactoryUtil.createJSONObject();
          }
          
          JSONArray selectionsJSONArray = stylesToolbar.getJSONArray("selections");
          
          for (int i = 0; i < selectionsJSONArray.length(); i++) {
              JSONObject selection = selectionsJSONArray.getJSONObject(i);

              if (Objects.equals(selection.get("name"), "text")) {
                      JSONArray buttons = selection.getJSONArray("buttons");
                      buttons.put("logSelectedText");
              }
          }
          stylesToolbar.put("selections", selectionsJSONArray);
          toolbarsJSONObject.put("styles", stylesToolbar);
          jsonObject.put("toolbars", toolbarsJSONObject);
  }
}