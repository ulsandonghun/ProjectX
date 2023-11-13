package org.example.week3;

public class BiodomeFamily01 {
    public static void main(String[] args) {
        Organism organism = new Organism("펭귄", "동물", "남극");
        Organism organism1 = new Organism("코알라", "동물", "호주");
        Organism organism2 = new Organism("선인장", "식물", "사막");
        Organism organism3 = new Organism("페퍼민트", "식물", "정원");

        LifeNest lifeNest = new LifeNest();
        lifeNest.addOrganism(organism);
        lifeNest.addOrganism(organism1);
        lifeNest.addOrganism(organism2);
        lifeNest.addOrganism(organism3);
        System.out.println();
        lifeNest.printAllOrganisms();
        System.out.println();
        lifeNest.deleteOrganism(organism1);
        lifeNest.deleteOrganism(organism2);

        lifeNest.changeField("펭귄", "해변");
        System.out.println();

        lifeNest.printAllOrganisms();
        System.out.println();
        //검색기능
        lifeNest.searchOrganismByName("펭귄");

    }
}
