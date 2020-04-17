---
header-id: javascript-utilities
---

# @product@ JavaScriptユーティリティ

[TOC levels=1-4]

このチュートリアルでは、`Liferay`グローバルJavaScriptオブジェクト内のユーティリティメソッドとオブジェクトの一部について説明します。

## Liferay Browser

`Liferay.Browser`オブジェクトには、グローバル`window.navigator`オブジェクトにアクセスして解析する必要なく、現在のユーザーエージェントの特性を公開するメソッドが含まれています。

`Liferay.Browser`オブジェクトで使用できるメソッドを以下の表にリストします。

| メソッド | 戻り値の型 | 説明 |
| --- | --- | --- |
| acceptsGzip | ブール値 | ブラウザがgzipファイル圧縮を受け入れるかどうかを返します |
| getMajorVersion | 数値 | ブラウザのメジャーバージョンを返します |
| getRevision | 数値 | ブラウザのリビジョンバージョンを返します |
| getVersion | 数値 | ブラウザのmajor.minorバージョンを返します |
| isAir | ブール値 | ブラウザがAdobe AIRかどうかを返します |
| isChrome | ブール値 | ブラウザがChromeかどうかを返します |
| isFirefox | ブール値 | ブラウザがFirefoxかどうかを返します |
| isGecko | ブール値 | ブラウザがGeckoかどうかを返します |
| isIe | ブール値 | ブラウザがInternet Explorerかどうかを返します |
| isIphone | ブール値 | ブラウザがIphone上にあるかどうかを返します |
| isLinux | ブール値 | ブラウザがLinuxで表示されているかどうかを返します |
| isMac | ブール値 | ブラウザがMacで表示されているかどうかを返します |
| isMobile | ブール値 | ブラウザがモバイルデバイスで表示されているかどうかを返します |
| isMozilla | ブール値 | ブラウザがMozillaかどうかを返します |
| isOpera | ブール値 | ブラウザがOperaかどうかを返します |
| isRtf | ブール値 | ブラウザがRTFをサポートしているかどうかを返します |
| isSafari | ブール値 | ブラウザがSafariかどうかを返します |
| isSun | ブール値 | ブラウザがSun OSで表示されているかどうかを返します |
| isWebKit | ブール値 | ブラウザがWebKitかどうかを返します |
| isWindows | ブール値 | ブラウザがWindowsで表示されているかどうかを返します |

## 関連トピック

[Accessing ThemeDisplay Information](/docs/7-1/tutorials/-/knowledge_base/t/liferay-themedisplay)
