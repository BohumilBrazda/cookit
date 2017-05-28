package client.repository.model;


import java.util.Date;

/**
 * Created by BOBES on 8.2.2015.
 */
public class UserEvent extends Event {
    private Long eventId;

    private Author author;

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
