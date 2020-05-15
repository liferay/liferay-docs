---
header-id: updating-default-plugins-provided-by-liferay-workspace
---

# Liferay Workspaceが提供するデフォルトプラグインのアップデート

[TOC levels=1-4]

Liferay Workspaceには、以下のようなプラグインが多数付属しています。

- [CSS Builder](https://github.com/liferay/liferay-portal/tree/master/modules/util/css-builder)
- [Javadoc Formatter](https://github.com/liferay/liferay-portal/tree/master/modules/util/javadoc-formatter)
- [Lang Builder](https://github.com/liferay/liferay-portal/tree/master/modules/util/lang-builder)
- [Service Builder](https://github.com/liferay/liferay-portal/tree/master/modules/util/portal-tools-service-builder)
- [Source Formatter](https://github.com/liferay/liferay-portal/tree/master/modules/util/source-formatter)
- [Theme Builder](https://github.com/liferay/liferay-portal/tree/master/modules/util/portal-tools-theme-builder)
- etc.

バンドルされたプラグインは、ワークスペースのリリースごとにアップデートされます。[Source Formatterプラグイン](https://github.com/liferay/liferay-portal/tree/master/modules/util/source-formatter)に新しい機能が必要だけれど、最新のワークスペースバージョンではまだそれが含まれていないとしましょう。このような場合は、自分でアップグレードすることができます。

ワークスペースにバンドルされているプラグインのいずれかをアップグレードするには、以下の手順を実行します。

1. アップデートするプラグインのバンドルシンボリック名（BSN）を見つけます。この値は、[`portal-tools.properties`](https://github.com/liferay/liferay-portal/blob/master/modules/sdk/gradle-plugins/src/main/resources/com/liferay/gradle/plugins/dependencies/portal-tools.properties)ファイルにあります。たとえば、Source FormatterのBSNは`com.liferay.source.formatter`です。

2. ワークスペースの`build.gradle`ファイルを開き、`.version`が後ろに続いているプラグインのBSNをコピーしてから、使用するプラグインバージョンを設定します。例えば、

       com.liferay.source.formatter.version=1.0.654
   
   最新のプラグインに関心がある場合は、上記のプロパティを`latest.release`に設定すると常に利用可能な最新バージョンを使用することができます。ただし、これによりワークスペースが不安定になる可能性があります。

 これで、ワークスペースが提供する特定のプラグインバージョンに縛られなくなります。
