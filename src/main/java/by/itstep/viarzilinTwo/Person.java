package by.itstep.viarzilinTwo;

import javax.persistence.*;

@Entity
@Table(name = "PERSONTWO")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name")
    String name;

    public Person() {}

    public Person(int id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return "Person {id = " + String.valueOf(id) + ", name = '" + name + "'}";
    }
}


