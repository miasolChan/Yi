package com.miasol.service;

import com.miasol.entity.Articles;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author miasol
 *
 */
public interface ArticlesService extends IService<Articles> {

    List<Articles> getLikeList(String userCode);

    List<Articles> getCollectList(String userCode);
}
