%{
  int vowels = 0, consonants = 0;
  %}
  
  %%
  [aeiouAEIOU]   { vowels++; printf("%s is a vowel\n", yytext); }
  [a-zA-Z]       { consonants++; printf("%s is a consonant\n", yytext); }
  .              ; // Ignore other characters
  %%
  
  int main() {
      printf("Enter text: ");
      yylex();
      printf("\nTotal Vowels: %d\n", vowels);
      printf("Total Consonants: %d\n", consonants);
      return 0;
  }
  
  int yywrap() {
      return 1;
  }
  