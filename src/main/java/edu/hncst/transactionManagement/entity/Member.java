package edu.hncst.transactionManagement.entity;

/**
 * @author 14360
 */
public class Member {
    /**
     * 成员id
     * 成员名称
     */
    private Integer id;
    private String memberName;

    /**
     * 构造 有参 and 空参
     */
    public Member(Integer id, String memberName) {
        this.id = id;
        this.memberName = memberName;
    }

    public Member(){}


    /**
     * get and set
     */
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }
}
