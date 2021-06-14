package models;

import javax.persistence.*;
import javax.validation.constraints.*;

import io.ebean.Finder;
import io.ebean.Model;
import io.ebean.annotation.*;
import io.ebean.annotation.NotNull;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "board")
public class MyBoard extends Model {

    @Id
    @Column(name ="id")
    public Integer id;

    @Column(name ="name")
    public String name;

    @Column(name ="email")
    public String email;

    @Column(name ="msg")
    public String message;

    @CreatedTimestamp
    @Column(name ="posted_at")
    public Timestamp postedAt;

    @UpdatedTimestamp
    @Column(name ="updated_at")
    public Timestamp updatedAt;

    @NotNull
    @SoftDelete
    @Column(name ="deleted")
    public Boolean deleted;


    public MyBoard(){};

    private static Finder<Integer, MyBoard> find = new Finder<Integer, MyBoard>(MyBoard.class);

}