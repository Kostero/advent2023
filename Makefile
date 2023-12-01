%.jar: %.kt
	kotlinc $< -include-runtime -d $@ && java -jar $@