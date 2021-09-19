package com.example.system_demo.model;

public class model_process {

    private int processID;
    private int userID;
    private int serviceID;
    private String processConfirmTime; /*确认用时*/
    private String processDispatchTime; /*派单用时*/
    private String Exception; /*异常退出情况*/
    private String serviceRectify; /*服务整改*/
    private String processExecute; /*执行过程*/

    public model_process() {
    }

    public int getProcessID() {
        return processID;
    }

    public void setProcessID(int processID) {
        this.processID = processID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getServiceID() {
        return serviceID;
    }

    public void setServiceID(int serviceID) {
        this.serviceID = serviceID;
    }

    public String getProcessConfirmTime() {
        return processConfirmTime;
    }

    public void setProcessConfirmTime(String processConfirmTime) {
        this.processConfirmTime = processConfirmTime;
    }

    public String getProcessDispatchTime() {
        return processDispatchTime;
    }

    public void setProcessDispatchTime(String processDispatchTime) {
        this.processDispatchTime = processDispatchTime;
    }

    public String getException() {
        return Exception;
    }

    public void setException(String exception) {
        Exception = exception;
    }

    public String getServiceRectify() {
        return serviceRectify;
    }

    public void setServiceRectify(String serviceRectify) {
        this.serviceRectify = serviceRectify;
    }

    public String getProcessExecute() {
        return processExecute;
    }

    public void setProcessExecute(String processExecute) {
        this.processExecute = processExecute;
    }
}
