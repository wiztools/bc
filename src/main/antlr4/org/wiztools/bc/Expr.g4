grammar Expr ;

prog :	expr ;
expr :	expr op=('*'|'/') expr     # MulDiv
    |	expr op=('+'|'-') expr     # AddSub
    |	NUMBER                     # number
    |	'(' expr ')'               # parens
    ;

NUMBER  : [0-9]+ ;
WS      : [ \t]+ -> skip ;

// Assign Token names to arithmetic symbols used in grammar:

MUL : '*';
DIV : '/';
ADD : '+';
SUB : '-';
