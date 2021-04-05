package main;

public class ExampleLinkedList {

	public static void main(String[] args) {
		
		SingleLinkedList<String> names = new SingleLinkedList<String>();  //although I don't have a constructor, why is it allowing me to put the parenthesis.
		
		names.add("Andrew");
		names.add("Mari");
		names.add("Janara");
		names.add("batman");
		
		System.out.println(names.toString());
		System.out.println();
		System.out.println();
		System.out.println();
		
		names.addFirst("Jordan");
		System.out.println(names.toString());
		


		

	}

}
