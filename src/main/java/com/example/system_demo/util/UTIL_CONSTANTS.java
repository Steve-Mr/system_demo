package com.example.system_demo.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class UTIL_CONSTANTS {

    /* 服务详情每项对应评分 */
    public static final Set<String> score_10 = new HashSet<>(
            Arrays.asList("serviceName", "serviceIntro", "servicePeople")
    );

    public static final  Set<String> score_7 = new HashSet<>(
            Arrays.asList("servicePhone", "serviceDuration", "servicePrice", "serviceProcedure", "serviceApplicable")
    );

    public static final  Set<String> score_0 = new HashSet<>(
            Arrays.asList("serviceID", "serviceCategory", "servicePicture", "serviceLogo")
    );


    /* 客户服务评价打分系数 */
    public static final Set<String> scale_4 = new HashSet<>(
            Arrays.asList("evaluateProcedure", "evaluateSupport")
    );

    public static final Set<String> scale_1 = new HashSet<>(
            Arrays.asList("evaluateTimely", "evaluateRespond", "evaluateClear","evaluatePersonalize")
    );

    public static final Set<String> scale_0 = new HashSet<>(
            Arrays.asList("evaluateID", "userID", "serviceID")
    );

    /**
     * 有形性
     */
    public static final Set<String> tangibility = new HashSet<>(
            Arrays.asList("serviceName", "serviceIntro", "servicePeople", "serviceDevice")
    );


    //可靠性
    public static final Set<String> reliability = new HashSet<>(
            Arrays.asList("servicePhone", "serviceDuration", "servicePrice", "servicePost", "serviceComplaint")
    );

    //响应性
    public static final Set<String> responsiveness = new HashSet<>(
            Arrays.asList("serviceConfirmation", "serviceDispatchTime")
    );

    //保证性
    public static final Set<String> guarantee = new HashSet<>(
            Arrays.asList("serviceProcedure", "serviceLegalInfo")
    );

    //移情性
    public static final Set<String> empathy = new HashSet<>(
            Arrays.asList("serviceApplicable", "servicePersonalize")
    );

}
