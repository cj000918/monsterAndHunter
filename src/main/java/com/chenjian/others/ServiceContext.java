package com.chenjian.others;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ServiceContext {

    private static final ThreadLocal<ServiceContext> LOCAL = new ThreadLocal<ServiceContext>() {
        protected ServiceContext initialValue() {
            return new ServiceContext();
        }
    };

    private final Map<String, Object> mapValue;
    private Map<String, String> mapContext = new ConcurrentHashMap();

    private ServiceContext() {
        this.mapValue = new ConcurrentHashMap();
    }

    public static ServiceContext getContext() {
        return (ServiceContext)LOCAL.get();
    }

    public static void removeContext() {
        LOCAL.remove();
    }
    public void set(String key, Object value) {
        if (value == null) {
            this.mapValue.remove(key);
        } else {
            this.mapValue.put(key, value);
        }

    }

    public void remove(String key) {
        this.mapValue.remove(key);
    }

    public Object get(String key) {
        return this.mapValue.get(key);
    }

    public Map<String, Object> getKeys() {
        return this.mapValue;
    }

    public void setAttachment(String key, String attachment) {
        if (attachment != null) {
            this.mapContext.put(this.convertKey(key), attachment);
        } else {
            this.removeAttachment(key);
        }

    }
    public String getAttachment(String key) {
        return (String)this.mapContext.get(this.convertKey(key));
    }

    private String convertKey(String key) {
        return key != null && key.startsWith("x-chenjian-context-") ? key : "x-chenjian-context-" + key;
    }

    public void removeAttachment(String key) {
        this.mapContext.remove(this.convertKey(key));
    }

    public Long getRequestUserId() {
        String userId = (String)this.get("yes.req.userId");
        if (userId == null) {
            userId = this.getAttachment("yes.req.userId");
        }

        return userId != null ? Long.valueOf(userId) : null;
    }

    public String getRequestId() {
        String reqId = (String)this.get("yes.req.requestId");
        if (reqId == null) {
            reqId = this.getAttachment("yes.req.requestId");
        }

        return reqId;
    }
}
