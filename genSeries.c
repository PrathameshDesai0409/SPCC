// series.h

#include <stdio.h>

#define FACTORIAL_SERIES(n)                            \
    do {                                               \
        printf("Factorial series up to %d:\n", n);     \
        int fact = 1;                                  \
        for (int i = 1; i <= n; i++) {                 \
            fact *= i;                                 \
            printf("%d ", fact);                       \
        }                                              \
        printf("\n");                                  \
    } while (0)

#define PRIME_SERIES(n)                                \
    do {                                               \
        printf("Prime numbers up to %d:\n", n);        \
        for (int i = 2; i <= n; i++) {                 \
            int isPrime = 1;                           \
            for (int j = 2; j*j <= i; j++) {           \
                if (i % j == 0) {                      \
                    isPrime = 0; break;                \
                }                                      \
            }                                          \
            if (isPrime) printf("%d ", i);             \
        }                                              \
        printf("\n");                                  \
    } while (0)

#define LEAP_YEAR_SERIES(start, end)                                  \
    do {                                                              \
        printf("Leap years from %d to %d:\n", start, end);            \
        for (int year = start; year <= end; year++) {                 \
            if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0)\
                printf("%d ", year);                                  \
        }                                                             \
        printf("\n");                                                 \
    } while (0)


//main.c
#include "series.h"

int main() {
    FACTORIAL_SERIES(5);
    PRIME_SERIES(30);
    LEAP_YEAR_SERIES(2000, 2024);
    return 0;
}
