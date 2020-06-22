package signature_algorithm;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;


public class hk {

    public static LinkedList compare(String key,LinkedList<String>list) {
        if(list.isEmpty()) {
            list.add(key);
            return list;
        }
        for(int i = 0; i < list.size(); i++) {
            String temp = list.get(i);
            if(key.compareTo(temp) < 0) {
                list.add(i,key);
                return list;
            }
        }
        list.add(key);
        return list;
    }

    public static LinkedList sort(LinkedList list) {
        Iterator<String> it = list.iterator();
        LinkedList<String> temp = new LinkedList<String>();
        while(it.hasNext()) {
            String key = it.next();
            temp = compare(key,temp);
        }
        return temp;
    }

    public static String signString(Map<String,String> map)  {
        String result = "";
        Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
        LinkedList list = new LinkedList<String>();
        while (it.hasNext()) {
            Map.Entry<String, String> entry = it.next();
            if(!entry.getKey().equals("sign")){
                String key = entry.getKey();
                list.add(key);
            }
        }
        list = sort(list);
        for(int i = 0; i < list.size();i++) {
            Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
            while(iterator.hasNext()) {
                Map.Entry<String, String> entry = iterator.next();
                if(list.get(i).equals(entry.getKey())){
                    result +=entry.getKey()+"="+entry.getValue()+"&";
                }
            }
        }
        result = result.substring(0,result.length() - 1);
        System.out.println(result);
        return result;
    }


    /**
     * 得到私钥
     * @param key 密钥字符串（经过base64编码）
     * @throws Exception
     */
    public static PrivateKey getPrivateKey(String key) throws Exception {
        byte[] keyBytes;
        keyBytes = Base64.decodeBase64(key.getBytes());

        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
        return privateKey;
    }

    /**
     * 得到密钥字符串（经过base64编码）
     * @return
     */
    public static String getKeyString(PrivateKey key) throws Exception {
        byte[] keyBytes = key.getEncoded();
        String s = new String(Base64.encodeBase64(key.getEncoded()));
        return s;
    }

    private static String sign(PrivateKey privateKey, String message) throws Exception {
        Signature sign = Signature.getInstance("SHA1withRSA");
        sign.initSign(privateKey);
        sign.update(message.getBytes("UTF-8"));
        return new String(Base64.encodeBase64(sign.sign()));
    }



    public static void main(String[] args) throws Exception {
        Map<String,String> map = new HashMap<>();
        map.put("biz_content","{\"cmbOrderId\":\"900119022715203210134445\",\"userId\":\"N003109945\",\"merId\":\"3089991701207X7\",\"orderId\":\"9001004180329103417277\"}");
        map.put("encoding","UTF-8");
        map.put("sign","UwCQ3qXGG7J+ODlZmfT5MSLqAC0etJwb1kt6zTuMazp1MIxDA8LoLIrPTsjCxdfl8bTjQySBJIED0RBt82U3/1OTOqHQROdTVygS+V8XfdzMznyjpefl2Ev905M5klz8u7/LzrTANZ5E2mOoWeswz2sEXowjYXv5sTHe+apFX7t4Cu+eD5uevSFcZ9DYdT12jUoLzx8yol22lIHwQR0LoyOLGK5whAisXuYN4lbZIM0o1ZxJZyCXfFgIq17lD/YhRWNjpeK5t9lEfQv42R9WjkfkDZp3Xi3/q4+tSh1Q7j1yELdl5BxfZ1z4ChHP0TaMoHbtGD325A1IuLO2vdC9sw==");
        map.put("signMethod","01");
        map.put("version","0.0.1");
        String key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCc540quYC9xzCMZeFOe8UmE3W5LWrqFd/2DDSHQASxq8vmOiwFRSG2hsVXtjfmNLQNhtpTR0SGDUjkCsx+SJH0JDnOfQ2xXHasO65Rnv2wrHs64P6U0aUrMWjgapjkmLwzRV12AKNAX77MGIocpcB0KZhk+0AVc6oQCBybV65JTGu+pAyFLMJRtIP5kH3VMuXmig6VeiZAsjEewD/emxgK3cXejMQvqlNYFnCLsZ7ovAhr+bhz6SHkOws3p80O6zfQbKfLzdSVaZK8FnwNPznUxAK77bRZN0zF3V9mL9+zrarvFPD5VkcVHNLj0DRzLmr2c5TbiCigs4+I+NMfhpoLAgMBAAECggEAbM8GzoImDXV87WAZhtu+NFF6ahhc9EiHL5H3O3PhzXRdyiK9NEpkvrdnUxRCX5pc4qSJ8waRNoUv7zSt60VYMf6NN+zw+fYtNfONR30CYOq76nDtGzbnW7TADiDeNmjU2plX3uVCUPoUzmSWIpevht7xl9XE8xtq7AM0E2YSrzEADcxtqQslM0uVOf+ki1eu0/OwCz13FzPlPtnDwt2Lw9xxCxWqTgpN4oD5m6EWTqbognUIJ0EFD0dHXjrYnHXc+/Za5e+CDXYApHuhR9bifa1e4HMN084oLY+rkSXUV3+Te0APPCfbeEeqvubziDmKxxKaWUq1wPbYi4c06ZQdgQKBgQDhF7zDWgiJFTgrLGmExJRKiR/3QZN4sugYE1itdRDJmiPV4xhWPXSsND3WtqR5+0otb/hbzRa3cyl/RXV/1ZmBbE46fX2DKnmLQ1gP74iOuqWpfxjh/qpk+3kEY9aP57le/O0QEEPsJmqCsGM7XnzfNsxGAFYaDHooRbcGtv++AwKBgQCycuvRUQjV4dxTuRJuwFbmdq4odSBMu7yCS4i5I9I73d3TGZBWfiXQWFmuiPh+pf3HdvMbgyA243Uv/NGapSmNvARXm0/eEyfTxV7+GVdwLf3sSe8DQMCR1eJA9VzuS+jhCrHkFgyW3V/3ki66W8YITENlgC+VebOatfFE8i/ZWQKBgQCZ2VmhxFX1LFW53J86qgoZb+QzYdTkOJQ+cGq6FDunL/2yYYfu2g527TYfHbMJ1OH8cH22cVVHiiUg4l7PQzWqqlZF0CQLlOqCb0MvkS8rLxOv6DkfrqrUXrV2dK7gqSegbwuxYQyryg4eyWTp3UlIX/H7Hpu7LjAIeq4Anu/p9QKBgFMtpiYHM6segGi2F5VwKhF6uGs7TTb3O0MwmiZSQCiPnlpLzC/E1TNsO0FTryC5lrVnCKKGWHm9RF595eXDnr7mKM/9IRlOrH3VvhWLEmrDxVxiifpmMFzJ6ZCFzi91SrO7HHhIns2jmpv3k7hiFsi/Y5roSUXPWJyAull82jjhAoGAaKujjF4HL91UXZFetkkKiBIpIrH5+XbiX9z7H9/Tv8NSy/zTvXp3hFl3dr9gO722i/96dTq4th23Gqtih4cA9x8Wd7RChR9yAK/ffSj1lW6RhBWj1j2JCPFCm1TJD5iO3bIeuHm2sAuafKKoWT/VCUkKRwt9Wwh9yF20vMQ3kFw=";
        String sign = sign(getPrivateKey(key),signString(map));
        System.out.println(sign);
    }
}
