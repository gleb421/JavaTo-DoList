package org.example.webapplication.repos;

import org.example.webapplication.domain.Task;
import org.springframework.data.repository.CrudRepository;


public interface TasksRepository extends CrudRepository<Task, Integer> {

}
