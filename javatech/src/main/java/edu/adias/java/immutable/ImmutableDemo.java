package edu.adias.java.immutable;

import java.util.Date;

public class ImmutableDemo {
	
	/**
	 * Strategy for defining immutable objects:
	 * 
	 * 1. Don't provide "setter" methods = methods that modify fields or objects referred to by fields
	 * 2. Make all fields final and private
	 * 3. Don't allow subclasses to override methods. The simplest way to do this is to declare the class as final. 
	 *    A more sophisticated approach is to make the constructor private and construct the instances in factory.
	 * 4. If the instance fields include references to mutable objects, don't allow those objects to be changed:	
	 * 		- Don't provide methods that modify the mutable objects.
 	 *		- Don't share references to the mutable objects. 
 	 *        Never store references to external, mutable objects passed to the constructor; 
 	 *        if necessary, create copies, and store references to the copies. Similarly, 
 	 *        create copies of your internal mutable objects when necessary to avoid returning the originals in your methods.
	 */
	
	public static void main(String[] args){
		Person p=new Person("Saida",new Date());
		System.out.println("Original Person:"+p);
		//str is a local variable no links with name field of Person object, so even though you make updates to local variable it has no impact to  the Person object
		String str=p.getName();
		str="Nagaraj";
		Date joiningDate=p.getJoiningDate();
		joiningDate.setDate(26);
	    System.out.println("Updated Person:"+p);
	}
}
