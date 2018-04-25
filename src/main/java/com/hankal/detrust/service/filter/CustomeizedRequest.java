package com.hankal.detrust.service.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.util.*;

public class CustomeizedRequest extends HttpServletRequestWrapper {
    private Map<String, String> paramMap;

    public CustomeizedRequest(HttpServletRequest request) throws IOException {
        super(request);
        paramMap = getParameterMap(request);
    }

    public String getParameter(String name) {
        return paramMap.get(name);
    }

    public String updateParameter(String name, String value) {
        return paramMap.put(name, value);
    }

    @SuppressWarnings("rawtypes")
    public static Map<String, String> getParameterMap(HttpServletRequest request) {
        // 参数Map
        Map properties = request.getParameterMap();
        // 返回值Map
        Map<String, String> returnMap = new HashMap<String, String>();
        Iterator entries = properties.entrySet().iterator();
        Map.Entry entry;
        String name = "";
        String value = "";
        while (entries.hasNext()) {
            entry = (Map.Entry) entries.next();
            name = (String) entry.getKey();
            Object valueObj = entry.getValue();
            if (null == valueObj) {
                value = "";
            } else if (valueObj instanceof String[]) {
                String[] values = (String[]) valueObj;
                for (int i = 0; i < values.length; i++) {
                    value = values[i] + ",";
                }
                value = value.substring(0, value.length() - 1);
            } else {
                value = valueObj.toString();
            }
            returnMap.put(name, value);
        }
        return returnMap;
    }

    @Override
    public Enumeration<String> getHeaders(String name) {
        if (null != name && name.equals("content-type")) {
            return new Enumeration<String>() {
                private boolean hasGetted = false;

                @Override
                public String nextElement() {
                    if (hasGetted) {
                        throw new NoSuchElementException();
                    } else {
                        hasGetted = true;
                        return "application/json;charset=utf-8";
                    }
                }

                @Override
                public boolean hasMoreElements() {
                    return !hasGetted;
                }
            };
        }
        return super.getHeaders(name);
    }
}
