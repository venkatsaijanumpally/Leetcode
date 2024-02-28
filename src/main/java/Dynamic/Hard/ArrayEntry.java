package Dynamic.Hard;

import java.util.Arrays;

public class ArrayEntry {
    public int[] array;

    public ArrayEntry(int[] temp) {
        this.array = temp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArrayEntry that = (ArrayEntry) o;
        return Arrays.equals(array, that.array);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(array);
    }
}

