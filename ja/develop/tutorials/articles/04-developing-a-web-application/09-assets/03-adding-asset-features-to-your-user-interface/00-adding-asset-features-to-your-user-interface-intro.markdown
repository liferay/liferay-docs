---
header-id: adding-asset-features-to-your-user-interface
---

# ユーザーインターフェイスへのアセット機能の追加

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
