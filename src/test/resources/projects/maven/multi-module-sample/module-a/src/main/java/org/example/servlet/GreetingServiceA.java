package org.example.servlet;

/**
 * Simple service for Module A.
 */
public class GreetingServiceA {

    public String greet(String name) {
        return "Hello from Module A, " + (name != null ? name : "World") + "!";
    }
}
