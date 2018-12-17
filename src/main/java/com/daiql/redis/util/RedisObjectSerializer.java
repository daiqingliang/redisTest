package com.daiql.redis.util;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.serializer.support.DeserializingConverter;
import org.springframework.core.serializer.support.SerializingConverter;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

public class RedisObjectSerializer implements RedisSerializer<Object> {
    // 为了方便进行对象与字节数组的转换，所以应该首先准备出两个转换器
    private Converter<Object,byte[]> serializingConverter = new SerializingConverter();
    private Converter<byte[], Object> deserializingConverter = new DeserializingConverter();
    private static final byte[] EMPTY_BYTE_ARRAY = new byte[0]; //做一个空数组，不是null
    @Override
    public byte[] serialize(Object obj) throws SerializationException {
        if (obj == null) {
            return EMPTY_BYTE_ARRAY;
        }
        return this.serializingConverter.convert(obj); // 将对象变为字节数组
    }

    public Object deserialize(byte[] data) throws SerializationException {
        if (data == null || data.length == 0) {
            return null;
        }
        return this.deserializingConverter.convert(data);
    }
}
