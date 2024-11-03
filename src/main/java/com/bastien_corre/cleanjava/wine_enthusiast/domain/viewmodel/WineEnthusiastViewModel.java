package com.bastien_corre.cleanjava.wine_enthusiast.domain.viewmodel;

import java.util.List;

public class WineEnthusiastViewModel {
    private String id;
    private String firstName;
    private String lastName;
    private List<String> preferredWines;

    public WineEnthusiastViewModel() {}

    public WineEnthusiastViewModel(String id, String firstName, String lastName, List<String> preferredWines) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.preferredWines = preferredWines;
    }

    public String getId() {
        return id;
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
