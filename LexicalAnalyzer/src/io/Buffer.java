package io;

public class Buffer {
	
	private static char end = (char)-1;//哨兵标记
	
	private char[] array = new char[4096];
	
	private int lexemeBegin;//该指针指向当前词素的开始处
	private int forward;//该指针一直向前扫描，直到发现某个模式被匹配为止
	
	public char getChar(){
		return 'a';
	}
	
}
