package com.threadedsquaresolver.board;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;

import com.threadedsquaresolver.shapes.*;

public class ThreadMaker {

    public TetrisBoard ansBoard;
    public List<TetrisBoard> solutionList;
    public ExecutorService executor;
    public static final int noThreads = Runtime.getRuntime().availableProcessors();

    public ThreadMaker(ArrayList<TetrisShape> shapes) throws InterruptedException {
        ArrayList<ArrayList<TetrisShape>> results = generateShapePermutations(shapes);
        Collections.shuffle(results);
        solutionList = Collections.synchronizedList(new ArrayList<>());
        executor = Executors.newFixedThreadPool(noThreads);

        for (ArrayList<TetrisShape> p_shape : results) {
            for (int i = 0; i < p_shape.size(); i++) {
                p_shape.get(i).setId(i);
            }
            try {
                executor.submit(new TetrisBoardSolver(p_shape, solutionList, executor));
            } catch (RejectedExecutionException e) {
            }
        }
        executor.awaitTermination(200, TimeUnit.MILLISECONDS);
    }

    private ArrayList<ArrayList<TetrisShape>> generateShapePermutations(ArrayList<TetrisShape> shapes) {
        ArrayList<ArrayList<TetrisShape>> results = new ArrayList<>();
        generatePermutations(shapes, results, new ArrayList<>());
        return results;
    }

    private void generatePermutations(ArrayList<TetrisShape> nums, ArrayList<ArrayList<TetrisShape>> results,
            ArrayList<TetrisShape> result) {
        if (nums.size() == result.size()) {
            ArrayList<TetrisShape> temp = new ArrayList<>();

            for (TetrisShape sh : result) {
                temp.add(sh.clone());
            }

            if (!isNotUnique(results, temp)) {
                results.add(temp);
            }
        }
        for (int i = 0; i < nums.size(); i++) {
            if (!result.contains(nums.get(i))) {
                result.add(nums.get(i));
                generatePermutations(nums, results, result);
                result.remove(result.size() - 1);
            }
        }
    }

    private boolean isNotUnique(ArrayList<ArrayList<TetrisShape>> results, ArrayList<TetrisShape> result) {
        for (ArrayList<TetrisShape> arr : results) {
            boolean match = true;
            for (int i = 0; i < arr.size(); i++) {
                if (!arr.get(i).getClass().getName().equals(result.get(i).getClass().getName())) {
                    match = false;
                    break;
                }
            }
            if (match) {
                return true; // Found a matching sequence
            }
        }
        return false;
    }
}