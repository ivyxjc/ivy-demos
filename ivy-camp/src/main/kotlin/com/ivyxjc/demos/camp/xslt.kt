package com.ivyxjc.demos.camp

import com.ivyxjc.demos.common.fileToString
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.nio.charset.Charset
import java.util.*
import javax.xml.transform.TransformerFactory
import javax.xml.transform.stream.StreamResult
import javax.xml.transform.stream.StreamSource
import kotlin.random.Random

private fun accountSwitching(): String {
    val map = HashMap<String, String>()
    map["UniqueReferenceNumber"] = Random.nextLong().toString()
    val outputStream = ByteArrayOutputStream()
    val template = fileToString("xslt/iso-20022-AccountSwitching.xslt")
    val templateSource = StreamSource(ByteArrayInputStream(template.toByteArray()))
    val xmlSource = StreamSource(ByteArrayInputStream(template.toByteArray()))

    val transformerFactory = TransformerFactory.newInstance()
    val transformer = transformerFactory.newTransformer(templateSource)
    transformer.setParameter("map", map)

    transformer.transform(xmlSource, StreamResult(outputStream))
    return outputStream.toString(Charset.defaultCharset().toString())
}

private fun addNamespace(): String {
    val outputStream = ByteArrayOutputStream()
    val template = fileToString("xslt/namespace.xslt")
    val xml = fileToString("xslt/namespace.xml")
    val templateSource = StreamSource(ByteArrayInputStream(template.toByteArray()))
    val xmlSource = StreamSource(ByteArrayInputStream(xml.toByteArray()))

    val transformerFactory = TransformerFactory.newInstance()
    val transformer = transformerFactory.newTransformer(templateSource)

    transformer.transform(xmlSource, StreamResult(outputStream))
    return outputStream.toString(Charset.defaultCharset().toString())
}


fun main() {
    println(accountSwitching())
    println(addNamespace())
}