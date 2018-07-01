package cz.brazda.cookit.common.dto;

import java.util.Date;
import java.util.Objects;

/**
 * DTO object for UserEvent entity
 * Created by Bohumil Brázda on 14.5.2017.
 */
public class UserEventDto implements EntityDto {

    private Long id;
    private Date eventTime;
    private AuthorDto author;

    public void setId(Long id) {
        this.id = id;
    }

    public void setEventTime(Date eventTime) {
        this.eventTime = eventTime;
    }

    public void setAuthor(AuthorDto author) {
        this.author = author;
    }

    public UserEventDto(Long id, Date eventTime, AuthorDto author) {
        this.id = id;
        this.eventTime = eventTime;
        this.author = author;
    }

    public UserEventDto() {
    }

    @Override
    public Long getId() {
        return id;
    }

    public Date getEventTime() {
        return eventTime;
    }

    public AuthorDto getAuthor(){
        return author;
    }


    @Override
    public String toString() {
        return "UserEventDto{" +
                "author='" + author + '\'' +
                ", date='" + eventTime + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEventDto that = (UserEventDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(eventTime, that.eventTime) &&
                Objects.equals(author, that.author);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, eventTime, author);
    }
}
