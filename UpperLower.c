//case_counter.l
%{
  int uppercase = 0, lowercase = 0;
  %}
  
  %%
  [A-Z]   { uppercase++; printf("%s is an UPPERCASE letter\n", yytext); }
  [a-z]   { lowercase++; printf("%s is a lowercase letter\n", yytext); }
  .       ;  // Ignore other characters
  %%
  
  int main() {
      printf("Enter text: ");
      yylex();
      printf("\nTotal Uppercase Letters: %d\n", uppercase);
      printf("Total Lowercase Letters: %d\n", lowercase);
      return 0;
  }
  
  int yywrap() {
      return 1;
  }
  