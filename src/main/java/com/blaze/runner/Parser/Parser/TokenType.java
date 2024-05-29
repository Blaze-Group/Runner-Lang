package com.blaze.runner.Parser.Parser;


public enum TokenType {


    MINUSGT,//->
    ENUM,
    FOREACH,
    EQ, // =
    EQEQ, // ==
    EXCL, // !
    EXCLEQ, // !=
    LTEQ, // <=
    LT, // <
    GT, // >
    GTEQ, // >=
    
    PLUSEQ, // +=
    MINUSEQ, // -=
    STAREQ, // *=
    SLASHEQ, // /=
    PERCENTEQ, // %=
    ATEQ, // @=
    AMPEQ, // &=
    CARETEQ, // ^=
    BAREQ, // |=
    COLONCOLONEQ, // ::=
    LTLTEQ, // <<=
    GTGTEQ, // >>=
    GTGTGTEQ,// >>>=
    
    PLUSPLUS, // ++
    MINUSMINUS, // --
    
    LTLT, // <<
    GTGT, // >>
    GTGTGT, // >>>

    DOTDOT, // ..
    STARSTAR, // **
    QUESTIONCOLON, // ?:
    QUESTIONQUESTION, // ??
    
    TILDE, // ~
    CARET, // ^
    CARETCARET, // ^^
    BAR, // |
    BARBAR, // ||
    AMP, // &
    AMPAMP, // &&
    
    QUESTION, // ?
    COLON, // :
    COLONCOLON, // ::

    LPAREN, // (
    RPAREN, // )
    LBRACKET, // [
    RBRACKET, // ]
    LBRACE, // {
    RBRACE, // }
    COMMA, // ,
    DOT, // .

    NUMBER,
    HEX_NUMBER,
    WORD,
    TEXT,

    OUT,
    OUTLINE,
    IF,
    ELSE,
    WHILE,
    FOR,
    BREAK,
    CONTINUE,
    VOID,
    RETURN,
    IMPORT,
    SWITCH,
    CASE,
    INCLUDE,
    CLASS,
    NEW,
    VAR,
    RUN,

    PLUS, // +
    MINUS, // -
    STAR, // *
    SLASH, // /
    PERCENT,// %
    AT, // @

    EOF
}
