package org.example.rest;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

/**
 * JAX-RS Application config for Module B using:
 *  - Jakarta REST (JAX-RS) 4.0 (EE 11)
 */
@ApplicationPath("/api-b")
public class RestApplicationB extends Application {
}
