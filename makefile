pdf: answers.md
	pandoc -s answers.md -o answers.pdf

docx: answers.md
	pandoc -s answers.md -o answers.docx

