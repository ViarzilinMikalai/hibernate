package by.itstep.hibrnateLessons2020.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "cars")
public class Cars {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String color;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
    private User user;

    public Long getId() {
        return id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public Cars() {
    }

    public Cars(String color, User user) {
        this.color = color;
        this.user = user;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cars)) return false;
        Cars cars = (Cars) o;
        return Objects.equals(color, cars.color) &&
                Objects.equals(user, cars.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, user);
    }
}
