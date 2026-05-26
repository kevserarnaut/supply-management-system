package model;

import java.util.ArrayList;
import java.util.List;

public class DesignManager {
    private List<Design> designs;

    public DesignManager() {
        this.designs = new ArrayList<>();
    }

    public void addDesign(Design design) {
        designs.add(design);
    }

    public List<Design> getDesigns() {
        return new ArrayList<>(designs);
    }
} 