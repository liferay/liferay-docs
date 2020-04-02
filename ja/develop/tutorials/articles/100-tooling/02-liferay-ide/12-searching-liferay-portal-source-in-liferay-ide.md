---
header-id: searching-product-source-in-liferay-ide
---

# Liferay Dev Studioでソースを検索する

[TOC levels=1-4]

Liferay Dev Studioでは、プロジェクトの開発支援のために@product@のソースコードを検索することができます。Liferayでは、開発に役立つ優れたリソース（例: 公式書類、 [docs.liferay.com](https://docs.liferay.com/)、 [サンプルプロジェクト](/docs/7-1/reference/-/knowledge_base/r/sample-projects)など）を提供していますが、Liferayのコードベース（プラットフォームおよび公式アプリ）でパターンを検索することも有用です。たとえば、Liferayの`portal-kernel` JARで提供されているクラスを拡張するカスタムアプリを作成する場合、そのクラスを調べて、@product@のコードベースの他のエリアでどのように使用されているのかを確認することができます。

これを行うには、[Liferay Workspace](/docs/7-1/tutorials/-/knowledge_base/t/liferay-workspace)で開発する必要があります。
Liferay Workspaceは、高度な検索ができるように設定された@product@のソースコードにインデックスを付ける特定の@product@バージョンを対象とすることで、この機能を提供することができます。この仕組みの詳細については、[Managing the Target Platform in Liferay Workspace](/docs/7-1/tutorials/-/knowledge_base/t/managing-the-target-platform-for-liferay-workspace)のチュートリアルを参照してください。

Liferay Workspaceは、デフォルトではポータルソースのインデックス作成を行いませんので、以下のプロパティをワークスペースの`gradle.properties`ファイルに追加して、この機能を有効にする必要があります。

```properties
target.platform.index.sources=true
```

| **注：**ポータルソースのインデックス作成は、Gradleワークスペースのバージョン| 2.0.3以降 (Target Platformプラグインのバージョン 2.0.0以降)では無効になっています。

このチュートリアルでは、高度な検索が役に立つ、3つのユースケースを紹介します。

- [クラス階層の検索](#search-class-hierarchy)
- [宣言の検索](#search-method-declarations)
- [リファレンスの検索](#search-annotation-references)

これらの例は、Liferay Dev Studioで検索できるもののほんの一部です。詳細については、[Java Search](http://help.eclipse.org/oxygen/index.jsp?topic=%2Forg.eclipse.jdt.doc.user%2Fconcepts%2Fconcept-java-search.htm&resultof=%22%6a%61%76%61%22%20)に関するEclipseのドキュメンテーションを参照してください。

## クラス階層の検索

同様のスーパークラスを拡張するクラスを調べることで、独自のアプリを開発するにあたって有用なパターンと例を見つけることができます。たとえば、開発中のアプリが[MVCPortlet](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/bridges/mvc/MVCPortlet.html)クラスを拡張するとします。`MVCPortlet`宣言を右クリックして、*[タイプ階層を開く]*を選択することにより、Dev Studioで同じクラスを拡張するクラスを検索します。
これにより、`MVCPortlet`を拡張するターゲットプラットフォームにあるすべてのクラスを確認できるウィンドウが開きます。

![図1：[タイプ階層]ウィンドウを参照し、クラスを拡張する方法の例について提供されているクラスを開きます。](../../../images/open-type-hierarchy.png)

 これで、クラスまたはインターフェイスのすべての拡張機能と実装を検索して、理想のアプリの開発を支援することができます。

## メソッド宣言の検索

場合によっては、クラスまたはインターフェースによって提供される特定のメソッド宣言を調べて、より詳細な検索をすることになることも考えられます。そういった場合に、Liferay Workspaceのターゲットプラットフォームインデックスでメソッドの検索もできます。

拡張する[MVCPortlet](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/bridges/mvc/MVCPortlet.html)クラスで、 オーバーライドする`doView`メソッドの宣言を検索するとします。これを行うには、カスタムアプリのクラスにある`doView`メソッド宣言を右クリックし、*[宣言]* → *[ワークスペース]*を選択します。

![図2：すべてのメソッド宣言が検索ウィンドウに返されます。](../../../images/inspect-declared-method.png)

レンダリングされた検索ウィンドウには、ターゲットプラットフォーム内でそのメソッドがオーバーライドされている他の出現箇所が表示されます。

## アノテーションリファレンスの検索

@product@のソースコードで使用されているアノテーションは、わかりにくい場合があります。Liferayのターゲットプラットフォームでこういったタイプのアノテーションが存在する場所を検索する機能を使用すると、独自のアプリでそれらを使用する方法を見つけることができます。

たとえば、OSGiモジュールで`@Reference`アノテーションを使用するための公式ドキュメンテーションを見つけて、カスタムアプリケーションに実装することができます。@product@のアプリで実際の例を参照して、他の場所でどのように使用されているかを確認すると便利です。これを検索するには、クラス内のアノテーションを右クリックし、*[リファレンス]* → *[ワークスペース]*を選択します。

![図3：検索ウィンドウに、一致するすべてのアノテーションが表示されます。](../../../images/inspect-references-ide.png)

レンダリングされた検索ウィンドウには、そのアノテーションが使用されたターゲットプラットフォーム内の他の出現箇所が表示されます。

 これで、Liferay Workspaceで指定された設定済みのターゲットプラットフォームを検索するツールが用意できました。
