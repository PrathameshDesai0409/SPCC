%{
  int characters = 0, words = 0, sentences = 0;
  int lines = 0, tabs = 0, numbers = 0, blanks = 0;
  %}
  
  %%
  
  [0-9]+          { numbers++; characters += yyleng; }
  [ \t]+          {
                      int i;
                      for (i = 0; i < yyleng; i++) {
                          if (yytext[i] == ' ') blanks++;
                          if (yytext[i] == '\t') tabs++;
                      }
                      characters += yyleng;
                  }
  [.!?]           { sentences++; characters++; }
  \n              { lines++; characters++; }
  [a-zA-Z]+       { words++; characters += yyleng; }
  .               { characters++; }
  
  %%
  
  int main() {
      printf("Enter text (Ctrl+D to end input):\n");
      yylex();
      printf("\n--- Text Statistics ---\n");
      printf("Characters: %d\n", characters);
      printf("Words: %d\n", words);
      printf("Sentences: %d\n", sentences);
      printf("Lines: %d\n", lines);
      printf("Tabs: %d\n", tabs);
      printf("Numbers: %d\n", numbers);
      printf("Blank spaces: %d\n", blanks);
      return 0;
  }
  
  int yywrap() {
      return 1;
  }
  