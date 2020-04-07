---
header-id: configuring-your-admin-apps-actions-menu
---

# 管理アプリのアクションメニューの構成

[TOC levels=1-4]

アプリのさまざまなビューにアクションを持つ一連のボタンまたはメニューがある場合、これらのすべてのアクションを管理ポートレットの右上のメニューに移動して、プライマリアクション（多くの場合 ["追加"操作](/docs/7-1/tutorials/-/knowledge_base/t/applying-the-add-button-pattern)）を残すことができ
追加メニューに表示されます。 たとえば、Webコンテンツアプリケーションには、以下に示すアクションメニューがあります。</p> 

![図1：右上の省略メニューには、アプリのほとんどのアクションが含まれています。](../../../images/actions-menu.png)

次の手順に従って、管理アプリのアクションメニューを構成します。

1.  [`BasePortletConfigurationIcon` クラス](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/configuration/icon/BasePortletConfigurationIcon.html) を拡張し、 `PortletConfigurationIcon` サービスを実装するアクションの `* ConfigurationIcon` コンポーネントクラスを作成します。 
   
   

    ``` java
    @Component(
        immediate = true,
        service = PortletConfigurationIcon.class
    )
    public class MyConfigurationIcon extends BasePortletConfigurationIcon {
      ...
    }
    ```


2.  `getMessage（）` メソッドをオーバーライドして、アクションのラベルを指定します。 
   
   

    ``` java
    @Override
    public String getMessage(PortletRequest portletRequest) {
      ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
        WebKeys.THEME_DISPLAY);

      ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
        themeDisplay.getLocale(), ExportAllConfigurationIcon.class);

      return LanguageUtil.get(resourceBundle, "export-all-settings");
    }
    ```


3.  `get（）` メソッドをオーバーライドして、 `GET` メソッドまたは `POST` メソッドでアクションを呼び出すかどうかを指定します。 
   
   

    ``` java
    @Override
    public String getMethod() {
        return "GET";
    }
    ```


4.  アクションがクリックされたときに呼び出すURL（または `onClick` JavaScriptメソッド）を指定するには、 `getURL（）` メソッドをオーバーライドします。 
   
   

    ``` java
    @Override
    public String getURL(
      PortletRequest portletRequest, PortletResponse portletResponse) {

      LiferayPortletURL liferayPortletURL =
        (LiferayPortletURL)_portal.getControlPanelPortletURL(
          portletRequest, ConfigurationAdminPortletKeys.SYSTEM_SETTINGS,
          PortletRequest.RESOURCE_PHASE);

      liferayPortletURL.setResourceID("export");

      return liferayPortletURL.toString();
    }
    ```


5.  `getWeight（）` メソッドをオーバーライドして、アクションがリストに表示される順序を指定します。 
   
   

    ``` java
    @Override
    public double getWeight() {
      return 1;
    }
    ```


6.  `isShow（）` メソッドをオーバーライドして、アクションを表示するコンテキストを指定します。 
   
   

    ``` java
    @Override
    public boolean isShow(PortletRequest portletRequest) {
        return true;
    }
    ```


7.  構成オプションを表示するビューを定義します。 デフォルトでは、ポートレットが `mvcPath`使用する場合、ポートレット `javax.portlet.init-paramの初期化パラメーターで指定されたJSPのグローバルアクション（構成、エクスポート/インポート、最大化など）が表示されます。 view-template = / view.jsp`。 この値は、グローバルアクションを表示するJSPを示します。 ただし、ポートレットがMVCコマンドを使用する場合、グローバルアクションのビューは初期化パラメーター `javax.portlet.init-param.mvc-command-names-default-views = / wiki_admin / view` で指定する必要があり、値はグローバルアクションを表示する `mvcRenderCommandName` が含まれます。

8.  ポートレットをページに追加でき、常に構成オプションを含める場合は、この初期化パラメータをポートレットのプロパティに追加します。 
   
   

    ``` java
    javax.portlet.init-param.always-display-default-configuration-icons=true
    ```


この例では、システム設定ポートレットにアクションが表示されます。 セカンダリ画面に表示するには、以下に示すように `パス` プロパティを使用できます。 `パス` プロパティの値は、アプリの開発に使用されるMVCフレームワークによって異なります。 MVCPortletフレームワークの場合、 `mvcPath` パラメーターからのパス（多くの場合JSP）を提供します。 MVCコマンドを使用したMVCPortletの場合、パスにはアクションが表示される `mvcRenderCommandName` が含まれている必要があります（たとえば、 `/ document_library / edit_folder` など）。 



    ``` java
    @Component(
        immediate = true,
        property = {
            "javax.portlet.name=" + ConfigurationAdminPortletKeys.SYSTEM_SETTINGS,
            "path=/view_factory_instances"
        },
        service = PortletConfigurationIcon.class
    )
    public class ExportFactoryInstancesIcon extends BasePortletConfigurationIcon {

        @Override
        public String getMessage(PortletRequest portletRequest) {
            ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
                WebKeys.THEME_DISPLAY);

            ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
                themeDisplay.getLocale(), ExportFactoryInstancesIcon.class);

            return LanguageUtil.get(resourceBundle, "export-entries");
        }

        @Override
        public String getMethod() {
            return "GET";
        }

        @Override
        public String getURL(
            PortletRequest portletRequest, PortletResponse portletResponse) {

            LiferayPortletURL liferayPortletURL =
                (LiferayPortletURL)_portal.getControlPanelPortletURL(
                    portletRequest, ConfigurationAdminPortletKeys.SYSTEM_SETTINGS,
                    PortletRequest.RESOURCE_PHASE);

            ConfigurationModel factoryConfigurationModel =
                (ConfigurationModel)portletRequest.getAttribute(
                    ConfigurationAdminWebKeys.FACTORY_CONFIGURATION_MODEL);

            liferayPortletURL.setParameter(
                "factoryPid", factoryConfigurationModel.getFactoryPid());

            liferayPortletURL.setResourceID("export");

            return liferayPortletURL.toString();
        }

        @Override
        public double getWeight() {
            return 1;
        }

        @Override
        public boolean isShow(PortletRequest portletRequest) {
            ConfigurationModelIterator configurationModelIterator =
                (ConfigurationModelIterator)portletRequest.getAttribute(
                    ConfigurationAdminWebKeys.CONFIGURATION_MODEL_ITERATOR);

            if (configurationModelIterator.getTotal() > 0) {
                return true;
            }

            return false;
        }

        @Reference
        private Portal _portal;

    }
    ```


これは利用可能な方法の一部をカバーしています。 参照してください [Javadocの](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/configuration/icon/BasePortletConfigurationIcon.html) 利用可能なメソッドの完全なリストについて。

すばらしいです\！ これで、管理アプリのアクションを構成する方法がわかりました。



## 関連トピック

  - [ナビゲーションバーへの粘土パターンの適用](/docs/7-1/tutorials/-/knowledge_base/t/applying-clay-patterns-to-navigation)
  - [アプリケーションのタイトルとバックリンクの構成](/docs/7-1/tutorials/-/knowledge_base/t/configuring-your-applications-title-and-back-link)
