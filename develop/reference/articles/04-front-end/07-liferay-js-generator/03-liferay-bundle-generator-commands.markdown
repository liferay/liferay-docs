---
header-id: liferay-js-generator-commands
---

# Liferay JS Generator Commands

[TOC levels=1-4]

The npm commands shown below are available for the Liferay JS Generator:

- `npm run build`: Places the output of liferay-npm-bundler in the designated 
  output folder. The standard output is a JAR file that can be deployed manually 
  to @product@. 

- `npm run deploy`: Deploys the bundle to the configured app server

- `npm run start`: Tests the application in a local webpack installation instead 
  of a @product@ server. This speeds up development because you can see live 
  changes without any need to deploy. Note, however, that because this is 
  separate from a Liferay instance, you don't have access to Liferay's APIs. 

| **Note:** By default, the webpack server uses port 8080. You can point the
| webpack server to a different port by setting the `port` key in `.npmbuildrc`:
| 
|     "webpack": {
|       "port": 2070
|     }

- `npm run translate`: Runs the translation features for your bundle. Note that 
  this feature requires Microsoft Translator credentials. See 
  [Using Translation Features in Your widget](/docs/7-1/tutorials/-/knowledge_base/t/using-translation-features-in-your-portlet) 
  for more information.
