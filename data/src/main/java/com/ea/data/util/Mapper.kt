package com.ea.data.util

interface Mapper<E,D> {
    fun mapFromEntity(type:E): D
}