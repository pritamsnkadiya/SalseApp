package com.example.pritam.model;

public class Product {

    String name = null;
    boolean selected = false;

    public Product(String name, boolean selected) {
        this.name = name;
        this.selected = selected;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", selected=" + selected +
                '}';
    }
}
