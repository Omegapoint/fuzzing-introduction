# Unpack a vulnerable version of OpenSSL:
tar xf openssl-1.0.1f.tar.gz

# Build OpenSSL with ASan and fuzzer instrumentation:
cd openssl-1.0.1f/
CC="/usr/local/opt/llvm/bin/clang -g -fsanitize=address,fuzzer-no-link"
CXX=/usr/local/opt/llvm/bin/clang++
./config
make
cd ..

# Build OpenSSL fuzz target for ClusterFuzz ($CXX points to clang++ binary):
$CXX -g handshake-fuzzer.cc -fsanitize=address,fuzzer openssl-1.0.1f/libssl.a \
  openssl-1.0.1f/libcrypto.a -std=c++17 -Iopenssl-1.0.1f/include/  \
  -ldl -lstdc++ -o handshake-fuzzer

# Start fuzzing
./handshake-fuzzer