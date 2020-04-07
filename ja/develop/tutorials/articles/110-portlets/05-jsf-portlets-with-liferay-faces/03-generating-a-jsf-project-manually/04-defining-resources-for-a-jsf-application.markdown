---
header-id: defining-resources-for-a-jsf-application
---

# JSFアプリケーションのリソースの定義

[TOC levels=1-4]

Hello Userポートレットの構造を振り返ると、2つの `リソース` フォルダーが定義されていることがわかります。 1つのポートレットにこれらのフォルダーが2つあるのはなぜですか？ これらの2つのフォルダーには、使用方法と配置する場所に明確な違いがあります。

アプリケーションの `src / main` フォルダー内の `リソース` フォルダーは、クラスパス上にある必要があるリソース用です。 このフォルダー内のファイルは通常、プロパティファイルです。 このポートレットでは、このフォルダーに存在する2つのプロパティファイルを作成します。

1.  `src / main / resources` フォルダーに `i18n.properties` ファイルを作成します。 このファイルに次のプロパティを追加します。
   
        enter-your-name=Enter your name:

    これは、JSFポートレットがビューXHTMLに表示する言語キーです。 `i18n.properties` ファイルのメッセージには、Liferay Faces Utilクラスによって提供される暗黙的な `i18n` オブジェクトを使用して、 [Expression Language](http://docs.oracle.com/javaee/6/tutorial/doc/bnahq.html) を介してアクセスできます。 `i18n` オブジェクトは、ポートレットの `portlet.xml` ファイルで定義されたリソースバンドルと、@product@の `Language.properties` ファイルの両方からメッセージにアクセスできます。

2.  `src / main / resources` フォルダーに `log4j.properties` ファイルを作成します。 このファイルは、JSFアプリケーションで定義されたLog4jロギングユーティリティのプロパティを設定します（つまり、 `faces-config.xml`）。 以下のプロパティをJSFアプリケーションの `log4j.properties` ファイルに挿入します。
   
        log4j.rootLogger=INFO, CONSOLE
       
        log4j.appender.CONSOLE=org.apache.log4j.ConsoleAppender
        log4j.appender.CONSOLE.layout=org.apache.log4j.PatternLayout
        log4j.appender.CONSOLE.layout.ConversionPattern=%d{ABSOLUTE} %-5p [%c{1}:%L] %m%n
       
        log4j.logger.com.liferay.faces.util.lifecycle.DebugPhaseListener=DEBUG

JSFアプリケーションの2番目の `リソース` フォルダーは、 `src / main / webapp / WEB-INF` フォルダーにあります。 このフォルダーには、ブラウザーから直接アクセスしてはならないCSS / JS / XHTMLリソースが含まれています。 Hello User JSFアプリケーションの場合、 `main.css` ファイルを内部に含む `css` フォルダーを作成します。 `main.css` ファイルに、次のスタイルを追加します。

    .com.liferay.hello.user.jsf.portlet {
        font-weight: bold;
    }

このファイルは、JSFポートレットに太字のフォントを提供します。

リソースが定義されたので、次にHello Userアプリケーションの動作とUIの開発を開始します。
