### Rules for making sure that the Object is immutable ############

##Objective##
Helps in understanding what rules are followed to make immutable objects in Java and why and how those rules are implemented.
//Custom class Person

public class Person {
	private String name;
	private Date joiningDate;
	
	public Person(String name,Date joiningDate){
		this.name=name;
		this.joiningDate=joiningDate;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name){
		this.name=name;
	}

	public Date getJoiningDate() {
		return joiningDate;
	}
	
	public void setJoiningDate(Date joiningDate){
		this.joiningDate=joiningDate;
	}
	
	@Override
	public String toString() {
		return "Person [name=" + name + ", joiningDate=" + joiningDate + "]";
	}
}
//Java client ImmutableDemo.java
public class ImmutableDemo {
public static void main(String[] args){
		Person p=new Person("Saida",new Date());
		System.out.println("Original Person:"+p);
		p.setName("Nagaraj");
		Date joiningDate=p.getJoiningDate();
		joiningDate.setDate(26);
		System.out.println("Updated Person:"+p);
	}
}

RUN#1:Output
=================
Original Person:hashCode-->773613056	Person [name=Saida, joiningDate=Tue Jun 23 12:38:59 IST 2015]
Updated Person:hashCode-->773613056	Person [name=Nagaraj, joiningDate=Fri Jun 26 12:38:59 IST 2015]

Comments:
=========
After seeing the above results, it is clear that the Person object is not immutable,
because was able to change both name and joiningDate fields. 
Now, the actual need comes, which is how to make Person object to immutable.

Rules for making objects immutable in Java:
Rule#1:Make all fields final and private.
Rule#2:Don't provide "setter" methods = methods that modify fields or objects referred to by fields.
Rule#3:Don't allow subclasses to override methods. The simplest way to do this is to declare the class as final.

After applying above three rules

public final class Person {
	final private String name;
	final private Date joiningDate;
	public Person(String name,Date joiningDate){
		this.name=name;
		this.joiningDate=joiningDate;
	}
	public String getName() {
		return name;
	}
	public Date getJoiningDate() {
		return joiningDate;
	}
	@Override
	public String toString() {
		return "hashCode-->"+this.hashCode()+"\tPerson [name=" + name + ", joiningDate=" + joiningDate + "]";
	}
}

public class ImmutableDemo {
public static void main(String[] args){
		Person p=new Person("Saida",new Date());
		System.out.println("Original Person:"+p);
		//str is a local variable no links with name field of Person object, so even though you make updates to local variable it has no impact to  the Person object
		String str=p.getName();
		// You can do whatever, it has no impact with name field of Person object, verify the output section for results
		str="Nagaraj";
		Date joiningDate=p.getJoiningDate();
		joiningDate.setDate(26);
		System.out.println("Updated Person:"+p);
	}
}

RUN#2:Output
=================
Original Person:hashCode-->856481473	Person [name=Saida, joiningDate=Tue Jun 23 12:31:10 IST 2015]
Updated Person:hashCode-->856481473	Person [name=Saida, joiningDate=Fri Jun 26 12:31:10 IST 2015]

Comments:
===========
Even after applying above 3 rules Person object is still not immutable as per the above output

Rule#4: If the instance fields include references to mutable objects, don't allow those objects to be changed:
        in this case we have two fields
        name:String --: String is immutable so there is no problem with name field
        joinigDate:Date --: java.util.Date is a mutable object, thats' the reason we are able to modify joiningDate as shown above
        
        Now, how to tackle this situation:
        #1: Don't provide methods that modify the mutable objects.
          meaning, removing the getJoiningDate() of Person will solve the problem, but
          what if there is a need to keep the getJoiningDate() for the client programs to access the information and after all it's getter method
          
          ----
          ----
          let's do this....
          
         #2: Don't share references to the mutable objects.
         meaning, change the return type of getJoiningDate() method to maybe to String 
         //so, String is immutable
         String joiningDate=p.getJoiningDate();
         
         ----
         ----
         But, if you do not want to do any changes to getter method signature...
         what else can be done....
         
         #3: Never store references to external, mutable objects passed to the constructor;
         if necessary, create copies, and store references to the copies. Similarly, 
         create copies of your internal mutable objects when necessary to avoid returning the originals in your methods.
         
        so, Person class getJoiningDate() becomes as shown below
        //clone
        public Date getJoiningDate() {
			return (Date)joiningDate.clone();
		}
				(or)
		//copy via constructor
		 public Date getJoiningDate() {
			return new Date(originalDate.getTime());
		}
         
RUN#3:Output
=================     
Original Person:hashCode-->773613056	Person [name=Saida, joiningDate=Tue Jun 23 14:22:41 IST 2015]
Updated Person:hashCode-->773613056	Person [name=Saida, joiningDate=Tue Jun 23 14:22:41 IST 2015]



