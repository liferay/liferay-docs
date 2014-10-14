# Environment Variable Security

Specify regular expression patterns used to match environment variables
that the plugin is permitted to access. 

*Example:*

    security-manager-environment-variables=\
        java.home,\
        java.vendor,\
        java.version,\
        java.vm.v.*
