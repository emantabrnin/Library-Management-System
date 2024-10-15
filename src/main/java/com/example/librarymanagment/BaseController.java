package com.example.librarymanagment;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.librarymanagment.entity.BaseEntity;
import com.example.librarymanagment.service.BaseService;

import java.util.List;
import java.util.UUID;

public class BaseController<T extends BaseEntity> {
    private BaseService<T> service;

    public BaseController(BaseService<T> service) {
        this.service = service;
    }


    @PostMapping
    public ResponseEntity<?> postData(@RequestBody T data) throws ValidationException {
        T result = service.save(data);
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(result);
    }

    @PutMapping
    public ResponseEntity<?> updateData(@RequestBody T data) {
        T result = service.update(data);
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(result);
    }

    @RequestMapping(value = "/{id}" , method = {RequestMethod.DELETE})
    public ResponseEntity<?> deleteData(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getData(@PathVariable UUID id) {
        T result = service.findById(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<?> getAllData(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "100") int size,
            @RequestParam(required = false, defaultValue = "createdAt") String sortBy,
            @RequestParam(required = false, defaultValue = "DESC") String direction) {
        if(size == -1) {
            List<T> result = service.findAll();
            return ResponseEntity.ok(result);
        }else {
            Page<T> result = service.findAll(PageRequest.of(page, size, direction.equals("DESC") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending()));
            return ResponseEntity.ok(result);
        }
    }

}
