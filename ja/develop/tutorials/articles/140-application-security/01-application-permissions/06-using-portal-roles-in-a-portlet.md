---
header-id: using-portal-roles-in-a-portlet
---

# ポートレットでのJSRロールの使用

[TOC levels=1-4]

@product@のロールは、コンテンツへのアクセスを許可または制限するための主要な手段です。Liferayのアクセス許可システムを使用*しない*ことにした場合は、ポートレット内のロールをポータルが提供するロールにマップするJSR168、286、および362の仕様が提供する基本システムを使用できます。

## JSRポートレットのセキュリティ

ポートレットの仕様は、ポートレットが`docroot/WEB-INF/portlet.xml`記述子で使用するロールを指定する手段を定義します。ただし、ロール名自体は標準化されていません。これらのポートレットを@product@で実行する場合、ポートレットで定義されたロール名をポータルに存在するロールにマップする必要があります。

たとえば、ゲストブックポートレットとゲストブック管理ポートレットの2つのポートレットを含むゲストブックプロジェクトを考えてみましょう。ゲストブックプロジェクトの`portlet.xml`ファイルのWARバージョンは、*administrator*、*guest*、*power-user*、および*user*ロールを参照します。

```xml
<?xml version="1.0"?>
<portlet-app xmlns="http://java.sun.com/xml/ns/portlet/portlet-app_2_0.xsd" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://java.sun.com/xml/ns/portlet/portlet-app_2_0.xsd http://java.sun.com/xml/ns/portlet/portlet-app_2_0.xsd" version="2.0">

<portlet>
    <portlet-name>guestbook</portlet-name>
    <display-name>Guestbook</display-name>
    <portlet-class>
        com.liferay.docs.guestbook.portlet.GuestbookPortlet
    </portlet-class>
    <init-param>
        <name>view-template</name>
        <value>/html/guestbook/view.jsp</value>
    </init-param>
    <expiration-cache>0</expiration-cache>
    <supports>
        <mime-type>text/html</mime-type>
        <portlet-mode>view</portlet-mode>
    </supports>
    <portlet-info>
        <title>Guestbook</title>
        <short-title>Guestbook</short-title>
        <keywords></keywords>
    </portlet-info>
    <security-role-ref>
        <role-name>administrator</role-name>
    </security-role-ref>
    <security-role-ref>
        <role-name>guest</role-name>
    </security-role-ref>
    <security-role-ref>
        <role-name>power-user</role-name>
    </security-role-ref>
    <security-role-ref>
        <role-name>user</role-name>
    </security-role-ref>
</portlet>
<portlet>
    <portlet-name>guestbook-admin</portlet-name>
    <display-name>Guestbook Admin</display-name>
    <portlet-class>
        com.liferay.docs.guestbook.portlet.GuestbookAdminPortlet
    </portlet-class>
    <init-param>
        <name>view-template</name>
        <value>/html/guestbookadmin/view.jsp</value>
    </init-param>
    <expiration-cache>0</expiration-cache>
    <supports>
        <mime-type>text/html</mime-type>
        <portlet-mode>view</portlet-mode>
    </supports>
    <portlet-info>
        <title>Guestbook Admin</title>
        <short-title>Guestbook Admin</short-title>
        <keywords></keywords>
    </portlet-info>
    <security-role-ref>
        <role-name>administrator</role-name>
    </security-role-ref>
    <security-role-ref>
        <role-name>guest</role-name>
    </security-role-ref>
    <security-role-ref>
        <role-name>power-user</role-name>
    </security-role-ref>
    <security-role-ref>
        <role-name>user</role-name>
    </security-role-ref>
</portlet>
```

OSGiベースの`guestbook-web`モジュールプロジェクトは、ポートレットクラスの`@Component`アノテーションに、XMLファイルを含まないロールを定義します。

```java
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + GuestbookPortletKeys.Guestbook,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
```
OSGiベースのMVCポートレットを使用している場合、JSR-362ロールをLiferayロールにマッピングする唯一の方法は、Liferay WARファイルの`portlet.xml`に配置することであるため、Liferayのアクセス許可システムを使用する必要があります。

`portlet.xml`ロールは、作成済みの特定のロールにマップする必要があります。
これらのマッピングにより、@product@は、異なるポートレット（例：異なる開発者のポートレット）からの名前が同じロール間の競合を解決できます。

**注**：ポートレットの`<security-role-ref>`要素で指定された各ロールには、ポートレットをページに追加する権限が付与されます。

## ポータルロールへのポートレットロールのマッピング

ロールを@product@にマップするには、`docroot/WEB-INF/liferay-portlet.xml` Liferay固有の構成ファイルを使用する必要があります。例については、ゲストブックプロジェクトの`liferay-portlet.xml`ファイルで定義されているマッピングを参照してください。

```xml
<role-mapper>
    <role-name>administrator</role-name>
    <role-link>Administrator</role-link>
</role-mapper>
<role-mapper>
    <role-name>guest</role-name>
    <role-link>Guest</role-link>
</role-mapper>
<role-mapper>
    <role-name>power-user</role-name>
    <role-link>Power User</role-link>
</role-mapper>
<role-mapper>
    <role-name>user</role-name>
    <role-link>User</role-link>
</role-mapper>
```

ポートレット定義がロール`power-user`を参照している場合、そのポートレットは、すでにLiferayのデータベースにある*パワーユーザー*と呼ばれるLiferayロールにマップされます。

前述したように、ポータルのロール名は標準化されていません。上記のデフォルトのLiferay名とは異なるロール名でポートレットをデプロイする場合は、`portal-ext.properties`ファイルの`system.roles`プロパティに名前を追加する必要があります。

```properties
system.roles=my-role,your-role,our-role
```

これにより、ロールが誤って作成されるのを防ぎます。

ロールがポータルにマップされると、ポートレット仕様で定義されているメソッドを使用できます。

- `getRemoteUser()`
- `isUserInRole()`
- `getUserPrincipal()`

たとえば、次のコードを使用して、現在のユーザーに`power-user`ロールがあるかどうかを確認できます。

```java
if (renderRequest.isUserInRole("power-user")) {
    // ...
}
```

デフォルトでは、Liferayは組み込みポートレットで`isUserInRole()`メソッドを使用しません。Liferayは、独自のアクセス許可システムを直接使用して、よりきめ細かいセキュリティを実現します。ポートレットを他のポータルサーバーにデプロイする予定がない場合は、Liferayのアクセス許可システムを使用することをお勧めします。このシステムでは、アプリケーションの権限を調整するためのより堅牢な方法が提供されます。

## 関連トピック

[Liferay Permissions](/docs/7-1/tutorials/-/knowledge_base/t/defining-application-permissions)

[Asset Framework](/docs/7-1/tutorials/-/knowledge_base/t/asset-framework)

[Portlets](/docs/7-2/tutorials/-/knowledge_base/t/portlets)

[Understanding ServiceContext](/docs/7-1/tutorials/-/knowledge_base/t/understanding-servicecontext)
