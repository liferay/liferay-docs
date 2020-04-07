---
header-id: servlets-in-a-module
---

# モジュール内のサーブレット

[TOC levels=1-4]

サーブレットまたは [JAX-RS](/docs/7-1/tutorials/-/knowledge_base/t/jax-rs) を使用して、軽量のWeb統合またはWebエンドポイントをブラウザークライアントに提供できます。 RESTエンドポイントまたはポートレットではなく、サーブレットを使用すると、アプリケーションのUIエクスペリエンス全体を制御できます。 @製品@支持体はベースのアプリケーションをサーブレットおよび埋め込み [HTTPホワイトボード](https://osgi.org/specification/osgi.cmpn/7.0.0/service.http.whiteboard.html) サーブレットのために。

ここでは、 [サーブレットサンプル](#servlet-sample) と [を調べて、独自のサーブレットベースのアプリケーション](#creating-a-servlet)を作成します。

## サーブレットのサンプル

[サーブレットサンプル](/docs/7-1/reference/-/knowledge_base/r/servlet) は、 [HTTPホワイトボード](https://osgi.org/specification/osgi.cmpn/7.0.0/service.http.whiteboard.html) を使用して、パターン `http：// localhost：8080 / o / blade / servlet / *`一致するURLの要求に応答します。

![図1：ユーザーが <code>http：// localhost：8080 / o / blade / servlet</code>にアクセスすると、サーブレットサンプルはメッセージ <code>Hello World</code>表示します。](../../images/servlet-sample.png)

サンプルサーブレットクラスは次のとおりです。

    package com.liferay.blade.samples.servlet;
    
    import java.io.IOException;
    
    import javax.servlet.Servlet;
    import javax.servlet.ServletException;
    import javax.servlet.http.HttpServlet;
    import javax.servlet.http.HttpServletRequest;
    import javax.servlet.http.HttpServletResponse;
    
    import org.osgi.service.component.annotations.Component;
    import org.osgi.service.component.annotations.Reference;
    import org.osgi.service.log.LogService;
    
    /**
     * @author Liferay
     */
    @Component(
        immediate = true,
        property = {
            "osgi.http.whiteboard.context.path=/",
            "osgi.http.whiteboard.servlet.pattern=/blade/servlet/*"
        },
        service = Servlet.class
    )
    public class BladeServlet extends HttpServlet {
    
        @Override
        public void init() throws ServletException {
            _log.log(LogService.LOG_INFO, "BladeServlet init");
    
            super.init();
        }
    
        @Override
        protected void doGet(
                HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
    
            _log.log(LogService.LOG_INFO, "doGet");
    
            _writeSampleHTML(response);
        }
    
        /**
         * Dummy contents
         * 
         * @return dummy contents string
         */
        private String _generateSampleHTML() {
            StringBuffer sb = new StringBuffer();
    
            sb.append("<html>");
            sb.append("<head><title>Sample HTML</title></head>");
            sb.append("<body>");
            sb.append("<h2>Hello World</h2>");
            sb.append("</body>");
            sb.append("</html>");
    
            return new String(sb);
        }
    
        /**
         * Write sample HTML
         * 
         * @param resp
         */
        private void _writeSampleHTML(HttpServletResponse resp) {
            resp.setCharacterEncoding("UTF-8");
            resp.setContentType("text/html; charset=UTF-8");
            resp.setStatus(HttpServletResponse.SC_OK);
    
            try {
                resp.getWriter().write(_generateSampleHTML());
            }
            catch (Exception e) {
                _log.log(LogService.LOG_WARNING, e.getMessage(), e);
    
                resp.setStatus(HttpServletResponse.SC_PRECONDITION_FAILED);
            }
        }
    
        private static final long serialVersionUID = 1L;
    
        @Reference
        private LogService _log;
    
    }

サンプルサーブレットクラスは、 `@Component` アノテーションを使用して、タイプ `サーブレット`OSGiサービスを宣言します。 OSGi HTTPホワイトボードを使用して、 `http：// localhost：8080 / o / blade / servlet / *`一致するURLのリクエストに応答します。 コンポーネントの `osgi.http.whiteboard.context.path` および `osgi.http.whiteboard.servlet.pattern` プロパティはサーブレットマッピングを構成するため、 `WEB-INF / web.xml` 記述子で1つを指定する必要はありません。 。

ポータルWebアプリケーションの `WEB-INF / web.xml` は、Liferayのモジュールフレームワークサーブレットマッピングを定義します。

``` 
  <servlet-mapping>
      <servlet-name>Module Framework Servlet</servlet-name>
      <url-pattern>/o/*</url-pattern>
  </servlet-mapping>
```

サーブレットマッピングはURLパターン `/ o / *`から始まります。 `@Component` プロパティ `"osgi.http.whiteboard.servlet.pattern = / blade / servlet / *"`と組み合わせると、サーブレットサンプルはURLパターン `/ o / blade / servlet / *`一致します。

独自のサーブレットを開発するには、 [Servletサンプルモジュールプロジェクト](/docs/7-1/reference/-/knowledge_base/r/servlet#where-is-this-sample) すべて（または一部）をコピーして変更するか、独自のモジュールにサーブレットを作成します。

## サーブレットを作成する

独自のサーブレットを作成する方法は次のとおりです。

1.  [モジュールプロジェクト](/docs/7-1/tutorials/-/knowledge_base/t/starting-module-development)作成します。

2.  必要な依存関係を追加します。 ここにGradleがあります：
   
        compileOnly group: "javax.servlet", name: "javax.servlet-api", version: "3.0.1"
        compileOnly group: "org.osgi", name: "org.osgi.service.component.annotations", version: "1.4.0"
        compileOnly group: "org.osgi", name: "org.osgi.service.log", version: "1.4.0"

3.  `javax.servlet.http.HttpServlet`を拡張するサーブレットクラスを作成します。

4.  次の `@Component` アノテーションを追加します。

    ``` 
    @Component(
        property = {
            "osgi.http.whiteboard.context.path=/",
            "osgi.http.whiteboard.servlet.pattern=/blade/servlet/*"
        },
        service = Servlet.class
    ) 
    ```

    `service = Servlet.class`：コンポーネントをタイプ `サーブレット`OSGiサービスにします。

5.  次の `@Component` `プロパティ` 値を設定して、コンテキストパスとサーブレットURLパターンを指定します。

    `"osgi.http.whiteboard.context.path = /"`：サーブレットのコンテキストを設定します。 値をサーブレットのコンテキストパスに置き換えます。

    `"osgi.http.whiteboard.servlet.pattern = / blade / servlet / *"`：サーブレットのマッピングパターンを設定します。 値をサーブレットのパターンに置き換えます。

6.  `HttpServlet` メソッドをオーバーライドして、サーブレットの動作を実装します。

7.  モジュールをデプロイします。

サーブレットが稼働しています。 サーブレットを使用してカスタムユーザーエクスペリエンスを提供する方法は順調です。

## 関連トピック

[サーブレットのサンプル](/docs/7-1/reference/-/knowledge_base/r/servlet)

[サーブレットフィルター](/docs/7-1/tutorials/-/knowledge_base/t/servlet-filters)

[JAX-RS](/docs/7-1/tutorials/-/knowledge_base/t/jax-rs)

[ポートレット](/docs/7-1/tutorials/-/knowledge_base/t/portlets)
