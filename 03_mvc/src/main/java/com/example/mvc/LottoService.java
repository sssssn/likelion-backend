package com.example.mvc;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.random.RandomGenerator;

@Service
public class LottoService {
    private int hits = 0;
    private final List<List<Integer>> history = new ArrayList<>();

    // 누군가가 방문했을 때 호출하는 메서드
    public int addHit() {
        hits++;
        return hits;
        // return ++hits;
    }

    // lotto 메서드 만들기
    public List<Integer> nextWinningNumber() {
        List<Integer> winningNums = new ArrayList<>();
        RandomGenerator random = new Random();
        for (int i = 0; i < 6; i++) {
            // 임의 정수를 반환하는 메서드
            winningNums.add(random.nextInt(1, 46));
        }

        history.add(winningNums);
        return winningNums;
    }

    // history 메서드 추가
    public List<List<Integer>> getHistory() {
        return this.history;
    }
}
