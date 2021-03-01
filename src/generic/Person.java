package generic;

class Person implements Comparable<Person>{
    String name;
    int score;
    Person(String name, int score) {
        this.name = name;
        this.score = score;
    }
    public String toString() {
        return this.name + "," + this.score;
    }
	@Override
	public int compareTo(Person p) {
		return this.name.compareTo(p.name);
	}
}
