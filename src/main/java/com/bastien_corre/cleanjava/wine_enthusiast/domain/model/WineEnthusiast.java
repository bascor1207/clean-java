package com.bastien_corre.cleanjava.wine_enthusiast.domain.model;

import com.bastien_corre.cleanjava.core.domain.model.BaseEntity;
import com.bastien_corre.cleanjava.core.utils.ListUtils;
import com.bastien_corre.cleanjava.core.utils.StringUtils;
import com.bastien_corre.cleanjava.wine_enthusiast.application.usecases.UpdateWineEnthusiastInfosCommand;
import com.bastien_corre.cleanjava.wine_enthusiast.application.usecases.UpdateWineEnthusiastWineRelevantInfosCommand;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "wine_enthusiasts")
public class WineEnthusiast extends BaseEntity {
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @ElementCollection
    private List<String> preferredWines;

    public WineEnthusiast() {}

    public WineEnthusiast(String id, String firstName, String lastName, List<String> preferredWines) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.preferredWines = preferredWines;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public List<String> getPreferredWines() {
        return preferredWines;
    }

    public void changePersonalInfos(UpdateWineEnthusiastInfosCommand updateWineEnthusiastInfosCommand) {
        this.firstName = StringUtils.requireNonNullNorBlankElse(updateWineEnthusiastInfosCommand.firstName(), getFirstName());
        this.lastName = StringUtils.requireNonNullNorBlankElse(updateWineEnthusiastInfosCommand.lastName(), getLastName());
    }

    public void changeWineRelevantInfos(UpdateWineEnthusiastWineRelevantInfosCommand updateWineEnthusiastWineRelevantInfosCommand) {
        this.preferredWines = ListUtils.requireNonNullNorEmptyElse(updateWineEnthusiastWineRelevantInfosCommand.preferredWines(), getPreferredWines());
    }
}
