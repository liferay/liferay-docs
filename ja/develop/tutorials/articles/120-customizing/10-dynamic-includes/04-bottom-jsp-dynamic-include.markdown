---
header-id: bottom-jsp-dynamic-includes
---

# Bottom JSP動的インクルード

[TOC levels=1-4]

`bottom.jsp`動的インクルードは、テーマの本文の下部に追加のHTMLまたはスクリプトを読み込みます。次のキーが使用可能です。

テーマの本文の下部で、既存のHTMLまたはスクリプトの前に追加のHTMLまたはスクリプトを読み込みます。

    /html/common/themes/bottom.jsp#pre

または、テーマの本文の下部で、既存のHTMLまたはスクリプトの後に追加のHTMLまたはスクリプトを読み込むこともできます。

    /html/common/themes/bottom.jsp#post

次の例では、テーマの本文の下部で、既存のスクリプトの後に、シミュレーションパネル用の追加のスクリプトが含まれています。

`SimulationDeviceDynamicInclude` Javaクラス：

    @Component(immediate = true, service = DynamicInclude.class)
    public class SimulationDeviceDynamicInclude extends BaseDynamicInclude {
    
    @Override
    public void include(
    HttpServletRequest request, HttpServletResponse response,
    String key)
    throws IOException {
    
    PrintWriter printWriter = response.getWriter();
    
    printWriter.print(_TMPL_CONTENT);
    }
    
    @Override
    public void register(DynamicIncludeRegistry dynamicIncludeRegistry) {
    dynamicIncludeRegistry.register("/html/common/themes/bottom.jsp#post");
    }
    
    private static final String _TMPL_CONTENT = StringUtil.read(
    SimulationDeviceDynamicInclude.class,
    "/META-INF/resources/simulation_device_dynamic_include.tmpl");
    
    }

`simulation_device_dynamic_include.tmpl`：

    <script type="text/javascript">
    // <![CDATA[
    AUI().use(
    'aui-base',
    function(A) {
    var frameElement = window.frameElement;
    
    if (frameElement && frameElement.getAttribute('id') === 'simulationDeviceIframe') {
    A.getBody().addClass('lfr-has-simulation-panel');
    }
    }
    );
    // ]]>
    </script>

シミュレーションパネルが開いているとき、スクリプトは`lfr-has-simulation-panel`クラスをテーマの本文に追加します。

![図1：Bottom JSP動的インクルードを使用して、スクリプトを挿入できます。](../../../images/dynamic-include-bottom-jsp-post-simulation-tmpl.png)

これで、`bottom.jsp`動的インクルードの使用方法がわかりました。

## 関連トピック

[Top Head JSP Dynamic Includes](/docs/7-1/tutorials/-/knowledge_base/t/top-head-jsp-dynamic-includes)

[Top JS Dynamic Include](/docs/7-1/tutorials/-/knowledge_base/t/top-js-dynamic-include)

[WYSIWYG Editor Dynamic Includes](/docs/7-1/tutorials/-/knowledge_base/t/wysiwyg-editor-dynamic-includes)
