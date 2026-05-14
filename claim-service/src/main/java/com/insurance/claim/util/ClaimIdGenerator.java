package com.insurance.claim.util;

import java.util.concurrent.ThreadLocalRandom;

public class ClaimIdGenerator {

    public static String generateClaimId() {
        long timestamp = System.currentTimeMillis();
        int random = ThreadLocalRandom.current().nextInt(100, 999);
        return timestamp + String.valueOf(random);
    }
}