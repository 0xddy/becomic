package cn.lmcw.becomic.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("register_meta")
public class RegisterMeta {

    private static final long serialVersionUID = 2L;

    @TableId(value = "uid")
    private Integer uid;

    private String ip;

    @TableField("user_agent")
    private String userAgent;

    @TableField("at_time")
    private Integer atTime = Math.toIntExact(System.currentTimeMillis() / 1000);

    @TableField("puid")
    private Integer pUid;

}
