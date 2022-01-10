# Find bugs in cddl-gen
cddl-gen is a library that parses a cddl file and generates C files for parsing CBOR data. A sample was created using
```sh
python cddl-gen/scripts/cddl_gen.py -c pet.cddl code -d -t Pet --oc pet_decode.c --oh pet_decode.h
```

This example uses both libFuzzer and AFL to find this bug. Lets focus on libFuzzer.

## Build cddl library and fuzzing target
Mac users will probably have to update Makefile and change *clang* to */usr/local/opt/llvm/bin/clang*.
```sh
# Build and start fuzzer
$ make libFuzzer
```

This will run and when a crash is found it will create a file with the input the caused the crash. The file will be named *crash-\**. E.g.: *crash-f14dfd8ee66d12f31c2b8315681dfdc096731464*

### Investigate the crash
A target that reads from stdin runs the same code as the libfuzzer target is also created. Build and run this by:
```sh
$ make debug

# Run the debug target with the input that caused a crash
$ ./pet_afl_debug < crash-f14dfd8ee66d12f31c2b8315681dfdc096731464

# Now you can use your favorite debugging tool for stepping through this, e.g.:
$ gdb ./pet_afl_debug
(gdb) break main
Breakpoint 1 at 0x691a: file afl_more.c, line 9.
(gdb) run < crash-f14dfd8ee66d12f31c2b8315681dfdc096731464

```

A version with a fix for one of the bugs is located within this folder: **cbor_decode_fix1.c**. Try replacing the faulty one with this one:
```sh
cp cbor_decode_fix1.c cddl-gen/src/cbor_decode.c

# Build and fuzz again:
make libFuzzer
```
Does it find any bugs? What else can we test?

Cbor is a binary format for representing structured data. It is quite like JSON but there are some additional data types, e.g. byte arrays. This is typical used in IoT systems. Those systems typicaly runs on 32 bits embedded systems. Lets test if we can find any bugs if we build it for 32 bit. Add the flash **-m32** to the libFuzzer target in the **Makefile**.

Can we find any more bugs?