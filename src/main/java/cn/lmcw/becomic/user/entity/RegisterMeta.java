package cn.lmcw.becomic.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class RegisterMeta {
    private static final long serialVersionUID = 2L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer uid;

    private String ip;

    @TableField("user_agent")
    private String userAgent;

    @TableField("at_time")
    private Integer atTime = Math.toIntExact(System.currentTimeMillis() / 1000);

}
