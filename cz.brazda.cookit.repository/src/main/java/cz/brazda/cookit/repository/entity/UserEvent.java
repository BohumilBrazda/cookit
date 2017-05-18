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
    private Long eventId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Author author;

    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date eventTime;

    @Override
    public Date getDate() {
        return eventTime;
    }

    @Override
    public Long getId() {
        return eventId;
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

    public void setDate(Date eventTime){
        this.eventTime = eventTime;
    }

}
