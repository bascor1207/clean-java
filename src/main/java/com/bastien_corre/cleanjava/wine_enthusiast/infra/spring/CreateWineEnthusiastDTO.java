package com.bastien_corre.cleanjava.wine_enthusiast.infra.spring;

import com.bastien_corre.cleanjava.core.utils.StringUtils;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Objects;

public class CreateWineEnthusiastDTO {
    private String firstName;
    private String lastName;
    private List<String> preferredWines;

    public CreateWineEnthusiastDTO() {}

    public CreateWineEnthusiastDTO(String firstName, String lastName, List<String> preferredWines) {
        this.firstName = StringUtils.requireNonNullNorBlankElse(firstName, "");
        this.lastName = StringUtils.requireNonNullNorBlankElse(lastName, "");
        this.preferredWines = Objects.requireNonNullElse(preferredWines, List.of());
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
}
