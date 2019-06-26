package cz.brazda.cookit.repository.entity;


import javax.persistence.*;
import java.util.Date;

/**
 * Created by BOBES on 8.2.2015.
 */

@Entity
@Table(name = "user_event")
public class UserEvent extends Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Author author;

    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date eventTime;

    //hibernate
    public UserEvent() {
    }

    public UserEvent(Long id, Author author, Date eventTime) {
        this.id = id;
        this.author = author;
        this.eventTime = eventTime;
    }

    public UserEvent(Author author, Date eventTime) {
        this.author = author;
        this.eventTime = eventTime;
    }

    @Override
    public Date getEventTime() {
        return eventTime;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getName() {
        return author.getFullName();
    }

    public Author getAuthor(){
        return author;
    }

    public void setAuthor(Author author){
        this.author = author;
    }

    public void setEventTime(Date eventTime){
        this.eventTime = eventTime;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
