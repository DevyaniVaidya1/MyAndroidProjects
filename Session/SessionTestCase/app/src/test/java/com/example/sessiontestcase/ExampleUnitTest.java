package com.example.sessiontestcase;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test

    public void addition_isCorrect() {
        Employee employee =new Employee();
        employee.setName("deu");

       assert employee!=null;
        assertEquals("deu",employee.getName());
    }
}