# Fuzz java code using Jazzer


## Using docker provided by Jazzer
docker run -v $(pwd):/fuzzing cifuzz/jazzer --cp=/fuzzing/fuzzing-1.0-SNAPSHOT-jar-with-dependencies.jar --target_class=se.omegapoint.fuzzing.JacksonCborFuzzing


Simples seems to be to download a release from:
https://github.com/CodeIntelligenceTesting/jazzer/releases/tag/v0.10.0

Then build the project using the jar files and then simply run
jazzer --cp=your.jar --target_class=domain.your.class