package com.ua.fishingforum.common.mapper;


public interface Mapper<D, S> {
    D map(S source);
}
