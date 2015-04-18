

package models;

import com.google.gson.annotations.Expose;

/**
 * jsonschema2pojo
 */

public class Response {

    @Expose
    private ResponseData responseData;
    @Expose
    private Object responseDetails;
    @Expose
    private Integer responseStatus;

    /**
     *
     * @return
     * The responseData
     */
    public ResponseData getResponseData() {
        return responseData;
    }

    /**
     *
     * @param responseData
     * The responseData
     */
    public void setResponseData(ResponseData responseData) {
        this.responseData = responseData;
    }

    /**
     *
     * @return
     * The responseDetails
     */
    public Object getResponseDetails() {
        return responseDetails;
    }

    /**
     *
     * @param responseDetails
     * The responseDetails
     */
    public void setResponseDetails(Object responseDetails) {
        this.responseDetails = responseDetails;
    }

    /**
     *
     * @return
     * The responseStatus
     */
    public Integer getResponseStatus() {
        return responseStatus;
    }

    /**
     *
     * @param responseStatus
     * The responseStatus
     */
    public void setResponseStatus(Integer responseStatus) {
        this.responseStatus = responseStatus;
    }

}