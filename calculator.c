//calc.l
%{
  #include "y.tab.h"
  %}
  
  %%
  
  [0-9]+          { yylval = atoi(yytext); return NUMBER; }
  [+\-*/()]       { return yytext[0]; }
  [\n\t ]         ;  // ignore
  .               { printf("Invalid character: %s\n", yytext); }
  
  %%



//calc.y
%{
  #include <stdio.h>
  #include <stdlib.h>
  %}
  
  %token NUMBER
  
  %left '+' '-'
  %left '*' '/'
  
  %%
  input:
      input expr '\n'   { printf("= %d\n", $2); }
      | /* empty */
      ;
  
  expr:
      expr '+' expr     { $$ = $1 + $3; }
      | expr '-' expr   { $$ = $1 - $3; }
      | expr '*' expr   { $$ = $1 * $3; }
      | expr '/' expr   { 
                          if ($3 == 0) {
                              printf("Error: Division by zero\n");
                              exit(1);
                          }
                          $$ = $1 / $3; 
                        }
      | '(' expr ')'    { $$ = $2; }
      | NUMBER          { $$ = $1; }
      ;
  %%
  
  int main() {
      printf("Enter expressions (Ctrl+C to quit):\n");
      return yyparse();
  }
  
  int yyerror(char *s) {
      printf("Parse Error: %s\n", s);
      return 0;
  }
  