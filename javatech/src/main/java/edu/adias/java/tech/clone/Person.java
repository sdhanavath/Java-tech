package edu.adias.java.tech.clone;


public class Person implements Cloneable {
	
	private String name;
	private int age;
	
	public Person(String name,int age){
		this.name=name;
		this.age=age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }


	@Override
	public String toString() {
		return "HashCode="+this.hashCode()+"\tPerson [name=" + name + ", age=" + age + "]";
	}
}
