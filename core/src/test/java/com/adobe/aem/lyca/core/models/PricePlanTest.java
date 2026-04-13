package com.adobe.aem.lyca.core.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test class for {@link PricePlan}.
 *
 * This class verifies the correctness of:
 * - Getter and Setter methods
 * - Default values of fields
 * - Handling of null values
 * - Array behavior
 * - Boolean field behavior
 */
class PricePlanTest {

    /**
     * Test to verify all setter and getter methods.
     *
     * This ensures that values set using setters
     * are correctly returned using getters.
     */
    @Test
    void testSettersAndGetters() {
        PricePlan plan = new PricePlan();

        String[] features = {"Calls", "SMS", "Data"};

        plan.setPlanTitle("Starter");
        plan.setPriceMonthly(5.0);
        plan.setPriceYearly(50.0);
        plan.setDataLimit("5GB");
        plan.setFeatures(features);
        plan.setPopular(true);
        plan.setCtaLabel("Choose Plan");
        plan.setCtaLink("/plans/starter");

        assertEquals("Starter", plan.getPlanTitle());
        assertEquals(5.0, plan.getPriceMonthly());
        assertEquals(50.0, plan.getPriceYearly());
        assertEquals("5GB", plan.getDataLimit());
        assertArrayEquals(features, plan.getFeatures());
        assertTrue(plan.isPopular());
        assertEquals("Choose Plan", plan.getCtaLabel());
        assertEquals("/plans/starter", plan.getCtaLink());
    }

    /**
     * Test to verify default values of a newly created object.
     *
     * Ensures that:
     * - Object fields are null or default initialized
     * - Primitive types have default values (0, false)
     */
    @Test
    void testDefaultValues() {
        PricePlan plan = new PricePlan();

        assertNull(plan.getPlanTitle());
        assertEquals(0.0, plan.getPriceMonthly());
        assertEquals(0.0, plan.getPriceYearly());
        assertNull(plan.getDataLimit());
        assertNull(plan.getFeatures());
        assertFalse(plan.isPopular());
        assertNull(plan.getCtaLabel());
        assertNull(plan.getCtaLink());
    }

    /**
     * Test to verify handling of null values.
     *
     * Ensures that fields accept null values
     * and getters return null accordingly.
     */
    @Test
    void testNullValues() {
        PricePlan plan = new PricePlan();

        plan.setPlanTitle(null);
        plan.setDataLimit(null);
        plan.setFeatures(null);
        plan.setCtaLabel(null);
        plan.setCtaLink(null);

        assertNull(plan.getPlanTitle());
        assertNull(plan.getDataLimit());
        assertNull(plan.getFeatures());
        assertNull(plan.getCtaLabel());
        assertNull(plan.getCtaLink());
    }

    /**
     * Test to verify behavior when features array is empty.
     *
     * Ensures that:
     * - Array is not null
     * - Length is zero
     */
    @Test
    void testEmptyFeaturesArray() {
        PricePlan plan = new PricePlan();

        plan.setFeatures(new String[]{});

        assertNotNull(plan.getFeatures());
        assertEquals(0, plan.getFeatures().length);
    }

    /**
     * Test to verify boolean field behavior.
     *
     * Ensures that:
     * - True and false values are correctly set and returned
     */
    @Test
    void testBooleanField() {
        PricePlan plan = new PricePlan();

        plan.setPopular(true);
        assertTrue(plan.isPopular());

        plan.setPopular(false);
        assertFalse(plan.isPopular());
    }
}