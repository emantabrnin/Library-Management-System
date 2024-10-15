package com.example.librarymanagment.service;

import com.example.librarymanagment.ValidationException;
import com.example.librarymanagment.entity.BaseEntity;
import com.example.librarymanagment.repository.BaseRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.Setter;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.*;

import java.util.*;
import java.util.stream.Collectors;

public class BaseService<T extends BaseEntity> {

    private final BaseRepository<T> repository;
    private Logger logger = LoggerFactory.getLogger(BaseService.class);
    @Setter
    private String errorMessage;

    ObjectMapper mapper = new ObjectMapper();


    public BaseService(BaseRepository<T> repository, String errorMessage) {
        this.repository = repository;
        this.errorMessage = errorMessage;
    }
    public BaseService(BaseRepository<T> repository) {
        this(repository,"general error");
    }


    public Boolean dataValidation(T data) { return true; }

    public T save(T data) throws ValidationException {
        Optional<T> obj = repository.findById(data.getId());
        if(obj.isPresent()) {
            throw new ValidationException("this id is already exist");
        }
        if(dataValidation(data)){
            T result = repository.save(data);
            logger.info("added record to DB : {}",data);
            return transform(result);
        }else {
            throw new ValidationException(errorMessage);
        }
    }

    @CachePut(value = "update", key = "#update.id")
    public T update(T data) {
        Optional<T> obj = repository.findById(data.getId());
        if(obj.isPresent()) {
           // data.setUpdatedAt(new LocalDateTime());
            repository.save(data);
            logger.info("update record with id {}",obj.get().getId());
        }else {
            throw new EntityNotFoundException(String.format("entity with id %s is not found",data.getId()));
        }
        return transform(obj.get());
    }
     @CacheEvict(value = "delete", key = "#id")
    public void delete(UUID id) {
        repository.deleteById(id);
        logger.info("delete record with id {}",id);
    }
    @Cacheable(value = "findById", key = "#id")
    public T findById(UUID id) {
        T result = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        logger.info("fetch record with id {}",id);
        return result;
    }
    @Cacheable("findAll")
    public List<T> findAll() {
        List<T> result = repository.findAll();
        logger.info("fetch all records with size {}", result.size());
        return result.stream().map(this::transform).collect(Collectors.toList());
    }
    
    @Cacheable("findAllWithPageable")
    public Page<T> findAll(Pageable page) {
        Page<T> result = repository.findAll(page);
        logger.info("fetch paged data page : {}, page size : {}",page.getPageNumber(),page.getPageSize() );
        return result.map(this::transform);
    }

    public T transform(T data) { return data; }


    public Class<T> clazz() {
        return null;
    }

}