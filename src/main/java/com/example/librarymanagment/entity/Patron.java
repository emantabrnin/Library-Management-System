package com.example.librarymanagment.entity;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "patron")
public class Patron extends BaseEntity{    
    private String name;
    private String contactInformation;
    

    public Patron(UUID id,String name, String contactInformation) {
        this.id=id;
        this.name = name;
        this.contactInformation = contactInformation;
    }
    
}
