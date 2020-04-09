---
header-id: adding-asset-features-to-your-user-interface
---

# ユーザーインターフェイスへのアセット機能の追加

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

<div class="learn-path-step row">
    <p id="stepTitle">UIへのアセット機能の追加</p><p>手順1/5</p>
</div>

ゲストブックとゲストブックエントリエンティティがアセット対応になったので、アプリケーションにアセット機能を追加できます。 ゲストブックのコメント、評価、タグ、カテゴリ、および関連アセットを実装することから始めます。 次に、ゲストブックエントリにも同じ操作を行います。 これらの機能のすべてのバックエンドサポートは既に実装されています。 唯一のタスクは、これらの機能を使用するようにアプリケーションのユーザーインターフェイスを更新することです。

ここで、新しいインポートを必要とするいくつかの新しいJSPを作成します。 `guestbook-web` モジュールプロジェクトの `init.jsp` ファイルに次のインポートを追加します。

    <%@ taglib uri="http://liferay.com/tld/asset" prefix="liferay-asset" %>
    <%@ taglib uri="http://liferay.com/tld/comment" prefix="liferay-comment" %>
    
    <%@ page import="java.util.Map" %> 
    <%@ page import="java.util.HashMap" %>
    <%@ page import="com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil" %>
    <%@ page import="com.liferay.asset.kernel.service.AssetTagLocalServiceUtil" %>
    <%@ page import="com.liferay.asset.kernel.model.AssetEntry" %>
    <%@ page import="com.liferay.asset.kernel.model.AssetTag" %>
    <%@ page import="com.liferay.portal.kernel.util.ListUtil" %>
    <%@ page import="com.liferay.portal.kernel.comment.Discussion" %>
    <%@ page import="com.liferay.portal.kernel.comment.CommentManagerUtil" %>
    <%@ page import="com.liferay.portal.kernel.service.ServiceContextFunction" %>

ここでこれらのインポートを追加して、このセクションの作業中にエラーが発生しないようにします。
