package com.nit.wx.model;

import java.io.Serializable;

public class UserListId  implements Serializable{

    private Integer userid;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public UserListId(Integer userid, String openid) {

        this.userid = userid;
        this.openid = openid;
    }

    private String openid;

    public Integer getUserid() {
        return userid;
    }

    public UserListId() {
    }


    public void setUserid(Integer userid) {
        this.userid = userid;
    }


    @Override
    public int hashCode(){
        final int PRIME = 31;
        int result = 1;
        result = PRIME*result+((userid == null)?0:userid.hashCode());
        result = PRIME*result+((openid == null)?0:openid.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj){
            return true;
        }
        if (obj == null){
            return false;
        }
        if (getClass() != obj.getClass()){
            return false;
        }

        final UserListId other = (UserListId) obj;

        if (userid == null){
            if (other.userid != null)
                return false;
        }else if (!userid.equals(other.userid)){
            return false;
        }
        if (openid == null){
            if (other.openid != null)
                return false;
        }else if (!openid.equals(other.openid)){
            return false;
        }

        return true;
    }
}
