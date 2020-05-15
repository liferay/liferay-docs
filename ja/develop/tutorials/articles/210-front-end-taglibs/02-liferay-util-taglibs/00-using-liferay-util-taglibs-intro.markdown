---
header-id: using-the-liferay-util-taglib
---

# Liferay Util Taglibの使用

[TOC levels=1-4]

Liferay Util taglibは、他のリソースをポートレットまたはテーマに取り込むために使用されます。
これを使用して、ページのHTMLの下部または上部に挿入するリソースを指定できます。

Liferay-Util taglibを使用するには、次の宣言をJSPに追加します。

    <%@ taglib prefix="liferay-util" uri="http://liferay.com/tld/util" %>

Liferay-Util taglibは、FreeMarkerのテーマテンプレートおよびWebコンテンツテンプレート用にマクロ経由でも利用できます。次の構文に従います。

    <@liferay_util["tag-name"] attribute="string value" attribute=10 />

チュートリアルのこのセクションでは、コンテンツをポートレットやテーマに挿入できる、アプリで使用可能なLiferay Utilタグについて説明します。
