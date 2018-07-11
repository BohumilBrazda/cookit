package client.repository.model;


import java.util.Date;

/**
 * Created by BOBES on 8.2.2015.
 */
public class UserEvent extends Event {
    @Override
    public String toString() {
        return author + ", " + eventTime;
    }

    private Long id;

    private Author author;

    private Date eventTime;

    public UserEvent(Long eventId, Author author, Date eventTime) {
        this.id = eventId;
        this.author = author;
        this.eventTime = eventTime;
    }

    public UserEvent() {

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

}
