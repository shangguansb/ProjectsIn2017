import com.google.common.base.Optional;
import com.google.common.base.Preconditions;

import javax.annotation.CheckForNull;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by kingsley.zhang on 2017/3/1.
 */
public class LruCache<K, V> {
    private Map recentQueueLinkedMap = null;
    private int size;    //当前cache的大小
    private int maxSize; //cache最大大小

    private LruCache(int maxSize) {
        this.recentQueueLinkedMap = new LinkedHashMap<K, V>(0, 0.75f, true);
        this.maxSize = maxSize;
    }

    public LruCache creat(int maxSize) {
        return new LruCache<K, V>(maxSize);
    }

    public void put(K key, V value) {
        Preconditions.checkNotNull(key);
        synchronized (this) {
            recentQueueLinkedMap.put(key, value);
            size++;
        }
        checkSize(size);
    }

    private void checkSize(int size) {
        while (true) {
            synchronized (this) {
                 if (size < 0 || (recentQueueLinkedMap.isEmpty() && size != 0)) {
                    throw new IllegalStateException();
                }
                if (size <= maxSize) {
                    break;
                }
                Map.Entry<K, V> entry =
                        (Map.Entry<K, V>) recentQueueLinkedMap.entrySet().iterator().next();
                if (entry == null) {
                    break;
                }
                removeElement(entry.getKey());
            }
        }
    }

    public int getSize() {
        return size;
    }

    public V get(K key) {
        Preconditions.checkNotNull(key);
        synchronized (this) {
            V value = (V) recentQueueLinkedMap.get(key);
            return value;
        }
    }

    public boolean removeElement(K key) {
        Preconditions.checkNotNull(key);
        recentQueueLinkedMap.remove(key);
        size--;
        return false;
    }

}
