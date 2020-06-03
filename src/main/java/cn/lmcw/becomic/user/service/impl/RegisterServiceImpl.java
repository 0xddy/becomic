package cn.lmcw.becomic.user.service.impl;

import cn.lmcw.becomic.user.entity.RegisterMeta;
import cn.lmcw.becomic.user.mapper.RegisterMetaMapper;
import cn.lmcw.becomic.user.service.IRegisterMetaService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class RegisterServiceImpl extends ServiceImpl<RegisterMetaMapper, RegisterMeta> implements IRegisterMetaService {
}
