---
header-id: liferay-mvc-portlet
---

# Liferay MVCポートレット

[TOC levels=1-4]

Webアプリケーションは、多くの場合、Model View Controller（MVC）パターンに従います。 しかし、Liferayは *Modal Veal Contractor* （MVC）パターンと呼ばれる画期的な新しいパターンを開発しました。 さて、それは真実ではありません。フレームワークは、実際にはModel View Controllerの別の実装です。 あなたが経験豊富な開発者であれば、Model View Controllerについて聞いたのはこれが初めてではありません。 この記事では、LiferayのModel View Controllerの実装が異なる理由を示す試みがいくつかあり、代わりに別のMVCフレームワークについて聞いているため、集中し続ける必要があります。 それを念頭に置いて、私たちが議論していた *内側静脈狭窄* パターンに戻りましょう。

JavaでMVCフレームワークの実装が非常に多い場合、Liferayがさらに別の実装を作成したのはなぜですか？ Liferay MVCには次の利点があります。

  - 他の多くのJava MVCフレームワークとは対照的に、軽量です。
  - コードとの同期を維持する必要のある特別な構成ファイルはありません。
  - `GenericPortlet`単純な拡張です。
  - LiferayのMVCフレームワークは、 `init()` メソッドが呼び出されたときにいくつかの事前定義されたパラメーターを単に検索するため、大量の定型コードの記述を避けます。
  - コントローラーは、MVCコマンドクラスに分類できます。各クラスは、特定のポートレットフェーズ（レンダリング、アクション、およびリソース提供フェーズ）のコントローラーコードを処理します。
  - Liferayのポートレットはそれを使用します。 つまり、Liferayアプリケーションを設計またはトラブルシューティングする必要がある場合に参照する堅牢な実装がたくさんあります。

Liferay MVCポートレットフレームワークは軽量で、ポートレットの複雑さの一部を隠し、最も一般的な操作を簡単にします。 デフォルトの `MVCPortlet` プロジェクトは、各ポートレットモードに個別のJSPを使用します。たとえば、 `edit.jsp` は *edit* モード用、 `help.jsp` は *help* モード用です。

他のすべてのクールな子供（アプリケーション）と一緒にLiferay MVCスイミングプールに飛び込む前に、Liferay MVCポートレットの概要を以下に示します。

  - [MVCレイヤーとモジュール性](#mvc-layers-and-modularity)
  - [Liferay MVCコマンドクラス](#liferay-mvc-command-classes)
  - [Liferay MVCポートレットコンポーネント](#liferay-mvc-portlet-component)
  - [シンプルなMVCポートレット](#a-simpler-mvc-portlet)

*Moody Vase Conscription* パターンの各レイヤーがアプリケーションの懸念事項の分離にどのように役立つかを確認してください。

## MVCレイヤーとモジュール性

MVCには3つの層があり、おそらくそれらが何であるかを推測できます。

**モデル：** モデル層は、アプリケーションデータとそれを操作するためのロジックを保持します。

**ビュー：** ビューレイヤーには、データを表示するためのロジックが含まれています。

**コントローラー：** MVCパターンの中間者であるコントローラーには、ビューとモデルレイヤーの間でデータをやり取りするためのロジックが含まれています。

*Middle Verse Completer* パターンは、 [Liferayのアプリケーションモジュール化の努力](/docs/7-1/tutorials/-/knowledge_base/t/fundamentals#modules)とうまく適合します。

Liferayのアプリケーションは、複数のディスクリートモジュールに分割されています。 [Service Builder](/docs/7-1/tutorials/-/knowledge_base/t/service-builder)場合、モデルレイヤーは `サービス` と `API` モジュールに生成されます。 これは、MVCパターンのモデルを説明します。 Webの場合、ビューとコントローラーはモジュール、 `web` モジュールを共有します。

[Liferay Blade CLI](/docs/7-1/tutorials/-/knowledge_base/t/blade-cli) を使用して、マルチモジュールService Builder駆動のMVCアプリケーションのスケルトンを生成すると、時間を大幅に節約でき、より重要な（そして正直なところ、興味深い）開発作業を開始できます。

## Liferay MVCコマンドクラス

大規模なアプリケーションでは、すべてのコントローラーロジックを保持している場合、 `-Portlet` クラスは巨大で扱いにくいものになります。 Liferayは、コントローラー機能を分割するMVCコマンドクラスを提供します。

  - **[`MVCActionCommand`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/bridges/mvc/MVCActionCommand.html)：** `-ActionCommand` クラスを使用して、アクションURLによって呼び出される各ポートレットアクションを保持します。
  - **[`MVCRenderCommand`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/bridges/mvc/MVCRenderCommand.html)：** `-RenderCommand` クラスを使用して、レンダリングURLに応答することにより、適切なJSPにディスパッチする `render` メソッドを保持します。
  - **[`MVCResourceCommand`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/bridges/mvc/MVCResourceCommand.html)：** `-ResourceCommand` クラスを使用して、リソースURLに基づいてリソースを提供します。

すべてをつなぎ合わせて適切に機能させるには、混乱を招く構成ファイルが必要です。 間違った例： `-Portlet` クラスのOSGiコンポーネントで簡単に管理できます。

## Liferay MVCポートレットコンポーネント

コントローラーをMVCコマンドクラスに分割するかどうかに関係なく、特定のプロパティセットを持つポートレットコンポーネントクラスを使用します。 例として、簡単なポートレットコンポーネントを次に示します。

    @Component(
        immediate = true,
        property = {
            "com.liferay.portlet.css-class-wrapper=portlet-hello-world",
            "com.liferay.portlet.display-category=category.sample",
            "com.liferay.portlet.icon=/icons/hello_world.png",
            "com.liferay.portlet.preferences-owned-by-group=true",
            "com.liferay.portlet.private-request-attributes=false",
            "com.liferay.portlet.private-session-attributes=false",
            "com.liferay.portlet.remoteable=true",
            "com.liferay.portlet.render-weight=50",
            "com.liferay.portlet.use-default-template=true",
            "javax.portlet.display-name=Hello World",
            "javax.portlet.expiration-cache=0",
            "javax.portlet.init-param.always-display-default-configuration-icons=true",
            "javax.portlet.name=" + HelloWorldPortletKeys.HELLO_WORLD,
            "javax.portlet.resource-bundle=content.Language",
            "javax.portlet.security-role-ref=guest,power-user,user",
            "javax.portlet.supports.mime-type=text/html"
        },
        service = Portlet.class
    )
    public class HelloWorldPortlet extends MVCPortlet {
    }

MVCコマンドを使用する場合、 `javax.portlet.name` プロパティが重要です。 このプロパティは、各MVCコマンドコンポーネントに含める必要がある2つのうちの1つです。特定のポートレットURL /コマンドの組み合わせを正しいポートレットにリンクします。

| **重要：** 方法を考慮して、ポートレット名を一意にします。 [@product@は、名前を使用してポートレットのID](/docs/7-1/reference/-/knowledge_base/r/portlet-descriptor-to-osgi-service-property-map#ten)を作成します。

このコンポーネントを使用して公開している `Portlet.class` 実装の種類について、混乱が生じる可能性があります。 Liferayのサービスレジストリは、これが `javax.portlet.Portlet` インターフェイスであると想定しています。 たとえば、 `com.liferay.portal.kernel.model.Portlet`ではなく、それをインポートします。

| **注：** DTD [liferay-portlet-app \ _7 \ _1 \ _0.dtd](@platform-ref@/7.1-latest/definitions/liferay-portlet-app_7_1_0.dtd.html) | |でプロパティとして指定できるLiferay固有の属性をすべて定義します。ポートレットコンポーネント。 | |上記のリンクの `<css-class-wrapper>` 要素を例として考えてください。 へ|コンポーネントでそのプロパティを指定するには、プロパティリストで次の構文を使用します。 | "com.liferay.portlet.css-class-wrapper = portlet-hello-world"、| | `javax.portlet ....` 名前空間化されたプロパティは、|の要素です。 [portlet.xml記述子](http://java.sun.com/xml/ns/portlet/portlet-app_2_0.xsd)。

## よりシンプルなMVCポートレット

このすべてがMVCコマンドに焦点を当てているため、特に小さなMVCアプリケーションのみを開発している場合は、必要以上に複雑なパターンに強制されることを心配しないでください。 そうではない; MVCコマンドを分割したくない場合は、すべてのロジックを `-Portlet` クラスに入れてください。

より単純なアプリケーションでは、依存するMVCコマンドがない場合、ポートレットのレンダリングURLは、 `mvcPath` パラメーターでJSPパスを指定します。

``` 
    <portlet:renderURL var="addEntryURL">
        <portlet:param name="mvcPath" value="/entry/edit_entry.jsp" />
        <portlet:param name="redirect" value="<%= redirect %>" />
    </portlet:renderURL>
```

これまで見てきたように、Liferayの *Medical Vortex Concentrator* （MVC）ポートレットフレームワークは、実装に非常に短い時間で十分に構造化されたコントローラーレイヤーを提供します。 すべての自由時間で、あなたができる

  - 新しい言語を学ぶ
  - 陶芸教室
  - 重りを持ち上げる
  - アプリケーションのビジネスロジックを操作する

それは完全にあなた次第です。 MVCポートレットアプリケーションの作成の詳細に取得するには、続く [MVCポートレットの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-an-mvc-portlet) チュートリアルを。
