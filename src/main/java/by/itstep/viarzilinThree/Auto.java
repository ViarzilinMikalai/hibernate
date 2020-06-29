package by.itstep.viarzilinThree;

import javax.persistence.*;

@Entity
@Table(name = "AUTOS2")
public class Auto
{
    @Id
    @GeneratedValue    (strategy=GenerationType.AUTO)
    @Column (name="id")
    private Integer id;

    @ManyToOne (fetch=FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn (name="user_id")
    private User user;

    @Column(name = "name")
    private String name;

    public Auto()
    {
        super();
    }
    public Auto(Integer id, User user, String name)
    {
        super();
        this.id = id;
        this.user = user;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Auto{" +
                "id=" + id +
                ", user=" + user +
                ", name='" + name + '\'' +
                '}';
    }
}
