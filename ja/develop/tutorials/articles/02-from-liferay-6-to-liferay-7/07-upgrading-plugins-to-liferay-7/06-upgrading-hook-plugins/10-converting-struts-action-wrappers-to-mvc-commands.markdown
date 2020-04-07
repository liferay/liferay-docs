---
header-id: converting-strutsactionwrappers-to-mvccommands
---

# Struts操作ラッパーからMVCコマンドへの変換

[TOC levels=1-4]

Liferayポータル6.1および6.2では、開発者はフックおよび`StrutsAction`ラッパーを使用して、ポータルおよびポートレットStruts操作をカスタマイズできました。 たとえば、ログインポートレットのログイン操作をオーバーライドしたフックの`liferay-hook.xml`ファイルには、次のエントリがありました。

    <struts-action>
        <struts-action-path>/login/login</struts-action-path>
        <struts-action-impl>
        com.liferay.sample.hook.action.ExampleStrutsPortletAction
        </struts-action-impl>
    </struts-action>

`liferay-hook.xml`には、Strutsマッピングと、デフォルトのログイン操作をオーバーライドする新しいクラスが含まれています。

ラッパーは、Struts操作がそれぞれポータルであるかポートレット操作であるかに応じて、 `BaseStrutsAction`または`BaseStrutsPortletAction`を拡張できます。

7.0以降このメカニズムはStruts操作を使用しなくなったため、ほとんどのポートレットに適用されなくなりましたが、代わりにLiferay`MVCCommand`を使用します。

このチュートリアルでは、既存の`StrutsAction`ラッパーを`MVCCommand`に変換する方法を示します。

## 古いラッパーをMVCコマンドに変換する

`StrutsAction`ラッパーを`MVCCommand`へ変換することは、思うよりも簡単です。

レビューとして、レガシー`StrutsAction`ラッパーは`processAction`、 `render`、および`serveResource`などのほか、たとえ1つのメソッドのみがカスタマイズされていた場合であっても、すべてのメソッドを実装しました。 これらの各メソッドは、異なるクラスを使用して個別にカスタマイズできるため、ロジックをよりシンプルで簡単に保守できるようになりました。 `StrutsAction`ラッパーでカスタマイズしたメソッドに応じて、以下に示す適合した[`MVCCommand`インターフェイス](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/bridges/mvc/MVCCommand.html)を使用する必要があります。

  - process操作 → [MVCActionCommand](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/bridges/mvc/MVCActionCommand.html)
  - レンダリング→ [MVCRenderCommand](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/bridges/mvc/MVCRenderCommand.html)
  - サーブリソース → [MVCResourceCommand](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/bridges/mvc/MVCResourceCommand.html)

`StrutsAction`ラッパーの例については、[`ExampleStrutsPortletAction`クラス](/docs/6-2/tutorials/-/knowledge_base/t/overriding-and-adding-struts-actions) をご覧ください。 オーバーライドされた操作に応じて、ユーザーは異なる`MVCCommand`を使用する必要があります。 この例では、操作とレンダリングがオーバーライドされているため、新しいパターンに移行するには、 `MVCActionCommand`と`MVCRenderCommand`の2つのクラスを作成します。

次に、`MVCCommand`が使用するマッピングを決定します。

## MVCコマンド URLのマッピング

ほとんどの場合、`MVCCommand`マッピングは、従来のStruts操作で定義されたマッピングと同じです。

最初のログインの例をもう一度使用すると、`struts-action-path`マッピングと`/login/login`が、@product-ver@の`MVCCommand`マッピングと同じままですが、マッピングの一部が変更されている場合があります。 @product@のソースコードをチェックして、正しいマッピングを決定することをお勧めします。

ポートレットURLタグを使用してMVCコマンドURLにマップします。

  - `MVCRenderCommand`URLは、`mvcRenderCommandName`パラメーターに入ります。 例:

    ``` 
    <portlet:renderURL var="editEntryURL">
        <portlet:param name="mvcRenderCommandName" value="/hello/edit_entry" 
        />
        <portlet:param name="entryId" value="<%= String.valueOf(
        entry.getEntryId()) %>" />
    </portlet:renderURL>    
    ```

  - `MVCActionCommand`URLは、`actionURL`タグ`name`属性またはパラメーター`ActionRequest.ACTION_NAME`に入ります。 例:
    
        <portlet:actionURL name="/blogs/edit_entry" var="editEntryURL" />

  - `MVCResourceCommand`URLは、`resourceURL`タグ`id`属性に入ります。 例:
    
        <portlet:resourceURL id="/login/captcha" var="captchaURL" />

この情報を取得したら、次の`MVCCommand`チュートリアルにある手順に従って、 `MVCCommand`をオーバーライドできます。

  - [MVCコマンドにロジックを追加する](/docs/7-1/tutorials/-/knowledge_base/t/adding-logic-to-mvc-commands)
  - [MVCレンダーコマンドのオーバーライド](/docs/7-1/tutorials/-/knowledge_base/t/overriding-mvcrendercommand)
  - [MVC操作コマンドのオーバーライド](/docs/7-1/tutorials/-/knowledge_base/t/overriding-mvcactioncommand)
  - [MVCリソースコマンドのオーバーライド](/docs/7-1/tutorials/-/knowledge_base/t/overriding-mvcresourcecommand)

ここまで、`StrutsActionWrapper`を`MVCCommand`に変換する方法について学びました。

## 関連トピック

[MVCコマンドのオーバーライド](/docs/7-1/tutorials/-/knowledge_base/t/overriding-mvc-commands)

[Liferay MVCポートレット](/docs/7-1/tutorials/-/knowledge_base/t/liferay-mvc-portlet)

[プラグインの依存関係の解決](/docs/7-1/tutorials/-/knowledge_base/t/resolving-a-plugins-dependencies)

[依存関係の設定方法](/docs/7-1/tutorials/-/knowledge_base/t/configuring-dependencies)

[Liferay Mavenビルドのアップグレード](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-the-liferay-maven-build)
