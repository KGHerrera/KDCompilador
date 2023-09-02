import compilerTools.TextColor;
import java.awt.Color;

%%
%class LexerColor
%type TextColor
%char

%{
    private TextColor textColor(long start, int size, Color color){
        return new TextColor((int) start, size, color);
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
{Comentario} { return textColor(yychar, yylength(), new Color(146, 146, 146)); }
{EspacioEnBlanco} { /*Ignorar*/ }


/* Identificador */
"!"{Identificador} { return textColor(yychar, yylength(), new Color(72, 240, 117)); }

/* Tipos de datos */
num | bol | cad { return textColor(yychar, yylength(), new Color(215, 237, 128)); }

/* decimal */
{Decimal} | {Numero} { return textColor(yychar, yylength(), new Color(136, 212, 235)); }

/* booleano */
ver | fal { return textColor(yychar, yylength(), new Color(201, 155, 250)); }

/* Operadores de agrupacion */
"(" | ")" | "{" | "}" { return textColor(yychar, yylength(), new Color(130, 232, 169)); }

/* signos de puntuacion */
";" { return textColor(yychar, yylength(), new Color(169, 155, 179)); }

/* Operador de asignacion */
"=" { return textColor(yychar, yylength(), new Color(196, 233, 255)); }

/*Estructuras de control*/
fun | imp| cic | sic | noc { return textColor(yychar, yylength(), new Color(255, 138, 216)); }

/* Operadores logicos */
y | o { return textColor(yychar, yylength(), new Color(35, 120, 147)); }

/* Operadores de comparacion */
menor    { return textColor(yychar, yylength(), new Color(120, 174, 255)); }
mayor    { return textColor(yychar, yylength(), new Color(120, 174, 255)); }
igual   { return textColor(yychar, yylength(), new Color(120, 174, 255)); }

/* Operaciones aritmeticas */
sumar    { return textColor(yychar, yylength(), new Color(149, 235, 52)); }
restar    { return textColor(yychar, yylength(), new Color(149, 235, 52)); }
multiplicar {  return textColor(yychar, yylength(), new Color(149, 235, 52)); }
dividir   { return textColor(yychar, yylength(), new Color(149, 235, 52)); }

/* Declarar funcion */
":"{Identificador} { return textColor(yychar, yylength(), new Color(72, 240, 117)); }

/* identificador erroneo */
{Identificador} { return textColor(yychar, yylength(), new Color(252, 162, 162)); }


. { /* Ignorar */ }