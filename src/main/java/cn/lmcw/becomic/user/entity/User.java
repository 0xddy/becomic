package cn.lmcw.becomic.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.beans.Transient;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author dingyong
 * @since 2020-06-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class User implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 用户ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户名
     */
    private String uname;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 用户钱包余额
     */
    private Integer wallet;

    /**
     * 其他配置选项JSON
     */
    private String options;

    @TableField(exist = false)
    private String token;

}
