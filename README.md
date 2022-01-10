# Fuzzing introduction
This repository contains some introduction to instrumented fuzzing. This tutorial assumes that you are using linux or macos and have not been tested with mac computers without intel chip.

In this repository you can find guides for running some simple fuzzing examples in C, Python and Java. Separate instruction can be find under each directory.

This repository contain some submodules, i recomment cloning it recursive by adding the **--recursive** flag.

## Docker
A docker is also provided that can be used instead of installing stuff on your system. Since i have no experience fuzzing on windows system, this would be the solution for people with windows. Some thing might work well in Windows (or perhaps in WSL?), but i have not tested that. 

You can either:
* build this one yourself, see the **docker** directory
* Pull a pre built one from dockerhub: todo add link

I typically mount the directory i am working in to the docker instance:
```sh
docker run -v $(pwd):/workspace -it ubuntu-fuzzing
```

## C/C++
C and C++ fuzzing are performed libFuzzer (https://llvm.org/docs/LibFuzzer.html) or AFLPlusPlus (https://github.com/AFLplusplus/AFLplusplus). Typicaly other C/C++ development tools are also needed/useful (make, cmake, ...). I recommend installing LLVM and using clang. On mac computers this can be done using homebrew:
```sh
brew install llvm
```

And on linux, see the Dockerfile in the *docker* directory for installation in ubuntu 20.04. Similar packages are available using other package managers as well. That dockerfile contains packages that i find useful when developing C and/or C++ code.

## Python
Fuzz test required python version [3.6, 3.9]. Also, for installing atheris you need to have llvm installed. See the **python** directory for more information.

## Java
Fuzzing is performed using Jazzer (https://github.com/CodeIntelligenceTesting/jazzer). This is how i interpreted the usage of it and how i set it up. Jazzer requires libFuzzer to be installed, see the C/C++ for more information how to install LLVM.

### macOS
I can't remember exactly how i set up my environment. I think i installed bazel using homebrew:
```sh
$ brew install bazel
```