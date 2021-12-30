package me.luligabi.magicfungi.common.misc.component;

import dev.onyxstudios.cca.api.v3.component.Component;

public interface FloatComponent extends Component {

    float getValue();

    void setValue(float value);

    float increaseBy(float increase);

    float decreaseBy(float decrease);

}