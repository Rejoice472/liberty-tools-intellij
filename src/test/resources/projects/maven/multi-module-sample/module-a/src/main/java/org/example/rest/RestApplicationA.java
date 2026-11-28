package org.example.rest;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

/**
 * JAX-RS Application config for Module A using:
 *  - Jakarta REST (JAX-RS) 4.0 (EE 11)
 */
@ApplicationPath("/api-a")
public class RestApplicationA extends Application {
}
