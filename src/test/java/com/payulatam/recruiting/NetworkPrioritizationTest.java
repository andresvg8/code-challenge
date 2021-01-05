package com.payulatam.recruiting;

import org.junit.Assert;
import org.junit.Test;

public class NetworkPrioritizationTest {

    @Test
    public void testOrderByResponseTime() {
        NetworkPrioritization networkPrioritization = new NetworkPrioritization();

        int[] expected = new int[]{5, 1, 3, 0, 4, 2};

        int[] result = networkPrioritization.prioritizeNetwork(
                new int[]{20, 15, 100, 16, 50, 9},
                new int[]{50, 60,  30, 55, 40, 70},
                NetworkPrioritization.Criteria.RESPONSE_TIME);

        Assert.assertArrayEquals(expected, result);
    }

    @Test
    public void testOrderByCost() {
        NetworkPrioritization networkPrioritization = new NetworkPrioritization();

        int[] expected = new int[]{2, 4, 0, 3, 1, 5};

        int[] result = networkPrioritization.prioritizeNetwork(
                new int[]{20, 15, 100, 16, 50, 9},
                new int[]{50, 60,  30, 55, 40, 70},
                NetworkPrioritization.Criteria.COST);

        Assert.assertArrayEquals(expected, result);
    }

    @Test
    public void testOrderByResponseTimeAndCost() {
        NetworkPrioritization networkPrioritization = new NetworkPrioritization();

        int[] expected = new int[]{5, 3, 1, 0, 4, 2};

        int[] result = networkPrioritization.prioritizeNetwork(
                new int[]{20, 15, 100, 15, 50, 9},
                new int[]{50, 60,  30, 55, 40, 70},
                NetworkPrioritization.Criteria.RESPONSE_TIME);

        Assert.assertArrayEquals(expected, result);
    }

}
