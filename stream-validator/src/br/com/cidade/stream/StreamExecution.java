package br.com.cidade.stream;


public class StreamExecution {

	
	public static void main(String[] args) {
		Stream stream = new StreamImpl(args[0]);
		char result = 0;
		while(stream.hasNext()){
			result = stream.getNext();
		}
		System.out.println(result);
	}
}
