package com.hankal.detrust.Util;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.google.gson.Gson;
import com.hankal.detrust.vo.RequestVo;
import net.sf.json.JSONObject;
import springfox.documentation.spring.web.json.Json;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class RequestUtil {
    private static final Logger logger = LoggerFactory.getLogger("RequestUtil");

/*    public static RequestVo parse(Map map) {
        JSONObject jsonObject = JSONObject.fromObject(map);
        System.out.println("输出的结果是：" + jsonObject);

        //3、将json对象转化为json字符串
        String result = jsonObject.toString();
        System.out.println(result);

        RequestVo Jsond = (RequestVo) Json.Deseribalize<RequestVo>(result);

        return requestVo;
    }*/

    public static String resetPostData(HttpServletRequest req, String jsonStr) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader reader = req.getReader();
            reader.mark(10000);

            String line;
            do {
                line = reader.readLine();
                sb.append(line).append(jsonStr + "\n");
            } while (line != null);
            reader.reset();
            // do NOT close the reader here, or you won't be able to get the post data twice
        } catch (IOException e) {
            logger.warn("getPostData couldn't.. get the post data", e);  // This has happened if the request's reader is closed
        }

        return sb.toString();
    }

    /**
     * 描述:获取 post 请求内容
     * <pre>
     * 举例：
     * </pre>
     *
     * @param request
     * @return
     * @throws IOException
     */
    public static String getRequestPostStr(HttpServletRequest request)
            throws IOException {
        byte buffer[] = getRequestPostBytes(request);
        String charEncoding = request.getCharacterEncoding();
        if (charEncoding == null) {
            charEncoding = "UTF-8";
        }
        return new String(buffer, charEncoding);
    }

    /**
     * 方法说明 :通过获取map的方式
     */
    @SuppressWarnings("rawtypes")
    private String getParameterMap(HttpServletRequest request) {
        Map map = request.getParameterMap();
        /*String jsonStr = JSONUtils.toJSONString(map);
        System.out.println("输出的结果是：" + jsonStr);*/

        String jsonStr = new Gson().toJson(map);
        System.out.println("输出的结果是：" + jsonStr);


        String text = "";
        if (map != null) {
            Set set = map.entrySet();
            Iterator iterator = set.iterator();
            while (iterator.hasNext()) {
                Map.Entry entry = (Map.Entry) iterator.next();
                if (entry.getValue() instanceof String[]) {
                    logger.info("==A==entry的key： " + entry.getKey());
                    String key = (String) entry.getKey();
                    if (key != null && !"id".equals(key) && key.startsWith("[") && key.endsWith("]")) {
                        text = (String) entry.getKey();
                        break;
                    }
                    String[] values = (String[]) entry.getValue();
                    for (int i = 0; i < values.length; i++) {
                        logger.info("==B==entry的value: " + values[i]);
                        key += "=" + values[i];
                    }
                    if (key.startsWith("[") && key.endsWith("]")) {
                        text = (String) entry.getKey();
                        break;
                    }
                } else if (entry.getValue() instanceof String) {
                    logger.info("==========entry的key： " + entry.getKey());
                    logger.info("==========entry的value: " + entry.getValue());
                }
            }
        }
        return text;
    }

    /**
     * 描述:获取 post 请求的 byte[] 数组
     * <pre>
     * 举例：
     * </pre>
     *
     * @param request
     * @return
     * @throws IOException
     */
    public static byte[] getRequestPostBytes(HttpServletRequest request)
            throws IOException {
        int contentLength = request.getContentLength();
        if (contentLength < 0) {
            return null;
        }
        byte buffer[] = new byte[contentLength];
        for (int i = 0; i < contentLength; ) {

            int readlen = request.getInputStream().read(buffer, i,
                    contentLength - i);
            if (readlen == -1) {
                break;
            }
            i += readlen;
        }
        return buffer;
    }

}
