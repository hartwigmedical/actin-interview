package datamodel.clinical;

import java.time.LocalDate;

import org.immutables.value.Value;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Value.Immutable
@Value.Style(passAnnotations = { NotNull.class, Nullable.class })
public abstract class BodyWeight {

    @NotNull
    public abstract LocalDate date();

    public abstract double value();

    @NotNull
    public abstract String unit();
}
