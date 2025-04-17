// number_properties.h

#include <stdio.h>

#define FACTORIAL(n)                                \
    ({                                              \
        int _fact = 1;                              \
        for (int i = 1; i <= n; i++)                \
            _fact *= i;                             \
        _fact;                                      \
    })

#define SUM_OF_NATURAL(n)                           \
    ({                                              \
        int _sum = 0;                               \
        for (int i = 1; i <= n; i++)                \
            _sum += i;                              \
        _sum;                                       \
    })


  
//main.c
#include "number_properties.h"

int main() {
    int n;
    printf("Enter a number: ");
    scanf("%d", &n);

    int factorial = FACTORIAL(n);
    int sum = SUM_OF_NATURAL(n);

    printf("Factorial of %d is: %d\n", n, factorial);
    printf("Sum of natural numbers till %d is: %d\n", n, sum);

    return 0;
}
