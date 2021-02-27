package com.evatool.impact.domain.event.json;

import com.evatool.impact.domain.entity.Impact;
import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

public class ImpactJson {

    @Getter
    @Setter
    private String id;

    @Getter
    @Setter
    private String stakeholderId;

    @Getter
    @Setter
    private String dimensionId;

    @Getter
    @Setter
    private double value;

    @Getter
    @Setter
    private String description;

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        var that = (ImpactJson) o;
        return Double.compare(this.value, that.value) == 0
                && Objects.equals(this.id, that.id)
                && Objects.equals(this.stakeholderId, that.stakeholderId)
                && Objects.equals(this.dimensionId, that.dimensionId)
                && Objects.equals(description, that.description);
    }

    public boolean equals(Impact that) {
        return Double.compare(that.getValue(), value) == 0
                && Objects.equals(id, that.getId().toString())
                && Objects.equals(stakeholderId, that.getStakeholder().getId().toString())
                && Objects.equals(dimensionId, that.getDimension().getId().toString())
                && Objects.equals(description, that.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.stakeholderId, this.dimensionId, this.value, this.description);
    }
}
