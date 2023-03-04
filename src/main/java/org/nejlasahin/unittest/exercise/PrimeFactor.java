package org.nejlasahin.unittest.exercise;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PrimeFactor {

    private List<Integer> result;

    public List<Integer> generate(int number) {
        result = new ArrayList<>();

        if (number <= 1) {
            return Collections.emptyList();
        }

        for (int i = 2; i <= number; i++) {
            if (number <= 0) {
                return result;
            }
            if (number % i == 0) {
                result.add(i);
                number /= i;
                i = 1;
            }
        }
        return result;
    }
}