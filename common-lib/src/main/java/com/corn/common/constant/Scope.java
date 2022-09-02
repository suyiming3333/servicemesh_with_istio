package com.corn.common.constant;

/**
 * 访问范围常量类
 * <p>
 * 目前有“来自浏览器的访问”和“来自微服务的访问”两个常量
 *
 **/
public interface Scope {

    String BROWSER = "BROWSER";

    String SERVICE = "SERVICE";

}
