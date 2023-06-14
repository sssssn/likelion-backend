package com.example.demo;

import org.springframework.stereotype.Service;

@Service
public class CountService {
    private int count;

    public int addCount() {
        return count++;
    }

    public void reset() {
        count = 0;
    }
}
