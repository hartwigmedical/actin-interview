package datamodel.clinical;

import java.time.LocalDate;

import org.immutables.value.Value;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Value.Immutable
@Value.Style(passAnnotations = { NotNull.class, Nullable.class })
public abstract class PatientDetails {

    @NotNull
    public abstract Gender gender();

    public abstract int birthYear();

    @NotNull
    public abstract LocalDate registrationDate();

    @Nullable
    public abstract LocalDate questionnaireDate();

    @Nullable
    public abstract String otherMolecularPatientId();

}
