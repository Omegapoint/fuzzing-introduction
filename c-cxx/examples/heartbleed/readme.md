# Heartbleed example
This setup is taken from: https://google.github.io/clusterfuzz/setting-up-fuzzing/heartbleed-example/

## Prerequisites
* llvm
* make

## Find heartbleed
### For linux users
```sh
# Unpack a vulnerable version of OpenSSL:
tar xf openssl-1.0.1f.tar.gz

# Build OpenSSL with ASan and fuzzer instrumentation:
cd openssl-1.0.1f/
CC="clang -g -fsanitize=address,fuzzer-no-link"
CXX=clang++
./config
make -j4
cd ..

# Build OpenSSL fuzz target for ClusterFuzz ($CXX points to clang++ binary):
$CXX -g handshake-fuzzer.cc -fsanitize=address,fuzzer openssl-1.0.1f/libssl.a \
  openssl-1.0.1f/libcrypto.a -std=c++17 -Iopenssl-1.0.1f/include/  \
  -ldl -lstdc++ -o handshake-fuzzer

# Start fuzzing
./handshake-fuzzer

```

### For MacOS users
* Install llvm using homebrew, possible you also need to install `make`. I have only tested this on a macbook pro with intel chip.
```sh
brew install llvm
```
For me, llvm was installed in `/usr/local/opt/llvm`. Then build OpenSSL and the handshake fuzzer like this:
```sh
# Unpack a vulnerable version of OpenSSL:
tar xf openssl-1.0.1f.tar.gz

# Build OpenSSL with ASan and fuzzer instrumentation:
cd openssl-1.0.1f/
CC="/usr/local/opt/llvm/bin/clang -g -fsanitize=address,fuzzer-no-link"
CXX=/usr/local/opt/llvm/bin/clang++
./Configure darwin64-x86_64-cc
make
cd ..

# Build OpenSSL fuzz target for ClusterFuzz ($CXX points to clang++ binary):
$CXX -g handshake-fuzzer.cc -fsanitize=address,fuzzer openssl-1.0.1f/libssl.a \
  openssl-1.0.1f/libcrypto.a -std=c++17 -Iopenssl-1.0.1f/include/  \
  -ldl -lstdc++ -o handshake-fuzzer

# Start fuzzing
./handshake-fuzzer
```

## Not working?
If you are getting a linker error, test compiling the handshake fuzzer like this:
```sh
$CXX -g handshake-fuzzer.cc -fsanitize=address,fuzzer openssl-1.0.1f/libssl.a \
  openssl-1.0.1f/libcrypto.a -std=c++17 -Iopenssl-1.0.1f/include/  \
  -ldl -lstdc++ -o handshake-fuzzer
```
