/**
 *
 */
package com.csf.whoami.common.domain.mw;

//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.NotNull;

/**
 * @author mba0019
 *
 */
public class TypeInfoDomain {
    //	@NotBlank(message = "tour name must not be blank")
//  @NotNull(message = "begin date must not be blank")
    private String id;
    private String infoType;
    private String typeCode;
    private boolean isActive;

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the infoType
     */
    public String getInfoType() {
        return infoType;
    }

    /**
     * @param infoType the infoType to set
     */
    public void setInfoType(String infoType) {
        this.infoType = infoType;
    }

    /**
     * @return the typeCode
     */
    public String getTypeCode() {
        return typeCode;
    }

    /**
     * @param typeCode the typeCode to set
     */
    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    /**
     * @return the isActive
     */
    public boolean isActive() {
        return isActive;
    }

    /**
     * @param isActive the isActive to set
     */
    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    /**
     *
     */
    public TypeInfoDomain() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @param id
     * @param infoType
     * @param typeCode
     * @param isActive
     */
    public TypeInfoDomain(String id, String infoType, String typeCode, boolean isActive) {
        super();
        this.id = id;
        this.infoType = infoType;
        this.typeCode = typeCode;
        this.isActive = isActive;
    }
}