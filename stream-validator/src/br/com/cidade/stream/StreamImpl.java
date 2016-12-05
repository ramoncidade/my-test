package br.com.cidade.stream;

import java.util.ArrayList;
import java.util.List;

public class StreamImpl implements Stream {

	private String input;

	private int index = 0;
 
	public StreamImpl(String input) {
		this.input = input;
	}

	public char getNext() {
		return input.charAt(index);
	}

	public boolean hasNext() {
		Boolean result = Boolean.TRUE;

		try {
			if(isVowel(input.charAt(index))){
				if(isConsonant(input.charAt(index-1))){
					if(isVowel(input.charAt(index-2))){
						if(hasMoreThanOne(input,input.charAt(index))){
							result = Boolean.TRUE;
						}else{
							result = Boolean.FALSE;
						}
					}else{
						result = Boolean.TRUE;
					}
				}else{
					result = Boolean.TRUE;
				}
			}else{
				result = Boolean.TRUE;
			}
		} catch (IndexOutOfBoundsException ex) {
			result = Boolean.TRUE;
		}
		index = index +1;
		return result;
	}

	private boolean hasMoreThanOne(String input, char charAt) {
		return input.split(String.valueOf(charAt)).length > 2;
	}

	private Boolean isVowel(char letter){
		List<String> vowels = new ArrayList<String>();
		vowels.add("a");
		vowels.add("e");
		vowels.add("i");
		vowels.add("o");
		vowels.add("u");
		return vowels.contains(String.valueOf(letter).toLowerCase());
	}
	
	private Boolean isConsonant(char letter){
		List<String> consonants = new ArrayList<String>();
		consonants.add("b");
		consonants.add("c");
		consonants.add("d");
		consonants.add("f");
		consonants.add("g");
		consonants.add("h");
		consonants.add("j");
		consonants.add("k");
		consonants.add("l");
		consonants.add("m");
		consonants.add("n");
		consonants.add("p");
		consonants.add("q");
		consonants.add("r");
		consonants.add("s");
		consonants.add("t");
		consonants.add("v");
		consonants.add("x");
		consonants.add("z");
		consonants.add("w");
		consonants.add("y");
		return consonants.contains(String.valueOf(letter).toLowerCase());
	}
}
