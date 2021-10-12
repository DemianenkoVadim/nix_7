package ua.com.alevel.service;

import ua.com.alevel.entity.*;

import java.util.List;

public interface PrintService {

    List<Solution> findsSolutionUnsolvedProblem(java.util.List<Problem> issues);
}
