package edu.uptc.model;

import jakarta.persistence.*;

@Entity
@Table(name = "acts")
public class Act {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String description;

    @OneToOne
    private Meet meet;

    public Act() {
    }

    public Act(String description, Meet meet ) {
        this.description = description;
        this.meet = meet;
        meet.setAct( this );
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Meet getMeet() {
        return meet;
    }

    public void setMeet(Meet meet) {
        this.meet = meet;
    }

    @Override
    public String toString() {
        return "Act{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", act=" + meet + "\n" +
                '}';
    }
}
