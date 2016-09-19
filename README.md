## Rest API for PostcoderWeb
The Rest API for PostcoderWeb wraps Allies Computing PostCoderWeb API call in order to avoid duplicated call, using a cache mechanism, minimizing the cost of the API access.
### How to build from sources
#### Prerequisites
 [Git]
 [JDK 8 update 20 or later][JDK8 build]
 [Docker Engine]
 
Be sure that your `JAVA_HOME` environment variable points to the `jdk1.8.0` folder
extracted from the JDK download.
#### Check out sources
`git clone https://github.com/emivaljr/postcoder-web-rest-cache.git`

#### Compile and test; build all jars, distribution zips, and docs
`./gradlew build`




[Git]: http://help.github.com/set-up-git-redirect
[JDK8 build]: http://www.oracle.com/technetwork/java/javase/downloads
[Docker Engine]: https://docs.docker.com/engine/installation/linux/
