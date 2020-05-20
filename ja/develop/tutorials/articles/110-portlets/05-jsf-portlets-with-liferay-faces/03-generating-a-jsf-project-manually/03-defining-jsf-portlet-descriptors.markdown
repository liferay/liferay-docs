---
header-id: defining-jsf-portlet-descriptors
---

# JSFポートレット記述子の定義

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

JSFポートレットはWARスタイルのフォルダー構造に従う必要があるため、WARスタイルのポートレット記述子も必要です。

1.  `webapp / WEB-INF` フォルダーに `portlet.xml` ファイルを作成します。 すべてのポートレットWARにはこのファイルが必要です。 このファイルでは、次のポートレットクラスを必ず宣言してください。
   
        <portlet>
            ...
        <portlet-class>javax.portlet.faces.GenericFacesPortlet</portlet-class>
            ...
        </portlet>

    `javax.portlet.faces.GenericFacesPortlet` クラスは、JSFポートレットへの呼び出しを処理し、Liferay Faces Bridgeに依存しているため、ポートレットを簡単に実装できるため、開発が容易です。

2.  `portlet.xml`デフォルトのビューファイルを `init-param` として定義します。 これにより、@product@にデプロイされたときにポートレットが表示されます。
   
        <init-param>
<name>javax.portlet.faces.defaultViewId.view</name> <value>/WEB-INF/views/view.xhtml</value> </init-param>

    このビューは後で作成します。

    `portlet.xml` ファイルには、ポートレット情報やセキュリティ設定など、他の重要な詳細も含まれています。 Hello User JSFアプリケーションの例については、 `portlet.xml` ファイルを見てください。
   
        <?xml version="1.0"?>
       
        <portlet-app xmlns="http://java.sun.com/xml/ns/portlet/portlet-app_2_0.xsd" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://java.sun.com/xml/ns/portlet/portlet-app_2_0.xsd http://java.sun.com/xml/ns/portlet/portlet-app_2_0.xsd" version="2.0">
            <portlet>
                <portlet-name>hello-user-jsf-portlet</portlet-name>
                <display-name>Hello User JSF Portlet</display-name>
                <portlet-class>javax.portlet.faces.GenericFacesPortlet</portlet-class>
                <init-param>
                    <name>javax.portlet.faces.defaultViewId.view</name>
                    <value>/WEB-INF/views/view.xhtml</value>
                </init-param>
                <expiration-cache>0</expiration-cache>
                <supports>
                    <mime-type>text/html</mime-type>
                </supports>
                <portlet-info>
                    <title>Hello User JSF Portlet</title>
                    <short-title>Hello User</short-title>
                    <keywords>com.liferay.hello.user.jsf.portlet</keywords>
                </portlet-info>
                <security-role-ref>
                    <role-name>administrator</role-name>
                </security-role-ref>
                <security-role-ref>
                    <role-name>guest</role-name>
                </security-role-ref>
                <security-role-ref>
                    <role-name>power-user</role-name>
                </security-role-ref>
                <security-role-ref>
                    <role-name>user</role-name>
                </security-role-ref>
            </portlet>
        </portlet-app>

    上記の構成により、ポートレットのさまざまな名前、MIMEタイプ、有効期限キャッシュ、およびセキュリティロールが設定されます。


    <!-- Should probably discuss security settings in more detail elsewhere,
 similar to /develop/tutorials/-/knowledge_base/6-2/using-portal-roles-in-a-portlet.
 It would be getting a bit off track talking about it here, though. -Cody -->

3.  JSFアプリケーションの `webapp / WEB-INF` フォルダーに `web.xml` ファイルを作成します。 The `web.xml` file serves as a deployment descriptor that provides necessary configurations for your JSF portlet to deploy and function in @product@. 以下のXMLコードをHello User JSFアプリケーションにコピーします。
   
        <?xml version="1.0" encoding="UTF-8"?>
       
        <web-app xmlns="http://java.sun.com/xml/ns/javaee" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd" version="3.0">
       
            <context-param>
                <param-name>javax.faces.PROJECT_STAGE</param-name>
                <param-value>${project.stage}</param-value>
            </context-param>
            <context-param>
                <param-name>javax.faces.WEBAPP_RESOURCES_DIRECTORY</param-name>
                <param-value>/WEB-INF/resources</param-value>
            </context-param>
            <servlet>
                <servlet-name>Faces Servlet</servlet-name>
<servlet-class>javax.faces.webapp.FacesServlet</servlet-class> <load-on-startup>1</load-on-startup> </servlet> <security-constraint> <display-name>Facelet XHTMLへの直接アクセスを防止する</display-name> <web-resource-collection> <web-resource-name>Facelet XHTML</web-resource-name> <url-pattern>* .xhtml</url-pattern> </web-resource-collection> <auth-constraint/> </security-constraint> </web-app>

    最初に、 `javax.faces.PROJECT_STAGE` パラメーターを `${project.stage}` 変数に設定します。この変数は、ビルドファイル（ `pom.xml`）で `Development`として定義されています。 `Development`設定すると、JSF実装は実行時に次の手順を実行します。

    1.  より詳細なメッセージを記録します。
    2.  ビューマークアップでヒントや警告を表示します。
    3.  デフォルトの `ExceptionHandler` に、開発者にとって使いやすいエラーページを表示させます。

    `javax.faces.WEBAPP_RESOURCES_DIRECTORY` パラメーターは、 `WEB-INF` フォルダー内のリソースフォルダーを設定します。 この設定により、そのフォルダー内のリソース（CSS、JavaScript、XHTMLなど）が非JSF呼び出しから保護されます。 後でアプリ用のリソース</a> を

作成します。</p> 
       
       Faces Servlet構成は、JSFを初期化するために必要であり、@product@にデプロイされたすべてのJSFポートレットで定義する必要があります。
       
       最後に、Facelet XHTMLにセキュリティ制限が設定され、JSFアプリケーションのXHTMLファイルへの直接アクセスが防止されます。</li> </ol></li> 
       
       4  JSFアプリケーションの `webapp / WEB-INF` フォルダーに `faces-config.xml` ファイルを作成します。 `faces-config.xml` 記述子は、JSFポートレットのアプリケーション構成ファイルであり、オブジェクトとナビゲーションルールの登録と構成に使用されます。 Hello Userポートレットの `faces-config.xml` ファイルの内容は次のとおりです。
  
      <?xml version="1.0"?>
      
      <faces-config version="2.2"
          xmlns="http://xmlns.jcp.org/xml/ns/javaee"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-facesconfig_2_2.xsd"
      >
      
  
  <lifecycle>
                <phase-listener>com.liferay.faces.util.lifecycle.DebugPhaseListener</phase-listener>
            </lifecycle>
    </faces-config>
  
  多くの自動生成された `faces-config.xml` ファイルには、次の構成があります。
  
      <lifecycle>
          <phase-listener>com.liferay.faces.util.lifecycle.DebugPhaseListener</phase-listener>
      </lifecycle>
      
  
  これにより、JSFライフサイクルの前後のフェーズをデバッグモードでコンソールに記録するようにJSFポートレットが構成されます。 実稼働環境にデプロイする前に、この宣言を削除してください。</ol> 

すばらしいです\！ これで、JSFポートレットの一般的な記述子ファイルを指定および定義する方法がわかりました。 JSFポートレットはLiferay記述子も使用します。これについては、 [Liferay記述子](/docs/7-1/tutorials/-/knowledge_base/t/configuring-a-spring-mvc-portlet#liferay-descriptors) サブセクションで詳細を確認できます。

ポートレット記述子が定義されたので、次にJSFアプリケーションのリソースで作業を開始します。
