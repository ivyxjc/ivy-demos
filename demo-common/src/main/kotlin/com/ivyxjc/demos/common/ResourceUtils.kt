@file:JvmName("ResourceUtils")

package com.ivyxjc.demos.common

import org.apache.commons.io.IOUtils
import java.nio.charset.Charset
import java.util.*

fun getProperty(key: String, filename: String): String {
    val input = topLevelClass.classLoader.getResourceAsStream(filename)
    val prop = Properties()
    prop.load(input)
    return prop.getProperty(key)
}

fun getPrivateProperty(key: String): String {
    return getProperty(key, "private.properties")
}

fun fileToString(filename: String): String {
    return fileToString(filename, Charset.defaultCharset())
}

fun fileToString(filename: String, charset: Charset): String {
    val input = Base::class.java.classLoader.getResourceAsStream(filename)
    return IOUtils.toString(input, charset)
}
