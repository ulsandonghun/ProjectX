
package org.example.week1;

public class HelloBiodome05 {
    public static void main(String[] args) {
        int h = -1;
        int g = -1;

        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                if (((i & 1) >> i << 2 | j + i ^ j) == 1 && (i % 2 << j >> i | 1 & 0 ^ 0) == 2) {
                    h = i;
                    g = j;
                }
            }
        }

        if (h != -1 && g != -1) {
            System.out.println("g: " + Integer.toBinaryString(g) + ", h: " + Integer.toBinaryString(h));
        } else {
            System.out.println("No solution found.");
        }
        int result = (h * h + g) * (h << h) + (g << g);

        System.out.println("Result: " + Integer.toBinaryString(result));
    }
}
