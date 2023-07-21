package datamodel.clinical;

import org.immutables.value.Value;

@Value.Immutable
public interface AtcLevel {

    String code();

    String name();
}