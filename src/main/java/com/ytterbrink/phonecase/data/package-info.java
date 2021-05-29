/**
 * This package contains the adapters
 * who conform to the data-layer ports defined by the domain.
 * Each adapter provides one entrypoint into the data layer.
 * There is no conditionals in the adapters,
 * to avoid the need for testing.
 * The integration tests that exist in the data test package
 * is to make sure that custom queries function as expected.
 */
package com.ytterbrink.phonecase.data;
