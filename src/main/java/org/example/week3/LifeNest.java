package org.example.week3;

import java.util.ArrayList;
import java.util.List;

public class LifeNest {

    private List<Organism> organismList = new ArrayList<>();

    // 이름으로 검색후 객체 반환
    private Organism findOrganismByName(String name) {
        for (Organism o : organismList) {
            if (o.name.equals(name))
                return o;
        }
        return null;
    }

    // 아름으로 검색 후 정보 출력
    public Organism searchOrganismByName(String name) {
        Organism organism = findOrganismByName(name);
        if (organism != null) {
            System.out.println(organism.name + "은 " + organism.specy + "이며, " + organism.livingfield + "에 서식합니다.");
        }
        return organism;
    }

    // Organism 객체 추가
    public void addOrganism(Organism organism) {
        this.organismList.add(organism);
        System.out.println("[LifeNest] "+organism.name+"가 추가되었습니다.");
    }

    // Organism 객체 삭제
    public void deleteOrganism(Organism organism) {
        organismList.remove(organism);
        System.out.println("[LifeNest] " + organism.name + "가 삭제되었습니다.");
    }

    // Organism 객체 출력
    public void printAllOrganisms() {
        System.out.println("전체 동식물 목록 출력 :");
        for (int i = 0; i < organismList.size(); i++) {
            Organism organism = organismList.get(i);
            System.out.println((i + 1) +". "+ organism.displayInfo());
        }
    }

    // 서식지 수정
    public Organism changeField(String name, String changefield) {
        Organism organism = findOrganismByName(name);
        //findOrganismByName 함수가 반환하는 값은 참조값(객체의 주소)
        //이므로 해당 객체의 값 필드값 변경시 원본 객체도 변경됨.
        if (organism != null) {
            organism.livingfield = changefield;
        }
        return organism;
    }

}
