
package com.lzk.pojo;

/**
 * @author xiaokang
 * @date 2018-06-01 15:09
 * @desc
 */
public class ProcResult {
    /**
     * 输出代码
     */
    private Integer outCode;
    /**
     * 输出信息
     */
    private String outmsg;
    /**
     * 结果集
     */
    private Object data;
    /**
     * 总记录数
     */
    private Integer countNum;

    public ProcResult() {
    }

    public ProcResult(Integer outCode, String outmsg) {
        this.outCode = outCode;
        this.outmsg = outmsg;
    }

    public Integer getOutCode() {
        return outCode;
    }

    public void setOutCode(Integer outCode) {
        this.outCode = outCode;
    }

    public String getOutmsg() {
        return outmsg;
    }

    public void setOutmsg(String outmsg) {
        this.outmsg = outmsg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Integer getCountNum() {
        return countNum;
    }

    public void setCountNum(Integer countNum) {
        this.countNum = countNum;
    }
}
