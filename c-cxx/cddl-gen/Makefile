SOURCES = cddl-gen/src/cbor_common.c \
    cddl-gen/src/cbor_decode.c \
    cddl-gen/src/cbor_encode.c \
    pet_decode.c

INCLUDES = -Icddl-gen/include

debug:
	gcc $(SOURCES) $(INCLUDES) afl_more.c -O0 -g -fsanitize=address -o pet_afl_debug

afl_basic:
	afl-clang $(SOURCES) $(INCLUDES) afl_basic.c -O0 -g -o pet_afl_basic
	AFL_USE_ASAN=1 afl-fuzz -i input -o output ./pet_afl_basic

afl_more:
	AFL_USE_ASAN=1 afl-clang $(SOURCES) $(INCLUDES) afl_more.c -O0 -g -o pet_afl_more
	AFL_USE_ASAN=1 afl-fuzz -i input -o output ./pet_afl_more

libFuzzer:
	clang $(SOURCES) $(INCLUDES) libFuzzer.c -fsanitize=fuzzer,address -O0 -g -o pet_libFuzzer -m32
	./pet_libFuzzer
