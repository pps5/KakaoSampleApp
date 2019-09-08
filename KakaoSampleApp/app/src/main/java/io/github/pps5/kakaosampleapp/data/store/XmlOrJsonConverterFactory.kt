package io.github.pps5.kakaosampleapp.data.store

import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type
import kotlin.reflect.KClass

class AnnotatedConverterFactory(
    private val factoryMap: Map<KClass<*>, Converter.Factory>
) : Converter.Factory() {

    override fun responseBodyConverter(
        type: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *>? {
        for (annotation in annotations) {
            val factory = factoryMap[annotation.annotationClass]
            if (factory != null) {
                return factory.responseBodyConverter(type, annotations, retrofit)
            }
        }
        return null
    }

    class Builder {
        var factoryMap: MutableMap<KClass<*>, Converter.Factory> = LinkedHashMap()

        fun add(factoryType: KClass<out Annotation>?, factory: Converter.Factory?): Builder {
            if (factoryType == null) throw NullPointerException("factoryType is null")
            if (factory == null) throw NullPointerException("factory is null")
            factoryMap[factoryType] = factory
            return this
        }

        fun build(): AnnotatedConverterFactory {
            return AnnotatedConverterFactory(factoryMap)
        }

    }
}
