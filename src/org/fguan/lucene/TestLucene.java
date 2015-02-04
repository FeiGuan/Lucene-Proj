package org.fguan.lucene;

import org.junit.Test;

public class TestLucene {
	
	@Test
	public void testIndex() {
		HelloLucene h1 = new HelloLucene();
		h1.index();
	}
	
	@Test
	public void testSearch() {
		HelloLucene h1 = new HelloLucene();
		h1.searcher();
	}
}
