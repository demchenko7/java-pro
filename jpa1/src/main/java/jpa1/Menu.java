package jpa1;

import javax.persistence.*;

@Entity
@Table(name="menu")

public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private long id;

    @Column(nullable = false)
    private String name;
    private int cost;
    private int weight;
    private int discount;

    public Menu() {}

    public Menu(String name, int cost, int weight, int discount) {
        this.name = name;
        this.cost = cost;
        this.weight = weight;
        this.discount = discount;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getCost() {
        return cost;
    }
    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getWeight() {
        return weight;
    }
    public void setWeight(int cost) {
        this.weight = weight;
    }

    public int getDiscount() {
        return discount;
    }
    public void setDiscount(int discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "menu{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", weight=" + weight +
                ", discount=" + discount +
                '}';
    }
}
