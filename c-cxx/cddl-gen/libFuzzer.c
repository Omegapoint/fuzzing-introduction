#include <stdint.h>
#include <stddef.h>
#include "pet_decode.h"

int LLVMFuzzerTestOneInput(const uint8_t *data, size_t size) {
    uint32_t payload_len_out = 0;
    struct Pet result;
    cbor_decode_Pet(data, size,
                    &result,
                    &payload_len_out);
    return 0;
}
