package org.example.week3;

public class Organism {
    public String name;
    public String specy;
    public String livingfield;

    // 생성자
    public Organism(String name, String specy, String livingfield) {
        this.name = name;
        this.specy = specy;
        this.livingfield = livingfield;
    }

    // 정보 출력
    public String displayInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append(name+",");
        sb.append(specy + ",");
        sb.append(livingfield);
        return sb.toString();
    }

}
