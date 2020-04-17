---
header-id: resolving-bundle-symbolicname-syntax-issues
---

# Bundle-SymbolicName構文問題の解決

[TOC levels=1-4]

LiferayのOSGiランタイムフレームワークは、`IllegalContextNameException`をスローすることがあります。多くの場合、これはOSGiバンドルの`Bundle-SymbolicName`マニフェストヘッダーにスペースが含まれているためです。

`Bundle-SymbolicName`は、`Bundle-Version`マニフェストヘッダーとともにバンドルを一意に識別するもので、スペースを含めることはできません。命名のベストプラクティスに従うには、`Bundle-SymbolicName`に逆ドメイン名を使用します。たとえば、`troubleshooting.liferay.com`というドメインのモジュールは、`com.liferay.troubleshooting.`と逆になります。

バンドルの`Bundle-SymbolicName`を指定するには、次の3つの方法があります。

1. バンドルの`bnd.bnd`ファイルの`Bundle-SymbolicName`ヘッダー。

2. プラグインWARの`liferay-plugin-package.properties`ファイルの`Bundle-SymbolicName`ヘッダー。

3. プラグインWARのファイル名（WARの`liferay-plugin-package.properties`に`Bundle-SymbolicName` ヘッダーがない場合）。

プラグインWARの場合は、`liferay-plugin-package.properties`ファイルで`Bundle-SymbolicName`を指定することをお勧めします。

たとえば、`liferay-plugin-package.properties`に`Bundle-SymbolicName`ヘッダーがないプラグインWARをデプロイする場合、[WABジェネレーター](/docs/7-1/tutorials/-/knowledge_base/t/using-the-wab-generator)はWARの名前をWABの`Bundle-SymbolicName`として使用します。WARの名前にスペースが含まれている場合（`space-program-theme v1.war`など）、デプロイ時に`IllegalContextNameException`が発生します。

    org.apache.catalina.core.ApplicationContext.log The context name 'space-program-theme v1' does not follow Bundle-SymbolicName syntax.
    org.eclipse.equinox.http.servlet.internal.error.IllegalContextNameException: The context name 'space-program-theme v1' does not follow Bundle-SymbolicName syntax.

ただし、`Bundle-SymbolicName`を設定する場合は、スペースを使用しないでください。

## 関連トピック

[Using the WAB Generator](/docs/7-1/tutorials/-/knowledge_base/t/using-the-wab-generator)
