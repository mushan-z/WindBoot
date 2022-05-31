package com.wind.service;

import java.util.concurrent.Future;

public interface StudentService {

    Future<String> getStudentName(Long id);

    Future<Integer> getStudentAge(Long id);
}
