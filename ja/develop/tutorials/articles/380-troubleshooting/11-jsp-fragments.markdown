---
header-id: why-arent-jsp-overrides-i-made-using-fragments-showing
---

# フラグメントを使用して行ったJSPオーバーライドが表示されないのはなぜですか？

[TOC levels=1-4]

**重要**：[@product@のAPIを使用してJSPをカスタマイズする](/docs/7-1/tutorials/-/knowledge_base/t/customizing-jsps)ことを強くお勧めします。 
OSGiフラグメントを使用したJSPのオーバーライドはAPIに基づいていないため、グレースフルな失敗を保証する方法はありません。代わりに、カスタマイズにバグがあると（コードやLiferayの変更のせいで）、実行時に機能が壊れたり厄介なログエラーが表示されたりすることで気付く場合が多いです。フラグメントを使用したJSPのオーバーライドは、最後の手段としてのみ使用してください。

フラグメントモジュールは、ホストモジュールの正確なバージョンを指定する必要があります。@product@のアップグレードにより、ホストモジュールの一部のJSPが変更され、バージョンの更新が要求される場合があります。この場合は、JSPのカスタマイズが更新されたホストのJSPと互換性があることを確認してから、ホストモジュールに一致するようにフラグメントモジュールの対象バージョンを更新します。

たとえば、フラグメントモジュールの次の`bnd.bnd`ファイルは、`Fragment-Host`を使用してホストモジュールとホストモジュールのバージョンを指定します。

    Bundle-Name: custom-login-jsp
    Bundle-SymbolicName: custom.login.jsp
    Bundle-Version: 1.0.0
    Fragment-Host: com.liferay.login.web;bundle-version="1.1.18"

[デプロイされたモジュールのバージョンを見つける](/docs/7-1/tutorials/-/knowledge_base/t/configuring-dependencies#finding-liferay-app-and-independent-artifacts)のは簡単です。

## 関連トピック

[JSP Overrides using Portlet Filters](/docs/7-1/tutorials/-/knowledge_base/t/jsp-overrides-using-portlet-filters)

[Customizing JSPs](/docs/7-1/tutorials/-/knowledge_base/t/customizing-jsps)

[Configuring Dependencies](/docs/7-1/tutorials/-/knowledge_base/t/configuring-dependencies)
