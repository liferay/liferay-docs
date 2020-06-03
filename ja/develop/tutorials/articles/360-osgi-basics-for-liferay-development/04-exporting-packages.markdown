---
header-id: exporting-packages
---

# パッケージのエクスポート

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

OSGiモジュールのJavaパッケージは、デフォルトではプライベートです。 パッケージを公開するには、明示的にエクスポートする必要があります。 これにより、共有したいクラスのみを共有できます。 OSGiモジュールJARのマニフェストでパッケージをエクスポートすると、すべてのパッケージのクラスが他のモジュールで [import](/docs/7-1/tutorials/-/knowledge_base/t/importing-packages)使用可能になります。

パッケージをエクスポートするには、モジュールまたはプラグインの `Export-Package` OSGiヘッダーに追加します。 `com.liferay.petra.io` および `com.liferay.petra.io.unsync` をエクスポートするヘッダーは次のようになります。

    Export-Package:\
    com.liferay.petra.io,\
    com.liferay.petra.io.unsync

ヘッダーの正しい場所は、プロジェクトのタイプによって異なります。

| プロジェクトの種類          | `Export-Package` ヘッダーの場所                    |
|:------------------ |:------------------------------------------- |
| モジュール（bndを使用）      | `[project]/bnd.bnd`                         |
| モジュール（bndを使用しません）  | `[module JAR]/META-INF/MANIFEST.MF`         |
| 従来のLiferayプラグインWAR | `WEB-INF/liferay-plugin-package.properties` |

モジュールのプロジェクトが使用して作成 [ブレイドCLI](/docs/7-1/tutorials/-/knowledge_base/t/blade-cli)、 [LiferayのMavenの原型](/docs/7-1/tutorials/-/knowledge_base/t/maven)、または [のLiferay IDE @ @](/docs/7-1/tutorials/-/knowledge_base/t/liferay-ide) 使用 [BND](http://bnd.bndtools.org/)。 そのようなプロジェクトのモジュールJARをビルドすると、bndはOSGiヘッダーをプロジェクトの `bnd.bnd` ファイルからJARの `META-INF / MANIFEST.MF`伝播します。

bndを使用しないモジュールプロジェクトでは、モジュールJARの `META-INF / MANIFEST.MF``Export-Package` ヘッダーにパッケージエクスポートを手動で追加する必要があります。

従来のLiferayプラグインWARプロジェクトでは、プロジェクトの `liferay-plugin-package.properties`の `Export-Package` ヘッダーにパッケージエクスポートを追加する必要があります。 WARを `[Liferay Home] / deploy` フォルダーにコピーすると、 [WAB Generator](/docs/7-1/tutorials/-/knowledge_base/t/using-the-wab-generator) はOSGiヘッダーをWARの `liferay-plugin-package.properties` ファイルから `META-INF / MANIFEST.MF` ファイルに伝搬します生成されたWebアプリケーションバンドル（WAB）。

| **注**：bndは、モジュールのエクスポートされたパッケージ *置換可能にします*。 つまり、 OSGiフレームワークは、モジュールのエクスポートされたパッケージを互換性のあるパッケージに置き換えることができます。エクスポートされた同じ名前のパッケージである可能性のあるバージョン別のモジュールから。 bndは、モジュールに対してこれを自動的に有効にします|モジュールがエクスポートするすべてのパッケージをインポートするようにします。 このようにして、モジュールは次のことができます。単独で動作しますが、|を提供するモジュールと連携して動作することもできます。パッケージの異なる（互換性のある）バージョン、さらには同じバージョン。 A |別のモジュールからのパッケージは、より良い「配線」の機会を提供するかもしれません。他のモジュール。 | [Peter Kriensのブログ投稿](http://blog.osgi.org/2007/04/importance-of-exporting-nd-importing.html) |代替可能なエクスポートの仕組みに関する詳細を提供します。

| **重要：** 異なるJARから同じパッケージをエクスポートしないでください。 複数|同じパッケージをエクスポートすると、「分割パッケージ」の問題が発生します。影響はケースごとに異なります。

これで、モジュールまたはプラグインの素晴らしい\ [EDITOR：またはterrible \！\]パッケージを他のモジュールと共有できます！

## 関連トピック

[WABジェネレーターの使用](/docs/7-1/tutorials/-/knowledge_base/t/using-the-wab-generator)

[ツーリング](/docs/7-1/tutorials/-/knowledge_base/t/tooling)
