package cn.com.leadfar.cms.utils;

import cn.com.leadfar.cms.backend.dao.ArticleDao;
import cn.com.leadfar.cms.backend.dao.ChannelDao;
import cn.com.leadfar.cms.backend.dao.impl.ArticleDaoImpl;

/**
 * Created by tusizi on 2015/11/13.
 */
public interface BeanFactory {
//    public  ArticleDao getArticleDao();
//    public ChannelDao getChannelDao();
    public Object getBean(String name);
}