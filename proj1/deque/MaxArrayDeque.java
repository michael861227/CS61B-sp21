package deque;

import java.util.Comparator;

/**
 * @Author Michael Chou
 */

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> comp;
    public MaxArrayDeque(Comparator<T> c) {
        super();
        comp = c;
    }

    /**
     * Returns the maximum element in the deque as governed by the
     * previously given Comparator.
     *
     * If the MaxArrayDeque is empty, simply return null.
     */
    public T max() {
        return max(comp);
    }

    /**
     * Returns the maximum element in the deque as governed by the
     * parameter Comparator c.
     *
     * If the MaxArrayDeque is empty, simply return null.
     */
    public T max(Comparator<T> c) {
        if (isEmpty()) {
            return null;
        }

        T maxItem = this.get(0);
        for (int i = 0; i < this.size(); i++) {
            if (c.compare(get(i), maxItem) > 0) {
                maxItem = get(i);
            }
        }

        return maxItem;
    }
}
