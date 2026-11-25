package org.example.servlet;

/**
 * Simple service for Module B.
 */
public class GreetingServiceB {

    public String greet(String name) {
        return "Hello from Module B, " + (name != null ? name : "World") + "!";
    }
}
