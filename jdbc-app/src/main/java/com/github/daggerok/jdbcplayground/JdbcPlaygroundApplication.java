package com.github.daggerok.jdbcplayground;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@NoArgsConstructor
@Accessors(chain = true)
class User {
  String id;
  String name;
}

@Data
@NoArgsConstructor
@Accessors(chain = true)
class Message {
  Long id;
  String name;
  String body;
}

@Slf4j
@Service
@RequiredArgsConstructor
class FirstBloodService implements ApplicationRunner {

  final JdbcTemplate jdbcTemplate;

  @Override
  public void run(ApplicationArguments args) throws Exception {

    System.out.println();
    System.out.println();

    final List<User> users = jdbcTemplate.query(" select u.* from users u ",
        (rs, rowNum) -> new User()
            .setId(rs.getString("id"))
            .setName(rs.getString("name")));

    users.stream()
        .map(String::valueOf)
        .forEach(e -> log.info("{}", e));

    System.out.println();
    System.out.println();
  }
}

@SpringBootApplication
public class JdbcPlaygroundApplication {

  public static void main(String[] args) {
    SpringApplication.run(JdbcPlaygroundApplication.class, args);
  }
}
