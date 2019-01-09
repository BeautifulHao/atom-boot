package com.atom.smart.utlis;

/**
 * @author BeautifulHao
 * @description redis操作类
 * @create 2018-10-06 16:17
 **/

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class RedisOperator<T> {

    @Autowired
    private RedisTemplate redisTemplate;

    /*获取缓存，如果不存在则自动获取*/
    public T get(String key, String id, BaseMapper<T> baseMapper){
        boolean exist = redisTemplate.hasKey(key);
        if(exist){
            return (T)redisTemplate.opsForValue().get(key);
        }
        else{
            T dbItem = baseMapper.selectById(id);
            if(dbItem ==null)
            {
                return null;
            }
            else{
                redisTemplate.opsForValue().set(key,dbItem);
                return dbItem;
            }
        }
    }
    /*获取缓存，如果不存在则自动获取*/
    public List<T> getList(String key, BaseMapper<T> baseMapper, Wrapper<T> wrapper){

        boolean exist = redisTemplate.hasKey(key);
        if(exist){
            return (List<T>) redisTemplate.opsForValue().get(key);
        }
        else{
            List<T> dbItem = baseMapper.selectList(wrapper);

            if(dbItem ==null)
            {
                return null;
            }
            else{
                redisTemplate.opsForValue().set(key,dbItem);
                return dbItem;
            }
        }

    }
    /*移除缓存*/
    public void remove(String key){
        boolean exist = redisTemplate.hasKey(key);
        if(exist){
             redisTemplate.delete(key);
        }
    }



}
