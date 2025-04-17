// conversion.h

#include <stdio.h>
#include <math.h>
#include <string.h>

#define BIN_TO_DEC(binStr, result)                   \
    do {                                             \
        int res = 0;                                 \
        for (int i = 0; binStr[i] != '\0'; i++) {    \
            res = res * 2 + (binStr[i] - '0');       \
        }                                            \
        result = res;                                \
    } while (0)

#define DEC_TO_BIN(dec, binStr)                      \
    do {                                             \
        int num = dec;                               \
        char temp[100] = "";                         \
        int idx = 0;                                 \
        while (num > 0) {                            \
            temp[idx++] = (num % 2) + '0';           \
            num /= 2;                                \
        }                                            \
        for (int i = 0; i < idx; i++)                \
            binStr[i] = temp[idx - i - 1];           \
        binStr[idx] = '\0';                          \
    } while (0)

#define BIN_TO_HEX(binStr, hexStr)                   \
    do {                                             \
        int decimal = 0;                             \
        BIN_TO_DEC(binStr, decimal);                 \
        sprintf(hexStr, "%X", decimal);              \
    } while (0)

#define HEX_TO_BIN(hexStr, binStr)                   \
    do {                                             \
        int decimal = 0;                             \
        sscanf(hexStr, "%x", &decimal);              \
        DEC_TO_BIN(decimal, binStr);                 \
    } while (0)



//main.c
#include "conversion.h"

int main() {
    char bin[100], hex[100], outbin[100];
    int dec;

    // Binary to Decimal
    strcpy(bin, "10101");
    BIN_TO_DEC(bin, dec);
    printf("Binary %s to Decimal: %d\n", bin, dec);

    // Decimal to Binary
    dec = 25;
    DEC_TO_BIN(dec, bin);
    printf("Decimal %d to Binary: %s\n", dec, bin);

    // Binary to Hexadecimal
    strcpy(bin, "11110000");
    BIN_TO_HEX(bin, hex);
    printf("Binary %s to Hex: %s\n", bin, hex);

    // Hexadecimal to Binary
    strcpy(hex, "1A");
    HEX_TO_BIN(hex, outbin);
    printf("Hex %s to Binary: %s\n", hex, outbin);

    return 0;
}
