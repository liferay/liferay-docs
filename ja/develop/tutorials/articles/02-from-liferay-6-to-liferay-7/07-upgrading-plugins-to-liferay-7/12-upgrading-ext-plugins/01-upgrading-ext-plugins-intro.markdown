---
header-id: upgrading-ext-plugins
---

# Extプラグインのアップグレード

[TOC levels=1-4]

Extプラグインを使用すると、内部APIを使用でき、@product@コアファイルを上書きすることもできます。 これによりデプロイメントは、Liferayによってリリースされたセキュリティ、パフォーマンス、または機能アップデートによって互換性がなくなるというリスクにさらされます。 @product@の新しいバージョンにアップグレードする場合、すべての変更を確認し、Extプロジェクトをマニュアルで変更して、変更を@product@にマージする必要があります。

@product-ver@へのアップグレード中は、可能であれば、既存のExtプラグインを使用する代わりに、拡張ポイントを活用して@product@をカスタマイズすることを強く推奨します。 @product-ver@は、@product@のほぼすべての詳細をカスタマイズできる、多くの拡張ポイントを提供します。 拡張ポイントで必要なものをカスタマイズする方法がある場合は、代わりにそのようにします。 @product@の拡張ポイントを使用する利点については、[More Extensible, Easier to Maintain](/docs/7-1/tutorials/-/knowledge_base/t/benefits-of-liferay-7-for-liferay-6-developers#more-extensible-easier-to-maintain)セクションで詳細をご覧ください。

Extプロジェクト、Extプロジェクトが必要な場合の決定方法、およびそれらの管理方法の詳細については、[Customization with Ext](/docs/7-1/reference/-/knowledge_base/r/customization-with-ext)セクションを参照してください。
