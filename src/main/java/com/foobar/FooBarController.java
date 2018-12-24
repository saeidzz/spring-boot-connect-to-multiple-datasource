package com.foobar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foobar.bar.domain.Bar;
import com.foobar.bar.repo.BarRepository;
import com.foobar.foo.domain.Foo;
import com.foobar.foo.repo.FooRepository;

@RestController
public class FooBarController {

  private final FooRepository fooRepo;
  private final BarRepository barRepo;

  @Autowired
  FooBarController(FooRepository fooRepo, BarRepository barRepo) {
    this.fooRepo = fooRepo;
    this.barRepo = barRepo;
  }

  @RequestMapping("/foobar")
  public String fooBar() {
    Foo foo1=new Foo("foo1");
    Foo foo2=new Foo("foo2");
    Foo foo3=new Foo("foo3");

    Bar bar1=new Bar("bar1");
    Bar bar2=new Bar("bar2");
    Bar bar3=new Bar("bar3");
    barRepo.save(bar1);
    barRepo.save(bar2);
    barRepo.save(bar3);
    foo1.setBar(bar1);
    foo2.setBar(bar2);
    foo3.setBar(bar3);
    fooRepo.save(foo1);
    fooRepo.save(foo2);
    fooRepo.save(foo3);




    return "ok";
  }

}
