#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <stdint.h>
#include <string.h>
#include "pet_decode.h"

int main(void)
{
    uint8_t buffer[4096];
    ssize_t bytes_read = read(0, buffer, sizeof(buffer));
    if (bytes_read < 0)
    {
        return -1;
    }


    uint32_t payload_len_out = 0;
    struct Pet result;
    bool ret = cbor_decode_Pet(buffer, bytes_read,
                               &result,
                               &payload_len_out);

    return (ret) ? 0 : -1;

}
