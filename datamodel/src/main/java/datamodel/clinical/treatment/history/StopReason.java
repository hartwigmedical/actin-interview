package datamodel.clinical.treatment.history;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public enum StopReason {
    PROGRESSIVE_DISEASE,
    TOXICITY;

    @Nullable
    public static StopReason createFromString(@NotNull String input) {
        String uppercase = input.toUpperCase();
        if (uppercase.contains("PD") || uppercase.contains("PROGRESSIVE")) {
            return PROGRESSIVE_DISEASE;
        } else if (uppercase.contains("TOXICITY")) {
            return TOXICITY;
        } else {
            return null;
        }
    }
}
