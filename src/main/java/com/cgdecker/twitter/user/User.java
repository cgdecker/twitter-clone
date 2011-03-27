package com.cgdecker.twitter.user;

import com.google.common.base.Objects;
import com.google.common.collect.Sets;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 * @author cgdecker@gmail.com (Colin Decker)
 */
@Entity
public class User {
  @Id @GeneratedValue private Long id;

  @Column(unique = true) private String username;
  private String password; // TODO: encrypt

  private String firstName;
  private String lastName;

  @Column(unique = true) private String email;

  private String location;
  private String about;

  @Type(type = "org.joda.time.contrib.hibernate.PersistentDateTime")
  private DateTime joinedDate;

  @ManyToMany private Set<User> following = Sets.newHashSet();

  @ManyToMany(mappedBy = "following", cascade = CascadeType.ALL)
  private Set<User> followers = Sets.newHashSet(); // inverse

  public User() {}

  public User(String username, String password, String firstName, String lastName, String email,
              DateTime joinedDate) {
    this.username = username;
    this.password = password;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.joinedDate = joinedDate;
  }

  public Long getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getName() {
    return firstName + " " + lastName;
  }

  public String getEmail() {
    return email;
  }

  public DateTime getJoinedDate() {
    return joinedDate;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String getAbout() {
    return about;
  }

  public void setAbout(String about) {
    this.about = about;
  }

  public void setJoinedDate(DateTime joinedDate) {
    this.joinedDate = joinedDate;
  }

  public Set<User> getFollowing() {
    return following;
  }

  public Set<User> getFollowers() {
    return followers;
  }

  public void setFollowers(Set<User> followers) {
    this.followers = Sets.newHashSet(followers);
  }

  public void followUser(User user) {
    following.add(user);
  }

  public void unfollowUser(User user) {
    following.remove(user);
  }

  public boolean isFollowing(User user) {
    return following.contains(user);
  }

  public boolean validatePassword(String password) {
    return this.password.equals(password);
  }

  @Override public boolean equals(Object obj) {
    if (!(obj instanceof User)) return false;
    User other = (User) obj;
    return Objects.equal(id, other.id);
  }

  @Override public int hashCode() {
    return id == null ? 0 : id.hashCode();
  }

  @Override public String toString() {
    return Objects.toStringHelper(this)
        .add("id", id)
        .add("username", username)
        .add("password", password)
        .add("firstName", firstName)
        .add("lastName", lastName)
        .add("email", email)
        .add("joinedDate", joinedDate)
        .toString();
  }
}
