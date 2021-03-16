package com.elmoledmol.www;

import java.util.List;

public class ViewModel {
    List<Integer> id;
    List<String> name;
    List<String> images;

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public ViewModel(List<Integer> id, List<String> name, List<String> images) {
        this.id = id;
        this.name = name;
        this.images = images;
    }

    public ViewModel(List<Integer> id, List<String> name) {
        this.id = id;
        this.name = name;
    }

    public List<Integer> getId() {
        return id;
    }

    public void setId(List<Integer> id) {
        this.id = id;
    }

    public List<String> getName() {
        return name;
    }

    public void setName(List<String> name) {
        this.name = name;
    }
}
