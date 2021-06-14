package models;

import javax.persistence.*;

import io.ebean.Finder;
import io.ebean.Model;
import io.ebean.annotation.*;
import io.ebean.annotation.NotNull;
import play.data.validation.Constraints.*;

import java.sql.Timestamp;

@Entity
@Table(name = "board")
public class MyBoard extends Model {

    @Id
    @Column(name ="id")
    public Integer id;

    @Column(name ="name")
    @Required(message="名前を入力してください")
    public String name;

    @Column(name ="email")
    public String email;

    @Column(name ="msg")
    @Required(message="本文を入力してください")
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

    public MyBoard(){

    }
    //Finderクラス
    public static Finder<Integer, MyBoard> finder = new Finder<Integer, MyBoard>(MyBoard.class);

    public Integer getId(){
        return this.id;
    }
    public void setId(Integer _id){
        this.id = _id;
    }
    public String getMessage(){
        return this.message;
    }
    public void setMessage(String _message){
        this.message = _message;
    }
    public String getName(){
        return this.name;
    }
    public void setName(String _name){
        this.name = _name;
    }
    public String getEmail(){
        return this.email;
    }
    public void setEmail(String _email){
        this.email = _email;
    }
    public Timestamp getPostedAt(){
        return this.postedAt;
    }
    public void setPostedAt(Timestamp _postedAt){
        this.postedAt = _postedAt;
    }
    public Timestamp getUpdatedAt(){
        return this.updatedAt;
    }
    public void setUpdatedAt(Timestamp _updatedAt){
        this.postedAt = _updatedAt;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean _deleted) {
        this.deleted = _deleted;
    }
}