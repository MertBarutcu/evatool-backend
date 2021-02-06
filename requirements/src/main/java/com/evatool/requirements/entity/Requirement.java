
package com.evatool.requirements.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

@Entity
public class Requirement {

    @Id
    private UUID id = UUID.randomUUID();
    private String titel;
    private String description;
    @ManyToMany
    private Collection<RequirementsVariants> variants = new ArrayList<>();

    public Requirement() {
    }

    public Requirement(String titel, String description) {
        this.titel = titel;
        this.description = description;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Collection<RequirementsVariants> getVariants() {
        return variants;
    }

    public void setVariants(Collection<RequirementsVariants> variants) {
        this.variants = variants;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}

