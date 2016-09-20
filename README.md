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

Execute inside git folder: `./gradlew build`

#### Build docker image
`./gradlew buildDocker`

#### Run docker
`docker run -p 8080:8080 -t com.fexco/postcoder-web-restapi-cache:1.0-SNAPSHOT`

#### Access the HTML/JS client
http://localhost:8080/client/index.html

### Configuration params

#### Change PostCoderWeb API key
Go to properties file located at 'src/main/resources/application.properties' and change the value of 'postcoder.web.api.key':
`postcoder.web.api.key=PCW45-12345-12345-1234X`

#### Change Cache Infinispan file storage location

Go to xml file located at 'src/main/resources/infinispan.xml' and change the value of `path` variable of file-store tag.
Default value points to `${java.io.tmpdir}`

```
<local-cache name="lookup-uk-address-geo">
           <persistence passivation="false">
               <!-- note that class is missing and is induced by the fileStore element name -->
               <file-store
                       shared="false"
                       preload="true"
                       fetch-state="true"
                       read-only="false"
                       purge="false"
                       path="${java.io.tmpdir}">
                   <write-behind flush-lock-timeout="15000" thread-pool-size="5" />
               </file-store>
           </persistence>
       </local-cache>
```


[Git]: http://help.github.com/set-up-git-redirect
[JDK8 build]: http://www.oracle.com/technetwork/java/javase/downloads
[Docker Engine]: https://docs.docker.com/engine/installation/linux/
