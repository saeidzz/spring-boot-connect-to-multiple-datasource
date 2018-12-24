package com.foobar.foo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.foobar.foo.domain.Foo;

import java.util.Optional;

@Repository
public interface FooRepository extends JpaRepository<Foo, Long> {

  Optional<Foo> findById(Long id);

}
