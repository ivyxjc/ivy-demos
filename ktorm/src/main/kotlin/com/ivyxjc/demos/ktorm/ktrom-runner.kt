package com.ivyxjc.demos.ktorm

import org.ktorm.database.Database
import org.ktorm.dsl.update
import org.ktorm.entity.sequenceOf
import org.ktorm.logging.ConsoleLogger
import org.ktorm.logging.LogLevel
import org.ktorm.support.mysql.MySqlDialect

val Database.rawTransaction get() = this.sequenceOf(RawTransactionDO)


fun main() {
    val database = Database.connect(
        url = "jdbc:mysql://rm-uf62qxy7vi2xjiu868o.mysql.rds.aliyuncs.com:3306/libra",
        driver = "com.mysql.cj.jdbc.Driver",
        user = "ivyxjc",
        password = "ZM1Q^vWsQrWv1Bc2",
        logger = ConsoleLogger(threshold = LogLevel.DEBUG),
        dialect = MySqlDialect()
    )
    println("+++++++++++++++")

    database.update(RawTransactionDO) {
        set(it.createdFrom, "ivyxjc")
    }

//    val query = database.from(RawTransactionDO)
//        .select()
//
//
//    val list = query.map { RawTransactionDO.createEntity(it) }
//
//
//    list.forEach {
//        it.createdBy = "ivyxjc"
//        database.rawTransaction.update(it)
//    }

}

