// series.h

#include <stdio.h>

#define FIBONACCI_SERIES(n)                          \
    do {                                             \
        printf("Fibonacci series up to %d terms:\n", n); \
        int a = 0, b = 1, c;                         \
        for (int i = 1; i <= n; i++) {               \
            printf("%d ", a);                        \
            c = a + b;                               \
            a = b;                                   \
            b = c;                                   \
        }                                            \
        printf("\n");                                \
    } while (0)

#define PRIME_SERIES(n)                              \
    do {                                             \
        printf("Prime numbers up to %d:\n", n);      \
        for (int i = 2; i <= n; i++) {               \
            int isPrime = 1;                         \
            for (int j = 2; j * j <= i; j++) {       \
                if (i % j == 0) {                    \
                    isPrime = 0;                     \
                    break;                           \
                }                                    \
            }                                        \
            if (isPrime)                             \
                printf("%d ", i);                    \
        }                                            \
        printf("\n");                                \
    } while (0)

#define LEAP_YEAR_SERIES(start, end)                                 \
    do {                                                             \
        printf("Leap years from %d to %d:\n", start, end);           \
        for (int year = start; year <= end; year++) {                \
            if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) \
                printf("%d ", year);                                 \
        }                                                            \
        printf("\n");                                                \
    } while (0)



//main.c
#include "series.h"

int main() {
    FIBONACCI_SERIES(10);
    PRIME_SERIES(30);
    LEAP_YEAR_SERIES(2000, 2024);
    return 0;
}
