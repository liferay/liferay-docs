---
header-id: contributing-the-button-to-alloyeditor
---

# AlloyEditorへのボタンの貢献

[TOC levels=1-4]

ボタンを作成したら、使用可能なボタンのリストに追加できます。 これは、エディターのインフラストラクチャにスマートに配置された `<liferay-util:dynamic-include />` タグのおかげで実現できます。 AlloyEditorでボタンを使用可能にするには、 [`BaseDynamicInclude` クラス](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/servlet/taglib/BaseDynamicInclude.html)拡張する必要があります。 以下は、このクラスを拡張する構成例です。

1.  `DynamicInclude.class` サービスを実装し、 `BaseDynamicInclude`を拡張するコンポーネントクラスを作成します。
   
        @Component(immediate = true, service = DynamicInclude.class)
        public class MyButtonDynamicInclude extends BaseDynamicInclude {

2.  `include（）` メソッドをオーバーライドして、トランスコンパイルされたJSXファイルにスクリプトを含めます。 `StringBundler` を使用して、スクリプトを連結できます。 `sb.append（ "/ js / buttons.js"）` 行下に注意してください。 これは、 [バンドルの `build.gradle` `transpileJS` タスク](/docs/7-1/tutorials/-/knowledge_base/t/creating-the-alloyeditor-buttons-osgi-bundle)定義した `bundleFileName` です。
   
        @Override
        public void include(
                        HttpServletRequest request, HttpServletResponse response,
                        String key)
                throws IOException {
       
                ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
                        WebKeys.THEME_DISPLAY);
       
                PrintWriter printWriter = response.getWriter();
       
                StringBundler sb = new StringBundler(7);
       
                sb.append("<script src=\"");
                sb.append(themeDisplay.getPortalURL());
                sb.append(PortalUtil.getPathProxy());
                sb.append(_servletContext.getContextPath());
                sb.append("/js/buttons.js");
                sb.append("\" ");
                sb.append("type=\"text/javascript\"></script>");
       
                printWriter.println(sb.toString());
        }

3.  `register（）` メソッドをオーバーライドして、 `additionalResources` 動的インクルードを使用してスクリプトを追加します。 `@Reference` アノテーションの `ターゲット` 値は、 `bnd.bnd` ファイルで定義されているバンドルのシンボル名です。

    ``` 
        @Override
        public void register(DynamicIncludeRegistry dynamicIncludeRegistry) {
                dynamicIncludeRegistry.register(
                        "com.liferay.frontend.editor.alloyeditor.web#alloyeditor#" +
                                "additionalResources");
        }

        @Reference(
                target = "(osgi.web.symbolicname=com.liferay.frontend.editor.alloyeditor.my.button.web)"
        )
        private ServletContext _servletContext;
    ```

    }

ボタンが含まれたので、「 [AlloyEditorのツールバーへのボタンの追加](/docs/7-1/tutorials/-/knowledge_base/t/adding-buttons-to-alloyeditor-toolbars) チュートリアル」で説明されている手順に従って、ボタンをエディターのツールバーに追加できます。

## 関連トピック

[エディターに新しい動作を追加する](/docs/7-1/tutorials/-/knowledge_base/t/adding-new-behavior-to-an-editor)

[ボタンのJSXファイルの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-the-alloyeditor-buttons-jsx-file)

[WYSIWYG Editor Dynamic Includes](/docs/7-1/tutorials/-/knowledge_base/t/wysiwyg-editor-dynamic-includes)
