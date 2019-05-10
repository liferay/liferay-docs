---
header-id: bundle-generator-commands
---

# Bundle Generator Commands

[TOC levels=1-4]

The generated OSGi bundle automatically includes this for you. The npm 
commands shown below are also available:

- `npm run build`: Places the output of liferay-npm-bundler in the 
designated output folder. The standard output is a JAR file that can be 
deployed manually to @product@. 

- `npm run deploy`: 

- `npm run start`: Tests the application in a local webpack installation 
instead of a @product@ server. This speeds up development because you can 
see live changes without any need to deploy. Note, however, that because 
this is separate from a Liferay instance, you don't have access to Liferay's 
APIs. 

Q: How do I specify the port to use in `.npmbuildrc`?

By default, the webpack server uses port 8080. You can point the webpack server to a different port by setting the `port` key in `.npmbuildrc`:

```json
"webpack": {
  "port": 2070
}
```

