/**
 * This is a spring boot application for storing and finding phone cases and phones.
 * It is built with ports and adapters, aka hexagonal design.
 * The purpose of this is to decouple the dependencies to the framework.
 * Interfaces are used to isolate dependencies, and the interfaces are owned and defined by the domain.
 * The aim is to create a loosely coupled design.
 * It also enables testing. 
 */
package com.ytterbrink.phonecase;