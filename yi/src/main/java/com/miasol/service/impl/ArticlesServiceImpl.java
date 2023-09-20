package com.miasol.service.impl;

import com.miasol.entity.Articles;
import com.miasol.mapper.ArticlesMapper;
import com.miasol.service.ArticlesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author miasol
 *
 */
@Service
public class ArticlesServiceImpl extends ServiceImpl<ArticlesMapper, Articles> implements ArticlesService {

    @Override
    public List<Articles> getLikeList(String userCode) {
        return baseMapper.getLikeList(userCode);
    }

    @Override
    public List<Articles> getCollectList(String userCode) {
        return baseMapper.getCollectList(userCode);
    }
}
