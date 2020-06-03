---
header-id: reading-configuration-values-from-a-mvc-portlet
---

# MVCポートレットからの構成値の読み取り

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

ポートレットクラスまたはそのJSPから設定値を読み取ることは非常に一般的です。 このチュートリアルでは、構成をリクエストに追加してビューレイヤーから読み取る方法、およびポートレットクラスで直接読み取る方法を示します。 このチュートリアルでは、サンプル構成ポートレットと呼ぶポートレットのダミーコードを使用します。 インポートステートメントはコードスニペットに含まれているため、使用されているすべてのクラスの完全修飾クラス名（FQCN）を確認できます。

## ポートレットクラスの構成オブジェクトへのアクセス

ポートレットクラスまたはJSPの構成値が必要かどうかに関係なく、最初のステップは、 `* Portlet` クラスの構成オブジェクトにアクセスすることです。

1.  最初にインポート：
   
        package com.liferay.docs.exampleconfig;
       
        import java.io.IOException;
        import java.util.Map;
       
        import javax.portlet.Portlet;
        import javax.portlet.PortletException;
        import javax.portlet.RenderRequest;
        import javax.portlet.RenderResponse;
       
        import org.osgi.service.component.annotations.Activate;
        import org.osgi.service.component.annotations.Component;
        import org.osgi.service.component.annotations.Modified;
       
        import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
       
        import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;

2.  ポートレットクラスはコンポーネントクラスです。 構成をコンポーネントと結合するには、構成クラスのFQCNで `configurationPid` プロパティを提供します。
   
        @Component(
            configurationPid = "com.liferay.docs.exampleconfig.ExampleConfiguration",
            immediate = true,
            property = {
                "com.liferay.portlet.display-category=category.sample",
                "com.liferay.portlet.instanceable=true",
                "javax.portlet.security-role-ref=power-user,user",
                "javax.portlet.init-param.template-path=/",
                "javax.portlet.init-param.view-template=/view.jsp",
                "javax.portlet.resource-bundle=content.Language"
            },
            service = Portlet.class
        )
        public class ExampleConfigPortlet extends MVCPortlet {

    ここでは、値を中括弧（`{}`）で囲み、各PIDの間にコンマを挿入することにより、複数の構成PIDを指定できることに注意してください。

3.  `@Activate` と `@Modified`注釈が付いた `activate` メソッドを記述します。 これらの注釈が必要な理由がわからない場合は、 [アプリケーションを構成可能にする](/docs/7-1/tutorials/-/knowledge_base/t/making-applications-configurable) チュートリアルを参照してください。
   
        @Activate
        @Modified
        protected void activate(Map<String, Object> properties) {
            _configuration = ConfigurableUtil.createConfigurable(
            ExampleConfiguration.class, properties);
        }
       
        private volatile ExampleConfiguration _configuration;

    volatileフィールド `_configuration` は、 `createConfigurable` メソッドによって作成されます。 これで、フィールドを使用して構成値を取得したり、リクエストに値を設定したりできるようになり、アプリケーションのJSPで値を取得できるようになりました。

## JSPから構成にアクセスする

JSPから読み取る場合は、要求オブジェクトに構成オブジェクトを追加して、アプリケーションのビューレイヤーを構成するJSPから値を読み取れるようにします。

| **注：** ポートレットインスタンスを取得するためのショートカットメソッドがあります|構成。 このセクションで説明する方法は簡単です。このショートカットを使用しないアプローチ。 を参照してください| [を通じてポートレット・インスタンスの設定へのアクセス `PortletDisplay`](/docs/7-1/tutorials/-/knowledge_base/t/reading-configuration-values-from-a-configuration-provider#accessing-the-portlet-instance-configuration-through-the-portletdisplay) 学ぶために記事|短い方法について。

1.  構成オブジェクトを要求に追加します。 単純なポートレットの `doView` メソッドでは、次のようになります。

    ``` 
     @Override
     public void doView(RenderRequest renderRequest,
         RenderResponse renderResponse) throws IOException, PortletException {

         renderRequest.setAttribute(
             ExampleConfiguration.class.getName(), _configuration);

         super.doView(renderRequest, renderResponse);
     }
    ```

    覆われ、この例とコンポーネントクラスの主な違い [前のチュートリアル](reading-configuration-values-from-a-component) 、このクラスは、ポートレットクラスであり、それはその中に要求属性として設定オブジェクトを設定することである `doView（）` 方法。

2.  JSPから構成値を読み取ります。 最初に、これらのインポートを `view.jsp` ファイルの先頭に追加します。

    ``` 
     <%@ page import="com.liferay.docs.exampleconfig.ExampleConfiguration" %>
     <%@ page import="com.liferay.portal.kernel.util.GetterUtil" %>
    ```

3.  JSPで、要求オブジェクトから構成オブジェクトを取得し、そこから目的の構成値を読み取ります。 これを行う `view.jsp` ファイルを次に示します。

    ``` 
     <%@ include file="/init.jsp" %>

     <p>
         <b>Hello from the Example Configuration portlet!</b>
     </p>

     <%
     ExampleConfiguration configuration = (ExampleConfiguration) GetterUtil.getObject(
         renderRequest.getAttribute(ExampleConfiguration.class.getName()));

     String favoriteColor = configuration.favoriteColor();
     %>

     <p>Favorite color: <span style="color: <%= favoriteColor %>;"><%= favoriteColor %></span></p
    ```

<!--[Figure 2: Here, the Example Configuration portlet's `view.jsp` is rendered. This JSP reads the value of the `favoriteColor` configuration and displays it.](../../images/example-configuration-portlet.png)-->

このサンプルコードは、アプリケーションに次のようなメッセージを表示させます。

    Favorite color: blue

単語 <font color="blue">* blue *</font> は青色のテキストで書かれています。 `ExampleConfiguration` インターフェースでデフォルトとして指定したため、デフォルトで *ブルー* が表示されることに注意してください。 *コントロールパネル* → *構成* → *システム設定* → *プラットフォーム* → *サードパーティ* に移動し、 *サンプル構成* リンクをクリックすると、 `お気に入り色` 設定を見つけてその値を変更できます。 JSPは構成を読み取り、UIを更新するとこの更新が反映されます。

## ポートレットクラスから構成へのアクセス

JSPで設定値にアクセスする詳細な例を見てきたので、 `-Portlet` クラスで設定に直接アクセスする場合、これ以上説明することはありません。 構成プロパティの値が必要な場合は、 `_configuration.propertyName` を呼び出すと、現在構成されている値にアクセスできます。 たとえば、次のコードは、 `favoriteColor` 構成値を、リクエストオブジェクトから取得した `userFavoriteColor` と比較します。

    public boolean isFavoriteColorMatched {
    
        String userFavoriteColor = ParamUtil.getString(request, "userFavoriteColor");
    
        if (_configuration.favoriteColor == userFavoriteColor) {
    
            SessionMessages.add(request, "congratulateUser");
    
            return true;
        }
    
        return false;
    }

trueを返し、2つの文字列が相互に一致する場合に成功メッセージを追加しますが、アプリケーションのコントローラーロジックにとって意味のあることは何でもできます。

MVCポートレットで設定値を読み取ることはこれですべてです。 次のチュートリアルでは、構成の分類と、構成プロバイダーを介した構成へのアクセスについて説明します。
