# Python fuzzing using atheris

## Prerequisites
* python3 - *3.6 < version < 3.9*
* llvm
* python3-pip

I use virtualenv for making a virtual python3 environment:
```sh
python3 -m pip install virtualenv
```

## Setup environment
```sh
# Create a virtual environment for python3
$ python3 -m virtualenv venv3
# Activate virtual environment
$ source venv3/bin/activate

# Install atheris
# For mac users this would probably be /usr/local/opt/llvm/bin/clang
CLANG_BIN=clang pip install --no-binary atheris atheris
```

## Cbor2 library
Install the cbor2 library using pip:
```sh
$ pip install cbor2
```
Then run the fuzzing target:
```sh
python fuzz_cbor2.py
```
