---
header-id: resolving-classnotfoundexception-and-noclassdeffounderror-in-osgi-bundles
---

# OSGiバンドルのClassNotFoundExceptionおよびNoClassDefFoundErrorの解決

[TOC levels=1-4]

`ClassNotFoundException`と`NoClassDefFoundError`は、よく知られた一般的な例外です。

- `ClassNotFoundException`は、クラスパスにないクラスを検索するか、無効な名前を使用してランタイムクラスパスにないクラスを検索するとスローされます。
- `NoClassDefFoundError`は、コンパイルされたクラスがランタイムクラスパス上にない別のクラスを参照するときに発生します。

ただし、OSGi環境では、`ClassNotFoundException`または`NoClassDefFoundError`が発生する可能性がある追加のケースがあります。

1. 欠落しているクラスが、OSGiモジュールであるモジュール依存関係に属している。
2. 欠落しているクラスが、OSGiモジュールでは*ない*モジュール依存関係に属している。
3. 欠落しているクラスが、@product@ webappスコープまたはアプリケーションサーバースコープのいずれかのグローバルライブラリに属している。
4. 欠落しているクラスが、Javaランタイムパッケージに属している。

このチュートリアルでは、各ケースの処理方法について説明します。

## ケース1：欠落しているクラスがOSGIモジュールに属している

この場合、2つの原因が考えられます。

1. **モジュールがクラスのパッケージをインポートしていない**：モジュール（またはWAB）が別のモジュールのエクスポートされたクラスを使用するには、使用するモジュールがそのクラスを含むエクスポートされたパッケージをインポートする必要があります。これを行うには、使用するモジュールの`bnd.bnd`ファイルに`Import-Package`ヘッダーを追加します。使用するモジュールがパッケージをインポートせずにクラスにアクセスしようとすると、`ClassNotFoundException`または`NoClassDefFoundError`が発生します。

   パッケージ名を確認し、使用するモジュールが正しいパッケージをインポートしていることを確認してください。インポートは正しくされているが、それでも例外またはエラーが発生する場合、クラスがパッケージに存在していない可能性があります。

2. **クラスがインポートされたパッケージに存在していない**：モジュールはOSGiランタイム環境で頻繁に変更されます。開発者によって削除された別のモジュールのクラスを参照すると、`NoClassDefFoundError`または`ClassNotFoundException`が発生します。[Semantic Versioning](http://semver.org)によって、エクスポートされたパッケージからクラスを削除するとそのパッケージの新しいメジャーバージョンが構成されるというシナリオを回避できます。パッケージのメジャーバージョンをインクリメントしないと、依存モジュールが壊れます。

   たとえば、クラス`com.foo.Bar`を使用するモジュールがパッケージインポート`com.foo;version=[1.0.0, 2.0.0)`を指定するとします。このモジュールは、`1.0.0`から`2.0.0`まで（ただし、2.0.0は含まない）の`com.foo`バージョンを使用します。バージョン番号の最初の部分（`1.0.0`の`1`）は、 *メジャー*バージョンを表しています。使用するモジュールは、クラスの削除など、*重大な*互換性を破る変更を想定していません。パッケージを新しいメジャーバージョン（`2.0.0`など）にインクリメントせずに`com.foo`から`com.foo.Bar`を削除すると、他のモジュールがそのクラスを検索または参照したときに`ClassNotFoundException`または`NoClassDefFoundError`が発生します。

   クラスがパッケージに存在しない場合は、選択肢が制限されます。

   - 新しいAPIに適合させます。これを行う方法については、パッケージ/モジュールのJavadoc、リリースノート、および/または正式なドキュメントを参照してください。
著者に質問したり、フォーラムを検索したりすることもできます。

   - 以前に使用していたモジュールバージョンに戻します。デプロイされたモジュールバージョンは`[Liferay_Home]/osgi/`にあります。詳細については、[Backing up Liferay Installations](/docs/7-1/deploy/-/knowledge_base/d/backing-up-a-liferay-installation#backing-up-liferays-file-system)を参照してください。
   モジュールを適切に動作させるために適切な方法を実行してください。

これで、`ClassNotFoundException`または`NoClassDefFoundError`に関連する一般的な状況を解決する方法がわかりました。`NoClassDefFoundError`の追加情報については、OSGi Enrouteの記事[What is NoClassDefFoundError?](http://enroute.osgi.org/faq/class-not-found-exception.html)を参照してください。

## ケース2：欠落しているクラスがOSGIモジュールに属していない

この場合、次の2つのオプションがあります。

1. 依存関係をOSGiモジュールに変換して、欠落しているクラスをエクスポートできるようにします。OSGi以外の`JAR`ファイルの依存関係を、アプリケーションと一緒にデプロイできるOSGiモジュールに変換することが理想的なソリューションであるため、まずはこの方法を選択するのがベストです。

2. 依存関係`JAR`ファイルのパッケージをプライベートパッケージとしてモジュールに埋め込むことにより、モジュールに依存関係を埋め込みます。アプリケーションにOSGi以外の`JAR`ファイルを埋め込む場合は、チュートリアル[Adding Third Party Libraries to a Module](/docs/7-1/tutorials/-/knowledge_base/t/adding-third-party-libraries-to-a-module)を参照してください。

## ケース3：欠落しているクラスがグローバルライブラリに属している

この場合、OSGiシステムモジュールが欠落しているクラスのパッケージをエクスポートするように、@product@を構成できます。その後、モジュールはパッケージをインポートできます。ただし、これは気軽に行うべきでは**ありません**。Liferayがグローバルライブラリを開発者が使用できるように設計している場合、システムモジュールにはこのライブラリがすでにエクスポートされています。他に解決策がない場合にのみ続行し、意図しない結果に注意してください。パッケージをエクスポートするには2つの方法があります。

1. `portal-ext.properties`ファイルで、プロパティ[`module.framework.system.packages.extra`](@platform-ref@/7.1-latest/propertiesdoc/portal.properties.html#Module%20Framework)を使用して、エクスポートするパッケージを指定します。プロパティの現在のリストを保持します。

2. 必要なパッケージが@product@ JARからのものである場合、`[LIFERAY_HOME]/osgi/core/com.liferay.portal.bootstrap.jar`の`META-INF/system.packages.extra.bnd`ファイルにあるエクスポートされたパッケージのリストにモジュールを追加できます。このオプションは、最初のオプションが機能しない場合にのみ試してください。

必要なパッケージが@product@モジュールからのものである場合（つまり、グローバルライブラリからのものでは**ない**）、そのモジュールの`bnd.bnd`エクスポートにパッケージを追加できます。ただし、これは気軽に行うべきでは**ありません**。Liferayがパッケージを使用できるように設計している場合、パッケージはすでにエクスポートされています。

## ケース4：欠落しているクラスがJavaランタイムパッケージに属している

この場合、クラスはJavaの`rt.jar`に属していますが、パッケージはOSGiフレームワークのブート委任リストで指定されていません。`rt.jar`の`java.*`パッケージは、クラスパスで自動的に使用できる唯一のパッケージです。 クラスパスにアクセスするには、ブート委任リストで他のパッケージを指定する必要があります。

パッケージをブート委任リストに追加する方法は次のとおりです。

1.  `portal-ext.properties`ファイルで、[portal property `org.osgi.framework.bootdelegation`](@platform-ref@/7.1-latest/propertiesdoc/portal.properties.html#Module%20Framework)をオーバーライドします。
プロパティの現在のリストを保持します。

2. 欠落しているパッケージをリストに追加します。

## 関連トピック

[Backing up Liferay Installations](/docs/7-1/deploy/-/knowledge_base/d/backing-up-a-liferay-installation)

[Adding Third Party Libraries to a Module](/docs/7-1/tutorials/-/knowledge_base/t/adding-third-party-libraries-to-a-module)

[Bundle Classloading Flow](/docs/7-1/tutorials/-/knowledge_base/t/bundle-classloading-flow)
