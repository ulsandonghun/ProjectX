package org.example.week3;

import java.util.ArrayList;
import java.util.List;

public class LifeNest {

    private List<Organism> organismList = new ArrayList<>();
    //검색기능 추가
    public Organism searchOrganismByName(String name) {
        for (Organism o : organismList) {
            if (o.name.equals(name)) {
                System.out.println(o.name + "은 " + o.specy + "이며, " + o.livingfield + "에 서식합니다.");
                return o;
            }
        }
       return null;
    }

    public Boolean addOrganism(Organism organism) {
        if (this.organismList.add(organism)) {
            System.out.println("[LifeNest] "+organism.name+"가 추가되었습니다.");
            return true;
        }else {
            return false;
        }
    }

    public Boolean deleteOrganism(Organism organism) {
        if (organismList.remove(organism)) {
            System.out.println("[LifeNest] " + organism.name + "가 삭제되었습니다.");
            return true;
        } else {
            return false;
        }
    }

    public void printAllOrganisms() {
        System.out.println("전체 동식물 목록 출력 :");
        for (int i = 0; i < organismList.size(); i++) {
            Organism organism = organismList.get(i);
            System.out.println((i + 1) +". "+ organism.displayInfo());
        }
    }

    public Organism changeField(String name, String changefield) {
        for (int i = 0; i < organismList.size(); i++) {
            Organism organism = organismList.get(i);
            if (organism.name.equals(name)) {
                organismList.get(i).livingfield=changefield;
                return organism;
            }
        }
        return null;
    }

}
