import cn.ubibi.jettyboot.demotest.configs.SystemConfig;
import cn.ubibi.jettyboot.framework.commons.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import java.util.List;

public class TempTest {
    public static void main(String [] args){

        String str = "[1,2,3]";

        List x = JSON.parseObject(str, JSONArray.class);


        System.out.println(x);
    }
}
