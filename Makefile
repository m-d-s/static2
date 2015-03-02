.SUFFIXES:	.jj .java
.PHONY:		Static clean

GENERATED=Parser.java ParserConstants.java ParserTokenManager.java \
	  ParseException.java SimpleCharStream.java \
	  Token.java TokenMgrError.java

Static:	Parser.java Static.java
	javac Static.java

Parser.java: Parser.jj
	javacc Parser.jj

clean:
	-rm *.class ast/*.class compiler/*.class ${GENERATED} \
		tests/*.out tests/*.diff
