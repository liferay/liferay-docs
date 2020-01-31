---
header-id: customizing-jsps-with-dynamic-includes
---

# 動的インクルードを使用したJSPのカスタマイズ

[TOC levels=1-4]

[`liferay-util:dynamic-include`タグ](@platform-ref@/7.1-latest/taglibs/util-taglib/liferay-util/dynamic-include.html)は、コンテンツを挿入することができるプレースホルダーです。すべてのJSPの動的インクルードタグは、コンテンツ（JavaScriptコード、HTMLなど）を挿入するための拡張ポイントです。これを行うには、挿入するコンテンツを含むモジュールを作成し、そのコンテンツを動的インクルードタグに登録して、モジュールをデプロイします。

**注**：カスタマイズするJSPに`liferay-util:dynamic-include`タグ（またはクラスが`IncludeTag`から継承しているタグ）がない場合は、[ポートレットフィルター](/docs/7-1/tutorials/-/knowledge_base/t/jsp-overrides-using-portlet-filters)など、別のカスタマイズ方法を使用する必要があります。

ブログエントリには、動的インクルードがどのように機能するかの良い例が含まれています。参考用に、[サンプルモジュール](https://portal.liferay.dev/documents/113763090/114000186/example-dynamic-include-blogs-master.zip)をダウンロードできます。

1. コンテンツを挿入する`liferay-util:dynamic-include`タグを見つけて、タグのキーを書き留めます。

   ブログアプリの`view_entry.jsp`の上部と一番下にそれぞれ動的インクルードタグがあります。

       <%@ include file="/blogs/init.jsp" %>
       
       <liferay-util:dynamic-include key="com.liferay.blogs.web#/blogs/view_entry.jsp#pre" />
       
       ... ここにJSPコンテンツ
       
       <liferay-util:dynamic-include key="com.liferay.blogs.web#/blogs/view_entry.jsp#post" />
   
   ブログビューエントリの動的インクルードキーは以下のとおりです。

   - `key="com.liferay.blogs.web#/blogs/view_entry.jsp#pre"`
   - `key="com.liferay.blogs.web#/blogs/view_entry.jsp#post"`

2. [モジュールを作成します](/docs/7-1/tutorials/-/knowledge_base/t/starting-module-development)（例：`blade create my-dynamic-include`）。（モジュールは、動的インクルードの実装を保持します。

3. モジュールビルドファイルで、これらのGradle依存関係のようなコンパイル専用の依存関係を指定します。

       dependencies {
       compileOnly group: "javax.portlet", name: "portlet-api", version: "2.0"
       compileOnly group: "javax.servlet", name: "javax.servlet-api", version: "3.0.1"
       compileOnly group: "com.liferay", name: "com.liferay.petra.string", version: "1.0.0"
       compileOnly group: "com.liferay.portal", name: "com.liferay.portal.kernel", version: "2.0.0"
       compileOnly group: "org.osgi", name: "osgi.cmpn", version: "6.0.0"
       }
   
4. [`DynamicInclude`インターフェイス](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/servlet/taglib/DynamicInclude.html)を実装するOSGiコンポーネントクラスを作成します。

   ブログ用の動的インクルード実装の例を次に示します。

       import java.io.IOException;
       import java.io.PrintWriter;
       
       import javax.servlet.http.HttpServletRequest;
       import javax.servlet.http.HttpServletResponse;
       
       import org.osgi.service.component.annotations.Component;
       
       import com.liferay.portal.kernel.servlet.taglib.DynamicInclude;
       
       @Component(
       immediate = true,
       service = DynamicInclude.class
       )
       public class BlogsDynamicInclude implements DynamicInclude {
       
       @Override
       public void include(
       HttpServletRequest request, HttpServletResponse response,
       String key)
       throws IOException {
       
       PrintWriter printWriter = response.getWriter();
       
       printWriter.println(
       "<h2>Added by Blogs Dynamic Include!</h2><br />");
       }
       
       @Override
       public void register(DynamicIncludeRegistry dynamicIncludeRegistry) {
       dynamicIncludeRegistry.register(
       "com.liferay.blogs.web#/blogs/view_entry.jsp#pre");
       }
       
       }
   
   サービス属性`service = DynamicInclude.class`を持つ`@Component`アノテーションをクラスに指定すると、クラスは`DynamicInclude`サービスコンポーネントになります。

       @Component(
       immediate = true,
       service = DynamicInclude.class
       )
   
   `include`メソッドで、コンテンツを追加します。この`include`メソッド例では、見出しを記述しています。

       @Override
       public void include(
       HttpServletRequest request, HttpServletResponse response,
       String key)
       throws IOException {
       
       PrintWriter printWriter = response.getWriter();
       
       printWriter.println(
       "<h2>Added by Blogs Dynamic Include!</h2><br />");
       }
   
   `register`メソッドで、使用する動的インクルードタグを指定します。このregisterメソッド例では、ブログの上部にある動的インクルード`view_entry.jsp`を対象としています。

       @Override
       public void register(DynamicIncludeRegistry dynamicIncludeRegistry) {
       dynamicIncludeRegistry.register(
       "com.liferay.blogs.web#/blogs/view_entry.jsp#pre");
       }
   
[モジュールをデプロイする](/docs/7-1/tutorials/-/knowledge_base/t/starting-module-development#building-and-deploying-a-module)と、JSPにコンテンツが動的に含まれます。動的コンテンツをJSPに挿入できました。
