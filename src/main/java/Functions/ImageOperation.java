package Functions;

import java.lang.reflect.InvocationTargetException;

public interface ImageOperation {
    float[] execute(float[] rgb) throws InvocationTargetException, IllegalAccessException;
}
