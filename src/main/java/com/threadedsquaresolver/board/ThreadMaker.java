package com.threadedsquaresolver.board;

import java.util.*;

import com.threadedsquaresolver.shapes.*;

public class ThreadMaker {

    public TetrisBoard ansBoard;
    public List<TetrisBoard> solutionList;
    // public volatile boolean found = false;

    public ThreadMaker(ArrayList<TetrisShape> shapes) {
        ArrayList<ArrayList<TetrisShape>> results = permutate(shapes);
        solutionList = Collections.synchronizedList(new ArrayList<>());
        for (ArrayList<TetrisShape> p_shapes : results) {
            for (int i = 0; i < p_shapes.size(); i++) {
                p_shapes.get(i).setId(i);
            }
            TetrisBoardSolver solver = new TetrisBoardSolver(p_shapes, solutionList);
            solver.start();
        }
    }

    private ArrayList<ArrayList<TetrisShape>> permutate(ArrayList<TetrisShape> shapes) {
        ArrayList<ArrayList<TetrisShape>> results = new ArrayList<ArrayList<TetrisShape>>();
        dfs(shapes, results, new ArrayList<>());
        return results;
    }

    private void dfs(ArrayList<TetrisShape> nums, ArrayList<ArrayList<TetrisShape>> results,
            ArrayList<TetrisShape> result) {
        if (nums.size() == result.size()) {
            ArrayList<TetrisShape> temp = new ArrayList<>(result);
            if (!notUnique(results, temp)) {
                results.add(temp);
            }
        }
        for (int i = 0; i < nums.size(); i++) {
            if (!result.contains(nums.get(i))) {
                result.add(nums.get(i));
                dfs(nums, results, result);
                result.remove(result.size() - 1);
            }
        }
    }

    private boolean notUnique(ArrayList<ArrayList<TetrisShape>> results, ArrayList<TetrisShape> result) {
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