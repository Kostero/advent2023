%.jar: %.kt
	kotlinc $< -include-runtime -d $@ && java -Xss1g -jar $@