 #!/bin/sh
find . -name .svn -print0 | xargs -0 rm -rf
