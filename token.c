//token_counter.l
%{
  #include <stdio.h>
  #include <string.h>
  
  int id = 0, kw = 0, op = 0, num = 0, sp = 0;
  
  char* keywords[] = {
      "int", "float", "char", "return", "if", "else", "while", "for", "void", NULL
  };
  
  int isKeyword(char *word) {
      for (int i = 0; keywords[i]; i++) {
          if (strcmp(keywords[i], word) == 0)
              return 1;
      }
      return 0;
  }
  %}
  
  %%
  [a-zA-Z_][a-zA-Z0-9_]* {
      if (isKeyword(yytext)) {
          printf("%s : KEYWORD\n", yytext); kw++;
      } else {
          printf("%s : IDENTIFIER\n", yytext); id++;
      }
  }
  [0-9]+ {
      printf("%s : NUMBER\n", yytext); num++;
  }
  "=="|"="|"+"|"-"|"*"|"/" {
      printf("%s : OPERATOR\n", yytext); op++;
  }
  [\(\){};,] {
      printf("%s : SPECIAL SYMBOL\n", yytext); sp++;
  }
  [ \t\n]+   ; // Ignore whitespace
  .          { printf("%s : UNKNOWN\n", yytext); }
  %%
  
  int main() {
      printf("Enter code (Ctrl+D to end input):\n");
      yylex();
      printf("\n--- Token Summary ---\n");
      printf("Keywords: %d\nIdentifiers: %d\nOperators: %d\nNumbers: %d\nSpecial symbols: %d\n", kw, id, op, num, sp);
      return 0;
  }
  
  int yywrap() {
      return 1;
  }
  