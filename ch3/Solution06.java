/* 
3.6 Animal Shelter:
	An animal shelter, which holds only dogs and cats, operates on a sctricly 
	"first in, first out" basis. People must adopt either the "oldest" (based
	on arrival time) of all animals at the shelter, or they can select whether
	they would prefer a dog or a cat (and will receive the oldest animal of 
	that type). They cannot select which specific animal they would like. Create
	the data structures to maintain this system and implement operations such
	as enqueue, dequeueAny, dequeueDog, and dequeueCat. You may use the built-in
	LinkedList data structure.
*/

import java.io.*;
import java.util.*;

/*
Solution:
	Use two seperate queues for dogs and cats. And use a timestamp with in dog and cat
	class to record its order in queue.
	Use system time as timestamp;
Time complexity: O(1) for offer and poll in the whole queue
Space Complexity: O(n) for storing timestamp
*/

class Animal {
	private long timestamp;
	private String name;

	public Animal (String name) {
		this.name = name;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public String getName() {
		return name;
	}

	public boolean isEarlier(Animal b) {
		return this.timestamp <= b.getTimestamp();
	}
}

class Dog extends Animal {
	public Dog(String name) {
		super(name);
	}
}

class Cat extends Animal {
	public Cat(String name) {
		super(name);
	}
}

class AnimalShelter {
	ArrayDeque<Dog> dogs = new ArrayDeque<Dog>();
	ArrayDeque<Cat> cats = new ArrayDeque<Cat>();

	public void offer(Animal a) {
		long curTime = System.currentTimeMillis();
		a.setTimestamp(curTime);
		if (a instanceof Dog) {
			dogs.offer((Dog) a);
		} else {
			cats.offer((Cat) a);
		}
	}

	public Animal poll() {
		//corner cases
		if (dogs.isEmpty() && cats.isEmpty()) {
			System.out.println("Shelter is empty");
			System.exit(0);
		}

		if (dogs.isEmpty()) {
			return (Animal) cats.poll();
		}
		if (cats.isEmpty()) {
			return (Animal) dogs.poll();
		}

		//not empty
		Dog dog = dogs.peek();
		Cat cat = cats.peek();
		if (dog.isEarlier(cat)) {
			return (Animal) dogs.poll();
		} else {
			return (Animal) cats.poll();
		}
	}

	public Dog pollDog() {
		if (dogs.isEmpty()) {
			System.out.println("There is no dog");
			System.exit(0);
		}
		return dogs.poll();
	}

	public Cat pollCat() {
		if (cats.isEmpty()) {
			System.out.println("There is no cat");
			System.exit(0);
		}
		return cats.poll();
	}
}

public class Solution06 {
	static public void main(String[] args) {
		System.out.println("----------- 3.6 Animal Shelter -----------");
		testCase();
	}

	static private void testCase() {
		System.out.println("----------- Test case 1 : -----------");
		AnimalShelter shelter = new AnimalShelter();

		Dog a = new Dog("a");
		Dog b = new Dog("b");
		Dog c = new Dog("c");

		Cat x = new Cat("x");
		Cat y = new Cat("y");
		Cat z = new Cat("z");

		//offer animals in order a, b, x, c, y, z
		shelter.offer(a);
		shelter.offer(b);
		shelter.offer(x);
		shelter.offer(c);
		shelter.offer(y);
		shelter.offer(z);

		System.out.println("Animals in the queue: ");
		System.out.println("    a(dog), b(dog), x(cat), c(dog), y(cat), z(cat)");

		Animal she = shelter.poll();
		System.out.println("Call poll() get the animal (should be a): " + she.getName());

		she = shelter.pollCat();
		System.out.println("Call pollCat() get the animal (should be x): " + she.getName());

		she = shelter.poll();
		System.out.println("Call poll() get the animal (should be b): " + she.getName());
	}

}