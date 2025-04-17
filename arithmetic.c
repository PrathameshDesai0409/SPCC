//arith_expr.l
%{
  #include <stdio.h>
  %}
  
  %%
  
  [0-9]+                  { printf("NUMBER: %s\n", yytext); }
  [a-zA-Z_][a-zA-Z0-9_]*  { printf("IDENTIFIER: %s\n", yytext); }
  "("                     { printf("LEFT PAREN: (\n"); }
  ")"                     { printf("RIGHT PAREN: )\n"); }
  [\+\-\*/]               { printf("OPERATOR: %s\n", yytext); }
  [ \t\n]+                ;   // Ignore whitespace
  .                       { printf("INVALID TOKEN: %s\n", yytext); }
  
  %%
  
  int main() {
      printf("Enter an arithmetic expression:\n");
      yylex();
      return 0;
  }
  
  int yywrap() {
      return 1;
  }
  