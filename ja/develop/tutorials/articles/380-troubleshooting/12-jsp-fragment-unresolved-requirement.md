---
header-id: why-is-a-package-i-use-from-the-fragment-host-unresolved
---

# フラグメントホストから使用しているパッケージが解決しないのはなぜですか？

[TOC levels=1-4]

OSGiフラグメントは、フラグメントホストのすべてのパッケージにアクセスできます。別のバンドルからインポートする必要はありません。bndは、フラグメントが使用する外部パッケージ（フラグメントホストにあるものも含む）をフラグメントの`Import-Package: [package],...` OSGiマニフェストヘッダーに追加します。OSGiランタイムにエクスポートされたパッケージの場合は問題ありません。ただし、問題は、bndがホストの内部パッケージ（ホストがエクスポートしていないパッケージ）をインポートしようとする場合です。内部パッケージは`Unresolved requirement`のままであるため、OSGiランタイムはフラグメントをアクティブ化できません。フラグメントはフラグメントホストのパッケージをインポートすべきではありません。

ホストがエクスポートしていないホストパッケージを明示的に除外して、問題を解決します。

たとえば、次のフラグメントバンドルのJSPは、フラグメントホストバンドルの内部パッケージ`com.liferay.portal.search.web.internal.custom.facet.display.context`のクラスを使用します

    <%@
    page import="com.liferay.portal.search.web.internal.custom.facet.display.context.CustomFacetDisplayContext" %><%@
    page import="com.liferay.portal.search.web.internal.custom.facet.display.context.CustomFacetTermDisplayContext" %>

サンプルのホストバンドルはパッケージをエクスポートしていないため、フラグメントバンドルは、以下のようなOSGiマニフェストヘッダーを使用してパッケージのインポートからそのパッケージを明示的に除外することで、そのパッケージのインポートを回避できます。

    Import-Package: !com.liferay.portal.search.web.internal.*,*
