---
header-id: upgrading-service-builder
---

# Service Builderのアップグレード

[TOC levels=1-4]

@product-ver@は引き続き[Service Builder](/docs/7-1/tutorials/-/knowledge_base/t/service-builder)を使用するため、永続性の詳細ではなく、アプリケーションのビジネスロジックに集中できます。 モデルクラス、ローカルおよびリモートサービス、および永続性は引き続き生成されます。

ほとんどのService Builderポートレットのアップグレードには、次の手順が含まれます。

1.  [@product-ver@のAPIにコードを適合させる](#step-1-adapt-the-code-to-product-vers-api)
2.  [依存関係を解決する](#step-2-resolve-dependencies)
3.  [サービスを構築する](#step-3-build-the-services)

まずはコードを調整することから始めます。

## ステップ1：コードを@product-ver@のAPIに適合させる

Upgrade Plannerを使用して、ポートレットを@product-ver@のAPIに適合させます。 プランナーの*アップグレードの問題の修復*ステップを実行すると、既存の事象の多くが自動修正されます。 残りの事象については、プランナーは新しいAPIの影響を受けるコード、およびそれを適応させる方法を特定します。

たとえば、次のコンパイルエラーがあるサンプルポートレットについて検討します。

``` bash
/html/guestbook/view.jsp(58,1) PWC6131: Attribute total invalid for tag search-container-results according to TLD
```

`view.jsp`ファイルは、@product-ver@の`liferay-ui`タグライブラリに存在しないタグライブラリ属性`total`を指定します。 2番目の属性`total`に注意してください。

``` markup
<liferay-ui:search-container-results
    results="<%=EntryLocalServiceUtil.getEntries(scopeGroupId,
                    guestbookId, searchContainer.getStart(),
                    searchContainer.getEnd())%>"
    total="<%=EntryLocalServiceUtil.getEntriesCount(scopeGroupId,
                    guestbookId)%>" />
```

このようなタグを作るため、`total`属性の割り当てを削除します。

``` markup
<liferay-ui:search-container-results
    results="<%=EntryLocalServiceUtil.getEntries(scopeGroupId,
                    guestbookId, searchContainer.getStart(),
                    searchContainer.getEnd())%>" />
```

コードが新しいAPIに適応するまで、これらのエラータイプなどを解決します。

## ステップ2：依存関係を解決する

アプリケーションの依存関係を適合するには、チュートリアル[Resolving a Plugin's Dependencies](/docs/7-1/tutorials/-/knowledge_base/t/resolving-a-plugins-dependencies)を参照してください。 依存関係がアップグレードされたら、サービスを再構築してください。\

## ステップ3：サービスを構築する

ポートレットのサービスを再構築するには、記事[Running Service Builder](/docs/7-1/tutorials/-/knowledge_base/t/running-service-builder)を参照してください。

以前のService Builderコードをアップグレードすると、異なる結果を生成する可能性があります。その変更の例を次に示します。

Liferay Portal 6.2ポートレットの`service.xml`ファイルは、`exception`要素で例外クラス名を次のように指定します。

``` xml
<service-builder package-path="com.liferay.docs.guestbook">
    ...
    <exceptions>
        <exception>GuestbookName</exception>
        <exception>EntryName</exception>
        <exception>EntryMessage</exception>
        <exception>EntryEmail</exception>
    </exceptions>
</service-builder>
```

Liferayポータル6.2では、Service Builderは`package-path`が指定するパス属性に、例外クラスを生成します。 @product-ver@では、Service Builderはそれらを`[package-path]/exception`に生成します。

古いパス：

    [package-path]

新しいパス：

``` 
[package-path]/exception 
```

例として、サンプルポートレットのパッケージパスは`com.liferay.docs.guestbook`です。 `exception`要素`GuestbookName`用の例外クラスは、`docroot/WEB-INF/service/com/liferay/docs/guestbook/exception`へ生成されます。 例外を使用するクラスは、`com.liferay.docs.guestbook.exception.GuestbookNameException`をインポートする必要があります。 Service Builderプロジェクトでこのアップグレードが必要な場合は、ポートレットの例外クラスへの参照をアップデートする必要があります。

Service Builderポートレットがアップグレードされたら、デプロイします。

| **注:** Upgrade PlannerまたはBlade CLIの`convert`コマンドを使用してLiferay Workspace | に自動的に移行されたService Builderポートレットは、自動的に | APIおよび実装モジュールに変換されたService Builderロジックを持ちます。 | これは@product-ver@のベストプラクティスです。

これでポートレットが@product@で利用可能になりました。 Service Builderを使用するポートレットのアップグレードはこれで完了です。\
