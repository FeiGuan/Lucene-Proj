#Lucene-Proj

##Step1: Create index
#####Involved jars:
lucene-core, lucene-analyze, junit
#####Flow:
Create Directory (where index lies)<br>
Create IndexWriter <br>
Create Document for each indexed file<br>
Add fields to each Document<br>
Write documents to index file<br>
Close IndexWriter<br>

##Step2: Create searcher
#####Involved jars:
lucene-queryparser, lucene-queries
#####Flow:
Create Directory (where index lies)
Create IndexReader
Create IndexSearcher
Create QueryParser & Query
Acqurie TopDocs
Acquire ScoreDoc array
Acquire Document