package lex;

import java.io.FileInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Scan {
	
//	public static void main(String[] args){
//		Scan scan = new Scan("source1.c");
//		System.out.println(scan.input);
//	}
	
	private static String inputPath = "Input/";
	
	public String input;
	public int pointer;
	
	public Scan(String filename){
		File sourceFile = new File(Scan.inputPath+filename);//"input/source1.c"
		ArrayList<Character> trans = new ArrayList<Character>();
		try {
			//DataInputStream in = new DataInputStream(new FileInputStream(sourceFile));
			FileInputStream in = new FileInputStream(sourceFile);
			char ch1 = ' ';
			char ch2 = ' ';//ÓÃÓÚÔÚÑéÖ¤ÊÇ·ñÎªÒýºÅÄÚ½áÎ²»òÕß×¢ÊÍ½áÎ²
			while(in.available()>0){
				if(ch2 != ' '){
					ch1 = ch2;
				} else {
					ch1 = (char) in.read();
				}
				
				if(ch1 == '\''){//±ÜÃâÉ¾³ý¿Õ°×Ê±½«¡®¡¯°üº¬µÄ¿Õ°××Ö·ûÌÞ³ý
					trans.add(ch1);
					trans.add((char)in.read());
					trans.add((char)in.read());
				} else if (ch1 == '\"'){//±ÜÃâ½«×Ö·û´®ÖÐµÄ¿Õ°×ÌÞ³ý
					trans.add(ch1);
					while(in.available()>0){
						ch1 = (char)in.read();
						trans.add(ch1);
						if(ch1 == '\"'){
							break;
						}
					}
				} else if (ch1 == '/'){//ÌÞ³ý×Ö·û´®
					ch2 = (char)in.read();
					if(ch2 == '/'){
						while(in.available() > 0){
							ch2 = (char)in.read();
							if(ch2 == '\n'){
								break;
							}
						}
						ch2 = ' ';
					} else if (ch2 == '*') {
						while(in.available() > 0){
							ch1 = (char)in.read();
							if(ch1 == '*'){
								ch2 = (char)in.read();
								if(ch2 == '/'){
									break;
								}
							}
						}
					} else {
						if(ch2 == ' '){
							while(ch2 == ' '){
								ch2 = (char)in.read();
							}
						}
						trans.add(ch1);
						trans.add(ch2);
						ch2 = ' ';
					}
				} else if(ch1 == ' '){
					if(trans.get(trans.size()-1) == ' '){
						continue;
					} else {
						//trans.add(' ');
					}
				} else {
					if((int)ch1 == 13 ||(int)ch1 == 10 ||(int)ch1 == 32){//È¥³ý»»ÐÐ
						
					} else {
						trans.add(ch1);
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		char[] chStr = new char[trans.size()];
		for(int i = 0;i < trans.size();i++){
			chStr[i] = trans.get(i);
		}
		String result = new String(chStr);
		this.input = result;
		this.pointer = 0;
	}
	
	public char getNextChar(){
		if(pointer==input.length()){
			return (char)0;
		} else {
			return input.charAt(pointer++);
		}
	}
	
	//»ØÍËn¸ö×Ö·û
	public void retract(int n){
		for(int i = 0;i < n;i++){
			pointer--;
		}
	}
	
	public int getIndex(){
		return pointer;
	}
	
	public int getLength(){
		return this.input.length();
	}
	
	public String getSubStr(int index,int length){
		if((index+length-1)>=this.input.length()){
			return null;
		} else {
			String result = this.input.substring(index,index+length);
			return result;
		}
	}
	
	public String getTestString(int index){
		int temp = index;
		int len = 1;
		while(isLetterOrDigit(input.charAt(temp))&&(temp<=(input.length()-1))){
			temp++;
			len++;
		}
		String result = input.substring(index-1,index-1+len);
		return result;
	}
	
	private boolean isLetterOrDigit(char c){
		if(c=='_'||(c>='a'&&c<='z')||(c>='A'&&c<='Z')||(c>='0'&&c<='9')){
			return true;
		} else {
			return false;
		}
	}
	
	public String getLeftStr(int index){
		if(index == input.length()-1){
			return null;
		} else {
			return input.substring(index);
		}
	}
	
	public void move(int n){
		for(int i = 0;i < n;i++){
			pointer++;
		}
	}
	
	public String getStringInQuotation(int index){
		int temp = index;
		while(input.charAt(temp-1)!='\"'){
			temp--;
		}
		StringBuilder sb = new StringBuilder();
		while(input.charAt(temp) != '\"'){
			sb.append(input.charAt(temp));
			temp++;
		}
		return sb.toString();
	}
	
}
