package org.example.week3;

public class Organism {
    public String name;
    public String specy;
    public String livingfield;

    public Organism(String name, String specy, String livingfield) {
        this.name = name;
        this.specy = specy;
        this.livingfield = livingfield;
    }

    public String displayInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append(name+",");
        sb.append(specy + ",");
        sb.append(livingfield);
//        System.out.println(sb);
        return sb.toString();
    }
}
