package com.cgdecker.twitter.status;

import com.cgdecker.twitter.user.User;
import com.google.common.base.Objects;
import org.hibernate.annotations.Index;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * @author cgdecker@gmail.com (Colin Decker)
 */
@Entity
public class Status {
  @Id @GeneratedValue
  private Long id;

  @Index(name = "user") @OneToOne private User author;
  private String text;
  @Type(type = "org.joda.time.contrib.hibernate.PersistentDateTime")
  private DateTime postTime;

  public Status() {}

  public Status(User author, String text) {
    this.author = author;
    this.text = text;
  }

  public Long getId() {
    return id;
  }

  public User getAuthor() {
    return author;
  }

  public void setAuthor(User author) {
    this.author = author;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text.trim();
  }


  public DateTime getPostTime() {
    return postTime;
  }

  public void setPostTime(DateTime postTime) {
    this.postTime = postTime;
  }

  @Override public boolean equals(Object obj) {
    return obj instanceof Status && Objects.equal(id, ((Status) obj).id);
  }

  @Override public int hashCode() {
    return id == null ? 0 : id.hashCode();
  }

  @Override public String toString() {
    return Objects.toStringHelper(this)
        .add("id", id)
        .add("user", author)
        .add("text", text)
        .add("postTime", postTime)
        .toString();
  }
}
