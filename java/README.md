# Fuzz java code using Jazzer


## Using docker provided by Jazzer

```sh
docker run -v $(pwd):/fuzzing cifuzz/jazzer --cp=/fuzzing/fuzzing-1.0-SNAPSHOT-jar-with-dependencies.jar --target_class=se.omegapoint.fuzzing.JacksonCborFuzzing
```

## Downloading pre-built jazzer
Simplest seems to be to download a release from:
https://github.com/CodeIntelligenceTesting/jazzer/releases/tag/v0.10.0

Then build the project using the jar files and then simply run
```sh
jazzer --cp=your.jar --target_class=domain.your.class
```

Steps:
* Download the jazzer for your system and unpack it
* Add the JAR files to your java project
* Build your java project and run like written above
I added this in pom.xml:
```sh
<dependencies>

    ...

    <dependency>
      <groupId>com.code-intelligence</groupId>
      <artifactId>jazzer-agent</artifactId>
      <version>0.9.1</version>
      <scope>system</scope>
      <systemPath>${pom.basedir}/src/resources/jazzer_agent_deploy.jar</systemPath>
    </dependency>

    <dependency>
      <groupId>com.code-intelligence</groupId>
      <artifactId>jazzer-api</artifactId>
      <version>0.9.1</version>
      <scope>system</scope>
      <systemPath>${pom.basedir}/src/resources/jazzer_api_deploy.jar</systemPath>
    </dependency>

</dependencies>

```

To re-create things from the presentation i did this (on macOS):
```sh
$ wget https://github.com/CodeIntelligenceTesting/jazzer/releases/download/v0.10.0/jazzer-macos-0.10.0.tar.gz
$ tar -xvf jazzer-macos-0.10.0.tar.gz
$ cd fuzzing
$ mvn clean compile assembly:single
$ cd ..
$ JAVA_HOME=/Library/Java/JavaVirtualMachines/openjdk.jdk/Contents/Home

# Jackson CBOR fuzzing
$ ./jazzer --cp=fuzzing/target/fuzzing-1.0-SNAPSHOT-jar-with-dependencies.jar --target_class=se.omegapoint.fuzzing.JacksonCborFuzzing

# JJWT
$ ./jazzer --cp=fuzzing/target/fuzzing-1.0-SNAPSHOT-jar-with-dependencies.jar --target_class=se.omegapoint.fuzzing.JacksonCborFuzzing
```

## Using bazel
This seems a little bit messy. I just installed bazel using brew. Then i used the JAR files from the downloaded release and put them into a Java project. Then i used bazel like:
```sh
$ proj_dir=$(pwd)
$ target_class="se.omegapoint.fuzzing.JacksonCborFuzzing"
$ cd jazzer
bazel run //:jazzer -- --cp=$proj_dir/fuzzing/target/fuzzing-1.0-SNAPSHOT-jar-with-dependencies.jar --target_class=$target_class $proj_dir/output/$target_class
```
