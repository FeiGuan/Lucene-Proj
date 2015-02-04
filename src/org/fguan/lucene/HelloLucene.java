package org.fguan.lucene;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class HelloLucene {

	public void index(){
		// Create Directory
		Directory directory = null;;
		try {
			directory = FSDirectory.open(new File("/Users/feiguan/Documents/lucene/index"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_4_9_1, new StandardAnalyzer(Version.LUCENE_CURRENT));
		IndexWriter writer = null;
		try {
			// Create IndexWriter
			writer = new IndexWriter(directory, iwc);
			
			Document doc = null;
			File f = new File("/Users/feiguan/Documents/lucene/example");
			for (File file : f.listFiles()) {
				// Create Document
				doc = new Document();
				// Add fields to document instance
				doc.add(new Field("content", new FileReader(file)));
				doc.add(new Field("filename", file.getName(), Field.Store.YES, Field.Index.NOT_ANALYZED));
				doc.add(new Field("path", file.getAbsolutePath(), Field.Store.YES, Field.Index.NOT_ANALYZED));
				// Write to index file
				writer.addDocument(doc);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (writer != null)
					writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void searcher() {
		
		try {
			// create Directory
			Directory directory = FSDirectory.open(new File("/Users/feiguan/Documents/lucene/index"));
			// create IndexReader
			IndexReader reader = IndexReader.open(directory);
			// create IndexSearcher based on IndexReader
			IndexSearcher searcher = new IndexSearcher(reader);
			// create Query
			QueryParser parser = new QueryParser(Version.LUCENE_4_9_1, "content", new StandardAnalyzer(Version.LUCENE_4_9_1));
			Query query = parser.parse("us");
			// return TopDocs based on searcher
			TopDocs tds = searcher.search(query, 10);
			// Acquire ScoreDoc based on TopDocs
			ScoreDoc[] sds = tds.scoreDocs;
			for (int i = 0; i < sds.length; i++) {
				// Acquire concrete Documents
				Document d = searcher.doc(sds[i].doc);
				System.out.println(d.get("filename")+"[" + d.get("path") + "]");
			}
			
			// Close reader
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
