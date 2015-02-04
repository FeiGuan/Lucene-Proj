#Lucene-Proj

##Step1: create index
#####Involved jars:
lucene-core, lucene-analyze, junit
#####Flow:
Create Directory (where index lies)<br>
Create IndexWriter <br>
Create Document for each indexed file<br>
Add fields to each Document<br>
Write documents to index file<br>
Close IndexWriter<br>