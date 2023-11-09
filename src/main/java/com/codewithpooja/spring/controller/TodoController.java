package com.codewithpooja.spring.controller;

import com.codewithpooja.spring.model.Todo;
import com.codewithpooja.spring.repository.TodoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/todo")
public class TodoController {
    @Autowired
    private TodoRepository todoRepository;

    @GetMapping("/todo/all")
    public List<Todo> getAllTodo() {
        return todoRepository.findAllTodo();
    }

    @GetMapping("/todo/{id}")
    public Todo getTodo(@PathVariable int id) {
        return todoRepository.findByIdTodo(id);
    }

    @PostMapping("/todo")
    public List<Todo> addTodo(@RequestBody Todo todo) {
        return todoRepository.insertTodo(todo);
    }

    @PutMapping("/todo")
    public List<Todo> updateTodo(@RequestBody Todo todo) {
        return todoRepository.updateTodo(todo);
    }

    @DeleteMapping("/todo/{id}")
    public List<Todo> deleteTodo(@PathVariable int id) {
        return todoRepository.deleteByIdTodo(id);
    }
}