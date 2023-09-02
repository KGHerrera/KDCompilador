import compilerTools.Token;

%%
%class Lexer
%type Token
%line
%column
%{
    private Token token(String lexeme, String lexicalComp, int line, int column){
        return new Token(lexeme, lexicalComp, line+1, column+1);
    }
%}
/* Variables básicas de comentarios y espacios */
TerminadorDeLinea = \r|\n|\r\n
EntradaDeCaracter = [^\r\n]
EspacioEnBlanco = {TerminadorDeLinea} | [ \t\f]
ComentarioTradicional = "/*" [^*] ~"*/" | "/*" "*"+ "/"
FinDeLineaComentario = "//" {EntradaDeCaracter}* {TerminadorDeLinea}?
ContenidoComentario = ( [^*] | \*+ [^/*] )*
ComentarioDeDocumentacion = "/**" {ContenidoComentario} "*"+ "/"

/* Comentario */
Comentario = {ComentarioTradicional} | {FinDeLineaComentario} | {ComentarioDeDocumentacion}

/* Identificador */
Letra = [A-Za-zÑñ_ÁÉÍÓÚáéíóúÜü]
Digito = [0-9]
Identificador = {Letra}({Letra}|{Digito})*
Decimal = [0-9]+\.[0-9]+


/* Número */
Numero = 0 | [1-9][0-9]*
%%

/* Comentarios o espacios en blanco */
{Comentario}|{EspacioEnBlanco} { /*Ignorar*/ }

/* Identificador */
"!"{Identificador} { return token(yytext(), "IDENTIFICADOR", yyline, yycolumn); }

/* Tipos de datos */
num | bol | cad { return token(yytext(), "TIPO_DATO", yyline, yycolumn); }

/* numero */
{Numero} | {Decimal} { return token(yytext(), "NUMERO", yyline, yycolumn); }

/* booleano */
ver | fal { return token(yytext(), "BOOLEANO", yyline, yycolumn); }

/* cadenas */
'[^']*' { return token(yytext(), "CADENA", yyline, yycolumn); }

/* Operadores de agrupacion */
"(" { return token(yytext(), "PARENTESIS_A", yyline, yycolumn); }
")" { return token(yytext(), "PARENTESIS_C", yyline, yycolumn); }

"{" { return token(yytext(), "LLAVE_A", yyline, yycolumn); }
"}" { return token(yytext(), "LLAVE_C", yyline, yycolumn); }

/* signos de puntuacion */
";" { return token(yytext(), "FIN_SENTENCIA", yyline, yycolumn); }
"," { return token(yytext(), "COMA", yyline, yycolumn); }

/* Operador de asignacion */
"=" { return token(yytext(), "ASIGNACION", yyline, yycolumn); }

/*Estructuras de control*/
fun { return token(yytext(), "FUNCION", yyline, yycolumn); }
imp { return token(yytext(), "IMPRIMIR", yyline, yycolumn); }
cic { return token(yytext(), "CICLO", yyline, yycolumn); }
sic { return token(yytext(), "SI", yyline, yycolumn); }
noc { return token(yytext(), "ENTONCES", yyline, yycolumn); }

/* Operadores logicos */
y | o { return token(yytext(), "OPERADOR_LOGICO", yyline, yycolumn); }

/* Operadores de comparacion */
menor    { return token(yytext(), "FUNCION_COMPARACION", yyline, yycolumn); }
mayor    { return token(yytext(), "FUNCION_COMPARACION", yyline, yycolumn); }
igual   { return token(yytext(), "FUNCION_COMPARACION", yyline, yycolumn); }

/* Operaciones aritmeticas */
sumar    { return token(yytext(), "FUNCION_ARITMETICA", yyline, yycolumn); }
restar    { return token(yytext(), "FUNCION_ARITMETICA", yyline, yycolumn); }
multiplicar   { return token(yytext(), "FUNCION_ARITMETICA", yyline, yycolumn); }
dividir   { return token(yytext(), "FUNCION_ARITMETICA", yyline, yycolumn); }

/* Declarar funcion */
":"{Identificador} { return token(yytext(), "IDENTIFICADOR_FUNCION", yyline, yycolumn); }

/* identificador erroneo */
{Identificador} { return token(yytext(), "ERROR2", yyline, yycolumn); }

. { return token(yytext(), "ERROR", yyline, yycolumn); }