---
header-id: retrieving-portlet-ids-with-the-gogo-shell
---

# Gogo Shellを使用してポートレットIDを取得する

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

[サイトマップ](/docs/7-1/tutorials/-/knowledge_base/t/defining-portlets-in-a-sitemap)に含めるには、そのIDが必要です。 便宜上、すぐに使用可能なポートレットのIDは、 [完全修飾ポートレットID](/docs/7-1/reference/-/knowledge_base/r/fully-qualified-portlet-ids) リファレンスガイドに表示されます。 購入または開発したポートレットをインストールしている場合、このチュートリアルの指示に従って、Gogo Shellを使用してそれらのIDを取得できます。

Gogo Shellを使用してポートレットIDを取得するには、次の手順に従います。

1.  コントロールパネルを開き、設定→Gogo Shellに移動します。

2.  コマンド `lb [app prefix]`を実行して、アプリのWebバンドルを見つけます。 たとえば、 `lb blogs` を実行して、ブログWebバンドルを見つけます。
   
        100|Active     |   10|Liferay Blogs Web (3.0.7)

3.  コマンド `scr：list [bundle ID]`実行し、アプリのコンポーネントIDを見つけます。 ブログポートレットエントリを以下に示します。 バンドルの状態に先行する最後の番号は、コンポーネントIDです。

    ``` 
    [ 100] com.liferay.blogs.web.internal.portlet.BlogsPortlet enabled 
    [ 196] [active] 
    ```

4.  コマンド `scr：info [コンポーネントID]` を実行して、ポートレットの情報をリストします。 たとえば、ブログポートレットコンポーネントの情報を一覧表示するには、 `scr：info 196`実行します。 バンドルやコンポーネントIDはインスタンスによって異なる場合があることに注意してください。

5.  結果で `javax.portlet.name` 検索します。 `javax.portlet.name`の値は、サイトマップに必要なポートレットIDです。 ブログポートレットのIDを以下に示します。
   
        javax.portlet.name = com_liferay_blogs_web_portlet_BlogsPortlet

![図1：ポートレットIDはGogo Shellを介して検索できます。](../../../../../images/resources-importer-gogo-shell.png)

## 関連トピック

[サイトマップ内のページの定義](/docs/7-1/tutorials/-/knowledge_base/t/defining-pages-in-a-sitemap)

[サイトマップでのポートレットの定義](/docs/7-1/tutorials/-/knowledge_base/t/defining-portlets-in-a-sitemap)

[Resources Importer用のWebコンテンツの準備と整理](/docs/7-1/tutorials/-/knowledge_base/t/preparing-and-organizing-web-content-for-the-resources-importer)
