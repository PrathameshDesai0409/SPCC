// geometry.h
#ifndef GEOMETRY_H
#define GEOMETRY_H

#define PI 3.14159

// Macro for area of Circle
#define AREA_CIRCLE(r) (PI * (r) * (r))

// Macro for area of Square
#define AREA_SQUARE(s) ((s) * (s))

// Macro for area of Rectangle
#define AREA_RECTANGLE(l, b) ((l) * (b))

// Macro for area of Triangle
#define AREA_TRIANGLE(b, h) (0.5 * (b) * (h))

#endif


//main.c
#include <stdio.h>
#include "geometry.h"

int main() {
    float radius = 5, side = 4, length = 6, breadth = 3, base = 8, height = 5;

    printf("Area of Circle: %.2f\n", AREA_CIRCLE(radius));
    printf("Area of Square: %.2f\n", AREA_SQUARE(side));
    printf("Area of Rectangle: %.2f\n", AREA_RECTANGLE(length, breadth));
    printf("Area of Triangle: %.2f\n", AREA_TRIANGLE(base, height));

    return 0;
}
