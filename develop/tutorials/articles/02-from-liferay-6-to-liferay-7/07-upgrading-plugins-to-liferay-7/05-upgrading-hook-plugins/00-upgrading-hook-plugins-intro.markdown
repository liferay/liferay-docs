# Upgrading Hook Plugins [](id=upgrading-hook-plugins)

@product@ has more extension points than ever. @product@ and most of its apps
are OSGi modules, comprised of components you can extend using OSGi Declarative
Services. Additionally, @product@ and its apps can be configured using OSGi
Configuration Administration. These standard technologies let you customize
@product@. 

@liferay-ide@'s Code Upgrade Tool helps you upgrade existing hook plugins to
@product-ver@. It adapts them to the API and converts them, in certain cases, to
modules or module fragments to run on @product-ver@. 
