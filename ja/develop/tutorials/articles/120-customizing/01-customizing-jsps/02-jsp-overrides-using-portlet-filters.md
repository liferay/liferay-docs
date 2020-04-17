---
header-id: jsp-overrides-using-portlet-filters
---

# ポートレットフィルターを使用したJSPのオーバーライド

[TOC levels=1-4]

ポートレットフィルターを使用すると、処理される前のポートレット要求と、処理された後クライアントに返される前のポートレット応答をインターセプトできます。要求や応答を操作して、JSPコンテンツを変更できます。動的インクルードとは異なり、ポートレットフィルターを使用すると、クライアントに返送されるすべてのコンテンツにアクセスできます。

次の例では、ポートレットフィルターを使用して、Liferayのブログポートレットのコンテンツを変更する方法を示します。参考用に、[サンプルモジュール](https://portal.liferay.dev/documents/113763090/114000186/example-portlet-filter-customize-jsp-master.zip)をダウンロードできます。

1. 新しいモジュールを作成し、次のGradle形式で示されているコンパイル専用の依存関係を指定していることを確認します。

       dependencies {
       compileOnly group: "javax.portlet", name: "portlet-api", version: "2.0"
       compileOnly group: "javax.servlet", name: "javax.servlet-api", version: "3.0.1"
       compileOnly group: "com.liferay.portal", name: "com.liferay.portal.kernel", version: "2.0.0"
       compileOnly group: "org.osgi", name: "osgi.cmpn", version: "6.0.0"
       }
   
2. `javax.portlet.filter.RenderFilter`インターフェイスを実装するOSGiコンポーネントクラスを作成します。

   ブログ用のポートレットフィルターの実装例を次に示します。

       import java.io.IOException;
       
       import javax.portlet.PortletException;
       import javax.portlet.RenderRequest;
       import javax.portlet.RenderResponse;
       import javax.portlet.filter.FilterChain;
       import javax.portlet.filter.FilterConfig;
       import javax.portlet.filter.PortletFilter;
       import javax.portlet.filter.RenderFilter;
       import javax.portlet.filter.RenderResponseWrapper;
       
       import org.osgi.service.component.annotations.Component;
       
       import com.liferay.portal.kernel.util.PortletKeys;
       
       @Component(
       immediate = true,
       property = {
       "javax.portlet.name=" + PortletKeys.BLOGS
       },
       service = PortletFilter.class
       )
       public class BlogsRenderFilter implements RenderFilter {
       
       @Override
       public void init(FilterConfig config) throws PortletException {
       
       }
       
       @Override
       public void destroy() {
       
       }
       
       @Override
       public void doFilter(RenderRequest request, RenderResponse response, FilterChain chain)
       throws IOException, PortletException {
       
       RenderResponseWrapper renderResponseWrapper = new BufferedRenderResponseWrapper(response);
       
       chain.doFilter(request, renderResponseWrapper);
       
       String text = renderResponseWrapper.toString();
       
       if (text != null) {
       String interestingText = "<input  class=\"field form-control\"";
       
       int index = text.lastIndexOf(interestingText);
       
       if (index >= 0) {
       String newText1 = text.substring(0, index);
       String newText2 = "\n<p>Added by Blogs Render Filter!</p>\n";
       String newText3 = text.substring(index);
       
       String newText = newText1 + newText2 + newText3;
       
       response.getWriter().write(newText);
       }
       }
       }
       
       }
   
3. サービス属性`service
= PortletFilter.class`を持つ`@Component`アノテーションを付けることにより、クラスを`PortletFilter`サービスコンポーネントにします。ポートレットのキーと同じjavax.portlet.nameプロパティを割り当てて、コンテンツをオーバーライドするポートレットを対象にします。`@Component`アノテーションの例を次に示します。

       @Component(
       immediate = true,
       property = {
       "javax.portlet.name=" + PortletKeys.BLOGS
       },
       service = PortletFilter.class
       )
   
4. `doFilterMethod`をオーバーライドして、要求または応答を操作して必要なコンテンツを生成します。この例では、ポートレットのコンテンツに`Added
by Blogs Render Filter!`を示す段落を追加します。

       @Override
       public void doFilter(RenderRequest request, RenderResponse response, FilterChain chain)
       throws IOException, PortletException {
       
       RenderResponseWrapper renderResponseWrapper = new BufferedRenderResponseWrapper(response);
       
       chain.doFilter(request, renderResponseWrapper);
       
       String text = renderResponseWrapper.toString();
       
       if (text != null) {
       String interestingText = "<input  class=\"field form-control\"";
       
       int index = text.lastIndexOf(interestingText);
       
       if (index >= 0) {
       String newText1 = text.substring(0, index);
       String newText2 = "\n<p>Added by Blogs Render Filter!</p>\n";
       String newText3 = text.substring(index);
       
       String newText = newText1 + newText2 + newText3;
       
       response.getWriter().write(newText);
       }
       }
       }
   
   この例では、`BufferedRenderResponseWrapper`と呼ばれる`RenderResponseWrapper`拡張クラスを使用しています。`BufferedRenderResponseWrapper`は、`toString`メソッドが現在の応答テキストを返すヘルパークラスであり、その`getWriter` メソッドを使用して、クライアントに返される前に応答にデータを書き込むことができます。

       import java.io.CharArrayWriter;
       import java.io.IOException;
       import java.io.OutputStream;
       import java.io.PrintWriter;
       
       import javax.portlet.RenderResponse;
       import javax.portlet.filter.RenderResponseWrapper;
       
       public class BufferedRenderResponseWrapper extends RenderResponseWrapper {
       
       public BufferedRenderResponseWrapper(RenderResponse response) {
       super(response);
       
       charWriter = new CharArrayWriter();
       }
       
       public OutputStream getOutputStream() throws IOException {
       if (getWriterCalled) {
       throw new IllegalStateException("getWriter already called");
       }
       
       getOutputStreamCalled = true;
       
       return super.getPortletOutputStream();
       }
       
       public PrintWriter getWriter() throws IOException {
       if (writer != null) {
       return writer;
       }
       
       if (getOutputStreamCalled) {
       throw new IllegalStateException("getOutputStream already called");
       }
       
       getWriterCalled = true;
       
       writer = new PrintWriter(charWriter);
       
       return writer;
       }
       
       public String toString() {
       String s = null;
       
       if (writer != null) {
       s = charWriter.toString();
       }
       
       return s;
       }
       
       protected CharArrayWriter charWriter;
       protected PrintWriter writer;
       protected boolean getOutputStreamCalled;
       protected boolean getWriterCalled;
       
       }
   
[モジュールをデプロイする](/docs/7-1/tutorials/-/knowledge_base/t/starting-module-development#building-and-deploying-a-module)と、ポートレットのJSPにカスタムコンテンツが表示されます。

ポートレットフィルターは、ポートレットの応答コンテンツで直接動作します。動的インクルードとは異なり、ポートレットフィルターを使用すると、JSPのすべてのコンテンツを操作できます
