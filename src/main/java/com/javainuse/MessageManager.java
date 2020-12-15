package com.javainuse;

import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.*;

/**
 * The type Message manager.
 */
@Component
public class MessageManager {
    private final Map<Integer, WebSocketMessage> map;

    private final List<MessageChangeListener> listeners;

    private final ThreadPoolExecutor notifyPool;

    private MessageManager() {
        map = new ConcurrentHashMap<>();
        listeners = new CopyOnWriteArrayList<>();
        notifyPool = new ThreadPoolExecutor(1, 5, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<>(100));
    }

    /**
     * Add.
     *
     * @param id  the id
     * @param msg the msg
     */
    public void add(Integer id, WebSocketMessage msg) {
        map.put(id, msg);
        notifyListeners();
    }

    /**
     * Remove.
     *
     * @param id the id
     */
    public void remove(Integer id) {
        map.remove(id);
        notifyListeners();
    }

    /**
     * Replace.
     *
     * @param id  the id
     * @param msg the msg
     */
    public void replace(Integer id, WebSocketMessage msg) {
        map.replace(id, msg);
        notifyListeners();
    }

    /**
     * Gets all.
     *
     * @return the all
     */
    public Set<Integer> getAll() {
        return map.keySet();
    }

    /**
     * Register listener.
     *
     * @param listener the listener
     */
    public void registerListener(MessageChangeListener listener) {
        listeners.add(listener);
    }

    /**
     * Remove listener.
     *
     * @param listener the listener
     */
    public void removeListener(MessageChangeListener listener) {
        listeners.remove(listener);
    }

    private void notifyListeners() {
        notifyPool.submit(() -> listeners.forEach(MessageChangeListener::notifyMessageChange));
    }
}