package datamodel.clinical;

import java.time.LocalDate;

import org.immutables.value.Value;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Value.Immutable
@Value.Style(passAnnotations = { NotNull.class, Nullable.class })
public abstract class Surgery {

    @NotNull
    public abstract LocalDate endDate();

    @NotNull
    public abstract SurgeryStatus status();
}