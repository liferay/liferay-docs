---
header-id: system-check
---

# システムチェック

[TOC levels=1-4]

開発時は、OSGiコンテナであらゆる種類の奇妙なことが起こる場合があります。  Liferayの`system:check` [Gogoシェル](/docs/7-1/reference/-/knowledge_base/r/using-the-felix-gogo-shell)コマンドを使用すると、何が起こっているかを確認できます。Portalの最後の起動ステップとして実行されるようにでき、Gogoシェルでいつでも実行できます。

`system:check`は次のコマンドを集約します。

- [`ds:unsatisfied`](/docs/7-1/tutorials/-/knowledge_base/t/detecting-unresolved-osgi-components#dsunsatisfied-command)：不満足なDeclarative Serviceコンポーネントを報告します。

- [`dm na`](/docs/7-1/tutorials/-/knowledge_base/t/detecting-unresolved-osgi-components#dm-na-command)：Service Builderサービスを含む、不満足なDependency Managerサービスコンポーネントを報告します。

将来のLiferayツールのシステムチェック機能が`system:check`に追加される予定です。

[開発者モード](/docs/7-1/tutorials/-/knowledge_base/t/using-developer-mode-with-themes)では、起動時に`system:check`が自動的に実行されます。

`portal-ext.properties`ファイルでこのプロパティを設定することにより、開発者モード以外の起動時に`system:check`が実行されるようにできます。

    module.framework.properties.initial.system.check.enabled=true

前述のように、[Gogoシェル](/docs/7-1/reference/-/knowledge_base/r/using-the-felix-gogo-shell)ではいつでも`system:check`コマンドを実行できます。 
`system:check`を使用すると、未解決のコンポーネントやその他の問題をすばやく検出できます。

## 関連トピック

[Detecting Unresolved OSGi Components](/docs/7-1/tutorials/-/knowledge_base/t/detecting-unresolved-osgi-components)

[Gogo shell](/docs/7-1/reference/-/knowledge_base/r/using-the-felix-gogo-shell)
