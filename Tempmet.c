// conversions.h
#ifndef CONVERSIONS_H
#define CONVERSIONS_H

// Metres ↔ Feet
#define M_TO_FT(m) ((m) * 3.28084)
#define FT_TO_M(ft) ((ft) / 3.28084)

// Litres ↔ Cubic Feet
#define L_TO_CFT(l) ((l) * 0.0353147)
#define CFT_TO_L(cft) ((cft) / 0.0353147)

// Celsius ↔ Fahrenheit
#define C_TO_F(c) (((c) * 9.0 / 5.0) + 32)
#define F_TO_C(f) (((f) - 32) * 5.0 / 9.0)

#endif


//main.c
#include <stdio.h>
#include "conversions.h"

int main() {
    float m = 1, ft = 3.28084;
    float l = 1, cft = 0.0353147;
    float c = 0, f = 32;

    printf("1 metre = %.2f feet\n", M_TO_FT(m));
    printf("3.28084 feet = %.2f metres\n", FT_TO_M(ft));

    printf("1 litre = %.5f cubic feet\n", L_TO_CFT(l));
    printf("0.0353147 cubic feet = %.2f litres\n", CFT_TO_L(cft));

    printf("0 °C = %.2f °F\n", C_TO_F(c));
    printf("32 °F = %.2f °C\n", F_TO_C(f));

    return 0;
}
