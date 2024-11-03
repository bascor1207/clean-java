package com.bastien_corre.cleanjava.wine_enthusiast.infra.spring;

import java.util.List;

public class WineEnthusiastPreferredWinesUpdateDTO {
    private List<String> preferredWines;

    public WineEnthusiastPreferredWinesUpdateDTO() {}

    public WineEnthusiastPreferredWinesUpdateDTO(List<String> preferredWines) {
        this.preferredWines = preferredWines;
    }

    public List<String> getPreferredWines() {
        return preferredWines;
    }
}
