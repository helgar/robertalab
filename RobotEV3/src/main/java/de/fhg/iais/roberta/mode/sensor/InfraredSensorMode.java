package de.fhg.iais.roberta.mode.sensor;

import de.fhg.iais.roberta.inter.mode.sensor.IInfraredSensorMode;

public enum InfraredSensorMode implements IInfraredSensorMode {
    DISTANCE( "getInfraredSensorDistance", "Distance" ), SEEK( "getInfraredSensorSeek", "Seek" );

    private final String[] values;

    private InfraredSensorMode(String... values) {

        this.values = values;
    }

    @Override
    public String[] getValues() {
        return this.values;
    }

}