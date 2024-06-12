JAVAPROJECT = ..
CLASSPATH = $(JAVAPROJECT)/bin
PARSER = parser.Parser

OUTPUT_DIR = output

help:
	@echo "1. INSTALL JavaCC with command: apt get install javacc"
	@echo "2. RUN     JavaCC with command: make parser"
	@echo "3. PARSE   file.gal with command: make file.parsing, make file.aut, make file.ast"  

parser:
	@make -C $(JAVAPROJECT)/gal/parser/

%.txt: %.gal 
	java -cp $(CLASSPATH) $(PARSER) -file $< -txt	

%.aut.dot: %.gal 
	@java -cp $(CLASSPATH) $(PARSER) -file $< -aut > $@

%.ast.dot: %.gal 
	@java -cp $(CLASSPATH) $(PARSER) -file $< -ast > $@

%.jpg: %.dot
	@dot -Tjpg $< > $@
	@open $@
	
clean:
	-@rm -f *.jpg *.dot