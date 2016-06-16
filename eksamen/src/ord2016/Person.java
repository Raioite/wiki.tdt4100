package ord2016;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Person implements Iterable<Person> {

	private String name;
	private Gender gender = null;
	private Person father, mother;
	private Collection<Person> children = new ArrayList<>();
	
	/**
	 * Initializes a Person with the provided name.
	 * @param name
	 */
	public Person(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name + ":" + gender;
	}
	
	public String getName() {
		return name;
	}
	
	/**
	 * @return the gender of this Person
	 */
	public Gender getGender() {
		return gender;
	}

	/**
	 * Sets the gender of this Person.
	 * @param the new gender
	 */
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
	/**
	 * @return this Person's mother
	 */
	public Person getMother() {
		return mother;
	}

	/**
	 * @return this Person's father
	 */
	public Person getFather() {
		return father;
	}

	/**
	 * @return the number if children of this Person
	 */
	public int getChildCount() {
		return children.size();
	}

	/**
	 * @param child
	 * @return if this Person has the provided Person as a child
	 */
	public boolean hasChild(Person child) {
		return children.contains(child);
	}

	@Override
	public Iterator<Person> iterator() {
		return children.iterator();
	}

	/**
	 * Adds the provided Person as a child of this Person.
	 * Also sets the child's father or mother to this Person,
	 * depending on this Person's gender.
	 * To ensure consistency, if the provided Person already
	 * has a parent of that gender,
	 * it is removed as a child of that parent.
	 * @param child
	 */
	public void addChild(Person child) {
		if (getGender() == Gender.MALE) {
			if (child.father != null) {
				child.father.children.remove(child);
			}
			child.father = this;
		} else if (getGender() == Gender.FEMALE) {
			if (child.mother != null) {
				child.mother.children.remove(child);
			}
			child.mother = this;
		}
		children.add(child);
	}
	
	/**
	 * Returns all children of this Person with the provided Gender.
	 * If gender is null, all children are returned.
	 * Can be used to get all daughters or sons of a person.
	 * @param gender
	 */
	public Collection<Person> getChildren(Gender gender) {
		Collection<Person> result = new ArrayList<>();
		for (Person child : children) {
			if (gender == null || child.getGender() == gender) {
				result.add(child);
			}
		}
		return result;
	}
}
