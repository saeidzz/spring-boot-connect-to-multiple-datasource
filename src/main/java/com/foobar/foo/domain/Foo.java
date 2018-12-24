package com.foobar.foo.domain;

import com.foobar.bar.domain.Bar;

import javax.persistence.*;

@Entity
@Table(name = "foo" ,schema = "zangeneh")
public class Foo {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "ID")
  private Long id;

  @Column(name = "FOO")
  private String foo;

  @OneToOne(targetEntity = Bar.class)
  @JoinColumn(name = "bar_id",referencedColumnName ="id")
  private Bar bar;

  public Foo(String foo) {
    this.foo = foo;
  }

  public Foo() {
    // Default constructor needed by JPA
  }

  public String getFoo() {
    return foo;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setFoo(String foo) {
    this.foo = foo;
  }

  public Bar getBar() {
    return bar;
  }

  public void setBar(Bar bar) {
    this.bar = bar;
  }
}
