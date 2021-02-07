package cdu_2017.xuye.cov19.common.model;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.HashMap;

public  class R extends HashMap<String, Object> {
    public R(){
        put("code",0);
        put("msg","success");
    }
    public static R put(Object msg){
        R r=  new R();
        r.put("code",0);
        r.put("msg",msg);
        return r;
    }
    public static R ok(){
        R r = new R();
        r.put("code",0);
        r.put("msg","success");
        return r;
    }
    public static R error(String msg){
        R r = new R();
        r.put("code",500);
        r.put("msg",msg);
        return r;
    }
    public static R error(Integer code, String msg){
        R r = new R();
        r.put("code",code);
        r.put("msg",msg);
        return r;
    }
}
