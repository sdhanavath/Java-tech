package edu.adias.java.tech.clone;

public class CloneDemo {

	public static void main(String[] args) {
		
		Person p=new Person("Saida",30);
		System.out.println("Original Person:"+p);
		
		Person duplicatePerson;
		
		try {
			duplicatePerson=(Person) p.clone();
			System.out.println("Duplicate Person:"+duplicatePerson);
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
	}

}
