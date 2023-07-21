package datamodel.clinical;

import org.jetbrains.annotations.NotNull;

public enum MedicationStatus {
    ACTIVE("Active"),
    ON_HOLD("On hold"),
    CANCELLED("Cancelled"),
    UNKNOWN("Unknown");

    @NotNull
    private final String display;

    MedicationStatus(@NotNull final String display) {
        this.display = display;
    }

    @NotNull
    public String display() {
        return display;
    }
}
