---
header-id: creating-an-mvc-portlet
---

# MVCポートレットの作成

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

MVCポートレットアプリケーションは、Liferayのランタイム環境にコンポーネントとして登録されている少なくとも1つのポートレットクラスを含むWebモジュールです。 Webモジュールは、標準のOSGiメタデータを使用して自身を記述し、任意のビルド環境を使用できます。

Liferay MVCポートレットコンポーネントモジュールを実装するための一般的な手順は次のとおりです。

1.  Webモジュールの構成

2.  OSGiメタデータの指定

3.  ポートレットコンポーネントの作成

ポートレット用のWebモジュールを作成することから始めます。

## ステップ1：Webモジュールの構成

Webモジュールのフォルダー構造は通常、次のパターンに従います。

  - docs.liferaymvc.web /
      - src/main/java/
          - com/liferay/docs/liferaymvc/web/portlet/LiferayMVCPortlet.java
      - src/main/resources/
          - content/
              - Language.properties
          - META/-INF/resources/
              - init.jsp
              - view.jsp
      - build.gradle
      - bnd.bnd

[Liferay @ide@](/docs/7-1/tutorials/-/knowledge_base/t/liferay-ide) および [Blade CLI](/docs/7-1/tutorials/-/knowledge_base/t/blade-cli)MavenとGradleの両方で使用できる [MVCポートレットテンプレート](/docs/7-1/reference/-/knowledge_base/r/using-the-mvc-portlet-template)により、このようなWebモジュールを簡単に作成できます。 もちろん、Gradleやbndを使用してプロジェクトをビルドすることに縛られているわけではありません。 ただし、モジュールをJARとしてビルドし、適切なOSGiヘッダーを使用してモジュールを定義する必要があります。

## ステップ2：OSGiメタデータの指定

OSGiメタデータは、モジュールをOSGiランタイム環境に記述します。 少なくとも、バンドルのシンボル名とバンドルバージョンを指定する必要があります。 人間が読み取れるバンドル名をお勧めします。

    Bundle-Name: Example Liferay MVC Web
    Bundle-SymbolicName: com.liferay.docs.liferaymvc.web
    Bundle-Version: 1.0.0

`Bundle-SymbolicName`指定しない場合、プロジェクトのフォルダーパスから生成されます。これは多くの場合に適しています。 Liferayの慣例では、ルートパッケージ名をバンドルのシンボリック名として指定します。

## ステップ3：ポートレットコンポーネントの作成

OSGi宣言サービスコンポーネントモデルを使用すると、OSGiランタイムにサービス実装を簡単に公開できます。 たとえば、ポートレットクラスを `javax.portlet.Portlet` として公開するには、次のような `@Component` アノテーションが必要です。

    @Component(
        immediate = true,
        service = Portlet.class
    )
    public class LiferayMVCPortlet extends MVCPortlet {
    }

`immediate = true` 属性は、依存関係が解決するとすぐにポートレットを公開するようランタイムに指示します。 属性 `service = Portlet.class` は、ポートレットが `javax.portlet.Portlet` サービスを提供することを指定します。

Liferayの [`MVCPortlet` クラス](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/bridges/mvc/MVCPortlet.html) 自体は `javax.portlet.Portlet`拡張であるため、適切な実装を提供しました。 それ自体は良いのですが、コンポーネントを設定する必要があります：

    @Component(
    immediate = true,
    property = {
    "com.liferay.portlet.display-category=category.sample",
    "com.liferay.portlet.instanceable=true",
    "javax.portlet.display-name=Liferay MVC Portlet",
    "javax.portlet.init-param.template-path=/",
    "javax.portlet.init-param.view-template=/view.jsp",
    "javax.portlet.name=MyMVCPortlet",
    "javax.portlet.resource-bundle=content.Language",
    "javax.portlet.security-role-ref=power-user,user"
    },
    service = Portlet.class
    )
    public class LiferayMVCPortlet extends MVCPortlet {
    }

Liferayの [MVCポートレットテンプレート](/docs/7-1/reference/-/knowledge_base/r/using-the-mvc-portlet-template) は、生成するポートレットクラスにこれらのコンポーネントプロパティが含まれています。

Liferay Portal 6.2用のLiferay MVCポートレットを開発している場合、一部のプロパティはおなじみに見えるかもしれません。 XMLは、あなたが指定するために使用される属性として、彼らは同じだからだ `のLiferay-portlet.xmlに`、 `のLiferay-display.xml`、および `のportlet.xml`。 [のOSGiプロパティへのポートレット記述子のマッピング](/docs/7-1/reference/-/knowledge_base/r/portlet-descriptor-to-osgi-service-property-map) あなたが既に知っている記述子のためのOSGiのプロパティを見つけることができます。

JSR-168およびJSR-286ポートレット仕様との互換性を保つために、これらのDTDはLiferay固有のポートレット属性を定義します。

  - [liferay-portlet-app \ _7 \ _1 \ _0.dtd](@platform-ref@/7.1-latest/definitions/liferay-portlet-app_7_1_0.dtd.html)。
  - [liferay-display \ _7 \ _1 \ _0.dtd](@platform-ref@/7.1-latest/definitions/liferay-display_7_1_0.dtd.html)

たとえば、 `liferay-portlet-app_7_1_0.dtd`の `<instanceable>` 要素を考えます。 コンポーネントでそのプロパティを指定するには、 `@Component` プロパティリストで次の構文を使用します。

    "com.liferay.portlet.instanceable=true",

`javax.portlet ....` 名前空間化されたプロパティは、 [portlet.xml記述子](http://java.sun.com/xml/ns/portlet/portlet-app_2_0.xsd)要素です。

また、 `com.liferay.portlet.display-category` プロパティを使用して、ネストされたカテゴリを作成できることに注意してください。 `//` を使用して、カテゴリルートと、ポートレットのカテゴリパスを構成するすべてのカテゴリおよびサブカテゴリを分離します。 以下に例を示します。

    com.liferay.portlet.display-category=root//category.category1//category.category2

Liferayの `MVCPortlet` を拡張し、OSGiランタイムのコンポーネントとして登録する方法がわかりました。 コントローラーコードを作成します。
