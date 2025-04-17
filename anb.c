//anb.l
%{
  #include "y.tab.h"
  %}
  
  %%
  a       { return A; }
  b       { return B; }
  \n      { return '\n'; }
  .       { /* Ignore other characters */ }
  %%

  

//anb.y
%{
  #include <stdio.h>
  %}
  
  %token A B
  
  %%
  start: string '\n'   { printf("Valid string (anb)\n"); return 0; }
       ;
  
  string: A string     // any number of a’s
        | B            // followed by single b
        ;
  %%
  
  int main() {
      printf("Enter a string (only a's followed by one b):\n");
      yyparse();
      return 0;
  }
  
  int yyerror(const char *s) {
      printf("Invalid string (does not match anb)\n");
      return 0;
  }
  