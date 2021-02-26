package com.evatool.variants.entities;

import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity(name = "VARIANTS_REQUIREMENT")
@Getter
public class VariantsRequirement extends RepresentationModel<VariantsRequirement> {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private UUID id;

    private String title;
}