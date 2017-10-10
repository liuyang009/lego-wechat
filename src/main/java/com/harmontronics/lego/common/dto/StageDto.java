package com.harmontronics.lego.common.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.harmontronics.lego.util.format.DateDeserializer;
import com.harmontronics.lego.util.format.DateSerializer;


import java.io.Serializable;
import java.util.Date;


public class StageDto implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 3618842147613180798L;
    private Long prdOrderId;
    private Integer stageSeq;
    private String nodeName;
    @JsonDeserialize(using = DateDeserializer.class)
    @JsonSerialize(using = DateSerializer.class)
    private Date startDate;
    @JsonDeserialize(using = DateDeserializer.class)
    @JsonSerialize(using = DateSerializer.class)
    private Date endDate;
    @JsonDeserialize(using = DateDeserializer.class)
    @JsonSerialize(using = DateSerializer.class)
    private Date preStartDate;
    @JsonDeserialize(using = DateDeserializer.class)
    @JsonSerialize(using = DateSerializer.class)
    private Date preEndDate;
    @JsonDeserialize(using = DateDeserializer.class)
    @JsonSerialize(using = DateSerializer.class)
    private Date createDate;

    public Long getPrdOrderId() {
        return prdOrderId;
    }

    public void setPrdOrderId(Long prdOrderId) {
        this.prdOrderId = prdOrderId;
    }

    public Integer getStageSeq() {
        return stageSeq;
    }

    public void setStageSeq(Integer stageSeq) {
        this.stageSeq = stageSeq;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public Date getPreStartDate() {
        return preStartDate;
    }

    public void setPreStartDate(Date preStartDate) {
        this.preStartDate = preStartDate;
    }

    public Date getPreEndDate() {
        return preEndDate;
    }

    public void setPreEndDate(Date preEndDate) {
        this.preEndDate = preEndDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}


